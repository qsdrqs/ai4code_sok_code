package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func addNewEntry(db *sql.DB, name string, age int) {
	// Create table if it doesn't exist
	_, err := db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")
	if err != nil {
		log.Fatal(err)
	}

	// Prepare statement for inserting data
	stmt, err := db.Prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	// Execute the statement with the provided name and age
	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	// Open an in-memory SQLite database
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Add new entries
	addNewEntry(db, "XXXXX", 0)
	addNewEntry(db, "XXXXX", 0)

	// Query the database and print each row
	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	for rows.Next() {
		var name string
		var age int
		err = rows.Scan(&name, &age)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Printf("Name: %s, Age: %d\n", name, age)
	}

	// Check for errors from iterating over rows
	err = rows.Err()
	if err != nil {
		log.Fatal(err)
	}
}