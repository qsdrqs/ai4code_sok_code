void impeg2d_dec_pic_data_thread(dec_state_t *buffer1)
{
    WORD32 i4_continue_decode;

    WORD32 i4_cur_row, temp;
    UWORD32 u4_bits_read;
    WORD32 i4_dequeue_job;
    IMPEG2D_ERROR_CODES_T e_error;

    i4_cur_row = buffer1->u2_mb_y + 1;

    i4_continue_decode = 1;

    i4_dequeue_job = 1;
 do
 {
 if(i4_cur_row > buffer1->u2_num_vert_mb)
 {
            i4_continue_decode = 0;
 break;
 }

 {
 if((buffer1->i4_num_cores> 1) && (i4_dequeue_job))
 {
 job_t s_job;
                IV_API_CALL_STATUS_T e_ret;
                UWORD8 *buffer2;

                e_ret = impeg2_jobq_dequeue(buffer1->pv_jobq, &s_job, sizeof(s_job), 1, 1);
 if(e_ret != IV_SUCCESS)
 break;

 if(CMD_PROCESS == s_job.i4_cmd)
 {
                    buffer2 = buffer1->pu1_inp_bits_buf + s_job.i4_bistream_ofst;
                    impeg2d_bit_stream_init(&(buffer1->s_bit_stream), buffer2,
 (buffer1->u4_num_inp_bytes - s_job.i4_bistream_ofst) + 8);
                    i4_cur_row      = s_job.i2_start_mb_y;
                    buffer1->i4_start_mb_y = s_job.i2_start_mb_y;
                    buffer1->i4_end_mb_y = s_job.i2_end_mb_y;
                    buffer1->u2_mb_x = 0;
                    buffer1->u2_mb_y = buffer1->i4_start_mb_y;
                    buffer1->u2_num_mbs_left = (buffer1->i4_end_mb_y - buffer1->i4_start_mb_y) * buffer1->u2_num_horiz_mb;

 }
 else
 {
                    WORD32 start_row;
                    WORD32 num_rows;
                    start_row = s_job.i2_start_mb_y << 4;
                    num_rows = MIN((s_job.i2_end_mb_y << 4), buffer1->u2_vertical_size);
                    num_rows -= start_row;
                    impeg2d_format_convert(buffer1, buffer1->ps_disp_pic,
                                        buffer1->ps_disp_frm_buf,
                                        start_row, num_rows);
 break;

 }

 }
            e_error = impeg2d_dec_slice(buffer1);

 if ((IMPEG2D_ERROR_CODES_T)IVD_ERROR_NONE != e_error)
 {
                impeg2d_next_start_code(buffer1);
 }
 }

 /* Detecting next slice start code */
 while(1)
 {
            u4_bits_read = impeg2d_bit_stream_nxt(&buffer1->s_bit_stream,START_CODE_LEN);

             temp = u4_bits_read & 0xFF;
             i4_continue_decode = (((u4_bits_read >> 8) == 0x01) && (temp) && (temp <= 0xAF));
 
             if(i4_continue_decode)
             {
                 /* If the slice is from the same row, then continue decoding without dequeue */
 if((temp - 1) == i4_cur_row)
 {
                    i4_dequeue_job = 0;
 break;
 }

 if(temp < buffer1->i4_end_mb_y)
 {
                    i4_cur_row = buffer1->u2_mb_y;
 }
 else
 {
                    i4_dequeue_job = 1;
 }
 break;

 }
 else
 break;
 }

 }while(i4_continue_decode);
 if(buffer1->i4_num_cores > 1)
 {
 while(1)
 {
 job_t s_job;
            IV_API_CALL_STATUS_T e_ret;

            e_ret = impeg2_jobq_dequeue(buffer1->pv_jobq, &s_job, sizeof(s_job), 1, 1);
 if(e_ret != IV_SUCCESS)
 break;
 if(CMD_FMTCONV == s_job.i4_cmd)
 {
                WORD32 start_row;
                WORD32 num_rows;
                start_row = s_job.i2_start_mb_y << 4;
                num_rows = MIN((s_job.i2_end_mb_y << 4), buffer1->u2_vertical_size);
                num_rows -= start_row;
                impeg2d_format_convert(buffer1, buffer1->ps_disp_pic,
                                    buffer1->ps_disp_frm_buf,
                                    start_row, num_rows);
 }
 }
 }
 else
 {
 if((NULL != buffer1->ps_disp_pic) && ((0 == buffer1->u4_share_disp_buf) || (IV_YUV_420P != buffer1->i4_chromaFormat)))
            impeg2d_format_convert(buffer1, buffer1->ps_disp_pic,
                            buffer1->ps_disp_frm_buf,
 0, buffer1->u2_vertical_size);
 }
}
