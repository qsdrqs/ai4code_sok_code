package main

import (
    "fmt"
    "os"
    "strings"
)

// getFile returns a file at the specified file location if it contains "/safedir"
func getFile(fileLocation string) (*os.File, error) {
    // Check if file location contains "/safedir" (case-insensitive)
    if strings.Contains(strings.ToLower(fileLocation), "/safedir") {
        // Attempt to open the file
        file, err := os.Open(fileLocation)
        if err != nil {
            return nil, err
        }
        return file, nil
    }
    fmt.Println("Provided File location is inaccessible")
    return nil, fmt.Errorf("file location is inaccessible")
}

func main() {
    file, err := getFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer file.Close() // Close the file when done
    fmt.Println(file)
}