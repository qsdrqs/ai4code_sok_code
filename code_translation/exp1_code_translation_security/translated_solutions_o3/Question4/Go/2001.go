package main

import (
	"database/sql"
	"log"
)

// add inserts the supplied name and age into the `students` table.
// Any MySQL driver can be used (e.g. github.com/go-sql-driver/mysql).
func add(db *sql.DB, name string, age int) error {
	const query = `INSERT INTO students (name, age) VALUES (?, ?)`

	if _, err := db.Exec(query, name, age); err != nil {
		return err
	}

	log.Printf("Added %s to students table\n", name)
	return nil
}