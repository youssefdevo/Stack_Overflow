public class Tag {
    private final int id;
    private final String name;
    private static int ID = 0;

    public Tag(String name) {
        this.name = name;
        this.id = ++ID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
