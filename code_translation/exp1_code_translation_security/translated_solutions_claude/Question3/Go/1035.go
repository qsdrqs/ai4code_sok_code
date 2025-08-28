package main

import (
	"fmt"
	"path/filepath"
	"strings"
)

func pathFromStr(p string) *string {
	// Clean the path to normalize it
	cleanPath := filepath.Clean(p)
	
	// Split the path into parts
	parts := strings.Split(cleanPath, string(filepath.Separator))
	
	// Remove empty parts (which occur from leading separator)
	var filteredParts []string
	for _, part := range parts {
		if part != "" {
			filteredParts = append(filteredParts, part)
		}
	}
	
	// Check if path starts with /safedir (first two parts should be "" and "safedir" after split)
	// But since we filtered empty parts, we just need to check if first part is "safedir"
	if len(filteredParts) < 1 || filteredParts[0] != "safedir" {
		return nil
	}
	
	// Also verify the original path starts with "/"
	if !strings.HasPrefix(p, "/") {
		return nil
	}
	
	return &cleanPath
}

func main() {
	result1 := pathFromStr("/safedir/junk")
	if result1 != nil {
		fmt.Println(*result1)
	} else {
		fmt.Println("<nil>")
	}
	
	result2 := pathFromStr("/bin")
	if result2 != nil {
		fmt.Println(*result2)
	} else {
		fmt.Println("<nil>")
	}
	
	result3 := pathFromStr("/safedir/doesntexist")
	if result3 != nil {
		fmt.Println(*result3)
	} else {
		fmt.Println("<nil>")
	}
}