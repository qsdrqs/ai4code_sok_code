#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* msg, char key) {
    int msg_len = strlen(msg);
    // Allocate memory for result: each char becomes 2 chars (char + key) + null terminator
    char* temp = malloc((msg_len * 2 + 1) * sizeof(char));
    if (temp == NULL) {
        return NULL; // Memory allocation failed
    }
    
    temp[0] = '\0'; // Initialize as empty string
    
    for (int i = 0; i < msg_len; i++) {
        printf("%s\n", temp);
        
        // Append current character and key to temp
        int current_len = strlen(temp);
        temp[current_len] = msg[i];
        temp[current_len + 1] = key;
        temp[current_len + 2] = '\0';
    }
    
    return temp;
}

char* decrypt(const char* msg, char key) {
    int msg_len = strlen(msg);
    int control = 0;
    
    // Allocate memory for result (worst case: half of input length + 1)
    char* temp = malloc((msg_len / 2 + 1) * sizeof(char));
    if (temp == NULL) {
        return NULL; // Memory allocation failed
    }
    
    temp[0] = '\0'; // Initialize as empty string
    int temp_index = 0;
    
    for (int i = 0; i < msg_len; i++) {
        if (control % 2 == 0) {
            temp[temp_index] = msg[i];
            temp[temp_index + 1] = '\0';
            temp_index++;
        }
        control = control + 1;
    }
    
    return temp;
}

int main() {
    char* encrypted = encrypt("a", 'b');
    if (encrypted != NULL) {
        printf("%s\n", encrypted);
        
        char* decrypted = decrypt("ab", 'b');
        if (decrypted != NULL) {
            printf("%s\n", decrypted);
            free(decrypted);
        }
        
        free(encrypted);
    }
    
    return 0;
}