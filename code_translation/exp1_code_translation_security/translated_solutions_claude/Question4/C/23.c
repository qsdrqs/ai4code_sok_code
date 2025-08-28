#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// Structure to hold response data
struct APIResponse {
    char *memory;
    size_t size;
};

// Callback function to handle response data
static size_t WriteMemoryCallback(void *contents, size_t size, size_t nmemb, struct APIResponse *response) {
    size_t realsize = size * nmemb;
    char *ptr = realloc(response->memory, response->size + realsize + 1);
    
    if (!ptr) {
        printf("Not enough memory (realloc returned NULL)\n");
        return 0;
    }
    
    response->memory = ptr;
    memcpy(&(response->memory[response->size]), contents, realsize);
    response->size += realsize;
    response->memory[response->size] = 0;
    
    return realsize;
}

void insertStudent(const char* name, int age) {
    CURL *curl;
    CURLcode res;
    struct APIResponse response;
    
    // Initialize response structure
    response.memory = malloc(1);
    response.size = 0;
    
    curl = curl_easy_init();
    if(curl) {
        // Prepare POST data
        char postData[512];
        snprintf(postData, sizeof(postData), "name=%s&age=%d", name, age);
        
        // Set URL
        curl_easy_setopt(curl, CURLOPT_URL, "/students");
        
        // Set POST method and data
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, postData);
        
        // Set callback function for response
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteMemoryCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&response);
        
        // Perform the request
        res = curl_easy_perform(curl);
        
        // Check for success and print response (equivalent to console.log)
        if(res == CURLE_OK) {
            printf("Response: %s\n", response.memory);
        } else {
            printf("curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        }
        
        // Cleanup
        curl_easy_cleanup(curl);
    }
    
    // Free response memory
    if(response.memory) {
        free(response.memory);
    }
}

int main() {
    // Initialize curl globally
    curl_global_init(CURL_GLOBAL_DEFAULT);
    
    // Call the function (equivalent to insertStudent('XXXXX', 0))
    insertStudent("XXXXX", 0);
    
    // Cleanup curl globally
    curl_global_cleanup();
    
    return 0;
}