import java.util.*;
import java.util.stream.Collectors;

public final class Menu {
    private final Scanner in;
    private final Library library;

    public Menu() {
        in = new Scanner(System.in);
        library = new Library("default");
    }

    public void run() {
        System.out.printf("Menu:%n" +
                "1: Tilføj bog%n" +
                "2: Vis alle bøger%n" +
                "3: Find bog%n" +
                "4: Marker bog som læst eller ulæst%n" +
                "5: Vis ulæste bøger%n" +
                "6: Afslut%n%n");
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                System.out.print("Valg: ");
                var choice = in.nextInt();
                in.skip(System.lineSeparator());

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> printAllBooks();
                    case 3 -> findBook();
                    case 4 -> markBookAsRead();
                    case 5 -> printUnreadBooks();
                    case 6 -> quit();
                    default -> System.out.println("Vælg venligst et tal fra 1 til og med 6");
                }
            }
        } catch (NoSuchElementException _) {
            quit();
        }
    }

    private void addBook() {
        System.out.printf("Indtast venligst bogens data.%n%nTitel: ");
        var title = in.nextLine();
        System.out.print("Forfatter: ");
        var author = in.nextLine();
        System.out.print("Udgivelsesår: ");
        var publicationYear = in.nextInt();
        in.skip(System.lineSeparator());

        var validGenres = Arrays.stream(Genre.values()).map(Enum::name).collect(Collectors.toUnmodifiableSet());

        var genres = new ArrayList<Genre>(4);
        System.out.printf("Tilføj genrer til bogen. Hvis din tekst ikke passer til en genre,%n" +
                "bruges dit input til at søge. Tryk Enter for at stoppe.%n");
        while (true) {
            var line = in.nextLine().toUpperCase(Locale.ROOT);
            if (line.isEmpty()) break;
            if (validGenres.contains(line)) {
                var genre = Genre.valueOf(line);
                genres.add(genre);
            } else {
                validGenres.stream()
                        .filter(g -> g.contains(line))
                        .sorted()
                        .forEach(g -> {
                            System.out.printf("- %s%n", g);
                        });
            }
        }

        var book = new Book(title, author, publicationYear, genres.toArray(Genre[]::new));

        library.addBooks(book);

        System.out.println("Tilføjede bogen til biblioteket:");
        book.printInfo();
        System.out.println();
    }

    private void printAllBooks() {
        if (library.getNumberOfBooks() < 1) {
            System.out.printf("Biblioteket er tomt.%n%n");
            return;
        }

        library.printBooks();
    }

    private void findBook() {
        if (library.getNumberOfBooks() < 1) {
            System.out.printf("Biblioteket er tomt.%n%n");
            return;
        }

        System.out.println("Indtast titlen på bogen. Tryk Enter for at stoppe.");

        while (true) {
            System.out.print("Titel: ");
            var title = in.nextLine();
            if (title.isEmpty()) return;

            var maybeBook = library.findBookByTitle(title);
            if (maybeBook != null) {
                System.out.println();
                maybeBook.printInfo();
                System.out.println();
                break;
            } else {
                System.out.printf("Vi kunne ikke finde bogen med præcis det navn.%n" +
                        "Store eller små bogstaver gør ingen forskel, men tegnsætningen skal være perfekt.%n");
            }
        }
    }

    private void markBookAsRead() {
        if (library.getNumberOfBooks() < 1) {
            System.out.printf("Biblioteket er tomt.%n%n");
            return;
        }

        System.out.println("Indtast titlen på bogen. Tryk Enter for at stoppe.");

        while (true) {
            System.out.print("Titel: ");
            var title = in.nextLine();
            if (title.isEmpty()) return;

            var maybeBook = library.findBookByTitle(title);
            if (maybeBook != null) {
                System.out.println();
                maybeBook.printInfo();
                System.out.println();
                System.out.print("Marker som læst (ja) eller ulæst? ");
                var markRead = in.nextLine();
                if (markRead.equalsIgnoreCase("ja")) {
                    maybeBook.markAsRead();
                    System.out.println("Bogen er nu markeret som læst.");
                } else {
                    maybeBook.markAsUnread();
                    System.out.println("Bogen er nu markeret som ulæst.");
                }
            } else {
                System.out.printf("Vi kunne ikke finde bogen med præcis det navn.%n" +
                        "Store eller små bogstaver gør ingen forskel, men tegnsætningen skal være perfekt.%n");
            }
        }
    }

    private void printUnreadBooks() {
        var readBooks = library.getNumberOfBooks();

        if (readBooks < 1) {
            System.out.printf("Biblioteket er tomt.%n%n");
            return;
        }


        if (library.getNumberOfReadBooks() == readBooks) {
            System.out.printf("Du har læst alle bøger i dit bibliotek!%n%n");
            return;
        }

        library.printUnreadBooks();
    }

    private void quit() {
        System.out.println("Tak for at bruge biblioteket.");
        System.exit(0);
    }
}
