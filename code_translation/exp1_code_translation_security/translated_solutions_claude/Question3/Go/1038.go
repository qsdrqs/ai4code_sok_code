package main

import (
    "os"
    "path/filepath"
)

// Returns just the filename
func getFileName(file *os.File) string {
    return filepath.Base(file.Name())
}

// Returns the full path
func getFilePath(file *os.File) string {
    return file.Name()
}

// If you want both name and path based on a condition
func getFilePathConditional(file *os.File, returnFullPath bool) string {
    if returnFullPath {
        return file.Name() // This is actually the full path in Go
    }
    return filepath.Base(file.Name()) // This extracts just the filename
}