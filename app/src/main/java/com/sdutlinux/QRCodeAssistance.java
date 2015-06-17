package com.sdutlinux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.sdutlinux.adapter.MainUIAdapter;
import com.sdutlinux.service.SysApplication;
import com.sdutlinux.utils.AlertDialogFactory;
import com.zxing.activity.CaptureActivity;

import java.util.Random;
import java.util.UUID;

import static java.lang.Math.random;

public class QRCodeAssistance extends Activity implements OnItemClickListener{
    private static final String TAG = "QRCodeAssistancetest";

    public static final String ANONYMOUS = "anonymous";

    private boolean isAnonymous;
    private GridView gridView;
    private LinearLayout ll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.main);

        setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);

        SysApplication.getInstance().addActivity(this);

        isAnonymous =
        		getIntent().getBooleanExtra(ANONYMOUS, false);
        if (isAnonymous) {
        	setTitle(getTitle() + " ");
        }

        gridView = (GridView) this.findViewById(R.id.gv_main);
        gridView.setAdapter(new MainUIAdapter(this));
        gridView.setOnItemClickListener(this);

        ll = (LinearLayout) this.findViewById(R.id.ll_main);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(1000);
		ll.startAnimation(alphaAnimation);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	switch (position) {
		case 0:
			Intent openCameraIntent = new Intent(QRCodeAssistance.this,CaptureActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;
		case 1:
			SysApplication.getInstance().exit();
			break;
		default:
			break;
		}
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Bundle result = data.getExtras();
			String scanResult = result.getString("result");

			String[] results = scanResult.split(";");

			Bundle bundle = new Bundle();

			String uid = String.valueOf(random());


			bundle.putString("id", uid);
			bundle.putString("name", results[0]);
			bundle.putBoolean(ANONYMOUS, isAnonymous);

			Intent intent = new Intent(QRCodeAssistance.this, BasicInfoActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);

		}
	}

}
