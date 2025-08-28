package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

// Assuming there's a SQL table called STUDENTS available

// Adds a new entry to SQL table "STUDENTS"
func add(db *sql.DB, name string, age int) {
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	fmt.Println(sqlStatement)

	_, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatalf("Error inserting record: %v", err)
	}

	fmt.Println("1 record inserted")
}

func main() {
	// Example connection string: "user:password@tcp(127.0.0.1:3306)/dbname"
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	// Example usage
	add(db, "John Doe", 25)
}