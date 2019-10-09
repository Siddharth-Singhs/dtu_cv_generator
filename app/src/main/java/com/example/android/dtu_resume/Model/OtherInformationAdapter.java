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
import com.example.android.dtu_resume.Holder.OtherInformation;
import com.example.android.dtu_resume.R;

import java.util.ArrayList;

public class OtherInformationAdapter extends ArrayAdapter<OtherInformation>{

    private Activity context;
    private ArrayList<OtherInformation> otherInformationArrayList;

    public OtherInformationAdapter(@NonNull Activity context, ArrayList<OtherInformation> otherInformationArrayList) {
        super(context, R.layout.other_list_view, otherInformationArrayList);
        this.context = context;
        this.otherInformationArrayList = otherInformationArrayList;
    }

    @Override
    public int getCount() {
        return otherInformationArrayList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.other_list_view, null, true);


        //
        TextView otherView = (TextView) listItemView.findViewById(R.id.other_view);
        EditText otherEditView = (EditText) listItemView.findViewById(R.id.edit_other_view);

        otherEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otherInformationArrayList.get(position).setOther(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final OtherInformation currentOtherInformation = otherInformationArrayList.get(position);
        String otherCount="Other Information "+ (position+1);
        otherView.setText(otherCount);
        otherEditView.setText(currentOtherInformation.getOther());


        return listItemView;
    }



}
