//Ameena
//Bonface
//Eve

package pharmatech;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DrugsBaseHelper;

public class NoteLab {
    private DrugsBaseHelper myDBhelp;
    private SQLiteDatabase mDatabase;
    private static NoteLab sNoteLab;
    private List<Note> mNotes;
    private Note myNote;
    Cursor c=null;
    private NoteLab(Context thisContext){
        thisContext = thisContext.getApplicationContext();
        myDBhelp= new DrugsBaseHelper(thisContext);
        try{
            myDBhelp.createDataBase();
        }
        catch (IOException e){
            throw new Error("Unable to create the DB");
        }
        try {
            myDBhelp.openDataBase();
        }
        catch (SQLException sql){
            throw  sql;
        }
        //this statement creates the table Note2 in DB
       myDBhelp.createNoteTable();

    }
    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }
    public void deleteNote(String id){
        myDBhelp.deleteNote(id);



    }
    public List<Note> getNotes() {
        mNotes = new ArrayList<>();
            c = myDBhelp.queryNote(null, null, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    myNote = new Note(c.getString(0), c.getString(1), c.getString(2), c.getString(3));

                     mNotes.add(myNote);

                } while (c.moveToNext());
            }

        c.close();
        return mNotes;
    }
    public Note getSpecificNote(String id) {

        c = myDBhelp.queryNote2(null, null, null, null, null, id, null, null);
        if (c.moveToFirst()) {

                myNote = new Note(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                return myNote;

        }

        c.close();
        return null;
    }
    public void saveAnoterNote(Note note){
        myDBhelp.updateNote(note);
    }

    public void saveNote(Note note){
        myDBhelp.saveNote(note);

    }
    public Note getNote(String id){
        for(Note myNote: mNotes) {
            if (myNote.getIdNote().equals(id))
                return myNote;
        }
        return null;
    }
    public String getContentNote(List<Note>allNote, String idnote ){
        for(Note myNote: allNote) {
            if (myNote.getIdNote().equals(idnote))
                return myNote.getContentNote();
        }
        return null;
    }
}
