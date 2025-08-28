package main

import (
    "os"
    "strings"
)

// openFile takes a file path string, partitions it at the first occurrence of "/safedir",
// and attempts to open the resulting path starting from "/safedir".
// If "/safedir" is not found, it attempts to open an empty path.
func openFile(path string) (*os.File, error) {
    _, after, found := strings.Cut(path, "/safedir")
    if found {
        return os.Open("/safedir" + after)
    }
    return os.Open("")
}