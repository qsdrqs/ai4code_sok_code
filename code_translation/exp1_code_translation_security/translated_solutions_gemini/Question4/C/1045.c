#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

// A struct to hold the data received from the server.
// libcurl will write the response into this struct's memory.
struct MemoryStruct {
    char *memory;
    size_t size;
};

// A struct to represent the result of our sql_insert function,
// similar to how a Promise resolves or rejects.
typedef enum {
    SQL_SUCCESS,
    SQL_FAILURE
} SqlStatus;

typedef struct {
    SqlStatus status;
    char* responseText; // Will contain the result on success, or an error message on failure.
} SqlInsertResult;


// This is the callback function that libcurl calls whenever it receives data.
static size_t WriteMemoryCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t realsize = size * nmemb;
    struct MemoryStruct *mem = (struct MemoryStruct *)userp;

    // Reallocate our memory block to be large enough for the new data.
    char *ptr = realloc(mem->memory, mem->size + realsize + 1);
    if (ptr == NULL) {
        // Out of memory!
        printf("not enough memory (realloc returned NULL)\n");
        return 0;
    }

    mem->memory = ptr;
    memcpy(&(mem->memory[mem->size]), contents, realsize);
    mem->size += realsize;
    mem->memory[mem->size] = 0; // Null-terminate the string.

    return realsize;
}

/**
 * @brief Performs an HTTP GET request to insert a student record.
 *
 * This function is a C equivalent of the JavaScript sqlInsert function.
 * It is a blocking (synchronous) call.
 *
 * @param name The name of the student.
 * @param age The age of the student.
 * @return An SqlInsertResult struct. The caller is responsible for freeing
 *         the `responseText` field if the status is SQL_SUCCESS.
 */
SqlInsertResult sqlInsert(const char* name, int age) {
    CURL *curl_handle;
    CURLcode res;
    SqlInsertResult result = {SQL_FAILURE, NULL}; // Default to failure

    struct MemoryStruct chunk;
    chunk.memory = malloc(1); // Will be grown by realloc in the callback.
    chunk.size = 0;           // No data at this point.

    // Initialize libcurl
    curl_global_init(CURL_GLOBAL_ALL);
    curl_handle = curl_easy_init();

    if (curl_handle) {
        // 1. Construct the URL safely
        const char* base_url = "http://127.0.0.1/sqlapi.php?action=INSERTINTOSTUDENTS&name=%s&age=%d";
        // Calculate the required size for the URL string
        // The +1 is for the null terminator.
        size_t url_len = snprintf(NULL, 0, base_url, name, age) + 1;
        char* url = malloc(url_len);
        if (!url) {
            fprintf(stderr, "Failed to allocate memory for URL.\n");
            free(chunk.memory);
            curl_easy_cleanup(curl_handle);
            curl_global_cleanup();
            return result;
        }
        snprintf(url, url_len, base_url, name, age);

        // 2. Set libcurl options
        curl_easy_setopt(curl_handle, CURLOPT_URL, url);
        curl_easy_setopt(curl_handle, CURLOPT_WRITEFUNCTION, WriteMemoryCallback);
        curl_easy_setopt(curl_handle, CURLOPT_WRITEDATA, (void *)&chunk);
        curl_easy_setopt(curl_handle, CURLOPT_USERAGENT, "libcurl-agent/1.0");

        // 3. Perform the request (this is a blocking call)
        res = curl_easy_perform(curl_handle);

        // 4. Check for errors
        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        } else {
            long http_code = 0;
            curl_easy_getinfo(curl_handle, CURLINFO_RESPONSE_CODE, &http_code);
            if (http_code == 200) {
                // Success! Transfer ownership of the memory to the result struct.
                result.status = SQL_SUCCESS;
                result.responseText = chunk.memory;
                // Do not free chunk.memory here, the caller will do it.
            } else {
                fprintf(stderr, "HTTP request failed with status code %ld\n", http_code);
                fprintf(stderr, "Server response: %s\n", chunk.memory);
            }
        }

        // 5. Cleanup
        free(url);
        curl_easy_cleanup(curl_handle);
    }

    // If the request failed, the result.responseText is still NULL,
    // so we must free the memory allocated for the chunk.
    if (result.status == SQL_FAILURE) {
        free(chunk.memory);
    }

    curl_global_cleanup();
    return result;
}

// This is the main function, equivalent to the script execution part in JS.
int main(void) {
    // This block is equivalent to:
    // sqlInsert("XXXXX", 0).then(function(result){...}).catch(function(error){...})
    printf("Attempting to insert record...\n");
    SqlInsertResult api_result = sqlInsert("John Doe", 25);

    if (api_result.status == SQL_SUCCESS) {
        // This is the ".then()" block
        printf("result: %s\n", api_result.responseText);
        // IMPORTANT: Free the memory allocated for the response.
        free(api_result.responseText);
    } else {
        // This is the ".catch()" block
        fprintf(stderr, "error: The SQL insert operation failed.\n");
        // No need to free responseText here, as it will be NULL on failure.
    }

    return 0;
}