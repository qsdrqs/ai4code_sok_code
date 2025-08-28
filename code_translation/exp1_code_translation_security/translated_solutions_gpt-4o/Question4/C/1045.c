#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Structure to hold the response data
struct ResponseData {
    char *data;
    size_t size;
};

// Callback function to handle the response data
size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t totalSize = size * nmemb;
    struct ResponseData *response = (struct ResponseData *)userp;

    char *ptr = realloc(response->data, response->size + totalSize + 1);
    if (ptr == NULL) {
        fprintf(stderr, "Failed to allocate memory for response data\n");
        return 0;
    }

    response->data = ptr;
    memcpy(&(response->data[response->size]), contents, totalSize);
    response->size += totalSize;
    response->data[response->size] = '\0';

    return totalSize;
}

// Function to perform the SQL insert operation
char *sqlInsert(const char *name, int age) {
    CURL *curl;
    CURLcode res;
    struct ResponseData response;

    // Initialize the response data structure
    response.data = malloc(1);
    response.size = 0;

    // Initialize CURL
    curl = curl_easy_init();
    if (curl) {
        // Construct the URL
        char url[256];
        snprintf(url, sizeof(url), "http://yourserver.com/sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d", name, age);

        // Set CURL options
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&response);

        // Perform the request
        res = curl_easy_perform(curl);

        // Check for errors
        if (res != CURLE_OK) {
            fprintf(stderr, "CURL request failed: %s\n", curl_easy_strerror(res));
            free(response.data);
            response.data = NULL;
        }

        // Clean up CURL
        curl_easy_cleanup(curl);
    } else {
        fprintf(stderr, "Failed to initialize CURL\n");
        free(response.data);
        response.data = NULL;
    }

    return response.data;
}

int main() {
    // Call the sqlInsert function
    char *result = sqlInsert("XXXXX", 0);

    // Check the result
    if (result) {
        printf("Result: %s\n", result);
        free(result);
    } else {
        fprintf(stderr, "Error occurred during the SQL insert operation\n");
    }

    return 0;
}