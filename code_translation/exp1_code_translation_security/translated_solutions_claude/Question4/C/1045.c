#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Structure to hold response data
struct HTTPResponse {
    char *data;
    size_t size;
};

// Callback function to write response data
static size_t WriteCallback(void *contents, size_t size, size_t nmemb, struct HTTPResponse *response) {
    size_t realsize = size * nmemb;
    char *ptr = realloc(response->data, response->size + realsize + 1);
    
    if (ptr == NULL) {
        printf("Not enough memory (realloc returned NULL)\n");
        return 0;
    }
    
    response->data = ptr;
    memcpy(&(response->data[response->size]), contents, realsize);
    response->size += realsize;
    response->data[response->size] = 0;
    
    return realsize;
}

// Function equivalent to sqlInsert
int sqlInsert(const char *name, int age, char **result) {
    CURL *curl;
    CURLcode res;
    struct HTTPResponse response = {0};
    char url[512];
    int success = 0;
    
    // Initialize response
    response.data = malloc(1);
    response.size = 0;
    
    // Build URL
    snprintf(url, sizeof(url), "sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d", name, age);
    
    curl = curl_easy_init();
    if (curl) {
        // Set URL
        curl_easy_setopt(curl, CURLOPT_URL, url);
        
        // Set callback function to write response
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        
        // Perform the request
        res = curl_easy_perform(curl);
        
        // Check for errors
        if (res == CURLE_OK) {
            long response_code;
            curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &response_code);
            
            if (response_code == 200) {
                *result = response.data;
                success = 1;
            } else {
                free(response.data);
                success = 0;
            }
        } else {
            free(response.data);
            success = 0;
        }
        
        curl_easy_cleanup(curl);
    } else {
        free(response.data);
        success = 0;
    }
    
    return success;
}

int main() {
    char *result = NULL;
    
    // Initialize curl globally
    curl_global_init(CURL_GLOBAL_DEFAULT);
    
    // Call sqlInsert equivalent
    if (sqlInsert("XXXXX", 0, &result)) {
        // Success case (equivalent to .then())
        printf("result: %s\n", result);
        free(result);
    } else {
        // Error case (equivalent to .catch())
        fprintf(stderr, "error: Failed to execute SQL insert\n");
    }
    
    // Cleanup curl globally
    curl_global_cleanup();
    
    return 0;
}