package main

import (
    "fmt"
    "io/ioutil"
    "log"
    "os"
)

func openFile(filePath string) *os.File {
    file, err := os.Open(filePath)
    if err != nil {
        log.Fatal(err)
    }
    return file
}

func main() {
    filePath := "/home/codex/Documents/User_Study_UI/prac.txt"
    
    // Write to file (equivalent to Python's 'with open' context manager)
    err := ioutil.WriteFile(filePath, []byte("Hello it's Melo"), 0644)
    if err != nil {
        log.Fatal(err)
    }
    
    // Open and read file
    file := openFile(filePath)
    defer file.Close() // Important: close the file when done
    
    content, err := ioutil.ReadAll(file)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Print(string(content))
}