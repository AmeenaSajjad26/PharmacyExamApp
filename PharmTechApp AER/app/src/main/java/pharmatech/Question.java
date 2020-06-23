//Ameena
//Bonface
//Eve

package pharmatech;

public class Question {

    private int mTextResId;
    private String mQuestion;
    private String mAnswer;
    private String mDrugInQuestion;
    private boolean mAnswerTrue;
    private Drug drug;

// --------------------- Constructors ---------------------

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId; // id of question
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, String question, String answer) {
        mTextResId = textResId; // id of question
        mAnswer = answer;
        mQuestion = question;
    }

    public Question(int textResId, String questionDrug) {
        mTextResId = textResId; // id of question
        mDrugInQuestion = questionDrug;
    }

    public Question(int textResId) {
        mTextResId = textResId; // # of question
    }

    public Question(int textResId, String questionText, String drugInQuestion, String drugAnswer) {
        mTextResId = textResId;
        mQuestion = questionText;
        mDrugInQuestion = drugInQuestion;
        mAnswer = drugAnswer;
    }

// --------------------- getters ---------------------

    public int getTextResId() {
        return mTextResId;
    }

    public String getQuestionText() {
        return "Question";
    }

    public String getDrugInQuestion() {
        return mDrugInQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

// --------------------- setters ---------------------

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public void setQuestionText(String question) {
        ;
    }

    public void setDrugInQuestion(String drugInQuestion) {mDrugInQuestion = drugInQuestion;}

    public void setAnswer(String rightAnswer) {
        mAnswer = rightAnswer;
    }

// --------------------- other methods ---------------------

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
}
