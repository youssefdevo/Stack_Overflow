import java.util.ArrayList;

public class User {
    private final String username;

    private final int id;
    private static int ID = 0;

    private final String email;
    private int reputation;


    private final ArrayList<Question> questions;
    private final ArrayList<Answer> answers;
    private final ArrayList<Comment> comments;

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;


    public User(String userName, String email) {
        this.username = userName;
        this.id = ++ID;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();

    }

    public Question askQuestion(String title, String content, ArrayList<String> tags) {
        Question question = new Question(this, title, content, tags);
        this.questions.add(question);
        updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Answer answerQuestion(Question question, String content) {
        Answer answer = new Answer(content, this, question);
        this.answers.add(answer);
        question.addAnswer(answer);
        updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        this.comments.add(comment);
        commentable.addComment(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public synchronized void updateReputation(int reputation) {
        this.reputation += reputation;
        if(this.reputation < 0)
            this.reputation = 0;
    }


    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public float getReputation() {
        return reputation;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
