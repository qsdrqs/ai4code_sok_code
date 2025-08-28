package main

// Student mirrors the structure of the JavaScript object.
type Student struct {
	NAME string
	AGE  int
}

// STUDENTS plays the same role as the JS array it replaces.
var STUDENTS []Student

// addStudent replicates the JavaScript addStudent function.
func addStudent(name string, age int) {
	student := Student{
		NAME: name,
		AGE:  age,
	}
	STUDENTS = append(STUDENTS, student)
}