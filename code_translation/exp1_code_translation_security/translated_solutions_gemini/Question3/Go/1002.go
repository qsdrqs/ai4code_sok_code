// In Go, code is organized into packages. The following would be in a file
// named `main.go` or similar.

package main

import (
	"os"
	"path/filepath"
	"strings"
)

// openFile resolves the given filename and opens it only if the resolved
// path is within a directory named "safedir" at the root of the filesystem
// or drive. It is a direct translation of the provided Python function.
//
// The security check ensures that the canonical path starts with "/safedir"
// (on Unix-like systems) or "C:\safedir" (on Windows).
//
// It returns the file handle if the check passes and the file is successfully
// opened.
// If the security check fails, it returns (nil, nil), mimicking the Python
// version's `None` return.
// If the security check passes but the file cannot be opened (e.g., it doesn't
// exist), it returns the underlying error from the open call.
func openFile(filename string) (*os.File, error) {
	// In Python, path.resolve() makes the path absolute and resolves '..'.
	// In Go, filepath.Abs and filepath.Clean accomplish this.
	path, err := filepath.Abs(filename)
	if err != nil {
		return nil, err
	}
	// filepath.Clean is essential to resolve ".." and "." components
	// to get the canonical path for the security check.
	path = filepath.Clean(path)

	// In Python, path.parts for "/a/b" is ('/', 'a', 'b').
	// On Windows, for "C:\a\b" it's ('C:\', 'a', 'b').
	// In Go, strings.Split("/a/b", "/") is ["", "a", "b"].
	// On Windows, strings.Split("C:\a\b", "\") is ["C:", "a", "b"].
	// In both cases, the second element (index 1) is the first directory.
	parts := strings.Split(path, string(os.PathSeparator))

	// The check `len(parts) >= 2` ensures there is at least one directory
	// component after the root (e.g., "/safedir" or "C:\safedir").
	// `parts[1] == "safedir"` checks if that first directory is "safedir".
	if len(parts) >= 2 && parts[1] == "safedir" {
		// The path is safe, attempt to open it.
		return os.Open(path)
	}

	// The path is not in "safedir", return (nil, nil) to mimic Python's `None`.
	return nil, nil
}

// The following test code would be in a separate file named `main_test.go`.
// To run the tests, you would execute `go test` in the terminal.

package main

import (
	"os"
	"path/filepath"
	"testing"
)

// TestOpenFile translates the assertions from the Python test function into
// idiomatic Go tests.
func TestOpenFile(t *testing.T) {
	// Test case 1: Corresponds to `assert open_file("/safedir/../a/b") is None`
	// This test checks for a path traversal vulnerability. The path resolves to "/a/b",
	// which is outside "/safedir", so the function should reject it by returning (nil, nil).
	t.Run("Path Traversal Rejected", func(t *testing.T) {
		// We use filepath.Join to construct the path in a cross-platform way.
		// The leading os.PathSeparator makes it an absolute path.
		path := filepath.Join(string(os.PathSeparator), "safedir", "..", "a", "b")
		f, err := openFile(path)

		if f != nil {
			f.Close()
			t.Errorf("Expected nil file for path traversal, but got a file handle")
		}
		// A rejected path should return (nil, nil), not an error.
		if err != nil {
			t.Errorf("Expected nil error for rejected path, but got: %v", err)
		}
	})

	// Test case 2: Corresponds to the `try...except` block for `open_file("/safedir/a")`
	// This test checks that a syntactically valid path inside "/safedir" is
	// passed to the OS for opening. Since the file doesn't exist, we expect
	// an error from the OS, not the (nil, nil) from our security check.
	t.Run("Non-existent but allowed path", func(t *testing.T) {
		path := filepath.Join(string(os.PathSeparator), "safedir", "a")
		f, err := openFile(path)

		if f != nil {
			f.Close()
			t.Errorf("Got a file handle for a non-existent file, should have been an error")
		}
		// We expect an error, specifically a "not exist" error from the OS.
		if !os.IsNotExist(err) {
			t.Errorf("Expected a 'file not exist' error, but got: %v", err)
		}
	})
}