package com.example.android.dtu_resume.ResumeElement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.dtu_resume.CreateResume;
import com.example.android.dtu_resume.R;

public class BasicDetail extends AppCompatActivity {

    private ImageView clearView;
    private EditText nameView;
    private EditText rollNoView;
    private EditText emailView;
    private EditText phoneNoView;
    private EditText websiteView;
    private Button saveButton;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_detail);

        // Initialising the view
        clearView=(ImageView) findViewById(R.id.clear_view);
        nameView=(EditText)findViewById(R.id.edit_name_view);
        rollNoView=(EditText)findViewById(R.id.edit_roll_no_view);
        emailView=(EditText)findViewById(R.id.edit_email_view);
        phoneNoView=(EditText)findViewById(R.id.edit_phone_view);
        websiteView=(EditText)findViewById(R.id.edit_website_view);
        saveButton=(Button)findViewById(R.id.save_button);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();


        clearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clearSharedPreference();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBasicDetail();
            }
        });


    }

    private void clearSharedPreference()
    {
        mPreferences.edit().clear().commit();
        nameView.getText().clear();
        rollNoView.getText().clear();
        emailView.getText().clear();
        phoneNoView.getText().clear();
        websiteView.getText().clear();

    }


    private void checkSharedPreference()
    {
        String name=mPreferences.getString(getString(R.string.name_key),"");
        String rollNo=mPreferences.getString(getString(R.string.roll_no_key),"");
        if(name.length() == 0 || rollNo.length() == 0)
        {
            return;
        }
        String email=mPreferences.getString(getString(R.string.email_key),"");
        String phoneNo=mPreferences.getString(getString(R.string.phone_key),"");
        String website=mPreferences.getString(getString(R.string.website_key),"");

        nameView.setText(name);
        rollNoView.setText(rollNo);
        if(email.length()>0)
        {
            emailView.setText(email);
        }
        if(phoneNo.length()>0)
        {
            phoneNoView.setText(phoneNo);
        }
        if(website.length()>0)
        {
            websiteView.setText(website);
        }

    }
    private void saveBasicDetail() {
        String name = nameView.getText().toString().trim();
        String rollNo=rollNoView.getText().toString().trim();
        String email = emailView.getText().toString().trim();
        String phoneNo=phoneNoView.getText().toString().trim();
        String website = websiteView.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            //if name is empty
            nameView.setHintTextColor(getColor(R.color.dot_light_screen1));
            if (TextUtils.isEmpty(rollNo))
            {
                rollNoView.setHintTextColor(getColor(R.color.dot_light_screen1));
            }
            Toast.makeText(this, R.string.name_required, Toast.LENGTH_SHORT).show();
            //stopping the futher execution
            return;
        }
        if (TextUtils.isEmpty(rollNo)) {
            //if email is empty
            rollNoView.setHintTextColor(getColor(R.color.dot_light_screen1));
            Toast.makeText(this, R.string.roll_no_required, Toast.LENGTH_SHORT).show();
            //stopping the futher execution
            return;
        }
        //
        mEditor.putString(getString(R.string.name_key),name);
        mEditor.commit();
        mEditor.putString(getString(R.string.roll_no_key),rollNo);
        mEditor.commit();

        if (!(TextUtils.isEmpty(email))) {
            mEditor.putString(getString(R.string.email_key),email);
            mEditor.commit();
        }
        if (!(TextUtils.isEmpty(phoneNo))) {
            mEditor.putString(getString(R.string.phone_key),phoneNo);
            mEditor.commit();
        }
        if (!(TextUtils.isEmpty(website))) {
            mEditor.putString(getString(R.string.website_key),website);
            mEditor.commit();
        }
        Toast.makeText(this,R.string.save_detail,Toast.LENGTH_SHORT).show();

    }
}
