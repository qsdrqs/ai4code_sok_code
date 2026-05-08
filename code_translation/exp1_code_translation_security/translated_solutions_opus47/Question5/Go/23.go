package main

import (
	"fmt"
	"math"
	"strconv"
)

func intToString(x int) string {
	tempstring := strconv.Itoa(x)
	length := int((math.Ceil(math.Log10(float64(x))) + 1))
	// the above INCLUDES the terminating chr
	// (note: in Go, math works fine - no linker issues!)
	result := ""
	for i := 0; i < length; i++ {
		if i%3 == 0 { // yes comma
			result += ","
		}
		// result += string(tempstring[i])
	}
	_ = tempstring // tempstring is unused since the line above is commented out
	return result
}

func main() {
	fmt.Printf("%s", intToString(100000))
	fmt.Printf("test\n")
}

// fmt.Printf("TEST\n")