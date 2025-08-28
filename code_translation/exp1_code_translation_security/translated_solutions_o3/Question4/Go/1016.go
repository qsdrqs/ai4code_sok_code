package main

import (
	"bytes"
	"fmt"
	"net/http"
)

// addStudent takes a student name and age and posts an SQL INSERT statement
// to http://localhost:8080/sql using the same mechanism as the JavaScript code.
func addStudent(name string, age int) error {
	// Build the INSERT statement
	sqlStmt := fmt.Sprintf(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
		name, age,
	)

	// Create the POST request
	req, err := http.NewRequest(
		http.MethodPost,
		"http://localhost:8080/sql",
		bytes.NewBufferString(sqlStmt),
	)
	if err != nil {
		return err
	}

	// Set the same header used in the JS version
	req.Header.Set("Content-Type", "application/x-www-form-urlencoded")

	// Execute the request (default client is fine here)
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	// Optional: treat non-2xx status codes as error
	if resp.StatusCode < http.StatusOK || resp.StatusCode >= http.StatusMultipleChoices {
		return fmt.Errorf("server returned status %s", resp.Status)
	}

	return nil
}

func main() {
	// Example usage
	if err := addStudent("Alice", 21); err != nil {
		fmt.Println("failed:", err)
	}
}