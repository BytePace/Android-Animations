package com.bytepace.myanimations.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bytepace.myanimations.AnimationsUtil;
import com.bytepace.myanimations.R;

/**
 * Created by Viktor Matskevich on 31-Aug-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class BlockContactFragment extends BaseFragment {

    private final static String COLOR_TEXT = "COLOR_TEXT";
    private final static String COLOR_BACKGROUND = "COLOR_BACKGROUND";
    private final static String TEXT_TITLE = "TEXT_TITLE";
    private final static String TEXT_DESCRIPTION = "TEXT_DESCRIPTION";

    private View mMainContainer;
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private View mSwitcherBar;
    private ImageView mIconContacts;
    private Switch mSwitchSync;

    private int mColorTextTitle;
    private int mFillBackgroundColor;
    private String mTextTitle;
    private String mTextDescription;

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.contact_sync));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mColorTextTitle = getArguments().getInt(COLOR_TEXT, Color.BLACK);
        mFillBackgroundColor = getArguments().getInt(COLOR_BACKGROUND, Color.WHITE);

        mTextTitle = getArguments().getString(TEXT_TITLE, "TITLE");
        mTextDescription = getArguments().getString(TEXT_DESCRIPTION, "DESCRIPTION");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.block_contact_fragment, container, false);

        mMainContainer = v.findViewById(R.id.main_container);

        mTitleTextView = (TextView) v.findViewById(R.id.tv_title);
        mTitleTextView.setText(mTextTitle);
        mTitleTextView.setTextColor(getResources().getColor(R.color.blue));

        mDescriptionTextView = (TextView) v.findViewById(R.id.tv_description);
        mDescriptionTextView.setText(mTextDescription);

        mSwitcherBar = v.findViewById(R.id.switcher_bar);
        mIconContacts = (ImageView) v.findViewById(R.id.image);
        mSwitchSync = (Switch) v.findViewById(R.id.switch_sync);
        mSwitchSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = mSwitcherBar.getX() + v.getX() + (mSwitchSync.isChecked() ? 70 : 40);
                float y = mSwitcherBar.getY() + v.getY() + v.getHeight() / 2;

                AnimationsUtil.setBackgroundToView(mMainContainer,
                        mFillBackgroundColor,
                        x, y,
                        500, !mSwitchSync.isChecked(), null);

                int fromColor = !mSwitchSync.isChecked() ?
                        mColorTextTitle : getResources().getColor(R.color.blue);
                int toColor = mSwitchSync.isChecked() ?
                        mColorTextTitle : getResources().getColor(R.color.blue);
                AnimationsUtil.setColorText(mTitleTextView, fromColor, toColor, null);
            }
        });

        return v;
    }

    public static class Builder {
        private Bundle arguments;

        public Builder() {
            arguments = new Bundle();
        }

        public Builder setColorText(int color) {
            arguments.putInt(COLOR_TEXT, color);
            return this;
        }

        public Builder setFillBackgroundColor(int color) {
            arguments.putInt(COLOR_BACKGROUND, color);
            return this;
        }

        public Builder setTitleText(String color) {
            arguments.putString(TEXT_TITLE, color);
            return this;
        }

        public Builder setDescriptionText(String color) {
            arguments.putString(TEXT_DESCRIPTION, color);
            return this;
        }

        public BlockContactFragment build() {
            BlockContactFragment fragment = new BlockContactFragment();
            fragment.setArguments(arguments);
            return fragment;
        }
    }
}
