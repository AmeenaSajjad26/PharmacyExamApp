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
import android.widget.CheckBox;

import com.example.android.pharmtechapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is our fragment dialog for study topics filter-menu.
 * Upon checking necessary check boxes and pressing "Ok" button
 * user setting up a filter for querying our database. This fragment used
 * upon start of PracticeMenuActivity.
  */
public class FilterFragmentDialog extends DialogFragment {

    List<String> myChoices = new ArrayList<>();
    DrugLab drugLab;
    private static final String ARG_Choice = "MyChoice";

    public static FilterFragmentDialog newInstance(String choice) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_Choice, choice);
        FilterFragmentDialog fragment = new FilterFragmentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.study_topics_fragment, null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectNa(v);
                    }
                })
                .create();
    }

    /**
     * Checks each checkbox and setting up filter for DrugLab class
     * accordingly.
     * @param v Current activity's view
     */
    public void selectNa(View v) {

        String c = "";
        drugLab = DrugLab.get(getActivity());

        final CheckBox cbCv = (CheckBox) v.findViewById(R.id.cv);
        final CheckBox cbUrine = (CheckBox) v.findViewById(R.id.urine);
        final CheckBox cbCns = (CheckBox) v.findViewById(R.id.cns);
        final CheckBox cbRespiratory = (CheckBox) v.findViewById(R.id.resp);
        final CheckBox cbDermat = (CheckBox) v.findViewById(R.id.derm);
        final CheckBox cbAnalgesic = (CheckBox) v.findViewById(R.id.analgesic);
        final CheckBox cbMusculo = (CheckBox) v.findViewById(R.id.muscul);
        final CheckBox cbInfective = (CheckBox) v.findViewById(R.id.infective);
        final CheckBox cbGi = (CheckBox) v.findViewById(R.id.gi);
        final CheckBox cbEye = (CheckBox) v.findViewById(R.id.eye);
        final CheckBox cbEndocrin = (CheckBox) v.findViewById(R.id.end);
        final CheckBox cbHeme = (CheckBox) v.findViewById(R.id.hm);
        final CheckBox cbAll = (CheckBox) v.findViewById(R.id.all);

        if (cbCv.isChecked())
            myChoices.add("CV");
        else
            myChoices.remove("CV");

        if (cbUrine.isChecked())
            myChoices.add("Urinary System");
        else
            myChoices.remove("Urinary System");

        if (cbCns.isChecked())
            myChoices.add("CNS");
        else
            myChoices.remove("CNS");

        if (cbRespiratory.isChecked())
            myChoices.add("Respiratory System");
        else
            myChoices.remove("Respiratory System");

        if (cbDermat.isChecked())
            myChoices.add("Derm");
        else
            myChoices.remove("Derm");

        if (cbAnalgesic.isChecked())
            myChoices.add("Analgesics");
        else
            myChoices.remove("Analgesics");

        if (cbMusculo.isChecked())
            myChoices.add("Musculo-Skeletal System");
        else
            myChoices.remove("Musculo-Skeletal System");

        if (cbInfective.isChecked())
            myChoices.add("Anti-Infectives");
        else
            myChoices.remove("Anti-Infectives");

        if (cbGi.isChecked())
            myChoices.add("GI System");

        else
            myChoices.remove("GI System");

        if (cbEye.isChecked())
            myChoices.add("Eyes");
        else
            myChoices.remove("Eyes");

        if (cbEndocrin.isChecked())
            myChoices.add("DM/Endocrine");
        else
            myChoices.remove("DM/Endocrine");

        if (cbHeme.isChecked())
            myChoices.add("Hematology");
        else
            myChoices.remove("Hematology");

        if (cbAll.isChecked())
            myChoices.add("All");
        else
            myChoices.remove("All");

        // Setting up filter
        drugLab.setFilter(myChoices);

        for (String s : myChoices) {
            c = c + s + " \n";
        }
    }
}
