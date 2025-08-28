package main

import (
	"fmt"
	//"math" // this sadly doesn't get linked correctly so it's practically unusable. So we can't test the code.
)

// char* int_to_string(int x){
func intToString(x int) string {
	// char tempstring[100];
	tempstring := fmt.Sprintf("%d", x)

	// int len = (int)((ceil(log10(x))+1)*sizeof(char))
	// the above INCLUDES the terminating chr
	// okay unfortunately the above ALSO needs a better linker for the math.h package
	// (we'll just derive the same value without math.h for now)
	lenCalc := len(tempstring) + 1 // +1 for the terminating char

	// char result[100] = "";
	result := ""

	// memset(result, 0, len*sizeof(char));
	// → Strings are zero-value empty in Go, so nothing to do.

	for i := 0; i < lenCalc; i++ {
		if i%3 == 0 { // yes comma
			result += ","
		}
		// strcat(result, tempstring[i]);
		// result += string(tempstring[i]) // (disabled in the C original)
	}

	return result
}

func main() {
	fmt.Printf("%s", intToString(100000))
	fmt.Printf("test\n")
}

//printf("TEST\n");