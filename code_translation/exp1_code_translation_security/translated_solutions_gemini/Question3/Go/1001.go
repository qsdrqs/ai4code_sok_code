package main

import (
	"errors" // Used to create custom error messages
	"fmt"    // Used for printing output in the main function example
	"os"     // Provides functions for interacting with the operating system, like opening files
	"strings"// Provides string manipulation functions like HasPrefix and Contains
)

// checkIfSafe checks if a given string represents a safe file path.
// A path is considered safe if it starts with "/safedir" and does not
// contain "/..", which could be used for directory traversal attacks.
func checkIfSafe(mystring string) bool {
	// In Go, string.startswith() is strings.HasPrefix()
	// and "in" is strings.Contains()
	return strings.HasPrefix(mystring, "/safedir") && !strings.Contains(mystring, "/..")
}

// getFile attempts to open a file at the given filepath.
// It first checks if the path is safe using checkIfSafe.
// In Go, it's idiomatic to return a value and an error.
// If the operation is successful, the error will be nil.
// If it fails, the file will be nil and the error will describe the problem.
func getFile(filepath string) (*os.File, error) {
	if checkIfSafe(filepath) {
		// os.Open returns a file pointer (*os.File) and an error.
		// We can directly return its result.
		file, err := os.Open(filepath)
		if err != nil {
			// This handles cases where the file doesn't exist, permissions are wrong, etc.
			return nil, fmt.Errorf("could not open safe file: %w", err)
		}
		return file, nil
	} else {
		// Instead of returning `None` like in Python, Go returns an explicit error.
		// This is safer and more descriptive.
		return nil, errors.New("unsafe file path provided")
	}
}

// main function to demonstrate the usage of the translated code.
func main() {
	// Create a dummy safe directory and file for demonstration
	os.MkdirAll("/safedir", 0755)
	os.WriteFile("/safedir/test.txt", []byte("hello world"), 0644)

	// --- Test Case 1: Safe Path ---
	fmt.Println("Attempting to open a safe file: /safedir/test.txt")
	file, err := getFile("/safedir/test.txt")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened.")
		// It's crucial to close the file when you're done with it.
		// `defer` ensures this runs even if errors occur later in the function.
		defer file.Close()
	}

	fmt.Println("---")

	// --- Test Case 2: Unsafe Path (Directory Traversal) ---
	fmt.Println("Attempting to open an unsafe file: /safedir/../someotherfile.txt")
	_, err = getFile("/safedir/../someotherfile.txt")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened. (This should not happen)")
	}

	fmt.Println("---")

	// --- Test Case 3: Unsafe Path (Wrong Directory) ---
	fmt.Println("Attempting to open an unsafe file: /etc/passwd")
	_, err = getFile("/etc/passwd")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened. (This should not happen)")
	}

	// Clean up the dummy file and directory
	os.RemoveAll("/safedir")
}