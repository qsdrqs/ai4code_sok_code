package main

import (
	"database/sql"
	"fmt"

	// The database driver (or any other dependencies) will be made available
	_ "example.com/your/db/driver"
)

// addStudent inserts a student record in the STUDENTS table.
func addStudent(name string, age int) error {
	// Direct string-building like the original JavaScript.
	// NOTE: Just as in the JS version, this is vulnerable to SQL injection.
	//       Prefer prepared statements in real code.
	cmd := fmt.Sprintf("INSERT INTO STUDENTS VALUES ('%s','%d');", name, age)

	// Open the connection. An empty connection string mirrors the JS example.
	db, err := sql.Open("your-driver", "")
	if err != nil {
		return err
	}
	defer db.Close()

	// Execute the INSERT.
	_, err = db.Exec(cmd)
	return err
}