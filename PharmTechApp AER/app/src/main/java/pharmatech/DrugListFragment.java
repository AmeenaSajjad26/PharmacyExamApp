//Ameena
//Bonaface
//Eve

package pharmatech;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.pharmtechapp.R;

import java.util.List;

/**
 * List of drugs in the Review Activity
 */
public class DrugListFragment extends Fragment {

    private DrugAdapter mDrugAdapter;
    RecyclerView drugListRecyclerView;

    public DrugListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drug_list, container, false);

        // set up  recycler view
        // set layout manager to linear layout manager
        drugListRecyclerView = (RecyclerView) view;
        drugListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // update recycler view
        updateUI();

        return view;
    }

    private void updateUI() {

        // get a medicine lab and pass it to an adapter
        // set an adapter to recycler view
        DrugLab medLab = DrugLab.get(getActivity());

        List<Drug> drugs = medLab.getDrugs();

        mDrugAdapter = new DrugAdapter(drugs);
        drugListRecyclerView.setAdapter(mDrugAdapter);
    }


    /**
     * Used for setting up column's width in the Review Activity
     * @return  returns device's screen width of given activity in pixels
    */
    public static int containerLength(Activity activity) {

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.widthPixels;
    }


    // =====================  ViewHolder =================================//

    private class DrugHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView idTextView;
        private TextView nameTextView;
        private TextView brandnameTextView;
        private Drug mDrug;
        private int columnCount = 2;

        public DrugHolder(View itemView) {
            super(itemView);
            //this is make sure a action happen when clicking on a view
            itemView.setOnClickListener(this);  //set onclick listener to the override method


            nameTextView = (TextView) itemView.findViewById(R.id.medicine_name);
            brandnameTextView = (TextView) itemView.findViewById(R.id.brand_name);

            // Getting current activity's screen width in pixels and divide it
            // by number of columns in the recycle view list
            int columnWidth = containerLength(getActivity())/columnCount;

            // Setting up textView width
            brandnameTextView.setWidth(columnWidth);
            nameTextView.setWidth(columnWidth);

        }

        public void bindDrug(Drug drug) {
            // get a medicine from an argument and set a medicine id and name to text views
            this.mDrug = drug;

            nameTextView.setText(this.mDrug.getGenericName());
            brandnameTextView.setText(this.mDrug.getBrandName());
        }

        @Override
        public void onClick(View v) {
            // show toast notifying a view holder is clicked


            // replace the bottom container by a detail fragment with corresponding medicine
            FragmentManager fm = getFragmentManager();
            //new fragment is created based on what generic name is clicked
            DetailFragment fragment = DetailFragment.newInstance(this.mDrug.getGenericName());

            fm.beginTransaction().replace(R.id.middle_fragment_container, fragment).commit();
        }
    }

    // =====================  Adapter =================================//

    private class DrugAdapter extends RecyclerView.Adapter<DrugHolder> {

        private List<Drug> mDrugs;

        public DrugAdapter(List<Drug> drugs) {
            this.mDrugs = drugs;
        }

        @Override
        public DrugHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_drug, parent, false);
           return new DrugHolder(view);
        }

        @Override
        public void onBindViewHolder(DrugHolder holder, int position) {
            Drug drug = mDrugs.get(position);
            // bind a drug to a view holder
            holder.bindDrug(drug);
        }

        @Override
        public int getItemCount() {
            return mDrugs.size();
        }
    }
}
