package jp.ishiharu.bitman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	
	public int			frame	= 0;	// 0最初,1スタート,2終わり
	private Paint		strP;
	private FontMetrics	fm		= null; // 左上を基点にするため
										
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		strP = new Paint();
		strP.setColor(Color.WHITE);
		strP.setTextSize(15 * GameActivity.density);
		strP.setAntiAlias(true);
		fm = strP.getFontMetrics();
	}
	
	@Override
	protected void onDraw(Canvas c) {
		super.onDraw(c);
		switch (frame) {
		case 0:
			String text1 = getContext().getString(R.string.game_text1);
			int t2Pos = (int) strP.measureText(text1, 0, 5);
			String text2 = getContext().getString(R.string.game_text2);
			c.drawText(text1, 0, 100 * GameActivity.density, strP);
			c.drawText(text2, t2Pos, 100 * GameActivity.density - fm.ascent, strP);
			break;
		case 1:
			
		default:
			break;
		}
	}
	
	public void changeFrame() {
		if (frame < 1) {
			frame++;
		} else {
			frame = 0;
		}
		invalidate();
	}
}
