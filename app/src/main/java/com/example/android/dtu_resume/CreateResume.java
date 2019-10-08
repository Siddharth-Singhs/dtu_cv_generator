package com.example.android.dtu_resume;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.android.dtu_resume.ResumeElement.BasicDetail;
import com.example.android.dtu_resume.ResumeElement.EducationDetail;
import com.example.android.dtu_resume.ResumeElement.InternshipDetail;
import com.example.android.dtu_resume.ResumeElement.ProjectDetail;

public class CreateResume extends AppCompatActivity {

    private CardView basicDetail;
    private CardView educationDetail;
    private CardView internshipDetail;
    private CardView projectDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resume);

        basicDetail=(CardView)findViewById(R.id.basic_detail_card);
        basicDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BasicDetail.class));
            }
        });

        educationDetail=(CardView)findViewById(R.id.education_card);
        educationDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EducationDetail.class));
            }
        });

        internshipDetail=(CardView)findViewById(R.id.experience_detail_card);
        internshipDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InternshipDetail.class));
            }
        });

        projectDetail=(CardView) findViewById(R.id.project_detail_card);
        projectDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProjectDetail.class));
            }
        });

    }
}
