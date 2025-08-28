package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// get_file securely reads a file, ensuring it's within the /safedir directory.
// In Go, functions that can fail typically return a result and an error value.
// This is the idiomatic replacement for Python's try/except blocks.
func get_file(path string) ([]byte, error) {
	// Check if the path starts with the allowed prefix.
	if strings.HasPrefix(path, "/safedir") {
		// os.ReadFile reads the entire file and returns its contents as a byte slice.
		// It's a direct equivalent of Python's open(path).read().
		// It also returns an error if one occurs (e.g., file not found).
		return os.ReadFile(path)
	} else {
		// If the check fails, return an error, similar to raising an exception in Python.
		return nil, errors.New("Access denied")
	}
}

func main() {
	// This block is equivalent to the first try/except in the Python code.
	// We call the function and check if the returned error is non-nil.
	content, err := get_file("/safedir/random.txt")
	if err != nil {
		// This branch executes if get_file returned an error,
		// either from our "Access denied" check or from an underlying
		// file system error (like file not found).
		fmt.Println("Error with /safedir/random.txt")
	} else {
		// If err is nil, the call was successful.
		// We print the content, converting the byte slice to a string.
		fmt.Println(string(content))
	}

	// This block is equivalent to the second try/except in the Python code.
	// We use the blank identifier _ because we don't need the content, only the error.
	_, err = get_file("/etc/passwd")
	if err != nil {
		// This branch is expected to execute because the path is not allowed.
		fmt.Println("Access was denied")
	}
	// There's no 'else' block here, just as the Python example doesn't
	// have code that runs on success for the second case.
}