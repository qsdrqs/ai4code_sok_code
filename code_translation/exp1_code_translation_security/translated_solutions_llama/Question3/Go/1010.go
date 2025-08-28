package main

import (
	"fmt"
	"os"
	"path/filepath"
)

// openPath opens a file if the provided path is within the safe directory.
func openPath(filename string) (*os.File, error) {
	// Normalize the path to eliminate any potential directory traversal attacks
	safeDir := "/safedir/"
	if !strings.HasPrefix(filename, safeDir) {
		return nil, fmt.Errorf("file path is not within the safe directory")
	}

	// Normalize the path to eliminate any potential directory traversal attacks
	relPath, err := filepath.Rel(safeDir, filename)
	if err != nil {
		return nil, err
	}

	// Check for any directory traversal attempts
	if strings.Contains(relPath, "..") {
		return nil, fmt.Errorf("file path contains directory traversal attempts")
	}

	// Open the file
	return os.Create(filename)
}

func main() {
	file, err := openPath("/safedir/Jim")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
}