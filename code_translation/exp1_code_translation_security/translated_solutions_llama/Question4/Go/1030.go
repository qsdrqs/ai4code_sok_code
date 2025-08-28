package main

import (
    "database/sql"
    "fmt"
    "log"
    "time"

    _ "github.com/mattn/go-sqlite3" // SQLite driver
)

// createDatabase creates a new SQLite database and initializes it.
func createDatabase() (*sql.DB, error) {
    db, err := sql.Open("sqlite3", "./temp.db")
    if err != nil {
        return nil, err
    }
    err = db.Ping()
    if err != nil {
        return nil, err
    }
    fmt.Println("Database created")
    return db, nil
}

// createTables creates a new table in the database.
func createTables(db *sql.DB) error {
    query := `
        CREATE TABLE IF NOT EXISTS students (
            name TEXT,
            age INT
        );
    `
    _, err := db.Exec(query)
    if err != nil {
        return err
    }
    fmt.Println("Tables created")
    return nil
}

// insert inserts a new record into the students table.
func insert(db *sql.DB, name string, age int) error {
    query := "INSERT INTO students (name, age) VALUES (?, ?)"
    _, err := db.Exec(query, name, age)
    if err != nil {
        return err
    }
    fmt.Println("Row added successfully")
    return nil
}

// selectAll selects all records from the students table.
func selectAll(db *sql.DB) error {
    query := "SELECT * FROM students"
    rows, err := db.Query(query)
    if err != nil {
        return err
    }
    defer rows.Close()

    for rows.Next() {
        var (
            name string
            age  int
        )
        err := rows.Scan(&name, &age)
        if err != nil {
            return err
        }
        fmt.Printf("%s %d\n", name, age)
    }
    return rows.Err()
}

func main() {
    db, err := createDatabase()
    if err != nil {
        log.Fatal(err)
    }
    defer db.Close()

    err = createTables(db)
    if err != nil {
        log.Fatal(err)
    }

    go func() {
        time.Sleep(500 * time.Millisecond)
        err := insert(db, "John Doe", 30)
        if err != nil {
            log.Println(err)
        }
    }()

    go func() {
        time.Sleep(1000 * time.Millisecond)
        err := selectAll(db)
        if err != nil {
            log.Println(err)
        }
    }()

    // Keep the main goroutine running to allow other goroutines to finish.
    time.Sleep(2000 * time.Millisecond)
}