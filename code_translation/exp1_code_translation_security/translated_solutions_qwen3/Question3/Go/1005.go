package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

const SAFE_DIR = "/safedir"

// safeLoadFile attempts to open a file only if its real path starts with SAFE_DIR.
// If the path is not safe, it prints "FAILED" and returns nil.
// If the file cannot be opened, it returns nil (mirroring Python's behavior).
func safeLoadFile(fname string) *os.File {
	// Resolve symlinks and get the real path
	fpath, err := filepath.EvalSymlinks(fname)
	if err != nil {
		fmt.Println("FAILED")
		return nil
	}

	// Check if the resolved path starts with SAFE_DIR
	if !strings.HasPrefix(fpath, SAFE_DIR) {
		fmt.Println("FAILED")
		return nil
	}

	// Open the file in read-only mode
	file, err := os.Open(fpath)
	if err != nil {
		// In Python, this would raise an exception, but we return nil to mirror the behavior
		return nil
	}

	return file
}

func main() {
	// Example usage
	f := safeLoadFile("/safedir/test.txt")
	if f != nil {
		// Read the entire file content
		content, _ := io.ReadAll(f)
		fmt.Println(string(content))
		f.Close()
	}
}