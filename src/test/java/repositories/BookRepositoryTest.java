package repositories;


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
        repository = null;
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
        for(int i=0; i< 4; i++)
            repository.createBook(new Book("Book " + (i+1), "Author " + i, "2020-11-0" + (i+1)));
        final var books = repository.getAllBooks();
        assertTrue(books.size() >= 4);
    }


    @Test
    void updateBook() {
        Book temp = new Book("Update Book", "Update author", "1990-01-01");
        long newId = repository.createBook(temp);
        Book saved = repository.findById(newId);
        saved.setTitle("Name Updated");
        saved.setAuthor("Author Updated");
        repository.updateBook(saved);

        Book updated = repository.findById(saved.getBookId());
        assertNotEquals(updated.getTitle(), "Update Book");
        assertEquals(updated.getTitle(), saved.getTitle());
        assertEquals(updated.getAuthor(), saved.getAuthor());
    }

    @Test
    void deleteBook() {
        Book temp = new Book("Update Book", "temp", "01-01-1222");
        long newId = repository.createBook(temp);
        Book saved = repository.findById(newId);
        assertNotEquals(null, saved);

        repository.deleteBook(saved);

        Book deleted = repository.findById(newId);
        assertNull(deleted);
    }

}