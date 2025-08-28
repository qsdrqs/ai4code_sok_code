package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	} else {
		return nil, fmt.Errorf("access denied")
	}
}

func main() {
	file, err := getFile("/safedir/test1.txt")
	if err != nil {
		fmt.Println(err)
	} else {
		defer file.Close()
		content, err := ioutil.ReadAll(file)
		if err != nil {
			fmt.Println("Error reading file:", err)
		} else {
			fmt.Println(string(content))
		}
	}

	file, err = getFile("/etc/passwd")
	if err != nil {
		fmt.Println(err)
	} else {
		defer file.Close()
		content, err := ioutil.ReadAll(file)
		if err != nil {
			fmt.Println("Error reading file:", err)
		} else {
			fmt.Println(string(content))
		}
	}
}