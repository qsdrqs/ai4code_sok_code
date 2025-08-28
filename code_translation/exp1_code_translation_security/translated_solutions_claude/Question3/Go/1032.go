package main

import (
    "fmt"
    "io/ioutil"
    "os"
    "strings"
)

func getFile(path string) (*os.File, error) {
    if strings.HasPrefix(path, "/safedir") {
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        return file, nil
    } else {
        return nil, fmt.Errorf("Access denied")
    }
}

func main() {
    file, err := getFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println("Error:", err)
        return
    }
    defer file.Close()
    
    content, err := ioutil.ReadAll(file)
    if err != nil {
        fmt.Println("Error reading file:", err)
        return
    }
    
    fmt.Print(string(content))
    
    // Commented out equivalent of the Python commented line
    // file2, err := getFile("/etc/passwd")
    // if err != nil {
    //     fmt.Println("Error:", err)
    //     return
    // }
    // defer file2.Close()
    // content2, err := ioutil.ReadAll(file2)
    // if err != nil {
    //     fmt.Println("Error reading file:", err)
    //     return
    // }
    // fmt.Print(string(content2))
}