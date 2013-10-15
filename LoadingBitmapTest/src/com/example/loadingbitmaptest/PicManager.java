package com.example.loadingbitmaptest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class PicManager {

	private Map<String, SoftReference<Bitmap>> imgCache;
	private Context ctx;

	public PicManager(Map<String, SoftReference<Bitmap>> imgCache, Context ctx) {
		this.ctx = ctx;
		this.imgCache = imgCache;
	}

	/**
	 * 从网络上下载
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getBitMapFromUrl(String url) {
		Bitmap bitmap = null;
		URL u = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 从文件中读取
	 * 
	 * @return
	 * @throws Exception
	 */
	private Bitmap getBitMapFromSDCard(String url) {
		Bitmap bitmap = null;
		String filename;
		try {
			filename = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/testfortest/" + MD5Util.getMD5(url);
			FileInputStream fis = new FileInputStream(filename);
			bitmap = BitmapFactory.decodeStream(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 从缓存中读取
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Bitmap getImgFromCache(String url) {
		Bitmap bitmap = null;
		// 从内存中读取
		if (imgCache.containsKey(url)) {
			synchronized (imgCache) {
				SoftReference<Bitmap> bitmapReference = imgCache.get(url);
				if (null != bitmapReference) {
					bitmap = bitmapReference.get();
				}
			}
		} else {// 否则从文件中读取
			bitmap = getBitMapFromSDCard(url);
			// 将图片保存进内存中
			imgCache.put(url, new SoftReference<Bitmap>(bitmap));
		}
		return bitmap;
	}

	/**
	 * 将图片写入sdcard中
	 * 
	 * @param bitmap
	 * @param url
	 * @throws Exception
	 */
	public void writePic2SDCard(Bitmap bitmap, String url) {
		String filename;
		ByteArrayInputStream bis = null;
		FileOutputStream fos = null;
		try {
			filename = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/testfortest/" + MD5Util.getMD5(url);
			fos = new FileOutputStream(filename);
			byte[] bitmapByte = PictureUtil.Bitmap2Bytes(bitmap);
			bis = new ByteArrayInputStream(bitmapByte);
			int len = 0;
			byte[] b = new byte[bis.available()];
			while ((len = bis.read(b)) != -1) {
				fos.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}