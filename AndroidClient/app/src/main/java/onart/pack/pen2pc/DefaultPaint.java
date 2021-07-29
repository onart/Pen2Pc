package onart.pack.pen2pc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

public class DefaultPaint extends View {

    private Paint paint;
    private Path path;
    private boolean enabled=false;

    public DefaultPaint(Context ctx, AttributeSet attrs){
        super(ctx,attrs);
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        int act=event.getActionMasked();
        switch (act){
            case MotionEvent.ACTION_DOWN:
                performClick();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        if(enabled){
            switch (act){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x,y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x,y);
            }
            invalidate();
        }
        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void erase(){
        path.reset();
        invalidate();
    }

    public void off(){
        enabled=false;
    }

    public void on(){
        enabled=true;
    }
}
