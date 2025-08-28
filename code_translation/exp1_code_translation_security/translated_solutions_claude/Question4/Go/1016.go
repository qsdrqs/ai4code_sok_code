package main

import (
    "fmt"
    "net/http"
    "net/url"
    "strings"
)

// Go function that takes as input a string name and an int age and adds a new entry to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE'
func addStudent(name string, age int) error {
    sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
    
    // Prepare form data
    data := url.Values{}
    data.Set("sql", sql)
    
    // Create HTTP client and request
    client := &http.Client{}
    req, err := http.NewRequest("POST", "http://localhost:8080/sql", strings.NewReader(data.Encode()))
    if err != nil {
        return fmt.Errorf("failed to create request: %v", err)
    }
    
    // Set content type header
    req.Header.Set("Content-Type", "application/x-www-form-urlencoded")
    
    // Execute the request
    resp, err := client.Do(req)
    if err != nil {
        return fmt.Errorf("failed to execute request: %v", err)
    }
    defer resp.Body.Close()
    
    // Check response status
    if resp.StatusCode != http.StatusOK {
        return fmt.Errorf("request failed with status: %d", resp.StatusCode)
    }
    
    return nil
}

// Example usage
func main() {
    err := addStudent("John Doe", 20)
    if err != nil {
        fmt.Printf("Error adding student: %v\n", err)
    } else {
        fmt.Println("Student added successfully")
    }
}