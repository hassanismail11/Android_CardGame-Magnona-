package com.example.magnona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout playSoloBut;
    RelativeLayout playOnlineBut;
    RelativeLayout ProfileBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent playSolo = new Intent(HomeActivity.this, PlaySoloActivity.class);
        startActivity(playSolo);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
                insetsController.hide(WindowInsets.Type.navigationBars());
            }
        } else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }

        playSoloBut = findViewById(R.id.playSoloBut);
        playSoloBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSolo = new Intent(HomeActivity.this, PlaySoloActivity.class);
                startActivity(playSolo);
            }
        });

        playOnlineBut = findViewById(R.id.playOnlineBut);
        playOnlineBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSolo = new Intent(HomeActivity.this, PlaySoloActivity.class);
                startActivity(playSolo);
            }
        });

        ProfileBut = findViewById(R.id.ProfileBut);
        ProfileBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSolo = new Intent(HomeActivity.this, PlaySoloActivity.class);
                startActivity(playSolo);
            }
        });


    }
}