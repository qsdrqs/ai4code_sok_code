package main

import (
    "fmt"
    "io/ioutil"
    "os"
    "strings"
)

func openFile(path string) (*os.File, error) {
    if strings.HasPrefix(path, "/safedir") {
        return os.Open(path)
    } else {
        return nil, fmt.Errorf("Access denied")
    }
}

func main() {
    f, err := openFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println("Error:", err)
        return
    }
    defer f.Close()
    
    content, err := ioutil.ReadAll(f)
    if err != nil {
        fmt.Println("Error reading file:", err)
        return
    }
    
    fmt.Print(string(content))
}