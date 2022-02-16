package com.developeralamin.notesmvvm.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developeralamin.notesmvvm.Activity.MainActivity;
import com.developeralamin.notesmvvm.Activity.UpdateNotesActivity;
import com.developeralamin.notesmvvm.Model.Notes;
import com.developeralamin.notesmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {


    MainActivity mainActivity;
    List<Notes> notesList;
    List<Notes> allNotesitem;


    public NotesAdapter(MainActivity mainActivity, List<Notes> notesList) {
        this.mainActivity = mainActivity;
        this.notesList = notesList;
        allNotesitem = new ArrayList<>(notesList);
    }

    public void searchNotes(List<Notes> filterredName) {
        this.notesList = filterredName;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Notes note = notesList.get(position);

        switch (note.Priority) {
            case "1":
                holder.Priority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "2":
                holder.Priority.setBackgroundResource(R.drawable.red_shape);
                break;
            case "3":
                holder.Priority.setBackgroundResource(R.drawable.green_shape);
                break;
        }
        holder.title.setText(note.notestitle);
        holder.subtitle.setText(note.notessubtitle);
        holder.notesdetails.setText(note.notes);
        holder.date.setText(note.date);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notestitle);
            intent.putExtra("subtitle", note.notessubtitle);
            intent.putExtra("priority", note.Priority);
            intent.putExtra("notesdetails", note.notes);

            mainActivity.startActivity(intent);


        });


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesdetails, date;
        View Priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            subtitle = itemView.findViewById(R.id.item_subtitle);
            notesdetails = itemView.findViewById(R.id.notesdetails);
            date = itemView.findViewById(R.id.item_date);
            Priority = itemView.findViewById(R.id.Priority);
        }
    }
}
