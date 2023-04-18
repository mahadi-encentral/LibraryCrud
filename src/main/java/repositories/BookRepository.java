package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;

import java.util.List;

public class BookRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public BookRepository(){
        entityManagerFactory = Persistence.createEntityManagerFactory("library_crud");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public long createBook(Book book){
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return book.getBookId();
    }

    public List<Book> getAllBooks(){
        return  entityManager.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public Book findById(long bookId){
        return entityManager.find(Book.class, bookId);
    }

    public Book updateBook(Book book){
        entityManager.getTransaction().begin();
        Book template = findById(book.getBookId());
        template.updateBook(book);
        entityManager.getTransaction().commit();
        return template;
    }

    public void deleteBook(Book book){
        entityManager.getTransaction().begin();
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }


    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }

}
