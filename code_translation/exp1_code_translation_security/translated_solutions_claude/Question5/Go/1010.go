package main

import (
	"fmt"
	"strconv"
	"strings"
)

func stringIt(value int) string {
	// Handle negative numbers
	negative := false
	if value < 0 {
		negative = true
		value = -value
	}
	
	// Convert to string
	str := strconv.Itoa(value)
	
	length := len(str)
	commaCount := (length - 1) / 3
	
	// Build result with commas
	result := str
	end := length
	
	for commaCount > 0 {
		start := end - 3
		fmt.Println(str[start:]) // This matches the printf in original C code
		
		// Insert comma
		result = result[:start] + "," + result[start:]
		commaCount--
		end = start
	}
	
	// Add negative sign if needed
	if negative {
		result = "-" + result
	}
	
	return result
}

func main() {
	fmt.Printf("   %s\n", stringIt(-123457))
}