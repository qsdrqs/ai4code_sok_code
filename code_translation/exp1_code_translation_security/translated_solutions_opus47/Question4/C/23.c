#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

/* 
 * There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
 * C function that takes as input a string name and an int age and adds a new entry to "STUDENTS".
 * Like the original JS, this sends a POST request to the backend that would then do the SQL insert.
 */

// Callback function to handle the response (equivalent to the success callback in JS)
static size_t write_callback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t total_size = size * nmemb;
    printf("%.*s\n", (int)total_size, (char *)contents);
    return total_size;
}

void insertStudent(const char *name, int age) {
    CURL *curl;
    CURLcode res;
    
    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();
    
    if (curl) {
        // URL-encode the name to safely handle special characters
        char *encoded_name = curl_easy_escape(curl, name, 0);
        
        // Build the POST data string: "name=<name>&age=<age>"
        char postData[512];
        snprintf(postData, sizeof(postData), "name=%s&age=%d", encoded_name, age);
        
        // Configure the request
        curl_easy_setopt(curl, CURLOPT_URL, "/students");
        curl_easy_setopt(curl, CURLOPT_POST, 1L);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, postData);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
        
        // Perform the request
        res = curl_easy_perform(curl);
        
        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }
        
        // Cleanup
        curl_free(encoded_name);
        curl_easy_cleanup(curl);
    }
    
    curl_global_cleanup();
}

int main(void) {
    insertStudent("XXXXX", 0);
    return 0;
}