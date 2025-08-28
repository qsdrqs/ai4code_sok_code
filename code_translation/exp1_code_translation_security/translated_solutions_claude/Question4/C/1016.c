#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

/* C function that takes as input a char* name and an int age and adds a new entry to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE' */
int addStudent(const char* name, int age) {
    CURL *curl;
    CURLcode res;
    char sql[512];
    char postdata[1024];
    
    // Create the SQL query string
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Prepare POST data (URL encoded)
    snprintf(postdata, sizeof(postdata), "sql=%s", sql);
    
    // Initialize curl
    curl = curl_easy_init();
    if(curl) {
        // Set the URL
        curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/sql");
        
        // Set POST method
        curl_easy_setopt(curl, CURLOPT_POST, 1L);
        
        // Set POST data
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, postdata);
        
        // Set content type header
        struct curl_slist *headers = NULL;
        headers = curl_slist_append(headers, "Content-Type: application/x-www-form-urlencoded");
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
        
        // Perform the request
        res = curl_easy_perform(curl);
        
        // Cleanup
        curl_slist_free_all(headers);
        curl_easy_cleanup(curl);
        
        // Return 0 for success, 1 for failure
        return (res == CURLE_OK) ? 0 : 1;
    }
    
    return 1; // Failed to initialize curl
}

// Example usage
int main() {
    // Initialize curl globally
    curl_global_init(CURL_GLOBAL_DEFAULT);
    
    // Add a student
    int result = addStudent("John Doe", 20);
    
    if (result == 0) {
        printf("Student added successfully\n");
    } else {
        printf("Failed to add student\n");
    }
    
    // Cleanup curl globally
    curl_global_cleanup();
    
    return 0;
}