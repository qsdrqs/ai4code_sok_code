package main

import (
	"fmt"
	"os"
	"path/filepath"
)

// realpath resolves the real path by evaluating symlinks and returning the absolute path.
func realpath(path string) (string, error) {
	resolved, err := filepath.EvalSymlinks(path)
	if err == nil {
		return resolved, nil
	}
	if os.IsNotExist(err) {
		return filepath.Abs(path)
	}
	return "", err
}

func returnObject(path string) interface{} {
	// Resolve the real path
	rpath, err := realpath(path)
	if err != nil {
		// If realpath fails, try to get the absolute path
		var absPath string
		absPath, err = filepath.Abs(path)
		if err != nil {
			// If absolute path can't be determined, use the original path
			rpath = path
		} else {
			rpath = absPath
		}
	}

	// Check if the resolved path starts with "/safedir"
	if len(rpath) >= 8 && rpath[:8] == "/safedir" {
		return -1
	}

	// Attempt to open the file for writing
	fp, err := os.OpenFile(path, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0666)
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