//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.android.pharmtechapp.R;

/**
 * This activity is a starting point of the app.
*/
public class MainMenuActivity extends AppCompatActivity {

    private ImageButton mImageButton;
    private Button btnAbout;
    private Button btnPractice;
    private Button btnLegal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        // Practice button brings to PracticeMenuActivity
        btnPractice = (Button)findViewById(R.id.btn_practice);
        btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, PracticeMenuActivity.class);
                startActivity(i);

            }
        });

        btnAbout = (Button)findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                AboutUsDialog dialog = new AboutUsDialog();
                dialog.show(manager,"asdfa");
            }
        });


    }
}
