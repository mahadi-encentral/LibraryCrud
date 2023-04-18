package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Student;

import java.util.List;

public class StudentRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public StudentRepository(){
        entityManagerFactory = Persistence.createEntityManagerFactory("library_crud");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public long createStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student.getStudentId();
    }

    public List<Student> getAllStudents(){
        return  entityManager.createNamedQuery("findAllStudents", Student.class).getResultList();
    }

    public Student findById(long studentId){
        return entityManager.find(Student.class, studentId);
    }

    public Student updateStudent(Student student){
        entityManager.getTransaction().begin();
        Student template = findById(student.getStudentId());
        template.updateStudent(student);
        entityManager.getTransaction().commit();
        return template;
    }

    public void deleteStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }


    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
