package main

import (
	"os"
	"path/filepath"
)

// path2obj opens the file at the given filePath and checks if any of its parent directories
// (up to the root) has a base name of "safedir". If found, it returns the open file handle.
// If not found, it returns nil. Note that the file is not closed in either case.
func path2obj(filePath string) *os.File {
	// Open the file. Ignore any error as per the original Python code.
	f, _ := os.Open(filePath)

	// Get the directory of the file path.
	directory := filepath.Dir(filePath)

	// In Python, os.path.dirname("file") returns "", but in Go, filepath.Dir("file") returns ".".
	// To match Python's behavior, we convert "." to "".
	if directory == "." {
		directory = ""
	}

	// Loop while directory is not empty.
	for directory != "" {
		// Normalize the directory path (similar to os.path.normpath in Python).
		normalizedDir := filepath.Clean(directory)

		// Get the base name of the normalized directory.
		base := filepath.Base(normalizedDir)

		// If the base name is "safedir", return the open file.
		if base == "safedir" {
			return f
		}

		// Move to the parent directory.
		newDir := filepath.Dir(directory)

		// Convert "." to "" to match Python's behavior.
		if newDir == "." {
			newDir = ""
		}

		// If the new directory is the same as the current one, we've reached the root.
		if newDir == directory {
			break
		}

		// Update the directory for the next iteration.
		directory = newDir
	}

	// If no "safedir" was found, return nil.
	return nil
}