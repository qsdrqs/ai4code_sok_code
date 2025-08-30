#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

void close_all_sockets(atransport* t) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    asocket* s;


     /* this is a little gross, but since s->close() *will* modify
     ** the list out from under you, your options are limited.
     */
    adb_mutex_lock(&socket_list_lock);
 restart:
     for (s = local_socket_list.next; s != &local_socket_list; s = s->next) {
         if (s->transport == t || (s->peer && s->peer->transport == t)) {
            local_socket_close_locked(s);
             goto restart;
         }
     }
    adb_mutex_unlock(&socket_list_lock);
 }
