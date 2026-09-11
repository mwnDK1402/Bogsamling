import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private ArrayList<Book> books;

    public void addBooks(Book... books) {
        this.books.addAll(List.of(books));
    }

    public void printBooks() {
        for (var book : books) {
            book.printInfo();
            System.out.println();
        }
    }

    public int getNumberOfBooks() {
        return books.size();
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
}
