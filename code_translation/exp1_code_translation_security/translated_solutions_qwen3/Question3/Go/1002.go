package main

import (
	"os"
	"path/filepath"
	"strings"
	"testing"
)

// openFile attempts to open a file if its resolved path starts with "/safedir".
// It returns the file handle and an error (if any).
func openFile(filename string) (*os.File, error) {
	// Resolve the absolute path and clean it (remove . and ..)
	absPath, err := filepath.Abs(filename)
	if err != nil {
		return nil, err
	}
	absPath = filepath.Clean(absPath)

	// Split the path into components
	components := strings.Split(absPath, string(filepath.Separator))

	// Check if the second component is "safedir"
	if len(components) >= 2 && components[1] == "safedir" {
		// Attempt to open the file
		return os.Open(absPath)
	}

	// If the path does not start with /safedir, return nil and no error
	return nil, nil
}

// TestOpenFile verifies the behavior of openFile as described in the original Python test.
func TestOpenFile(t *testing.T) {
	// Test 1: Path resolves to /a/b, which is not under /safedir
	file, err := openFile("/safedir/../a/b")
	if file != nil || err != nil {
		t.Errorf("Expected nil file and nil error for path '/safedir/../a/b', got file: %v, error: %v", file, err)
	}

	// Test 2: Path resolves to /safedir/a, which is under /safedir
	// We expect an error because the file likely does not exist
	file, err = openFile("/safedir/a")
	if file != nil || err == nil {
		t.Errorf("Expected nil file and non-nil error for path '/safedir/a', got file: %v, error: %v", file, err)
	}
}