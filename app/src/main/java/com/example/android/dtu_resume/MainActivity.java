package com.example.android.dtu_resume;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private CardView create_resume;
    private CardView download_resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_resume=(CardView) findViewById(R.id.create_resume_card);
        download_resume=(CardView) findViewById(R.id.download_resume_card);

        create_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CreateResume.class));
            }
        });
    }
}
