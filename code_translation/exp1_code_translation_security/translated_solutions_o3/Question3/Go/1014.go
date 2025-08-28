package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

// openFile replicates the Python open_file() helper.
// It permits access only to paths that start with “/safedir”.
func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("access denied")
}

func main() {
	// Like the Python original, main () bypasses openFile()
	// and opens the file directly.
	f, err := os.Open("/safedir/test1.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer f.Close()

	data, err := ioutil.ReadAll(f)
	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Print(string(data))
}