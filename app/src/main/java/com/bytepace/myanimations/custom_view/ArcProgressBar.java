package com.bytepace.myanimations.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.bytepace.myanimations.R;

/**
 * Created by Viktor Matskevich on 06-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class ArcProgressBar extends View {

    private static final int DEFAULT_START_ANGLE_POINT = 135;
    private static final int DEFAULT_CURRENT_ANGLE_POINT = 120;
    private static final int DEFAULT_END_ANGLE_POINT = 405;

    private static final int DEFAULT_STROKE_WIDTH = 40;
    private static final int DEFAULT_CIRCLE_RADIUS = 200;

    private final Paint paint;
    private final RectF rect;

    private int mStartAngle;
    private int mCurrentAngle;
    private int mEndAngle;

    private float stepAngle;


    public ArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressBar);

        final float strokeWidth = a.getFloat(R.styleable.ArcProgressBar_stroke_width, DEFAULT_STROKE_WIDTH);

        int circleRadius = a.getDimensionPixelOffset(R.styleable.ArcProgressBar_circle_radius, DEFAULT_CIRCLE_RADIUS);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(Color.RED);

        //size 200x200 example
        rect = new RectF(strokeWidth / 2, strokeWidth / 2, circleRadius + strokeWidth, circleRadius + strokeWidth);

        //Initial Angle (optional, it can be zero)
        mStartAngle = a.getInteger(R.styleable.ArcProgressBar_start_angle, DEFAULT_START_ANGLE_POINT);
        mCurrentAngle = a.getInteger(R.styleable.ArcProgressBar_current_angle, DEFAULT_CURRENT_ANGLE_POINT);
        mEndAngle = a.getInteger(R.styleable.ArcProgressBar_end_angle, DEFAULT_END_ANGLE_POINT);

        stepAngle = (mEndAngle - mStartAngle) / 360.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, mStartAngle, (float) mCurrentAngle * stepAngle, false, paint);
    }

    public int getAngle() {
        return mCurrentAngle;
    }

    public void setAngle(int angle) {
        this.mCurrentAngle = angle;
    }

    public void setColorProgress(int color) {
        paint.setColor(getResources().getColor(color));
    }

    public int getCurrentProgress() {
        return mCurrentAngle * 100 / 360;
    }
}
