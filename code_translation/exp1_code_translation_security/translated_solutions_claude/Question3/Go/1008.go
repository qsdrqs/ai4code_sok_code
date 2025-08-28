package main

import (
    "fmt"
    "os"
    "strings"
)

func test(path string) (*os.File, error) {
    if strings.Contains(path, "/safedir") {
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        return file, nil
    }
    return nil, fmt.Errorf("no access")
}

func main() {
    // j, err := test("/junk")
    // if err != nil {
    //     fmt.Println("Error:", err)
    // }
    
    k, err := test("/safedir/junk")
    if err != nil {
        fmt.Println("Error:", err)
    } else {
        defer k.Close() // Don't forget to close the file
        fmt.Println("File opened successfully")
    }
}