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

status_t MediaPlayer::setDataSource(
 const sp<IMediaHTTPService> &httpService,
 const char *url, const KeyedVector<String8, String8> *headers)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);


     ALOGV("setDataSource(%s)", url);
     status_t err = BAD_VALUE;
     if (url != NULL) {
        const sp<IMediaPlayerService>& service(getMediaPlayerService());
         if (service != 0) {
             sp<IMediaPlayer> player(service->create(this, mAudioSessionId));
             if ((NO_ERROR != doSetRetransmitEndpoint(player)) ||
 (NO_ERROR != player->setDataSource(httpService, url, headers))) {
                player.clear();
 }
            err = attachNewPlayer(player);
 }
 }
 return err;
}
