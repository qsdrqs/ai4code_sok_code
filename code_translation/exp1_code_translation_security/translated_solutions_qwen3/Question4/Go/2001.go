package main

import (
    "database/sql"
    "fmt"
    "log"

    _ "github.com/go-sql-driver/mysql"
)

var db *sql.DB

func add(name string, age int) {
    query := "INSERT INTO students (name, age) VALUES (?, ?)"
    result, err := db.Exec(query, name, age)
    if err != nil {
        log.Fatal(err)
    }
    // Optional: Check the result if needed
    fmt.Printf("Added %s to students table\n", name)
}