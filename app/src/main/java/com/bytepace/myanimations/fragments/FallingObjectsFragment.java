package com.bytepace.myanimations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytepace.myanimations.AnimationsUtil;
import com.bytepace.myanimations.R;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor Matskevich on 07-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class FallingObjectsFragment extends BaseFragment implements View.OnClickListener, Transition.TransitionListener {

    private List<View> mMarkers;
    private ViewGroup mMainContainer;

    public static FallingObjectsFragment newInstance() {

        Bundle args = new Bundle();

        FallingObjectsFragment fragment = new FallingObjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.falling_objects));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fail_objects_fragment, container, false);

        mMarkers = new ArrayList<>();

        mMarkers.add(v.findViewById(R.id.marker1));
        mMarkers.add(v.findViewById(R.id.marker2));
        mMarkers.add(v.findViewById(R.id.marker3));
        mMarkers.add(v.findViewById(R.id.marker4));
        mMarkers.add(v.findViewById(R.id.marker5));
        mMarkers.add(v.findViewById(R.id.marker6));
        mMarkers.add(v.findViewById(R.id.marker7));

        mMainContainer = (ViewGroup) v.findViewById(R.id.main_container);

        v.findViewById(R.id.btn_start).setOnClickListener(this);

        return v;
    }

    int index = 0;

    @Override
    public void onClick(View v) {
        for (View marker : mMarkers)
            marker.setVisibility(View.GONE);

        index = 0;
        setVisibilityFromMarker(this);
    }

    private void setVisibilityFromMarker(Transition.TransitionListener listenerAdapter) {
        if(index > mMarkers.size() - 1)
            return;

        TransitionManager.beginDelayedTransition(mMainContainer,
                new Slide(Gravity.TOP).setDuration(200).addListener(listenerAdapter));
        mMarkers.get(index).setVisibility(View.VISIBLE);
    }

    @Override
    public void onTransitionStart(Transition transition) {

    }

    @Override
    public void onTransitionEnd(Transition transition) {
        if(index < mMarkers.size())
            AnimationsUtil.shake(getContext(), mMarkers.get(index));

        index++;
        setVisibilityFromMarker(this);
    }

    @Override
    public void onTransitionCancel(Transition transition) {

    }

    @Override
    public void onTransitionPause(Transition transition) {

    }

    @Override
    public void onTransitionResume(Transition transition) {

    }
}
