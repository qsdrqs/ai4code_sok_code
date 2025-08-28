#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to find index of character in alphabet array
int find_alpha_index(char c) {
    return c - 'a';
}

// Function to encrypt a string with Caesar cipher
char* encrypt(char* str, int key) {
    char alpha[] = "abcdefghijklmnopqrstuvwxyz";
    int len = strlen(str);
    char* newString = (char*)malloc((len + 1) * sizeof(char));
    int newIndex = 0;
    
    for (int i = 0; i < len; i++) {
        char c = str[i];
        if (c == ' ') {
            newString[newIndex] = ' ';
            newIndex++;
            continue;
        }
        
        int alphaIndex = find_alpha_index(c);
        printf("%d\n", alphaIndex);
        
        if (alphaIndex + key > 25) {  // 25 is len(alpha) - 1
            alphaIndex = key - (25 - alphaIndex);
            newString[newIndex] = alpha[alphaIndex - 1];  // -1 because we're wrapping around
        } else {
            newString[newIndex] = alpha[alphaIndex + key];
        }
        newIndex++;
    }
    
    newString[newIndex] = '\0';  // Null terminate the string
    return newString;
}

// Function to decrypt a string with Caesar cipher
char* decrypt(char* str, int key) {
    char alpha[] = "abcdefghijklmnopqrstuvwxyz";
    int len = strlen(str);
    char* newString = (char*)malloc((len + 1) * sizeof(char));
    int newIndex = 0;
    
    for (int i = 0; i < len; i++) {
        char c = str[i];
        if (c == ' ') {
            continue;  // Skip spaces in decryption
        }
        
        int alphaIndex = find_alpha_index(c);
        int decryptedIndex = alphaIndex - key;
        
        // Handle negative wrap-around
        if (decryptedIndex < 0) {
            decryptedIndex += 26;
        }
        
        newString[newIndex] = alpha[decryptedIndex];
        newIndex++;
    }
    
    newString[newIndex] = '\0';  // Null terminate the string
    return newString;
}

// Function to print a string (since C doesn't have built-in list printing)
void print_string(char* str) {
    printf("%s\n", str);
}

int main() {
    char* encrypted = encrypt("hello worlz", 3);
    print_string(encrypted);
    
    char* decrypted = decrypt(encrypted, 3);
    print_string(decrypted);
    
    // Free allocated memory
    free(encrypted);
    free(decrypted);
    
    return 0;
}