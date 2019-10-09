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
import com.example.android.dtu_resume.Holder.ExtraCurricularInformation;
import com.example.android.dtu_resume.R;

import java.util.ArrayList;

public class ExtraCurricularAdapter extends ArrayAdapter<ExtraCurricularInformation> {
    private Activity context;
    private ArrayList<ExtraCurricularInformation> extraCurricularInformationArrayList;

    public ExtraCurricularAdapter(@NonNull Activity context, ArrayList<ExtraCurricularInformation> extraCurricularInformationArrayList) {
        super(context, R.layout.extra_list_view, extraCurricularInformationArrayList);
        this.context = context;
        this.extraCurricularInformationArrayList = extraCurricularInformationArrayList;
    }

    @Override
    public int getCount() {
        return extraCurricularInformationArrayList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.extra_list_view, null, true);


        //
        TextView extraView = (TextView) listItemView.findViewById(R.id.extra_view);
        EditText extraEditView = (EditText) listItemView.findViewById(R.id.edit_extra_view);






        extraEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                extraCurricularInformationArrayList.get(position).setExtraCurricular(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final ExtraCurricularInformation currentExtraInformation = extraCurricularInformationArrayList.get(position);
        String extraCount="Extra-Curricular Activities "+ (position+1);
        extraView.setText(extraCount);
        extraEditView.setText(currentExtraInformation.getExtraCurricular());


        return listItemView;
    }

}
