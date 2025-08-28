package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Add inserts a single row into the given table.
// `name`  -> first column ("title" in the JS example)
// `age`   -> second column ("completed" in the JS example)
// `table` -> target table; matches the JS parameter even if that
//            parameter wasn’t used in the original code.
func Add(name string, age int, table string) error {
	// Load connection information (host, user, pass, db, …).
	cfg, err := LoadConfig() // Provided elsewhere.
	if err != nil {
		return fmt.Errorf("config: %w", err)
	}

	// Format the DSN string; helper provided by cfg or build it manually.
	dsn := cfg.FormatDSN() // e.g. "user:pass@tcp(host:3306)/dbname"

	// Open a connection pool.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		return fmt.Errorf("open: %w", err)
	}
	defer db.Close()

	// Ensure the connection is actually alive.
	if err = db.Ping(); err != nil {
		return fmt.Errorf("ping: %w", err)
	}

	// NOTE: Using fmt.Sprintf to inject a table name is only safe if
	//       `table` is trusted/validated. Otherwise one should use a
	//       whitelist or map of allowed table names.
	query := fmt.Sprintf("INSERT INTO %s(title,completed) VALUES (?, ?)", table)

	res, err := db.Exec(query, name, age)
	if err != nil {
		return fmt.Errorf("exec: %w", err)
	}

	rows, err := res.RowsAffected()
	if err != nil {
		return fmt.Errorf("rows affected: %w", err)
	}

	log.Printf("Row inserted: %d\n", rows)
	return nil
}

func main() {
	if err := Add("Buy milk", 0, "todos"); err != nil {
		log.Fatal(err)
	}
}