package main

import "fmt"

// =================================================================
// Dependencies (provided as requested)
// In Go, we define explicit types for our data structures.
// =================================================================

// 1. Define the structure for a single student.
type Student struct {
	Name string
	Age  int
}

// 2. Define a custom type for a list of students. This allows us
// to attach methods to it, like 'Add'.
type StudentList []Student

// 3. Define the 'Add' method for our StudentList.
// It uses a pointer receiver (*StudentList) because it needs to modify the
// original slice it's called on.
func (sl *StudentList) Add(name string, age int) {
	newStudent := Student{Name: name, Age: age}
	*sl = append(*sl, newStudent)
}

// 4. Declare the package-level variable, which is the Go equivalent
// of the global 'STUDENTS' object from the JavaScript example.
var STUDENTS StudentList

// =================================================================
// Translated Function
// =================================================================

/*
Original JavaScript:
function myFunction(name, age){
  STUDENTS.add(name, age);
}
*/

// Go Translation:
// Functions are declared with the 'func' keyword.
// Parameters must have explicit types (e.g., name string, age int).
func myFunction(name string, age int) {
	STUDENTS.Add(name, age)
}

// =================================================================
// Example Usage
// The main function is the entry point for a Go program.
// =================================================================

func main() {
	fmt.Println("Student list before calling myFunction:", STUDENTS)

	// Call our translated function
	myFunction("Alice", 21)
	myFunction("Bob", 22)

	fmt.Println("Student list after calling myFunction:", STUDENTS)

	// You can see the students have been added to the list
	// Output:
	// Student list before calling myFunction: []
	// Student list after calling myFunction: [{Alice 21} {Bob 22}]
}