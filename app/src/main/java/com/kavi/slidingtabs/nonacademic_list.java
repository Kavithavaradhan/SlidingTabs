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

public class nonacademic_list extends ArrayAdapter<nonacademicupdate> {

    private Activity context;
    private List<nonacademicupdate> InfoList;

    public nonacademic_list(Activity context, List<nonacademicupdate> InfoList){
        super(context, R.layout.nonacademic_list, InfoList);
        this.context = context;
        this.InfoList= InfoList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        final View listviewitem = inflater.inflate(R.layout.nonacademic_list, null, true);

        TextView textInfo = (TextView) listviewitem.findViewById(R.id.nonacline);

        nonacademicupdate non = InfoList.get(position);

        textInfo.setText(non.getNonacline());

        final ImageButton likebtn = (ImageButton) listviewitem.findViewById(R.id.likebtn);

        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return listviewitem;
    }
}
