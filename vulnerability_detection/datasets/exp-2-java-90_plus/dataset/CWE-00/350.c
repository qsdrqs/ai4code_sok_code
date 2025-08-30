private State readHeaders(ByteBuf buffer) {
        final HttpMessage message = this.message;
        final HttpHeaders headers = message.headers();

        AppendableCharSequence line = headerParser.parse(buffer);
        if (line == null) {
            return null;
        }
        if (line.length() > 0) {
            do {
                char firstChar = line.charAtUnsafe(0);
                if (name != null && (firstChar == ' ' || firstChar == '\t')) {
                    //please do not make one line from below code
                    //as it breaks +XX:OptimizeStringConcat optimization
                    String trimmedLine = line.toString().trim();
                    String valueStr = String.valueOf(value);
                    value = valueStr + ' ' + trimmedLine;
                } else {
                    if (name != null) {
                        headers.add(name, value);
                    }
                    splitHeader(line);
                }

                line = headerParser.parse(buffer);
                if (line == null) {
                    return null;
                }
            } while (line.length() > 0);
        }

        // Add the last header.
        if (name != null) {
            headers.add(name, value);
        }

        // reset name and value fields
        name = null;
        value = null;

        List<String> contentLengthFields = headers.getAll(HttpHeaderNames.CONTENT_LENGTH);

        if (!contentLengthFields.isEmpty()) {
            // Guard against multiple Content-Length headers as stated in
            // https://tools.ietf.org/html/rfc7230#section-3.3.2:
            //
            // If a message is received that has multiple Content-Length header
            //   fields with field-values consisting of the same decimal value, or a
            //   single Content-Length header field with a field value containing a
            //   list of identical decimal values (e.g., "Content-Length: 42, 42"),
            //   indicating that duplicate Content-Length header fields have been
            //   generated or combined by an upstream message processor, then the
            //   recipient MUST either reject the message as invalid or replace the
            //   duplicated field-values with a single valid Content-Length field
            //   containing that decimal value prior to determining the message body
            //   length or forwarding the message.
            boolean multipleContentLengths =
                    contentLengthFields.size() > 1 || contentLengthFields.get(0).indexOf(COMMA) >= 0;
            if (multipleContentLengths && message.protocolVersion() == HttpVersion.HTTP_1_1) {
                if (allowDuplicateContentLengths) {
                    // Find and enforce that all Content-Length values are the same
                    String firstValue = null;
                    for (String field : contentLengthFields) {
                        String[] tokens = COMMA_PATTERN.split(field, -1);
                        for (String token : tokens) {
                            String trimmed = token.trim();
                            if (firstValue == null) {
                                firstValue = trimmed;
                            } else if (!trimmed.equals(firstValue)) {
                                throw new IllegalArgumentException(
                                        "Multiple Content-Length values found: " + contentLengthFields);
                            }
                        }
                    }
                    // Replace the duplicated field-values with a single valid Content-Length field
                    headers.set(HttpHeaderNames.CONTENT_LENGTH, firstValue);
                    contentLength = Long.parseLong(firstValue);
                } else {
                    // Reject the message as invalid
                    throw new IllegalArgumentException(
                            "Multiple Content-Length values found: " + contentLengthFields);
                }
            } else {
                contentLength = Long.parseLong(contentLengthFields.get(0));
            }
        }

        if (isContentAlwaysEmpty(message)) {
            HttpUtil.setTransferEncodingChunked(message, false);
            return State.SKIP_CONTROL_CHARS;
        } else if (HttpUtil.isTransferEncodingChunked(message)) {
            if (!contentLengthFields.isEmpty() && message.protocolVersion() == HttpVersion.HTTP_1_1) {
                handleTransferEncodingChunkedWithContentLength(message);
            }
            return State.READ_CHUNK_SIZE;
        } else if (contentLength() >= 0) {
            return State.READ_FIXED_LENGTH_CONTENT;
        } else {
            return State.READ_VARIABLE_LENGTH_CONTENT;
        }
    }