package main

import (
	"fmt"
	"strconv"
)

func numToStr(num int) string {
	str := strconv.Itoa(num)
	
	length := len(str)
	
	if num == 0 {
		return str
	} else if num > 0 {
		numCommas := (length - 1) / 3
		if numCommas == 0 {
			return str
		}
		
		// Create a new string with space for commas
		result := make([]byte, length+numCommas)
		digitsMoved := 0
		
		for i := length; i > 0; i-- {
			if digitsMoved < 3 {
				result[i+numCommas-1] = str[i-1]
				digitsMoved++
			} else {
				result[i+numCommas-1] = ','
				numCommas--
				digitsMoved = 0
				i++ // Process the same position again
			}
		}
		
		return string(result)
	} else {
		numCommas := (length - 2) / 3
		if numCommas == 0 {
			return str
		}
		
		// Create a new string with space for commas
		result := make([]byte, length+numCommas)
		digitsMoved := 0
		
		// Copy the negative sign first
		result[0] = str[0]
		
		for i := length; i > 1; i-- {
			if digitsMoved < 3 {
				result[i+numCommas-1] = str[i-1]
				digitsMoved++
			} else {
				result[i+numCommas-1] = ','
				numCommas--
				digitsMoved = 0
				i++ // Process the same position again
			}
		}
		
		return string(result)
	}
}

func main() {
	num := 7042
	
	result := numToStr(num)
	
	fmt.Printf("Output: %s", result)
}