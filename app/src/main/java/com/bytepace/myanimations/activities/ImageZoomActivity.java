package com.bytepace.myanimations.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bytepace.myanimations.R;

/**
 * Created by Viktor Matskevich on 05-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 */

public class ImageZoomActivity extends AppCompatActivity {

    private static final String ID_RESOURCE = "ID_RESOURCE";

    public static Intent newIntent(Context ctx, int idResourse) {
        Intent intent = new Intent(ctx, ImageZoomActivity.class);
        intent.putExtra(ID_RESOURCE, idResourse);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.zoom_image_screen));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_review);
        int idResource = getIntent().getIntExtra(ID_RESOURCE, android.R.drawable.gallery_thumb);
        ((ImageView) findViewById(R.id.image)).setImageDrawable(getResources().getDrawable(idResource));
        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
