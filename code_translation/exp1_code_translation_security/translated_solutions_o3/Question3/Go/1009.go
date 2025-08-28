package securefile

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// SAFE corresponds to the Python constant SAFE = "/safedir/"
const SAFE = "/safedir"

// GetFile replicates the semantics of the Python getFile function.
// It guarantees that the caller can open only files that reside
// inside SAFE (not SAFE itself) and returns an *os.File ready for reading.
func GetFile(p string) (*os.File, error) {
	// Build the candidate path below SAFE.
	testPath := filepath.Join(SAFE, p)

	// Canonicalise (clean, resolve symlinks, make absolute).
	resolvedTest, err := filepath.EvalSymlinks(testPath)
	if err != nil {
		return nil, err
	}
	resolvedSafe, err := filepath.EvalSymlinks(SAFE)
	if err != nil {
		return nil, err
	}

	resolvedTest, err = filepath.Abs(resolvedTest)
	if err != nil {
		return nil, err
	}
	resolvedSafe, err = filepath.Abs(resolvedSafe)
	if err != nil {
		return nil, err
	}

	// Ensure resolvedTest is **inside** resolvedSafe.
	rel, err := filepath.Rel(resolvedSafe, resolvedTest)
	if err != nil {
		return nil, err
	}
	if strings.HasPrefix(rel, "..") || rel == "." {
		// Outside SAFE or equals SAFE itself ⇒ reject.
		return nil, fmt.Errorf("filename %s is not in %s directory", resolvedTest, resolvedSafe)
	}

	// Open the file for reading.
	file, err := os.Open(resolvedTest)
	if err != nil {
		return nil, err
	}
	return file, nil
}