package main

import (
    "database/sql"
    "errors"
    "fmt"

    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Student represents a student
type Student struct {
    Name string
    Age  int
}

// RegisterStudent registers a student in the database
func RegisterStudent(name string, age int) error {
    // Establish a connection to the database
    db, err := sql.Open("mysql", "user:password@tcp(localhost:3306)/database")
    if err != nil {
        return err
    }
    defer db.Close()

    // Prepare the SQL query
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

    // Execute the query with the student details
    _, err = db.Exec(query, name, age)
    if err != nil {
        return err
    }

    return nil
}

func main() {
    // Example usage
    name := "John Doe"
    age := 20
    err := RegisterStudent(name, age)
    if err != nil {
        fmt.Println("Error registering student:", err)
        return
    }
    fmt.Println("Student registered successfully")
}