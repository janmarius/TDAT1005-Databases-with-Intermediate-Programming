/**
* AssignmentHandler.java
*
* Assignment 3 - Assignment Management System (Arrays & ArrayList)
*
* Class AssignmentHandler
*/

import java.util.ArrayList;

public class AssignmentHandler {
    private ArrayList<Student> students = new ArrayList<Student>();

    // We only need the default constructor, therefore there is no need to generate one in the code.

    public int getNumOfStudents() {
        return students.size();
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
        String[] temp = new String[students.size()];
        if(students.size() == 0) return temp;

        for(int i = 0; i < students.size(); i++) {
            temp[i] = students.get(i).getName();
        }
        return temp;
    }

    public boolean addNewStudent(String name) {
        if(name == null || name.trim().isEmpty()) return false;

        Student newStudent = new Student(name);

        // Checks if student already exists.
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).equals(newStudent)) return false;
        }

        students.add(newStudent);
        return true;
    }

    @Override
    public String toString() {
        return "AssignmentHandler{" +
                "students=" + students +
                '}';
    }
}
