package com.geniuseoe.wyactionbar;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * 
 * @author geniuseoe2012
 *  more brilliant£¬please pay attention to my csdn blog --> http://blog.csdn.net/geniuseoe2012 
 *  android  develop group £º298044305
 */
public class SliderTabPagerActivity extends SherlockFragmentActivity implements OnPageChangeListener,
												TabListener{

	private  int COUNT = 0;
	private List<DataStruct> mList;
	
	private ViewPager mViewPager;
	private LayoutInflater mInflater;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);		
		setContentView(R.layout.slider_tab_pager_layout);		 
		setupViews();
		initData();
	}
	
	  
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getSupportMenuInflater().inflate(R.menu.main_menu, menu);
	    return true;
	    
	}

	  @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:
	        	Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu_setting:
	        	Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }


	private void setupViews(){
		 mInflater = getLayoutInflater();
		
		 mViewPager = (ViewPager)findViewById(R.id.pager);
		 mViewPager.setOnPageChangeListener(this);

	     ActionBar actionBar = getSupportActionBar();
	     actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	     
	     String[] arrStrings = getResources().getStringArray(R.array.sections);
	     COUNT = arrStrings.length;
         for (int i = 0; i < COUNT; i++) {
        	Tab tab =  actionBar.newTab();
        	tab.setCustomView(getTabView(arrStrings[i]));
        	tab.setTabListener(this);
        	actionBar.addTab(tab);      
         }
    
         actionBar.setDisplayHomeAsUpEnabled(true);
         actionBar.setLogo(R.drawable.biz_pics_main_back_normal);
         actionBar.setTitle("ÍøÒ×Í¼Æ¬");


	}
	
	private void initData(){
		mList = new ArrayList<DataStruct>();
		for(int i = 0; i < COUNT; i++){
			DataStruct struct = new DataStruct();
			struct.index = i;
			struct.daString = "tab-->" + i;
			mList.add(struct);
		}
		mViewPager.setAdapter(new SliderPagerAdapter(getSupportFragmentManager(), mList));
	}


	private View getTabView(String title){
		View view = mInflater.inflate(R.layout.tab_item_layout, null);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(title);
		return view;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onPageSelected(int position) {
	      getSupportActionBar().setSelectedNavigationItem(position);
	}



	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		 mViewPager.setCurrentItem(tab.getPosition());
	}



	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	
	class SliderPagerAdapter extends FragmentStatePagerAdapter{

		private List<DataStruct> mList;
		
		public SliderPagerAdapter(FragmentManager fm, List<DataStruct> list) {
			super(fm);
			mList = list;
		}

		@Override
		public Fragment getItem(int pos) {
			return new SliderFragment(mList.get(pos));
		}

		@Override
		public int getCount() {
		
			return mList.size();
		}
		
	}

	
	public static class SliderFragment extends SherlockFragment{

		private DataStruct mStruct;
		
		public SliderFragment(DataStruct struct){
			mStruct = struct;
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {


			View view = inflater.inflate(R.layout.layout1, null);
			TextView tView = (TextView) view.findViewById(R.id.textView);
			tView.setText(mStruct.toString());
			
			return view;
		}
		
		
	}
	
}
