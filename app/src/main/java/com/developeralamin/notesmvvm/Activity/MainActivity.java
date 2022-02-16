package com.developeralamin.notesmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.developeralamin.notesmvvm.Adapter.NotesAdapter;
import com.developeralamin.notesmvvm.Model.Notes;
import com.developeralamin.notesmvvm.R;
import com.developeralamin.notesmvvm.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button;
    NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    TextView nofilter, highfilter, lowfilter;
    List<Notes> filternotesalllist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.newNoteBtn);
        recyclerView = findViewById(R.id.recyclerview);

        nofilter = findViewById(R.id.nofilter);
        highfilter = findViewById(R.id.highfilter);
        lowfilter = findViewById(R.id.lowfilter);

        nofilter.setBackgroundResource(R.drawable.filter_selecter_shape);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            highfilter.setBackgroundResource(R.drawable.filter_un_shape);
            lowfilter.setBackgroundResource(R.drawable.filter_un_shape);
            nofilter.setBackgroundResource(R.drawable.filter_selecter_shape);
        });

        highfilter.setOnClickListener(v -> {
            loadData(1);
            highfilter.setBackgroundResource(R.drawable.filter_selecter_shape);
            lowfilter.setBackgroundResource(R.drawable.filter_un_shape);
            nofilter.setBackgroundResource(R.drawable.filter_un_shape);
        });

        lowfilter.setOnClickListener(v -> {
            loadData(2);
            highfilter.setBackgroundResource(R.drawable.filter_un_shape);
            lowfilter.setBackgroundResource(R.drawable.filter_selecter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_un_shape);
        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsetNoteActivity.class));
            }
        });

        noteViewModel.getallNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
            }
        });
    }

    private void loadData(int i) {
        if (i==0){
            noteViewModel.getallNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }else if (i==1){
            noteViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }else if (i==2){
            noteViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });

        }
    }

    public void setAdapter(List<Notes> notes) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this, notes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here.....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> FilterNames = new ArrayList<>();

        for (Notes notes : this.filternotesalllist){
            if (notes.notestitle.contains(newText) || notes.notessubtitle.contains(newText))
            {
                FilterNames.add(notes);
            }
        }

        this.adapter.searchNotes(FilterNames);
    }
}