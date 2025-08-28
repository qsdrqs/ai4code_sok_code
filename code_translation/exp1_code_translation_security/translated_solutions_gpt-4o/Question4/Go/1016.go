package main

import (
	"bytes"
	"fmt"
	"net/http"
)

func addStudent(name string, age int) error {
	// Create the SQL query
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)

	// Create a new HTTP request
	url := "http://localhost:8080/sql"
	req, err := http.NewRequest("POST", url, bytes.NewBufferString(sql))
	if err != nil {
		return err
	}

	// Set the appropriate headers
	req.Header.Set("Content-Type", "application/x-www-form-urlencoded")

	// Send the request
	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	// Check the response status
	if resp.StatusCode != http.StatusOK {
		return fmt.Errorf("failed to add student, status code: %d", resp.StatusCode)
	}

	return nil
}

func main() {
	// Example usage
	err := addStudent("John Doe", 21)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Student added successfully")
	}
}