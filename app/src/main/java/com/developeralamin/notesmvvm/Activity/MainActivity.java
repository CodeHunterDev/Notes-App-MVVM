package com.developeralamin.notesmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developeralamin.notesmvvm.Adapter.NotesAdapter;
import com.developeralamin.notesmvvm.R;
import com.developeralamin.notesmvvm.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button;
    NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.newNoteBtn);
        recyclerView = findViewById(R.id.recyclerview);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsetNoteActivity.class));
            }
        });

        noteViewModel.getallNotes.observe(this, notes -> {

            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            adapter = new NotesAdapter(MainActivity.this, notes);
            recyclerView.setAdapter(adapter);

        });
    }
}