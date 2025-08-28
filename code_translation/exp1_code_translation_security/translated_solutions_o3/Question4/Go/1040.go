package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// addNewEntry inserts a new record into the STUDENTS table, creating
// the table first if it doesn't already exist.
func addNewEntry(db *sql.DB, name string, age int) error {
	// Ensure the table exists.
	if _, err := db.Exec(`CREATE TABLE IF NOT EXISTS STUDENTS (
		NAME TEXT,
		AGE  INT
	)`); err != nil {
		return err
	}

	// Insert the record.
	if _, err := db.Exec(`INSERT INTO STUDENTS VALUES (?, ?)`, name, age); err != nil {
		return err
	}
	return nil
}

func main() {
	// Open an in-memory SQLite database.
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Guarantee the same in-memory DB is reused for every operation.
	db.SetMaxOpenConns(1)

	// Insert sample data.
	if err := addNewEntry(db, "XXXXX", 0); err != nil {
		log.Fatal(err)
	}
	if err := addNewEntry(db, "XXXXX", 0); err != nil {
		log.Fatal(err)
	}

	// Query and print all records.
	rows, err := db.Query(`SELECT NAME, AGE FROM STUDENTS`)
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	for rows.Next() {
		var name string
		var age int
		if err := rows.Scan(&name, &age); err != nil {
			log.Fatal(err)
		}
		fmt.Printf("{ NAME: %q, AGE: %d }\n", name, age)
	}
	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}