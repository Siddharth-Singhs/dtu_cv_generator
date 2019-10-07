package com.example.android.dtu_resume.ResumeElement;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.dtu_resume.R;

public class EducationDetail extends AppCompatActivity {

    private EditText collegeCourseView;
    private EditText collegeYearView;
    private EditText collegeNameView;
    private EditText collegeCGPAView;

    private EditText class12BoardView;
    private EditText class12YearView;
    private EditText class12NameView;
    private EditText class12CGPAView;

    private EditText class10BoardView;
    private EditText class10YearView;
    private EditText class10NameView;
    private EditText class10CGPAView;

    private Button saveButton;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_detail);

        saveButton=(Button)findViewById(R.id.save_button);
        // College View Initialise
        collegeCourseView=(EditText)findViewById(R.id.edit_course_view);
        collegeYearView=(EditText)findViewById(R.id.edit_year_view);
        collegeNameView=(EditText)findViewById(R.id.edit_college_view);
        collegeCGPAView=(EditText)findViewById(R.id.edit_college_cpga_view);
        // Class 12 View Initialise
        class12BoardView=(EditText)findViewById(R.id.edit_board_12_view);
        class12YearView=(EditText)findViewById(R.id.edit_year_12_view);
        class12NameView=(EditText)findViewById(R.id.edit_school_12_view);
        class12CGPAView=(EditText)findViewById(R.id.edit_school_12_percent_view);
        // Class 10 View Initialise
        class10BoardView=(EditText)findViewById(R.id.edit_board_10_view);
        class10YearView=(EditText)findViewById(R.id.edit_year_10_view);
        class10NameView=(EditText)findViewById(R.id.edit_school_10_view);
        class10CGPAView=(EditText)findViewById(R.id.edit_school_10_percent_view);


        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBasicDetail();
            }
        });
    }

    private void saveBasicDetail() {

        String collegeCourse=collegeCourseView.getText().toString().trim();
        String collegeYear=collegeYearView.getText().toString().trim();
        String collegeName=collegeNameView.getText().toString().trim();
        String collegeCGPA=collegeCGPAView.getText().toString().trim();

        String class12Board=class12BoardView.getText().toString().trim();
        String class12Year=class12YearView.getText().toString().trim();
        String class12Name=class12NameView.getText().toString().trim();
        String class12CGPA=class12CGPAView.getText().toString().trim();

        String class10Board=class10BoardView.getText().toString().trim();
        String class10Year=class10YearView.getText().toString().trim();
        String class10Name=class10NameView.getText().toString().trim();
        String class10CGPA=class10CGPAView.getText().toString().trim();

        // Setting the college value
        mEditor.putString(getString(R.string.college_course_key),collegeCourse);
        mEditor.commit();

        mEditor.putString(getString(R.string.college_year_key),collegeYear);
        mEditor.commit();

        mEditor.putString(getString(R.string.college_name_key),collegeName);
        mEditor.commit();

        mEditor.putString(getString(R.string.college_cgpa_key),collegeCGPA);
        mEditor.commit();


        // Setting the class 12 Value
        mEditor.putString(getString(R.string.class_12_board_key),class12Board);
        mEditor.commit();

        mEditor.putString(getString(R.string.class_12_year_key),class12Year);
        mEditor.commit();

        mEditor.putString(getString(R.string.class_12_name_key),class12Name);
        mEditor.commit();

        mEditor.putString(getString(R.string.class_12_cgpa_key),class12CGPA);
        mEditor.commit();

        // Setting the Class 10 Value
        mEditor.putString(getString(R.string.class_10_board_key),class10Board);
        mEditor.commit();

        mEditor.putString(getString(R.string.class_10_year_key),class10Year);
        mEditor.commit();

        mEditor.putString(getString(R.string.class_10_name_key),class10Name);
        mEditor.commit();

        mEditor.putString(getString(R.string.class_10_cgpa_key),class10CGPA);
        mEditor.commit();

        Toast.makeText(this,R.string.save_detail,Toast.LENGTH_SHORT).show();

    }



    private void checkSharedPreference()
    {
        String collegeCourse=mPreferences.getString(getString(R.string.college_course_key),"");
        String collegeYear=mPreferences.getString(getString(R.string.college_year_key),"");
        String collegeName=mPreferences.getString(getString(R.string.college_name_key),"");
        String collegeCGPA=mPreferences.getString(getString(R.string.college_cgpa_key),"");

        String class12Board=mPreferences.getString(getString(R.string.class_12_board_key),"");
        String class12Year=mPreferences.getString(getString(R.string.class_12_year_key),"");
        String class12Name=mPreferences.getString(getString(R.string.class_12_name_key),"");
        String class12CGPA=mPreferences.getString(getString(R.string.class_12_cgpa_key),"");

        String class10Board=mPreferences.getString(getString(R.string.class_10_board_key),"");
        String class10Year=mPreferences.getString(getString(R.string.class_10_year_key),"");
        String class10Name=mPreferences.getString(getString(R.string.class_10_name_key),"");
        String class10CGPA=mPreferences.getString(getString(R.string.class_10_cgpa_key),"");

        // Setting the college value
        if(collegeCourse.length() > 0)
        {
            collegeCourseView.setText(collegeCourse);
        }
        if(collegeYear.length() >0 )
        {
            collegeYearView.setText(collegeYear);
        }
        if(collegeName.length() > 0)
        {
            collegeNameView.setText(collegeName);
        }
        if(collegeCGPA.length() > 0)
        {
            collegeCGPAView.setText(collegeCGPA);
        }

        // Setting the class 12 Value
        if(class12Board.length() > 0)
        {
            class12BoardView.setText(class12Board);
        }
        if(class12Year.length() > 0)
        {
            class12YearView.setText(class12Year);
        }
        if(class12Name.length() > 0)
        {
            class12NameView.setText(class12Name);
        }
        if(class12CGPA.length() > 0)
        {
            class12CGPAView.setText(class12CGPA);
        }

        // Setting the Class 10 Value
        if(class10Board.length() > 0)
        {
            class10BoardView.setText(class10Board);
        }
        if(class10Year.length() > 0)
        {
            class10YearView.setText(class10Year);
        }
        if(class10Name.length() > 0)
        {
            class10NameView.setText(class10Name);
        }
        if(class10CGPA.length() > 0)
        {
            class10CGPAView.setText(class10CGPA);
        }

    }
}
