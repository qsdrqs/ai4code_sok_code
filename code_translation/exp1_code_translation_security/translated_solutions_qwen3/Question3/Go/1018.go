package main

import (
    "os"
    "strings"
)

// ReadIn opens a file if the path contains "/safedir", otherwise returns nil and no error.
func ReadIn(path string) (*os.File, error) {
    if strings.Contains(path, "/safedir") {
        return os.Open(path)
    }
    return nil, nil
}