package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// SAFE_DIR is the root directory under which all file paths must reside.
const SAFE_DIR = "/safedir"

// getFile opens a file for reading after validating that the path is within SAFE_DIR.
// It returns a pointer to the opened file and an error (if any).
func getFile(path string) (*os.File, error) {
	// Normalize the path to resolve any relative components like . or ..
	normalizedPath := filepath.Clean(path)

	// Ensure the normalized path starts with the SAFE_DIR to prevent path traversal
	if !strings.HasPrefix(normalizedPath, SAFE_DIR) {
		return nil, fmt.Errorf("path must be in SAFE_DIR")
	}

	// Open the file using the original path (OS will normalize it again)
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	return file, nil
}