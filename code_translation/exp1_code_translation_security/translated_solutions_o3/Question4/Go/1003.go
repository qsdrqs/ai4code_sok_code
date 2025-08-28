package studentdb

import (
	"database/sql"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// AddStudent inserts one row into the STUDENTS (NAME, AGE) table.
//
// db   : *sql.DB opened against an SQLite database that already contains
//        the STUDENTS table.
// name : student name
// age  : student age
//
// It behaves like the original JS helper: it doesn’t do any validation and it
// simply logs whether the insert succeeded or failed.  An error is returned so
// callers can decide what to do with it (ignore it, propagate it, etc.).
func AddStudent(db *sql.DB, name string, age int) error {
	stmt, err := db.Prepare(`INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`)
	if err != nil {
		log.Println("inserted", err)
		return err
	}
	defer stmt.Close()

	if _, err = stmt.Exec(name, age); err != nil {
		log.Println("inserted", err)
		return err
	}

	log.Println("inserted", nil)
	return nil
}