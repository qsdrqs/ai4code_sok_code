void HTTP2Codec::generateHeaderImpl(
    folly::IOBufQueue& writeBuf,
    StreamID stream,
    const HTTPMessage& msg,
    const folly::Optional<StreamID>& assocStream,
    const folly::Optional<HTTPCodec::ExAttributes>& exAttributes,
    bool eom,
    HTTPHeaderSize* size) {
  if (assocStream) {
    CHECK(!exAttributes);
    VLOG(4) << "generating PUSH_PROMISE for stream=" << stream;
  } else if (exAttributes) {
    CHECK(!assocStream);
    VLOG(4) << "generating ExHEADERS for stream=" << stream
            << " with control stream=" << exAttributes->controlStream
            << " unidirectional=" << exAttributes->unidirectional;
  } else {
    VLOG(4) << "generating HEADERS for stream=" << stream;
  }

  if (!isStreamIngressEgressAllowed(stream)) {
    VLOG(2) << "Suppressing HEADERS/PROMISE for stream=" << stream <<
      " ingressGoawayAck_=" << ingressGoawayAck_;
    if (size) {
      size->uncompressed = 0;
      size->compressed = 0;
    }
    return;
  }

  if (msg.isRequest()) {
    DCHECK(transportDirection_ == TransportDirection::UPSTREAM ||
           assocStream || exAttributes);
    if (msg.isEgressWebsocketUpgrade()) {
      upgradedStreams_.insert(stream);
    }
  } else {
    DCHECK(transportDirection_ == TransportDirection::DOWNSTREAM ||
           exAttributes);
  }

  std::vector<std::string> temps;
  auto allHeaders = CodecUtil::prepareMessageForCompression(msg, temps);
  auto out = encodeHeaders(msg.getHeaders(), allHeaders, size);
  IOBufQueue queue(IOBufQueue::cacheChainLength());
  queue.append(std::move(out));
  auto maxFrameSize = maxSendFrameSize();
  if (queue.chainLength() > 0) {
    folly::Optional<http2::PriorityUpdate> pri;
    auto res = msg.getHTTP2Priority();
    auto remainingFrameSize = maxFrameSize;
    if (res) {
      pri = http2::PriorityUpdate{std::get<0>(*res), std::get<1>(*res),
                                  std::get<2>(*res)};
      DCHECK_GE(remainingFrameSize, http2::kFramePrioritySize)
        << "no enough space for priority? frameHeadroom=" << remainingFrameSize;
      remainingFrameSize -= http2::kFramePrioritySize;
    }
    auto chunk = queue.split(std::min(remainingFrameSize, queue.chainLength()));

    bool endHeaders = queue.chainLength() == 0;

    if (assocStream) {
      DCHECK_EQ(transportDirection_, TransportDirection::DOWNSTREAM);
      DCHECK(!eom);
      generateHeaderCallbackWrapper(stream, http2::FrameType::PUSH_PROMISE,
                                    http2::writePushPromise(writeBuf,
                                                            *assocStream,
                                                            stream,
                                                            std::move(chunk),
                                                            http2::kNoPadding,
                                                            endHeaders));
    } else if (exAttributes) {
      generateHeaderCallbackWrapper(
        stream,
        http2::FrameType::EX_HEADERS,
        http2::writeExHeaders(writeBuf,
                              std::move(chunk),
                              stream,
                              *exAttributes,
                              pri,
                              http2::kNoPadding,
                              eom,
                              endHeaders));
    } else {
      generateHeaderCallbackWrapper(stream, http2::FrameType::HEADERS,
                                    http2::writeHeaders(writeBuf,
                                                        std::move(chunk),
                                                        stream,
                                                        pri,
                                                        http2::kNoPadding,
                                                        eom,
                                                        endHeaders));
    }

    if (!endHeaders) {
      generateContinuation(writeBuf, queue, stream, maxFrameSize);
    }
  }
}