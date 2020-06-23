//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.pharmtechapp.R;

/**
 *  Review activity shows a list of drugs and its information
*/
public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Cursor c = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_fragments);

        //Toolbar tool = (Toolbar) findViewById(R.id.my_toolbar);
       // setSupportActionBar(tool);

        // create a Fragment manager
        FragmentManager fmTop = getFragmentManager();
        Fragment fragmentTop = fmTop.findFragmentById(R.id.top_fragment_container);

        // Creates a new fragment and puts it in a top container, if no fragments already in a container
        // Creates a new drug list fragment and puts it in a container
        if (fragmentTop == null) {
            fragmentTop = new DrugListFragment();
            fmTop.beginTransaction().add(R.id.top_fragment_container, fragmentTop).commit();
        }

        // create a Fragment manager
        FragmentManager fmMiddle = getFragmentManager();
        Fragment fragmentMiddle = fmMiddle.findFragmentById(R.id.middle_fragment_container);

        // Creates a new fragment and puts it in a middle container, if no fragments already in a container
        // Creates a new detail fragment and puts it in a container
        if (fragmentMiddle == null) {
            fragmentMiddle = new DetailFragment();
            fmMiddle.beginTransaction().add(R.id.middle_fragment_container, fragmentMiddle).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater myMenu = getMenuInflater();
        myMenu.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    // Choices of toolbar on the top of page
    public boolean onOptionsItemSelected(MenuItem item) {
        String ed = "sds";
        switch (item.getItemId()) {
            case R.id.about_us:
                android.app.FragmentManager manager = getFragmentManager();
                AboutUsDialog dialog = new AboutUsDialog();
                dialog.show(manager, "asdfa");
                return true;
            case R.id.seeNote:
                android.app.FragmentManager manag = ReviewActivity.this.getFragmentManager();
                MyNotes myNote = new MyNotes();
                //manag.beginTransaction().
                myNote.show(manag, "note");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
