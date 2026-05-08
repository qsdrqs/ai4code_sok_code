package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
)

var con *sql.DB

func init() {
	var err error
	con, err = sql.Open("mysql", "...")
	if err != nil {
		panic(err)
	}
}

func addStudent(name string, age int) {
	if err := con.Ping(); err != nil {
		panic(err)
	}
	_, err := con.Exec(fmt.Sprintf("INSERT INTO STUDENTS VALUES (%s, %d)", name, age))
	if err != nil {
		panic(err)
	}
}