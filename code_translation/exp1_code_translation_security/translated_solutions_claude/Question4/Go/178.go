package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // or appropriate database driver
)

// Student represents a student structure
type Student struct {
    Name string
    Age  int
}

// Connection represents a database connection
type Connection struct {
    db *sql.DB
}

// NewConnection creates a new database connection
func NewConnection() (*Connection, error) {
    // Replace with your actual database connection string
    db, err := sql.Open("mysql", "user:password@tcp(localhost:3306)/database")
    if err != nil {
        return nil, err
    }
    
    return &Connection{db: db}, nil
}

// Query executes a query and returns the result
func (c *Connection) Query(query string) (sql.Result, error) {
    return c.db.Exec(query)
}

// Close closes the database connection
func (c *Connection) Close() error {
    return c.db.Close()
}

// registerStudent registers a new student in the database
func registerStudent(name string, age int) (sql.Result, error) {
    student := Student{
        Name: name,
        Age:  age,
    }
    
    connection, err := NewConnection()
    if err != nil {
        return nil, err
    }
    defer connection.Close() // Important: close connection when done
    
    query := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", student.Name, student.Age)
    return connection.Query(query)
}