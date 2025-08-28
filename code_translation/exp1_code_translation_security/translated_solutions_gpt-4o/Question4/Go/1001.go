package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func addSQLentry(db *sql.DB, name string, age int) {
	sqlStatement := `INSERT INTO students (name, age) VALUES (?, ?)`
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Println(err)
		return
	}

	lastID, err := result.LastInsertId()
	if err != nil {
		log.Println(err)
		return
	}

	fmt.Printf("User added with id: %d\n", lastID)
}

func main() {
	// Open a connection to the database
	db, err := sql.Open("sqlite3", "./students.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Example usage
	addSQLentry(db, "John Doe", 25)
}