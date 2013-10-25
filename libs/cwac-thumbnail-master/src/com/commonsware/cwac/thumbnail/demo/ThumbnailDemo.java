/***
	Copyright (c) 2008-2009 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.commonsware.cwac.thumbnail.demo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.net.URI;
import java.util.ArrayList;
import winterwell.jtwitter.Twitter;
import com.commonsware.cwac.thumbnail.ThumbnailAdapter;
import com.commonsware.cwac.thumbnail.ThumbnailBus;

public class ThumbnailDemo extends ListActivity {
	private static final String FETCH_ACTION="com.commonsware.cwac.cache.demo.FETCH_ACTION";
	private static final int[] IMAGE_IDS={R.id.avatar};
	private SharedPreferences prefs=null;
	private Twitter client=null;
	private ThumbnailAdapter thumbs=null;
	private ArrayList<TimelineEntry> model=null;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		prefs=PreferenceManager.getDefaultSharedPreferences(this);
		
		String user=prefs.getString("user", null);
		String password=prefs.getString("password", null);
		
		if (user==null || password==null) {
			startActivity(new Intent(this, TWPrefs.class));
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		model=(ArrayList<TimelineEntry>)getLastNonConfigurationInstance();
		
		if (model==null) {
			model=new ArrayList<TimelineEntry>();
			
			String user=prefs.getString("user", null);
			String password=prefs.getString("password", null);
			
			if (user!=null && password!=null) {
				client=new Twitter(user, password);
				
				for (Twitter.Status s :client.getFriendsTimeline()) {
					model.add(new TimelineEntry(s.user.screenName,
																						s.createdAt.toString(),
																						s.text,
																						s.user.profileImageUrl));
				}
			}
		}
		
		thumbs=new ThumbnailAdapter(this, new TimelineAdapter(),
															 ((Application)getApplication()).getCache(),
																IMAGE_IDS);
																				
		setListAdapter(thumbs);																				
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		thumbs.close();
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		return(model);
	}
	
	private ThumbnailBus getBus() {
		return(((Application)getApplication()).getBus());		
	}
	
	private void goBlooey(Throwable t) {
		Log.e("CacheDemo", "Exception!", t);
		
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		
		builder
			.setTitle(R.string.exception)
			.setMessage(t.toString())
			.setPositiveButton(R.string.ok, null)
			.show();
	}
	
	class TimelineEntry {
		String friend="";
		String createdAt="";
		String status="";
		URI profileImageUrl=null;
		
		TimelineEntry(String friend, String createdAt,
									String status, URI profileImageUrl) {
			this.friend=friend;
			this.createdAt=createdAt;
			this.status=status;
			this.profileImageUrl=profileImageUrl;
		}
	}
	
	class TimelineAdapter extends ArrayAdapter<TimelineEntry> {
		 TimelineAdapter() {
			super(ThumbnailDemo.this, R.layout.row, model);
		}
		
		public View getView(int position, View convertView,
												ViewGroup parent) {
			View row=convertView;
			TimelineEntryWrapper wrapper=null;
			
			if (row==null) {													
				LayoutInflater inflater=getLayoutInflater();
				
				row=inflater.inflate(R.layout.row, null);
				wrapper=new TimelineEntryWrapper(row);
				row.setTag(wrapper);
			}
			else {
				wrapper=(TimelineEntryWrapper)row.getTag();
			}
			
			wrapper.populateFrom(getItem(position));
			
			return(row);
		}
	}
	
	class TimelineEntryWrapper {
		private TextView friend=null;
		private TextView createdAt=null;
		private TextView status=null;
		private ImageView avatar=null;
		private View row=null;
		
		TimelineEntryWrapper(View row) {
			this.row=row;
		}
		
		void populateFrom(TimelineEntry s) {
			getFriend().setText(s.friend);
			getCreatedAt().setText(s.createdAt);
			getStatus().setText(s.status);
			
			if (s.profileImageUrl!=null) {
				try {
					getAvatar().setImageResource(R.drawable.placeholder);
					getAvatar().setTag(s.profileImageUrl.toString());
				}
				catch (Throwable t) {
					goBlooey(t);
				}
			}
		}
		
		TextView getFriend() {
			if (friend==null) {
				friend=(TextView)row.findViewById(R.id.friend);
			}
			
			return(friend);
		}
		
		TextView getCreatedAt() {
			if (createdAt==null) {
				createdAt=(TextView)row.findViewById(R.id.created_at);
			}
			
			return(createdAt);
		}
		
		TextView getStatus() {
			if (status==null) {
				status=(TextView)row.findViewById(R.id.status);
			}
			
			return(status);
		}
		
		ImageView getAvatar() {
			if (avatar==null) {
				avatar=(ImageView)row.findViewById(R.id.avatar);
			}
			
			return(avatar);
		}
	}
}