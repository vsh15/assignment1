package com.assignment.assignment1;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static android.system.Os.write;

public class MainActivity extends Activity {


    private EditText editText1;
    private EditText editText2;
    private ListView myView;
    private Button myButton;
    private ArrayList<MyList> myList;
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        myView = (ListView) findViewById(R.id.listView);
        myButton = (Button) findViewById(R.id.button);
        myList = new ArrayList<MyList>();
        myAdapter = new MyAdapter(MainActivity.this, R.layout.custom_row, myList);


        File file = getBaseContext().getFileStreamPath("my_file_new.txt");
        if (file.exists()) {

            try {
                FileInputStream myFile = openFileInput("my_file_new.txt");
                Scanner scanner = new Scanner(myFile);

                while (scanner.hasNextLine()) {

                    String myLine = scanner.nextLine();
                    String[] items = myLine.split("\t");
                    MyList myItem = new MyList();
                    myItem.setTitle(items[0]);
                    myItem.setDescription(items[1]);
                    myList.add(myItem);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        myView.setAdapter(myAdapter);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItem();


                editText1.setText("");
                editText2.setText("");

            }
        });

        myView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myList.remove(position);
                myView.setAdapter(myAdapter);
                return false;
            }
        });



    }

    public void addItem() {
        String title = editText1.getText().toString();
        String description = editText2.getText().toString();

        if (title.equals("") || description.equals("")) {
            // do nothing

        } else {

            MyList item = new MyList();
            item.setTitle(title);
            item.setDescription(description);

            myList.add(item);

            myView.setAdapter(myAdapter);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String title = editText1.getText().toString();
        String description = editText2.getText().toString();

        outState.putSerializable("myList", myList);
        outState.putString("tx1", title);
        outState.putString("tx2", description);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        myList = (ArrayList<MyList>) savedInstanceState.getSerializable("myList");

        String title = savedInstanceState.getString("tx1");
        editText1.setText(title);

        String description = savedInstanceState.getString("tx2");
        editText2.setText(description);

        myAdapter = new MyAdapter(MainActivity.this, R.layout.custom_row, myList);

        myView.setAdapter(myAdapter);




    }

    @Override
    protected void onStop() {
        super.onStop();

        try {


            FileOutputStream fileName = openFileOutput("my_file_new.txt", MODE_PRIVATE);
            OutputStreamWriter myWriter = new OutputStreamWriter(fileName);


            for(MyList listLocal: myList) {

                myWriter.write(listLocal.getTitle() + "\t" + listLocal.getDescription() + "\n");

            }

            myWriter.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


    }


}
