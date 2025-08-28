package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// SAFE defines the secure base directory where files can be accessed.
const SAFE = "/safedir/"

// GetFile safely opens a file within the SAFE directory.
// It prevents directory traversal attacks by ensuring the resolved
// path of the requested file is still within the SAFE directory.
// It returns a file handle or an error if the path is unsafe or the file doesn't exist.
func GetFile(path string) (*os.File, error) {
	// Get the absolute path of the safe directory. We ignore the error here
	// for simplicity, assuming SAFE is a valid path constant.
	safeAbs, _ := filepath.Abs(SAFE)

	// Join the safe directory with the user-provided path and clean it.
	// filepath.Clean resolves ".." and "." segments, preventing traversal.
	// For example, /safedir/../etc/passwd becomes /etc/passwd.
	testPath := filepath.Join(safeAbs, path)
	testPath = filepath.Clean(testPath)

	// Security Check:
	// Ensure the cleaned, absolute path of the requested file has the
	// safe directory's absolute path as a prefix.
	// This is the Go equivalent of Python's `resolve()` and parent check.
	// We also check that the path is not the safe directory itself.
	if !strings.HasPrefix(testPath, safeAbs) || testPath == safeAbs {
		return nil, fmt.Errorf("filename %s is not in %s directory", testPath, safeAbs)
	}

	// If the path is safe, open the file for reading.
	return os.Open(testPath)
}

// Example of how to use the GetFile function.
// To run this example, you would need to create the /safedir directory
// and a file inside it, e.g., /safedir/hello.txt.
//
// sudo mkdir /safedir
// sudo chown $USER /safedir
// echo "hello world" > /safedir/hello.txt
//
func main() {
	// --- Test Cases ---

	// 1. Valid file request
	fmt.Println("Attempting to open a valid file: 'hello.txt'")
	file, err := GetFile("hello.txt")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened.")
		file.Close() // Always close the file when done
	}
	fmt.Println("--------------------")

	// 2. Directory traversal attempt
	fmt.Println("Attempting directory traversal: '../etc/passwd'")
	file, err = GetFile("../etc/passwd")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened. (This should not happen!)")
		file.Close()
	}
	fmt.Println("--------------------")

	// 3. Requesting the safe directory itself
	fmt.Println("Attempting to open the safe directory itself: '.'")
	file, err = GetFile(".")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: Directory opened. (This should not happen!)")
		file.Close()
	}
}