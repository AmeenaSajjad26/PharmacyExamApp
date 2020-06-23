//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.pharmtechapp.R;

import java.util.List;


// --------------------- List of allNotes taken by user ---------------------
public class MyNotes extends DialogFragment {
    RecyclerView noteListRecyclerView;
    //private NoteAdapter noteAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.recycle_view_note, null);

        noteListRecyclerView = (RecyclerView) v;
        noteListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return new AlertDialog.Builder(getActivity())
                .setView(noteListRecyclerView)
                .setTitle("Review Note")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
    private void updateUI(){
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();
        //noteAdapter = new MyNotes.NoteAdapter(notes);
        //noteListRecyclerView.setAdapter(noteAdapter);

    }
    // =====================  ViewHolder =================================//

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView aboutNot;
        private TextView dateNote;
        private ImageButton deleteNote;
        private Note note;
        private Drug mDrug;
        private DetailFragment fragment;
        public NoteHolder(View itemView) {
            super(itemView);
            //this is make sure a action happen when clicking on a view
            itemView.setOnClickListener(this);  //set onclick listener to the override method

        }

        public Note getNote() {
            return note;
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public void bindNote(final Note note) {
            // get a medicine from an argument and set a medicine id and name to text views
            this.note = note;

            aboutNot.setText(this.note.getAboutNote());
            dateNote.setText(this.note.getDateNote());

            deleteNote.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    NoteLab noteLab = NoteLab.get(getActivity());
                    noteLab.deleteNote(note.getIdNote());
                    updateUI();
                }
            });
        }

        @Override
        public void onClick(View v) {
            final Note thisNote;
            NoteLab noteLab = NoteLab.get(getActivity().getApplicationContext());
            updateUI();
            FragmentManager fm = getFragmentManager();
        }
    }
}
