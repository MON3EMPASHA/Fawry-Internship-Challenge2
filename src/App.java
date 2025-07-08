import java.util.*;

interface Shippable {
    void ship(String address);
}

interface Emailable {
    void email(String email);
}

abstract class Book {
    String isbn;
    String title;
    int year;
    double price;
    int quantity;

    Book(String isbn, String title, int year, double price, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
    }

    abstract boolean isForSale();
    abstract boolean isOutdated(int currentYear, int maxAge);
}

class PaperBook extends Book implements Shippable {
    PaperBook(String isbn, String title, int year, double price, int quantity) {
        super(isbn, title, year, price, quantity);
    }

    boolean isForSale() { return true; }
    boolean isOutdated(int currentYear, int maxAge) {
        return currentYear - year > maxAge;
    }

    public void ship(String address) {
        System.out.println("Shipping paper book '" + title + "' to " + address);
    }
}

class EBook extends Book implements Emailable {
    String fileType;

    EBook(String isbn, String title, int year, double price, int quantity, String fileType) {
        super(isbn, title, year, price, quantity);
        this.fileType = fileType;
    }

    boolean isForSale() { return true; }
    boolean isOutdated(int currentYear, int maxAge) {
        return currentYear - year > maxAge;
    }

    public void email(String email) {
        System.out.println("Sending ebook '" + title + "' to " + email);
    }
}

class ShowcaseBook extends Book {
    ShowcaseBook(String isbn, String title, int year) {
        super(isbn, title, year, 0, 1);
    }

    boolean isForSale() { return false; }
    boolean isOutdated(int currentYear, int maxAge) {
        return false;
    }
}

class Bookstore {
    Map<String, Book> inventory = new HashMap<>();

    void addBook(Book book) {
        inventory.put(book.isbn, book);
        System.out.println("Added: " + book.title);
    }

    void removeOutdatedBooks(int currentYear, int maxAge) {
        Iterator<Book> it = inventory.values().iterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.isForSale() && b.isOutdated(currentYear, maxAge)) {
                System.out.println("Removed outdated: " + b.title);
                it.remove();
            }
        }
    }

    double buyBook(String isbn, int qty, String email, String address) {
        Book b = inventory.get(isbn);
        if (b == null || !b.isForSale()) throw new RuntimeException("Book not for sale");
        if (b.quantity < qty) throw new RuntimeException("Not enough stock");
        b.quantity -= qty;
        double paid = b.price * qty;

        if (b instanceof PaperBook) {
            ((PaperBook) b).ship(address);
        } else if (b instanceof EBook) {
            ((EBook) b).email(email);
        }
        System.out.println("Bought " + qty + "x " + b.title + " for " + paid);
        return paid;
    }

    void printInventory() {
        System.out.println("** Inventory **");
        for (Book b : inventory.values()) {
            System.out.printf("%s (%s) - %d in stock\n", b.title, b.getClass().getSimpleName(), b.quantity);
        }
    }
}

public class App {
    public static void main(String[] args) {
        Bookstore store = new Bookstore();

        store.addBook(new PaperBook("100", "book1", 2025, 100, 5));
        store.addBook(new EBook("200", "book2", 2024, 50, 10, "pdf"));
        store.addBook(new ShowcaseBook("300", "book3", 2000));

        store.printInventory();

        // Remove outdated books (older than 5 years)
        store.removeOutdatedBooks(2024, 5);

        // Buy a paper book
        store.buyBook("100", 2, "ali@mail.com", "Cairo, Egypt");

        // Buy an ebook
        store.buyBook("200", 1, "bob@mail.com", "N/A");

        store.printInventory();
    }
}