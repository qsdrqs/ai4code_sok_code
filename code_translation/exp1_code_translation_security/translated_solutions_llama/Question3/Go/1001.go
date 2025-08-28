package main

import (
	"os"
	"strings"
)

// checkIfSafe checks if a given filepath is safe to access.
// A filepath is considered safe if it starts with "/safedir" and does not contain "/..".
func checkIfSafe(mystring string) bool {
	return strings.HasPrefix(mystring, "/safedir") && !strings.Contains(mystring, "/..")
}

// getFile attempts to open a file at the given filepath if it is considered safe.
// If the filepath is safe, it returns a file descriptor; otherwise, it returns nil.
func getFile(filepath string) *os.File {
	if checkIfSafe(filepath) {
		file, err := os.Open(filepath)
		if err != nil {
			return nil
		}
		return file
	} else {
		return nil
	}
}

func main() {
	// Example usage:
	filepath := "/safedir/example.txt"
	file := getFile(filepath)
	if file != nil {
		defer file.Close()
		// You can now use the file descriptor
	} else {
		// The filepath is not safe or there was an error opening the file
	}
}