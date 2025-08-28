package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

// load emulates the behaviour of the Python `load` function
// It resolves the supplied path, makes sure the first directory
// after the root is `safedir`, and then opens the file.
func load(pathStr string) (*os.File, error) {
	// Turn the given path into an absolute, symlink–resolved path
	abs, err := filepath.Abs(pathStr)
	if err != nil {
		return nil, err
	}
	resolved, err := filepath.EvalSymlinks(abs)
	if err != nil {
		return nil, err
	}

	// Split into path components.
	// On POSIX systems a leading slash produces an empty first element.
	parts := strings.Split(resolved, string(os.PathSeparator))

	// Need at least ["", "safedir", …]
	if len(parts) < 2 || parts[1] != "safedir" {
		return nil, fmt.Errorf("cannot access unsafe file")
	}

	// Open the file.
	return os.Open(resolved)
}

func main() {
	if f, err := load("/safedir/foo.txt"); err == nil {
		defer f.Close()
		data, _ := io.ReadAll(f)
		fmt.Println(string(data))
	} else {
		fmt.Println(err)
	}

	if f, err := load("/tmp/foo.txt"); err == nil {
		defer f.Close()
		data, _ := io.ReadAll(f)
		fmt.Println(string(data))
	} else {
		fmt.Println(err) // prints: cannot access unsafe file
	}
}