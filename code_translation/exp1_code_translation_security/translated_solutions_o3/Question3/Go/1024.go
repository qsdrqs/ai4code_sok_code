package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// returnObject replicates the semantics of the original Python function.
// It returns:
//   * -1          – when the resolved path begins with "/safedir";
//   * *os.File    – when the file could be opened for writing;
//   * nil         – when an error occurred that was only printed.
func returnObject(path string) interface{} {
	// Go’s close-enough equivalent of Python’s os.path.realpath
	rpath, err := filepath.Abs(path) // make it absolute first
	if err == nil {
		// Resolve symlinks as realpath does
		rpath, err = filepath.EvalSymlinks(rpath)
	}
	if err != nil {
		fmt.Printf("Error resolving path: %v\n", err)
		return nil
	}

	if strings.HasPrefix(rpath, "/safedir") {
		return -1
	}

	// Open the file for writing, creating/truncating like Python's 'w' mode
	fp, err := os.OpenFile(path, os.O_CREATE|os.O_WRONLY|os.O_TRUNC, 0o644)
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