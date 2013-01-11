package jp.ishiharu.bitman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m);
	}
	
	public void buttonClick(View v) {
		Intent i = null;
		switch (v.getId()) {
		case R.id.ButtonEasy:
			i = new Intent(this, GameActivity.class);
			i.putExtra("mode", 0);
			break;
		case R.id.ButtonHard:
			i = new Intent(this, GameActivity.class);
			i.putExtra("mode", 1);
			break;
		default:
			break;
		}
		startActivity(i);
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_m, menu);
	// return true;
	// }
	
}
