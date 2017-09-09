package com.bytepace.myanimations.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by Viktor Matskevich on 30-Aug-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class BaseFragment extends Fragment {
    protected final String TAG = "<--" + this.getClass() + "-->";

    protected void setTitle(String text) {
        getActivity().setTitle(text);
    }

    protected void setTitle(int id) {
        getActivity().setTitle(id);
    }
}
