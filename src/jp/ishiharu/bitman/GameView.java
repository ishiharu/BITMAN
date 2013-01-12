package jp.ishiharu.bitman;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {
	
	public static final int	FRAME_BOOT			= 0;
	public static final int	FRAME_DESCRIPTION	= 1;
	public static final int	FRAME_START			= 2;
	public static final int	FRAME_GAMEOVER		= 3;
	/** 0最初,1説明,2スタート,3終わり */
	public static int		frame				= FRAME_BOOT;
	
	public static final int	OPERATOR_AND		= 0;
	public static final int	OPERATOR_OR			= 1;
	public static final int	OPERATOR_XOR		= 2;
	
	private Paint			strP;
	private FontMetrics		fm					= null;			// 左上を基点にするため
	private Random			rnd					= new Random();
	private final int		MAX_FIGURE			= 8;
	private final int		MAX_NUM				= 1 << MAX_FIGURE;
	private final int		MAX_LENGTH			= 3;
	
	private int				vxCenter;
	private int				vyCenter;
	
	private int				textH;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		strP = new Paint();
		strP.setColor(Color.WHITE);
		strP.setTextSize(15 * MActivity.density);
		strP.setAntiAlias(true);
		fm = strP.getFontMetrics();
		textH = (int) (fm.descent - fm.ascent);
		Log.i("text", "tH:" + textH);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		vxCenter = w / 2;
		vyCenter = h / 2;
		Log.i("GameView", "vxc:" + vxCenter + ",vyc:" + vyCenter);
	}
	
	@Override
	protected void onDraw(Canvas c) {
		super.onDraw(c);
		switch (frame) {
		
		case FRAME_DESCRIPTION:
			String text1 = getContext().getString(R.string.game_text1);
			int t2Pos = (int) strP.measureText(text1, 0, 5);
			String text2 = getContext().getString(R.string.game_text2);
			c.drawText(text1, 0, 100 * MActivity.density, strP);
			c.drawText(text2, t2Pos, 100 * MActivity.density - fm.ascent, strP);
			break;
		
		case FRAME_START:
			int[] q = new int[2];
			String qs[] = new String[q.length];
			
			int operator = rnd.nextInt(3);
			String opeS = null;
			switch (operator) {
			case OPERATOR_AND:
				opeS = "AND ";
				break;
			case OPERATOR_OR:
				opeS = "OR ";
				break;
			case OPERATOR_XOR:
				opeS = "XOR ";
				break;
			}
			
			for (int i = 0; i < q.length; i++) {
				q[i] = rnd.nextInt(MAX_NUM);
				
				switch (MActivity.mode) {
				case MActivity.MODE_EASY:
					// 10進数を2進数に
					qs[i] = ""; // 初期がnullなので
					for (int j = MAX_FIGURE - 1; j >= 0; j--) {
						int checkTarget = 1 << j;
						qs[i] = (q[i] & checkTarget) == checkTarget ? qs[i] + "1" : qs[i] + "0";
					}
					break;
				
				case MActivity.MODE_HARD:
					qs[i] = Integer.toString(q[i]);
					// 桁数揃える
					for (int j = qs[i].length(); j < MAX_LENGTH; j++) {
						qs[i] = "0" + qs[i];
					}
					break;
				default:
					break;
				}
				Log.i("q" + i, "i:" + q[i] + ",s:" + qs[i]);
			}
			strP.setTextSize(30 * MActivity.density);
			fm = strP.getFontMetrics();
			textH = (int) (fm.descent - fm.ascent);
			Log.i("text", "tH:" + textH);
			int opeEnd = (int) strP.measureText(opeS);
			c.drawText(qs[0], vxCenter, vyCenter - textH * 2, strP);
			c.drawText(opeS, vxCenter - opeEnd, vyCenter - textH, strP);
			c.drawText(qs[1], vxCenter, vyCenter - textH, strP);
			// String line = "";
			// for (int i = 0; i < opeS.length() + qs[1].length(); i++) {
			// if ((i & 1) == 0) {
			// line += "ー";
			// }
			// }
			String line = "--------------------";
			c.drawText(line, vxCenter - opeEnd, vyCenter, strP);
			c.drawText("00000000", vxCenter, vyCenter + textH, strP);
			c.drawText("▲", vxCenter, vyCenter + textH * 2, strP);
		default:
			break;
		}
	}
	
	/** 画面切り替え */
	public void changeFrame() {
		frame = (frame + 1) & 3;
		invalidate();
	}
}
