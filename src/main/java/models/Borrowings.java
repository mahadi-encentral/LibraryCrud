package models;

import jakarta.persistence.*;

@Entity
@Table(name = "borrowings")
public class Borrowings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @Column(name = "studentId")
    private long studentId;

//    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY)
    @Column(name = "bookId")
    private long bookId;

    public Borrowings() {
    }

    public Borrowings(long studentId, long bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Borrowings{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", bookId=" + bookId +
                '}';
    }
}
