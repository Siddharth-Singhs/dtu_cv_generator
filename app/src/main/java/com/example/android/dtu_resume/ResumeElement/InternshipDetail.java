package com.example.android.dtu_resume.ResumeElement;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.dtu_resume.Holder.InternshipInformation;
import com.example.android.dtu_resume.Model.ExperienceAdapter;
import com.example.android.dtu_resume.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class InternshipDetail extends AppCompatActivity {

    private ListView experienceListView;
    private ExperienceAdapter experienceAdapter;
    private ArrayList<InternshipInformation> internshipInformationList;

    private Button addButton;
    private Button saveButton;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_detail);

        experienceListView=(ListView)findViewById(R.id.experience_list_view);
        internshipInformationList=new ArrayList<>();
        addButton=(Button)findViewById(R.id.add_button);
        saveButton=(Button)findViewById(R.id.save_button);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInternDetail();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInternDetail();
            }
        });

    }

    private void checkSharedPreference() {
        int size=mPreferences.getAll().size();
        int start=0;
        while(start< size)
        {
            Gson gson = new Gson();
            String key=getString(R.string.internship_key)+start;
            String internship_str=mPreferences.getString(key,"");
            InternshipInformation internshipInformation=gson.fromJson(internship_str, InternshipInformation.class);
            if(internshipInformation!=null) {
                internshipInformationList.add(internshipInformation);
            }
            start++;
        }
        if(internshipInformationList.size()>0) {
            experienceAdapter = new ExperienceAdapter(InternshipDetail.this, internshipInformationList);
            experienceListView.setAdapter(experienceAdapter);
        }
    }
    private void saveInternDetail()
    {
        for(int position=0;position<internshipInformationList.size();position++)
        {
            InternshipInformation currentInformation = internshipInformationList.get(position);
            Gson gson = new Gson();
            String json = gson.toJson(currentInformation);
            String key=getString(R.string.internship_key)+position;
            mEditor.putString(key,json);
            mEditor.commit();

        }
    }
    private void addInternDetail()
    {
        if(internshipInformationList.size() > 0)
        {
            int lastElement=internshipInformationList.size() - 1;
            InternshipInformation lastInternshipInformation=internshipInformationList.get(lastElement);
            String organisation= lastInternshipInformation.getOrganisation();
            String designation=lastInternshipInformation.getDesignation();
            String date=lastInternshipInformation.getDate();
            String role=lastInternshipInformation.getRole();
            if(organisation.length() >0 && designation.length() > 0 && date.length() > 0 && role.length() > 0)
            {
                creatingNewElement();
            }
        }
        else
        {
            creatingNewElement();
        }


    }
    private void creatingNewElement()
    {
        InternshipInformation internshipInformation=new InternshipInformation("","","","");
        internshipInformationList.add(internshipInformation);
        experienceAdapter =new ExperienceAdapter(InternshipDetail.this,internshipInformationList);
        experienceListView.setAdapter(experienceAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
