package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

const SafeDir = "/safedir"

// safeLoadFile mimics the behavior of the Python safe_load_file.
// It prints "FAILED" and returns nil on any failure, otherwise it
// returns an *os.File opened for reading.
func safeLoadFile(fname string) *os.File {
	// Resolve to an absolute, symlink-free path.
	resolved, err := filepath.EvalSymlinks(fname)
	if err != nil {
		fmt.Println("FAILED")
		return nil
	}
	resolved, err = filepath.Abs(resolved)
	if err != nil {
		fmt.Println("FAILED")
		return nil
	}

	// Security check: make sure the resolved path starts with SafeDir.
	if !strings.HasPrefix(resolved, SafeDir) {
		fmt.Println("FAILED")
		return nil
	}

	// Attempt to open the file for reading.
	file, err := os.Open(resolved)
	if err != nil {
		fmt.Println("FAILED")
		return nil
	}

	return file
}

func main() {
	f := safeLoadFile("/safedir/test.txt")
	if f == nil {
		return // Already reported failure.
	}
	defer f.Close()

	// Print the contents to stdout.
	_, _ = io.Copy(os.Stdout, f)
}