package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// studentWrite inserts a student record.
func studentWrite(db *sql.DB, name string, age int) error {
	_, err := db.Exec(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)",
		name, age,
	)
	return err
}

func main() {
	// Open (or create) the SQLite database.
	db, err := sql.Open("sqlite3", "/home/codex/Documents/User_Study_UI/database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Insert a sample row.
	if err := studentWrite(db, "XXXXX", 0); err != nil {
		log.Printf("insert failed: %v\n", err)
	}

	// Query every row in the STUDENTS table.
	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	// Dynamically fetch column names so we can print any schema.
	cols, err := rows.Columns()
	if err != nil {
		log.Fatal(err)
	}

	for rows.Next() {
		// Create a slice of `interface{}`s to scan values into.
		values := make([]interface{}, len(cols))
		valuePtrs := make([]interface{}, len(cols))
		for i := range values {
			valuePtrs[i] = &values[i]
		}

		if err := rows.Scan(valuePtrs...); err != nil {
			log.Fatal(err)
		}

		// Put the row into a map[column]value for convenient printing.
		rowMap := make(map[string]interface{}, len(cols))
		for i, col := range cols {
			rowMap[col] = values[i]
		}

		fmt.Println(rowMap) // Equivalent to console.log(row, row) in JS
	}

	if err := rows.Err(); err != nil {
		log.Fatal(err)
	}
}