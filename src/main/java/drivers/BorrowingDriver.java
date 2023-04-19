package drivers;

import exceptions.StudentAlreadyBorrowedException;
import models.Book;
import models.Borrowings;
import models.Student;
import repositories.BookRepository;
import repositories.BorrowingsRepository;
import repositories.StudentRepository;

public class BorrowingDriver {

    public static void main(String[] args) throws StudentAlreadyBorrowedException {


        BorrowingsRepository repository = new BorrowingsRepository();
        StudentRepository sp = new StudentRepository();
        BookRepository bp = new BookRepository();

//        Student student = new Student();
//        student.setName("Mahadi");student.setLevel(4);
//        sp.createStudent(student);
//
//        Book book = new Book();
//        book.setTitle("title 1");book.setAuthor("Author I");book.setDatePublished("2020-12-11");
//        bp.createBook(book);

//        Borrowings borrowings = new Borrowings(student, book);
//        repository.createBorrowings(borrowings);
        System.out.println(repository.getAllBorrowings());

        Student student = sp.findById(1);
        Book book = bp.findById(2);

        repository.borrowBook(student, book);

        System.out.println(repository.getAllBorrowings());

        sp.close();
        bp.close();
        repository.close();
    }
}
