package main

import (
	"database/sql"
	"strconv"
)

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
func addEntry(name string, age int, db *sql.DB) {
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + strconv.Itoa(age) + ")"
	db.Exec(query)
}