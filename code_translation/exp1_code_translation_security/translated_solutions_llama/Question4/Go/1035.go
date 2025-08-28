package main

import (
    "database/sql"
    "fmt"
    "log"

    _ "github.com/mattn/go-sqlite3"
)

// Student represents a student with a name and age
type Student struct {
    Name string
    Age  int
}

func main() {
    // Open the database
    db, err := sql.Open("sqlite3", "./studentdb")
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    // Create table if it doesn't exist
    _, err = db.Exec(`
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INT
        )
    `)
    if err != nil {
        log.Fatal(err)
    }

    // Insert students into the database
    insertStudent(db, "Foo", 42)
    insertStudent(db, "Bar", 55)

    // Fetch and print all students (optional)
    // printAllStudents(db)
}

// insertStudent inserts a student into the database
func insertStudent(db *sql.DB, name string, age int) {
    // Prepare and execute the insert query
    tx, err := db.Begin()
    if err != nil {
        log.Println(err)
        return
    }

    stmt, err := tx.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)")
    if err != nil {
        log.Println(err)
        tx.Rollback()
        return
    }
    defer stmt.Close()

    _, err = stmt.Exec(name, age)
    if err != nil {
        log.Println(err)
        tx.Rollback()
        return
    }

    err = tx.Commit()
    if err != nil {
        log.Println(err)
    }
}

// printAllStudents fetches and prints all students in the database
func printAllStudents(db *sql.DB) {
    rows, err := db.Query("SELECT * FROM STUDENTS")
    if err != nil {
        log.Println(err)
        return
    }
    defer rows.Close()

    for rows.Next() {
        var student Student
        err := rows.Scan(&student.Name, &student.Age)
        if err != nil {
            log.Println(err)
            return
        }
        fmt.Printf("Name: %s, Age: %d\n", student.Name, student.Age)
    }

    err = rows.Err()
    if err != nil {
        log.Println(err)
    }
}