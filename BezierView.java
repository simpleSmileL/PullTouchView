package weigt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    mPaint.setColor(Color.RED);
    canvas.drawPath(mBezier, mPaint);
  }

  public void init() {
    Paint paint = mPaint;
    paint.setAntiAlias(true);
    //设置防抖动
    paint.setDither(true);
    //设置填充方式
    paint.setStyle(Paint.Style.STROKE);

    paint.setStrokeWidth(5);

    new Thread(){
      @Override public void run() {
        //初始化
        initBezier();
      }
    }.start();



  }

  private void initBezier() {

    float[] xPoint = new float[] {0, 150, 200, 400, 600};
    float[] yPoint = new float[] {0, 150, 400, 700, 200};
    Path path = mBezier;

    int fbs = 10000;
    for (int i = 0; i <= fbs; i++) {
      float progress = i / (float) fbs;
      float x = calculateBezier(progress, xPoint);
      float y = calculateBezier(progress, yPoint);
      path.lineTo(x, y);

      postInvalidate();

      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 计算某时刻贝塞尔曲线所处的值
   *
   * @param t 时间（0-1）
   * @param values 贝塞尔点集合（x或者y）
   */
  private float calculateBezier(float t, float... values) {

    final int len = values.length;
    for (int i = len - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        values[j] = values[j] + (values[j + 1] - values[j]) * t;
      }
    }

    return values[0];
  }
}
