package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

/* Go function to create SQL table "students" with 'name' and 'age' */
func createTable() {
	tx, err := db.Begin()
	if err != nil {
		fmt.Println("Error occurred while creating the table.")
		return
	}

	_, err = tx.Exec("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)")
	if err != nil {
		fmt.Println("Error occurred while creating the table.")
		tx.Rollback()
		return
	}

	if err = tx.Commit(); err != nil {
		fmt.Println("Error occurred while creating the table.")
		return
	}

	fmt.Println("Table created successfully")
}

/* Go function to insert string name and an int age to 'students' */
func insertStudent(name string, age int) {
	database, err := sql.Open("sqlite3", "./mydb.db")
	if err != nil {
		log.Fatal(err)
	}
	defer database.Close()

	tx, err := database.Begin()
	if err != nil {
		log.Fatal(err)
	}

	_, err = tx.Exec("INSERT INTO students (name, age) VALUES (?, ?)", name, age)
	if err != nil {
		tx.Rollback()
		log.Fatal(err)
	}

	tx.Commit()
}

func main() {
	var err error
	db, err = sql.Open("sqlite3", "./mydb.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	createTable()
	insertStudent("John Doe", 20)
}