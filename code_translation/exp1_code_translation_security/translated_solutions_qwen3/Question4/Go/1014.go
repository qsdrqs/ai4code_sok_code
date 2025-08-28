package main

// Define a struct to represent a student
type Student struct {
    NAME string
    AGE  int
}

// Global slice to hold all students
var STUDENTS []Student

// Function to add a new student to the global STUDENTS slice
func addStudent(name string, age int) {
    student := Student{
        NAME: name,
        AGE:  age,
    }
    STUDENTS = append(STUDENTS, student)
}