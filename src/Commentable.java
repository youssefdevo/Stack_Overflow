import java.util.ArrayList;

public interface Commentable {

    void addComment(Comment comment);
    ArrayList<Comment> getComments();
}
