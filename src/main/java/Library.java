import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private ArrayList<Book> books;

    public void addBooks(Book... books) {
        this.books.addAll(List.of(books));
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
}
