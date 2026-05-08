#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

/* Dynamic buffer that accumulates the HTTP response body */
typedef struct {
    char *data;
    size_t size;
} ResponseBuffer;

/* libcurl write callback: appends received bytes into ResponseBuffer */
static size_t write_callback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t realsize = size * nmemb;
    ResponseBuffer *buf = (ResponseBuffer *)userp;

    char *ptr = realloc(buf->data, buf->size + realsize + 1);
    if (!ptr) return 0;            /* out of memory -> abort transfer */

    buf->data = ptr;
    memcpy(buf->data + buf->size, contents, realsize);
    buf->size += realsize;
    buf->data[buf->size] = '\0';
    return realsize;
}

/*
 * Equivalent of the JS sqlInsert().
 * Returns the response body (malloc'd, caller must free) on HTTP 200,
 * or NULL on any error. This mirrors resolve/reject of the original Promise.
 */
char *sqlInsert(const char *name, int age) {
    CURL *curl;
    CURLcode res;
    ResponseBuffer buf = { NULL, 0 };
    char url[1024];
    long http_code = 0;

    buf.data = malloc(1);
    if (!buf.data) return NULL;
    buf.data[0] = '\0';

    curl = curl_easy_init();
    if (!curl) { free(buf.data); return NULL; }

    snprintf(url, sizeof(url),
             "http://localhost/sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d",
             name, age);

    curl_easy_setopt(curl, CURLOPT_URL, url);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&buf);

    res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
        free(buf.data);
        curl_easy_cleanup(curl);
        return NULL;
    }

    curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &http_code);
    curl_easy_cleanup(curl);

    if (http_code == 200) {
        return buf.data;           /* analogous to resolve(this.responseText) */
    }

    free(buf.data);
    return NULL;                   /* analogous to an unresolved / rejected promise */
}

int main(void) {
    char *result;

    curl_global_init(CURL_GLOBAL_DEFAULT);

    /* sqlInsert("XXXXX", 0).then(...).catch(...) */
    result = sqlInsert("XXXXX", 0);
    if (result) {
        printf("result %s\n", result);     /* console.log("result", result) */
        free(result);
    } else {
        fprintf(stderr, "error\n");        /* console.error("error", error) */
    }

    curl_global_cleanup();
    return 0;
}