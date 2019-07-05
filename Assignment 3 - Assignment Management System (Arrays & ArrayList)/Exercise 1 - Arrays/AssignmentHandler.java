/**
* AssignmentHandler.java
*
* Assignment 3 - Assignment Management System (Arrays & ArrayList)
*
* Class AssignmentHandler
*/

import java.util.Arrays;

public class AssignmentHandler {
    private Student[] students = new Student[5];
    private int numOfStudents = 0;

    // We only need the default constructor, therefore there is no need to generate one in the code.

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public int getNumOfAssignments(String name) {
        if(name == null || name.trim().isEmpty()) return -1;

        for(Student student : students) {
            if(student != null) {
                if(student.getName().equalsIgnoreCase(name)) return student.getNumOfAssignments();
            }
        }
        return -1;
    }

    public boolean increaseNumOfAssignments(String name, int numOfAssignments) throws IllegalArgumentException {
        if(name == null || name.trim().isEmpty()) return false;

        for(Student student : students) {
            if(student != null) {
                if(student.getName().equalsIgnoreCase(name)) {
                    student.setNumOfAssignments(student.getNumOfAssignments() + numOfAssignments);
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getAllStudentNames() {
        String[] temp = new String[numOfStudents];
        if(numOfStudents == 0) return temp;

        for(int i = 0; i < numOfStudents; i++) {
            temp[i] = students[i].getName();
        }
        return temp;
    }

    public boolean addNewStudent(String name) {
        if(name == null || name.trim().isEmpty()) return false;

        Student newStudent = new Student(name);

        // Checks if student already exists.
        for(int i = 0; i < numOfStudents; i++) {
            if(students[i].equals(newStudent)) return false;
        }

        if(numOfStudents >= students.length) {
            increaseArraySize();
        }

        students[numOfStudents] = newStudent;
        numOfStudents++;
        return true;
    }

    private void increaseArraySize() {
        Student[] temp = new Student[students.length + 5];
        for(int i = 0; i < numOfStudents; i++) {
            temp[i] = students[i];
        }
        students = temp;
    }

    @Override
    public String toString() {
        return "AssignmentHandler{" +
                "students=" + Arrays.toString(students) +
                ", numOfStudents=" + numOfStudents +
                '}';
    }
}
