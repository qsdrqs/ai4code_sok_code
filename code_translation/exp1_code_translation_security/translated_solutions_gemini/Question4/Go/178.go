package main

import (
	"database/sql"
	"fmt"
)

// Student represents the structure for a student's data.
// In Go, we typically define structs for data objects.
type Student struct {
	Name string
	Age  int
}

// Connection is a mock database connection struct.
// In a real application, this would hold a connection pool from a library
// like `database/sql`.
type Connection struct {
	// In a real scenario, this would hold the *sql.DB object
	// db *sql.DB
}

// NewConnection acts as a constructor to create a new Connection instance.
// This is a common pattern in Go, replacing JavaScript's `new` keyword.
func NewConnection() *Connection {
	// In a real app, you would establish the DB connection here.
	// For this example, we just return a new instance of our mock struct.
	return &Connection{}
}

// Query is a mock method to simulate executing a database query.
// Note: The idiomatic Go way is to accept the query string and arguments separately
// to prevent SQL injection. The return signature (sql.Result, error) is standard.
func (c *Connection) Query(query string, args ...interface{}) (sql.Result, error) {
	fmt.Println("Executing Query:")
	fmt.Printf("  SQL: %s\n", query)
	fmt.Printf("  Args: %v\n", args)

	// In a real application, you would execute the query against the database.
	// For example: return c.db.Exec(query, args...)
	// Here, we return a nil result and no error to simulate success.
	return nil, nil
}

// RegisterStudent registers a new student in the database.
// Go functions require explicit types for parameters and return values.
// It's idiomatic to return an error as the last value.
func RegisterStudent(name string, age int) (sql.Result, error) {
	// Create an instance of the Student struct
	student := Student{
		Name: name,
		Age:  age,
	}

	// Create a new connection instance
	connection := NewConnection()

	// IMPORTANT: Use parameterized queries to prevent SQL injection.
	// The database driver replaces the '?' placeholders with the arguments safely.
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// Execute the query, passing the student's data as separate arguments.
	// This is the secure way to handle database operations.
	return connection.Query(query, student.Name, student.Age)
}

// main function to run the example
func main() {
	// Call the function to register a student
	_, err := RegisterStudent("Alice", 21)

	// In Go, you must always check for errors.
	if err != nil {
		fmt.Printf("Failed to register student: %v\n", err)
		return
	}

	fmt.Println("\nStudent registered successfully!")
}