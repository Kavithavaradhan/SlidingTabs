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

public class article_list extends ArrayAdapter<articleupdate> {

    private Activity context;
    private List<articleupdate> articleList;

    public article_list(Activity context, List<articleupdate> articleList){
        super(context, R.layout.article_list, articleList);
        this.context = context;
        this.articleList = articleList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        final View listviewitem = inflater.inflate(R.layout.article_list, null, true);

        TextView textarticle = (TextView) listviewitem.findViewById(R.id.articleline);

        articleupdate art = articleList.get(position);

        textarticle.setText(art.getArticleline());

        final ImageButton likebtn = (ImageButton) listviewitem.findViewById(R.id.likebtn);

        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return listviewitem;
    }
}
