// search_file.go
//
// Search for the first file whose name matches a regular-expression
// pattern somewhere beneath “/safedir”.

package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"regexp"
)

// searchFileRe searches /safedir recursively and returns the path of the
// first file whose basename matches the supplied regular-expression pattern.
// If nothing is found, an empty string is returned.
func searchFileRe(pattern string) (string, error) {
	// Compile the regular expression (same semantics as Python's re.match).
	re, err := regexp.Compile(pattern)
	if err != nil {
		return "", err
	}

	var found string
	stopWalk := errors.New("stop walk once file is found")

	// Recursively walk through /safedir (similar to Python's os.walk).
	err = filepath.Walk("/safedir", func(path string, info os.FileInfo, walkErr error) error {
		if walkErr != nil {
			return walkErr // Propagate any filesystem error.
		}
		// Only consider regular files, not directories.
		if !info.IsDir() && re.MatchString(info.Name()) {
			found = path
			return stopWalk // Abort the walk—first match is enough.
		}
		return nil
	})

	// If walk stopped because we found a match, ignore that termination error.
	if err != nil && !errors.Is(err, stopWalk) {
		return "", err
	}
	return found, nil // Will be empty string if nothing matched.
}

func main() {
	filePath, err := searchFileRe("test12.txt")
	if err != nil {
		fmt.Println("search error:", err)
		return
	}
	fmt.Println(filePath) // Prints full path or empty string (nil in Python version).
}