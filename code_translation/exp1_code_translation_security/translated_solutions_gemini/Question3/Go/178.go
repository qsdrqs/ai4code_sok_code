package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// In Go, constants are declared with the `const` keyword.
const allowedPath = "/safedir"

// getFile takes a file path and returns a pointer to a file object (*os.File) and an error.
// This two-value return (result, error) is the standard way to handle potential failures in Go.
func getFile(path string) (*os.File, error) {
	// Instead of slicing, Go's `strings.HasPrefix` is a safer and more readable
	// way to check if a string starts with a specific substring.
	if !strings.HasPrefix(path, allowedPath) {
		// If the path is not allowed, we return `nil` for the file
		// and a new error with a descriptive message.
		return nil, errors.New("not allowed to get file from this dir")
	}

	// os.Open is the Go equivalent of Python's open(path, 'r').
	// It returns a file object and an error, which we can directly return
	// to the caller of our function.
	file, err := os.Open(path)
	if err != nil {
		// If os.Open failed (e.g., file not found), we pass the error up.
		return nil, err
	}

	// If everything is successful, we return the file and a `nil` error.
	return file, nil
}

func main() {
	// To run this example, create a directory named "safedir" and a file inside it.
	// For example:
	// mkdir safedir
	// echo "hello world" > safedir/test.txt

	fmt.Println("--- Attempting a valid path ---")
	// We call the function and check the returned error.
	file, err := getFile("/safedir/test.txt")
	if err != nil {
		// If the error is not nil, something went wrong.
		fmt.Printf("Error: %v\n", err)
	} else {
		// If the error is nil, the call was successful.
		fmt.Printf("Successfully opened file: %s\n", file.Name())
		// It's crucial to close the file when you're done with it.
		// `defer` schedules the Close() call to run just before the function exits.
		defer file.Close()
	}

	fmt.Println("\n--- Attempting an invalid path ---")
	// Here we expect an error because the path is outside "/safedir".
	_, err = getFile("/etc/passwd")
	if err != nil {
		fmt.Printf("Error: %v\n", err)
	} else {
		fmt.Println("This should not have succeeded!")
	}
}