package main

import (
    "fmt"
)

// Student represents a student with a name and age
type Student struct {
    Name string
    Age  int
}

// STUDENTS is a slice to hold all students
var STUDENTS []Student

// addStudent adds a new student to the STUDENTS slice
func addStudent(name string, age int) {
    student := Student{
        Name: name,
        Age:  age,
    }
    STUDENTS = append(STUDENTS, student)
}

func main() {
    // Example usage
    addStudent("John Doe", 20)
    addStudent("Jane Doe", 22)
    
    // Print all students
    for _, student := range STUDENTS {
        fmt.Printf("Name: %s, Age: %d\n", student.Name, student.Age)
    }
}