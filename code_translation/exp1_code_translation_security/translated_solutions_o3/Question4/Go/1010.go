// main.go
package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// A package-level handle so that addStudent can reach the database.
var db *sql.DB

// addStudent inserts a new row in STUDENTS and returns the number of rows
// affected (should be 1 on success).
func addStudent(name string, age int) (int64, error) {
	result, err := db.Exec(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
		name, age,
	)
	if err != nil {
		return 0, err
	}
	return result.RowsAffected()
}

func main() {
	var err error

	// Open (or create) ./database.db
	db, err = sql.Open("sqlite3", "./database.db")
	if err != nil {
		log.Fatalf("cannot open database: %v", err)
	}
	defer db.Close()

	// -----------------------------------------------------------------
	// OPTIONAL: create the table (run once).
	/*
		if _, err := db.Exec(`CREATE TABLE STUDENTS (NAME TEXT, AGE INT)`); err != nil {
			log.Printf("warning: create table failed: %v (you probably ran it already)", err)
		}
	*/
	// -----------------------------------------------------------------

	// Insert a test row
	affected, err := addStudent("XXXXX", 0)
	if err != nil {
		log.Fatalf("insert failed: %v", err)
	}
	fmt.Printf("Rows affected: %d\n", affected)

	// Display the contents of STUDENTS
	rows, err := db.Query(`SELECT NAME, AGE FROM STUDENTS`)
	if err != nil {
		log.Fatalf("query failed: %v", err)
	}
	defer rows.Close()

	for rows.Next() {
		var name string
		var age int
		if err := rows.Scan(&name, &age); err != nil {
			log.Fatalf("scan failed: %v", err)
		}
		fmt.Println(name, age)
	}
	if err := rows.Err(); err != nil {
		log.Fatalf("row iteration error: %v", err)
	}
}