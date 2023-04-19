package repositories;

import exceptions.StudentAlreadyBorrowedException;
import models.Book;
import models.Borrowings;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingsRepositoryTest {

    private  BorrowingsRepository repository;
    private StudentRepository sp;
    private BookRepository bp;

    @BeforeEach
    void setUp(){
        repository = new BorrowingsRepository();
        sp = new StudentRepository();
        bp = new BookRepository();
    }



    @Test
    void borrowBook() {
        Student std1 = new Student("name", 2);
        Book bk1 = new Book("title","author", "12-22-2020");

        sp.createStudent(std1);
        bp.createBook(bk1);

        long borrowId = repository.createBorrowings(new Borrowings(std1,bk1));
        assertNotEquals(0, borrowId);

//      test throws error
        assertThrows(StudentAlreadyBorrowedException.class, () -> repository.borrowBook(std1, bk1));
    }

    @AfterEach
    void tearDown(){
        repository.close();
        sp.close();
        bp.close();
    }
}