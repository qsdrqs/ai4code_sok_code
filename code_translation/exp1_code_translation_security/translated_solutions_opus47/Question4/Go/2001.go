package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func add(db *sql.DB, name string, age int) {
	/* Add name, age to students table in MySQL */
	query := "INSERT INTO students (name, age) VALUES (?, ?)"
	_, err := db.Exec(query, name, age)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Added " + name + " to students table")
}