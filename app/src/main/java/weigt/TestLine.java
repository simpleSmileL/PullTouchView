package weigt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by simpleSmile on 2017/7/22.
 */

public class TestLine extends View {
  public TestLine(Context context) {
    super(context);
    init();
  }

  public TestLine(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public TestLine(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private Path mPath = new Path();


  public void init(){
    Paint paint = mPaint;
    Path  path = mPath;

    paint.setAntiAlias(true);
    paint.setDither(true);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(2);
    path.moveTo(100,100);
    path.lineTo(200,200);

    //二阶贝塞尔曲线
    path.quadTo(400,0,500,300);
    //path.rQuadTo();    相对位置的实现.第一个为控制点，一个终点

    path.moveTo(300,500);

    //三阶贝塞尔曲线
    path.cubicTo(150,200,200,800,500,500);   //前两个为控制点，后一个为终点

    //path.rCubicTo();   相对位置的实现
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawPath(mPath,mPaint);

  }
}
