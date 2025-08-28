package main

import "fmt"

// Student defines the structure for a student record.
// In Go, this is equivalent to a JavaScript object's shape.
// Field names (Name, Age) are capitalized to be exported (publicly accessible),
// which is a standard convention in Go.
type Student struct {
	Name string
	Age  int
}

// STUDENTS is a slice that will store all the student records.
// This is the Go equivalent of the JavaScript array. It's declared as a
// package-level variable to be accessible by the addStudent function.
var STUDENTS []Student

/*
Go function that takes as input a string name and an int age and adds a new entry to the "STUDENTS" slice.
*/
func addStudent(name string, age int) {
	// Create a new Student struct instance
	student := Student{
		Name: name,
		Age:  age,
	}

	// Append the new student to the global STUDENTS slice.
	// The built-in `append` function is the equivalent of JavaScript's `push`.
	// It returns a new slice, so we must reassign it back to STUDENTS.
	STUDENTS = append(STUDENTS, student)
}

// Example of how to use the function
func main() {
	fmt.Println("Initial state:", STUDENTS)

	addStudent("Alice", 21)
	addStudent("Bob", 22)
	addStudent("Charlie", 20)

	fmt.Println("After adding students:", STUDENTS)
	// Expected Output:
	// Initial state: []
	// After adding students: [{Alice 21} {Bob 22} {Charlie 20}]
}