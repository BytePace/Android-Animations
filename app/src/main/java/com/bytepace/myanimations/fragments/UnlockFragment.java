package com.bytepace.myanimations.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.AnimationsUtil;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

/**
 * Created by Viktor Matskevich on 04-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class UnlockFragment extends BaseFragment implements View.OnClickListener {

    private ViewGroup mMainContainer;
    private ViewGroup mLockScreen;
    private View mLockImageButton;
    private View mMainContent;

    public static UnlockFragment newInstance() {

        Bundle args = new Bundle();

        UnlockFragment fragment = new UnlockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.unlock));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.unlock_fragment, container, false);

        mLockScreen = (ViewGroup) v.findViewById(R.id.lock_screen);
        mMainContainer = (ViewGroup) v.findViewById(R.id.main_container);
        mLockImageButton = v.findViewById(R.id.unlock);
        mMainContent = v.findViewById(R.id.main_content);

        mLockImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int cx = (mLockImageButton.getLeft() + mLockImageButton.getRight()) / 2;
                final int cy = mMainContainer.getBottom();

                final int finalRadius = Math.max(mMainContainer.getWidth(), mMainContainer.getHeight());

                TransitionManager.beginDelayedTransition(mMainContainer,
                        new TransitionSet()
                                .addTransition(new Fade())
                                .addTransition(new Slide(Gravity.BOTTOM)));

                mLockImageButton.setVisibility(View.GONE);
                mLockImageButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(mMainContent, cx, cy, 0, finalRadius);

                        mMainContent.setVisibility(View.VISIBLE);

                        anim.start();
                    }
                }, 300);

            }
        });

        v.findViewById(R.id.btn_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int finalRadius = Math.max(mMainContainer.getWidth(), mMainContainer.getHeight());

                Animator anim = ViewAnimationUtils.createCircularReveal(
                        mMainContent,
                        mMainContainer.getWidth() / 2, mMainContainer.getBottom(),
                        finalRadius, 0);

                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        mMainContent.setVisibility(View.GONE);

                        TransitionManager.beginDelayedTransition(mMainContainer,
                                new TransitionSet()
                                        .addTransition(new Fade())
                                        .addTransition(new Slide(Gravity.BOTTOM)));

                        mLockImageButton.setVisibility(View.VISIBLE);
                    }
                });

                anim.setDuration(500);
                anim.start();
            }
        });

        v.findViewById(R.id.square1).setOnClickListener(this);
        v.findViewById(R.id.square2).setOnClickListener(this);
        v.findViewById(R.id.square3).setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        AnimationsUtil.shake(getContext(), v);
    }
}