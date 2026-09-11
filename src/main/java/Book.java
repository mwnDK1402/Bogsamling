public class Book {
    private String title;
    private String author;
    private int publicationYear;

    public void printInfo() {
        System.out.printf("Titel: %s%nForfatter: %s%nUdgivelsesår: %d%n",
                title, author, publicationYear);
    }

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
