package exceptions;

public class StudentAlreadyBorrowedException extends Exception{
    public StudentAlreadyBorrowedException() {
        this("Student Already Borrowed this book!");
    }
    public StudentAlreadyBorrowedException(String message) {
        super(message);
    }
}
