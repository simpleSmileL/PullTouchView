package weigt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by simpleSmile on 2017/9/15.
 */

public class BezierView extends View {
  public BezierView(Context context) {
    super(context);
    init();
  }

  public BezierView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private Path mBezier = new Path();
  private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawPath(mBezier,mPaint);

    
  }

  public void init() {
    Paint paint = mPaint;
    paint.setAntiAlias(true);
    //设置防抖动
    paint.setDither(true);
    //设置填充方式
    paint.setStyle(Paint.Style.FILL);

    paint.setStrokeWidth(10);

    initBezier();
  }

  public void initBezier() {

  }
}
