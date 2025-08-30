status_t MediaPlayer::setDataSource(
 const sp<IMediaHTTPService> &httpService,
 const char *buffer1, const KeyedVector<String8, String8> *buffer2)
{

     ALOGV("setDataSource(%s)", buffer1);
     status_t err = BAD_VALUE;
     if (buffer1 != NULL) {
        const sp<IMediaPlayerService>& service(getMediaPlayerService());
         if (service != 0) {
             sp<IMediaPlayer> player(service->create(this, mAudioSessionId));
             if ((NO_ERROR != doSetRetransmitEndpoint(player)) ||
 (NO_ERROR != player->setDataSource(httpService, buffer1, buffer2))) {
                player.clear();
 }
            err = attachNewPlayer(player);
 }
 }
 return err;
}
