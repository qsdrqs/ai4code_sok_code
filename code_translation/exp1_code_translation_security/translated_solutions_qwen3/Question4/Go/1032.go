package main

import (
    "strconv"
)

func addStudent(name string, age int) string {
    sql := "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + strconv.Itoa(age) + ")"
    return sql
}