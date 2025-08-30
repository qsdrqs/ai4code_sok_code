static int mxf_seek_to_previous_partition(MXFContext *mxf)
{
    AVIOContext *pb = mxf->fc->pb;
    KLVPacket klv;
    int64_t current_partition_ofs;
    int ret;

    if (!mxf->current_partition ||
        mxf->run_in + mxf->current_partition->previous_partition <= mxf->last_forward_tell)
        return 0;   /* we've parsed all partitions */

    /* seek to previous partition */
    current_partition_ofs = mxf->current_partition->pack_ofs;   //includes run-in
    avio_seek(pb, mxf->run_in + mxf->current_partition->previous_partition, SEEK_SET);
    mxf->current_partition = NULL;

    av_log(mxf->fc, AV_LOG_TRACE, "seeking to previous partition\n");

    /* Make sure this is actually a PartitionPack, and if so parse it.
     * See deadlock2.mxf
     */
    if ((ret = klv_read_packet(&klv, pb)) < 0) {
        av_log(mxf->fc, AV_LOG_ERROR, "failed to read PartitionPack KLV\n");
        return ret;
    }

    if (!mxf_is_partition_pack_key(klv.key)) {
        av_log(mxf->fc, AV_LOG_ERROR, "PreviousPartition @ %" PRIx64 " isn't a PartitionPack\n", klv.offset);
        return AVERROR_INVALIDDATA;
    }

    /* We can't just check ofs >= current_partition_ofs because PreviousPartition
     * can point to just before the current partition, causing klv_read_packet()
     * to sync back up to it. See deadlock3.mxf
     */
    if (klv.offset >= current_partition_ofs) {
        av_log(mxf->fc, AV_LOG_ERROR, "PreviousPartition for PartitionPack @ %"
               PRIx64 " indirectly points to itself\n", current_partition_ofs);
        return AVERROR_INVALIDDATA;
    }

    if ((ret = mxf_parse_klv(mxf, klv, mxf_read_partition_pack, 0, 0)) < 0)
        return ret;

    return 1;
}
