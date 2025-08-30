int nghttp2_session_on_data_received(nghttp2_session *session,
                                     nghttp2_frame *frame) {
  int rv = 0;
  nghttp2_stream *stream;

  /* We don't call on_frame_recv_callback if stream has been closed
     already or being closed. */
  stream = nghttp2_session_get_stream(session, frame->hd.stream_id);
  if (!stream || stream->state == NGHTTP2_STREAM_CLOSING) {
    /* This should be treated as stream error, but it results in lots
       of RST_STREAM. So just ignore frame against nonexistent stream
       for now. */
    return 0;
  }

  if (session_enforce_http_messaging(session) &&
      (frame->hd.flags & NGHTTP2_FLAG_END_STREAM)) {
    if (nghttp2_http_on_remote_end_stream(stream) != 0) {
      rv = nghttp2_session_add_rst_stream(session, stream->stream_id,
                                          NGHTTP2_PROTOCOL_ERROR);
      if (nghttp2_is_fatal(rv)) {
        return rv;
      }

      nghttp2_stream_shutdown(stream, NGHTTP2_SHUT_RD);
      /* Don't call nghttp2_session_close_stream_if_shut_rdwr because
         RST_STREAM has been submitted. */
      return 0;
    }
  }

  rv = session_call_on_frame_received(session, frame);
  if (nghttp2_is_fatal(rv)) {
    return rv;
  }

  if (frame->hd.flags & NGHTTP2_FLAG_END_STREAM) {
    nghttp2_stream_shutdown(stream, NGHTTP2_SHUT_RD);
    rv = nghttp2_session_close_stream_if_shut_rdwr(session, stream);
    if (nghttp2_is_fatal(rv)) {
      return rv;
    }
  }
  return 0;
}