#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to encrypt the message
char* encrypt(const char* msg, const char* key) {
    int msg_len = strlen(msg);
    int key_len = strlen(key);
    int result_len = msg_len * (key_len + 1) + 1; // Each character in msg is followed by key
    char* temp = (char*)malloc(result_len * sizeof(char)); // Allocate memory for the result
    temp[0] = '\0'; // Initialize the string

    for (int i = 0; i < msg_len; i++) {
        strncat(temp, &msg[i], 1); // Append the current character from msg
        strcat(temp, key);         // Append the key
        printf("%s\n", temp);      // Print the intermediate result
    }

    return temp;
}

// Function to decrypt the message
char* decrypt(const char* msg, const char* key) {
    int msg_len = strlen(msg);
    int key_len = strlen(key);
    int result_len = msg_len / (key_len + 1) + 1; // Calculate the length of the decrypted message
    char* temp = (char*)malloc(result_len * sizeof(char)); // Allocate memory for the result
    int control = 0;
    int temp_index = 0;

    for (int i = 0; i < msg_len; i++) {
        if (control % 2 == 0) {
            temp[temp_index++] = msg[i]; // Add the character to the result
        }
        control++;
    }

    temp[temp_index] = '\0'; // Null-terminate the string
    return temp;
}

int main() {
    char* encrypted = encrypt("a", "b");
    printf("Encrypted: %s\n", encrypted);

    char* decrypted = decrypt("ab", "b");
    printf("Decrypted: %s\n", decrypted);

    // Free allocated memory
    free(encrypted);
    free(decrypted);

    return 0;
}