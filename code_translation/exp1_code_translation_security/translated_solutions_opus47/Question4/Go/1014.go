package main

// Student represents a student entry with a name and age
type Student struct {
    NAME string
    AGE  int
}

// STUDENTS is the global slice holding all student entries
var STUDENTS []Student

/*
Go function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
*/
func addStudent(name string, age int) {
    student := Student{
        NAME: name,
        AGE:  age,
    }
    STUDENTS = append(STUDENTS, student)
}