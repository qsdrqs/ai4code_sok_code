package main

import (
	"errors"
	"os"
	"strings"
)

// GetFile returns a File object at given path, don't access files outside of "/safedir"
//
// Args:
//
//	path: string representing a file path
//
// Returns:
//
//	File object at path
func GetFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, errors.New("invalid path")
	}
	return os.Open(path)
}

func main() {
	GetFile("/safedir/random.txt")
	GetFile("/random.txt")
}