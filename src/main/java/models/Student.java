package models;


import jakarta.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "findAllStudents", query = "SELECT s FROM Student s")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "level")
    private int level;

    public Student(){}

    public Student(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
