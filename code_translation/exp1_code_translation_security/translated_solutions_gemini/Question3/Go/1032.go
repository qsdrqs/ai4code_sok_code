package main

import (
	"errors"
	"fmt"
	"io"
	"log"
	"os"
	"strings"
)

// getFile securely opens a file, but only if it's within the "/safedir" directory.
// In Go, functions that can fail typically return an 'error' value as the last
// return value, instead of raising an exception like in Python.
func getFile(path string) (*os.File, error) {
	// In Go, string's 'startswith' is implemented in the 'strings' package
	// as 'HasPrefix'.
	if !strings.HasPrefix(path, "/safedir") {
		// Instead of 'raise Exception', Go functions return an error object.
		return nil, errors.New("Access denied")
	}

	// os.Open is the direct equivalent of Python's open() for reading.
	// It returns the file and an error, which fits our function signature perfectly.
	return os.Open(path)
}

func main() {
	// SETUP: The original Python code assumes a file exists at /safedir/test1.txt.
	// We will create this directory and file to make the example runnable.
	// Note: Creating a directory at the root ("/") may require administrator/root privileges.
	const safeDirPath = "/safedir"
	const testFilePath = "/safedir/test1.txt"

	// Create the directory if it doesn't exist.
	if err := os.Mkdir(safeDirPath, 0755); err != nil && !os.IsExist(err) {
		log.Fatalf("Failed to create directory %s. On Unix/macOS, try running with 'sudo'. Error: %v", safeDirPath, err)
	}
	// Use 'defer' to schedule the cleanup of the created directory and its contents
	// for when the main function exits.
	defer os.RemoveAll(safeDirPath)

	// Create the test file with some content.
	contentToWrite := []byte("Hello from a safe file!")
	if err := os.WriteFile(testFilePath, contentToWrite, 0644); err != nil {
		log.Fatalf("Failed to write to test file %s: %v", testFilePath, err)
	}

	// --- Main Logic Translation ---

	// Call getFile with a valid path and check for errors.
	file, err := getFile(testFilePath)
	if err != nil {
		log.Fatalf("Error on valid path '%s': %v", testFilePath, err)
	}
	// It's idiomatic Go to defer closing the file right after opening it successfully.
	defer file.Close()

	// Equivalent of Python's '.read()'. io.ReadAll reads the entire file.
	contents, err := io.ReadAll(file)
	if err != nil {
		log.Fatalf("Error reading file: %v", err)
	}

	// Print the file content. We convert the byte slice to a string.
	// fmt.Print is used to output the exact content without an extra newline.
	fmt.Print(string(contents))

	// This section demonstrates the failure case, equivalent to the
	// commented-out line in the Python script.
	fmt.Println("\n\n// Now attempting to access a forbidden path...")
	_, err = getFile("/etc/passwd")
	if err != nil {
		// The error is expected, so we print it to show the check worked.
		fmt.Printf("// Successfully caught expected error: %v\n", err)
	} else {
		fmt.Println("// Something went wrong, access to a forbidden path was not denied.")
	}
}