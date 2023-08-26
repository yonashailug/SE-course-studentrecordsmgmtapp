import model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyStudentRecordsMgmtApp {
    public static void main(String[] args) {
        Student[] students = new Student[5];

        students[0] = new Student(110001, "Dave", parseDate("11/18/1951"));
        students[1] = new Student(110002, "Anna", parseDate("12/07/1990"));
        students[2] = new Student(110003, "Erica", parseDate("01/31/1974"));
        students[3] = new Student(110004, "Carlos", parseDate("08/22/2009"));
        students[4] = new Student(110005, "Bob", parseDate("03/05/1990"));

        printListOfStudents(students);

        List<Student> platinumAlumni = getListOfPlatinumAlumniStudents(students);
        for (Student student : platinumAlumni) {
            System.out.println(student);
        }

        int[] numbers = {10, 14, 35, 70, 100, 105, 49};

        printHelloWorld(numbers);


        int[] numbers1 = {1, 2, 3, 4, 5};
        int[] numbers2 = {19, 9, 11, 0, 12};

        System.out.println(findSecondBiggest(numbers1));
        System.out.println(findSecondBiggest(numbers2));
    }

    public static void printListOfStudents(Student[] students) {
        Arrays.sort(students, Comparator.comparing(Student::getName));

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static List<Student> getListOfPlatinumAlumniStudents(Student[] students) {
        List<Student> platinumAlumni = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Student student : students) {
            if (today.minusYears(30).isAfter(student.getDateOfAdmission())) {
                platinumAlumni.add(student);
            }
        }

        platinumAlumni.sort(Comparator.comparing(Student::getDateOfAdmission).reversed());

        return platinumAlumni;
    }

    private static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static void printHelloWorld(int[] numbers) {
        for (int num : numbers) {
            if (num % 5 == 0 && num % 7 == 0) {
                System.out.println("HelloWorld");
            } else if (num % 5 == 0) {
                System.out.println("Hello");
            } else if (num % 7 == 0) {
                System.out.println("World");
            }
        }
    }

    public static int findSecondBiggest(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("Input array should have at least two elements.");
        }

        int biggest = Integer.MIN_VALUE;
        int secondBiggest = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > biggest) {
                secondBiggest = biggest;
                biggest = num;
            } else if (num > secondBiggest && num < biggest) {
                secondBiggest = num;
            }
        }

        return secondBiggest;
    }
}