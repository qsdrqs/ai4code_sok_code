package main

import (
	"errors"
	"fmt"
	"io"
	"os"
	"strings"
)

// get_file checks if a path is within a safe directory before opening it.
// In Go, functions that can fail typically return a result and an error.
// Here, it returns a pointer to an os.File and an error.
func get_file(path string) (*os.File, error) {
	// Python's `startswith` is `strings.HasPrefix` in Go.
	if strings.HasPrefix(path, "/safedir") {
		// os.Open returns (*os.File, error), which matches our
		// function's return signature perfectly.
		return os.Open(path)
	} else {
		// Instead of raising an exception, we return a nil file pointer
		// and a new error.
		return nil, errors.New("Access denied")
	}
}

func main() {
	// --- Setup: Create a dummy file for the example to work ---
	// This part is just to make the example runnable.
	if err := os.MkdirAll("/safedir", 0755); err != nil {
		panic(err)
	}
	if err := os.WriteFile("/safedir/test1.txt", []byte("Hello from a safe file!\n"), 0644); err != nil {
		panic(err)
	}
	// --- End Setup ---

	// First call: A valid path
	// We call the function and receive two values: the file and a potential error.
	file1, err := get_file("/safedir/test1.txt")
	if err != nil {
		// This block runs if the error is not nil.
		fmt.Printf("Error getting first file: %v\n", err)
	} else {
		// If there's no error, we can use the file.
		// `defer file.Close()` is a Go idiom to ensure the file is closed
		// when the function (main) exits.
		defer file1.Close()

		// io.ReadAll reads the entire file into a byte slice.
		content, readErr := io.ReadAll(file1)
		if readErr != nil {
			fmt.Printf("Error reading first file: %v\n", readErr)
		} else {
			// We convert the byte slice to a string to print it.
			// Using fmt.Print to match Python's print function behavior.
			fmt.Print(string(content))
		}
	}

	fmt.Println("---")

	// Second call: An invalid path
	// The Python code would crash here. The Go code handles the error gracefully.
	_, err = get_file("/etc/passwd")
	if err != nil {
		// The error returned from get_file will be caught here.
		fmt.Printf("Error getting second file: %v\n", err)
	} else {
		// This block will not be executed.
		fmt.Println("Successfully accessed /etc/passwd (this should not happen)")
	}

	// --- Cleanup: Remove the dummy file and directory ---
	os.RemoveAll("/safedir")
	// --- End Cleanup ---
}