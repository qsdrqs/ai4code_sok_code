private ChannelBuffer unwrap(
            ChannelHandlerContext ctx, Channel channel,
            ChannelBuffer nettyInNetBuf, ByteBuffer nioInNetBuf,
            int initialNettyOutAppBufCapacity) throws SSLException {

        final int nettyInNetBufStartOffset = nettyInNetBuf.readerIndex();
        final int nioInNetBufStartOffset = nioInNetBuf.position();
        final ByteBuffer nioOutAppBuf = bufferPool.acquireBuffer();

        ChannelBuffer nettyOutAppBuf = null;

        try {
            boolean needsWrap = false;
            for (;;) {
                SSLEngineResult result;
                boolean needsHandshake = false;
                synchronized (handshakeLock) {
                    if (!handshaken && !handshaking &&
                        !engine.getUseClientMode() &&
                        !engine.isInboundDone() && !engine.isOutboundDone()) {
                        needsHandshake = true;
                    }
                }

                if (needsHandshake) {
                    handshake();
                }

                synchronized (handshakeLock) {
                    // Decrypt at least one record in the inbound network buffer.
                    // It is impossible to consume no record here because we made sure the inbound network buffer
                    // always contain at least one record in decode().  Therefore, if SSLEngine.unwrap() returns
                    // BUFFER_OVERFLOW, it is always resolved by retrying after emptying the application buffer.
                    for (;;) {
                        try {
                            result = engine.unwrap(nioInNetBuf, nioOutAppBuf);
                            switch (result.getStatus()) {
                                case CLOSED:
                                    // notify about the CLOSED state of the SSLEngine. See #137
                                    sslEngineCloseFuture.setClosed();
                                    break;
                                case BUFFER_OVERFLOW:
                                    // Flush the unwrapped data in the outAppBuf into frame and try again.
                                    // See the finally block.
                                    continue;
                            }

                            break;
                        } finally {
                            nioOutAppBuf.flip();

                            // Sync the offset of the inbound buffer.
                            nettyInNetBuf.readerIndex(
                                    nettyInNetBufStartOffset + nioInNetBuf.position() - nioInNetBufStartOffset);

                            // Copy the unwrapped data into a smaller buffer.
                            if (nioOutAppBuf.hasRemaining()) {
                                if (nettyOutAppBuf == null) {
                                    ChannelBufferFactory factory = ctx.getChannel().getConfig().getBufferFactory();
                                    nettyOutAppBuf = factory.getBuffer(initialNettyOutAppBufCapacity);
                                }
                                nettyOutAppBuf.writeBytes(nioOutAppBuf);
                            }
                            nioOutAppBuf.clear();
                        }
                    }

                    final HandshakeStatus handshakeStatus = result.getHandshakeStatus();
                    handleRenegotiation(handshakeStatus);
                    switch (handshakeStatus) {
                    case NEED_UNWRAP:
                        break;
                    case NEED_WRAP:
                        wrapNonAppData(ctx, channel);
                        break;
                    case NEED_TASK:
                        runDelegatedTasks();
                        break;
                    case FINISHED:
                        setHandshakeSuccess(channel);
                        needsWrap = true;
                        continue;
                    case NOT_HANDSHAKING:
                        if (setHandshakeSuccessIfStillHandshaking(channel)) {
                            needsWrap = true;
                            continue;
                        }
                        if (writeBeforeHandshakeDone) {
                            // We need to call wrap(...) in case there was a flush done before the handshake completed.
                            //
                            // See https://github.com/netty/netty/pull/2437
                            writeBeforeHandshakeDone = false;
                            needsWrap = true;
                        }
                        break;
                    default:
                        throw new IllegalStateException(
                                "Unknown handshake status: " + handshakeStatus);
                    }

                    if (result.getStatus() == Status.BUFFER_UNDERFLOW ||
                        result.bytesConsumed() == 0 && result.bytesProduced() == 0) {
                        break;
                    }
                }
            }

            if (needsWrap) {
                // wrap() acquires pendingUnencryptedWrites first and then
                // handshakeLock.  If handshakeLock is already hold by the
                // current thread, calling wrap() will lead to a dead lock
                // i.e. pendingUnencryptedWrites -> handshakeLock vs.
                //      handshakeLock -> pendingUnencryptedLock -> handshakeLock
                //
                // There is also the same issue between pendingEncryptedWrites
                // and pendingUnencryptedWrites.
                if (!Thread.holdsLock(handshakeLock) && !pendingEncryptedWritesLock.isHeldByCurrentThread()) {
                    wrap(ctx, channel);
                }
            }
        } catch (SSLException e) {
            setHandshakeFailure(channel, e);
            throw e;
        } finally {
            bufferPool.releaseBuffer(nioOutAppBuf);
        }

        if (nettyOutAppBuf != null && nettyOutAppBuf.readable()) {
            return nettyOutAppBuf;
        } else {
            return null;
        }
    }