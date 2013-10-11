package com.jeremyfeinstein.slidingmenu.example;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.jeremyfeinstein.slidingmenu.example.fragments.ColorFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ViewPagerActivity extends BaseActivity {

	public ViewPagerActivity() {
		super(R.string.viewpager);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame_two, new SampleListFragment())
		.commit();
		
		
		ViewPager vp = new ViewPager(this);
		vp.setId("VP".hashCode());
		vp.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
		setContentView(vp);

		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) { }

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) { }

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					getSlidingMenu().setMode(SlidingMenu.LEFT);
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				case 4:
					getSlidingMenu().setMode(SlidingMenu.RIGHT);
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				default:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					break;
				}
			}

		});
		
		vp.setCurrentItem(0);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	}

	public class ColorPagerAdapter extends FragmentPagerAdapter {
		
		private ArrayList<Fragment> mFragments;

		private final int[] COLORS = new int[] {
			R.color.red,
			R.color.green,
			R.color.blue,
			R.color.white,
			R.color.black
		};
		
		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS)
				mFragments.add(new ColorFragment(color));
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

	}

}
