package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// SafeDir is the directory where files are allowed to be accessed.
const SafeDir = "/safedir"

// GetFile securely opens a file, ensuring its path is within SafeDir.
// It returns a pointer to the opened file or an error if the path is
// not in the safe directory or if the file cannot be opened.
func GetFile(path string) (*os.File, error) {
	// filepath.Clean normalizes a path, resolving ".." and "." elements.
	// This is crucial for preventing directory traversal attacks.
	// It is the Go equivalent of Python's os.path.normpath.
	cleanedPath := filepath.Clean(path)

	// Check if the cleaned, canonical path starts with the safe directory prefix.
	if !strings.HasPrefix(cleanedPath, SafeDir) {
		return nil, errors.New("path must be in SAFE_DIR")
	}

	// os.Open opens a file for reading. It is a wrapper around
	// os.OpenFile(name, O_RDONLY, 0). This is equivalent to Python's open(path, 'r').
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	return file, nil
}

// Example usage (optional, for demonstration)
func main() {
	// This is a placeholder. In a real scenario, you would create these files/dirs.
	fmt.Println("Demonstrating GetFile function.")
	fmt.Printf("SAFE_DIR is set to: %s\n\n", SafeDir)

	// --- Test Cases ---

	// 1. Valid path
	// In a real filesystem, you would run:
	// mkdir -p /safedir
	// echo "hello" > /safedir/test.txt
	fmt.Println("Attempting to access a valid path: /safedir/test.txt")
	// _, err := GetFile("/safedir/test.txt")
	// if err != nil {
	// 	fmt.Printf("  -> FAILED: %v\n", err)
	// } else {
	// 	fmt.Println("  -> SUCCESS")
	// }
	// Since we can't create files here, we'll just show the expected logic.
	fmt.Println("  -> This would succeed if the file exists.")

	// 2. Invalid path (outside SAFE_DIR)
	fmt.Println("\nAttempting to access an invalid path: /etc/passwd")
	_, err := GetFile("/etc/passwd")
	if err != nil {
		fmt.Printf("  -> SUCCESS (Correctly blocked): %v\n", err)
	} else {
		fmt.Println("  -> FAILED (Incorrectly allowed access)")
	}

	// 3. Path traversal attempt
	fmt.Println("\nAttempting path traversal: /safedir/../etc/passwd")
	_, err = GetFile("/safedir/../etc/passwd")
	if err != nil {
		fmt.Printf("  -> SUCCESS (Correctly blocked): %v\n", err)
	} else {
		fmt.Println("  -> FAILED (Incorrectly allowed access)")
	}
}