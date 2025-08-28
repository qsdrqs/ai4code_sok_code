package main

import "fmt"

// Define a struct to represent a student
type Student struct {
	NAME string
	AGE  int
}

// Define a slice to hold the students
var STUDENTS []Student

// Function to add a new student
func addStudent(name string, age int) {
	student := Student{
		NAME: name,
		AGE:  age,
	}
	STUDENTS = append(STUDENTS, student)
}

func main() {
	// Example usage
	addStudent("Alice", 20)
	addStudent("Bob", 22)

	// Print the students
	fmt.Println(STUDENTS)
}