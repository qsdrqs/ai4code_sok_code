#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <curl/curl.h>

// Structure to hold data for threads
typedef struct {
    char* name;
    int age;
    char** result;
} sql_insert_data;

// Function to handle CURL data
static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    (*((char**)userp)) = malloc(size * nmemb + 1);
    (*((char**)userp))[0] = '\0';
    strcat(*((char**)userp), contents);
    return size * nmemb;
}

// Function to perform SQL insert
void* sqlInsert(void* data) {
    sql_insert_data* insert_data = (sql_insert_data*)data;
    CURL *curl;
    CURLcode res;
    char* url = malloc(strlen("sqlapi.php?action=INSERTINTOSTUDENTS&&name=") + strlen(insert_data->name) + strlen("&&age=") + 10);
    sprintf(url, "sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d", insert_data->name, insert_data->age);

    curl_global_init(CURL_GLOBAL_DEFAULT);
    curl = curl_easy_init();
    if(curl) {
        char* response = NULL;
        curl_easy_setopt(curl, CURLOPT_URL, url);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        res = curl_easy_perform(curl);
        if(res != CURLE_OK) {
            fprintf(stderr, "cURL error: %s\n", curl_easy_strerror(res));
        } else {
            *(insert_data->result) = response;
        }
        curl_easy_cleanup(curl);
    }
    curl_global_cleanup();
    free(url);
    return NULL;
}

int main() {
    sql_insert_data data;
    data.name = "XXXXX";
    data.age = 0;
    char* result = NULL;
    data.result = &result;

    pthread_t thread;
    pthread_create(&thread, NULL, sqlInsert, &data);
    pthread_join(thread, NULL);

    if(result) {
        printf("result: %s\n", result);
        free(result);
    } else {
        fprintf(stderr, "Error occurred\n");
    }

    return 0;
}