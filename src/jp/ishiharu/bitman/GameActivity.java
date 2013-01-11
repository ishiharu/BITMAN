package jp.ishiharu.bitman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameActivity extends Activity implements OnTouchListener {
	public static float			density;
	
	// public static int dispX;
	// public static int dispY;
	
	private Button				upB;
	private Button				dwB;
	private Button				leB;
	private Button				riB;
	private Button				aB;
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
		
		Intent i = getIntent();
		int mode = i.getIntExtra("mode", -1);
		setContentView(R.layout.activity_game);
		
		upB = (Button) findViewById(R.id.ButtonUp);
		dwB = (Button) findViewById(R.id.ButtonDown);
		leB = (Button) findViewById(R.id.ButtonLeft);
		riB = (Button) findViewById(R.id.ButtonRight);
		aB = (Button) findViewById(R.id.ButtonA);
		upB.setOnTouchListener(this);
		dwB.setOnTouchListener(this);
		leB.setOnTouchListener(this);
		riB.setOnTouchListener(this);
		aB.setOnTouchListener(this);
		btnP = new ButtonProcessing(findViewById(R.id.gameView));
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		int id = v.getId();
		int event = e.getAction() & MotionEvent.ACTION_MASK;
		btnP.touch(id, event);
		return false;
	}
}
