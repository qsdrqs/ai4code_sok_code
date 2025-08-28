package main

import (
	"os"
	"path/filepath"
	"strings"
	"testing"
)

// openFile behaves like the Python version:
//
//  1. Canonicalise the path (make it absolute, remove “..”, etc.).
//  2. Check that the first directory after the leading “/” is “safedir”.
//  3. If that check passes, attempt to open the file and return the *os.File.
//  4. Otherwise return (nil, nil) – the Go analogue of Python’s `None`.
func openFile(name string) (*os.File, error) {
	abs, err := filepath.Abs(name)
	if err != nil {
		return nil, err
	}

	// Split the path into components.  For an absolute path on a POSIX
	// system the first element will be the empty string before the leading
	// “/”.  Thus parts[1] is the first real directory component.
	parts := strings.Split(abs, string(os.PathSeparator))
	if len(parts) >= 2 && parts[1] == "safedir" {
		return os.Open(abs)
	}
	return nil, nil
}

/*
   -------------------------------
   The tests – mirroring Python’s.
   -------------------------------
*/

func TestOpenFile(t *testing.T) {
	// 1. “/safedir/../a/b” should be rejected (returns nil, nil).
	if f, err := openFile("/safedir/../a/b"); err != nil || f != nil {
		t.Fatalf("expected (nil, nil); got (%v, %v)", f, err)
	}

	// 2. Trying to open a non-existent file under /safedir should yield an error.
	if _, err := openFile("/safedir/a"); err == nil {
		t.Fatalf("expected an error opening /safedir/a")
	}
}