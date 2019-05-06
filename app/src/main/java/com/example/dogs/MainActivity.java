package com.example.dogs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SEARCH_QTY = 20;
    private RecyclerView rv;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        String breed = ((TextView) findViewById(R.id.etBreed)).getText().toString();
        this.getDogs(breed, SEARCH_QTY);
    }

    public void getDogs(String breed, int qty) {
        DogService.getDogs(breed, qty).observe(
                this,
                (List<Dog> dogs) -> {
                    if (dogs == null) {
                        Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    rv.setAdapter(new RVAdapter(dogs));
                    for (Dog dog : dogs) {
                        Log.i("Dogs", dog.toString());
                    }
                }
        );
    }

    public void onSearchClick(View view) {
        String breed = ((TextView) findViewById(R.id.etBreed)).getText().toString();
        if (breed.length() <= 0) {
            Toast.makeText(this, "You need to input a dog breed", Toast.LENGTH_SHORT).show();
            return;
        }
        this.getDogs(breed, SEARCH_QTY);
    }

    public void onEtClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter dog breed");

        // Set up the input
        final EditText input = new EditText(this);
        final EditText breed = (EditText) findViewById(R.id.etBreed);
        input.setText(breed.getText().toString());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                breed.setText(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
