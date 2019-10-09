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
import com.example.android.dtu_resume.Holder.OtherInformation;
import com.example.android.dtu_resume.Model.AchievementAdapter;
import com.example.android.dtu_resume.Model.OtherInformationAdapter;
import com.example.android.dtu_resume.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class OtherInformationDetail extends AppCompatActivity {

    private ListView otherListView;
    private OtherInformationAdapter otherInformationAdapter;
    private ArrayList<OtherInformation> otherInformationArrayList;

    private Button addButton;
    private Button saveButton;
    private ImageView clearImageView;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_information_detail);

        otherListView=(ListView)findViewById(R.id.other_list_view);
        otherInformationArrayList=new ArrayList<>();
        addButton=(Button)findViewById(R.id.add_button);
        saveButton=(Button)findViewById(R.id.save_button);
        clearImageView=(ImageView)findViewById(R.id.clear_view);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOtherDetail();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOtherDetail();
            }
        });

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearOtherDetail();
            }
        });
    }
    private void saveOtherDetail() {
        for(int position=0;position<otherInformationArrayList.size();position++)
        {
            OtherInformation currentInformation = otherInformationArrayList.get(position);
            Gson gson = new Gson();
            String json = gson.toJson(currentInformation);
            String key=getString(R.string.other_information_key)+position;
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
            String key=getString(R.string.other_information_key)+start;
            String other_str=mPreferences.getString(key,"");
            OtherInformation otherInformation=gson.fromJson(other_str, OtherInformation.class);
            if(otherInformation!=null) {
                otherInformationArrayList.add(otherInformation);
            }
            start++;
        }
        if(otherInformationArrayList.size()>0) {
            otherInformationAdapter =new OtherInformationAdapter(OtherInformationDetail.this,otherInformationArrayList);
            otherListView.setAdapter(otherInformationAdapter);

        }
    }

    private void clearOtherDetail()
    {
        int currentSize=otherInformationArrayList.size();
        if(currentSize > 0)
        {
            otherInformationArrayList.remove(currentSize-1);
            String key=getString(R.string.other_information_key)+ (currentSize-1);
            mEditor.remove(key);
            mEditor.apply();
            otherInformationAdapter =new OtherInformationAdapter(OtherInformationDetail.this,otherInformationArrayList);
            otherListView.setAdapter(otherInformationAdapter);

        }
        Toast.makeText(this,"Clearing the Project",Toast.LENGTH_SHORT).show();
    }

    private void addOtherDetail()
    {
        if(otherInformationArrayList.size() > 0)
        {
            int lastElement=otherInformationArrayList.size() - 1;
            OtherInformation lastOtherInformation=otherInformationArrayList.get(lastElement);
            String other_desc=lastOtherInformation.getOther();

            if(other_desc.length() >0)
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
        OtherInformation otherInformation=new OtherInformation("");
        otherInformationArrayList.add(otherInformation);
        otherInformationAdapter =new OtherInformationAdapter(OtherInformationDetail.this,otherInformationArrayList);
        otherListView.setAdapter(otherInformationAdapter);
    }
}
