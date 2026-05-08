package main

import (
	"fmt"
	"strings"
)

func pathFromStr(p string) *string {
	parts := strings.Split(p, "/")
	// In Python, Path("/safedir/...").parts returns ("/", "safedir", ...)
	// strings.Split("/safedir/...", "/") returns ["", "safedir", ...]
	// where "" corresponds to the leading "/"
	if len(parts) < 2 || parts[0] != "" || parts[1] != "safedir" {
		return nil
	}
	return &p
}

func printPath(p *string) {
	if p == nil {
		fmt.Println("None")
	} else {
		fmt.Println(*p)
	}
}

func main() {
	printPath(pathFromStr("/safedir/junk"))
	printPath(pathFromStr("/bin"))
	printPath(pathFromStr("/safedir/doesntexist"))
}