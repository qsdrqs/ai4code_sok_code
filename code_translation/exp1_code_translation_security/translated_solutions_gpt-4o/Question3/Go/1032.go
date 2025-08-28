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
		return
	}
	defer file.Close()

	content, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println(string(content))

	// Uncomment the following lines to test access denial
	// file, err = getFile("/etc/passwd")
	// if err != nil {
	//     fmt.Println(err)
	//     return
	// }
	// defer file.Close()
	//
	// content, err = ioutil.ReadAll(file)
	// if err != nil {
	//     fmt.Println(err)
	//     return
	// }
	//
	// fmt.Println(string(content))
}