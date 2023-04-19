package repositories;

import exceptions.StudentAlreadyBorrowedException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;
import models.Borrowings;
import models.Student;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowingsRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    private Logger logger = Logger.getLogger(BorrowingsRepository.class);

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
        return getAllBorrowings().stream()
                .filter(br -> br.getStudent().getStudentId() == student.getStudentId())
                .map(Borrowings::getBook).collect(Collectors.toList());
    }

    public List<Student> findStudentsBorrowingBook(Book book) {
        return getAllBorrowings().stream()
                .filter(br -> br.getBook().getBookId() == book.getBookId())
                .map(Borrowings::getStudent).collect(Collectors.toList());
    }

    /*
     * Can also serve as returning
     */
    public void deleteBorrowings(Borrowings borrowing){
        entityManager.getTransaction().begin();
        entityManager.remove(borrowing);
        entityManager.getTransaction().commit();
    }

    public void borrowBook(Student student, Book book) throws StudentAlreadyBorrowedException {
        final var studentBorrowings = findBooksBorrowedByStudent(student);
        boolean hasBorrowed = studentBorrowings.stream().anyMatch(bk -> bk.getBookId() == bk.getBookId());
        if(hasBorrowed){
            logger.error("Student already has a copy of the book");
            throw new StudentAlreadyBorrowedException();
        }
        Borrowings borrowings = new Borrowings(student, book);
        entityManager.getTransaction().begin();
        entityManager.persist(borrowings);
        entityManager.getTransaction().commit();
    }


    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
