package jp.ishiharu.bitman;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MActivity extends Activity implements OnTouchListener {
	
	public static float			density;
	// public static int dispX;
	// public static int dispY;
	private int					mode;
	private ButtonProcessing	btnP;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 画面サイズを得る
		Window window = getWindow();
		WindowManager manager = window.getWindowManager();
		Display disp = manager.getDefaultDisplay();
		// dispX = disp.getWidth();
		// dispY = disp.getHeight();
		// displayの情報取得
		DisplayMetrics metrics = new DisplayMetrics();
		disp.getMetrics(metrics);
		density = metrics.density;
		// TODO layoutのボタン修正せよ
		setContentView(R.layout.activity_m);
	}
	
	/** モード選択ボタン */
	public void buttonClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonEasy:
			mode = 0;
			break;
		case R.id.ButtonHard:
			mode = 1;
			break;
		}
		changeView();
	}
	
	/** Layout作っちゃったんでこれで切り替え */
	private void changeView() {
		setContentView(R.layout.activity_game);
		Button upB = (Button) findViewById(R.id.ButtonUp);
		Button dwB = (Button) findViewById(R.id.ButtonDown);
		Button leB = (Button) findViewById(R.id.ButtonLeft);
		Button riB = (Button) findViewById(R.id.ButtonRight);
		Button aB = (Button) findViewById(R.id.ButtonA);
		upB.setOnTouchListener(this);
		dwB.setOnTouchListener(this);
		leB.setOnTouchListener(this);
		riB.setOnTouchListener(this);
		aB.setOnTouchListener(this);
		btnP = new ButtonProcessing(findViewById(R.id.gameView));
		GameView.frame = 1;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		int id = v.getId();
		int event = e.getAction() & MotionEvent.ACTION_MASK;
		btnP.touch(id, event);
		// Log.i("touch", "touch");
		return false;
	}
	
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_m, menu);
	// return true;
	// }
	
}
