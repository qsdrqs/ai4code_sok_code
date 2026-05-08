package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func addNewEntry(name string, age int) {
	db, err := sql.Open("sqlite3", "database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?,?)")
	if err != nil {
		log.Fatal(err)
	}

	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}
	stmt.Close()

	rows, err := db.Query("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	for rows.Next() {
		var id int
		var rowName string
		var rowAge int
		if err := rows.Scan(&id, &rowName, &rowAge); err != nil {
			log.Fatal(err)
		}
		fmt.Printf("%d: %s %d\n", id, rowName, rowAge)
	}
}

func main() {
	addNewEntry("XXXXX", 0)
}