package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// openFile replicates the Python logic: it only opens files that are
// inside /safedir (after resolving absolute paths). Otherwise it returns
// an error.
func openFile(path string) (*os.File, error) {
	// Resolve the absolute path of the target file.
	absPath, err := filepath.Abs(path)
	if err != nil {
		return nil, err
	}

	// Resolve the absolute path of the safe directory.
	safeDir, err := filepath.Abs("/safedir")
	if err != nil {
		return nil, err
	}

	// Check that the requested path is within /safedir.
	if absPath == safeDir || strings.HasPrefix(absPath, safeDir+string(os.PathSeparator)) {
		return os.Open(path)
	}

	return nil, fmt.Errorf("access denied")
}

func main() {
	f, err := openFile("/safedir/test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(f)
}