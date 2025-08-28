package main

import "fmt"

// Version 1: Direct Translation
// This version directly translates the string concatenation logic from JavaScript.
// WARNING: This method is highly vulnerable to SQL Injection and should NOT be used
// in production code. It is provided only for a literal translation.
func addStudent(name string, age int) string {
	// fmt.Sprintf is the Go equivalent of building a formatted string.
	// '%s' is for a string, and '%d' is for a decimal integer.
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	return sql
}

// Version 2: Idiomatic & Secure Go Version
// This is the standard, secure way to write database queries in Go.
// It returns the SQL query template and a separate slice of arguments.
// The database driver then safely combines them, preventing SQL Injection.
func addStudentSecure(name string, age int) (string, []interface{}) {
	sql := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	args := []interface{}{name, age}
	return sql, args
}

func main() {
	// --- Using the Direct Translation (Insecure) ---
	fmt.Println("--- 1. Direct (Insecure) Translation ---")

	// Example of normal usage
	sql1 := addStudent("Alice", 25)
	fmt.Println(sql1) // Output: INSERT INTO STUDENTS (NAME, AGE) VALUES ('Alice', 25)

	// Example demonstrating the SQL Injection vulnerability
	maliciousName := "Robert'); DROP TABLE STUDENTS; --"
	sql2 := addStudent(maliciousName, 30)
	fmt.Println("Vulnerable statement:", sql2) // Output: INSERT INTO STUDENTS (NAME, AGE) VALUES ('Robert'); DROP TABLE STUDENTS; --', 30)

	fmt.Println("\n--- 2. Idiomatic & Secure Go Version ---")

	// This approach separates the SQL command from the user-provided data.
	queryTemplate, queryArgs := addStudentSecure("Bob", 40)

	fmt.Println("Query Template:", queryTemplate)
	fmt.Printf("Arguments: %v\n", queryArgs)

	// In a real application, you would pass these to your database driver, like this:
	//
	// import "database/sql"
	// db, _ := sql.Open("driver-name", "connection-string")
	// _, err := db.Exec(queryTemplate, queryArgs...)
	//
	// The driver would safely handle the arguments.
}