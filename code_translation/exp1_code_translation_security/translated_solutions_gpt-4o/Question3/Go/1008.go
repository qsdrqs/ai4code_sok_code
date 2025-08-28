package main

import (
	"fmt"
	"os"
	"strings"
)

func test(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil, err
		}
		return file, nil
	}
	return nil, fmt.Errorf("no access")
}

func main() {
	// _, err := test("/junk")
	// if err != nil {
	//     fmt.Println(err)
	// }

	file, err := test("/safedir/junk")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	// Additional code to work with the file can be added here
}