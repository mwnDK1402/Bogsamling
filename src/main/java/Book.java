public class Book {
    private String title;
    private String author;
    private int publicationYear;

    private boolean read = false;

    public void markAsRead() {
        read = true;
    }

    public void markAsUnread() {
        read = false;
    }

    public void printInfo() {
        System.out.printf("Titel: %s%nForfatter: %s%nUdgivelsesår: %d%nLæst: %s%n",
                title, author, publicationYear, read ? "ja" : "nej");
    }

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
