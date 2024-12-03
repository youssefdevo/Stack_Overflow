
import java.util.ArrayList;
import java.util.Date;

public class Answer implements Commentable, Votable {
    private final int id;
    private static int ID = 0;
    private final String content;
    private final User author;
    private final Question question;
    private ArrayList<Comment> comments;
    private ArrayList<Vote> votes;
    private final Date date;
    private boolean isAccepted;

    public Answer(String content, User author, Question question) {
        this.content = content;
        this.author = author;
        this.question = question;
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.id = ++ID;
        this.date = new Date();
        this.isAccepted = false;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);

    }

    public void addVote(Vote vote) {
        votes.add(vote);
    }

    public int getId() {
        return id;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Answer.ID = ID;
    }

    public String getContent() {
        return content;
    }


    public User getAuthor() {
        return author;
    }


    public Question getQuestion() {
        return question;
    }

    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Date getDate() {
        return date;
    }



    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    @Override
    public void vote(User user, int val) {
        if (val != 1 && val != -1) {
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        }
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, val));
        author.updateReputation(val * 10);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public void markAsAccepted() {
        if (isAccepted) {
            throw new IllegalStateException("This answer is already accepted");
        }
        isAccepted = true;
        author.updateReputation(15);
    }
}
