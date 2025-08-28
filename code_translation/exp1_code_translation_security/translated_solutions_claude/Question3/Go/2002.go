package main

import (
    "os"
    "path/filepath"
)

// getFile returns a file handle for a path relative to /safedir
// e.g. "hello/world" refers to "/safedir/hello/world"
func getFile(path string) (*os.File, error) {
    filename := filepath.Join("/safedir", path)
    return os.Open(filename)
}