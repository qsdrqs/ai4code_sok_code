package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func addNewEntry(name string, age int) {
	// Open database connection
	db, err := sql.Open("sqlite3", "database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Prepare and execute INSERT statement
	stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?,?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}

	// Query and display all records
	rows, err := db.Query("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	for rows.Next() {
		var id int
		var studentName string
		var studentAge int

		err := rows.Scan(&id, &studentName, &studentAge)
		if err != nil {
			log.Fatal(err)
		}

		fmt.Printf("%d: %s %d\n", id, studentName, studentAge)
	}

	if err = rows.Err(); err != nil {
		log.Fatal(err)
	}
}

func main() {
	addNewEntry("XXXXX", 0)
}