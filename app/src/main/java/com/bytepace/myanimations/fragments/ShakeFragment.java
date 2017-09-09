package com.bytepace.myanimations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.AnimationsUtil;

/**
 * Created by Viktor Matskevich on 05-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class ShakeFragment extends BaseFragment implements View.OnClickListener {

    public static ShakeFragment newInstance() {

        Bundle args = new Bundle();

        ShakeFragment fragment = new ShakeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("Shake. Click any object");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shake_fragment, container, false);

        v.findViewById(R.id.square1).setOnClickListener(this);
        v.findViewById(R.id.square2).setOnClickListener(this);
        v.findViewById(R.id.square3).setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        AnimationsUtil.shake(getContext(), v);
    }
}
