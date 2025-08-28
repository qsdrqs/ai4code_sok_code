package main

import (
    "os"
)

// get_file_path takes as input a string path representing a file path
func getFilePath(path string) *os.File {
    // Check if the path exists and is a regular file
    if info, err := os.Stat(path); err == nil && info.Mode().IsRegular() {
        // Open the file for reading
        if file, err := os.Open(path); err == nil {
            return file
        }
    }
    return nil
}