package com.example.test;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SlipToPaddingRefresh extends ListActivity implements OnScrollListener {
	Aleph0 adapter = new Aleph0();
	int lastItem = 0;
	int mProgressStatus = 0;
	private Handler mHandler = new Handler();
	ProgressBar progressBar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout searchLayout = new LinearLayout(this);
		searchLayout.setOrientation(LinearLayout.HORIZONTAL);
		progressBar = new ProgressBar(this);
		progressBar.setPadding(0, 0, 15, 0);
		searchLayout.addView(progressBar, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		TextView textView = new TextView(this);
		textView.setText("加载中...");
		textView.setGravity(Gravity.CENTER_VERTICAL);
		searchLayout.addView(textView, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));
		searchLayout.setGravity(Gravity.CENTER);
		LinearLayout loadingLayout = new LinearLayout(this);
		loadingLayout.addView(searchLayout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		loadingLayout.setGravity(Gravity.CENTER);
		getListView().addFooterView(loadingLayout);
		// Start lengthy operation in a background thread
		// new Thread(new Runnable() {
		// public void run() {
		// while (mProgressStatus < 100) {
		//
		// // Update the progress bar
		// mHandler.post(new Runnable() {
		// public void run() {
		// progressBar.setProgress(mProgressStatus);
		// }
		// });
		// }
		// }
		// }).start();
		registerForContextMenu(getListView());
		setListAdapter(adapter);
		getListView().setOnScrollListener(this);
	}

	public void onScroll(AbsListView v, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		lastItem = firstVisibleItem + visibleItemCount - 1;
		System.out.println("lastItem:" + lastItem);
	}

	public void onScrollStateChanged(AbsListView v, int state) {
		if (lastItem == adapter.count
				&& state == OnScrollListener.SCROLL_STATE_IDLE) {
			adapter.count += 10;
			adapter.notifyDataSetChanged();
		}
		// if(state == OnScrollListener.SCROLL_STATE_IDLE ) {
		// adapter.count += 10;
		// adapter.notifyDataSetChanged();
		// }
	}

	class Aleph0 extends BaseAdapter {
		int count = 10;

		public int getCount() {
			return count;
		}

		public Object getItem(int pos) {
			return pos;
		}

		public long getItemId(int pos) {
			return pos;
		}

		public View getView(int pos, View v, ViewGroup p) {
			TextView view = new TextView(SlipToPaddingRefresh.this);
			view.setText("entry " + pos);
			view.setHeight(90);
			return view;
		}
	}
}