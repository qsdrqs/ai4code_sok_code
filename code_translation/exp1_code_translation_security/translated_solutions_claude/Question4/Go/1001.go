package main

import (
    "database/sql"
    "fmt"
    "log"
    _ "github.com/mattn/go-sqlite3"
)

// Assuming db is a global variable or passed as parameter
var db *sql.DB

func addSQLentry(name string, age int) {
    sqlStatement := "INSERT INTO students (name, age) VALUES (?, ?)"
    
    result, err := db.Exec(sqlStatement, name, age)
    if err != nil {
        log.Println(err)
        return
    }
    
    lastID, err := result.LastInsertId()
    if err != nil {
        log.Println(err)
        return
    }
    
    fmt.Printf("User added with id: %d\n", lastID)
}