package main

import (
	"context"
	"database/sql"
	"log"

	// The blank import `_` is used to register the driver with the `database/sql` package.
	_ "github.com/denisenkom/go-mssqldb"
)

// InsertUser inserts a new user into the STUDENTS table.
// It assumes `db` is an active and configured database connection pool (*sql.DB).
// It returns an error if the insertion fails.
func InsertUser(db *sql.DB, name string, age int) error {
	// The SQL query uses named parameters (@name, @age) which are supported by the go-mssqldb driver.
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (@name, @age);"

	// We use ExecContext for executing statements that don't return rows.
	// It's best practice to use a context for cancellation and timeouts.
	// context.Background() is a good default.
	_, err := db.ExecContext(
		context.Background(),
		query,
		sql.Named("name", name),
		sql.Named("age", age),
	)

	// In Go, errors are returned from functions to be handled by the caller.
	// If an error occurred, we return it.
	if err != nil {
		// We could log the error here, but returning it is more idiomatic
		// as it gives the caller control over logging and handling.
		log.Printf("Failed to insert user: %v", err)
		return err
	}

	// A nil error indicates success.
	return nil
}

// Example of how you might use the InsertUser function.
func main() {
	// Connection string - replace with your actual connection details.
	// Example format: "sqlserver://username:password@host/instance?database=dbname"
	connString := "sqlserver://sa:YourStrong!Passw0rd@localhost:1433?database=master"

	// sql.Open() returns a connection pool (*sql.DB).
	db, err := sql.Open("sqlserver", connString)
	if err != nil {
		log.Fatalf("Failed to open database connection: %v", err)
	}
	// It's idiomatic to defer the closing of the connection.
	defer db.Close()

	// Ping the database to verify the connection is alive.
	err = db.Ping()
	if err != nil {
		log.Fatalf("Failed to ping database: %v", err)
	}

	log.Println("Successfully connected to the database.")

	// Call the function to insert a user.
	err = InsertUser(db, "Alice", 30)
	if err != nil {
		// The error is already logged inside InsertUser, but we could handle it further here.
		log.Println("The main function received an error from InsertUser.")
	} else {
		log.Println("User 'Alice' inserted successfully.")
	}

	err = InsertUser(db, "Bob", 25)
	if err != nil {
		log.Println("The main function received an error from InsertUser.")
	} else {
		log.Println("User 'Bob' inserted successfully.")
	}
}