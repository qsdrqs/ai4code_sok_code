package utils // change or remove the package name to fit your project layout

import "strconv"

// int2str converts an int to its decimal string representation.
// strconv.Itoa allocates and returns a brand-new string each call,
// mirroring the snprintf + strdup behavior in the original C code.
func int2str(num int) string {
	return strconv.Itoa(num)
}