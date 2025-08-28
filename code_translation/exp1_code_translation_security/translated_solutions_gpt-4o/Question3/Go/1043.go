package main

import (
	"fmt"
	"os"
	"path/filepath"
	"regexp"
)

// searchFileRe searches for a file in a path using a regular expression.
func searchFileRe(filePath string) string {
	// Define the base directory to search in
	baseDir := "/safedir"

	// Compile the regular expression
	re, err := regexp.Compile(filePath)
	if err != nil {
		fmt.Println("Error compiling regex:", err)
		return ""
	}

	// Walk through the directory
	var result string
	err = filepath.Walk(baseDir, func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return err
		}

		// Check if the current file matches the regex
		if !info.IsDir() && re.MatchString(info.Name()) {
			result = path
			return filepath.SkipDir // Stop further traversal once a match is found
		}
		return nil
	})

	if err != nil {
		fmt.Println("Error walking the directory:", err)
		return ""
	}

	return result
}

func main() {
	// Call the searchFileRe function
	filePath := searchFileRe("test12.txt")

	// Print the result
	fmt.Println(filePath)
}