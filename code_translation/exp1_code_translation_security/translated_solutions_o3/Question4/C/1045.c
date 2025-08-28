/*
 *  sql_insert.c
 *
 *  C translation of:
 *      sqlInsert("XXXXX",0)
 *          .then( … )
 *          .catch( … );
 *
 *  Build example (Linux):
 *      gcc sql_insert.c -o sql_insert -lcurl -lpthread
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <curl/curl.h>

/* ------------------------------------------------------------------ */
/*  Small helper that grows a buffer while libcurl is writing to it   */

typedef struct {
    char  *ptr;
    size_t len;
} dyn_buffer;

static void buffer_init(dyn_buffer *b)
{
    b->len = 0;
    b->ptr = malloc(1);          /* will be grown by realloc later   */
    if (b->ptr) b->ptr[0] = '\0';
}

static size_t curl_write_cb(void *data, size_t size, size_t nmemb, void *userp)
{
    size_t      add = size * nmemb;
    dyn_buffer *buf = (dyn_buffer *)userp;

    char *tmp = realloc(buf->ptr, buf->len + add + 1);
    if (!tmp)                       /* out of memory → abort transfer */
        return 0;

    buf->ptr = tmp;
    memcpy(buf->ptr + buf->len, data, add);
    buf->len += add;
    buf->ptr[buf->len] = '\0';
    return add;
}

/* ------------------------------------------------------------------ */
/*  "Promise"-like infrastructure                                     */

typedef void (*success_cb)(const char *result, void *user_data);
typedef void (*error_cb)  (const char *error , void *user_data);

/*  Everything a worker thread needs to know */
typedef struct {
    char       *name;
    int         age;
    success_cb  on_ok;
    error_cb    on_err;
    void       *u;        /* user_data                                */
} worker_args;

/* ------------------------------------------------------------------ */
/*  Thread entry – performs the GET request                           */

static void *worker(void *arg)
{
    worker_args *p = (worker_args *)arg;

    CURL *curl = curl_easy_init();
    if (!curl) {
        if (p->on_err) p->on_err("curl_easy_init failed", p->u);
        goto cleanup;
    }

    char url[1024];
    snprintf(url, sizeof url,
             "http://localhost/sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d",
             p->name, p->age);

    dyn_buffer buf;
    buffer_init(&buf);

    curl_easy_setopt(curl, CURLOPT_URL,            url);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION,  curl_write_cb);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA,     &buf);

    CURLcode cc = curl_easy_perform(curl);
    if (cc == CURLE_OK) {
        if (p->on_ok) p->on_ok(buf.ptr, p->u);
    } else {
        if (p->on_err) p->on_err(curl_easy_strerror(cc), p->u);
    }

    free(buf.ptr);
    curl_easy_cleanup(curl);

cleanup:
    free(p->name);
    free(p);
    return NULL;
}

/* ------------------------------------------------------------------ */
/*  Public API: “sqlInsert”                                           */

void sqlInsert(const char  *name,
               int          age,
               success_cb   then_cb,
               error_cb     catch_cb,
               void        *user_data)
{
    worker_args *wa = malloc(sizeof *wa);
    if (!wa) {
        if (catch_cb) catch_cb("malloc failed", user_data);
        return;
    }

    wa->name   = strdup(name);
    wa->age    = age;
    wa->on_ok  = then_cb;
    wa->on_err = catch_cb;
    wa->u      = user_data;

    pthread_t tid;
    if (pthread_create(&tid, NULL, worker, wa) != 0) {
        if (catch_cb) catch_cb("pthread_create failed", user_data);
        free(wa->name);
        free(wa);
        return;
    }
    pthread_detach(tid);          /* fire-and-forget, like a Promise  */
}

/* ------------------------------------------------------------------ */
/*  Example usage (mirrors the JS demo)                               */

static void on_success(const char *result, void *u)
{
    (void)u;
    printf("result: %s\n", result);
}

static void on_error(const char *err, void *u)
{
    (void)u;
    fprintf(stderr, "error : %s\n", err);
}

int main(void)
{
    curl_global_init(CURL_GLOBAL_DEFAULT);

    sqlInsert("XXXXX", 0, on_success, on_error, NULL);

    /* keep the process alive long enough for the detached thread      */
    /* a real program would use a proper event loop / join / CV        */
    puts("(press <Enter> to quit)");
    getchar();

    curl_global_cleanup();
    return 0;
}