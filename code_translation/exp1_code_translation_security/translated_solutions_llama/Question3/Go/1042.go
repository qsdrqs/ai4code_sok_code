package main

import (
    "fmt"
    "os"
)

// getFilePath checks if a given path is a file and returns a file object if it is, otherwise it returns nil.
func getFilePath(path string) (*os.File, error) {
    // Check if the path is a file
    fileInfo, err := os.Stat(path)
    if err != nil {
        return nil, err
    }

    if !fileInfo.IsDir() {
        // Open the file in read mode
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        return file, nil
    }

    return nil, nil
}

func main() {
    path := "example.txt"
    file, err := getFilePath(path)
    if err != nil {
        fmt.Println(err)
        return
    }

    if file != nil {
        defer file.Close() // Don't forget to close the file
        fmt.Println("File opened successfully")
    } else {
        fmt.Println("The path is not a file")
    }
}