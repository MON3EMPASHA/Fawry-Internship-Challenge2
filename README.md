![Uploading image.png…]()
# Quantum Bookstore

A simple, extensible Java console application simulating an online bookstore. The system supports multiple book types, inventory management, and a basic purchase flow.

## Features
- **Book Types:**
  - **PaperBook:** Physical books with stock, can be shipped.
  - **EBook:** Digital books with file type, can be sent via email.
  - **ShowcaseBook:** Demo books, not for sale.
- **Inventory Management:**
  - Add books with ISBN, title, year, price, and quantity.
  - Remove outdated books (older than a specified number of years).
  - Print current inventory.
- **Purchase Flow:**
  - Buy a book by ISBN, specifying quantity, email, and address.
  - Reduces stock, throws error if not available.
  - Returns the paid amount.
  - Ships PaperBooks (simulated) or emails EBooks (simulated).
- **Extensible Design:**
  - Easily add new book types by extending the `Book` class.

## Project Structure
```
Fawry Internship Challenge2/
  ├── src/
  │   ├── App.java         # Main application and test class
  │   └── ...              # (Other classes can be added here)
  ├── bin/                 # Compiled classes (if using javac)
  ├── lib/                 # (Optional: for external libraries)
  └── README.md            # This file
```

## How to Run
1. **Compile:**
   - Navigate to the `src` directory.
   - Run: `javac App.java`
2. **Run:**
   - From the `src` directory, run: `java App`

## Example Output
```
Added: book1
Added: book2
Added: book3
** Inventory **
book1 (PaperBook) - 5 in stock
book2 (EBook) - 10 in stock
book3 (ShowcaseBook) - 1 in stock
Added: book3
** Inventory **
book1 (PaperBook) - 5 in stock
Added: book3
Added: book3
** Inventory **
book1 (PaperBook) - 5 in stock
book2 (EBook) - 10 in stock
book3 (ShowcaseBook) - 1 in stock
Shipping paper book 'book1' to Cairo, Egypt
Bought 2x book1 for 200.0
Sending ebook 'book2' to bob@mail.com
Bought 1x book2 for 50.0
** Inventory **
book1 (PaperBook) - 3 in stock
book2 (EBook) - 9 in stock
book3 (ShowcaseBook) - 1 in stock
```

## Extending the System
To add a new book type, create a new class extending `Book` and implement the required methods. Register it in the `Bookstore` as needed.

## License
MIT (or specify your license here)
