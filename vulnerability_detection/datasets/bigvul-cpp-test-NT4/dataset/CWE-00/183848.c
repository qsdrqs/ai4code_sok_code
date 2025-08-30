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

void ExtensionTtsController::Stop() {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (current_utterance_ && !current_utterance_->extension_id().empty()) {
    current_utterance_->profile()->GetExtensionEventRouter()->
        DispatchEventToExtension(
            current_utterance_->extension_id(),
            events::kOnStop,
            "[]",
            current_utterance_->profile(),
            GURL());
  } else {
    GetPlatformImpl()->clear_error();
    GetPlatformImpl()->StopSpeaking();
   }
 
  if (current_utterance_)
    current_utterance_->set_error(kSpeechInterruptedError);
  FinishCurrentUtterance();
  ClearUtteranceQueue();
}
