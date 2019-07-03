/**
* GUI.java
*
* Assignment 3 - Arrays of References and the Class ArrayList
*
* Class GUI
*/

import static javax.swing.JOptionPane.*;

public class GUI {
    public final String NEW_STUDENT = "New student";
    public final String EXIT = "Exit";
    private String[] options = {NEW_STUDENT, EXIT};
    private AssignmentHandler aH;

    public GUI(AssignmentHandler aH) {
        this.aH = aH;
    }

    public String readChoice() {
        int numOfStudents = aH.getNumOfStudents();
        String choice = (String) showInputDialog(null, "Choose from list, " + numOfStudents +
                            " students:", "Approved assignments", DEFAULT_OPTION, null, options, options[0]);

        if(EXIT.equals(choice)) choice = null;

        return choice;
    }

    public void executeSelectedChoice(String choice) {
        if(choice != null && !choice.equals(EXIT)) {
            if(choice.equals(NEW_STUDENT)) {
                addNewStudent();
            } else {
                increaseNumOfAssignments(choice);
            }
        }
    }

    private void addNewStudent() {
        String nameNewStudent = null;
        do {
            nameNewStudent = showInputDialog("Enter name: ");
        } while(nameNewStudent == null);

        nameNewStudent = nameNewStudent.trim();

        if(aH.addNewStudent(nameNewStudent)) {
            showMessageDialog(null, nameNewStudent + " is added.");
            String[] allNames = aH.getAllStudentNames();
            String[] newOptions = new String[allNames.length + 2];

            for(int i = 0; i < allNames.length; i++) {
                newOptions[i] = allNames[i];
            }

            newOptions[allNames.length] = NEW_STUDENT;
            newOptions[allNames.length + 1] = EXIT;
            options = newOptions;
        } else {
            showMessageDialog(null, nameNewStudent + " is already in the system.");
        }
    }

    private void increaseNumOfAssignments(String name) {
        String message = "Enter the number of new assignments to be approved for " + name + ": ";
        int numOfAssignments = 0;
        boolean registered = false;

        do {
            try {
                numOfAssignments = readInteger(message);
                aH.increaseNumOfAssignments(name, numOfAssignments);
                registered = true;
            } catch (IllegalArgumentException e) {
                message = "Invalid input. Please try again!";
            }
        } while (!registered);

        message = "Total number of assignments approved for " + name + " is " + aH.getNumOfAssignments(name) + ".";
        showMessageDialog(null, message);
    }

    private int readInteger(String message) {
        int number = 0;
        boolean ok = false;
        do {
            String numberRead = showInputDialog(message);
            try {
                number = Integer.parseInt(numberRead);
                ok = true;
            } catch (Exception e) {
                showMessageDialog(null, "Invalid input. Please try again!");
            }
        } while(!ok);
        return number;
    }
}
