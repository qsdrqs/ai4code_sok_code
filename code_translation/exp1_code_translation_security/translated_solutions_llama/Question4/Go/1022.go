package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// Student represents a student entity
type Student struct {
    Name string
    Age  int
}

func main() {
    // Open the database connection
    db, err := sql.Open("sqlite3", "./database.db")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Create table if it doesn't exist
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INTEGER
        )
    `)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Insert a student
    studentWrite(db, "XXXXX", 0)

    // Retrieve all students
    students, err := studentReadAll(db)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Print students
    for _, student := range students {
        fmt.Printf("Name: %s, Age: %d\n", student.Name, student.Age)
    }
}

// studentWrite inserts a student into the database
func studentWrite(db *sql.DB, name string, age int) {
    _, err := db.Exec("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", name, age)
    if err != nil {
        fmt.Println(err)
    }
}

// studentReadAll retrieves all students from the database
func studentReadAll(db *sql.DB) ([]Student, error) {
    rows, err := db.Query("SELECT * FROM STUDENTS")
    if err != nil {
        return nil, err
    }
    defer rows.Close()

    var students []Student
    for rows.Next() {
        var student Student
        err := rows.Scan(&student.Name, &student.Age)
        if err != nil {
            return nil, err
        }
        students = append(students, student)
    }

    return students, rows.Err()
}