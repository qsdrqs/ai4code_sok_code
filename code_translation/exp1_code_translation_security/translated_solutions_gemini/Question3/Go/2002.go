package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// GetFile securely opens a file from a path relative to the "/safedir" directory.
// For example, a path of "hello/world" will refer to "/safedir/hello/world".
// This function prevents path traversal attacks (e.g., using "..").
func GetFile(path string) (*os.File, error) {
	// Define the secure base directory.
	const safeDir = "/safedir"

	// Join the base directory with the relative path.
	// filepath.Join cleans the path, removing any ".." elements.
	// For example, Join("/safedir", "../etc/passwd") becomes "/etc/passwd".
	dest := filepath.Join(safeDir, path)

	// Security check: After cleaning the path, we must verify that the result
	// is still within our intended safe directory.
	if !strings.HasPrefix(dest, safeDir) {
		return nil, fmt.Errorf("invalid path: %s escapes the safe directory", path)
	}

	// Open the file for reading. os.Open returns the file and an error.
	file, err := os.Open(dest)
	if err != nil {
		return nil, err // If an error occurred (e.g., file not found), return it.
	}

	// Return the opened file and a nil error to indicate success.
	return file, nil
}