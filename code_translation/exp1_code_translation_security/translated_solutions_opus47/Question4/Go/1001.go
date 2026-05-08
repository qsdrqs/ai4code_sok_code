package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

func addSQLentry(name string, age int) {
	sqlQuery := "INSERT INTO students (name, age) VALUES (?, ?)"
	result, err := db.Exec(sqlQuery, name, age)
	if err != nil {
		log.Println(err)
		return
	}

	lastID, err := result.LastInsertId()
	if err != nil {
		log.Println(err)
		return
	}

	fmt.Println("User added with id:", lastID)
}