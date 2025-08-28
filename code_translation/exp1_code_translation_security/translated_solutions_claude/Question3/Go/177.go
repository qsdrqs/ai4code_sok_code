package main

import (
    "os"
    "path/filepath"
)

func path2obj(filePath string) *os.File {
    f, err := os.Open(filePath)
    if err != nil {
        return nil
    }
    
    directory := filepath.Dir(filePath)
    for directory != "" && directory != "." && directory != "/" {
        if filepath.Base(directory) == "safedir" {
            return f
        }
        directory = filepath.Dir(directory)
    }
    
    // Close the file if we're not returning it
    f.Close()
    return nil
}