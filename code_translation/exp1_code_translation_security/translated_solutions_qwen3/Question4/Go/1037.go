package main

import (
    "database/sql"
    _ "github.com/go-sql-driver/mysql"
)

// insertStudent inserts a fixed record into the STUDENTS table.
// Note: The parameters 'name' and 'age' are not used in this function,
// as in the original JavaScript code.
func insertStudent(name string, age int) {
    // DSN (Data Source Name) - Replace with your actual MySQL credentials
    dsn := "user:password@tcp(127.0.0.1:3306)/dbname"

    // Open a new connection to the database
    db, err := sql.Open("mysql", dsn)
    if err != nil {
        panic(err)
    }
    defer db.Close()

    // Ping the database to ensure the connection is established
    if err := db.Ping(); err != nil {
        panic(err)
    }

    // Fixed SQL insert statement
    sqlStmt := `
        INSERT INTO STUDENTS (title, completed)
        VALUES ('Learn how to insert a new row', true)
    `

    // Execute the SQL statement
    _, err = db.Exec(sqlStmt)
    if err != nil {
        panic(err)
    }
}

// main function to invoke the insertStudent function
func main() {
    insertStudent("XXXXX", 0)
}