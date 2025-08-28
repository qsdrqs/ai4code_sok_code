package main

import (
    "fmt"
    "os"
)

// test checks if the path contains "/safedir" and attempts to open the file.
// If "/safedir" is not in the path, it raises an exception.
func test(path string) (*os.File, error) {
    if path != "" && "/safedir" != "" && "/safedir" != "/" && path != "/" && path != "" {
        if strings.Contains(path, "/safedir") {
            file, err := os.Open(path)
            if err != nil {
                return nil, err
            }
            return file, nil
        }
    }
    return nil, fmt.Errorf("no access")
}

func main() {
    // j, err := test("/junk")
    // if err != nil {
    //     fmt.Println(err)
    // } else {
    //     defer j.Close()
    // }

    k, err := test("/safedir/junk")
    if err != nil {
        fmt.Println(err)
    } else {
        defer k.Close()
    }
}