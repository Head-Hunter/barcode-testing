package com.sdutlinux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.EditText;

import com.sdutlinux.service.SysApplication;
import com.sdutlinux.service.WebService;
import com.sdutlinux.utils.JsonParser;
import com.sdutlinux.utils.SimpleProgressDialog;

public class BasicInfoActivity extends Activity {
//	private ExpandableListView expListView;
	private ListView lv_info;
	
	private TextView nameTxt;

	private SimpleProgressDialog progressDialog;
	private Boolean isAnonymous;
	
    public static final String ANONYMOUS = "anonymous";
	private final static String TAG = "BasicInfoActivity";
//	private static final String CATEGORY = "Catogery";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_info);
		
		SysApplication.getInstance().addActivity(this);
		
		Log.i(TAG, "BasicInfoActivity");
		
//		expListView = (ExpandableListView) this.findViewById(R.id.expListView);
		//lv_info = (ListView) this.findViewById(R.id.lv_info);


		nameTxt = (TextView) this.findViewById(R.id.fullData);
		
		Intent data = getIntent();
		String id = data.getStringExtra("id");
		String name = data.getStringExtra("name");
		isAnonymous = data.getBooleanExtra(ANONYMOUS, false);
		
		nameTxt.setText("" + name);

		
	}

}
