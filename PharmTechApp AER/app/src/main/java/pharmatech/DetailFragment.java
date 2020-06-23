//Ameena
//Bonface
//EVE

package pharmatech;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.pharmtechapp.R;

/**
 * Displays details about chosen drug in Review Activity
 */
public class DetailFragment extends Fragment {

    private static final String ARG_MEDICINE_ID = "arg: drug id";

    private Drug mDrug;
    private String medicineId;
    private TextView medicineNameTextView;
    private Button btnTakeNote;

    public DetailFragment() {
    }

    // Create a new fragment based on the id of medicine that is its generic name
    public static DetailFragment newInstance(String medicineID) {
        Bundle args = new Bundle();  // a new bundle to hold arguments
        args.putString(ARG_MEDICINE_ID, medicineID);  // put a medicine id as an argument for the fragment that will be created
        DetailFragment fragment = new DetailFragment();  // create a new detail fragment
        fragment.setArguments(args);  // put arguments to a fragment
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        // check if a fragment contains any arguments
        // if so get a medicine id and get a medicine from a medicine lab
        if (args == null) {
            mDrug = new Drug();
        }
        else {
            medicineId = args.getString(ARG_MEDICINE_ID);
            //return a medecine base on the genric name that was clicked
            mDrug = DrugLab.get(getActivity()).getMedicine(medicineId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        final Bundle args = getArguments();

        // link a variable to a widget
        medicineNameTextView = (TextView) view.findViewById(R.id.medicine_name_textview);

       // set a drug name to a text view
        NoteLab labNote=NoteLab.get(getActivity());
        medicineNameTextView.setText(mDrug.description(labNote.getContentNote(labNote.getNotes(),mDrug.getId_med())));
        btnTakeNote =(Button)view.findViewById(R.id.noteButton);
        btnTakeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getFragmentManager();
                if(args != null) {
                    SaveNote dialog = SaveNote.newInstance(mDrug.getId_med());
                    dialog.show(manager, "asdfa");
                }
            }

        });
        return view;
    }
}