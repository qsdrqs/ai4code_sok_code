package main

import "fmt"

// Assuming STUDENTS is a struct that can store student information
type StudentCollection struct {
    students []Student
}

type Student struct {
    Name string
    Age  int
}

// Method to add a student to the collection
func (sc *StudentCollection) Add(name string, age int) {
    student := Student{
        Name: name,
        Age:  age,
    }
    sc.students = append(sc.students, student)
}

// Global variable equivalent to STUDENTS in JavaScript
var STUDENTS = &StudentCollection{}

// Translated function
func myFunction(name string, age int) {
    STUDENTS.Add(name, age)
}

// Example usage
func main() {
    myFunction("John", 20)
    myFunction("Jane", 22)
    
    // Print to verify it works
    for _, student := range STUDENTS.students {
        fmt.Printf("Name: %s, Age: %d\n", student.Name, student.Age)
    }
}