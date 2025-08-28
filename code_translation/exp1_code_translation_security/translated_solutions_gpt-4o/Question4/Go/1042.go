package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func addStudent(db *sql.DB, name string, age int) {
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	_, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatalf("Error inserting record: %v", err)
	}
	fmt.Println("1 record inserted")
}

func main() {
	// Replace with your own connection string
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	addStudent(db, "John Doe", 20)
}