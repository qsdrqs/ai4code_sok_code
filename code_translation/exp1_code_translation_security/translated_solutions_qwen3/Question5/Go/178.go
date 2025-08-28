package main

import (
	"fmt"
	"math"
	"strconv"
)

func convertToString(num int) string {
	s := strconv.Itoa(num)
	space := len(s)
	numOfCommas := int(math.Round(float64(space / 3)))
	fmt.Printf("%d\n", numOfCommas)
	newStringLength := space + numOfCommas
	newString := make([]byte, newStringLength)
	for i := 0; i < newStringLength; i++ {
		if i < space {
			newString[i] = s[i]
		}
		fmt.Printf("%d", newString[i])
		if i%3 == 0 && i != newStringLength {
			fmt.Print("s")
			if i+1 < newStringLength {
				newString[i+1] = ','
			}
		}
	}
	return string(newString)
}

func main() {
	fmt.Print(convertToString(2000))
}