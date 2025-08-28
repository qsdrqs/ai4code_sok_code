package main

import (
	"fmt"
	"io"
	"os"
	"strings"
)

// getFile mimics the Python get_file function.
func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("access denied")
}

func main() {
	// Attempt to open and read /safedir/random.txt
	if f, err := getFile("/safedir/random.txt"); err == nil {
		defer f.Close()
		if data, err := io.ReadAll(f); err == nil {
			fmt.Println(string(data))
		} else {
			fmt.Println("Error with /safedir/random.txt")
		}
	} else {
		fmt.Println("Error with /safedir/random.txt")
	}

	// Attempt to open and read /etc/passwd
	if f, err := getFile("/etc/passwd"); err == nil {
		defer f.Close()
		if data, err := io.ReadAll(f); err == nil {
			fmt.Println(string(data))
		} else {
			fmt.Println("Access was denied")
		}
	} else {
		fmt.Println("Access was denied")
	}
}