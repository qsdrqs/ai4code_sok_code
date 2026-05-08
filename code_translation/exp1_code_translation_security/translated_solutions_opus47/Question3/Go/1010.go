package main

import (
	"os"
	"strings"
)

/*
Open a file
*/
func openPath(filename string) *os.File {
	// Worry about ".." and any way of getting out of director
	matchCount := strings.Index(filename, "/safedir/")
	if matchCount != 0 {
		return nil
	}
	// Possible weird cases with encodings of ".." I'd have to research
	if strings.Contains(filename, "/../") { // Safe but overly conservative
		return nil
	}
	// Open filename
	file, err := os.Create(filename)
	if err != nil {
		return nil
	}
	return file
}

func main() {
	// fmt.Println(openPath("/safedir/Jim"))
}