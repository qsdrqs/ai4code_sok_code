package main

import (
    "database/sql"
    "fmt"
    _ "github.com/go-sql-driver/mysql"
)

// AddStudent inserts a new student into the STUDENTS table.
func AddStudent(name string, age int) {
    // Create a connection to the MySQL database
    db, err := sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/mydb")
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to establish a connection
    if err := db.Ping(); err != nil {
        panic(err)
    }
    fmt.Println("Connected!")

    // Construct the SQL query using string formatting
    sqlStr := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", name, age)

    // Execute the query
    _, err = db.Exec(sqlStr)
    if err != nil {
        panic(err)
    }

    // Log the successful insertion
    fmt.Println("1 record inserted")
}