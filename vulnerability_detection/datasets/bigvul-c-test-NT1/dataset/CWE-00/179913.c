static int read_request_line(request_rec *buffer1, apr_bucket_brigade *buffer2)
{
    const char *buffer3;
    const char *buffer4;
    const char *buffer5;

    unsigned int major = 1, minor = 0;   /* Assume HTTP/1.0 if non-"HTTP" protocol */
    char buffer6[5];
    apr_size_t len;
    int num_blank_lines = 0;
    int max_blank_lines = r->server->limit_req_fields;
    core_server_config *buffer7 = ap_get_core_module_config(r->server->module_config);
    int strict = conf->http_conformance & AP_HTTP_CONFORMANCE_STRICT;
    int enforce_strict = !(conf->http_conformance & AP_HTTP_CONFORMANCE_LOGONLY);

    if (max_blank_lines <= 0) {
        max_blank_lines = DEFAULT_LIMIT_REQUEST_FIELDS;
    }

    /* Read past empty lines until we get a real request line,
     * a read error, the connection closes (EOF), or we timeout.
     *
     * We skip empty lines because browsers have to tack a CRLF on to the end
     * of POSTs to support old CERN webservers.  But note that we may not
     * have flushed any previous response completely to the client yet.
     * We delay the flush as long as possible so that we can improve
     * performance for clients that are pipelining requests.  If a request
     * is pipelined then we won't block during the (implicit) read() below.
     * If the requests aren't pipelined, then the client is still waiting
     * for the final buffer flush from us, and we will block in the implicit
     * read().  B_SAFEREAD ensures that the BUFF layer flushes if it will
     * have to block during a read.
     */

    do {
        apr_status_t rv;

        /* ensure ap_rgetline allocates memory each time thru the loop
         * if there are empty lines
         */
        buffer1->the_request = NULL;
        rv = ap_rgetline(&(buffer1->the_request), (apr_size_t)(buffer1->server->limit_req_line + 2),
                         &len, buffer1, 0, buffer2);

        if (rv != APR_SUCCESS) {
            buffer1->request_time = apr_time_now();

            /* ap_rgetline returns APR_ENOSPC if it fills up the
             * buffer before finding the end-of-line.  This is only going to
             * happen if it exceeds the configured limit for a request-line.
              */
             if (APR_STATUS_IS_ENOSPC(rv)) {
                 buffer1->status    = HTTP_REQUEST_URI_TOO_LARGE;
                buffer1->proto_num = HTTP_VERSION(1,0);
                buffer1->protocol  = apr_pstrdup(buffer1->pool, "HTTP/1.0");
             }
             else if (APR_STATUS_IS_TIMEUP(rv)) {
                 buffer1->status = HTTP_REQUEST_TIME_OUT;
             }
             else if (APR_STATUS_IS_EINVAL(rv)) {
                 buffer1->status = HTTP_BAD_REQUEST;
             }
             return 0;
         }
     } while ((len <= 0) && (++num_blank_lines < max_blank_lines));

    if (APLOGrtrace5(buffer1)) {
        ap_log_rerror(APLOG_MARK, APLOG_TRACE5, 0, buffer1,
                      "Request received from client: %s",
                      ap_escape_logitem(buffer1->pool, buffer1->the_request));
    }

    buffer1->request_time = apr_time_now();
    buffer3 = buffer1->the_request;
    buffer1->method = ap_getword_white(buffer1->pool, &buffer3);

    buffer4 = ap_getword_white(buffer1->pool, &buffer3);

    /* Provide quick information about the request method as soon as known */

    buffer1->method_number = ap_method_number_of(buffer1->method);
    if (buffer1->method_number == M_GET && buffer1->method[0] == 'H') {
        buffer1->header_only = 1;
    }

    ap_parse_uri(buffer1, buffer4);

    if (buffer3[0]) {
        buffer1->assbackwards = 0;
        buffer5 = buffer3;
        len = strlen(buffer3);
    } else {
        buffer1->assbackwards = 1;
        buffer5 = "HTTP/0.9";
        len = 8;
        if (buffer7->http09_enable == AP_HTTP09_DISABLE) {
                buffer1->status = HTTP_VERSION_NOT_SUPPORTED;
                buffer1->protocol = apr_pstrmemdup(buffer1->pool, buffer5, len);
                /* If we deny 0.9, send error message with 1.x */
                buffer1->assbackwards = 0;
                buffer1->proto_num = HTTP_VERSION(0, 9);
                buffer1->connection->keepalive = AP_CONN_CLOSE;
                ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02401)
                              "HTTP/0.9 denied by server configuration");
                return 0;
        }
    }
    buffer1->protocol = apr_pstrmemdup(buffer1->pool, buffer5, len);

    /* Avoid sscanf in the common case */
    if (len == 8
        && buffer5[0] == 'H' && buffer5[1] == 'T' && buffer5[2] == 'T' && buffer5[3] == 'P'
        && buffer5[4] == '/' && apr_isdigit(buffer5[5]) && buffer5[6] == '.'
        && apr_isdigit(buffer5[7])) {
        buffer1->proto_num = HTTP_VERSION(buffer5[5] - '0', buffer5[7] - '0');
    }
    else {
        if (strict) {
            ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02418)
                          "Invalid protocol '%s'", buffer1->protocol);
            if (enforce_strict) {
                buffer1->status = HTTP_BAD_REQUEST;
                return 0;
            }
        }
        if (3 == sscanf(buffer1->protocol, "%4s/%u.%u", buffer6, &major, &minor)
            && (strcasecmp("http", buffer6) == 0)
            && (minor < HTTP_VERSION(1, 0)) ) { /* don't allow HTTP/0.1000 */
            buffer1->proto_num = HTTP_VERSION(major, minor);
        }
        else {
            buffer1->proto_num = HTTP_VERSION(1, 0);
        }
    }

    if (strict) {
        int err = 0;
        if (ap_has_cntrl(buffer1->the_request)) {
            ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02420)
                          "Request line must not contain control characters");
            err = HTTP_BAD_REQUEST;
        }
        if (buffer1->parsed_uri.fragment) {
            /* RFC3986 3.5: no fragment */
            ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02421)
                          "URI must not contain a fragment");
            err = HTTP_BAD_REQUEST;
        }
        else if (buffer1->parsed_uri.user || buffer1->parsed_uri.password) {
            ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02422)
                          "URI must not contain a username/password");
            err = HTTP_BAD_REQUEST;
        }
        else if (buffer1->method_number == M_INVALID) {
            ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02423)
                          "Invalid HTTP method string: %s", buffer1->method);
            err = HTTP_NOT_IMPLEMENTED;
        }
        else if (buffer1->assbackwards == 0 && buffer1->proto_num < HTTP_VERSION(1, 0)) {
            ap_log_rerror(APLOG_MARK, APLOG_DEBUG, 0, buffer1, APLOGNO(02424)
                          "HTTP/0.x does not take a protocol");
            err = HTTP_BAD_REQUEST;
        }

        if (err && enforce_strict) {
            buffer1->status = err;
            return 0;
        }
    }

    return 1;
}
