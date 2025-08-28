package main

import (
	"fmt"
	"io"
	"log"
	"net/http"
	"net/url"
	"strconv"
)

// sqlInsert makes an HTTP GET request to a SQL API to insert a new student.
// It is an idiomatic Go translation of the JavaScript function.
// It returns the response body as a string and an error if one occurs.
func sqlInsert(name string, age int) (string, error) {
	// In Go, we must provide a full URL, including the scheme and host.
	// The original JS used a relative path, so we assume a host here.
	baseURL := "http://localhost/sqlapi.php"

	// Use the net/url package to safely build the query string.
	// This is better than string concatenation as it handles special characters.
	params := url.Values{}
	params.Add("action", "INSERTINTOSTUDENTS")
	params.Add("name", name)
	params.Add("age", strconv.Itoa(age)) // Convert integer to string

	// Construct the full URL
	fullURL := baseURL + "?" + params.Encode()

	// Make the HTTP GET request. http.Get is a convenient wrapper.
	resp, err := http.Get(fullURL)
	if err != nil {
		// This handles network errors (e.g., DNS failure, connection refused).
		return "", fmt.Errorf("failed to execute request: %w", err)
	}
	// It's crucial to close the response body to prevent resource leaks.
	// defer ensures this runs before the function returns.
	defer resp.Body.Close()

	// Check if the HTTP status code is 200 OK.
	// This is equivalent to `this.status == 200` in the JavaScript code.
	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("request failed with status: %s", resp.Status)
	}

	// Read the entire response body.
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return "", fmt.Errorf("failed to read response body: %w", err)
	}

	// Return the response body as a string and a nil error to indicate success.
	return string(body), nil
}

func main() {
	// This is the Go equivalent of calling the function and handling the result.
	// The `if err != nil` block is the equivalent of the `.catch()` block.
	// The code after the error check is the equivalent of the `.then()` block.
	result, err := sqlInsert("XXXXX", 0)
	if err != nil {
		// log.Fatalf prints the error and exits the program.
		// This is similar to console.error() followed by process termination.
		log.Fatalf("error: %v", err)
	}

	// This code runs only if err is nil (the request was successful).
	fmt.Printf("result: %s\n", result)
}