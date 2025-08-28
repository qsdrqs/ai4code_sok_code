package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Change the DSN to match your own credentials / host / db.
const dsn = "user:password@tcp(localhost:3306)/dbname"

var db *sql.DB

func init() {
	var err error
	db, err = sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("opening DB failed: %v", err)
	}

	// Verify the connection is actually alive.
	if err = db.Ping(); err != nil {
		log.Fatalf("ping DB failed: %v", err)
	}
}

// addStudent inserts a new row into the STUDENTS table.
func addStudent(name string, age int) error {
	// Parameterized query prevents SQL-injection issues.
	_, err := db.Exec("INSERT INTO STUDENTS (name, age) VALUES (?, ?)", name, age)
	return err
}

func main() {
	if err := addStudent("Alice", 22); err != nil {
		fmt.Printf("insert failed: %v\n", err)
	} else {
		fmt.Println("insert OK")
	}
}