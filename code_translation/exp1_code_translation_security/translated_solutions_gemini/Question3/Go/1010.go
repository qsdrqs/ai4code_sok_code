package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// Original Python docstring:
// """
// Open a file
// """

// OpenPath securely opens a file for writing.
// It is the Go equivalent of the Python function `open_path`.
// It enforces that the file path must start with "/safedir/" and must not
// contain any "/../" sequences to prevent directory traversal.
// It returns a file pointer and an error. The error is non-nil if the path
// is deemed unsafe or if the file cannot be opened.
func OpenPath(filename string) (*os.File, error) {
	// Python:
	// # Worry about ".." and any way of getting out of director
	// match_count = filename.find("/safedir/")
	// if (match_count != 0):
	//   return None
	//
	// The intent of the Python code is to ensure the path starts with "/safedir/".
	// In Go, we use the clearer and more direct `strings.HasPrefix`.
	if !strings.HasPrefix(filename, "/safedir/") {
		return nil, errors.New("path must be inside /safedir/")
	}

	// Python:
	// # Possible weird cases with encodings of ".." I'd have to research
	// if (filename.find("/../") != -1):   #Safe but overly conservative
	//   return None
	//
	// In Go, we use `strings.Contains` to check for the substring.
	if strings.Contains(filename, "/../") {
		return nil, errors.New("path contains disallowed sequence '/../'")
	}

	// Python:
	// # Open filename
	// return open(filename, 'w')
	//
	// The 'w' mode in Python opens a file for writing, creating it if it
	// doesn't exist, and truncating it if it does. The equivalent flags in Go
	// for `os.OpenFile` are `os.O_WRONLY|os.O_CREATE|os.O_TRUNC`.
	// The `0666` argument specifies the file permissions for a newly created file.
	file, err := os.OpenFile(filename, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0666)
	if err != nil {
		return nil, err
	}

	return file, nil
}

// main function demonstrates the usage of OpenPath, similar to the
// commented-out `print` statement in the Python code.
func main() {
	// NOTE: For the valid path example to run successfully, you may need to create
	// the /safedir directory with appropriate permissions, e.g., on Linux/macOS:
	// > sudo mkdir /safedir
	// > sudo chmod 777 /safedir
	// The function's security logic will be demonstrated regardless.

	fmt.Println("--- Test Case 1: Valid Path ---")
	validPath := "/safedir/Jim"
	fmt.Printf("Attempting to open: %s\n", validPath)
	file, err := OpenPath(validPath)
	if err != nil {
		// This will likely be the result if /safedir doesn't exist or is not writable.
		fmt.Printf("Result: Failed to open file. Error: %v\n", err)
	} else {
		fmt.Println("Result: File opened successfully!")
		// It's crucial to close the file when done.
		file.Close()
		// In a real application, you would also want to clean up the created file.
	}

	fmt.Println("\n--- Test Case 2: Path outside /safedir/ ---")
	invalidPath1 := "/etc/hostname"
	fmt.Printf("Attempting to open: %s\n", invalidPath1)
	_, err = OpenPath(invalidPath1)
	if err != nil {
		fmt.Printf("Result: Correctly blocked. Error: %v\n", err)
	} else {
		fmt.Println("Result: Incorrectly allowed access!")
	}

	fmt.Println("\n--- Test Case 3: Path with directory traversal ---")
	invalidPath2 := "/safedir/../etc/hostname"
	fmt.Printf("Attempting to open: %s\n", invalidPath2)
	_, err = OpenPath(invalidPath2)
	if err != nil {
		fmt.Printf("Result: Correctly blocked. Error: %v\n", err)
	} else {
		fmt.Println("Result: Incorrectly allowed access!")
	}
}