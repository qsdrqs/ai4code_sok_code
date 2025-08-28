package main

import (
	"bufio"
	"database/sql"
	"fmt"
	"os"
	"strconv"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

// Global database connection
var db *sql.DB

func init() {
	var err error
	// Replace with your actual credentials
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"
	db, err = sql.Open("mysql", dsn)
	if err != nil {
		panic(err)
	}
}

func insert() {
	// Ping the database to ensure connection
	if err := db.Ping(); err != nil {
		panic(err)
	}
	fmt.Println("Connected!")

	// Read user input
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("enter a name: ")
	name, _ := reader.ReadString('\n')
	name = strings.TrimSpace(name)

	fmt.Print("enter an age: ")
	ageStr, _ := reader.ReadString('\n')
	ageStr = strings.TrimSpace(ageStr)

	age, err := strconv.Atoi(ageStr)
	if err != nil {
		panic(err)
	}

	// Insert into the database
	result, err := db.Exec("INSERT INTO students (name, age) VALUES (?, ?)", name, age)
	if err != nil {
		panic(err)
	}

	// Get number of affected rows
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		panic(err)
	}

	fmt.Printf("Number of records inserted: %d\n", rowsAffected)
}

func main() {
	insert()
}