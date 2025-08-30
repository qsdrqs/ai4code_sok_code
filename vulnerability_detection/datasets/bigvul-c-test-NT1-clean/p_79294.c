static int get_samples_per_packet(MOVTrack *buffer1)
{
    int i, first_duration;


    /* use 1 for raw PCM */
    if (!buffer1->audio_vbr)
        return 1;

    /* check to see if duration is constant for all clusters */
    if (!buffer1->entry)
        return 0;
    first_duration = get_cluster_duration(buffer1, 0);
    for (i = 1; i < buffer1->entry; i++) {
        if (get_cluster_duration(buffer1, i) != first_duration)
            return 0;
    }
    return first_duration;
}
