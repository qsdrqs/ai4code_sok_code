void update_rate_histogram(struct rate_hist *buffer1,
 const vpx_codec_enc_cfg_t *buffer2,
 const vpx_codec_cx_pkt_t *buffer3) {
 int i;
 int64_t then = 0;
 int64_t avg_bitrate = 0;
 int64_t sum_sz = 0;
 const int64_t now = pkt->data.frame.pts * 1000 *
 (uint64_t)cfg->g_timebase.num /
 (uint64_t)cfg->g_timebase.den;

 int idx = hist->frames++ % hist->samples;
  buffer1->pts[idx] = now;
  buffer1->sz[idx] = (int)buffer3->data.frame.sz;


   if (now < buffer2->rc_buf_initial_sz)
     return;
 
   then = now;
 
   /* Sum the size over the past rc_buf_sz ms */
 for (i = buffer1->frames; i > 0 && buffer1->frames - i < buffer1->samples; i--) {
 const int i_idx = (i - 1) % hist->samples;

    then = buffer1->pts[i_idx];
 if (now - then > buffer2->rc_buf_sz)
 break;
    sum_sz += buffer1->sz[i_idx];
 }

 if (now == then)
 return;

  avg_bitrate = sum_sz * 8 * 1000 / (now - then);
  idx = (int)(avg_bitrate * (RATE_BINS / 2) / (buffer2->rc_target_bitrate * 1000));
 if (idx < 0)
    idx = 0;
 if (idx > RATE_BINS - 1)
    idx = RATE_BINS - 1;
 if (buffer1->bucket[idx].low > avg_bitrate)
    buffer1->bucket[idx].low = (int)avg_bitrate;
 if (buffer1->bucket[idx].high < avg_bitrate)
    buffer1->bucket[idx].high = (int)avg_bitrate;
  buffer1->bucket[idx].count++;
  buffer1->total++;
}
