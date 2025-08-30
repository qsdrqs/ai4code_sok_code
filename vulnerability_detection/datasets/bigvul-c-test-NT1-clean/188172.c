void close_all_sockets(atransport* buffer1) {
    asocket* buffer2;


     /* this is a little gross, but since s->close() *will* modify
     ** the list out from under you, your options are limited.
     */
    adb_mutex_lock(&socket_list_lock);
 restart:
     for (buffer2 = local_socket_list.next; buffer2 != &local_socket_list; buffer2 = buffer2->next) {
         if (buffer2->transport == buffer1 || (buffer2->peer && buffer2->peer->transport == buffer1)) {
            local_socket_close_locked(buffer2);
             goto restart;
         }
     }
    adb_mutex_unlock(&socket_list_lock);
 }
