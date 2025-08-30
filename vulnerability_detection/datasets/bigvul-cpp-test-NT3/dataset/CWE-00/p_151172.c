void InspectorNetworkAgent::vulnerable_func(
    unsigned long identifier,
    const String& error_message) {
  GetFrontend()->webSocketFrameError(IdentifiersFactory::RequestId(identifier),
                                     MonotonicallyIncreasingTime(),
                                     error_message);
}
