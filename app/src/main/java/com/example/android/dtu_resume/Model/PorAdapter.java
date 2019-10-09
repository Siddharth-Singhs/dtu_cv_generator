package com.example.android.dtu_resume.Model;


import android.app.Activity;
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

import com.example.android.dtu_resume.Holder.PorInformation;
import com.example.android.dtu_resume.R;

import java.util.ArrayList;

    public class PorAdapter extends ArrayAdapter<PorInformation> {
        private Activity context;
        private ArrayList<PorInformation> porInformationArrayList;

        public PorAdapter(@NonNull Activity context, ArrayList<PorInformation> porInformationArrayList) {
            super(context, R.layout.por_list_view, porInformationArrayList);
            this.context = context;
            this.porInformationArrayList = porInformationArrayList;
        }

        @Override
        public int getCount() {
            return porInformationArrayList.size();
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            LayoutInflater inflater = context.getLayoutInflater();
            View listItemView = inflater.inflate(R.layout.por_list_view, null, true);


            //
            TextView porView = (TextView) listItemView.findViewById(R.id.por_view);
            EditText titleView = (EditText) listItemView.findViewById(R.id.edit_title_view);
            EditText descriptionView=(EditText)listItemView.findViewById(R.id.edit_description_view);






            titleView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    porInformationArrayList.get(position).setTitle(charSequence.toString());
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
                    porInformationArrayList.get(position).setDescription(charSequence.toString());
                }


                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            final  PorInformation currentPorInformation = porInformationArrayList.get(position);
            String porCount="Positions of Responsibility "+ (position+1);
            porView.setText(porCount);
            titleView.setText(currentPorInformation.getTitle());
            descriptionView.setText(currentPorInformation.getDescription());

            return listItemView;
        }

    }
