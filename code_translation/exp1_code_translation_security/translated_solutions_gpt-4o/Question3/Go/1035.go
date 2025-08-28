package main

import (
	"fmt"
	"path/filepath"
	"strings"
)

func pathFromStr(p string) *string {
	// Clean the path and split it into parts
	cleanPath := filepath.Clean(p)
	parts := strings.Split(cleanPath, string(filepath.Separator))

	// Check if the first two parts are "/" and "safedir"
	if len(parts) < 2 || parts[0] != "" || parts[1] != "safedir" {
		return nil
	}

	return &cleanPath
}

func main() {
	fmt.Println(pathFromStr("/safedir/junk"))
	fmt.Println(pathFromStr("/bin"))
	fmt.Println(pathFromStr("/safedir/doesntexist"))
}