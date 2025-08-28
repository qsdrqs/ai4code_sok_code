package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

func addNewEntry(name string, age int) {
	// Open (or create) the database file.
	db, err := sql.Open("sqlite3", "./database.db")
	if err != nil {
		log.Fatalf("opening DB: %v", err)
	}
	defer db.Close()

	// Prepare and run the INSERT statement.
	stmt, err := db.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)")
	if err != nil {
		log.Fatalf("prepare statement: %v", err)
	}
	defer stmt.Close()

	if _, err = stmt.Exec(name, age); err != nil {
		log.Fatalf("executing insert: %v", err)
	}

	// Query back all rows.
	rows, err := db.Query("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatalf("query rows: %v", err)
	}
	defer rows.Close()

	for rows.Next() {
		var (
			id   int
			nm   string
			ag   int
		)
		if err := rows.Scan(&id, &nm, &ag); err != nil {
			log.Fatalf("scan row: %v", err)
		}
		fmt.Printf("%d: %s %d\n", id, nm, ag)
	}
	if err := rows.Err(); err != nil {
		log.Fatalf("rows iteration: %v", err)
	}
}

func main() {
	addNewEntry("XXXXX", 0)
}