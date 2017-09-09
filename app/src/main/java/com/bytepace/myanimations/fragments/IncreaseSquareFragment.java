package com.bytepace.myanimations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.AnimationsUtil;

/**
 * Created by Viktor Matskevich on 30-Aug-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class IncreaseSquareFragment extends BaseFragment {

    private ViewGroup mSceneRoot;
    private boolean mExpanded;

    public static IncreaseSquareFragment newInstance() {

        Bundle args = new Bundle();

        IncreaseSquareFragment fragment = new IncreaseSquareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(R.string.increase_square_size);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.increase_square_fragment, container, false);
        mSceneRoot = (ViewGroup) v.findViewById(R.id.container);
        mExpanded = false;
        mSceneRoot.findViewById(R.id.transition_square).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSquareSize = !mExpanded
                        ? getResources().getDimensionPixelSize(R.dimen.square_size_expanded)
                        : getResources().getDimensionPixelSize(R.dimen.square_size_normal);
                mExpanded = !mExpanded;

                AnimationsUtil.setSizeFromView(mSceneRoot, v, newSquareSize, newSquareSize, new AnimationsUtil.AnimationCallBack() {
                    @Override
                    public void onStartAnimation() {
                        Log.i(TAG, "onStartAnimation: ");
                    }

                    @Override
                    public void onProcess() {
                        Log.i(TAG, "onProcess: ");
                    }

                    @Override
                    public void onAnimationComplete() {
                        Log.i(TAG, "onAnimationComplete: ");
                    }

                    @Override
                    public void onError(String message) {
                        Log.e(TAG, "onError: " + message);
                    }
                });
            }
        });

        return v;
    }
}
