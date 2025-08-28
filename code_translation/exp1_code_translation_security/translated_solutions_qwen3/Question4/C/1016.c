#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

void addStudent(const char* name, int age) {
    // Buffer to hold the SQL query string
    char sql[1024];

    // Format the SQL INSERT statement
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Initialize CURL
    CURL* curl = curl_easy_init();
    if (!curl) {
        // Handle initialization failure (optional)
        return;
    }

    // Set up HTTP headers
    struct curl_slist* headers = NULL;
    headers = curl_slist_append(headers, "Content-Type: application/x-www-form-urlencoded");

    // Configure the CURL request
    curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/sql");
    curl_easy_setopt(curl, CURLOPT_POST, 1L);
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, sql);

    // Perform the request
    CURLcode res = curl_easy_perform(curl);

    // Cleanup
    curl_slist_free_all(headers);
    curl_easy_cleanup(curl);
}