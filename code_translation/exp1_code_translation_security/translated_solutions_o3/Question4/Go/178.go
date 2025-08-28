package main

import (
	"database/sql"
	"fmt"
)

// assume that the driver and any other deps will be injected/provided
func newConnection() (*sql.DB, error) {
	// create / return a connection – driver & DSN are injected elsewhere
	return sql.Open("driver-name", "dsn-string")
}

// RegisterStudent stores a new student row and returns the sql.Result (or an error)
func RegisterStudent(name string, age int) (sql.Result, error) {
	student := struct {
		Name string
		Age  int
	}{
		Name: name,
		Age:  age,
	}

	conn, err := newConnection() // create connection here
	if err != nil {
		return nil, err
	}
	defer conn.Close()

	// Same query the JS version built (string-interpolation); left exactly as-is
	query := fmt.Sprintf(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')",
		student.Name,
		student.Age,
	)

	return conn.Exec(query)
}