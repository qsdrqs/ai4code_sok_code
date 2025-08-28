package main

import (
	"fmt"
	"io"
	"os"
	"strings"
)

func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("access denied")
}

func main() {
	// First attempt: /safedir/test1.txt
	if f, err := getFile("/safedir/test1.txt"); err != nil {
		fmt.Println(err)
	} else {
		defer f.Close()
		if _, err := io.Copy(os.Stdout, f); err != nil {
			fmt.Println("read error:", err)
		}
	}

	// Second attempt: /etc/passwd
	if f, err := getFile("/etc/passwd"); err != nil {
		fmt.Println(err)
	} else {
		defer f.Close()
		if _, err := io.Copy(os.Stdout, f); err != nil {
			fmt.Println("read error:", err)
		}
	}
}