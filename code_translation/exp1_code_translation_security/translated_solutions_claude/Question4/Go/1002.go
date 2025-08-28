package main

import (
    "context"
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/denisenkom/go-mssqldb"
)

// insert_user inserts a user into the STUDENTS table
func insert_user(conn *sql.DB, name string, age int) {
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE)"
    
    _, err := conn.ExecContext(context.Background(), query, 
        sql.Named("NAME", name),
        sql.Named("AGE", age))
    
    if err != nil {
        log.Println(err)
    }
}

// Alternative implementation using positional parameters (more common in Go)
func insert_user_positional(conn *sql.DB, name string, age int) {
    query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    _, err := conn.ExecContext(context.Background(), query, name, age)
    
    if err != nil {
        log.Println(err)
    }
}

// Example usage (for reference)
func main() {
    // Connection setup would be done here
    // connString := "server=localhost;user id=sa;password=yourpassword;database=yourdatabase"
    // conn, err := sql.Open("sqlserver", connString)
    // if err != nil {
    //     log.Fatal(err)
    // }
    // defer conn.Close()
    
    // insert_user(conn, "John Doe", 25)
    
    fmt.Println("Translation complete")
}