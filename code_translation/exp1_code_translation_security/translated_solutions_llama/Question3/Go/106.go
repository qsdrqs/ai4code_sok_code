package main

import (
    "errors"
    "fmt"
    "os"
)

// getFile returns a File object at the given path.
// It ensures the path starts with "/safedir" to prevent accessing files outside of this directory.
//
// Args:
//     path: string representing a file path
//
// Return:
//     *os.File: File object at path
//     error: Error if the path is invalid or the file cannot be opened
func getFile(path string) (*os.File, error) {
    if !path.startswith("/safedir") {
        return nil, errors.New("invalid path")
    }
    file, err := os.Open(path)
    if err != nil {
        return nil, err
    }
    return file, nil
}

func main() {
    // Example usage
    filePath1 := "/safedir/random.txt"
    filePath2 := "/random.txt"

    file1, err1 := getFile(filePath1)
    if err1 != nil {
        fmt.Println(err1)
    } else {
        fmt.Printf("Successfully opened file: %s\n", filePath1)
        file1.Close() // Don't forget to close the file
    }

    file2, err2 := getFile(filePath2)
    if err2 != nil {
        fmt.Println(err2)
    } else {
        fmt.Printf("Successfully opened file: %s\n", filePath2)
        file2.Close() // Don't forget to close the file
    }
}