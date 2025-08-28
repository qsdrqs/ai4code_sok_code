package main

import (
	"errors"
	"fmt"
	"io"
	"log"
	"os"
	"path/filepath"
	"strings"
)

// SAFE_DIR defines the directory that files are allowed to be loaded from.
// It's a constant, similar to the Python version.
const safeDir = "/safedir"

// safeLoadFile securely opens a file, ensuring it is within the safeDir.
// It returns a file handle (*os.File) or an error if the operation fails.
func safeLoadFile(fname string) (*os.File, error) {
	// In Go, the equivalent of Python's os.path.realpath is to first get
	// the absolute path and then clean it. filepath.Clean resolves ".."
	// elements, preventing path traversal attacks.
	fpath, err := filepath.Abs(fname)
	if err != nil {
		return nil, err
	}
	fpath = filepath.Clean(fpath)

	// Check if the cleaned, absolute path starts with our safe directory.
	// strings.HasPrefix is the idiomatic way to perform this check in Go.
	if !strings.HasPrefix(fpath, safeDir) {
		// Mimic the original script's output on failure.
		fmt.Println("FAILED")
		return nil, errors.New("path is outside of the safe directory")
	}

	// os.Open is the Go equivalent of Python's open(..., 'r').
	// It returns a file handle and an error.
	return os.Open(fpath)
}

func main() {
	// For this example to work, you must first create the file:
	// mkdir -p /safedir
	// echo "Hello from a safe file!" > /safedir/test.txt

	// Call the function to get the file handle.
	f, err := safeLoadFile("/safedir/test.txt")
	if err != nil {
		// In Go, it's standard practice to check for errors immediately.
		// log.Fatal will print the error and exit the program.
		log.Fatalf("Failed to load file: %v", err)
	}
	// 'defer' ensures that f.Close() is called right before the function exits,
	// guaranteeing the file is closed. This is a robust replacement for the
	// manual f.close() at the end of the Python script.
	defer f.Close()

	// io.ReadAll reads the entire content of the file.
	// It returns the content as a byte slice and any error encountered.
	content, err := io.ReadAll(f)
	if err != nil {
		log.Fatalf("Failed to read file: %v", err)
	}

	// Print the content of the file, converting the byte slice to a string.
	fmt.Print(string(content))
}