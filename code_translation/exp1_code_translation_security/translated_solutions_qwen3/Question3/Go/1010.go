package main

import (
    "errors"
    "os"
    "strings"
)

// Open a file after validating the path to ensure it's within the safe directory
func openPath(filename string) (*os.File, error) {
    // Ensure the path starts with "/safedir/"
    if !strings.HasPrefix(filename, "/safedir/") {
        return nil, errors.New("path does not start with /safedir/")
    }

    // Prevent directory traversal attempts using "/../"
    if strings.Contains(filename, "/../") {
        return nil, errors.New("path contains invalid directory traversal")
    }

    // Open the file for writing (equivalent to 'w' mode in Python)
    return os.Create(filename)
}

// Example usage (commented out, as in the original Python code)
/*
func main() {
    file, err := openPath("/safedir/Jim")
    if err != nil {
        fmt.Println("Error:", err)
    } else {
        defer file.Close()
        fmt.Println("File opened successfully:", file.Name())
    }
}
*/