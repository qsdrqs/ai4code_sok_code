package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

// createEntry inserts a new row into the STUDENTS table.
// NOTE: The DSN ("user:pass@tcp(host:port)/dbname") will be provided
//       for you in the actual project setup.
func createEntry(name string, age int) error {
	// In a real-world application the *sql.DB handle would be created once
	// and reused (it’s safe for concurrent use).  We open a new one here
	// just to keep the example self-contained.
	dsn := "user:password@tcp(localhost:3306)/dbname"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		return err
	}
	defer db.Close()

	// Optional: verify the connection is alive.
	if err := db.Ping(); err != nil {
		return err
	}

	// Perform the INSERT.
	_, err = db.Exec(`INSERT INTO STUDENTS (name, age) VALUES (?, ?)`, name, age)
	return err
}

func main() {
	if err := createEntry("Alice", 21); err != nil {
		log.Fatalf("failed to insert: %v", err)
	}
	fmt.Println("row inserted successfully")
}