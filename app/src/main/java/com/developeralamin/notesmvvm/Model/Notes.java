package com.developeralamin.notesmvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Notes_Title")
    public String notestitle;

    @ColumnInfo(name = "Notes_Subtitle")
    public String notessubtitle;

    @ColumnInfo(name = "Notes")
    public String notes;

    @ColumnInfo(name = "Notes_Data")
    public String date;

    @ColumnInfo(name = "Notes_Priority")
    public String Priority;


}
