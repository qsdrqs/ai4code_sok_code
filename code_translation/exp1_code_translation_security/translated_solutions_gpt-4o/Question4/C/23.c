#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Function to insert a student into the "STUDENTS" table
void insertStudent(const char *name, int age) {
    CURL *curl;
    CURLcode res;

    // Initialize libcurl
    curl = curl_easy_init();
    if (curl) {
        // URL of the endpoint
        const char *url = "http://localhost/students"; // Replace with your actual endpoint

        // Prepare the POST data
        char postData[256];
        snprintf(postData, sizeof(postData), "name=%s&age=%d", name, age);

        // Set the URL
        curl_easy_setopt(curl, CURLOPT_URL, url);

        // Set the HTTP method to POST
        curl_easy_setopt(curl, CURLOPT_POST, 1L);

        // Set the POST data
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, postData);

        // Perform the request
        res = curl_easy_perform(curl);

        // Check for errors
        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        } else {
            printf("Student inserted successfully.\n");
        }

        // Clean up
        curl_easy_cleanup(curl);
    } else {
        fprintf(stderr, "Failed to initialize libcurl.\n");
    }
}

int main() {
    // Example usage of the insertStudent function
    insertStudent("XXXXX", 0);

    return 0;
}