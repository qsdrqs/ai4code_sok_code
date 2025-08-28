package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

func main() {
	createDatabase()
}

// createDatabase opens (or creates) temp.db and delegates
// the rest of the work.
func createDatabase() {
	fmt.Println("created database")

	db, err := sql.Open("sqlite3", "./temp.db")
	if err != nil {
		log.Fatalf("getting error: %v", err)
	}
	defer db.Close()

	if err := createTablesAndInsert(db); err != nil {
		log.Fatalf("setup failed: %v", err)
	}

	// For demonstration: show what we inserted.
	if err := listStudents(db); err != nil {
		log.Fatalf("listing failed: %v", err)
	}
}

// createTablesAndInsert creates the table (if it does not exist)
// and immediately inserts one row, mirroring createTables2 in JS.
func createTablesAndInsert(db *sql.DB) error {
	fmt.Println("pre-created tables")

	const createStmt = `
		CREATE TABLE IF NOT EXISTS students (
			name TEXT,
			age  INT
		);`
	if _, err := db.Exec(createStmt); err != nil {
		return err
	}

	return insert(db, "John Doe", 30)
}

// insert adds one student row.
func insert(db *sql.DB, name string, age int) error {
	const insertStmt = `INSERT INTO students(name, age) VALUES(?, ?);`
	if _, err := db.Exec(insertStmt, name, age); err != nil {
		return err
	}
	fmt.Println("row added successfully")
	return nil
}

// listStudents is roughly equivalent to the db.all (…) section.
func listStudents(db *sql.DB) error {
	fmt.Println("current rows:")
	rows, err := db.Query(`SELECT name, age FROM students;`)
	if err != nil {
		return err
	}
	defer rows.Close()

	for rows.Next() {
		var (
			name string
			age  int
		)
		if err := rows.Scan(&name, &age); err != nil {
			return err
		}
		fmt.Printf("%s %d\n", name, age)
	}
	return rows.Err()
}