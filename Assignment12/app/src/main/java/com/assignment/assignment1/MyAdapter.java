package com.assignment.assignment1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<MyList> {



    public MyAdapter(Context context, int resource,  List<MyList> objects) {
        super(context,  R.layout.custom_row, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = LayoutInflater.from(getContext());
        View myView = inflater.inflate(R.layout.custom_row, parent,false);

        TextView tx1 = (TextView) myView.findViewById(R.id.textView3);
        TextView tx2 = (TextView) myView.findViewById(R.id.textView2);

        tx1.setText(getItem(position).getTitle());
        tx2.setText(getItem(position).getDescription());

        final CheckBox box = (CheckBox) myView.findViewById(R.id.checkBox);

        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (box.isChecked()){
                    remove(getItem(position));
                }

            }
        });



        return myView;
    }
}
