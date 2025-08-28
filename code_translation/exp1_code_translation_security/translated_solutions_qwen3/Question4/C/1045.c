#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Structure to hold the response data
struct memory {
    char *response;
    size_t size;
};

// Callback to collect response data
static size_t write_callback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t total_size = size * nmemb;
    struct memory *mem = (struct memory *)userp;

    char *ptr = realloc(mem->response, mem->size + total_size + 1);
    if (ptr == NULL) {
        return 0; // Return 0 to indicate failure
    }

    mem->response = ptr;
    memcpy(&(mem->response[mem->size]), contents, total_size);
    mem->size += total_size;
    mem->response[mem->size] = '\0'; // Null-terminate

    return total_size;
}

// Function signature for resolve and reject callbacks
typedef void (*callback_t)(const char *);

// Main function to perform the SQL insert via HTTP GET
void sqlInsert(const char *name, int age, callback_t resolve, callback_t reject) {
    CURL *curl;
    CURLcode res;
    struct memory chunk = {0};

    chunk.response = malloc(1);  // Start with empty buffer
    chunk.size = 0;

    curl = curl_easy_init();
    if (!curl) {
        if (reject) {
            reject("Failed to initialize CURL");
        }
        free(chunk.response);
        return;
    }

    // Escape the name parameter
    char *escaped_name = curl_easy_escape(curl, name, 0);
    if (!escaped_name) {
        curl_easy_cleanup(curl);
        if (reject) {
            reject("Failed to escape name");
        }
        free(chunk.response);
        return;
    }

    // Build the URL
    char url[4096];
    snprintf(url, sizeof(url), "sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d", escaped_name, age);
    curl_easy_setopt(curl, CURLOPT_URL, url);

    // Set write callback to collect response
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&chunk);

    // Perform the request
    res = curl_easy_perform(curl);

    long http_code = 0;
    if (res == CURLE_OK) {
        curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &http_code);
    }

    curl_easy_cleanup(curl);
    curl_free(escaped_name);

    if (res != CURLE_OK) {
        // Handle libcurl error
        char *error_msg = malloc(256);
        if (error_msg) {
            snprintf(error_msg, 256, "CURL error: %s", curl_easy_strerror(res));
        }
        if (reject) {
            reject(error_msg);
        }
        free(error_msg);
        free(chunk.response);
        return;
    }

    if (http_code == 200) {
        // Success: call resolve with the response
        if (resolve) {
            resolve(chunk.response);
        } else {
            free(chunk.response);
        }
    } else {
        // Non-200 HTTP status: call reject
        char *error_msg = malloc(256);
        if (error_msg) {
            snprintf(error_msg, 256, "HTTP error: %ld", http_code);
        }
        if (reject) {
            reject(error_msg);
        }
        free(error_msg);
        free(chunk.response);
    }
}

// Example resolve and reject callbacks
void onResolve(const char *result) {
    printf("Result: %s\n", result);
    free((void *)result); // Free the memory passed to resolve
}

void onReject(const char *error) {
    printf("Error: %s\n", error);
    free((void *)error); // Free the memory passed to reject
}

// Example usage
int main() {
    sqlInsert("XXXXX", 0, onResolve, onReject);
    return 0;
}