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
import com.example.android.dtu_resume.Holder.PorInformation;
import com.example.android.dtu_resume.Model.ExperienceAdapter;
import com.example.android.dtu_resume.Model.PorAdapter;
import com.example.android.dtu_resume.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PorDetail extends AppCompatActivity {

    private ListView porListView;
    private PorAdapter porAdapter;
    private ArrayList<PorInformation> porInformationArrayList;

    private Button addButton;
    private Button saveButton;
    private ImageView clearImageView;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_por_detail);

        porListView=(ListView)findViewById(R.id.por_list_view);
        porInformationArrayList=new ArrayList<>();
        addButton=(Button)findViewById(R.id.add_button);
        saveButton=(Button)findViewById(R.id.save_button);
        clearImageView=(ImageView) findViewById(R.id.clear_view);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePorDetail();

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPorDetail();
            }
        });

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPor();
            }
        });

    }

    private void savePorDetail() {
        for(int position=0;position<porInformationArrayList.size();position++)
        {
            PorInformation currentInformation = porInformationArrayList.get(position);
            Gson gson = new Gson();
            String json = gson.toJson(currentInformation);
            String key=getString(R.string.por_key)+position;
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
            String key=getString(R.string.por_key)+start;
            String por_str=mPreferences.getString(key,"");
            PorInformation porInformation=gson.fromJson(por_str, PorInformation.class);
            if(porInformation!=null) {
                porInformationArrayList.add(porInformation);
            }
            start++;
        }
        if(porInformationArrayList.size()>0) {
            porAdapter = new PorAdapter(PorDetail.this, porInformationArrayList);
            porListView.setAdapter(porAdapter);
        }
    }
    private void addPorDetail()
    {
        if(porInformationArrayList.size() > 0)
        {
            int lastElement=porInformationArrayList.size() - 1;
            PorInformation lastPorInformation=porInformationArrayList.get(lastElement);
            String title= lastPorInformation.getTitle();
            String description=lastPorInformation.getDescription();

            if(title.length() >0 && description.length() > 0 )
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
    private void clearPor()
    {
        int currentSize=porInformationArrayList.size();
        if(currentSize > 0)
        {
            porInformationArrayList.remove(currentSize-1);
            String key=getString(R.string.por_key)+ (currentSize-1);
            mEditor.remove(key);
            mEditor.apply();
            porAdapter = new PorAdapter(PorDetail.this, porInformationArrayList);
            porListView.setAdapter(porAdapter);

        }
        Toast.makeText(this,"Clearing the Experience",Toast.LENGTH_SHORT).show();
    }
    private void creatingNewElement()
    {
        PorInformation porInformation=new PorInformation("","");
        porInformationArrayList.add(porInformation);
        porAdapter =new PorAdapter(PorDetail.this,porInformationArrayList);
        porListView.setAdapter(porAdapter);
    }
}
