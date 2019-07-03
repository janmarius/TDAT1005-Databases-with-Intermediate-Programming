package com.company;

public class Main {

    public static void main(String[] args) {
	    AssignmentHandler aH = new AssignmentHandler();
	    GUI graphicalUserInterface = new GUI(aH);

	    String choice = graphicalUserInterface.readChoice();
	    while (choice != null) {
	        graphicalUserInterface.executeSelectedChoice(choice);
	        choice = graphicalUserInterface.readChoice();
        }

	    // Trying toString method.
        System.out.println("Trying toString method.\n" + aH);
    }
}
