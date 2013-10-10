package com.pps.netnew.activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.pps.netnew.adapter.MainPagerAdapter;
import com.pps.netnew.fragment.LeftCategoryFragment;
import com.pps.netnew.fragment.RightPerMsgCenterFragment;

public class MainActivity extends SlidingFragmentActivity {

	private ImageButton main_left_imgbtn;
	private ImageButton main_right_imgbtn;
	private ViewPager myViewPager;
	private PagerTitleStrip pagertitle;
	private PagerAdapter mAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		initSlidingMenu();
		initView();
		initValidata();
		bindData();
		initListener();
	}
	/**
	 * 初始化SlidingMenu视图
	 */
	private void initSlidingMenu(){
		// 设置滑动菜单的属性值
	    getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);	
		getSlidingMenu().setShadowDrawable(R.drawable.shadow);
		getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
		getSlidingMenu().setFadeDegree(0.35f);
		//设置主界面的视图		
		setContentView(R.layout.main);
		// 设置左边菜单打开后的视图界面
	    setBehindContentView(R.layout.left_content);	
	    getSupportFragmentManager().beginTransaction().replace(R.id.left_content_id, new LeftCategoryFragment()).commit();
	    // 设置右边菜单打开后的视图界面
		getSlidingMenu().setSecondaryMenu(R.layout.right_content);
		getSupportFragmentManager().beginTransaction().replace(R.id.right_content_id, new RightPerMsgCenterFragment()).commit();
	}
	
	private void initView()
	{
		main_left_imgbtn=(ImageButton)this.findViewById(R.id.main_left_imgbtn);
		main_right_imgbtn=(ImageButton)this.findViewById(R.id.main_right_imgbtn);
		myViewPager=(ViewPager)this.findViewById(R.id.myviewpager);
		pagertitle=(PagerTitleStrip)this.findViewById(R.id.pagertitle);
		
		
	}
	
	/**
	 * 初始化变量
	 */
	private void initValidata()
	{
		pagertitle.setTextSize(0, 25);
		mAdapter=new MainPagerAdapter(getSupportFragmentManager());
		
	}
	
	/**
	 * 绑定数据
	 */
	private void bindData()
	{
		myViewPager.setAdapter(mAdapter);
		myViewPager.setCurrentItem(0);
	}
	private void initListener()
	{
		main_left_imgbtn.setOnClickListener(new MySetOnClickListener());
		main_right_imgbtn.setOnClickListener(new MySetOnClickListener());
		myViewPager.setOnPageChangeListener(new MySetOnPageChangeListener());
	}
	
	/**
	 * ViewPager页面选项卡切换监听器
	 */
	class MySetOnPageChangeListener implements OnPageChangeListener
	{
		public void onPageScrollStateChanged(int arg0) {				
		}
		public void onPageScrolled(int arg0, float arg1, int arg2) {		
		}
		public void onPageSelected(int arg0) {		
		}	
	}
	/**
	 * 进行侧滑界面打开与关闭
	 * @author jiangqq
	 *
	 */
	class MySetOnClickListener implements OnClickListener
	{

		public void onClick(View v) {
		  toggle();
		}
	}
}
