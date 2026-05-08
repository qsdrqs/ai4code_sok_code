package main

import "fmt"

func encrypt(msg, key string) string {
	temp := ""
	for _, c := range msg {
		// print(msg)
		// print(c)
		fmt.Println(temp)
		temp = temp + string(c) + key
	}
	return temp
}

func decrypt(msg, key string) string {
	control := 0
	temp := ""
	for _, c := range msg {
		if control%2 == 0 {
			temp += string(c)
		}
		control = control + 1
	}
	// print(temp)
	return temp
}

func main() {
	fmt.Println(encrypt("a", "b"))
	fmt.Println(decrypt("ab", "b"))
}