import java.util.Date;

public class Comment {
    private final int id;
    private static int ID = 0;
    private final User author;
    private String content;
    private final Date date;

    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = new Date();
        this.id = ++ID;
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
