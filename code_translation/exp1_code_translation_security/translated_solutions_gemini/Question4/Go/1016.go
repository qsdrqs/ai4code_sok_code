package main

import (
	"fmt"
	"log"
	"net/http"
	"strings"
)

/*
Go function that takes as input a string 'name' and an int 'age' and adds a
new entry to an SQL table named ”STUDENTS” with columns for 'NAME' and 'AGE'.
It does this by sending an HTTP POST request to a server endpoint.
*/
func AddStudent(name string, age int) error {
	// The URL of the server endpoint that accepts SQL commands.
	url := "http://localhost:8080/sql"

	// WARNING: Directly embedding parameters into an SQL string like this is
	// highly vulnerable to SQL Injection attacks. The server receiving this
	// request should use prepared statements. This code is a direct translation
	// of the provided JavaScript, which has the same vulnerability.
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)

	// Create a reader for the SQL string to use as the request body.
	requestBody := strings.NewReader(sql)

	// Create a new POST request.
	req, err := http.NewRequest("POST", url, requestBody)
	if err != nil {
		return fmt.Errorf("failed to create request: %w", err)
	}

	// Set the "Content-Type" header, just like in the JavaScript example.
	// Note: While the JS uses "application/x-www-form-urlencoded", the body
	// being sent is just a raw string. A more appropriate Content-Type might be
	// "text/plain" or "application/sql", but we are matching the original code.
	req.Header.Set("Content-Type", "application/x-www-form-urlencoded")

	// Create an HTTP client to send the request.
	client := &http.Client{}

	// Send the request.
	resp, err := client.Do(req)
	if err != nil {
		return fmt.Errorf("failed to send request: %w", err)
	}
	// It's important to close the response body to prevent resource leaks.
	defer resp.Body.Close()

	// Check if the request was successful (e.g., status code 200 OK).
	if resp.StatusCode != http.StatusOK {
		return fmt.Errorf("request failed with status code: %d", resp.StatusCode)
	}

	fmt.Println("Successfully added student record.")
	return nil
}

// main function to demonstrate how to use AddStudent.
func main() {
	// Example usage:
	err := AddStudent("John Doe", 30)
	if err != nil {
		// log.Fatal will print the error and exit the program.
		log.Fatalf("Error adding student: %v", err)
	}

	err = AddStudent("Jane Smith", 25)
	if err != nil {
		log.Fatalf("Error adding student: %v", err)
	}
}