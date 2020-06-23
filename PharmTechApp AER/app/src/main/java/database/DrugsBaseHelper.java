//Ameena
//Bonface
//Eve

package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import pharmatech.Note;

// this class helps to create the class if it doest exist
public class DrugsBaseHelper extends SQLiteOpenHelper {

    String DB_Path = null;
    private static String DB_Name = "Drugsv4.sqlite";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DrugsBaseHelper(Context context) {
        super(context, DB_Name, null, 1);
        this.myContext = context;

        this.DB_Path = "/data/data/" + this.myContext.getPackageName() + "/databases/";

        Log.e("path 1", DB_Path);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() throws IOException {
        boolean dbexist = checkDatabase();

        if (dbexist) {

        } else {
            this.getReadableDatabase();
            try {
               copyDataBase();

            } catch (IOException e) {
                throw new Error("Error Copying database");
            } finally {
                this.close();
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = this.DB_Path + this.DB_Name;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        if (checkDB != null)
            checkDB.close();
        return checkDB != null ? true : false;
    }

    public void db_delete() {
        File file = new File(this.DB_Path + this.DB_Name);
        if (file.exists()) {
            file.delete();
            System.out.println("delete database file.");
        }
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    private void copyDataBase() throws IOException {
        //db_delete();
        InputStream myInput = this.myContext.getAssets().open(this.DB_Name);
        String outFileName = this.DB_Path + this.DB_Name;
        OutputStream myOutPut = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutPut.write(buffer, 0, length);
        }

        myOutPut.flush();
        myOutPut.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = this.DB_Path + this.DB_Name;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }


    public Cursor query(String table, String[] columns,
                        String selection, String[] slectionArgs, String groupBy, String having, String orderBy, String limit) {
        String query = "SELECT * FROM Drug";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);

        return db.rawQuery(query, null);
    }

    /**
     * Querying database according to applied filters
     * @param having Filter string that contains all needed for user study topics
     */
    public Cursor queryDrugs(String table, String[] columns,
                             String selection, String[] slectionArgs, String groupBy, String having, String orderBy, String limit) {
        String query;
        SQLiteDatabase db;
        if (!having.equals("All"))
            query = "SELECT * FROM Drug where Study_Topic=\'" + having + "\'";
        else
            query = "SELECT * FROM Drug";
        db = SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);
        return db.rawQuery(query, null);
    }

    public Cursor queryNote(String table, String[] columns,
                            String selection, String[] slectionArgs, String groupBy, String having, String orderBy, String limit) {
        String query;
        SQLiteDatabase db;
        query = "SELECT * FROM Note2";
        db = SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);
        return db.rawQuery(query, null);
    }
    public Cursor queryNote2(String table, String[] columns,
                            String selection, String[] slectionArgs, String groupBy, String having, String orderBy, String limit) {
        String query;
        SQLiteDatabase db;
        query = "SELECT * FROM Note2 where id=\'" + having + "\'";
        db = SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);
        return db.rawQuery(query, null);
    }
     public void createNoteTable(){
         String sql="CREATE TABLE IF NOT EXISTS `Note2` (\n" +
                 "\t`id`\tINTEGER NOT NULL UNIQUE,\n" +
                 "\t`about`\tSTRING(50),\n" +
                 "\t`date`\tSTRING(30),\n" +
                 "\t`content`\tTEXT,\n" +
                 "\tPRIMARY KEY(`id`)\n" +
                 ");";
         SQLiteDatabase db =SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);
         db.execSQL(sql);

     }
    public void saveNote(Note note) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();

        SQLiteDatabase db =SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues contentValues = new ContentValues();
        contentValues.put(PharmTechDB.NoteTable.ColsNote.id, Integer.parseInt(note.getIdNote()));
        contentValues.put(PharmTechDB.NoteTable.ColsNote.aboutCol, note.getAboutNote());
        contentValues.put(PharmTechDB.NoteTable.ColsNote.dateCol, dateFormat.format(date));
        contentValues.put(PharmTechDB.NoteTable.ColsNote.contentCol, note.getContentNote());

        db.insert("Note2", null, contentValues);
    }
    public void updateNote(Note note) {
        String id=note.getIdNote();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        SQLiteDatabase db =SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues contentValues = new ContentValues();
        contentValues.put(PharmTechDB.NoteTable.ColsNote.id, note.getIdNote());
        contentValues.put(PharmTechDB.NoteTable.ColsNote.aboutCol, note.getAboutNote());
        contentValues.put(PharmTechDB.NoteTable.ColsNote.dateCol, dateFormat.format(date));
        contentValues.put(PharmTechDB.NoteTable.ColsNote.contentCol, note.getContentNote());
         //db.delete("Note2",null,null);
        db.update("Note2",contentValues, PharmTechDB.NoteTable.ColsNote.id+"=?",new String[] {id});
    }
    public void deleteNote(String id) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.DB_Path + this.DB_Name, null, SQLiteDatabase.OPEN_READWRITE);

        db.delete("Note2",PharmTechDB.NoteTable.ColsNote.id+"=?",new String[] {id});
    }
}
