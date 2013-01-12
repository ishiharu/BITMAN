package jp.ishiharu.bitman;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ButtonProcessing {
	private final int	UP		= 1;
	private final int	DOWN	= 2;
	private final int	LEFT	= 4;
	private final int	Right	= 8;
	private final int	A		= 16;
	private int			btn		= 0;
	private GameView	gameV;
	
	public ButtonProcessing(View v) {
		gameV = (GameView) v;
		Log.i("", "" + gameV);
	}
	
	/**
	 * 押された時　ループ時はDOWNのとこを|=にしたら同時押しできるかな
	 * 
	 * @param id どのボタンか
	 * @param event MotionEvent
	 * @return 上1,下2,左4,右8,A16
	 */
	public int touch(int id, int event) {
		switch (event) {
		case MotionEvent.ACTION_DOWN:
			switch (id) {
			case R.id.ButtonUp:
				btn = UP;
				break;
			case R.id.ButtonDown:
				btn = DOWN;
				break;
			case R.id.ButtonLeft:
				btn = LEFT;
				break;
			case R.id.ButtonRight:
				btn = Right;
				break;
			case R.id.ButtonA:
				btn = A;
				break;
			default:
				break;
			}
			Log.i("btnDown", "key:" + btn);
			actDwn();
			return btn;
			
		case MotionEvent.ACTION_UP:
			switch (id) {
			case R.id.ButtonUp:
				btn ^= 1;
				break;
			case R.id.ButtonDown:
				btn ^= 2;
				break;
			case R.id.ButtonLeft:
				btn ^= 4;
				break;
			case R.id.ButtonRight:
				btn ^= 8;
				break;
			case R.id.ButtonA:
				btn ^= 16;
				break;
			default:
				break;
			}
			return btn;
		default:
			return btn;
		}
		
	}
	
	/** 処理 */
	private void actDwn() {
		if (GameView.frame == GameView.FRAME_DESCRIPTION) {
			if ((btn & A) == A) {
				gameV.changeFrame();
			}
		}
	}
}
