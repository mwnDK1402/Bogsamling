import java.util.ArrayList;
import java.util.List;

public final class Library {
    public final String name;
    private final ArrayList<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBooks(Book... books) {
        this.books.addAll(List.of(books));
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getNumberOfBooks() {
        return books.size();
    }

    public Book findBookByTitle(String title) {
        var maybeBook = books.stream()
                .filter(b -> b.title.equalsIgnoreCase(title))
                .findFirst();
        return maybeBook.orElse(null);
    }

    public boolean removeBookByTitle(String title) {
        return books.removeIf(b -> b.title.equalsIgnoreCase(title));
    }

    public void printBooks() {
        for (var book : books) {
            book.printInfo();
            System.out.println();
        }
    }

    public void printUnreadBooks() {
        books.stream().filter(b -> !b.getRead()).forEach(b -> {
            b.printInfo();
            System.out.println();
        });
    }

    public void printBooksByAuthor(String author) {
        books.stream().filter(b -> b.author.equalsIgnoreCase(author)).forEach(b -> {
            b.printInfo();
            System.out.println();
        });
    }
}
