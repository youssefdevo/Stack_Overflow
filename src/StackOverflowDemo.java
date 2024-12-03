import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackOverflowDemo {
    public static void run() {

        StackOverflow stackOverflow = new StackOverflow();

        // Create users
        User youssef = stackOverflow.createUser("Youssef", "Youssef@example.com");
        User alice = stackOverflow.createUser("Alice", "alice@example.com");
        User bob = stackOverflow.createUser("Bob", "bob@example.com");
        User charlie = stackOverflow.createUser("Charlie", "charlie@example.com");

        // Youssef asks a question
        Question javaQuestion1 = stackOverflow.askQuestion(youssef, "What is the difference between HashMap and ConcurrentHashMap?",
                "What is the difference between HashMap and ConcurrentHashMap?",
                new ArrayList<>(Arrays.asList("java", "data structures")));


        // Alice asks a question
        Question javaQuestion2 = stackOverflow.askQuestion(alice, "What is polymorphism in Java?",
                "Can someone explain polymorphism in Java with an example?",
                new ArrayList<>(Arrays.asList("java", "oop")));

        // Bob answers Alice's question
        Answer bobAnswer = stackOverflow.answerQuestion(bob, javaQuestion2,
                "Polymorphism in Java is the ability of an object to take on many forms...");

        // Charlie comments on the question
        stackOverflow.addComment(charlie, javaQuestion2, "Great question! I'm also interested in learning about this.");

        // Alice comments on Bob's answer
        stackOverflow.addComment(alice, bobAnswer, "Thanks for the explanation! Could you provide a code example?");

        // Charlie votes on the question and answer
        stackOverflow.voteQuestion(charlie, javaQuestion2, 1);  // Upvote
        stackOverflow.voteAnswer(charlie, bobAnswer, 1);  // Upvote

        // Alice accepts Bob's answer
        stackOverflow.acceptAnswer(bobAnswer);

        // Bob asks another question
        Question pythonQuestion = stackOverflow.askQuestion(bob, "How to use list comprehensions in Python?",
                "I'm new to Python and I've heard about list comprehensions. Can someone explain how to use them?",
                new ArrayList<>(Arrays.asList("python", "list-comprehension")));

        // Alice answers Bob's question
        Answer aliceAnswer = stackOverflow.answerQuestion(alice, pythonQuestion,
                "List comprehensions in Python provide a concise way to create lists...");

        // Charlie votes on Bob's question and Alice's answer
        stackOverflow.voteQuestion(charlie, pythonQuestion, 1);  // Upvote
        stackOverflow.voteAnswer(charlie, aliceAnswer, 1);  // Upvote

        // Print out the current state
        System.out.println("Question: " + javaQuestion2.getTitle());
        System.out.println("Asked by: " + javaQuestion2.getAuthor().getUsername());
        System.out.println("Tags: " + javaQuestion2.getTags().stream().map(Tag::getName).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + javaQuestion2.getVoteCount());
        System.out.println("Comments: " + javaQuestion2.getComments().size());
        System.out.println("\nAnswer by " + bobAnswer.getAuthor().getUsername() + ":");
        System.out.println(bobAnswer.getContent());
        System.out.println("Votes: " + bobAnswer.getVoteCount());
        System.out.println("Accepted: " + bobAnswer.isAccepted());
        System.out.println("Comments: " + bobAnswer.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Alice: " + alice.getReputation());
        System.out.println("Bob: " + bob.getReputation());
        System.out.println("Charlie: " + charlie.getReputation());

        // Demonstrate search functionality
        System.out.println("\nSearch Results for 'java':");
        List<Question> searchResults = stackOverflow.searchQuestions("java");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        System.out.println("\nSearch Results for 'python':");
        searchResults = stackOverflow.searchQuestions("python");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        // Demonstrate getting questions by user
        System.out.println("\nYoussef's Questions:");
        List<Question> youssefQuestions = stackOverflow.getQuestionsByUser(youssef);
        for (Question q : youssefQuestions) {
            System.out.println(q.getTitle());
        }
    }
}