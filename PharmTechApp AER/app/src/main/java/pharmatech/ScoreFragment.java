//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.android.pharmtechapp.R;

/**
 * Fragment dialog that returns user's score at the end of each quiz.
 * When "Ok" button is pressed - current activity is cloesed.
 */
public class ScoreFragment extends DialogFragment {


    private static final String ARG_Choice = "MyChoice";
    public static String name= null;

    public void setName(String string){
        name = string;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.score_dialog, null);

        final TextView tvAbout = (TextView) v.findViewById(R.id.tvAbout);
        tvAbout.setText(name);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .create();
    }
}

