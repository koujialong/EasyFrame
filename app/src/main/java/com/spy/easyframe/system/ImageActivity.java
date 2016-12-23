package com.spy.easyframe.system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.spy.easyframe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    @Bind(R.id.imageIv)
    ImageView imageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        Log.e("TAG",image);
        Glide.with(this).load(image).into(imageIv);
    }
}
