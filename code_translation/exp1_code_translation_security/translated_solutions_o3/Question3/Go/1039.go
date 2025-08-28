package main

import (
	"fmt"
	"os"
	"strings"
)

// returnFile attempts to open the file at the provided path.
// It only allows access to files that reside under "/safedir".
func returnFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		fmt.Println("File cannot be accessed")
		return nil, fmt.Errorf("access to %s is denied", path)
	}

	return os.Open(path)
}