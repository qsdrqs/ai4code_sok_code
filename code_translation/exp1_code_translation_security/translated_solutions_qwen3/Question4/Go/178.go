package main

import (
	"database/sql"
	"fmt"
)

// registerStudent inserts a new student into the STUDENTS table.
func registerStudent(name string, age int) (sql.Result, error) {
	// Create a student struct to mirror the JavaScript object
	student := struct {
		name string
		age  int
	}{
		name: name,
		age:  age,
	}

	// Simulate creating a new connection (as in the JavaScript code)
	connection, err := sql.Open("mysql", "user:password@/dbname")
	if err != nil {
		return nil, err
	}

	// Build the SQL query using string interpolation
	query := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", student.name, student.age)

	// Execute the query and return the result and error
	return connection.Exec(query)
}