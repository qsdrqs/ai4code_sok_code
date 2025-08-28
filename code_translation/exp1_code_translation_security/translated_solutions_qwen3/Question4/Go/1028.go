package main

import (
    "database/sql"
    "fmt"
    "log"
)

// Assuming a global database connection is available
var db *sql.DB

func addStudent(name string, age int) {
    // Construct the SQL query using string formatting
    sqlStr := fmt.Sprintf("INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age)

    // Execute the query
    _, err := db.Exec(sqlStr)
    if err != nil {
        // Log and terminate on error, similar to throwing an error in JS
        log.Fatal(err)
    }

    // Log the successful insertion
    fmt.Println("1 record inserted")
}