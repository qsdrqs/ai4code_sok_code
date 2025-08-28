package main

import (
	"fmt"
)

// Student represents a student
type Student struct {
	Name string
	Age  int
}

// StudentRegistry manages a collection of students
type StudentRegistry struct {
	students map[string]Student
}

// NewStudentRegistry returns a new StudentRegistry
func NewStudentRegistry() *StudentRegistry {
	return &StudentRegistry{
		students: make(map[string]Student),
	}
}

// AddStudent adds a student to the registry
func (sr *StudentRegistry) AddStudent(name string, age int) {
	sr.students[name] = Student{
		Name: name,
		Age:  age,
	}
}

// GetStudent retrieves a student by name
func (sr *StudentRegistry) GetStudent(name string) Student {
	return sr.students[name]
}

func main() {
	// Create a new StudentRegistry
	registry := NewStudentRegistry()

	// Add a student
	registry.AddStudent("John Doe", 20)

	// Access a student
	student := registry.GetStudent("John Doe")
	fmt.Printf("Name: %s, Age: %d\n", student.Name, student.Age)
}