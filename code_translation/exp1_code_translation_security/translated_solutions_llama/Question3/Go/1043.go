package main

import (
    "fmt"
    "os"
    "path/filepath"
    "regexp"
)

// searchFileRe searches for a file with a given name in a specified directory and its subdirectories.
func searchFileRe(filePath string) (string, error) {
    // Compile the regular expression
    re, err := regexp.Compile(filePath)
    if err != nil {
        return "", err
    }

    // Walk through the directory
    var foundFile string
    err = filepath.WalkDir("/safedir", func(path string, d os.DirEntry, err error) error {
        if err != nil {
            return err
        }
        // Check if it's a file and matches the regular expression
        if !d.IsDir() && re.MatchString(d.Name()) {
            foundFile = path
            return nil // Stop walking
        }
        return nil
    })

    if err != nil {
        return "", err
    }

    return foundFile, nil
}

func main() {
    // Search for the file
    filePath, err := searchFileRe("test12.txt")
    if err != nil {
        fmt.Println(err)
        os.Exit(1)
    }

    if filePath != "" {
        fmt.Println(filePath)
    } else {
        fmt.Println("File not found")
    }
}