package main

import (
	"os"
	"path/filepath"
	"strings"
)

func openFile(filename string) (*os.File, error) {
	// filepath.Abs returns an absolute representation and calls Clean,
	// which resolves "." and ".." elements (similar to Path.resolve()).
	absPath, err := filepath.Abs(filename)
	if err != nil {
		return nil, err
	}

	// Split into parts. For an absolute Unix path like "/safedir/a",
	// this yields ["", "safedir", "a"], matching Python's parts[1]
	// which gives the first directory after root.
	parts := strings.Split(absPath, string(filepath.Separator))

	if len(parts) >= 2 && parts[1] == "safedir" {
		return os.Open(absPath)
	}
	return nil, nil
}

func test() {
	// assert open_file("/safedir/../a/b") is None
	// After resolving, path becomes "/a/b", so parts[1] == "a", not "safedir"
	f, err := openFile("/safedir/../a/b")
	if f != nil || err != nil {
		panic("expected nil file and nil error for /safedir/../a/b")
	}

	// open_file("/safedir/a") should raise an exception
	// (the path passes the check but the file doesn't exist, so os.Open fails)
	_, err = openFile("/safedir/a")
	if err == nil {
		panic("expected error for /safedir/a")
	}
}

func main() {
	test()
}