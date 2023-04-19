package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;
import models.Borrowings;
import models.Student;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowingsRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public BorrowingsRepository(){
        entityManagerFactory = Persistence.createEntityManagerFactory("library_crud");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public long createBorrowings(Borrowings borrowing){
        entityManager.getTransaction().begin();
        entityManager.persist(borrowing);
        entityManager.getTransaction().commit();
        return borrowing.getId();
    }

    public List<Borrowings> getAllBorrowings(){
        return  entityManager.createNamedQuery("findAllBorrowings", Borrowings.class).getResultList();
    }

    public Borrowings findById(long borrowingId){
        return entityManager.find(Borrowings.class, borrowingId);
    }

    public List<Book> findBooksBorrowedByStudent(Student student) {
        return entityManager
                    .createNamedQuery("getStudentBorrowings", Borrowings.class)
                    .setParameter("id", student.getStudentId())
                    .getResultList().stream().map(Borrowings::getBook).collect(Collectors.toList());
    }

    public List<Student> findStudentsBorrowingBook(Book book) {
        return entityManager
                .createNamedQuery("findBookBorrowers", Borrowings.class)
                .setParameter("id", book.getBookId())
                .getResultList().stream().map(Borrowings::getStudent).collect(Collectors.toList());
    }

    public void deleteBorrowings(Borrowings borrowing){
        entityManager.getTransaction().begin();
        entityManager.remove(borrowing);
        entityManager.getTransaction().commit();
    }


    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
