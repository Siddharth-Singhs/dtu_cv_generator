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

import com.example.android.dtu_resume.Holder.ExtraCurricularInformation;
import com.example.android.dtu_resume.Model.ExtraCurricularAdapter;
import com.example.android.dtu_resume.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ExtraCurricularDetail extends AppCompatActivity {

    private ListView extraListView;
    private ExtraCurricularAdapter extraCurricularAdapter;
    private ArrayList<ExtraCurricularInformation> extraCurricularInformationArrayList;

    private Button addButton;
    private Button saveButton;
    private ImageView clearImageView;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_curricular_detail);
        extraListView=(ListView)findViewById(R.id.extra_list_view);
        extraCurricularInformationArrayList =new ArrayList<>();
        addButton=(Button)findViewById(R.id.add_button);
        saveButton=(Button)findViewById(R.id.save_button);
        clearImageView=(ImageView)findViewById(R.id.clear_view);

        mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();

        checkSharedPreference();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExtraDetail();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExtraDetail();
            }
        });

        clearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearExtraDetail();
            }
        });
    }

    private void saveExtraDetail() {
        for(int position = 0; position< extraCurricularInformationArrayList.size(); position++)
        {
            ExtraCurricularInformation currentInformation = extraCurricularInformationArrayList.get(position);
            Gson gson = new Gson();
            String json = gson.toJson(currentInformation);
            String key=getString(R.string.extra_curricular_key)+position;
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
            String key=getString(R.string.extra_curricular_key)+start;
            String extra_str=mPreferences.getString(key,"");
            ExtraCurricularInformation extraCurricularInformation=gson.fromJson(extra_str, ExtraCurricularInformation.class);
            if(extraCurricularInformation!=null) {
                extraCurricularInformationArrayList.add(extraCurricularInformation);
            }
            start++;
        }
        if(extraCurricularInformationArrayList.size()>0) {
            extraCurricularAdapter =new ExtraCurricularAdapter(ExtraCurricularDetail.this,extraCurricularInformationArrayList);
            extraListView.setAdapter(extraCurricularAdapter);

        }
    }

    private void clearExtraDetail()
    {
        int currentSize=extraCurricularInformationArrayList.size();
        if(currentSize > 0)
        {
            extraCurricularInformationArrayList.remove(currentSize-1);
            String key=getString(R.string.extra_curricular_key)+ (currentSize-1);
            mEditor.remove(key);
            mEditor.apply();
            extraCurricularAdapter =new ExtraCurricularAdapter(ExtraCurricularDetail.this,extraCurricularInformationArrayList);
            extraListView.setAdapter(extraCurricularAdapter);

        }
        Toast.makeText(this,"Clearing the Project",Toast.LENGTH_SHORT).show();
    }

    private void addExtraDetail()
    {
        if(extraCurricularInformationArrayList.size() > 0)
        {
            int lastElement=extraCurricularInformationArrayList.size() - 1;
            ExtraCurricularInformation lastExtraInformation=extraCurricularInformationArrayList.get(lastElement);
            String extraCurricular=lastExtraInformation.getExtraCurricular();

            if(extraCurricular.length() >0)
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
        ExtraCurricularInformation extraCurricularInformation=new ExtraCurricularInformation("");
        extraCurricularInformationArrayList.add(extraCurricularInformation);
        extraCurricularAdapter =new ExtraCurricularAdapter(ExtraCurricularDetail.this,extraCurricularInformationArrayList);
        extraListView.setAdapter(extraCurricularAdapter);
    }
}
