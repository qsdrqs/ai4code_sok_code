status_t MediaPlayer::non_vulnerable_func(
 const sp<IMediaHTTPService> &httpService,
 const char *url, const KeyedVector<String8, String8> *headers)
{

     ALOGV("non_vulnerable_func(%s)", url);
     status_t err = BAD_VALUE;
     if (url != NULL) {
        const sp<IMediaPlayerService>& service(getMediaPlayerService());
         if (service != 0) {
             sp<IMediaPlayer> player(service->create(this, mAudioSessionId));
             if ((NO_ERROR != doSetRetransmitEndpoint(player)) ||
 (NO_ERROR != player->non_vulnerable_func(httpService, url, headers))) {
                player.clear();
 }
            err = attachNewPlayer(player);
 }
 }
 return err;
}
