package com.example.httpclienttest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				HttpClientTest1();
				HttpClientGetMethod();
				register();
				return null;
			}
		}.execute();

	}

	private void register() {
		final String url = "http://yourdomain/context/adduser";
		NameValuePair param1 = new BasicNameValuePair("username",
				"registerTest");
		NameValuePair param2 = new BasicNameValuePair("password", "123654789");

		int resultId = -1;

		try {
			String response = CustomerHttpClient.post(url, param1, param2);
			JSONObject root = new JSONObject(response);
			resultId = Integer.parseInt(root.getString("userid"));
			Log.d("New user ID:", "---New user ID:---" + resultId);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void HttpClientTest1() {
		try {
			HttpClient httpclient = CustomerHttpClient.getHttpClient();
			HttpGet request = new HttpGet("http://www.google.com");
			String response = httpclient.execute(request,
					new BasicResponseHandler());
			Log.v("response text", response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void HttpClientGetMethod() {
		// 先将参数放入List，再对参数进行URL编码
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("param1", "中国"));
		params.add(new BasicNameValuePair("param2", "value2"));

		// 对参数编码
		String param = URLEncodedUtils.format(params, "UTF-8");

		// baseUrl
		String baseUrl = "http://ubs.free4lab.com/php/method.php";

		// 将URL与参数拼接
		HttpGet getMethod = new HttpGet(baseUrl + "?" + param);

		HttpClient httpClient = CustomerHttpClient.getHttpClient();

		try {
			HttpResponse response = httpClient.execute(getMethod); // 发起GET请求
			Log.d("---TAG---", "resCode = "
					+ response.getStatusLine().getStatusCode()); // 获取响应码
			Log.d("---TAG---",
					"result = "
							+ EntityUtils.toString(response.getEntity(),
									"utf-8"));// 获取服务器响应内容
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
