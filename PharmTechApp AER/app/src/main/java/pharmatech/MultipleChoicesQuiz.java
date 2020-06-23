//Ameena
//Bonface
//Eve

package pharmatech;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pharmtechapp.R;

import java.util.List;
import java.util.Random;


// --------------------- Multiple choices quiz ---------------------

public class MultipleChoicesQuiz extends AppCompatActivity {
    private static final String TAG = "MultipleChoiceActivity";
    private static final String KEY_INDEX = "index";
    private static final boolean DEBUG = false;
    private static final int REQUEST_CODE_CHEAT = 0;

    public RadioGroup radioGroup;
    private RadioButton mAnswer1;
    private RadioButton mAnswer2;
    private RadioButton mAnswer3;
    private RadioButton mAnswer4;

    private Button mNextButton;

    // Gets a filtered list of drugs
    DrugLab drugLab = DrugLab.get(this);
    List<Drug> mDrugs = drugLab.getDrugs();

    // Gets a full list of drugs
    List<Drug> fullList = drugLab.getFullList();

    Random randomGenerator = new Random();
    private TextView mNextQuestionTextView;
    private TextView mQuestionNum;

    String questionDrug = null;
    String answerDrug = null;
    String wrongAnswer1 = null;
    String wrongAnswer2 = null;
    String wrongAnswer3 = null;

    // StringBuilder for collecting info for
    // each answered question during the quiz.
    StringBuilder report = new StringBuilder();

    // Keeps track of right answers
    private int correctAnswers = 0;

    // Keeps track of number of questions shown
    int questionCount = 1;

    // index of the questions array
    private int mCurrentIndex = 0;

    // Number of answer options
    final int NUM_OF_ANSWERS_OPTION = 4;
    // Number of questions in the quiz
    final int NUM_OF_QUESTIONS = 5;

    // Array to hold answer options
    String[] answersArray = new String[4] ;

    // Array of questions
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_generic_name),
            new Question(R.string.question_brand_name),
            new Question(R.string.question_category),
            new Question(R.string.question_purpose),
    };


    // Updates questions and displays it on the screen
    private void updateQuestion() {
        if (DEBUG)  Log.d(TAG, "Updating question text for question #" + mCurrentIndex,
                new Exception());

        String questionNumber = " " + questionCount;
        mQuestionNum.setText(String.format(getString(R.string.question_out_of_text),questionNumber));

        // Index of drug chosen in drug list(mDrugs)
        int index = randomGenerator.nextInt(mDrugs.size()-1);

        // Random number from full list
        int wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);

        boolean debug = true;

        // Randomly picks a question from Question array
        mCurrentIndex = randomGenerator.nextInt(mQuestionBank.length);

        if(debug) {
            for (int i = 0; i < NUM_OF_ANSWERS_OPTION; i++) {
                if (answersArray[i] != null)  Log.i("MCQUIZ answer array", answersArray[i]);
            }
        }

        // reads Drug info from index given, chooses the drug for the question and the answer for it,
        // depending on type of question (mCurrentIndex)
        switch(mCurrentIndex){
            case 0: questionDrug = mDrugs.get(index).getBrandName();
                answerDrug = mDrugs.get(index).getGenericName();
                if (debug)Log.i("MCQUIZ Answer", answerDrug);
                wrongAnswer1 = fullList.get(wrongAnswerIndex).getGenericName();
                while(answerDrug == wrongAnswer1){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer1 = fullList.get(wrongAnswerIndex).getGenericName();
                }
                if (debug)Log.i("MCQUIZ wrong1", wrongAnswer1);

                wrongAnswer2 = fullList.get(wrongAnswerIndex).getGenericName();
                while(answerDrug == wrongAnswer2 || wrongAnswer2 == wrongAnswer1){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer2 = fullList.get(wrongAnswerIndex).getGenericName();
                }
                if (debug)Log.i("MCQUIZ wrong2", wrongAnswer2);

                wrongAnswer3 = fullList.get(wrongAnswerIndex).getGenericName();
                while(answerDrug == wrongAnswer3 || wrongAnswer3 == wrongAnswer1 || wrongAnswer3 == wrongAnswer2){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer3 = fullList.get(wrongAnswerIndex).getGenericName();
                }
                if (debug)Log.i("MCQUIZ wrong3", wrongAnswer3);

                break;

            case 1: questionDrug = mDrugs.get(index).getGenericName();
                answerDrug = mDrugs.get(index).getBrandName();
                wrongAnswer1 = fullList.get(wrongAnswerIndex).getBrandName();
                while(answerDrug == wrongAnswer1){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer1 = fullList.get(wrongAnswerIndex).getBrandName();
                }

                wrongAnswer2 = fullList.get(wrongAnswerIndex).getBrandName();
                while(wrongAnswer2 == answerDrug || wrongAnswer2 == wrongAnswer1){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer2 = fullList.get(wrongAnswerIndex).getBrandName();
                }

                wrongAnswer3 = fullList.get(wrongAnswerIndex).getBrandName();
                while(wrongAnswer3 == answerDrug || wrongAnswer3 == wrongAnswer1 || wrongAnswer3 == wrongAnswer2){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer3 = fullList.get(wrongAnswerIndex).getBrandName();
                }

                break;

            case 2: questionDrug = mDrugs.get(index).getGenericName();
                answerDrug = mDrugs.get(index).getCategory();
                wrongAnswer1 = fullList.get(wrongAnswerIndex).getCategory();
                while(answerDrug.equals(wrongAnswer1)){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer1 = fullList.get(wrongAnswerIndex).getCategory();
                }

                wrongAnswer2 = fullList.get(wrongAnswerIndex).getCategory();
                while(answerDrug.equals(wrongAnswer2) || wrongAnswer2.equals(wrongAnswer1)){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer2 = fullList.get(wrongAnswerIndex).getCategory();
                }

                wrongAnswer3 = fullList.get(wrongAnswerIndex).getCategory();
                while(answerDrug.equals(wrongAnswer3) || wrongAnswer3.equals(wrongAnswer1) || wrongAnswer3.equals(wrongAnswer2)){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer3 = fullList.get(wrongAnswerIndex).getCategory();
                }

                break;

            case 3: questionDrug = mDrugs.get(index).getBrandName();
                answerDrug= mDrugs.get(index).getPurpose();
                wrongAnswer1 = fullList.get(wrongAnswerIndex).getPurpose();
                while(answerDrug.equals(wrongAnswer1)){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer1 = fullList.get(wrongAnswerIndex).getPurpose();
                }

                wrongAnswer2 = fullList.get(wrongAnswerIndex).getPurpose();
                while(answerDrug.equals(wrongAnswer2) || wrongAnswer2.equals(wrongAnswer1)){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer2 = fullList.get(wrongAnswerIndex).getPurpose();
                }

                wrongAnswer3 = fullList.get(wrongAnswerIndex).getPurpose();
                while(answerDrug.equals(wrongAnswer3) || wrongAnswer3.equals(wrongAnswer1) || wrongAnswer3.equals(wrongAnswer2)){
                    wrongAnswerIndex = randomGenerator.nextInt(fullList.size()-1);
                    wrongAnswer3 = fullList.get(wrongAnswerIndex).getPurpose();
                }

                break;
        }

        // Randomize place of right answer
        int rightAnswerPlace = randomGenerator.nextInt(NUM_OF_ANSWERS_OPTION);

        // Stores right answer in random index of answersArray
        answersArray[rightAnswerPlace] = answerDrug;

        // Stores wrong answers
        for(int i = 0; i < NUM_OF_ANSWERS_OPTION; i++){
            if(answersArray[i] == null){
                answersArray[i] = wrongAnswer1;
                break;
            }
        }

        for(int i = 0; i < NUM_OF_ANSWERS_OPTION; i++){
            if(answersArray[i] == null){
                answersArray[i] = wrongAnswer2;
                break;
            }
        }

        for(int i = 0; i < NUM_OF_ANSWERS_OPTION; i++){
            if(answersArray[i] == null){
                answersArray[i] = wrongAnswer3;
                break;
            }
        }

        // sets the question in the textview
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mNextQuestionTextView.setText(String.format(getString(question), questionDrug));

        // Assigns the drugs in the array to the radio buttons
        mAnswer1.setText(answersArray[0]);
        mAnswer2.setText(answersArray[1]);
        mAnswer3.setText(answersArray[2]);
        mAnswer4.setText(answersArray[3]);

        for(int i = 0; i < NUM_OF_ANSWERS_OPTION; i++){
            answersArray[i] = null;
        }

        radioGroup.clearCheck();
        questionCount++;

    }

    // checks if answer is correct or not and shows a message
    private void checkAnswer(CharSequence chosenAnswer, String rightAnswer) {

        String messageResId = "";
        String giveRightAnswer = "The correct answer is " + rightAnswer;
        String giveYourAnswer = "Your answer is " + chosenAnswer;

        //if user's answer is right, toast value is correct
        if(chosenAnswer == rightAnswer){
            messageResId = "Correct";
            correctAnswers++;
            report.append("Question ").append(questionCount-1)
                    .append(": ").append(messageResId).append(" ").append(giveYourAnswer)
                    .append("\n");
        }
        else{ //if user's answer is wrong, toast value is incorrect and it shows right answer
            messageResId = "Incorrect!";
            report.append("Question ").append(questionCount-1)
                    .append(": ").append(messageResId).append(" ").append(giveRightAnswer)
                    .append("\n");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_multiple_choice_quiz);


        // Title to show question number
        mQuestionNum = (TextView) findViewById(R.id.text_text_view);

        // question set to textView
        mNextQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mNextQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();
            }
        });

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        // creates radioButton and set as an answer option
        mAnswer1 = (RadioButton) findViewById(R.id.option1_button);
        mAnswer1.setText(answersArray[0]);
        mAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(mAnswer1.getText(), answerDrug);
            }
        });

        // creates radioButton and set as an answer option
        mAnswer2 = (RadioButton) findViewById(R.id.option2_button);
        mAnswer2.setText(answersArray[1]);
        mAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(mAnswer2.getText(), answerDrug);
            }
        });

        // creates radioButton and set as an answer option
        mAnswer3 = (RadioButton) findViewById(R.id.option3_button);
        mAnswer3.setText(answersArray[2]);
        mAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(mAnswer3.getText(), answerDrug);
            }
        });

        // creates radioButton and sets text as an answer option
        mAnswer4 = (RadioButton) findViewById(R.id.option4_button);
        mAnswer4.setText(answersArray[3]);
        mAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(mAnswer4.getText(), answerDrug);
            }
        });


        //button to go to the next question
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                if(questionCount < NUM_OF_QUESTIONS + 1) {
                    updateQuestion();
                }
                else {
                    // Toast to show number of correct answers
                    String score = "You got " + correctAnswers + " correct answers!";

                    report.append("\nSCORE: ").append(correctAnswers).append(" out of ").append(NUM_OF_QUESTIONS);
                    if(correctAnswers == NUM_OF_QUESTIONS)
                        report.append("\nGOOD JOB!");

                    FragmentManager manager = getFragmentManager();
                    ScoreFragment dialog = new ScoreFragment();
                    dialog.setName(report.toString());
                    dialog.show(manager,"asdfa");

                    Toast.makeText(getApplicationContext(), score,Toast.LENGTH_SHORT).show();

                }
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // Keeps track of number of questions
        if(questionCount < NUM_OF_QUESTIONS) {
            updateQuestion();
        }
        else {
            finishActivity(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }

        }
    }

    // saves data when device configuration changes
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
}
