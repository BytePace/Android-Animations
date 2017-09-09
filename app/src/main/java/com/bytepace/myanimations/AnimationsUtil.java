package com.bytepace.myanimations;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.transitionseverywhere.TransitionManager;

import java.math.BigDecimal;

/**
 * Created by Viktor Matskevich on 30-Aug-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class AnimationsUtil extends TransitionManager {

    private static final String TAG = AnimationsUtil.class.toString();

    public static void setSizeFromView(ViewGroup scene, View view, int pxWidth, int pxHeight, AnimationCallBack callBack) {
        TransitionManager.beginDelayedTransition(scene);

        if(pxWidth < 0 || pxHeight < 0) {
            if (callBack != null)
                callBack.onError("Not correct size width = " + pxWidth + " height = " + pxHeight);
            return;
        }

        if(callBack != null)
            callBack.onStartAnimation();

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = pxWidth;
        params.height = pxHeight;

        if(callBack != null)
            callBack.onProcess();

        view.setLayoutParams(params);

        if(callBack != null)
            callBack.onAnimationComplete();
    }

public static void setBackgroundToView(final View target, final int color,
                                       final float x, final float y,
                                       final long duration, final boolean revers, AnimationCallBack callBack) {

    float X = getAccurateValue(x, target.getWidth());
    float Y = getAccurateValue(y, target.getHeight());

    final float maxSize =
             new BigDecimal(Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2))).floatValue();

    Animation a = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            if(Float.compare(interpolatedTime, 0) <= 0)
                return;

            Bitmap canvasBitmap = Bitmap.createBitmap(target.getWidth(), target.getHeight(), Bitmap.Config.ARGB_8888);

            Paint paint = new Paint();
            paint.setColor(color);
            Canvas canvas = new Canvas(canvasBitmap);
            canvas.drawCircle(x, y, revers ?
                    maxSize * (1 - interpolatedTime) : maxSize * interpolatedTime,
                    paint);

            target.setBackgroundDrawable(new BitmapDrawable(canvasBitmap));
        }
    };
    a.setDuration(duration);
    target.startAnimation(a);
}

private static float getAccurateValue(float value, float max) {
    if(value >= max / 2)
        return value;
    else
        return max - value;
}

    public static void setColorText(final TextView target, int colorFrom, int colorTo, AnimationCallBack callBack) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                target.setTextColor((Integer)animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    public static void shake(Context context, View target) {
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
        target.startAnimation(shake);
    }

    public interface AnimationCallBack {
        void onStartAnimation();
        void onProcess();
        void onAnimationComplete();
        void onError(String message);
    }
}
