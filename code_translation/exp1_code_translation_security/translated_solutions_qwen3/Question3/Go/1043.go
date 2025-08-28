package main

import (
	"fmt"
	"os"
	"path/filepath"
	"regexp"
)

// searchFileRe searches for a file in the directory tree rooted at "/safedir"
// whose name matches the given regular expression pattern.
// Returns the full path of the first matching file, or an empty string if none is found.
func searchFileRe(filePattern string) string {
	// Compile the regular expression
	regex, err := regexp.Compile(filePattern)
	if err != nil {
		// If the regex is invalid, return an empty string (similar to Python's None)
		return ""
	}

	var matchedPath string

	// Walk through the directory tree
	filepath.Walk("/safedir", func(path string, info os.FileInfo, err error) error {
		if err != nil {
			// Skip files/dirs we can't access
			return nil
		}

		// Only consider files (not directories)
		if !info.IsDir() {
			// Check if the file name matches the regex
			if regex.MatchString(info.Name()) {
				matchedPath = path
				// Return a non-nil error to stop the walk early
				return fmt.Errorf("match found")
			}
		}

		return nil
	})

	return matchedPath
}

// main is the entry point of the program
func main() {
	// Search for a file matching "test12.txt"
	filePath := searchFileRe("test12.txt")
	// Print the result
	fmt.Println(filePath)
}