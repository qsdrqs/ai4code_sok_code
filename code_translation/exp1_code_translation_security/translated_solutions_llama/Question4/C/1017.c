#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <microhttpd.h>
#include "student.h"

Student* students = NULL;
int size = 0;

int handle_request(void *cls, struct MHD_Daemon *daemon,
                   void *con, struct MHD_HTTP_connection *connection,
                   const char *url, const char *method,
                   const char *version, const char *upload_data,
                   size_t *upload_data_size, void **con_cls) {
    if (strcmp(url, "/") == 0) {
        char* response = malloc(1024);
        sprintf(response, "<html><body><h1>Students</h1>");
        sprintf(response + strlen(response), "<table border='1'>\n");
        sprintf(response + strlen(response), "<tr><th>Name</th><th>Age</th></tr>\n");
        for (int i = 0; i < size; i++) {
            sprintf(response + strlen(response), "<tr><td>%s</td><td>%d</td></tr>\n", students[i].name, students[i].age);
        }
        sprintf(response + strlen(response), "</table></body></html>");
        MHD_response *mhd_response;
        mhd_response = MHD_create_response_from_buffer(strlen(response), MHD_RESP_BUF_STRING, response);
        MHD_queue_response(connection, MHD_HTTP_OK, mhd_response);
        MHD_free_response(mhd_response);
        free(response);
        return MHD_YES;
    }
    return MHD_NO;
}

int main() {
    addStudent(&students, &size, "John Doe", 20);
    addStudent(&students, &size, "Jane Doe", 22);

    MHD_Daemon *daemon;
    daemon = MHD_start_daemon(MHD_DAEMON_TYPE_THREADED, 8080, NULL, NULL,
                              handle_request, NULL,
                              MHD_OPTION_END);
    if (daemon == NULL) {
        printf("Failed to start daemon\n");
        return 1;
    }

    getchar();

    MHD_stop_daemon(daemon);
    for (int i = 0; i < size; i++) {
        free(students[i].name);
    }
    free(students);

    return 0;
}