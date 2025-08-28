package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func returnObject(path string) (*os.File, error) {
	rpath, err := filepath.EvalSymlinks(path)
	if err != nil {
		return nil, err
	}

	if strings.HasPrefix(rpath, "/safedir") {
		return nil, fmt.Errorf("path is in safe directory")
	}

	fp, err := os.Create(path)
	if err != nil {
		return nil, err
	}

	return fp, nil
}

func main() {
	fileName := "/tmp/foo"
	fp, err := returnObject(fileName)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer fp.Close()

	fmt.Println(fp)
}