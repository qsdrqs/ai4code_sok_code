package main

import (
    "errors"
    "io"
    "os"
    "path/filepath"
)

const SAFE = "/safedir/"

// getFile opens a file at the specified path and ensures it's within the SAFE directory.
func getFile(path string) (*os.File, error) {
    // Construct the absolute path
    testPath, err := filepath.Abs(SAFE + path)
    if err != nil {
        return nil, err
    }

    // Ensure the SAFE directory is a parent of the testPath
    safeDir, err := filepath.Abs(SAFE)
    if err != nil {
        return nil, err
    }
    if !strings.HasPrefix(testPath, safeDir) {
        return nil, errors.New("filename is not in SAFE directory")
    }

    // Open the file
    file, err := os.Open(testPath)
    if err != nil {
        return nil, err
    }

    return file, nil
}

func main() {
    // Example usage
    filePath := "example.txt"
    file, err := getFile(filePath)
    if err != nil {
        panic(err)
    }
    defer file.Close()

    // Read and print the file content
    buf := make([]byte, 1024)
    _, err = file.Read(buf)
    if err != nil && err != io.EOF {
        panic(err)
    }
    println(string(buf))
}