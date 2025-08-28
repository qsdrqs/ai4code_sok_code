package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

var db *sql.DB

func init() {
	// Open (or create) the database file.
	var err error
	db, err = sql.Open("sqlite3", "./students.db")
	if err != nil {
		log.Fatal(err)
	}

	// Optional: make sure the connection works.
	if err = db.Ping(); err != nil {
		log.Fatal(err)
	}
}

// addSQLEntry inserts a new student record and prints the inserted row ID.
func addSQLEntry(name string, age int) error {
	const sqlStmt = "INSERT INTO students (name, age) VALUES (?, ?)"

	// Exec returns a sql.Result that lets us obtain the last inserted ID.
	res, err := db.Exec(sqlStmt, name, age)
	if err != nil {
		return err
	}

	id, err := res.LastInsertId()
	if err != nil {
		return err
	}

	fmt.Printf("User added with id: %d\n", id)
	return nil
}

func main() {
	if err := addSQLEntry("Alice", 20); err != nil {
		log.Println("addSQLEntry error:", err)
	}
}