package main

import (
    "database/sql"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

// Assuming connection is a global variable of type *sql.DB
var connection *sql.DB

func addStudent(name string, age int) {
    q := `INSERT INTO STUDENTS(NAME, AGE)
          VALUES(?,?)`
    
    _, err := connection.Exec(q, name, age)
    if err != nil {
        log.Printf("Error executing query: %v", err)
    }
}