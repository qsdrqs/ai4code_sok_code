// safeopen.go
package safeopen

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// SafeOpen is the Go equivalent of the Python `safe_open` function.
//
// It tries to open `path` and returns an *os.File only if the resolved, real
// path lies inside "/safedir" and is a plain file.  Any failure causes the
// function to return (nil, error).
func SafeOpen(path string) (*os.File, error) {
	// Absolute path to whatever the caller supplied.
	abs, err := filepath.Abs(path)
	if err != nil {
		return nil, err
	}

	// Resolve every symlink component in that path.
	realPath, err := filepath.EvalSymlinks(abs)
	if err != nil {
		return nil, err
	}

	// Resolve /safedir itself in case *it* is a symlink.
	safeRoot, err := filepath.EvalSymlinks("/safedir")
	if err != nil {
		return nil, err
	}

	// Is the resolved path still inside /safedir?
	rel, err := filepath.Rel(safeRoot, realPath)
	if err != nil {
		return nil, err
	}
	if strings.HasPrefix(rel, ".."+string(os.PathSeparator)) ||
		rel == ".." || filepath.IsAbs(rel) {
		return nil, fmt.Errorf("path %q escapes %q", realPath, safeRoot)
	}

	// Make sure it exists and is a regular file.
	info, err := os.Stat(realPath)
	if err != nil {
		return nil, err
	}
	if !info.Mode().IsRegular() {
		return nil, fmt.Errorf("not a regular file: %q", realPath)
	}

	// Everything checks out; open the file for reading.
	f, err := os.Open(realPath)
	if err != nil {
		return nil, err
	}
	return f, nil
}