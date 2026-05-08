#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

/* C function that takes as input a String name and an int age and adds a new
   entry to an SQL table named "STUDENTS" with columns for 'NAME' and 'AGE' */
void addStudent(const char *name, int age) {
    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    CURL *request;
    CURLcode res;

    request = curl_easy_init();
    if (request) {
        struct curl_slist *headers = NULL;
        headers = curl_slist_append(headers,
                    "Content-Type: application/x-www-form-urlencoded");

        curl_easy_setopt(request, CURLOPT_URL, "http://localhost:8080/sql");
        curl_easy_setopt(request, CURLOPT_POST, 1L);
        curl_easy_setopt(request, CURLOPT_HTTPHEADER, headers);
        curl_easy_setopt(request, CURLOPT_POSTFIELDS, sql);

        res = curl_easy_perform(request);

        if (res != CURLE_OK) {
            fprintf(stderr, "curl_easy_perform() failed: %s\n",
                    curl_easy_strerror(res));
        }

        curl_slist_free_all(headers);
        curl_easy_cleanup(request);
    }
}