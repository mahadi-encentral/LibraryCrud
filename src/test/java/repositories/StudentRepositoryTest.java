package repositories;

import models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new StudentRepository();
    }

    @AfterEach
    void tearDown() {
        repository.close();
        repository = null;
    }

    @Test
    void createStudent() {
        Student temp = new Student("Student Create", 3);
        long newId = repository.createStudent(temp);
        assertNotEquals(0,newId);
        Student saved = repository.findById(newId);
        assertEquals(temp.getName(), saved.getName());
        assertEquals(temp.getLevel(), saved.getLevel());
    }

    @Test
    void getAllStudents() {
        for(int i=0; i< 4; i++)
            repository.createStudent(new Student("Student " + (i+1), i+1));
        assertTrue(repository.getAllStudents().size() >= 4);
    }


    @Test
    void updateStudent() {
        Student temp = new Student("Update Student", 3);
        long newId = repository.createStudent(temp);
        Student saved = repository.findById(newId);
        saved.setName("Name Updated");
        saved.setLevel(1);
        repository.updateStudent(saved);

        Student updated = repository.findById(saved.getStudentId());
        assertNotEquals(updated.getName(), "Update Student");
        assertEquals(updated.getName(), saved.getName());
        assertEquals(updated.getLevel(), saved.getLevel());

    }
    @Test
    void deleteStudent() {
        Student temp = new Student("Update Student", 3);
        long newId = repository.createStudent(temp);
        Student saved = repository.findById(newId);
        assertNotEquals(null, saved);

        repository.deleteStudent(saved);

        Student deleted = repository.findById(newId);
        assertNull(deleted);
    }


}