package main

// Student represents a student with name and age
type Student struct {
    NAME string
    AGE  int
}

// Global slice to store students (equivalent to JavaScript array)
var STUDENTS []Student

// addStudent takes a string name and int age and adds a new entry to STUDENTS
func addStudent(name string, age int) {
    student := Student{
        NAME: name,
        AGE:  age,
    }
    STUDENTS = append(STUDENTS, student)
}