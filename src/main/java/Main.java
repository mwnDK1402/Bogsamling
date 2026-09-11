void main() {
    Book[] books = {
            new Book("The Hobbit", "J.R.R. Tolkien", 1937),
            new Book("Harry Potter og De Vises Sten", "J.K. Rowling", 1997),
            new Book("1984", "George Orwell", 1949),
    };

    books[0].markAsUnread(); // should be unread
    books[1].markAsRead();   // should be read
    books[2].markAsRead();
    books[2].markAsUnread(); // should be unread

    Book classicBook = new Book("Harry Potter and the Half-Blood Prince",
            "J.K. Rowling", 2005);
    Book nonClassicBook = new Book("The God Delusion: A Study of Religious Belief and Skepticism",
            "Richard Dawkins", 2006);

    System.out.printf("'%s' is %s%n", classicBook.getTitle(), classicBook.isClassic(2026) ? "a classic" : "not a classic");
    System.out.printf("'%s' is %s%n", nonClassicBook.getTitle(), nonClassicBook.isClassic(2026) ? "a classic" : "not a classic");

    Library library = new Library("Min bogsamling");
    library.addBooks(books);
    library.addBooks(classicBook, nonClassicBook);

    System.out.println();
    library.printBooks();
}