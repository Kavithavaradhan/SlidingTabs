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

public class idealist extends ArrayAdapter<idea> {

    private Activity context;
    private List<idea> ideaList;

    public idealist(Activity context, List<idea> ideaList){
        super(context,R.layout.idea_list,ideaList);
        this.context = context;
        this.ideaList = ideaList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        final View listviewitem = inflater.inflate(R.layout.idea_list, null, true);

        TextView textViewidea = (TextView) listviewitem.findViewById(R.id.textidealine);

        idea idea = ideaList.get(position);

        textViewidea.setText(idea.getIdealine());

        final ImageButton likebtn = (ImageButton) listviewitem.findViewById(R.id.likebtn);

        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return listviewitem;
    }
}
