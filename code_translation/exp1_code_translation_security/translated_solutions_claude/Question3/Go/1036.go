package main

import (
    "fmt"
    "io/ioutil"
    "os"
    "strings"
)

func getFile(path string) (*os.File, error) {
    if strings.HasPrefix(path, "/safedir") {
        return os.Open(path)
    } else {
        return nil, fmt.Errorf("Access denied")
    }
}

func main() {
    // First file access
    file1, err1 := getFile("/safedir/test1.txt")
    if err1 != nil {
        fmt.Println("Error:", err1)
    } else {
        defer file1.Close()
        content1, readErr1 := ioutil.ReadAll(file1)
        if readErr1 != nil {
            fmt.Println("Error reading file:", readErr1)
        } else {
            fmt.Print(string(content1))
        }
    }

    // Second file access
    file2, err2 := getFile("/etc/passwd")
    if err2 != nil {
        fmt.Println("Error:", err2)
    } else {
        defer file2.Close()
        content2, readErr2 := ioutil.ReadAll(file2)
        if readErr2 != nil {
            fmt.Println("Error reading file:", readErr2)
        } else {
            fmt.Print(string(content2))
        }
    }
}