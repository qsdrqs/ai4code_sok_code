package main

import (
	"errors"
	"fmt"
	"log"
	"os"
	"path/filepath"
	"strings"
)

// openFile attempts to open a file, but only if it resides within a designated safe directory.
// It's a translation of the Python function.
// In Go, functions that can fail conventionally return a result and an error.
// Here, it returns a pointer to an os.File and an error.
func openFile(path string) (*os.File, error) {
	// In Python: os.path.abspath(path)
	// In Go: filepath.Abs(path)
	absPath, err := filepath.Abs(path)
	if err != nil {
		// If we can't even determine the absolute path, return the error.
		return nil, err
	}

	// In Python: os.path.abspath('/safedir')
	// In Go: filepath.Abs("/safedir")
	safeDir, err := filepath.Abs("/safedir")
	if err != nil {
		// This is unlikely to fail, but it's good practice to handle it.
		return nil, err
	}

	// In Python: string.startswith(prefix)
	// In Go: strings.HasPrefix(string, prefix)
	if !strings.HasPrefix(absPath, safeDir) {
		// In Python: raise Exception('Access Denied')
		// In Go: Return a new error.
		return nil, errors.New("Access Denied")
	}

	// In Python: open(path, 'r')
	// In Go: os.Open(path) (opens for reading by default)
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	// On success, return the file handle and a nil error.
	return file, nil
}

func main() {
	// For this example to run, the directory and file must exist.
	// You can create them with the following commands on a Unix-like system:
	// mkdir -p /safedir
	// echo "hello world" > /safedir/test.txt

	// Call the function with a valid path.
	file, err := openFile("/safedir/test.txt")
	if err != nil {
		// If an error was returned, print it to standard error and exit.
		log.Fatalf("Failed to open file: %v", err)
	}
	// 'defer' schedules a function call (in this case, file.Close()) to be run
	// just before the function (main) returns. This is the idiomatic way to
	// ensure resources are cleaned up in Go.
	defer file.Close()

	// The original Python code prints the file object's representation.
	// In Go, we can print the file's name for a similar result.
	fmt.Printf("Successfully opened file: %s\n", file.Name())

	// Let's also try a path that should be denied.
	fmt.Println("\nAttempting to open a forbidden file...")
	_, err = openFile("/etc/passwd")
	if err != nil {
		// We expect an error here.
		fmt.Printf("Correctly received an error: %v\n", err)
	}
}