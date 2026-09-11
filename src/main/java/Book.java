import java.util.List;

public final class Book {
    public final String title;
    public final String author;
    public final int publicationYear;
    public final List<Genre> genres;

    private boolean read = false;

    public Book(String title, String author, int publicationYear, Genre... genres) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genres = List.of(genres);
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
        System.out.printf("Titel: %s%nForfatter: %s%nUdgivelsesår: %d%nLæst: %s%nGenres:%n",
                title, author, publicationYear, read ? "ja" : "nej");
        for (var genre : genres) {
            System.out.printf("- %s%n", genre.name());
        }
    }
}
