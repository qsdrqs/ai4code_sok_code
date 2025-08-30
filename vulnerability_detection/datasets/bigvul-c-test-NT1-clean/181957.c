lrmd_remote_listen(gpointer data)
{
    int csock = 0;
    int flag = 0;
    unsigned laddr = 0;
    struct sockaddr addr;
    gnutls_session_t *buffer1 = NULL;
    crm_client_t *buffer2 = NULL;

    static struct mainloop_fd_callbacks lrmd_remote_fd_cb = {
        .dispatch = lrmd_remote_client_msg,
        .destroy = lrmd_remote_client_destroy,
    };

    laddr = sizeof(addr);
    memset(&addr, 0, sizeof(addr));
    getsockname(ssock, &addr, &laddr);

    /* accept the connection */

    if (addr.sa_family == AF_INET6) {
        struct sockaddr_in6 sa;
        char buffer3[INET6_ADDRSTRLEN];

        laddr = sizeof(sa);
        memset(&sa, 0, sizeof(sa));
        csock = accept(ssock, &sa, &laddr);
        get_ip_str((struct sockaddr *)&sa, buffer3, INET6_ADDRSTRLEN);
        crm_info("New remote connection from %s", buffer3);

    } else {
        struct sockaddr_in sa;
        char buffer4[INET_ADDRSTRLEN];

        laddr = sizeof(sa);
        memset(&sa, 0, sizeof(sa));
        csock = accept(ssock, &sa, &laddr);
        get_ip_str((struct sockaddr *)&sa, buffer4, INET_ADDRSTRLEN);
        crm_info("New remote connection from %s", buffer4);
    }

    if (csock == -1) {
        crm_err("accept socket failed");
        return TRUE;
    }

    if ((flag = fcntl(csock, F_GETFL)) >= 0) {
        if (fcntl(csock, F_SETFL, flag | O_NONBLOCK) < 0) {
            crm_err("fcntl() write failed");
            close(csock);
            return TRUE;
        }
    } else {
        crm_err("fcntl() read failed");
        close(csock);
        return TRUE;
    }

    buffer1 = create_psk_tls_session(csock, GNUTLS_SERVER, psk_cred_s);
    if (buffer1 == NULL) {
        crm_err("TLS session creation failed");
        close(csock);
        return TRUE;
    }

    buffer2 = calloc(1, sizeof(crm_client_t));
    buffer2->remote = calloc(1, sizeof(crm_remote_t));
    buffer2->kind = CRM_CLIENT_TLS;
    buffer2->remote->tls_session = buffer1;
    buffer2->id = crm_generate_uuid();
    buffer2->remote->auth_timeout =
        g_timeout_add(LRMD_REMOTE_AUTH_TIMEOUT, lrmd_auth_timeout_cb, buffer2);
    crm_notice("LRMD client connection established. %p id: %s", buffer2, buffer2->id);

    buffer2->remote->source =
        mainloop_add_fd("lrmd-remote-client", G_PRIORITY_DEFAULT, csock, buffer2,
                         &lrmd_remote_fd_cb);
     g_hash_table_insert(client_connections, buffer2->id, buffer2);
 
    /* Alert other clients of the new connection */
    notify_of_new_client(buffer2);
     return TRUE;
 }
