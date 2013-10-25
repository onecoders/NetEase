package learn2crack.jsonparsing;

import learn2crack.jsonparsing.library.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	// URL to get JSON Array
	private static String url = "http://api.learn2crack.com/android/json/";

	// JSON Node Names
	private static final String TAG_USER = "user";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";

	JSONArray user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// do network task in background
		new MyAsyncTask().execute(url);
	}

	class MyAsyncTask extends AsyncTask<String, Void, JSONObject> {

		JSONParser jParser;

		@Override
		protected void onPreExecute() {
			// Creating new JSON Parser
			jParser = new JSONParser();
			super.onPreExecute();
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			// Getting JSON from URL
			return jParser.getJSONFromUrl(params[0]);
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			try {
				// Getting JSON Array
				user = result.getJSONArray(TAG_USER);
				JSONObject c = user.getJSONObject(0);

				// Storing JSON item in a Variable
				String id = c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String email = c.getString(TAG_EMAIL);

				// Importing TextView
				final TextView uid = (TextView) findViewById(R.id.uid);
				final TextView name1 = (TextView) findViewById(R.id.name);
				final TextView email1 = (TextView) findViewById(R.id.email);

				// Set JSON Data in TextView
				uid.setText(id);
				name1.setText(name);
				email1.setText(email);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			super.onPostExecute(result);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
