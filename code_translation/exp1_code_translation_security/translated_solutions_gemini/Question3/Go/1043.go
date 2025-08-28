package main

import (
	"errors"
	"fmt"
	"io/fs"
	"log"
	"path/filepath"
	"regexp"
)

// errFound is a sentinel error used to stop the filepath.WalkDir function
// once a matching file has been found. This is more efficient than walking
// the entire directory tree.
var errFound = errors.New("file found")

// searchFileRe searches for a file recursively starting from a root directory.
// It returns the full path of the first file whose name matches the given
// regular expression pattern.
//
// The pattern matching is anchored to the start of the filename to mimic
// the behavior of Python's `re.match()`.
//
// It returns the found path and a nil error on success.
// If no file is found, it returns an empty string and a nil error.
// If an error occurs during the search (e.g., invalid pattern, permissions),
// it returns an empty string and the error.
func searchFileRe(pattern string) (string, error) {
	// In Go, regex patterns must be compiled first. We add `^` to the pattern
	// to ensure it matches from the beginning of the filename, just like Python's re.match.
	re, err := regexp.Compile("^" + pattern)
	if err != nil {
		return "", fmt.Errorf("invalid regex pattern: %w", err)
	}

	var foundPath string
	searchRoot := "/safedir"

	// filepath.WalkDir walks the file tree rooted at searchRoot.
	walkErr := filepath.WalkDir(searchRoot, func(path string, d fs.DirEntry, err error) error {
		// If WalkDir encounters an error visiting a file or directory, pass it up.
		if err != nil {
			return err
		}

		// We are only interested in files, not directories.
		if d.IsDir() {
			return nil // Continue walking
		}

		// Check if the file's name matches the compiled regex.
		if re.MatchString(d.Name()) {
			foundPath = path
			return errFound // Return the sentinel error to stop the walk.
		}

		return nil // No match, continue walking.
	})

	// If the walk was stopped because we found the file, it's a success.
	// We clear the error and return the path.
	if walkErr == errFound {
		return foundPath, nil
	}

	// If a different error occurred during the walk, return that error.
	if walkErr != nil {
		return "", walkErr
	}

	// If the walk completed without finding a file, return an empty path and no error.
	return "", nil
}

// main is the entry point of the program.
func main() {
	// Search for a file matching the pattern "test12.txt".
	filePath, err := searchFileRe("test12.txt")
	if err != nil {
		// If an error occurred (e.g., permissions), log it and exit.
		log.Fatalf("Error during file search: %v", err)
	}

	// The original Python code prints "None" if the file is not found.
	// We replicate that behavior by checking if the returned path is empty.
	if filePath != "" {
		fmt.Println(filePath)
	} else {
		fmt.Println("None")
	}
}