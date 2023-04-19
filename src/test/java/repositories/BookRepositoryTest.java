package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private BookRepository repository;


    @BeforeEach
    void setUp(){
        repository = new BookRepository();
    }

    @AfterEach
    void tearDown() {
        repository.close();
    }

    @Test
    void createBook() {
        Book newBook = new Book();
        newBook.setTitle("This is a title");
        newBook.setAuthor("This is an Author");
        newBook.setDatePublished("2020-12-12");
        long id = repository.createBook(newBook);
        assertNotEquals(0, id);
    }

    @Test
    void getAllBooks() {
        final var books = repository.getAllBooks();
    }

    @Test
    void findById() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void close() {
    }
}