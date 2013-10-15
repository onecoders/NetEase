package com.example.loadingbitmaptest;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

public class ImgLoader {

	private Map<String, SoftReference<Bitmap>> imgCache = new HashMap<String, SoftReference<Bitmap>>();

	public ImgLoader(Context ctx) {
		manager = new PicManager(imgCache, ctx);
	}

	private PicManager manager;
	private Handler handler = new Handler();

	private ExecutorService threadPool = Executors.newFixedThreadPool(5);

	public Bitmap loadImg(final String url, final ImgCallback callback) {

		// 先从缓存中读取图片资源
		Bitmap bitmap = null;
		bitmap = manager.getImgFromCache(url);

		if (null == bitmap) {
			// 开启线程从网络上下载
			threadPool.submit(new Runnable() {// submit方法确保下载是从线程池中的线程执行
						@Override
						public void run() {
							final Bitmap bitmapFromUrl = manager
									.getBitMapFromUrl(url);
							Log.d("---123---", "123");
							try {
								manager.writePic2SDCard(bitmapFromUrl, url);
							} catch (Exception e) {
								e.printStackTrace();
							}
							handler.post(new Runnable() {
								@Override
								public void run() {
									callback.refresh(bitmapFromUrl);
								}
							});
						}
					});
		} else {

		}
		return bitmap;
	}

}