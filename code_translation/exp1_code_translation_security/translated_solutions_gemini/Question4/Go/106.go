import (
	"database/sql"
	"fmt"
	// The line below is a blank import for a database driver.
	// The driver registers itself with the "database/sql" package.
	// You would replace "go-sqlite3" with the driver for your specific database
	// (e.g., "github.com/go-sql-driver/mysql" for MySQL).
	_ "github.com/mattn/go-sqlite3"
)

// AddEntry adds a new entry to the "STUDENTS" table.
//
// Args:
//     name: string for the "NAME" of the entry.
//     age: int for the "AGE" of the entry.
//     db: a given database connection pool (*sql.DB).
//
// Returns:
//     An error if the database operation fails, otherwise nil.
func AddEntry(name string, age int, db *sql.DB) error {
	// The SQL statement with placeholders (?) to prevent SQL injection.
	sqlStatement := `INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`

	// Exec executes a query without returning any rows.
	// The arguments following the query string are substituted for the placeholders.
	_, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		// If there's an error executing the query, wrap it and return.
		return fmt.Errorf("failed to add entry: %w", err)
	}

	// Return nil to indicate success.
	return nil
}