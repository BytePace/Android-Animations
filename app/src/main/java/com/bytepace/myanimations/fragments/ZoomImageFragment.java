package com.bytepace.myanimations.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.activities.ImageZoomActivity;

/**
 * Created by Viktor Matskevich on 04-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class ZoomImageFragment extends BaseFragment implements View.OnClickListener {

    public static ZoomImageFragment newInstance() {

        Bundle args = new Bundle();

        ZoomImageFragment fragment = new ZoomImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.zoom_image));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.zoom_image_fragment, container, false);

        v.findViewById(R.id.image1).setOnClickListener(this);
        v.findViewById(R.id.image2).setOnClickListener(this);
        v.findViewById(R.id.image3).setOnClickListener(this);
        v.findViewById(R.id.image4).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                ZoomImageFragment.this.getActivity(), (ImageView) v, "smallImage");

        int idResource;
        switch (v.getId()) {
            case R.id.image1:
                idResource = R.drawable.violet;
                break;
            case R.id.image2:
                idResource = R.drawable.rosa;
                break;
            case R.id.image3:
                idResource = R.drawable.lilium;
                break;
            case R.id.image4:
                idResource = R.drawable.chamomile;
                break;
            default:
                idResource = android.R.drawable.gallery_thumb;
                break;
        }

        Intent intent = ImageZoomActivity.newIntent(getContext(), idResource);
        startActivity(intent, options.toBundle());
    }
}
