void InspectorNetworkAgent::DidReceiveWebSocketFrameError(
    unsigned long identifier,
    const String& error_message) {
  GetFrontend()->webSocketFrameError(IdentifiersFactory::RequestId(identifier),
                                     MonotonicallyIncreasingTime(),
                                     error_message);
}
