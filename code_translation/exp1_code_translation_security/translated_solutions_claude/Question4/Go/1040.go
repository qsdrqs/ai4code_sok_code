package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/mattn/go-sqlite3"
)

func main() {
    // Create in-memory SQLite database
    db, err := sql.Open("sqlite3", ":memory:")
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    addNewEntry(db, "XXXXX", 0)
    addNewEntry(db, "XXXXX", 0)
    
    // Query and print results
    rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
    if err != nil {
        log.Fatal(err)
    }
    defer rows.Close()
    
    for rows.Next() {
        var name string
        var age int
        err := rows.Scan(&name, &age)
        if err != nil {
            log.Fatal(err)
        }
        fmt.Printf("map[AGE:%d NAME:%s]\n", age, name)
    }
    
    if err := rows.Err(); err != nil {
        log.Fatal(err)
    }
}

func addNewEntry(db *sql.DB, name string, age int) {
    // Create table if it doesn't exist
    _, err := db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
    if err != nil {
        log.Fatal(err)
    }
    
    // Prepare and execute insert statement
    stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?, ?)")
    if err != nil {
        log.Fatal(err)
    }
    defer stmt.Close()
    
    _, err = stmt.Exec(name, age)
    if err != nil {
        log.Fatal(err)
    }
}