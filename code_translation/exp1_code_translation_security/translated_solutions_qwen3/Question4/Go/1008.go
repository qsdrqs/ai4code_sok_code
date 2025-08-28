package main

import (
	"database/sql"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

// CreateTable creates the 'students' table if it does not already exist.
// This function mimics the behavior of the JavaScript `createTable` function,
// using a transaction and logging success or error messages.
func CreateTable() {
	db, err := sql.Open("sqlite3", "./mydb.db")
	if err != nil {
		log.Println("Error opening database:", err)
		return
	}
	defer db.Close()

	tx, err := db.Begin()
	if err != nil {
		log.Println("Error starting transaction:", err)
		return
	}

	createSQL := `CREATE TABLE IF NOT EXISTS students (
		id INTEGER PRIMARY KEY,
		name TEXT,
		age INTEGER
	)`

	stmt, err := tx.Prepare(createSQL)
	if err != nil {
		tx.Rollback()
		log.Println("Error preparing statement:", err)
		return
	}
	defer stmt.Close()

	_, err = stmt.Exec()
	if err != nil {
		tx.Rollback()
		log.Printf("Error creating table: %v\n", err)
	} else {
		tx.Commit()
		log.Println("Table created successfully")
	}
}

// InsertStudent inserts a new student into the 'students' table.
// This function mimics the behavior of the JavaScript `insertStudent` function,
// using a transaction and logging any errors that occur.
func InsertStudent(name string, age int) {
	db, err := sql.Open("sqlite3", "./mydb.db")
	if err != nil {
		log.Println("Error opening database:", err)
		return
	}
	defer db.Close()

	tx, err := db.Begin()
	if err != nil {
		log.Println("Error starting transaction:", err)
		return
	}

	insertSQL := "INSERT INTO students (name, age) VALUES (?, ?)"
	stmt, err := tx.Prepare(insertSQL)
	if err != nil {
		tx.Rollback()
		log.Println("Error preparing statement:", err)
		return
	}
	defer stmt.Close()

	_, err = stmt.Exec(name, age)
	if err != nil {
		tx.Rollback()
		log.Printf("Error inserting student: %v\n", err)
	} else {
		tx.Commit()
	}
}