package com.bytepace.myanimations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.bytepace.myanimations.R;

/**
 * Created by Viktor Matskevich on 30-Aug-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class TransitionSquareFragment extends BaseFragment {

    public static TransitionSquareFragment newInstance() {

        Bundle args = new Bundle();

        TransitionSquareFragment fragment = new TransitionSquareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(R.string.transition_square);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transition_sqare_fragment, container, false);
        ViewGroup sceneRoot = (ViewGroup) v.findViewById(R.id.scene_root);

        final Scene scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, getContext());

        v.findViewById(R.id.btn_change_scene).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionSet set = new TransitionSet();

                set.addTransition(new Fade());
                set.addTransition(new ChangeBounds());
                set.setOrdering(TransitionSet.ORDERING_TOGETHER);
                set.setDuration(500);
                set.setInterpolator(new AccelerateInterpolator());

                TransitionManager.go(scene2, set);
            }
        });

        return v;
    }
}
