#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>
#include <sqlite3.h>

// Function to write memory callback for libcurl
static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    ((char*)userp)[0] = '\0';
    return size * nmemb;
}

// Function to insert a student into the database directly using sqlite3
int insertStudentDirectly(const char *name, int age) {
    sqlite3 *db;
    char *errMsg = NULL;
    int rc;

    // Open the database
    rc = sqlite3_open("students.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Prepare SQL statement
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    sqlite3_stmt *stmt;
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute SQL statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
    }

    // Finalize statement and close database
    sqlite3_finalize(stmt);
    sqlite3_close(db);

    return 0;
}

// Function to insert a student using an HTTP POST request
int insertStudentViaHTTP(const char *name, int age) {
    CURL *curl;
    CURLcode res;
    char readBuffer[1024];

    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();
    if(curl) {
        char *url = "http://localhost:8080/students"; // Replace with your actual URL
        char *data = NULL;
        asprintf(&data, "name=%s&age=%d", name, age);

        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, readBuffer);
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, data);

        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            fprintf(stderr, "cURL error: %s\n", curl_easy_strerror(res));
        } else {
            printf("%s\n", readBuffer);
        }

        free(data);
        curl_easy_cleanup(curl);
    }
    curl_global_cleanup();

    return 0;
}

int main() {
    const char *name = "XXXXX";
    int age = 0;

    // Insert student directly into SQLite database
    insertStudentDirectly(name, age);

    // Insert student via HTTP POST request
    insertStudentViaHTTP(name, age);

    return 0;
}