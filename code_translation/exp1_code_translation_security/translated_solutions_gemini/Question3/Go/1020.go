package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// funcPath translates the Python function. It checks if a path contains "/safedir"
// and, if so, attempts to open it.
//
// In Go, it's idiomatic for functions that can fail (like opening a file)
// to return two values: the result (*os.File) and an error.
func funcPath(path string) (*os.File, error) {
	// The 'in' operator in Python checks for a substring.
	// The equivalent in Go is strings.Contains().
	if strings.Contains(path, "/safedir") {
		// os.Open is the Go equivalent of Python's open().
		// It returns a file object (*os.File) and an error, which we
		// return directly to the caller.
		return os.Open(path)
	} else {
		// If the path is not "safe", we return a nil file pointer and a new error,
		// which is the idiomatic way to handle the Python version's string return.
		return nil, errors.New("file not found or unsafe for access")
	}
}

func main() {
	// To make this example runnable, we'll create the necessary directory and file.
	// The original Python code would require "/safedir/Jim" to exist.
	os.MkdirAll("safedir", 0755) // Create directory if it doesn't exist
	dummyFile, _ := os.Create("safedir/Jim")
	dummyFile.Close()

	// --- Test Case 1: Safe Path (Success) ---
	fmt.Println("Attempting to open 'safedir/Jim'...")
	// Call the function with a path that should be accepted.
	// Note: We use a relative path to make the example work anywhere.
	file, err := funcPath("safedir/Jim")

	// In Go, you explicitly check for errors, which is different from Python's try/except.
	if err != nil {
		// This block handles both the "unsafe path" error and file system errors.
		fmt.Println("Error:", err)
	} else {
		// The Python code prints the file object's representation.
		// Here, we print a success message and the file's name.
		fmt.Println("Successfully opened file:", file.Name())
		// CRITICAL: In Go, you must explicitly close files you open.
		// 'defer' ensures file.Close() is called right before main() exits.
		defer file.Close()
	}

	// --- Test Case 2: Unsafe Path (Failure) ---
	fmt.Println("\nAttempting to open 'another/dir/file.txt'...")
	_, err = funcPath("another/dir/file.txt")
	if err != nil {
		fmt.Println("Error:", err)
	}

	// Clean up the dummy file and directory created for the example.
	os.Remove("safedir/Jim")
	os.Remove("safedir")
}