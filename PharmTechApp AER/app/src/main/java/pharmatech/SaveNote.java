//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.android.pharmtechapp.R;

/**
 * Saves Notes user makes
 */
public class SaveNote extends DialogFragment {

    NoteLab mNoteLab;
    private static final String ARG_MEDICINE_ID = "arg: drug id";
    private  static String idDrug;
    private Drug mDrug;
    private DetailFragment fragment;
    private Note newNote,myNote;

    public static SaveNote  newInstance(String medicineID) {
        Bundle args = new Bundle();  // a new bundle to hold arguments
        args.putString(ARG_MEDICINE_ID, medicineID);
        SaveNote myNote= new SaveNote();
        idDrug=medicineID;
        return myNote;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText aboutText;
        final EditText contentText;
        final Note note;
        Bundle args = getArguments();

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.take_note, null);
        contentText = (EditText) v.findViewById(R.id.contentId);
        mNoteLab = NoteLab.get(getActivity());
        note=mNoteLab.getSpecificNote(idDrug);
        if(note!=null)
        {
            contentText.setText(note.getContentNote());
            newNote=new Note();

        }

        if(note==null){
            myNote=new Note();
        }

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.note)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if(newNote!=null){
                            newNote.setContentNote(contentText.getText().toString());
                            newNote.setIdNote(idDrug);
                            mNoteLab.saveAnoterNote(newNote);
                        }
                        else{
                            myNote.setContentNote(contentText.getText().toString());
                            myNote.setIdNote(idDrug);

                            mNoteLab.saveNote(myNote);
                        }

                        if(newNote!=null){

                            mDrug = DrugLab.get(getActivity()).getAnotherMedicine(newNote.getIdNote());
                            fragment = DetailFragment.newInstance(mDrug.getGenericName());
                            FragmentManager fm = getFragmentManager();
                            fm.beginTransaction().replace(R.id.middle_fragment_container, fragment).commit();
                        }
                        if(myNote!=null){

                            mDrug = DrugLab.get(getActivity()).getAnotherMedicine(myNote.getIdNote());
                            fragment = DetailFragment.newInstance(mDrug.getGenericName());
                            FragmentManager fm = getFragmentManager();
                            fm.beginTransaction().replace(R.id.middle_fragment_container, fragment).commit();
                        }
                    }
                }).create();
    }
}

