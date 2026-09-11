public class Book {
    private String title;
    private String author;
    private int publicationYear;

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
        System.out.printf("Titel: %s%nForfatter: %s%nUdgivelsesår: %d%nLæst: %s%n",
                title, author, publicationYear, read ? "ja" : "nej");
    }

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
