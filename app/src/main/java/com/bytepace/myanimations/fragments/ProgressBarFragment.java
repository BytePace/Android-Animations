package com.bytepace.myanimations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.custom_view.ArcProgressBar;

/**
 * Created by Viktor Matskevich on 06-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class ProgressBarFragment extends BaseFragment implements View.OnClickListener {

    private final int[] colors = {
            android.R.color.holo_red_light,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light};
    private ArcProgressBar arcProgressBar;
    private TextView mProgressTextView;
    private int step = 0;

    public static ProgressBarFragment newInstance() {

        Bundle args = new Bundle();

        ProgressBarFragment fragment = new ProgressBarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.custom_progress_bar));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.progress_bar_fragment, container, false);
        mProgressTextView = (TextView) v.findViewById(R.id.tv_progress);
        arcProgressBar = (ArcProgressBar) v.findViewById(R.id.progress_bar);
        v.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (step > 2)
                    step = 0;

                arcProgressBar.setColorProgress(colors[step]);

                Animation animation = new Animation() {
                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        int progress = Math.round(360 * interpolatedTime);
                        arcProgressBar.setAngle(progress);
                        arcProgressBar.requestLayout();
                        mProgressTextView.setText("" + arcProgressBar.getCurrentProgress());
                    }
                };
                animation.setDuration(3000);
                arcProgressBar.startAnimation(animation);
                step++;
            }
        });
        return v;
    }


    @Override
    public void onClick(View v) {

    }
}
