package main

import (
    "fmt"
    "io/ioutil"
    "log"
    "os"
    "path/filepath"
)

// openFile opens a file if it's located within a safe directory
func openFile(path string) (*os.File, error) {
    // Define a helper function to check if a file is in a safe directory
    inSafe := func(fname string) bool {
        cwd := filepath.ToSlash("/safedir")
        path := filepath.ToSlash(filepath.Dir(filepath.Abs(fname)))
        return filepath.HasPrefix(path, cwd)
    }

    if inSafe(path) {
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        return file, nil
    } else {
        return nil, nil
    }
}

func main() {
    file, err := openFile("/env.sh")
    if err != nil {
        if file == nil {
            log.Println("File not in safe directory or does not exist")
        } else {
            log.Println(err)
        }
        return
    }
    defer file.Close()

    // Example usage: read the file
    data, err := ioutil.ReadAll(file)
    if err != nil {
        log.Println(err)
        return
    }
    fmt.Println(string(data))
}