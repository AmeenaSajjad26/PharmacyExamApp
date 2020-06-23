//Ameena
//Bonaface
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

public class AboutUsDialog extends DialogFragment {


    private static final String ARG_Choice = "MyChoice";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.about_dialog, null);

        final TextView tvAbout = (TextView) v.findViewById(R.id.tvAbout);

        String us
                = "\nPharmaTech v1.0\n"
                + "\nThis has been made by the students of Bunker Hill Community College as a final "
                + "project for CIT-243 (Android Development for Java Programmers) course."
                + "Fall 2016"
                +"\n\nGroup Members \n\t\t\t - Ruchelly P. Almeida \n" +
                "\t\t\t - Oleksii Butakov \n" +
                "\t\t\t - Edgard Zafack \n" +
                "\t\t\t - Evan Y. Liu \n" +
                "\t\t\t **************************************************************************************\n"+
                " \n This project was then editted and improved by\n"+
                " \t\t\t - Ameena Sajjad \n" +
                " \t\t\t - Bonface \n" +
                "\t\t\t  - EVE \n" ;

        tvAbout.setText(us);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
    }


}

