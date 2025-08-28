package main

import (
	"database/sql"
	"fmt"
	"log"
	"os"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	// async issues equivalent - immediate function execution
	createDatabase()
}

func createDatabase() {
	fmt.Println("created database")
	
	db, err := sql.Open("sqlite3", "temp.db")
	if err != nil {
		fmt.Printf("Getting error %v\n", err)
		os.Exit(1)
	}
	defer db.Close()
	
	// Test the connection
	if err = db.Ping(); err != nil {
		fmt.Printf("Getting error %v\n", err)
		os.Exit(1)
	}
	
	createTables2(db)
}

func createTables(db *sql.DB) {
	fmt.Println("pre-created tables")
	_, err := db.Exec("CREATE TABLE students(name TEXT, age INT)")
	if err != nil {
		log.Printf("Error creating table: %v", err)
	}
}

func createTables2(db *sql.DB) {
	fmt.Println("pre-created tables")
	_, err := db.Exec("CREATE TABLE students(name TEXT, age INT)")
	if err != nil {
		log.Printf("Error creating table: %v", err)
		return
	}
	insert(db, "John Doe", 30)
}

func insert(db *sql.DB, name string, age int) {
	sql := "INSERT INTO students(name, age) VALUES (?, ?)"
	_, err := db.Exec(sql, name, age)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return
	}
	fmt.Println("row added successfully")
}