package main

import (
	"os"
	"strings"
)

// checkIfSafe checks if the given string starts with "/safedir" and does not contain "/.."
func checkIfSafe(mystring string) bool {
	return strings.HasPrefix(mystring, "/safedir") && !strings.Contains(mystring, "/..")
}

// getFile opens the file if the path is safe, otherwise returns nil and nil error
func getFile(filepath string) (*os.File, error) {
	if checkIfSafe(filepath) {
		return os.Open(filepath)
	}
	return nil, nil
}