public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String genre;

    private boolean read = false;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean getRead() {
        return read;
    }

    public void markAsRead() {
        read = true;
    }

    public void markAsUnread() {
        read = false;
    }

    public boolean isClassic(int currentYear) {
        return currentYear > publicationYear + 20;
    }

    public void printInfo() {
        System.out.printf("Titel: %s%nForfatter: %s%nUdgivelsesår: %d%nGenre: %s%nLæst: %s%n",
                title, author, publicationYear, genre, read ? "ja" : "nej");
    }

    public Book(String title, String author, int publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }
}
