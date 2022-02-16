package com.developeralamin.notesmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.developeralamin.notesmvvm.Model.Notes;
import com.developeralamin.notesmvvm.R;
import com.developeralamin.notesmvvm.ViewModel.NoteViewModel;
import com.developeralamin.notesmvvm.databinding.ActivityInsetNoteBinding;


import java.util.Date;

public class InsetNoteActivity extends AppCompatActivity {

    ActivityInsetNoteBinding binding;
    String title, subtitle, notes;
    NoteViewModel noteViewModel;
    String Colors = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsetNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        binding.yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.yellow.setImageResource(R.drawable.ic_baseline_done_24);
                binding.green.setImageResource(0);
                binding.red.setImageResource(0);

                Colors = "1";

            }
        });


        binding.red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.red.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellow.setImageResource(0);
                binding.green.setImageResource(0);

                Colors = "2";

            }
        });


        binding.green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.green.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellow.setImageResource(0);
                binding.red.setImageResource(0);

                Colors = "3";

            }
        });


        binding.doneNoteBtn.setOnClickListener(v -> {
            title = binding.noteTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesDeatils.getText().toString();

           if (inputValidity()){
               CreateNote(title, subtitle, notes);
           }

        });
    }

    private void CreateNote(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMM d, yyy", date.getTime());

        Notes notes1 = new Notes();
        notes1.notestitle = title;
        notes1.notessubtitle = subtitle;
        notes1.Priority = Colors;
        notes1.date = sequence.toString();
        notes1.notes = notes;

        noteViewModel.insertNote(notes1);

        Toast.makeText(getApplicationContext(), "Note Add Successful", Toast.LENGTH_SHORT).show();

        finish();
    }
    boolean inputValidity(){
        if (binding.noteTitle.getText().length() == 0){
            binding.noteTitle.setError("Title cant be empty");
            return false;
        }
        if (binding.notesSubtitle.getText().length() == 0){
            binding.notesSubtitle.setError("SubTitle cant be empty");
            return false;
        }
        if (binding.notesDeatils.getText().length() == 0){
            binding.notesDeatils.setError("Details cant be empty");
            return false;
        }
        return true;
    }
}