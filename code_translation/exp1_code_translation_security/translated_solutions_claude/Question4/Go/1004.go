package main

import (
    "database/sql"
    "fmt"
    "log"
    
    _ "github.com/go-sql-driver/mysql"
)

/*
Go function add_student

Given a String name and int age, add entry to table STUDENTS
*/
func add_student(name string, age int) string {
    sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    fmt.Println(sql)
    return sql
}

func main() {
    // Create connection string
    dsn := "yourusername:yourpassword@tcp(localhost:3306)/"
    
    // Open database connection
    con, err := sql.Open("mysql", dsn)
    if err != nil {
        log.Fatal(err)
    }
    defer con.Close()
    
    // Test connection
    err = con.Ping()
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Connected!")
    
    // Execute query
    sqlQuery := add_student("XXXXX", 0)
    result, err := con.Exec(sqlQuery)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Printf("Result: %v\n", result)
}