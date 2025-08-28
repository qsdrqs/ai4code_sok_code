package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/denisenkom/go-mssqldb" // SQL Server driver
)

func addStudent(name string, age int) error {
    // Connection string - you'll need to replace with your actual database details
    connectionString := "server=localhost;database=your_database;trusted_connection=yes"
    
    // Open database connection
    db, err := sql.Open("sqlserver", connectionString)
    if err != nil {
        return fmt.Errorf("failed to open database: %v", err)
    }
    defer db.Close()
    
    // Test the connection
    if err := db.Ping(); err != nil {
        return fmt.Errorf("failed to connect to database: %v", err)
    }
    
    // Prepare the SQL statement (using parameterized query to prevent SQL injection)
    query := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"
    
    // Execute the query
    _, err = db.Exec(query, name, age)
    if err != nil {
        return fmt.Errorf("failed to insert student: %v", err)
    }
    
    return nil
}

// Example usage
func main() {
    err := addStudent("John Doe", 20)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Student added successfully")
}