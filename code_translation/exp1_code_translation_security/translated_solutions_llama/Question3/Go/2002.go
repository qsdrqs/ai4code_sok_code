package main

import (
	"fmt"
	"os"
)

// getFile returns a file object for the given path relative to /safedir
func getFile(path string) (*os.File, error) {
	filename := fmt.Sprintf("/safedir/%s", path)
	return os.Open(filename)
}

func main() {
	// Example usage:
	path := "hello/world"
	file, err := getFile(path)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
	fmt.Println(file.Name())
}