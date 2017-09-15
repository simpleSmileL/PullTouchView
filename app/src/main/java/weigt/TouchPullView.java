package weigt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by simpleSmile on 2017/7/21.
 */

public class TouchPullView extends View {

  private Paint mCirclePaint;

  //圆半径
  private int mRadious = 100;

  private int mCricleX, mCricleY;//圆心坐标

  private float mProgess;

  private int mDragHeight = 800;

  public TouchPullView(Context context) {
    super(context);
    init();
  }

  public TouchPullView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public TouchPullView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public TouchPullView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  public void init() {
    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    //设置抗锯齿
    p.setAntiAlias(true);
    //设置防抖动
    p.setDither(true);
    //设置填充方式
    p.setStyle(Paint.Style.FILL);
    //设置画笔颜色
    p.setColor(Color.YELLOW);
    mCirclePaint = p;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawCircle(mCricleX, mCricleY, mRadious, mCirclePaint);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int width = MeasureSpec.getSize(widthMeasureSpec);

    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);

    int measureWidth, measureHeight;

    int iwidth = mRadious * 2 + getPaddingLeft() + getPaddingLeft();
    int iheight = (int) ((mDragHeight * mProgess + 0.5f) + getPaddingTop() + getPaddingBottom());

    if (widthMode == MeasureSpec.EXACTLY) {
      //确切值
      measureWidth = width;
    } else if (widthMode == MeasureSpec.AT_MOST) {
      //最大值
      measureWidth = Math.min(iwidth, width);
    } else {
      //不确定值
      measureWidth = iwidth;
    }

    if (heightMode == MeasureSpec.EXACTLY) {
      measureHeight = height;
    } else if (heightMode == MeasureSpec.AT_MOST) {
      measureHeight = Math.min(iheight, height);
    } else {
      measureHeight = iheight;
    }

    //设置测量的高度宽度
    setMeasuredDimension(measureWidth, measureHeight);
  }

  /**
   * 当大小发生改变的时候触发
   */

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mCricleX = getWidth() >> 1;//右移一位，相当于除以2
    mCricleY = getHeight() >> 1;
  }

  /**
   * 设置拉动位置
   */
  public void setProgess(float progess) {
    Log.i("P", "滑动值" + progess);
    mProgess = progess;

    //刷新布局
    requestLayout();
  }
}
