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

import com.example.android.pharmtechapp.R;


// --------------------- Page where user chooses what kind of mode to use ---------------------

public class PracticeMenuActivity extends AppCompatActivity {

    // types of modes available
    private Button btnQuiz;
    private Button btnReview;
    private Button btnFlashCards;
    private Button btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_menu);

        // Shows list of study topics to choose from upon activity start.
        // Also, this fragment is setting up the filter for the DrugLab
        FragmentManager manager = getFragmentManager();
        FilterFragmentDialog dialog = new FilterFragmentDialog();
        dialog.show(manager,"asdfa");

        btnQuiz = (Button)findViewById(R.id.btn_quiz_mode);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenuActivity.this, MultipleChoicesQuiz.class);
                startActivity(intent);
            }
        });

        btnReview = (Button)findViewById(R.id.btn_review_mode);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PracticeMenuActivity.this, ReviewActivity.class);
                startActivity(i);
            }
        });

        btnFlashCards = (Button)findViewById(R.id.btn_flashcard_mode);
        btnFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PracticeMenuActivity.this, FlashcardActivity.class);
                startActivity(i);
            }
        });
    }
}
