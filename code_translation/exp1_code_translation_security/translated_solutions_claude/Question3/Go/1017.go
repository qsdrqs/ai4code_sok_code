package main

import (
    "fmt"
    "strings"
)

// File struct to represent a file
type File struct {
    Path string
}

// Constructor for File
func NewFile(path string) *File {
    return &File{Path: path}
}

// GetFile function that mimics the Python behavior
func GetFile(path string) (*File, error) {
    if strings.HasPrefix(path, "/safedir") {
        return NewFile(path), nil
    } else {
        return nil, fmt.Errorf("Access denied")
    }
}