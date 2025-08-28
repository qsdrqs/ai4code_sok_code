package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	createDatabase()
}

func createDatabase() {
	fmt.Println("created database")
	db, err := sql.Open("sqlite3", "./temp.db")
	if err != nil {
		log.Fatalf("Getting error: %v", err)
	}
	defer db.Close()

	createTables2(db)
}

func createTables(db *sql.DB) {
	fmt.Println("pre-created tables")
	_, err := db.Exec("CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)")
	if err != nil {
		log.Fatalf("Error creating table: %v", err)
	}
}

func createTables2(db *sql.DB) {
	fmt.Println("pre-created tables")
	_, err := db.Exec("CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)")
	if err != nil {
		log.Fatalf("Error creating table: %v", err)
	}

	insert(db, "John Doe", 30)
}

func insert(db *sql.DB, name string, age int) {
	sqlStmt := "INSERT INTO students(name, age) VALUES (?, ?)"
	_, err := db.Exec(sqlStmt, name, age)
	if err != nil {
		log.Fatalf("Error inserting row: %v", err)
	}
	fmt.Println("row added successfully")
}