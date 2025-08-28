package main

import (
	"database/sql"
)

// assume `db` is an *sql.DB that has already been opened / configured
var db *sql.DB

// addStudent inserts a new student record into the STUDENTS table.
func addStudent(name string, age int) error {
	const query = `INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`
	_, err := db.Exec(query, name, age)
	return err
}