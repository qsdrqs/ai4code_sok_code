package main

import (
	"fmt"
	"os"
	"strings"
)

// OpenPath opens a file for writing if it is within the /safedir/ directory
func OpenPath(filename string) (*os.File, error) {
	// Check if the filename starts with "/safedir/"
	if !strings.HasPrefix(filename, "/safedir/") {
		return nil, fmt.Errorf("invalid path: %s", filename)
	}

	// Check for any occurrence of "/../" in the filename
	if strings.Contains(filename, "/../") {
		return nil, fmt.Errorf("invalid path: %s", filename)
	}

	// Open the file for writing
	file, err := os.OpenFile(filename, os.O_WRONLY|os.O_CREATE, 0644)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	file, err := OpenPath("/safedir/Jim")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer file.Close()

	fmt.Println("File opened successfully:", file.Name())
}