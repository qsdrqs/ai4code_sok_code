package main

import (
	"os"
	"strings"
)

func openFile(path string) (*os.File, error) {
	// Split the path into head, sep, and tail
	parts := strings.SplitN(path, "/safedir", 2)
	if len(parts) < 2 {
		return nil, os.ErrNotExist // Handle case where "/safedir" is not found
	}

	// Construct the new path
	newPath := "/safedir" + parts[1]

	// Open the file
	file, err := os.Open(newPath)
	if err != nil {
		return nil, err
	}

	return file, nil
}