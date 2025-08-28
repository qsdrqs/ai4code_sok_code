package main

import (
	"os"
	"strings"
)

// openFile behaves like the Python version:
// 1. Split the incoming path at the first occurrence of "/safedir".
// 2. Keep only the part starting with "/safedir" (sep+tail in Python).
// 3. Open that shortened path and return the file handle.
func openFile(path string) (*os.File, error) {
	const sep = "/safedir"

	// Find first occurrence of "/safedir".
	idx := strings.Index(path, sep)

	var newPath string
	if idx == -1 {
		// Separator not found -> behave like Python's partition (sep = "", tail = path)
		newPath = path
	} else {
		// Keep "/safedir" and everything after it.
		newPath = path[idx:]
	}

	// Open the resulting path; caller must close the file.
	return os.Open(newPath)
}