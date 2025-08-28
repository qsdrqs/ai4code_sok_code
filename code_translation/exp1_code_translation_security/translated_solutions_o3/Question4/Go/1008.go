package main

import (
	"database/sql"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// A package-level handle so every function can reuse the same connection.
var db *sql.DB

func init() {
	var err error

	// Open (or create) a SQLite database file named "mydb.sqlite".
	// The returned *sql.DB object is safe for concurrent use
	// and internally manages its own connection pool.
	db, err = sql.Open("sqlite3", "mydb.sqlite")
	if err != nil {
		log.Fatalf("opening database: %v", err)
	}

	// Verify the connection is actually usable.
	if err = db.Ping(); err != nil {
		log.Fatalf("ping database: %v", err)
	}

	// Create the table right away so the program can start inserting.
	if err = createTable(); err != nil {
		log.Fatalf("create table: %v", err)
	}
}

// createTable mirrors the JavaScript createTable() routine.
func createTable() error {
	const stmt = `
		CREATE TABLE IF NOT EXISTS students (
			id   INTEGER PRIMARY KEY,
			name TEXT,
			age  INTEGER
		);
	`
	_, err := db.Exec(stmt)
	if err != nil {
		return err
	}

	// In the browser code an alert was shown; here we log instead.
	log.Println("Table created (or already existed).")
	return nil
}

// insertStudent corresponds to JavaScript insertStudent(name, age).
func insertStudent(name string, age int) error {
	const stmt = `INSERT INTO students (name, age) VALUES (?, ?);`
	_, err := db.Exec(stmt, name, age)
	return err
}

/*** example usage ***/

func main() {
	// Insert a couple of students.
	if err := insertStudent("Alice", 22); err != nil {
		log.Printf("insert Alice: %v", err)
	}
	if err := insertStudent("Bob", 25); err != nil {
		log.Printf("insert Bob: %v", err)
	}
}