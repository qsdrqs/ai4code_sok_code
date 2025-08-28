package main

import (
	"database/sql"
	"fmt"
)

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
func addEntry(name string, age int, db *sql.DB) error {
	// Build the SQL statement.
	sqlStmt := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)

	// Execute the statement.
	_, err := db.Exec(sqlStmt)
	return err
}