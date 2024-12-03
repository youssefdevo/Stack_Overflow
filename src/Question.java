
import java.util.ArrayList;
import java.util.Date;

public class Question implements Commentable, Votable {
    private final User author;
    private final String title;
    private final String content;
    private final int id;
    private ArrayList<Answer> answers;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;
    private ArrayList<Vote> votes;
    private static int ID = 0;
    private final Date date;

    public Question(User author, String title, String content, ArrayList<String> tags) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.id = ++ID;
        answers = new ArrayList<>();
        comments = new ArrayList<>();
        this.tags = new ArrayList<>();
        for (String tag : tags) {
            if (tag != null) {
                assert this.tags != null;
                this.tags.add(new Tag(tag));
            }
        }
        this.votes = new ArrayList<>();
        this.date = new Date();
    }

    public void addAnswer(Answer answer) {
        if (!answers.contains(answer))
            answers.add(answer);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }


    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void addVote(Vote vote) {
        votes.add(vote);
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    public int getId() {
        return id;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Question.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public void vote(User user, int val) {
        if (val != 1 && val != -1)
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, val));
        author.updateReputation(val * 5);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }
}
