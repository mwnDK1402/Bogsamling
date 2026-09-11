public static final int BAR_WIDTH = 20;

void main() {
    Book[] books = {
            new Book("The Hobbit", "J.R.R. Tolkien", 1937, Genre.CHILDRENS_FANTASY, Genre.CHILDRENS_ADVENTURE, Genre.MYTHICAL_CREATURES, Genre.CANON, Genre.BOOK_TO_SCREEN, Genre.FICTION),
            new Book("Harry Potter og De Vises Sten", "J.K. Rowling", 1997, Genre.HARRY_POTTER, Genre.CHILDRENS_FANTASY, Genre.CHILDRENS_ADVENTURE, Genre.WITCHCRAFT, Genre.BOOK_TO_SCREEN, Genre.CHOSEN_FAMILY, Genre.CANON),
            new Book("1984", "George Orwell", 1949, Genre.FICTION, Genre.POLITICAL_THRILLER, Genre.SCIENCE_FICTION_FANTASY, Genre.DYSTOPIAN_YOUNG_ADULT),
    };

    books[0].markAsRead();
    books[1].markAsRead();

    Book classicBook = new Book("Harry Potter and the Half-Blood Prince",
            "J.K. Rowling", 2005, Genre.HARRY_POTTER, Genre.CHILDRENS_FANTASY, Genre.CHILDRENS_ADVENTURE, Genre.WITCHCRAFT, Genre.BOOK_TO_SCREEN, Genre.CHOSEN_FAMILY, Genre.CANON);
    Book nonClassicBook = new Book("The God Delusion: A Study of Religious Belief and Skepticism",
            "Richard Dawkins", 2006, Genre.NON_FICTION, Genre.SCIENCE_NATURE, Genre.SPIRITUALITY, Genre.HISTORY_AND_POLITICS);

    System.out.printf("%s%n%n", "-".repeat(BAR_WIDTH));

    System.out.printf("'%s' is %s%n", classicBook.title, classicBook.isClassic(2026) ? "a classic" : "not a classic");
    System.out.printf("'%s' is %s%n", nonClassicBook.title, nonClassicBook.isClassic(2026) ? "a classic" : "not a classic");

    classicBook.markAsRead();


    Library library = new Library("Min bogsamling");
    library.addBooks(books);
    library.addBooks(classicBook, nonClassicBook);

    System.out.printf("%n%s%n", "-".repeat(BAR_WIDTH));
    System.out.printf("Antal bøger: %d%n", library.getNumberOfBooks());
    System.out.printf("%s%n%n", "-".repeat(BAR_WIDTH));
    System.out.println("=== Min bogsamling ===");
    library.printBooks();

    System.out.printf("%s%n%n", "-".repeat(BAR_WIDTH));

    Book foundBook = library.findBookByTitle("the hobbit");

    System.out.println("=== Find en bog ===");
    if (foundBook != null) {
        foundBook.printInfo();
    } else {
        System.out.println("Bogen blev ikke fundet.");
    }

    System.out.printf("%n%s%n%n", "-".repeat(BAR_WIDTH));
    System.out.println("=== Bøger du ikke har læst ===");
    library.printUnreadBooks();

    System.out.printf("%s%n", "-".repeat(BAR_WIDTH));
    if (library.removeBookByTitle("the god delusion: a study of religious belief and skepticism")) {
        System.out.println("'The God Delusion' blev fjernet fra biblioteket.");
    }

    System.out.printf("%s%n%n", "-".repeat(BAR_WIDTH));
    System.out.println("=== Bøger af J.K. Rowling ===");
    library.printBooksByAuthor("J.K. Rowling");

    System.out.printf("%s%n", "-".repeat(BAR_WIDTH));
    System.out.printf("Antal læste bøger: %d%n", library.getNumberOfReadBooks());
    System.out.printf("%s%n", "-".repeat(BAR_WIDTH));
}