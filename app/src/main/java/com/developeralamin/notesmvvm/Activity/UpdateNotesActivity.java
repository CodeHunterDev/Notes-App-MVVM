package com.developeralamin.notesmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.developeralamin.notesmvvm.Model.Notes;
import com.developeralamin.notesmvvm.R;
import com.developeralamin.notesmvvm.ViewModel.NoteViewModel;
import com.developeralamin.notesmvvm.databinding.ActivityUpdateNotesBinding;


import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {


    ActivityUpdateNotesBinding binding;
    String Colors = "1";
    String  stitile, ssubtitle, sprioriy, snotes;
    int iid;
    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        iid = getIntent().getIntExtra("id",0);
        stitile = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        sprioriy = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("notesdetails");

        PrioritySetter(sprioriy);

        binding.updateTitle.setText(stitile);
        binding.updateSubtitle.setText(ssubtitle);
        binding.Priority.setText(sprioriy);
        binding.updateDeatils.setText(snotes);

        binding.yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrioritySetter("1");


                Colors = "1";

            }
        });


        binding.red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrioritySetter("2");

                Colors = "2";

            }
        });


        binding.green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrioritySetter("3");


                Colors = "3";

            }
        });

        binding.updateNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.updateTitle.getText().toString();
                String subtitle = binding.updateSubtitle.getText().toString();
                String notes = binding.updateDeatils.getText().toString();

                UpdateNote(title, subtitle, notes);


            }
        });


    }

    private void UpdateNote(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMM d, yyy", date.getTime());

        Notes updatenote = new Notes();
        updatenote.id = iid;
        updatenote.notestitle = title;
        updatenote.notessubtitle = subtitle;
        updatenote.Priority = Colors;
        updatenote.date = sequence.toString();
        updatenote.notes = notes;

        noteViewModel.updateNotes(updatenote);

        Toast.makeText(getApplicationContext(), "Updated Successful", Toast.LENGTH_SHORT).show();
        finish();

    }

    void PrioritySetter(String prioriy){
        if (prioriy.equals("1")){
            binding.yellow.setImageResource(R.drawable.ic_baseline_done_24);
            binding.green.setImageResource(0);
            binding.red.setImageResource(0);
        }else if (prioriy.equals("2")){
            binding.red.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellow.setImageResource(0);
            binding.green.setImageResource(0);
        }else{
            binding.green.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellow.setImageResource(0);
            binding.red.setImageResource(0);
        }

    }
}