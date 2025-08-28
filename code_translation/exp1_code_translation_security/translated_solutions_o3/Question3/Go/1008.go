package main

import (
	"fmt"
	"os"
	"strings"
)

// test replicates the Python test(path) function.
// It returns an *os.File (opened file handle) on success or an error otherwise.
func test(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("no access")
}

func main() {
	// j, err := test("/junk")
	// if err != nil {
	//     fmt.Println(err)
	// }

	k, err := test("/safedir/junk")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer k.Close()

	// do something with k (the opened file) if needed...
}