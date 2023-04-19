package models;


import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    public Book() {
    }

    @Column(name = "isbn")
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String isbn;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "published_date")
    private String datePublished;

    public Book(String title, String author, String datePublished) {
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public void updateBook(Book book){
        title = book.getTitle();
        isbn = book.getIsbn();
        author = book.getAuthor();
        datePublished = book.getDatePublished();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", datePublished='" + datePublished + '\'' +
                '}';
    }
}
