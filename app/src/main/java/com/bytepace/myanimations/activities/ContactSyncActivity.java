package com.bytepace.myanimations.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.fragments.BlockContactFragment;

/**
 * Created by Viktor Matskevich on 04-Sep-17.
 * Company: Bytepace
 * EMAIL: viktor.matskevich@sy-dev.com
 *
 */

public class ContactSyncActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, ContactSyncActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sync);

        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.card1,
                        new BlockContactFragment.Builder()
                                .setColorText(getResources().getColor(R.color.light_blue))
                                .setFillBackgroundColor(getResources().getColor(R.color.blue))
                                .setTitleText(getString(R.string.connect_contacts))
                                .setDescriptionText(getString(R.string.connect_contacts_description))
                                .build())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.card2,
                        new BlockContactFragment.Builder()
                                .setColorText(getResources().getColor(R.color.light_green))
                                .setFillBackgroundColor(getResources().getColor(R.color.green))
                                .setTitleText(getString(R.string.allow_discovery))
                                .setDescriptionText(getString(R.string.card_2_about))
                                .build())
                .commit();
    }
}
