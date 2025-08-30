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

bool ConfigureWebsocketOverHttp2(
    const base::CommandLine& command_line,
    const VariationParameters& http2_trial_params) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (command_line.HasSwitch(switches::kEnableWebsocketOverHttp2))
    return true;

  const std::string websocket_value =
      GetVariationParam(http2_trial_params, "websocket_over_http2");
  return websocket_value == "true";
}
