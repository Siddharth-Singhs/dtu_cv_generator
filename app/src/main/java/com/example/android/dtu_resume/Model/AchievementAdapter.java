package com.example.android.dtu_resume.Model;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.dtu_resume.Holder.AchievementInformation;
import com.example.android.dtu_resume.Holder.InternshipInformation;
import com.example.android.dtu_resume.R;

import java.util.ArrayList;

public class AchievementAdapter extends ArrayAdapter<AchievementInformation> {
    private Activity context;
    private ArrayList<AchievementInformation> achievementInformationArrayList;

    public AchievementAdapter(@NonNull Activity context, ArrayList<AchievementInformation> achievementInformationArrayList) {
        super(context, R.layout.achievement_list_view, achievementInformationArrayList);
        this.context = context;
        this.achievementInformationArrayList = achievementInformationArrayList;
    }

    @Override
    public int getCount() {
        return achievementInformationArrayList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.achievement_list_view, null, true);


        //
        TextView achievementView = (TextView) listItemView.findViewById(R.id.achievement_view);
        EditText achievementEditView = (EditText) listItemView.findViewById(R.id.edit_achievement_view);






        achievementEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                achievementInformationArrayList.get(position).setAchievement(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final AchievementInformation currentAchievementInformation = achievementInformationArrayList.get(position);
        String achievementCount="Achievement & Awards "+ (position+1);
        achievementView.setText(achievementCount);
        achievementEditView.setText(currentAchievementInformation.getAchievement());


        return listItemView;
    }

}
