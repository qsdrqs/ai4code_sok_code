package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Student represents a student
type Student struct {
    Name string
    Age  int
}

// AddStudent adds a student to the students table in MySQL
func AddStudent(db *sql.DB, student Student) error {
    // SQL query to add a student
    query := "INSERT INTO students (name, age) VALUES (?, ?)"

    // Execute the query
    _, err := db.Exec(query, student.Name, student.Age)
    if err != nil {
        return err
    }

    fmt.Printf("Added %s to students table\n", student.Name)
    return nil
}

func main() {
    // MySQL connection details
    const (
        dbUser     = "your_username"
        dbPassword = "your_password"
        dbHost     = "your_host"
        dbName     = "your_database"
    )

    // Establish a connection to MySQL
    dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", dbUser, dbPassword, dbHost, dbName)
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Create a student
    student := Student{
        Name: "John Doe",
        Age:  20,
    }

    // Add the student to the database
    err = AddStudent(db, student)
    if err != nil {
        panic(err)
    }
}