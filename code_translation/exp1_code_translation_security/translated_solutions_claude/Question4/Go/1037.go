package main

import (
    "database/sql"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

// Assuming config would be defined somewhere like this
var config = "username:password@tcp(localhost:3306)/database_name"

func insertStudent(name string, age int) {
    // Create connection
    connection, err := sql.Open("mysql", config)
    if err != nil {
        log.Fatal(err)
    }
    defer connection.Close()
    
    // Insert statement
    sql := `INSERT INTO STUDENTS(title,completed)
            VALUES('Learn how to insert a new row',true)`
    
    // Execute the insert statement
    _, err = connection.Exec(sql)
    if err != nil {
        log.Fatal(err)
    }
}

func main() {
    insertStudent("XXXXX", 0)
}