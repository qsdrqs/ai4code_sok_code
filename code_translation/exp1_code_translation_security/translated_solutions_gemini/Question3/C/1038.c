#include <stdio.h> // For printf in the example

/*
 * In C, we use a struct to represent an object with attributes.
 * This FileObject struct mimics the Python 'file' object that has
 * 'name' and 'path' attributes.
 */
typedef struct {
    char* name;
    char* path;
} FileObject;

/*
 * @brief Gets the name of a file object.
 *
 * @param file A pointer to the FileObject.
 * @return A char pointer to the file's name.
 *
 * NOTE: The original Python code had an unreachable second return statement.
 * This function faithfully translates the reachable logic.
 */
char* get_file_path(FileObject* file) {
    // The function returns the 'name' attribute.
    return file->name;

    // The following line is unreachable, just like in the original Python code.
    // A C compiler will likely issue a warning about this.
    return file->path;
}

// --- Example Usage ---
int main() {
    // 1. Create an instance of our FileObject
    FileObject my_file;
    my_file.name = "document.txt";
    my_file.path = "/usr/home/user/documents/document.txt";

    // 2. Call the function, passing the address of our object
    char* result = get_file_path(&my_file);

    // 3. Print the result
    // It will print "document.txt" because that is the first return value.
    printf("The result from get_file_path is: %s\n", result);

    return 0;
}