package main

import (
    "database/sql"
    "fmt"
    "log"
    _ "github.com/mattn/go-sqlite3"
)

// Global database variable (similar to the JavaScript example)
var db *sql.DB

// addStudent takes a name and age and adds a new entry to STUDENTS table
// Returns the number of rows affected
func addStudent(name string, age int) int64 {
    sqlStatement := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    
    result, err := db.Exec(sqlStatement)
    if err != nil {
        log.Printf("Error executing SQL: %v", err)
        return 0
    }
    
    rowsAffected, err := result.RowsAffected()
    if err != nil {
        log.Printf("Error getting rows affected: %v", err)
        return 0
    }
    
    // Print result (similar to console.log in JavaScript)
    fmt.Printf("Rows affected: %d\n", rowsAffected)
    
    return rowsAffected
}

func main() {
    // Test example
    var err error
    
    // Create sql database
    db, err = sql.Open("sqlite3", "./database.db")
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()
    
    // Create table (only do this once!)
    // createTableSQL := "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)"
    // _, err = db.Exec(createTableSQL)
    // if err != nil {
    //     log.Fatal(err)
    // }
    
    addStudent("XXXXX", 0)
    
    // Display contents of database
    rows, err := db.Query("SELECT * FROM STUDENTS")
    if err != nil {
        log.Fatal(err)
    }
    defer rows.Close()
    
    for rows.Next() {
        var name string
        var age int
        err = rows.Scan(&name, &age)
        if err != nil {
            log.Fatal(err)
        }
        fmt.Printf("%s %d\n", name, age)
    }
    
    if err = rows.Err(); err != nil {
        log.Fatal(err)
    }
}