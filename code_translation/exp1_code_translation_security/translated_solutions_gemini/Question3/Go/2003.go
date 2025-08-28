package main

import (
	"errors"         // For creating simple error messages
	"fmt"            // For formatted I/O (like printing to the console)
	"io"             // For I/O operations, like reading a file
	"log"            // For logging errors
	"os"             // For OS-level operations like file access
	"path/filepath"  // For platform-independent path manipulation
	"strings"        // For string manipulation, like splitting
)

// load resolves the given path and checks if it is inside a "safedir" directory.
// If the path is safe, it returns an open file handle (*os.File).
// Otherwise, it returns an error.
func load(pathStr string) (*os.File, error) {
	// In Python: path = Path(path_str).resolve()
	// In Go, we first make the path absolute, then evaluate symbolic links.
	resolvedPath, err := filepath.Abs(pathStr)
	if err != nil {
		return nil, err
	}
	resolvedPath, err = filepath.EvalSymlinks(resolvedPath)
	if err != nil {
		return nil, err
	}

	// In Python: path.parts
	// In Go, we split the path by the OS's separator.
	// On Linux/macOS, "/safedir/foo.txt" becomes ["", "safedir", "foo.txt"]
	parts := strings.Split(resolvedPath, string(os.PathSeparator))

	// In Python: if len(path.parts) < 2 or path.parts[1] != 'safedir':
	// The check ensures the path is at least "/safedir" and the directory
	// immediately after the root is "safedir".
	if len(parts) < 3 || parts[1] != "safedir" {
		// In Python: raise Exception(...)
		// In Go, we return a nil file and a new error.
		return nil, errors.New("cannot access unsafe file")
	}

	// In Python: return open(path)
	// In Go, os.Open returns (*os.File, error). We return both.
	return os.Open(resolvedPath)
}

func main() {
	// --- Setup a temporary directory for a runnable example ---
	// We create a temporary root directory to simulate "/"
	tempRoot, err := os.MkdirTemp("", "go-example")
	if err != nil {
		log.Fatalf("Failed to create temp dir: %v", err)
	}
	// Defer cleanup of the entire temporary directory structure
	defer os.RemoveAll(tempRoot)

	// Create the "safedir" and a file inside it
	safeDirPath := filepath.Join(tempRoot, "safedir")
	os.Mkdir(safeDirPath, 0755)
	safeFilePath := filepath.Join(safeDirPath, "foo.txt")
	os.WriteFile(safeFilePath, []byte("safe content"), 0644)

	// Create the "tmp" directory (our unsafe location) and a file inside it
	tmpDirPath := filepath.Join(tempRoot, "tmp")
	os.Mkdir(tmpDirPath, 0755)
	unsafeFilePath := filepath.Join(tmpDirPath, "foo.txt")
	os.WriteFile(unsafeFilePath, []byte("unsafe content"), 0644)
	// --- End of setup ---


	// --- First test case: Loading the safe file ---
	fmt.Printf("Attempting to load: %s\n", safeFilePath)
	file, err := load(safeFilePath)
	if err != nil {
		// This part should not be reached in the successful case
		log.Fatalf("Expected to load safe file, but got an error: %v", err)
	}
	// defer ensures the file is closed when main() exits
	defer file.Close()

	// Read the content of the successfully opened file
	content, err := io.ReadAll(file)
	if err != nil {
		log.Fatalf("Failed to read the safe file: %v", err)
	}
	fmt.Printf("Success! Content: %s\n\n", string(content))


	// --- Second test case: Attempting to load the unsafe file ---
	fmt.Printf("Attempting to load: %s\n", unsafeFilePath)
	_, err = load(unsafeFilePath)
	if err != nil {
		// This is the expected outcome. The error is caught and printed.
		fmt.Printf("Success! Correctly failed to load unsafe file with error: %v\n", err)
	} else {
		// If err is nil, the security check failed.
		log.Fatal("Security check failed: allowed access to an unsafe file!")
	}
}