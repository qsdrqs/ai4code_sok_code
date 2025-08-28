package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	// Open an in-memory SQLite database
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Add two entries
	addNewEntry(db, "XXXXX", 0)
	addNewEntry(db, "XXXXX", 0)

	// Query and print all rows
	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	var name string
	var age int
	for rows.Next() {
		err := rows.Scan(&name, &age)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Printf("Name: %s, Age: %d\n", name, age)
	}

	// Check for any errors during iteration
	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}

// addNewEntry creates the table (if not exists) and inserts a new student
func addNewEntry(db *sql.DB, name string, age int) {
	// Create the table if it doesn't exist
	_, err := db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")
	if err != nil {
		log.Fatal(err)
	}

	// Prepare the insert statement
	stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?, ?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	// Execute the insert
	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}
}