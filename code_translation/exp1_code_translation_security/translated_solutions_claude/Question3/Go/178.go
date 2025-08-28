package main

import (
    "fmt"
    "os"
)

const ALLOWED_PATH = "/safedir"

func getFile(path string) interface{} {
    if len(path) < 8 || path[0:8] != ALLOWED_PATH {
        return "Not allowed to get file from this dir"
    }
    file, err := os.Open(path)
    if err != nil {
        return err
    }
    return file
}

func main() {
    result := getFile("/safedir/test/path")
    
    // Handle the result based on its type
    switch v := result.(type) {
    case string:
        fmt.Println(v)
    case *os.File:
        fmt.Println("File opened successfully")
        v.Close() // Don't forget to close the file
    case error:
        fmt.Printf("Error opening file: %v\n", v)
    }
}