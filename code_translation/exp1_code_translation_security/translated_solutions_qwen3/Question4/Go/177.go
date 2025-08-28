package main

import (
    "database/sql"
    _ "github.com/alexbrainman/odbc"
)

func addStudent(name, age string) {
    sqlCommand := "INSERT INTO STUDENTS VALUES ('" + name + "','" + age + "');"
    db, _ := sql.Open("odbc", "")
    defer db.Close()
    db.Exec(sqlCommand)
}