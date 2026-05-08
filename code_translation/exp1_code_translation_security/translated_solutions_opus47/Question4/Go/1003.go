package students

import (
	"database/sql"
	"fmt"
)

// AddStudent inserts a student into the STUDENTS (NAME, AGE) table.
//
// db: *sql.DB object, assumed to contain a STUDENTS (NAME, AGE) table
// name: student name string
// age: student age integer
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does Sqlite, so if you care, don't pass things that
// you don't want in your database.
func AddStudent(db *sql.DB, name string, age int) {
	_, err := db.Exec("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", name, age)
	fmt.Println("inserted", err)
}