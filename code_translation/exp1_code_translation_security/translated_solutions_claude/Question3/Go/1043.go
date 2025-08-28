package main

import (
	"fmt"
	"os"
	"path/filepath"
	"regexp"
)

// searchFileRe searches for a file matching the given pattern in /safedir
func searchFileRe(filePattern string) string {
	var result string
	
	// Compile the regular expression
	re, err := regexp.Compile(filePattern)
	if err != nil {
		return ""
	}
	
	// Walk through the directory tree
	err = filepath.Walk("/safedir", func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return nil // Continue walking even if there's an error
		}
		
		// Check if it's a file (not a directory)
		if !info.IsDir() {
			// Get just the filename from the full path
			filename := filepath.Base(path)
			
			// Check if the filename matches the pattern
			if re.MatchString(filename) {
				result = path
				return filepath.SkipAll // Stop walking once we find a match
			}
		}
		
		return nil
	})
	
	if err != nil {
		return ""
	}
	
	return result
}

func main() {
	filePath := searchFileRe("test12.txt")
	fmt.Println(filePath)
}