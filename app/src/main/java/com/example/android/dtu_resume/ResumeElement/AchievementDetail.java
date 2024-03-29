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

import com.example.android.dtu_resume.Holder.AchievementInformation;
import com.example.android.dtu_resume.Holder.ProjectInformation;
import com.example.android.dtu_resume.Model.AchievementAdapter;
import com.example.android.dtu_resume.Model.ProjectAdapter;
import com.example.android.dtu_resume.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AchievementDetail extends AppCompatActivity {

    private ListView achievementListView;
    private AchievementAdapter achievementAdapter;
    private ArrayList<AchievementInformation> achievementInformationList;

    private Button addButton;
    private Button saveButton;
    private ImageView clearImageView;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_detail);


        achievementListView=(ListView)findViewById(R.id.achievement_list_view);
        achievementInformationList=new ArrayList<>();
        addButton=(Button)findViewById(R.id.add_button);
        saveButton=(Button)findViewById(R.id.save_button);
        clearImageView=(ImageView)findViewById(R.id.clear_view);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAchievementDetail();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAchievementDetail();
            }
        });

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAchievementDetail();
            }
        });
    }

    private void saveAchievementDetail() {
        for(int position=0;position<achievementInformationList.size();position++)
        {
            AchievementInformation currentInformation = achievementInformationList.get(position);
            Gson gson = new Gson();
            String json = gson.toJson(currentInformation);
            String key=getString(R.string.achievement_award_key)+position;
            mEditor.putString(key,json);
            mEditor.commit();
        }
        Toast.makeText(this,R.string.save_detail,Toast.LENGTH_SHORT).show();
    }

    private void checkSharedPreference() {
        int size=mPreferences.getAll().size();
        int start=0;
        while(start< size)
        {
            Gson gson = new Gson();
            String key=getString(R.string.achievement_award_key)+start;
            String achievement_str=mPreferences.getString(key,"");
            AchievementInformation achievementInformation=gson.fromJson(achievement_str, AchievementInformation.class);
            if(achievementInformation!=null) {
                achievementInformationList.add(achievementInformation);
            }
            start++;
        }
        if(achievementInformationList.size()>0) {
            achievementAdapter =new AchievementAdapter(AchievementDetail.this,achievementInformationList);
            achievementListView.setAdapter(achievementAdapter);

        }
    }

    private void clearAchievementDetail()
    {
        int currentSize=achievementInformationList.size();
        if(currentSize > 0)
        {
            achievementInformationList.remove(currentSize-1);
            String key=getString(R.string.achievement_award_key)+ (currentSize-1);
            mEditor.remove(key);
            mEditor.apply();
            achievementAdapter =new AchievementAdapter(AchievementDetail.this,achievementInformationList);
            achievementListView.setAdapter(achievementAdapter);

        }
        Toast.makeText(this,"Clearing the Project",Toast.LENGTH_SHORT).show();
    }

    private void addAchievementDetail()
    {
        if(achievementInformationList.size() > 0)
        {
            int lastElement=achievementInformationList.size() - 1;
            AchievementInformation lastAchievementInformation=achievementInformationList.get(lastElement);
            String achievement_desc=lastAchievementInformation.getAchievement();

            if(achievement_desc.length() >0)
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
        AchievementInformation achievementInformation=new AchievementInformation("");
        achievementInformationList.add(achievementInformation);
        achievementAdapter =new AchievementAdapter(AchievementDetail.this,achievementInformationList);
        achievementListView.setAdapter(achievementAdapter);
    }
}
