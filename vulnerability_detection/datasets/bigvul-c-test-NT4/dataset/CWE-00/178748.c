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

static void _out_result(conn_t out, nad_t nad) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    int attr;
    jid_t from, to;
    char *rkey;
    int rkeylen;

    attr = nad_find_attr(nad, 0, -1, "from", NULL);
    if(attr < 0 || (from = jid_new(NAD_AVAL(nad, attr), NAD_AVAL_L(nad, attr))) == NULL) {
        log_debug(ZONE, "missing or invalid from on db result packet");
        nad_free(nad);
        return;
    }

    attr = nad_find_attr(nad, 0, -1, "to", NULL);
    if(attr < 0 || (to = jid_new(NAD_AVAL(nad, attr), NAD_AVAL_L(nad, attr))) == NULL) {
        log_debug(ZONE, "missing or invalid to on db result packet");
        jid_free(from);
        nad_free(nad);
        return;
    }

    rkey = s2s_route_key(NULL, to->domain, from->domain);
     rkeylen = strlen(rkey);
 
     /* key is valid */
    if(nad_find_attr(nad, 0, -1, "type", "valid") >= 0) {
         log_write(out->s2s->log, LOG_NOTICE, "[%d] [%s, port=%d] outgoing route '%s' is now valid%s%s", out->fd->fd, out->ip, out->port, rkey, (out->s->flags & SX_SSL_WRAPPER) ? ", TLS negotiated" : "", out->s->compressed ? ", ZLIB compression enabled" : "");
 
         xhash_put(out->states, pstrdup(xhash_pool(out->states), rkey), (void *) conn_VALID);    /* !!! small leak here */

        log_debug(ZONE, "%s valid, flushing queue", rkey);

        /* flush the queue */
        out_flush_route_queue(out->s2s, rkey, rkeylen);

        free(rkey);

        jid_free(from);
        jid_free(to);

        nad_free(nad);

        return;
    }

    /* invalid */
    log_write(out->s2s->log, LOG_NOTICE, "[%d] [%s, port=%d] outgoing route '%s' is now invalid", out->fd->fd, out->ip, out->port, rkey);

    /* close connection */
    log_write(out->s2s->log, LOG_NOTICE, "[%d] [%s, port=%d] closing connection", out->fd->fd, out->ip, out->port);

    /* report stream error */
    sx_error(out->s, stream_err_INVALID_ID, "dialback negotiation failed");

    /* close the stream */
    sx_close(out->s);

    /* bounce queue */
    out_bounce_route_queue(out->s2s, rkey, rkeylen, stanza_err_SERVICE_UNAVAILABLE);

    free(rkey);

    jid_free(from);
    jid_free(to);

    nad_free(nad);
}
