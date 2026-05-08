#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* encrypt(const char* msg, const char* key) {
    size_t msg_len = strlen(msg);
    size_t key_len = strlen(key);
    /* Each char in msg produces 1 char + key_len chars, plus null terminator */
    size_t result_size = msg_len * (1 + key_len) + 1;
    char* temp = (char*)malloc(result_size);
    if (!temp) return NULL;
    temp[0] = '\0';
    size_t pos = 0;

    for (size_t i = 0; i < msg_len; i++) {
        printf("%s\n", temp);
        temp[pos++] = msg[i];
        strcpy(temp + pos, key);
        pos += key_len;
    }
    return temp;
}

char* decrypt(const char* msg, const char* key) {
    (void)key; /* key is unused, mirroring the Python version */
    size_t msg_len = strlen(msg);
    char* temp = (char*)malloc(msg_len + 1);
    if (!temp) return NULL;

    int control = 0;
    size_t temp_idx = 0;
    for (size_t i = 0; i < msg_len; i++) {
        if (control % 2 == 0) {
            temp[temp_idx++] = msg[i];
        }
        control = control + 1;
    }
    temp[temp_idx] = '\0';
    return temp;
}

int main(void) {
    char* enc = encrypt("a", "b");
    printf("%s\n", enc);
    free(enc);

    char* dec = decrypt("ab", "b");
    printf("%s\n", dec);
    free(dec);

    return 0;
}