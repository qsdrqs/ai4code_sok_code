package util

import (
	"os"
)

// GetFilePath tries to open the file located at `path`.
// It mimics the Python behaviour of returning a file handle if the path
// exists and points to a regular file, or nil otherwise.
//
// If the path exists but the call to os.Open fails (e.g. permission error),
// the corresponding error is returned.  When the path does not refer to a
// regular file, both the *os.File and the error are nil.
func GetFilePath(path string) (*os.File, error) {
	// Check that the path exists and is not a directory.
	if info, err := os.Stat(path); err == nil && !info.IsDir() {
		f, err := os.Open(path)
		if err != nil {
			return nil, err
		}
		return f, nil
	}

	return nil, nil
}