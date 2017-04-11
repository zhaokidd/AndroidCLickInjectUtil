package com.yoongoo.zy.injectclickdemo;

import com.yoongoo.zy.inject.ClickInject;
import com.yoongoo.zy.inject.ClickUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	@ClickInject(R.id.button)
	private Button button;
	@ClickInject(R.id.textview)
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ClickUtil.inject(this, this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			Toast.makeText(MainActivity.this, "Click Me", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.textview:
			Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}
	}
}
