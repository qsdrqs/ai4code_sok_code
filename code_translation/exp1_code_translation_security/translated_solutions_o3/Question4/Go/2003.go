package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// insertStudent mirrors the JS helper.
func insertStudent(db *sql.DB, name string, age int) error {
	_, err := db.Exec(`INSERT INTO students (name, age) VALUES (?, ?)`, name, age)
	return err
}

func main() {
	// Open an in-memory database (single connection).
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Create the table (equivalent to db.serialize + db.run in JS).
	if _, err := db.Exec(`CREATE TABLE students (name TEXT, age INT)`); err != nil {
		log.Fatal(err)
	}

	// Insert a row (same values as JS example).
	if err := insertStudent(db, "XXXXX", 0); err != nil {
		log.Fatal(err)
	}

	// Query and print the rows (equivalent to db.each in JS).
	rows, err := db.Query(`SELECT * FROM students`)
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
		fmt.Printf("name: %s, age: %d\n", name, age)
	}
	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}