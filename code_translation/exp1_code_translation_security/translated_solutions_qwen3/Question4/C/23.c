#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Structure to hold the response data
struct response_data {
    char *memory;
    size_t size;
};

// Callback function to write the response into memory
static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t total_size = size * nmemb;
    struct response_data *response = (struct response_data *)userp;

    char *ptr = realloc(response->memory, response->size + total_size + 1);
    if (!ptr) {
        return 0; // Out of memory
    }

    response->memory = ptr;
    memcpy(&(response->memory[response->size]), contents, total_size);
    response->size += total_size;
    response->memory[response->size] = '\0'; // Null-terminate

    return total_size;
}

// Function to insert a student into the STUDENTS table via HTTP POST
void insertStudent(const char *name, int age) {
    CURL *curl = curl_easy_init();
    struct response_data response;
    response.memory = malloc(1);
    response.size = 0;

    if (!curl || !response.memory) {
        fprintf(stderr, "Failed to initialize curl or allocate memory.\n");
        goto cleanup;
    }

    // URL-encode the name
    char *encoded_name = curl_easy_escape(curl, name, 0);
    if (!encoded_name) {
        fprintf(stderr, "Failed to URL-encode the name.\n");
        goto cleanup;
    }

    // Create the POST data string
    int len = snprintf(NULL, 0, "name=%s&age=%d", encoded_name, age);
    char *postdata = malloc(len + 1);
    if (!postdata) {
        fprintf(stderr, "Failed to allocate memory for POST data.\n");
        curl_free(encoded_name);
        goto cleanup;
    }
    snprintf(postdata, len + 1, "name=%s&age=%d", encoded_name, age);
    curl_free(encoded_name);

    // Set up the curl options
    curl_easy_setopt(curl, CURLOPT_URL, "/students");
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, postdata);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

    // Set the Content-Type header
    struct curl_slist *headers = NULL;
    headers = curl_slist_append(headers, "Content-Type: application/x-www-form-urlencoded");
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

    // Perform the request
    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
        fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
    } else {
        printf("Response: %s\n", response.memory);
    }

cleanup:
    if (headers) curl_slist_free_all(headers);
    if (curl) curl_easy_cleanup(curl);
    if (response.memory) free(response.memory);
    if (postdata) free(postdata);
}

// Main function to test the insertStudent function
int main() {
    insertStudent("XXXXX", 0);
    return 0;
}