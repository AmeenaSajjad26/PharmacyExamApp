//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.pharmtechapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashcardActivity extends Activity {

    final DrugLab mDrugLab = DrugLab.get(this); // DrugLab singleton called with "this" Activity for the argument

    // TextViews for the drug name and whether the name is the generic or brand
    private TextView mDrugInfo;
    private TextView mNameType;

    private Button mNextButton; // Increments to the next Drug in the List
    private Button mPreviousButton; // Increments to the previous Drug in the List

    //private Button mInfoUpButton;
    private Button mInfoDownButton;

    private List<Drug> mDrugList; // List for holding Drug objects. Defined later by using getDrugs() method from DrugLab
    final ArrayList<String> mInfoList = new ArrayList<>();
    final ArrayList<String> mTitleList = new ArrayList<>();

    private int drugIndex = 0;
    private int infoIndex = 0;
    // Integer for keeping track of current Drug in List

    private String // Basic Strings for displaying current type of name in TextView mNameType
            brandName = "Brand Name",
            genericName = "Generic Name",
            purpose = "Purpose",
            special = "Special",
            deaSchedule = "DEA Schedule",
            studyTopic = "Study Topic",
            category = "Category";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        mDrugList = mDrugLab.getDrugs(); // getDrugs() to define List<Drug> mDrugList

        mInfoList.clear();
        mTitleList.clear();

        Collections.addAll(
                mInfoList,
                mDrugList.get(drugIndex).getBrandName(),
                mDrugList.get(drugIndex).getGenericName(),
                mDrugList.get(drugIndex).getPurpose(),
                mDrugList.get(drugIndex).getSpecial(),
                mDrugList.get(drugIndex).getDEASch(),
                mDrugList.get(drugIndex).getStudyTopic(),
                mDrugList.get(drugIndex).getCategory());

        Collections.addAll(mTitleList,
                brandName,
                genericName,
                purpose,
                special,
                deaSchedule,
                studyTopic,
                category);

        mDrugInfo = (TextView)findViewById(R.id.flashcard_tv_drug_name);// finding TextViews in layout by reference ID
        mDrugInfo.setText(mInfoList.get(infoIndex));

        mNameType = (TextView)findViewById(R.id.flashcard_tv_name_type);
        mNameType.setText(mTitleList.get(infoIndex));

        mInfoDownButton = (Button) findViewById(R.id.flashcard_bt_info_down);
        mInfoDownButton.setText(mTitleList.get(infoIndex == 0 ? mTitleList.size() - 1 : infoIndex - 1));
        mInfoDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoIndex == 0){
                    infoIndex = mTitleList.size() - 1;
                } else if (infoIndex > 0) {
                    infoIndex--;
                }

                mDrugInfo.setText(mInfoList.get(infoIndex));
                mNameType.setText(mTitleList.get(infoIndex));
               // mInfoUpButton.setText(mTitleList.get(infoIndex == mTitleList.size() - 1 ? 0 : infoIndex + 1));
                mInfoDownButton.setText(mTitleList.get(infoIndex == 0 ? mTitleList.size() - 1 : infoIndex - 1));
            }
        });

        mNextButton = (Button) findViewById(R.id.flashcard_bt_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drugIndex == mDrugList.size() - 1)
                    drugIndex = 0;
                else if (drugIndex < mDrugList.size() - 1)
                    drugIndex++;

                updateInfoList();

                mDrugInfo.setText(mInfoList.get(infoIndex));
                mNameType.setText(mTitleList.get(infoIndex));
            }
        });

        mPreviousButton = (Button) findViewById(R.id.flashcard_bt_previous);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drugIndex == 0)
                    drugIndex = mDrugList.size() - 1;
                else if (drugIndex > 0)
                    drugIndex--;

                updateInfoList();

                mDrugInfo.setText(mInfoList.get(infoIndex));
                mNameType.setText(mTitleList.get(infoIndex));
            }
        });

    }

    public void updateInfoList(){
        mInfoList.clear();
        Collections.addAll(
                mInfoList,
                mDrugList.get(drugIndex).getBrandName(),
                mDrugList.get(drugIndex).getGenericName(),
                mDrugList.get(drugIndex).getPurpose(),
                mDrugList.get(drugIndex).getSpecial(),
                mDrugList.get(drugIndex).getDEASch(),
                mDrugList.get(drugIndex).getStudyTopic(),
                mDrugList.get(drugIndex).getCategory());
    }
}
