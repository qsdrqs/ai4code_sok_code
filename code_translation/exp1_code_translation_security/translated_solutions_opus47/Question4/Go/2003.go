package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func insertStudent(db *sql.DB, name string, age int) {
	_, err := db.Exec("INSERT INTO students (name, age) VALUES (?, ?)", name, age)
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	_, err = db.Exec("CREATE TABLE students (name TEXT, age INT)")
	if err != nil {
		log.Fatal(err)
	}

	insertStudent(db, "XXXXX", 0)

	rows, err := db.Query("SELECT * FROM students")
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
		fmt.Printf("{ name: '%s', age: %d }\n", name, age)
	}

	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}