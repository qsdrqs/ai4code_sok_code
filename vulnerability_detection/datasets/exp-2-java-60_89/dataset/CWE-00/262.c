@Override
    public synchronized SSLEngineResult wrap(
            final ByteBuffer[] srcs, final int offset, final int length, final ByteBuffer dst) throws SSLException {

        // Check to make sure the engine has not been closed
        if (isDestroyed()) {
            return CLOSED_NOT_HANDSHAKING;
        }

        // Throw required runtime exceptions
        if (srcs == null) {
            throw new IllegalArgumentException("srcs is null");
        }
        if (dst == null) {
            throw new IllegalArgumentException("dst is null");
        }

        if (offset >= srcs.length || offset + length > srcs.length) {
            throw new IndexOutOfBoundsException(
                    "offset: " + offset + ", length: " + length +
                            " (expected: offset <= offset + length <= srcs.length (" + srcs.length + "))");
        }

        if (dst.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }

        HandshakeStatus status = NOT_HANDSHAKING;
        // Prepare OpenSSL to work in server mode and receive handshake
        if (handshakeState != HandshakeState.FINISHED) {
            if (handshakeState != HandshakeState.STARTED_EXPLICITLY) {
                // Update accepted so we know we triggered the handshake via wrap
                handshakeState = HandshakeState.STARTED_IMPLICITLY;
            }

            status = handshake();
            if (status == NEED_UNWRAP) {
                return NEED_UNWRAP_OK;
            }

            if (engineClosed) {
                return NEED_UNWRAP_CLOSED;
            }
        }

        int bytesProduced = 0;

        // There was no pending data in the network BIO -- encrypt any application data
        int bytesConsumed = 0;
        int endOffset = offset + length;
        for (int i = offset; i < endOffset; ++ i) {
            final ByteBuffer src = srcs[i];
            if (src == null) {
                throw new IllegalArgumentException("srcs[" + i + "] is null");
            }
            while (src.hasRemaining()) {

                // Write plaintext application data to the SSL engine
                int result = writePlaintextData(src);
                if (result > 0) {
                    bytesConsumed += result;
                } else {
                    int sslError = SSL.getError(ssl, result);
                    switch (sslError) {
                    case SSL.SSL_ERROR_ZERO_RETURN:
                        // This means the connection was shutdown correctly, close inbound and outbound
                        if (!receivedShutdown) {
                            closeAll();
                        }
                        // fall-trough!
                    case SSL.SSL_ERROR_WANT_READ:
                    case SSL.SSL_ERROR_WANT_WRITE:
                        // Break here as this means we need check if there is something pending in the BIO
                        break;
                    default:
                        // Everything else is considered as error
                        throw shutdownWithError("SSL_write");
                    }
                }

                SSLEngineResult pendingNetResult = readPendingBytesFromBIO(dst, bytesConsumed, bytesProduced, status);
                if (pendingNetResult != null) {
                    return pendingNetResult;
                }
            }
        }
        // We need to check if pendingWrittenBytesInBIO was checked yet, as we may not checked if the srcs was empty,
        // or only contained empty buffers.
        if (bytesConsumed == 0) {
            SSLEngineResult pendingNetResult = readPendingBytesFromBIO(dst, 0, bytesProduced, status);
            if (pendingNetResult != null) {
                return pendingNetResult;
            }
        }

        return newResult(bytesConsumed, bytesProduced, status);
    }