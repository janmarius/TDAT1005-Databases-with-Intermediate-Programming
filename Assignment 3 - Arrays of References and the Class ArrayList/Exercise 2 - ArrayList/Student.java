package com.company;

public class Student {
    private final String name;
    private int numOfAssignments;

    public Student(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }
        this.name = name;
        this.numOfAssignments = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumOfAssignments() {
        return numOfAssignments;
    }

    public void setNumOfAssignments(int numOfAssignments) {
        if(numOfAssignments < 0) {
            throw new IllegalArgumentException("The number of assignments cannot be negative.");
        }
        this.numOfAssignments = numOfAssignments;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Student)) return false;

        if(this == object) return true;

        Student other = (Student) object;
        return (name.equalsIgnoreCase(other.getName()));
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", numOfAssignments=" + numOfAssignments +
                '}';
    }
}
