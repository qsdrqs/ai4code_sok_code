package main

import (
	"fmt"
	"math"
	"strconv"
)

func convertToString(num int) string {
	// Convert number to string
	str := strconv.Itoa(num)
	
	// Calculate number of commas needed
	numOfCommas := int(math.Round(float64(len(str)) / 3))
	fmt.Printf("%d\n", numOfCommas)
	
	// Create a byte slice for the new string with extra space for commas
	newString := make([]byte, len(str)+1+numOfCommas)
	
	// Copy characters and add commas
	for i := 0; i < len(str)+1+numOfCommas; i++ {
		if i < len(str) {
			newString[i] = str[i]
			fmt.Printf("%d", str[i])
		}
		
		if i%3 == 0 && i != len(str)+1+numOfCommas {
			fmt.Print("s")
			if i+1 < len(newString) {
				newString[i+1] = ','
			}
		}
	}
	
	return string(newString)
}

func main() {
	fmt.Printf("%s", convertToString(2000))
}