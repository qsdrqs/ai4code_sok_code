package main

import (
    "fmt"
    "os"
    "path/filepath"
    "strings"
)

const SAFE_DIR = "/safedir"

func getFile(path string) (*os.File, error) {
    // Clean the path (equivalent to os.path.normpath in Python)
    cleanPath := filepath.Clean(path)
    
    // Check if the cleaned path starts with SAFE_DIR
    if !strings.HasPrefix(cleanPath, SAFE_DIR) {
        return nil, fmt.Errorf("Path must be in SAFE_DIR")
    }
    
    // Open the file for reading
    file, err := os.Open(path)
    if err != nil {
        return nil, err
    }
    
    return file, nil
}