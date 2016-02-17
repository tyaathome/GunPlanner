package com.example.trungie.gunplanner.ui.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

public class MyProgressAnimation extends View {
    private int width;
    private int height;

    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private Path mPath;
    private Paint mPathPaint;

    private Paint mPaintCircle;

    private Paint mPaintText;

    private int maxProgress = 100;
    private int currentProgress = 0;

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }


    private int count = 0;
    private static final int NEED_INVALIDATE = 0X6666;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NEED_INVALIDATE:
                    count += 5;
                    if (count > -5) {
                        count = -80;
                    }
                    invalidate();
                    sendEmptyMessageDelayed(NEED_INVALIDATE, 50);
                    break;
            }

        }
    };

    public MyProgressAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(Color.argb(0xff, 0xff, 0x69, 0x5a));
        mPathPaint.setStyle(Paint.Style.FILL);
        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.argb(0xff, 0xf8, 0x8e, 0x8b));

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.argb(0xff, 0xFF, 0xF3, 0xF7));
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(50);

        handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 50);
    }

    public MyProgressAnimation(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        bitmapCanvas.drawCircle(width / 2, height / 2, width / 2, mPaintCircle);
        mPath.reset();
        mPath.moveTo(width, height - (currentProgress * height / maxProgress));
        mPath.lineTo(width, height);
        mPath.lineTo(count, height);
        mPath.lineTo(count, height - (currentProgress * height / maxProgress));
        for (int i = 0; i < 10; i++) {
            mPath.rQuadTo(20, 5, 40, 0);
            mPath.rQuadTo(20, -5, 40, 0);
        }
        mPath.close();
        bitmapCanvas.drawPath(mPath, mPathPaint);
        Rect bounds = new Rect();
        mPaintText.getTextBounds("a", 0, 1, bounds);
        int paintTextHeight = bounds.height();
        DecimalFormat df = new DecimalFormat("0.00");
        String result = df.format(currentProgress * 100f / maxProgress);
        bitmapCanvas.drawText(result + "%", width / 2, height / 2 + paintTextHeight/2, mPaintText);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
