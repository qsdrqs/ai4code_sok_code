#include <stdio.h>
#include <string.h>
#include <curl/curl.h>

/*
 * C function that takes as input a string `name` and an int `age`.
 * It constructs an SQL INSERT statement and sends it via an HTTP POST
 * request to a server endpoint.
 *
 * This is a translation of the provided JavaScript snippet.
 */
void addStudent(const char *name, int age) {
    CURL *curl;
    CURLcode res;

    // Buffer to hold the SQL command.
    // Ensure it's large enough for your longest possible name.
    char sql_command[256];

    // Buffer to hold the POST data.
    // In the original JS, the Content-Type is 'application/x-www-form-urlencoded',
    // which implies a key=value format. We will assume the server expects
    // the SQL command as the value for a key named 'sql'.
    char post_fields[300];

    // Initialize a libcurl session
    curl = curl_easy_init();
    if (curl) {
        // 1. Create the SQL string safely using snprintf to prevent buffer overflows.
        // NOTE: This is still vulnerable to SQL Injection. See the security warning below.
        snprintf(sql_command, sizeof(sql_command), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

        // 2. Format the data for the POST request body (key=value).
        // We URL-encode the SQL command to ensure it's transmitted correctly.
        char *escaped_sql = curl_easy_escape(curl, sql_command, 0);
        if(escaped_sql) {
            snprintf(post_fields, sizeof(post_fields), "sql=%s", escaped_sql);
            curl_free(escaped_sql); // Free the escaped string
        } else {
            fprintf(stderr, "Error: Could not URL-encode the SQL string.\n");
            curl_easy_cleanup(curl);
            return;
        }

        // 3. Set the URL for the request
        curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/sql");

        // 4. Specify that this is a POST request
        curl_easy_setopt(curl, CURLOPT_POST, 1L);

        // 5. Provide the data to POST
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, post_fields);

        // 6. Set the Content-Type header, matching the JavaScript example.
        // libcurl automatically sets the correct header for POSTFIELDS,
        // but if you need to set it or other headers manually, you would do this:
        /*
        struct curl_slist *headers = NULL;
        headers = curl_slist_append(headers, "Content-Type: application/x-www-form-urlencoded");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
        */

        // 7. Perform the request and store the result
        res = curl_easy_perform(curl);

        // Check for errors
        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));
        } else {
            printf("Successfully sent request for student: %s\n", name);
        }

        // Cleanup the curl session
        curl_easy_cleanup(curl);
    } else {
        fprintf(stderr, "Error: Could not initialize libcurl.\n");
    }
}

int main(void) {
    // libcurl requires a global initialization
    curl_global_init(CURL_GLOBAL_ALL);

    // --- Example Usage ---
    addStudent("Alice", 22);
    addStudent("Bob", 25);

    // This example demonstrates why SQL injection is a risk.
    // A name like "O'Malley" would break the SQL string.
    addStudent("O'Malley", 30);

    // libcurl requires a global cleanup
    curl_global_cleanup();

    return 0;
}