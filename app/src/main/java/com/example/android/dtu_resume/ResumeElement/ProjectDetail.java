package com.example.android.dtu_resume.ResumeElement;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.android.dtu_resume.Holder.InternshipInformation;
import com.example.android.dtu_resume.Holder.ProjectInformation;
import com.example.android.dtu_resume.Model.ExperienceAdapter;
import com.example.android.dtu_resume.Model.ProjectAdapter;
import com.example.android.dtu_resume.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ProjectDetail extends AppCompatActivity {

    private ListView projectListView;
    private ProjectAdapter projectAdapter;
    private ArrayList<ProjectInformation> projectInformationList;

    private Button addButton;
    private Button saveButton;
    private ImageView clearImageView;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        projectListView=(ListView)findViewById(R.id.project_list_view);
        projectInformationList=new ArrayList<>();
        addButton=(Button)findViewById(R.id.add_button);
        saveButton=(Button)findViewById(R.id.save_button);
        clearImageView=(ImageView)findViewById(R.id.clear_view);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProjectDetail();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProjectDetail();
            }
        });

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearProjectDetail();
            }
        });
    }

    private void saveProjectDetail()
    {
        for(int position=0;position<projectInformationList.size();position++)
        {
            ProjectInformation currentInformation = projectInformationList.get(position);
            Gson gson = new Gson();
            String json = gson.toJson(currentInformation);
            String key=getString(R.string.project_key)+position;
            mEditor.putString(key,json);
            mEditor.commit();
        }
        Toast.makeText(this,R.string.save_detail,Toast.LENGTH_SHORT).show();
    }

    private void addProjectDetail()
    {
        if(projectInformationList.size() > 0)
        {
            int lastElement=projectInformationList.size() - 1;
            ProjectInformation lastProjectInformation=projectInformationList.get(lastElement);
            String title= lastProjectInformation.getTitle();
            String description=lastProjectInformation.getDescription();
            String date=lastProjectInformation.getDate();

            if(title.length() >0 && description.length() > 0 && date.length() > 0)
            {
                creatingNewElement();
            }
            else
            {
                Toast.makeText(this,"First fill the detail",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            creatingNewElement();
        }
    }
    private void creatingNewElement()
    {
        ProjectInformation projectInformation=new ProjectInformation("","","");
        projectInformationList.add(projectInformation);
        projectAdapter =new ProjectAdapter(ProjectDetail.this,projectInformationList);
        projectListView.setAdapter(projectAdapter);
    }

    private void clearProjectDetail()
    {
        int currentSize=projectInformationList.size();
        if(currentSize > 0)
        {
            projectInformationList.remove(currentSize-1);
            String key=getString(R.string.project_key)+ (currentSize-1);
            mEditor.remove(key);
            mEditor.apply();
            projectAdapter =new ProjectAdapter(ProjectDetail.this,projectInformationList);
            projectListView.setAdapter(projectAdapter);

        }
        Toast.makeText(this,"Clearing the Project",Toast.LENGTH_SHORT).show();
    }

    private void checkSharedPreference()
    {
        int size=mPreferences.getAll().size();
        int start=0;
        while(start< size)
        {
            Gson gson = new Gson();
            String key=getString(R.string.project_key)+start;
            String project_str=mPreferences.getString(key,"");
            ProjectInformation projectInformation=gson.fromJson(project_str, ProjectInformation.class);
            if(projectInformation!=null) {
                projectInformationList.add(projectInformation);
            }
            start++;
        }
        if(projectInformationList.size()>0) {
            projectAdapter = new ProjectAdapter(ProjectDetail.this, projectInformationList);
            projectListView.setAdapter(projectAdapter);
        }
    }
}
