bool ConfigureWebsocketOverHttp2(
    const base::CommandLine& command_line,
    const VariationParameters& http2_trial_params) {
  if (command_line.HasSwitch(switches::kEnableWebsocketOverHttp2))
    return true;

  const std::string websocket_value =
      GetVariationParam(http2_trial_params, "websocket_over_http2");
  return websocket_value == "true";
}
