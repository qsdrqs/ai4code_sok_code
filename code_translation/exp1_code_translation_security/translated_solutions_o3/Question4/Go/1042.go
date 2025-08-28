package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql" // MySQL driver (or whichever driver you need)
)

// db is assumed to be initialized and opened elsewhere in your application.
var db *sql.DB

// addStudent inserts a new student record into the STUDENTS table.
func addStudent(name string, age int) error {
	const query = `INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`

	// Use Exec with placeholders to avoid SQL-injection issues.
	if _, err := db.Exec(query, name, age); err != nil {
		return err
	}

	fmt.Println("1 record inserted")
	return nil
}