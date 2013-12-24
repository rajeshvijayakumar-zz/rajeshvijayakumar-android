package com.rajeshvijayakumar.barcode;

import jim.h.common.android.lib.zxing.config.ZXingLibConfig;
import jim.h.common.android.lib.zxing.integrator.IntentIntegrator;
import jim.h.common.android.lib.zxing.integrator.IntentResult;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private ZXingLibConfig zxingLibConfig;
	private Button mScanBarCodeButton;
	private TextView mSummaryView;
	private String result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mScanBarCodeButton = (Button) findViewById(R.id.scan_button);
		mSummaryView = (TextView) findViewById(R.id.textView1);
		mScanBarCodeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.scan_button:
			IntentIntegrator.initiateScan(MainActivity.this, zxingLibConfig);
			break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE:
			IntentResult scanResult = IntentIntegrator.parseActivityResult(
					requestCode, resultCode, data);
			if (scanResult != null) {
				result = scanResult.getContents();// scanned result
				mSummaryView.setText("Scanned Barcode : "+ result);
				} else {
					// DO Something
				}
			break;
		default:
		}
	}
}