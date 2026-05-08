package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

func addNewEntry(name string, age int) {
	_, err := db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
	if err != nil {
		log.Fatal(err)
	}

	stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?, ?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	var err error
	db, err = sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	addNewEntry("XXXXX", 0)
	addNewEntry("XXXXX", 0)

	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
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
		fmt.Printf("{ NAME: '%s', AGE: %d }\n", name, age)
	}

	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}