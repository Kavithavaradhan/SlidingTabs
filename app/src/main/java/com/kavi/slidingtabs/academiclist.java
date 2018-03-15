package com.kavi.slidingtabs;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kavi on 9/3/18.
 */

public class academiclist extends ArrayAdapter<academicupdate> {

    private Activity context;
    private List<academicupdate> academicList;

    public academiclist(Activity context, List<academicupdate> academicList){
        super(context, R.layout.academiclist, academicList);
        this.context = context;
        this.academicList = academicList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        final View listviewitem = inflater.inflate(R.layout.academiclist, null, true);

        TextView textarticle = (TextView) listviewitem.findViewById(R.id.acaline);

        academicupdate acad = academicList.get(position);

        textarticle.setText(acad.getAcaline());

        final ImageButton likebtn = (ImageButton) listviewitem.findViewById(R.id.likebtn);

        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return listviewitem;
    }
}
