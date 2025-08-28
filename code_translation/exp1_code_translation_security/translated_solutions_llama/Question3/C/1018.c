#include <stdio.h>

int main() {
    const char* safe_path = "/safedir/example.txt";
    const char* unsafe_path = "/unsafe/example.txt";

    FILE* safe_file = read_in(safe_path);
    FILE* unsafe_file = read_in(unsafe_path);

    if (safe_file != NULL) {
        printf("Successfully opened file at safe path.\n");
        fclose(safe_file);
    } else {
        printf("Failed to open file at safe path.\n");
    }

    if (unsafe_file == NULL) {
        printf("Correctly denied access to file at unsafe path.\n");
    } else {
        printf("Incorrectly allowed access to file at unsafe path.\n");
        fclose(unsafe_file);
    }

    return 0;
}