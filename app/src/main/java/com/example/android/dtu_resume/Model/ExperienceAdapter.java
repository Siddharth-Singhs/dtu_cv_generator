package com.example.android.dtu_resume.Model;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.dtu_resume.Holder.InternshipInformation;
import com.example.android.dtu_resume.R;

import java.util.ArrayList;


public class ExperienceAdapter extends ArrayAdapter<InternshipInformation> {

    private Activity context;
    private ArrayList<InternshipInformation> internshipExperience;

    public ExperienceAdapter(@NonNull Activity context, ArrayList<InternshipInformation> internshipExperience) {
        super(context, R.layout.internship_list_view, internshipExperience);
        this.context = context;
        this.internshipExperience = internshipExperience;
    }

    @Override
    public int getCount() {
        return internshipExperience.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.internship_list_view, null, true);


        //
        TextView experienceView = (TextView) listItemView.findViewById(R.id.experience_view);
        EditText organisationView = (EditText) listItemView.findViewById(R.id.edit_organisation_view);
        EditText designationView = (EditText) listItemView.findViewById(R.id.edit_designation_view);
        EditText dateView = (EditText) listItemView.findViewById(R.id.edit_date_view);
        EditText roleView = (EditText) listItemView.findViewById(R.id.edit_role_view);





        organisationView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                internshipExperience.get(position).setOrganisation(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        designationView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                internshipExperience.get(position).setDesignation(charSequence.toString());
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
                internshipExperience.get(position).setDate(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        roleView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                internshipExperience.get(position).setRole(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final InternshipInformation currentInternshipInformation = internshipExperience.get(position);
        String experienceCount="Experience "+ (position+1);
        experienceView.setText(experienceCount);
        organisationView.setText(currentInternshipInformation.getOrganisation());
        designationView.setText(currentInternshipInformation.getDesignation());
        dateView.setText(currentInternshipInformation.getDate());
        roleView.setText(currentInternshipInformation.getRole());

        return listItemView;
    }



}