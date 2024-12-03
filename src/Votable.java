public interface Votable {

    void vote(User user, int val);
    int getVoteCount();
}
