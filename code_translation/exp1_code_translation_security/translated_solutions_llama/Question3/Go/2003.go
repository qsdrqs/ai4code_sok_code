package main

import (
    "fmt"
    "io/ioutil"
    "log"
    "os"
    "path/filepath"
)

// load opens a file at the given path and returns its contents.
// It checks if the file is within a 'safedir' directory.
func load(pathStr string) ([]byte, error) {
    // Resolve the absolute path
    path, err := filepath.Abs(pathStr)
    if err != nil {
        return nil, err
    }

    // Split the path into its components
    parts := filepath.SplitList(path)

    // Check if the path is safe
    if len(parts) < 2 || parts[1] != "safedir" {
        return nil, fmt.Errorf("cannot access unsafe file")
    }

    // Read the file contents
    contents, err := ioutil.ReadFile(path)
    if err != nil {
        return nil, err
    }

    return contents, nil
}

func main() {
    // Load and print the contents of a safe file
    safeContents, err := load("/safedir/foo.txt")
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(string(safeContents))

    // Load and print the contents of an unsafe file
    _, err = load("/tmp/foo.txt")
    if err != nil {
        log.Println(err)
    }
}