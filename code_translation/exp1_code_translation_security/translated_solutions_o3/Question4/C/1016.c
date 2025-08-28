/*
 * C version of the JavaScript “addStudent” routine.
 *
 * The function builds an SQL INSERT statement and sends it to
 *   http://localhost:8080/sql
 * with a POST request whose content-type is
 *   application/x-www-form-urlencoded
 *
 * NOTE:
 *   • It relies on libcurl for the HTTP transfer.
 *   • Any headers, build system files, or libraries that may be missing
 *     are assumed to be supplied in the environment where this code
 *     will be compiled/run, as per the exercise statement.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

void addStudent(const char *name, int age)
{
    /* ------------------------------------------------------------------
       1.  Build the raw SQL string
       ------------------------------------------------------------------ */
    char sql[512];
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    /* ------------------------------------------------------------------
       2.  URL-encode and wrap it into an x-www-form-urlencoded body:
           query=<url-encoded-sql>
       ------------------------------------------------------------------ */
    CURL *curl = curl_easy_init();         /* first thing: init curl handle */
    if (!curl) {
        fprintf(stderr, "Could not init libcurl.\n");
        return;
    }

    char *encoded = curl_easy_escape(curl, sql, 0);   /* 0 = strlen(sql)   */
    if (!encoded) {
        fprintf(stderr, "curl_easy_escape failed.\n");
        curl_easy_cleanup(curl);
        return;
    }

    size_t body_len = strlen("query=") + strlen(encoded) + 1;
    char *post_fields = malloc(body_len);
    if (!post_fields) {
        fprintf(stderr, "Out of memory.\n");
        curl_free(encoded);
        curl_easy_cleanup(curl);
        return;
    }
    sprintf(post_fields, "query=%s", encoded);

    /* ------------------------------------------------------------------
       3.  Configure and perform the HTTP POST
       ------------------------------------------------------------------ */
    curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/sql");
    curl_easy_setopt(curl, CURLOPT_POST, 1L);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, post_fields);

    struct curl_slist *hdrs = NULL;
    hdrs = curl_slist_append(hdrs,
                             "Content-Type: application/x-www-form-urlencoded");
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, hdrs);

    /* Optional: have curl dump the response to stdout */
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, stdout);

    CURLcode rc = curl_easy_perform(curl);
    if (rc != CURLE_OK) {
        fprintf(stderr, "curl_easy_perform() failed: %s\n",
                curl_easy_strerror(rc));
    }

    /* ------------------------------------------------------------------
       4.  Cleanup
       ------------------------------------------------------------------ */
    curl_slist_free_all(hdrs);
    curl_free(encoded);
    free(post_fields);
    curl_easy_cleanup(curl);
}

/* Simple demo */
int main(void)
{
    addStudent("Jane Doe", 21);
    return 0;
}