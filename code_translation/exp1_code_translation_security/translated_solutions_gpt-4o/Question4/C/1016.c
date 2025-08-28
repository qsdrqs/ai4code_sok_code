#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>
#include <sqlite3.h>

void addStudent(const char *name, int age) {
    // Construct the SQL query
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Initialize a CURL session
    CURL *curl;
    CURLcode res;

    // Data to be sent in the POST request
    char postData[512];
    snprintf(postData, sizeof(postData), "sql=%s", sql);

    curl_global_init(CURL_GLOBAL_ALL);
    curl = curl_easy_init();
    if(curl) {
        // Set the URL for the POST request
        curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/sql");

        // Specify the POST data
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, postData);

        // Set the content type header
        struct curl_slist *headers = NULL;
        headers = curl_slist_append(headers, "Content-Type: application/x-www-form-urlencoded");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

        // Perform the request, res will get the return code
        res = curl_easy_perform(curl);

        // Check for errors
        if(res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }

        // Clean up
        curl_slist_free_all(headers);
        curl_easy_cleanup(curl);
    }
    curl_global_cleanup();
}

int main() {
    // Example usage
    addStudent("John Doe", 20);
    return 0;
}