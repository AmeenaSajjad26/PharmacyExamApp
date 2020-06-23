package pharmatech;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DrugsBaseHelper;

/**
 * This class works with the the given in the database list of drugs.
 * Querying db accordingly to the given filters and retrieves
 * only needed drugs to the list. List saves in the app since retrieved,
 * so it can be used by any other class.
 */
public class DrugLab {

    private List<Drug> mDrugs;
    private Drug mDrug;
    private static DrugLab sDrugLab;
    private Context mContext;
    private DrugsBaseHelper myDBhelp;
    private SQLiteDatabase mDatabase;
    private List<String> filter;
    Cursor c = null;

    // Database attributes indexes
    final int GENERIC_NAME = 1;
    final int BRAND_NAME = 2;
    final int PURPOSE = 3;
    final int DEA_SCH = 4;
    final int SPECIAL = 5;
    final int CATEGORY = 6;
    final int STUDY_TOPIC = 7;
    final int ID = 0;

    public static DrugLab get(Context context) {
        if (sDrugLab == null) {
            sDrugLab = new DrugLab(context);
        }
        return sDrugLab;
    }

    private DrugLab(Context context) {

        filter = new ArrayList<>();
        mContext = context.getApplicationContext();
        myDBhelp = new DrugsBaseHelper(mContext);

    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    // Method that opens database, read and stores drugs
    // accordingly to chosen filters


    /**
     * Method that opens database, read and stores drugs
     * accordingly to chosen filters
     * @return List of drugs from the database according to current filters
     */
    public List<Drug> getDrugs() {
        mDrugs = new ArrayList<>();

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
        // If not filters were applied - loads All drugs from the database into the list by default
        if (!getFilter().isEmpty())
            for (String myChoice : this.getFilter()) {
                c = myDBhelp.queryDrugs(null, null, null, null, null, myChoice, null, null);
                int i = 0;
                if (c.moveToFirst()) {
                    do {
                        mDrug = new Drug(c.getString(ID),c.getString(GENERIC_NAME), c.getString(BRAND_NAME), c.getString(PURPOSE), c.getString(DEA_SCH),
                                c.getString(SPECIAL), c.getString(CATEGORY), c.getString(STUDY_TOPIC));
                        mDrugs.add(mDrug);
                        i++;
                    } while (c.moveToNext() && i != 200);
                }
            }
            // If only particular study topics were chosen
            // applies filter  and loads only needed drugs into the list
        else {
            String temp = "All";
            c = myDBhelp.queryDrugs(null, null, null, null, null, temp, null, null);
            int i = 0;
            if (c.moveToFirst()) {
                do {
                    mDrug = new Drug(c.getString(ID),c.getString(GENERIC_NAME), c.getString(BRAND_NAME), c.getString(PURPOSE), c.getString(DEA_SCH),
                            c.getString(SPECIAL), c.getString(CATEGORY), c.getString(STUDY_TOPIC));

                    mDrugs.add(mDrug);
                    i++;
                } while (c.moveToNext() && i != 200);
            }
        }
        c.close();
        return mDrugs;
    }

    /**
     * Method that opens database, read and stores drugs
     * accordingly to chosen filters
     * @return Full list of drugs from the database
     */
    public List<Drug> getFullList(){

        c = myDBhelp.queryDrugs(null, null, null, null, null, "All", null, null);
        int i = 0;
        if (c.moveToFirst()) {
            do {
                mDrug = new Drug(c.getString(ID),c.getString(GENERIC_NAME), c.getString(BRAND_NAME), c.getString(PURPOSE),c.getString(DEA_SCH), c.getString(SPECIAL),c.getString(CATEGORY),c.getString(STUDY_TOPIC));
                mDrugs.add(mDrug);
                i++;
            } while (c.moveToNext() && i != 200);
        }
        return mDrugs;
    }

    public Drug getMedicine(String id) {
        for (Drug drug : mDrugs) {
            if (drug.getGenericName().equals(id)) {
                return drug;
            }
        }
        return null;
    }
    public Drug getAnotherMedicine(String id1) {
        for (Drug drug : mDrugs) {
            if (drug.getId_med().equals(id1)) {
                return drug;
            }
        }
        return null;
    }

}
