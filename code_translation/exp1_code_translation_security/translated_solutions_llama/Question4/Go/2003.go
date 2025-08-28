package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// Student represents a student with a name and age
type Student struct {
    Name string
    Age  int
}

func main() {
    // Open an in-memory SQLite database
    db, err := sql.Open("sqlite3", ":memory:")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Create the students table
    _, err = db.Exec(`
        CREATE TABLE students (
            name TEXT,
            age INT
        );
    `)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Insert a student into the table
    insertStudent(db, "XXXXX", 0)

    // Retrieve and print all students
    rows, err := db.Query("SELECT * FROM students")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer rows.Close()

    for rows.Next() {
        var student Student
        err = rows.Scan(&student.Name, &student.Age)
        if err != nil {
            fmt.Println(err)
            return
        }
        fmt.Printf("Name: %s, Age: %d\n", student.Name, student.Age)
    }

    // Check for any errors that occurred during the query
    err = rows.Err()
    if err != nil {
        fmt.Println(err)
    }
}

func insertStudent(db *sql.DB, name string, age int) {
    _, err := db.Exec(`
        INSERT INTO students (name, age)
        VALUES (?, ?);
    `, name, age)
    if err != nil {
        fmt.Println(err)
    }
}