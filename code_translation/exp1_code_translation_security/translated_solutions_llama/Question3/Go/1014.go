package main

import (
    "fmt"
    "os"
    "strings"
)

// openFile opens a file at the specified path if it starts with "/safedir"
func openFile(path string) (*os.File, error) {
    if strings.HasPrefix(path, "/safedir") {
        return os.Open(path)
    } else {
        return nil, fmt.Errorf("access denied")
    }
}

func main() {
    f, err := openFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer f.Close()

    buf := make([]byte, 1024)
    for {
        n, err := f.Read(buf)
        if err != nil {
            if err != os.ErrEOF {
                fmt.Println(err)
            }
            break
        }
        fmt.Print(string(buf[:n]))
    }
}