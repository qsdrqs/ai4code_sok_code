package main

import (
    "database/sql"
    "fmt"
    "log"
)

func addStudent(name string, age int) {
    sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    _, err := connection.Exec(sql)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("1 record inserted")
}