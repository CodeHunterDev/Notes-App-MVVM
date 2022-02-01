package com.developeralamin.notesmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.developeralamin.notesmvvm.Dao.NotesDAO;
import com.developeralamin.notesmvvm.Database.NotesDatabase;
import com.developeralamin.notesmvvm.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDAO notesDAO;
    public LiveData<List<Notes>> getallNotes;


    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getNotesDatabase(application);
        notesDAO = database.notesDAO();
        getallNotes = notesDAO.getAllNotes();

    }

    public void insertNotes(Notes notes) {
        notesDAO.insertNots(notes);
    }

    public void updateNotes(Notes notes) {
        notesDAO.updateNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDAO.deleteNotes(id);
    }

}
