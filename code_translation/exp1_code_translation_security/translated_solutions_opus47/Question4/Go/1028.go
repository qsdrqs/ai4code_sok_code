package main

import (
	"database/sql"
	"fmt"
	"strconv"

	_ "github.com/go-sql-driver/mysql"
)

var connection *sql.DB

func addStudent(name string, age int) {
	sqlStr := "INSERT INTO STUDENTS (name, age) VALUES ('" + name + "', " + strconv.Itoa(age) + ")"
	_, err := connection.Exec(sqlStr)
	if err != nil {
		panic(err)
	}
	fmt.Println("1 record inserted")
}