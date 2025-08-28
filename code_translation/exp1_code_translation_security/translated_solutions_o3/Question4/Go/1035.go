// File: main.go
package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite-driver
)

func main() {
	// Open (or create) the SQLite database file
	db, err := sql.Open("sqlite3", "studentdb")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Create the STUDENTS table if it does not exist
	_, err = db.Exec(`CREATE TABLE IF NOT EXISTS STUDENTS (
		NAME TEXT,
		AGE  INT
	)`)
	if err != nil {
		log.Fatal(err)
	}

	// Insert a couple of rows
	insert(db, "Foo", 42)
	insert(db, "Bar", 55)
}

// insert adds a single student row
func insert(db *sql.DB, name string, age int) {
	tx, err := db.Begin()
	if err != nil {
		log.Println(err)
		return
	}
	defer tx.Commit()

	stmt, err := tx.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)")
	if err != nil {
		log.Println(err)
		return
	}
	defer stmt.Close()

	if _, err := stmt.Exec(name, age); err != nil {
		log.Println(err)
		tx.Rollback()
		return
	}

	/*
	// If you want to read back all rows (similar to the commented code in JS):
	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		log.Println(err)
		return
	}
	defer rows.Close()

	for rows.Next() {
		var n string
		var a int
		if err := rows.Scan(&n, &a); err != nil {
			log.Println(err)
			return
		}
		fmt.Printf("%s | %d\n", n, a)
	}
	*/
	fmt.Printf("Inserted %s, %d\n", name, age)
}