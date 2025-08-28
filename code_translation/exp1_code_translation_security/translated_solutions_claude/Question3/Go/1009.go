package main

import (
    "fmt"
    "os"
    "path/filepath"
    "strings"
)

const SAFE = "/safedir/"

func getFile(path string) (*os.File, error) {
    // Resolve the safe directory path
    safeResolved, err := filepath.Abs(filepath.Clean(SAFE))
    if err != nil {
        return nil, err
    }
    
    // Join and resolve the test path
    testPath := filepath.Join(SAFE, path)
    testResolved, err := filepath.Abs(filepath.Clean(testPath))
    if err != nil {
        return nil, err
    }
    
    // Check if the resolved test path is within the safe directory
    // This prevents directory traversal attacks
    if !strings.HasPrefix(testResolved, safeResolved+string(filepath.Separator)) && testResolved != safeResolved {
        return nil, fmt.Errorf("Filename %s is not in %s directory", testResolved, safeResolved)
    }
    
    // Open and return the file
    return os.Open(filepath.Join(SAFE, path))
}