package drivers;

import models.Student;
import repositories.StudentRepository;

import java.util.Scanner;

import static drivers.GeneralMenu.showMenu;

public class StudentDriver {
    private final static StudentRepository studentRepository = new StudentRepository();
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String[] studentMenus = {
                "List Students", "Find Student", "Add Student", "Update Student", "Delete Student", "Exit"
        };

        int choice = 0;
        while (choice != studentMenus.length){
            choice = showMenu(studentMenus, in);


            switch (choice){
                case 1:
                    listStudents();
                    break;
                case 2:
                    findStudent();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    studentRepository.close();
                    in.close();
                    System.out.println("\nGood Bye");
                    break;
            }
        }
    }

    static void listStudents(){
        final var students = studentRepository.getAllStudents();
        students.forEach(System.out::println);
    }

    static void addStudent(){
        System.out.print("Enter Student's Name: ");
        String name = in.nextLine();

        System.out.print("Enter Student's Level: ");
        int level = in.nextInt();
        in.nextLine();

        studentRepository.createStudent(new Student(name, level));
        System.out.println("Student Added Successfully");

    }

    static Student findStudent(){
        System.out.print("Enter student's id: ");
        Student student = studentRepository.findById(in.nextLong());
        if(student != null)
            System.out.println("\n" + student);
        else{
            System.out.println("Student not found");
        }
        in.nextLine();
        return student;
    }

    static void updateStudent(){
        Student student = findStudent();
        String temp;
        if(student == null){
            return;
        }
        System.out.print("Name (Enter to Skip): ");
        temp = in.nextLine();
        if(!temp.strip().equals("")) student.setName(temp);

        System.out.print("Level (Enter to Skip): ");
        temp = in.nextLine();
        if(!temp.strip().equals("")) student.setLevel(Integer.parseInt(temp));


        studentRepository.updateStudent(student);
        System.out.println("\nStudent Updated Successfully");
    }

    static void deleteStudent(){
        Student student = findStudent();
        if(student != null){
            studentRepository.deleteStudent(student);
            System.out.println("\nStudent Deleted Successfully");
        }
    }
}
