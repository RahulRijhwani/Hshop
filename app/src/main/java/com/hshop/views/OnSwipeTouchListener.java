package com.hshop.views;

import android.content.Context;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {

    protected MotionEvent mLastOnDownEvent = null;
    private final GestureDetector gestureDetector;
    int s1 = MotionEvent.axisFromString("MotionEvent { action=ACTION_UP, actionButton=0, id[0]=0,x[0]=637.4098,y[0]=901.3938,toolType[0]=TOOL_TYPE_FINGER,buttonState=0,metaState=0,flags=0x0,edgeFlags=0x0,pointerCount=1,historySize=0,eventTime=384143830,downTime=384143676,deviceId=5,source=0x1002 }");

    public OnSwipeTouchListener (Context ctx){
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //mLastOnDownEvent=event;
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;




        @Override
        public boolean onDown(MotionEvent e) {
            mLastOnDownEvent=e;
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                if(e1==null)
                {
                    //e1=MotionEvent.actionToString(s1)
                }
                float diffY = e2.getY() - e1.getY();

                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                      }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}
