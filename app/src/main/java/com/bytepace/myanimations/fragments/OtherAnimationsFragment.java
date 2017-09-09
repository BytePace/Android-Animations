package com.bytepace.myanimations.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytepace.myanimations.R;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.Explode;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

/**
 * Created by Viktor Matskevich on 30-Aug-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class OtherAnimationsFragment extends BaseFragment {

    public static OtherAnimationsFragment newInstance() {

        Bundle args = new Bundle();

        OtherAnimationsFragment fragment = new OtherAnimationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.other_animations));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.other_animations_fragment, container, false);

        final ViewGroup rootSlide = (ViewGroup) v.findViewById(R.id.container_slide);
        final TextView textFieldSlide = (TextView) rootSlide.findViewById(R.id.text_slide);
        textFieldSlide.setText(R.string.slide);
        rootSlide.findViewById(R.id.animation_slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View slide) {
                int visibility = textFieldSlide.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE;

                TransitionManager.beginDelayedTransition(rootSlide,
                        new TransitionSet()
                                .addTransition(new Fade()).addTransition(new Slide(Gravity.START)));

                textFieldSlide.setVisibility(visibility);
            }
        });

        final ViewGroup rootExplode = (ViewGroup) v.findViewById(R.id.container_explode);
        final TextView textFieldExplode = (TextView) rootExplode.findViewById(R.id.text_slide);
        textFieldSlide.setText(R.string.slide);
        rootExplode.findViewById(R.id.animation_slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View slide) {
                int visibility = textFieldExplode.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE;

                TransitionManager.beginDelayedTransition(rootExplode,
                        new TransitionSet()
                                .addTransition(new Fade())
                                .addTransition(new Explode()));

                textFieldExplode.setVisibility(visibility);
            }
        });

        final ViewGroup rootImageTransform = (ViewGroup) v.findViewById(R.id.container_image_transform);
        final View image_transform = rootImageTransform.findViewById(R.id.image_transform);
        image_transform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(rootImageTransform,
                        new TransitionSet()
                                .addTransition(new ChangeImageTransform())
                                .addTransition(new ChangeBounds()));

                ViewGroup.LayoutParams params = image_transform.getLayoutParams();
                int size = params.width == getResources().getDimensionPixelSize(R.dimen.size_image) ?
                        getResources().getDimensionPixelSize(R.dimen.max_size_image) :
                        getResources().getDimensionPixelSize(R.dimen.size_image);
                params.width = size;
                params.height = size;

                image_transform.setLayoutParams(params);
            }
        });

        return v;
    }
}
