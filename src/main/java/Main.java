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

    for (var book : books) {
        book.printInfo();
        System.out.println();
    }
}