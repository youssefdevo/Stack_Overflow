
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverflow {
    private final Map<Integer, User> users;
    private final Map<Integer, Question> questions;
    private final Map<Integer, Answer> answers;
    private final Map<String, Tag> tags;

    public StackOverflow() {
        users = new ConcurrentHashMap<>();
        questions = new ConcurrentHashMap<>();
        answers = new ConcurrentHashMap<>();
        tags = new ConcurrentHashMap<>();
    }

    public User createUser(String username, String email) {
        User user = new User(username, email);
        users.put(user.getId(), user);
        return user;
    }

    public Question askQuestion(User user, String title, String questionText, ArrayList<String> tags) {
        Question question = user.askQuestion(title, questionText, tags);
        questions.put(question.getId(), question);
        for (Tag tag : question.getTags()) {
            this.tags.putIfAbsent(tag.getName(), tag);
        }
        return question;
    }

    public Answer answerQuestion(User user, Question question, String answerText) {
        Answer answer = user.answerQuestion(question, answerText);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(User user, Commentable commentable, String commentText) {
        return user.addComment(commentable, commentText);
    }

    public void voteQuestion(User user, Question question, int val) {
        question.vote(user, val);
    }

    public void voteAnswer(User user, Answer answer, int val) {
        answer.vote(user, val);
    }

    public void acceptAnswer(Answer answer) {
        answer.markAsAccepted();
    }

    public List<Question> searchQuestions(String query) {
        return questions.values().stream()
                .filter(x -> x.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        x.getContent().toLowerCase().contains(query.toLowerCase()) ||
                        x.getTags().stream().anyMatch(tag -> tag.getName().toLowerCase().contains(query.toLowerCase())))
                .collect(Collectors.toList());
    }

    public ArrayList<Question> getQuestionsByUser(User user) {
        return user.getQuestions();
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public Map<String, Tag> getTags() {
        return tags;
    }

    public Map<Integer, Answer> getAnswers() {
        return answers;
    }
}
