#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Function to add a student to the database
int addStudent(const char* name, int age) {
    CURL *curl;
    CURLcode res;

    // Create a curl handle
    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();
    if(curl) {
        // Construct the SQL query
        char sql[256];
        snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

        // Set up the request
        char* url = "http://localhost:8080/sql";
        char* data = malloc(strlen(sql) + 10);
        snprintf(data, strlen(sql) + 10, "sql=%s", sql);

        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, data);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, NULL); // You may want to set a write function

        // Perform the request
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            fprintf(stderr, "cURL error: %s\n", curl_easy_strerror(res));
        }

        // Clean up
        free(data);
        curl_easy_cleanup(curl);
    }

    curl_global_cleanup();

    return 0;
}

int main() {
    const char* name = "John Doe";
    int age = 20;

    addStudent(name, age);

    return 0;
}