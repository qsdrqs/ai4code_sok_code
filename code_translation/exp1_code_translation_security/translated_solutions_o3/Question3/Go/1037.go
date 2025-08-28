package main

import (
	"fmt"
	"os"
	"strings"
)

// validateDir checks if dir starts with "/safedir"
func validateDir(dir string) bool {
	return strings.HasPrefix(dir, "/safedir")
}

// createFile validates dir and, if valid, creates (or truncates) the file
// It mimics the Python version by returning *os.File when successful,
// or nil when the path is invalid or the creation fails.
func createFile(dir string) *os.File {
	if !validateDir(dir) {
		return nil
	}
	f, err := os.Create(dir)
	if err != nil {
		return nil
	}
	return f
}

func main() {
	fmt.Println(createFile("test"))
	fmt.Println(createFile("/safedir/testAgain1.txt"))
}