package main

import (
    "database/sql"
    "fmt"
    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// addStudent adds a new student entry to the "STUDENTS" table.
func addStudent(db *sql.DB, name string, age int) (int64, error) {
    sqlStmt := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    result, err := db.Exec(sqlStmt, name, age)
    if err != nil {
        return 0, err
    }
    return result.RowsAffected()
}

func main() {
    // Open the database.
    db, err := sql.Open("sqlite3", "./database.db")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer db.Close()

    // Create table if it doesn't exist (only do this once!).
    // sqlStmt := "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)"
    // _, err = db.Exec(sqlStmt)
    // if err != nil {
    //     fmt.Println(err)
    //     return
    // }

    // Add a new student.
    name := "XXXXX"
    age := 0
    rowsAffected, err := addStudent(db, name, age)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Rows affected:", rowsAffected)

    // Display contents of database.
    rows, err := db.Query("SELECT * FROM STUDENTS")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer rows.Close()

    for rows.Next() {
        var (
            name string
            age  int
        )
        err = rows.Scan(&name, &age)
        if err != nil {
            fmt.Println(err)
            return
        }
        fmt.Println(name, age)
    }
}