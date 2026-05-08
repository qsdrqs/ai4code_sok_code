package main

import (
	"database/sql"
	"fmt"
	"os"

	_ "github.com/mattn/go-sqlite3"
)

// async issues (note: Go database operations are synchronous by default,
// so the async sequencing problems from the JS version don't exist here)
func main() {
	//db, _ := sql.Open("sqlite3", "./temp.db")
	createDatabase()
	/*
		createTables(db)
		insert(db, "John Doe", 30)
		rows, _ := db.Query("select * FROM students")
		defer rows.Close()
		for rows.Next() {
			var name string
			var age int
			rows.Scan(&name, &age)
			fmt.Printf("%s %d\n", name, age)
		}
	*/
}

func createDatabase() {
	fmt.Println("created database")
	newdb, err := sql.Open("sqlite3", "temp.db")
	if err != nil {
		fmt.Println("Getting error " + err.Error())
		os.Exit(1)
	}
	defer newdb.Close()
	createTables2(newdb)
}

func createTables(db *sql.DB) {
	fmt.Println("pre-created tables")
	_, err := db.Exec("CREATE TABLE students(name TEXT,age INT)")
	if err != nil {
		fmt.Println(err)
	}
}

func createTables2(db *sql.DB) {
	fmt.Println("pre-created tables")
	_, err := db.Exec("CREATE TABLE students(name TEXT,age INT)")
	if err != nil {
		fmt.Println(err)
	}
	insert(db, "John Doe", 30)
}

func insert(db *sql.DB, name string, age int) {
	sqlStmt := "insert into students(name, age) VALUES (?,?)"
	_, err := db.Exec(sqlStmt, name, age)
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	fmt.Println("row added successfully")
}