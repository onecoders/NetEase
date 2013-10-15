package com.example.loadingbitmaptest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity implements ImgCallback {

	private ImageView imageview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageview = (ImageView) findViewById(R.id.imageview);

		ImgLoader loader = new ImgLoader(this);

		loader.loadImg("http://goo.gl/yxNeG", this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void refresh(Bitmap bitmap) {
		imageview.setImageBitmap(bitmap);
	}

}
