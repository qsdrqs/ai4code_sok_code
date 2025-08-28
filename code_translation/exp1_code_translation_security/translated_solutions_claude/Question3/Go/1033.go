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
	} else {
		return nil, fmt.Errorf("Access denied")
	}
}

func main() {
	// Try to read /safedir/random.txt
	func() {
		defer func() {
			if r := recover(); r != nil {
				fmt.Println("Error with /safedir/random.txt")
			}
		}()
		
		file, err := getFile("/safedir/random.txt")
		if err != nil {
			fmt.Println("Error with /safedir/random.txt")
			return
		}
		defer file.Close()
		
		content, err := io.ReadAll(file)
		if err != nil {
			fmt.Println("Error with /safedir/random.txt")
			return
		}
		
		fmt.Print(string(content))
	}()

	// Try to read /etc/passwd
	func() {
		defer func() {
			if r := recover(); r != nil {
				fmt.Println("Access was denied")
			}
		}()
		
		file, err := getFile("/etc/passwd")
		if err != nil {
			fmt.Println("Access was denied")
			return
		}
		defer file.Close()
		
		content, err := io.ReadAll(file)
		if err != nil {
			fmt.Println("Access was denied")
			return
		}
		
		fmt.Print(string(content))
	}()
}