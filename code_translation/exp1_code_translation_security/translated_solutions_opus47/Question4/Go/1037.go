package main

import (
	"database/sql"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

// config should be in DSN format, e.g. "user:password@tcp(127.0.0.1:3306)/dbname"
var config = "user:password@tcp(127.0.0.1:3306)/dbname"

func insertStudent(name string, age int) {
	connection, err := sql.Open("mysql", config)
	if err != nil {
		log.Fatal(err)
	}
	defer connection.Close()

	// insert statement
	sqlStmt := `INSERT INTO STUDENTS(title, completed)
	            VALUES('Learn how to insert a new row', true)`

	// execute the insert statement
	_, err = connection.Exec(sqlStmt)
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	insertStudent("XXXXX", 0)
}