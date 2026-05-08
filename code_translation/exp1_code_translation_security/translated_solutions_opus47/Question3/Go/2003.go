package main

import (
	"errors"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

func load(pathStr string) (*os.File, error) {
	// Make path absolute (equivalent to Path.resolve() step 1)
	absPath, err := filepath.Abs(pathStr)
	if err != nil {
		return nil, err
	}

	// Resolve symlinks (equivalent to Path.resolve() step 2)
	resolvedPath, err := filepath.EvalSymlinks(absPath)
	if err != nil {
		return nil, err
	}

	// Split into parts. For "/safedir/foo.txt" this gives ["", "safedir", "foo.txt"]
	// which parallels Python's Path.parts giving ("/", "safedir", "foo.txt")
	parts := strings.Split(resolvedPath, string(filepath.Separator))
	if len(parts) < 2 || parts[1] != "safedir" {
		return nil, errors.New("Cannot access unsafe file")
	}

	return os.Open(resolvedPath)
}

func main() {
	f1, err := load("/safedir/foo.txt")
	if err != nil {
		panic(err)
	}
	defer f1.Close()
	content, err := io.ReadAll(f1)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content))

	f2, err := load("/tmp/foo.txt")
	if err != nil {
		panic(err)
	}
	defer f2.Close()
	content, err = io.ReadAll(f2)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content))
}