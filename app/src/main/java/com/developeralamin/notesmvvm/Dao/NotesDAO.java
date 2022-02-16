package com.developeralamin.notesmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.developeralamin.notesmvvm.Model.Notes;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    void insertNots(Notes notes);

    @Query("SELECT * FROM Notes_Database ORDER BY Notes_Priority DESC")
    LiveData<List<Notes>> highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY Notes_Priority ASC")
    LiveData<List<Notes>> lowToHigh();

    @Update
    void updateNotes(Notes notes);

    @Query("DELETE FROM notes_database WHERE id=:id")
    void deleteNotes(int id);

    @Query("SELECT * FROM notes_database")
    LiveData<List<Notes>> getAllNotes();

}
