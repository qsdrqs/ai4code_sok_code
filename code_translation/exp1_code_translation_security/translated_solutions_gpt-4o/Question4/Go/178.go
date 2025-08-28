package main

import (
	"database/sql"
	"fmt"
	"log"
)

// Assuming a Connection struct is defined somewhere in your dependencies
type Connection struct {
	db *sql.DB
}

// NewConnection creates a new database connection
func NewConnection() (*Connection, error) {
	// Replace with your actual database connection details
	dsn := "user:password@tcp(localhost:3306)/dbname"
	db, err := sql.Open("mysql", dsn) // or "postgres" for PostgreSQL
	if err != nil {
		return nil, err
	}
	return &Connection{db: db}, nil
}

// Query executes a query on the database
func (c *Connection) Query(query string) (sql.Result, error) {
	return c.db.Exec(query)
}

func registerStudent(name string, age int) (sql.Result, error) {
	student := struct {
		name string
		age  int
	}{
		name: name,
		age:  age,
	}

	connection, err := NewConnection()
	if err != nil {
		log.Fatalf("Failed to create connection: %v", err)
		return nil, err
	}
	defer connection.db.Close()

	query := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", student.name, student.age)
	return connection.Query(query)
}

func main() {
	// Example usage
	result, err := registerStudent("John Doe", 20)
	if err != nil {
		log.Fatalf("Failed to register student: %v", err)
	}
	fmt.Println("Student registered successfully:", result)
}