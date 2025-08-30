static void enable_tracks(AVFormatContext *buffer1)
{
    MOVMuxContext *buffer2 = s->priv_data;
    int i;
    int buffer3[AVMEDIA_TYPE_NB];
    int buffer4[AVMEDIA_TYPE_NB];

    for (i = 0; i < AVMEDIA_TYPE_NB; i++) {
        buffer3[i] = 0;
        buffer4[i] = -1;
    }

    for (i = 0; i < buffer1->nb_streams; i++) {
        AVStream *buffer5 = s->streams[i];

        if (buffer5->codecpar->codec_type <= AVMEDIA_TYPE_UNKNOWN ||
            buffer5->codecpar->codec_type >= AVMEDIA_TYPE_NB ||
            is_cover_image(buffer5))
            continue;

        if (buffer4[buffer5->codecpar->codec_type] < 0)
            buffer4[buffer5->codecpar->codec_type] = i;
        if (buffer5->disposition & AV_DISPOSITION_DEFAULT) {
            buffer2->tracks[i].flags |= MOV_TRACK_ENABLED;
            buffer3[buffer5->codecpar->codec_type]++;
        }
    }

    for (i = 0; i < AVMEDIA_TYPE_NB; i++) {
        switch (i) {
        case AVMEDIA_TYPE_VIDEO:
        case AVMEDIA_TYPE_AUDIO:
        case AVMEDIA_TYPE_SUBTITLE:
            if (buffer3[i] > 1)
                buffer2->per_stream_grouping = 1;
            if (!buffer3[i] && buffer4[i] >= 0)
                buffer2->tracks[buffer4[i]].flags |= MOV_TRACK_ENABLED;
            break;
        }
    }
}
