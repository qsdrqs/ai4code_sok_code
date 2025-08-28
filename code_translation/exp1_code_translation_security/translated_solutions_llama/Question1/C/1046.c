#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to encrypt a message with a given key
char* encrypt(const char* msg, const char* key) {
    // Calculate the length of the message and key
    int msg_len = strlen(msg);
    int key_len = strlen(key);

    // Allocate memory for the encrypted message
    char* temp = (char*)malloc((msg_len * (key_len + 1) + 1) * sizeof(char));
    if (temp == NULL) {
        printf("Memory allocation failed\n");
        return NULL;
    }

    // Initialize temp string
    temp[0] = '\0';

    // Iterate over each character in the message
    for (int i = 0; i < msg_len; i++) {
        // Concatenate the character and key to temp
        char* temp_str = (char*)malloc((strlen(temp) + key_len + 2) * sizeof(char));
        if (temp_str == NULL) {
            printf("Memory allocation failed\n");
            free(temp);
            return NULL;
        }
        sprintf(temp_str, "%s%c%s", temp, msg[i], key);
        free(temp);
        temp = temp_str;
        printf("%s\n", temp);
    }

    return temp;
}

// Function to decrypt a message with a given key
char* decrypt(const char* msg) {
    int msg_len = strlen(msg);
    char* temp = (char*)malloc((msg_len / 2 + 1) * sizeof(char));
    if (temp == NULL) {
        printf("Memory allocation failed\n");
        return NULL;
    }

    temp[0] = '\0';

    int control = 0;
    for (int i = 0; i < msg_len; i += 2) {
        if (control % 2 == 0) {
            // Append the character to temp
            char* temp_str = (char*)malloc((strlen(temp) + 2) * sizeof(char));
            if (temp_str == NULL) {
                printf("Memory allocation failed\n");
                free(temp);
                return NULL;
            }
            sprintf(temp_str, "%s%c", temp, msg[i]);
            free(temp);
            temp = temp_str;
        }
        control++;
    }

    return temp;
}

int main() {
    char* encrypted_msg = encrypt("a", "b");
    printf("%s\n", encrypted_msg);
    free(encrypted_msg);

    char* decrypted_msg = decrypt("ab");
    printf("%s\n", decrypted_msg);
    free(decrypted_msg);

    return 0;
}