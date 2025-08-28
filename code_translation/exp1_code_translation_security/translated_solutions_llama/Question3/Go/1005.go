package main

import (
	"fmt"
	"os"
	"path/filepath"
)

const SAFE_DIR = "/safedir"

// safeLoadFile loads a file if its path starts with SAFE_DIR
func safeLoadFile(fname string) (*os.File, error) {
	// Parse path
	fpath, err := filepath.EvalSymlinks(fname)
	if err != nil {
		return nil, err
	}

	if !filepath.HasPrefix(fpath, SAFE_DIR) {
		fmt.Println("FAILED")
		return nil, fmt.Errorf("file path does not start with %s", SAFE_DIR)
	}

	return os.Open(fpath)
}

func main() {
	f, err := safeLoadFile("/safedir/test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer f.Close()

	buf := make([]byte, 1024)
	for {
		n, err := f.Read(buf)
		if err != nil {
			if err != os.ErrEOF {
				fmt.Println(err)
			}
			break
		}
		fmt.Print(string(buf[:n]))
	}
}