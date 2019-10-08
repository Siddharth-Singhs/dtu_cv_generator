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


import com.example.android.dtu_resume.Holder.InternshipInformation;
import com.example.android.dtu_resume.Holder.ProjectInformation;
import com.example.android.dtu_resume.R;

import java.util.ArrayList;

public class ProjectAdapter extends ArrayAdapter<ProjectInformation> {

    private Activity context;
    private ArrayList<ProjectInformation> projectInformationArrayList;

    public ProjectAdapter(@NonNull Activity context, ArrayList<ProjectInformation> projectInformationArrayList) {
        super(context, R.layout.project_list_view, projectInformationArrayList);
        this.context = context;
        this.projectInformationArrayList = projectInformationArrayList;
    }

    @Override
    public int getCount() {
        return projectInformationArrayList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.project_list_view, null, true);


        //
        TextView projectView = (TextView) listItemView.findViewById(R.id.project_view);
        EditText titleView = (EditText) listItemView.findViewById(R.id.edit_title_view);
        EditText descriptionView = (EditText) listItemView.findViewById(R.id.edit_description_view);
        EditText dateView = (EditText) listItemView.findViewById(R.id.edit_date_view);




        titleView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                projectInformationArrayList.get(position).setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        descriptionView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                projectInformationArrayList.get(position).setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dateView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                projectInformationArrayList.get(position).setDate(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final ProjectInformation currentProjectInformation = projectInformationArrayList.get(position);
        String experienceCount="Experience "+ (position+1);
        projectView.setText(experienceCount);
        titleView.setText(currentProjectInformation.getTitle());
        descriptionView.setText(currentProjectInformation.getDescription());
        dateView.setText(currentProjectInformation.getDate());


        return listItemView;
    }
}
