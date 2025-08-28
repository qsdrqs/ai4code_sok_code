package main

import (
	"database/sql"
	"log"

	// The blank import `_` is used to register the sqlite3 driver with the
	// database/sql package without importing any other names from the driver package.
	_ "github.com/mattn/go-sqlite3"
)

// AddStudent inserts a new student record into the database.
//
// Parameters:
//   db:   A pointer to a sql.DB object, which is assumed to be connected to a
//         database containing a STUDENTS (NAME TEXT, AGE INTEGER) table.
//   name: The student's name (string).
//   age:  The student's age (int).
//
// This function returns an error if the database operation fails. It also logs
// the outcome of the operation, similar to the original JavaScript function.
func AddStudent(db *sql.DB, name string, age int) error {
	// Prepare the SQL statement for insertion.
	// Using placeholders (?) is the standard way to prevent SQL injection.
	statement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"

	// db.Exec is used for SQL commands that don't return rows from the database,
	// such as INSERT, UPDATE, or DELETE.
	_, err := db.Exec(statement, name, age)
	if err != nil {
		// Log the error, similar to the callback in the JS version.
		log.Printf("Error inserting student record: %v", err)
		return err // Return the error to the caller.
	}

	// Log the successful insertion.
	log.Println("Successfully inserted student record.")
	return nil // Return nil to indicate success.
}

// The following main function is provided as a complete, runnable example
// to demonstrate how to use the AddStudent function.
func main() {
	// For demonstration, we'll use an in-memory SQLite database.
	// The database will be discarded when the program exits.
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatalf("Failed to open database: %v", err)
	}
	defer db.Close()

	// Create the STUDENTS table.
	createTableSQL := `CREATE TABLE STUDENTS (
		"ID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
		"NAME" TEXT,
		"AGE" INTEGER
	);`

	_, err = db.Exec(createTableSQL)
	if err != nil {
		log.Fatalf("Failed to create table: %v", err)
	}
	log.Println("STUDENTS table created successfully.")

	// --- Usage of the translated AddStudent function ---

	// Call AddStudent to insert a new student.
	err = AddStudent(db, "Alice", 21)
	if err != nil {
		// The error is already logged by AddStudent, but we could add
		// more handling here if needed.
		log.Println("The main function received an error from AddStudent.")
	}

	// Insert another student.
	err = AddStudent(db, "Bob", 22)
	if err != nil {
		log.Println("The main function received an error from AddStudent.")
	}
}