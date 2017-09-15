package com.gb.touchpullweigt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import weigt.TouchPullView;

/**
 * MainActivity,AppCompatActivity
 */

public class MainActivity extends AppCompatActivity {
  private static final float TOUCH_MOVE_MAX_ = 600;
  private float mTouchStartY = 0;
  private TouchPullView pullView ;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pullView = (TouchPullView) findViewById(R.id.view_circle);

    findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getActionMasked();
        switch (action) {
          case MotionEvent.ACTION_DOWN:
            mTouchStartY = motionEvent.getY();
            return true;
          case MotionEvent.ACTION_MOVE:
            float y = motionEvent.getY();
            if (y >= mTouchStartY) {
              float moveSize = y - mTouchStartY;
              float progess = moveSize >= TOUCH_MOVE_MAX_ ? 1 : moveSize / TOUCH_MOVE_MAX_;
              pullView.setProgess(progess);
            }

            return true;
          default:
            break;
        }

        return false;
      }
    });
  }
}
