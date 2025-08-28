package main

import (
	"database/sql"
	"fmt"
	"log"

	// The blank import `_` is used to register the MySQL driver with the `database/sql` package.
	// The driver's functions are not called directly from our code.
	_ "github.com/go-sql-driver/mysql"
)

// Config holds the database connection details, similar to the `config.js` file.
type Config struct {
	User     string
	Password string
	Host     string
	Database string
}

// loadConfig simulates requiring the './config.js' file by returning a populated Config struct.
// In a real application, you would load this from a file (e.g., JSON, YAML) or environment variables.
func loadConfig() Config {
	return Config{
		User:     "user",
		Password: "password",
		Host:     "127.0.0.1:3306",
		Database: "testdb",
	}
}

// add connects to a MySQL database and inserts a new row.
//
// Note on parameter mapping from the original JS:
// - 'name' is mapped to the 'title' column.
// - 'age' is mapped to the 'completed' column.
// - The 'table' parameter from the original function was unused, so it's included here for signature
//   matching but is similarly unused. A comment highlights this.
func add(name string, age int, table string) {
	// 1. Load configuration
	config := loadConfig()

	// 2. Create the Data Source Name (DSN) string for the connection.
	// Format: "user:password@tcp(host)/dbname"
	dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s", config.User, config.Password, config.Host, config.Database)

	// 3. Open a connection to the database. sql.Open doesn't establish any
	// connections, it just prepares the database handle.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		// Use log.Fatal to print the error and exit if the DSN is invalid.
		log.Fatal("Failed to create database handle:", err)
	}
	// defer db.Close() ensures the connection is closed when the function exits.
	defer db.Close()

	// 4. Verify the connection to the database is actually alive.
	err = db.Ping()
	if err != nil {
		log.Fatal("Failed to connect to database:", err)
	}

	fmt.Println("Successfully connected to MySQL!")

	// 5. Prepare the SQL statement.
	// Note: The 'table' parameter is unused, just like in the original JS code.
	// The table name 'todos' is hardcoded.
	// Go's database/sql uses '?' as a placeholder for arguments to prevent SQL injection.
	// Unlike the JS driver, you need one '?' for each value you are inserting.
	stmt := "INSERT INTO todos(title, completed) VALUES (?, ?)"

	// 6. Execute the statement.
	// For INSERT, UPDATE, or DELETE, use Exec(). For SELECT, use Query() or QueryRow().
	// The arguments (name, age) are passed safely to the driver.
	result, err := db.Exec(stmt, name, age)
	if err != nil {
		// In Go, errors are returned explicitly and should be checked.
		// This is the equivalent of the `if (err)` block in the JS callback.
		fmt.Printf("Error executing query: %v\n", err)
		return
	}

	// 7. Get the number of rows affected by the query.
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		fmt.Printf("Error getting affected rows: %v\n", err)
		return
	}

	// get inserted rows
	fmt.Printf("Row inserted: %d\n", rowsAffected)
}

// main function to run the example
func main() {
	// To run this code, you need a MySQL server with a 'testdb' database
	// and a 'todos' table, for example:
	// CREATE TABLE todos (
	//   id INT AUTO_INCREMENT PRIMARY KEY,
	//   title VARCHAR(255) NOT NULL,
	//   completed INT
	// );
	add("Learn Go", 1, "some_unused_table")
}