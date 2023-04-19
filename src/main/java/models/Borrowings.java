package models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowings")
@NamedQueries({
        @NamedQuery(name = "findAllBorrowings", query = "SELECT br FROM Borrowings br"),
//        @NamedQuery(
//                name = "getStudentBorrowings",
//                query = "SELECT b FROM Borrowings br LEFT JOIN Book b WHERE br.studentId = :id"
//        ),
//        @NamedQuery(
//                name = "findBookBorrowers",
//                query = "Select br FROM Borrowings br WHERE br.bookId = :id"
//        )
})
public class Borrowings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book book;

    @Column(name = "dateBorrowed")
    private final LocalDate dateBorrowed;

    public Borrowings() {
        dateBorrowed = LocalDate.now();
    }

    public Borrowings(Student student, Book book) {
        this();
        this.student = student;
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Borrowings{" +
                "id=" + id +
                ", student=" + student +
                ", book=" + book +
                ", dateBorrowed=" + dateBorrowed +
                '}';
    }

}
