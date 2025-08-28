package main

import (
	"database/sql"
	"fmt"
	"log"
)

// connection will be supplied elsewhere in the project.
var connection *sql.DB

func addStudent(name string, age int) {
	// Use placeholders instead of string-concatenation to avoid SQL-injection
	const query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"

	_, err := connection.Exec(query, name, age)
	if err != nil {
		// In the original JS version the error is thrown; here we’ll log and exit.
		log.Fatal(err)
	}

	fmt.Println("1 record inserted")
}