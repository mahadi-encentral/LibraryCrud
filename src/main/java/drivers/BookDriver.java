package drivers;

import models.Book;
import repositories.BookRepository;

import java.util.Scanner;

import static drivers.GeneralMenu.showMenu;

public class BookDriver {
    private final static BookRepository bookRepository = new BookRepository();
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String[] bookMenus = {
                "List Books", "Find Book", "Add Book", "Update Book", "Delete Book", "Exit"
        };

        int choice = 0;
        while (choice != bookMenus.length){
            choice = showMenu(bookMenus, in);
            switch (choice){
                case 1:
                    listBooks();
                    break;
                case 2:
                    findBook();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    bookRepository.close();
                    in.close();
                    System.out.println("\nGood Bye");
                    break;
            }
        }
    }

    static void listBooks(){
        final var books = bookRepository.getAllBooks();
        books.forEach(System.out::println);
    }

    static void addBook(){
        Book book = new Book();

        System.out.print("Enter Book's title: ");
        book.setTitle(in.nextLine());

        System.out.print("Enter Book's Author: ");
        book.setAuthor(in.nextLine());

        System.out.print("Enter Book's publishing date: ");
        book.setDatePublished(in.nextLine());

        bookRepository.createBook(book);
        System.out.println("Book Added Successfully");

    }

    static Book findBook(){
        System.out.print("Enter book's id: ");
        Book book = bookRepository.findById(in.nextLong());
        if(book != null)
            System.out.println("\n" + book);
        else{
            System.out.println("Book not found");
        }
        in.nextLine();
        return book;
    }

    static void updateBook(){
        Book book = findBook();
        String temp;
        if(book == null){
            return;
        }
        System.out.print("Title (Enter to Skip): ");
        temp = in.nextLine();
        if(!temp.strip().equals("")) book.setTitle(temp);

        System.out.print("Author (Enter to Skip): ");
        temp = in.nextLine();
        if(!temp.strip().equals("")) book.setAuthor(temp);

        System.out.print("Published Date (Enter to Skip): ");
        temp = in.nextLine();
        if(!temp.strip().equals("")) book.setDatePublished(temp);

        bookRepository.updateBook(book);
        System.out.println("\nBook Updated Successfully");
    }

    static void deleteBook(){
        Book book = findBook();
        if(book != null){
            bookRepository.deleteBook(book);
            System.out.println("\nBook Deleted Successfully");
        }
    }

}
