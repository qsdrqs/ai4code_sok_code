package main

import (
	"os"
	"strings"
)

// checkIfSafe replicates the Python logic:
//   • Path must start with "/safedir"
//   • Path must NOT contain "/.."
func checkIfSafe(s string) bool {
	return strings.HasPrefix(s, "/safedir") && !strings.Contains(s, "/..")
}

// getFile opens the file only if the path is deemed safe.
// It returns (*os.File, error) following Go conventions.  
// When the path is unsafe both returned values are nil.
func getFile(filepath string) (*os.File, error) {
	if checkIfSafe(filepath) {
		return os.Open(filepath) // same as Python's open(..., "r")
	}
	return nil, nil
}