package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func returnObject(path string) interface{} {
	// Get the absolute path first (handles non-existent paths)
	rpath, err := filepath.Abs(path)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return nil
	}

	// Try to resolve symlinks (similar to os.path.realpath)
	// EvalSymlinks fails for non-existent paths, so we only apply it if it succeeds
	if resolved, err := filepath.EvalSymlinks(rpath); err == nil {
		rpath = resolved
	}

	// Check if the real path starts with "/safedir"
	if strings.HasPrefix(rpath, "/safedir") {
		return -1
	}

	// Open the file for writing (os.Create is equivalent to open(path, 'w'))
	fp, err := os.Create(path)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return nil
	}
	return fp
}

func main() {
	fileName := "/tmp/foo"
	fmt.Println(returnObject(fileName))
}