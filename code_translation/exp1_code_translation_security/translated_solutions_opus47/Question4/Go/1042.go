package main

import (
	"database/sql"
	"fmt"
)

var connection *sql.DB

func addStudent(name string, age int) {
	sqlStr := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	_, err := connection.Exec(sqlStr)
	if err != nil {
		panic(err)
	}
	fmt.Println("1 record inserted")
}