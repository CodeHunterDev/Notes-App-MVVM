package com.developeralamin.notesmvvm.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.developeralamin.notesmvvm.Dao.NotesDAO;
import com.developeralamin.notesmvvm.Model.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDAO notesDAO();

    public static NotesDatabase notesDatabase;

    public static NotesDatabase getNotesDatabase(Context context) {
        if (notesDatabase == null) {
            notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase.class,
                    "Notes_Database"

            ).allowMainThreadQueries().build();
        }
        return notesDatabase;
    }
}
