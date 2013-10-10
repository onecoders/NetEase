package com.pps.netnew.fragment;

import com.pps.netnew.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * ²Æ¾­Fragment
 * @author jiangqq
 *
 */
public class EconomicsFragment extends Fragment {
	private View mView;
	
	  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

			mView=inflater.inflate(R.layout.economics, container, false);
		return mView;
	}
}
