package main

import (
    "os"
)

// GetFilePath checks if the given path is a regular file.
// If it is, it opens the file in read mode and returns the file handle.
// If the path is not a regular file or an error occurs during stat, it returns (nil, nil).
// If the file exists but cannot be opened, it returns (nil, error).
func GetFilePath(path string) (*os.File, error) {
    fileInfo, err := os.Stat(path)
    if err != nil {
        // If the file doesn't exist or we can't access it, return nil, nil
        return nil, nil
    }

    if !fileInfo.Mode().IsRegular() {
        // Not a regular file
        return nil, nil
    }

    // Open the file and return the result
    return os.Open(path)
}