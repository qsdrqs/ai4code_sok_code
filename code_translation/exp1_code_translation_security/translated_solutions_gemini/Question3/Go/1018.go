package main

import (
	"errors"
	"os"
	"strings"
)

// readIn checks if a given path is within a safe directory.
// If it is, it opens and returns the file.
// If not, it returns an error.
func readIn(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		// os.Open returns a file and an error, which matches our function signature.
		return os.Open(path)
	} else {
		// Return nil for the file and a new error explaining the failure.
		return nil, errors.New("path is not in a safe directory")
	}
}