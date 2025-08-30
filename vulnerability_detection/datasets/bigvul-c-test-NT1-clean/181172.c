static int dnxhd_decode_header(DNXHDContext *buffer1, AVFrame *buffer2,
                               const uint8_t *buffer3, int buf_size,
                               int first_field)
{
    int i, cid, ret;
    int old_bit_depth = ctx->bit_depth, bitdepth;
    uint64_t header_prefix;
    if (buf_size < 0x280) {
        av_log(buffer1->avctx, AV_LOG_ERROR,
               "buffer too small (%d < 640).\n", buf_size);
        return AVERROR_INVALIDDATA;
    }

    header_prefix = ff_dnxhd_parse_header_prefix(buffer3);
    if (header_prefix == 0) {
        av_log(buffer1->avctx, AV_LOG_ERROR,
               "unknown header 0x%02X 0x%02X 0x%02X 0x%02X 0x%02X\n",
               buffer3[0], buffer3[1], buffer3[2], buffer3[3], buffer3[4]);
        return AVERROR_INVALIDDATA;
    }
    if (buffer3[5] & 2) { /* interlaced */
        buffer1->cur_field = buffer3[5] & 1;
        buffer2->interlaced_frame = 1;
        buffer2->top_field_first  = first_field ^ buffer1->cur_field;
        av_log(buffer1->avctx, AV_LOG_DEBUG,
               "interlaced %d, cur field %d\n", buffer3[5] & 3, buffer1->cur_field);
    } else {
        buffer1->cur_field = 0;
    }
    buffer1->mbaff = (buffer3[0x6] >> 5) & 1;

    buffer1->height = AV_RB16(buffer3 + 0x18);
    buffer1->width  = AV_RB16(buffer3 + 0x1a);

    switch(buffer3[0x21] >> 5) {
    case 1: bitdepth = 8; break;
    case 2: bitdepth = 10; break;
    case 3: bitdepth = 12; break;
    default:
        av_log(buffer1->avctx, AV_LOG_ERROR,
               "Unknown bitdepth indicator (%d)\n", buffer3[0x21] >> 5);
        return AVERROR_INVALIDDATA;
    }

    cid = AV_RB32(buffer3 + 0x28);

    buffer1->avctx->profile = dnxhd_get_profile(cid);

    if ((ret = dnxhd_init_vlc(buffer1, cid, bitdepth)) < 0)
        return ret;
    if (buffer1->mbaff && buffer1->cid_table->cid != 1260)
        av_log(buffer1->avctx, AV_LOG_WARNING,
               "Adaptive MB interlace flag in an unsupported profile.\n");

    buffer1->act = buffer3[0x2C] & 7;
    if (buffer1->act && buffer1->cid_table->cid != 1256 && buffer1->cid_table->cid != 1270)
        av_log(buffer1->avctx, AV_LOG_WARNING,
               "Adaptive color transform in an unsupported profile.\n");

    buffer1->is_444 = (buffer3[0x2C] >> 6) & 1;
    if (buffer1->is_444) {
        if (bitdepth == 8) {
            avpriv_request_sample(buffer1->avctx, "4:4:4 8 bits");
            return AVERROR_INVALIDDATA;
        } else if (bitdepth == 10) {
            buffer1->decode_dct_block = dnxhd_decode_dct_block_10_444;
            buffer1->pix_fmt = buffer1->act ? AV_PIX_FMT_YUV444P10
                                    : AV_PIX_FMT_GBRP10;
        } else {
            buffer1->decode_dct_block = dnxhd_decode_dct_block_12_444;
            buffer1->pix_fmt = buffer1->act ? AV_PIX_FMT_YUV444P12
                                    : AV_PIX_FMT_GBRP12;
        }
    } else if (bitdepth == 12) {
        buffer1->decode_dct_block = dnxhd_decode_dct_block_12;
        buffer1->pix_fmt = AV_PIX_FMT_YUV422P12;
    } else if (bitdepth == 10) {
        if (buffer1->avctx->profile == FF_PROFILE_DNXHR_HQX)
            buffer1->decode_dct_block = dnxhd_decode_dct_block_10_444;
        else
            buffer1->decode_dct_block = dnxhd_decode_dct_block_10;
        buffer1->pix_fmt = AV_PIX_FMT_YUV422P10;
    } else {
        buffer1->decode_dct_block = dnxhd_decode_dct_block_8;
        buffer1->pix_fmt = AV_PIX_FMT_YUV422P;
    }

    buffer1->avctx->bits_per_raw_sample = buffer1->bit_depth = bitdepth;
    if (buffer1->bit_depth != old_bit_depth) {
        ff_blockdsp_init(&buffer1->bdsp, buffer1->avctx);
        ff_idctdsp_init(&buffer1->idsp, buffer1->avctx);
        ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->scantable,
                          ff_zigzag_direct);
    }

    if (buffer1->width != buffer1->cid_table->width &&
        buffer1->cid_table->width != DNXHD_VARIABLE) {
        av_reduce(&buffer1->avctx->sample_aspect_ratio.num,
                  &buffer1->avctx->sample_aspect_ratio.den,
                  buffer1->width, buffer1->cid_table->width, 255);
        buffer1->width = buffer1->cid_table->width;
    }

    if (buf_size < buffer1->cid_table->coding_unit_size) {
        av_log(buffer1->avctx, AV_LOG_ERROR, "incorrect frame size (%d < %u).\n",
               buf_size, buffer1->cid_table->coding_unit_size);
        return AVERROR_INVALIDDATA;
    }

    buffer1->mb_width  = (buffer1->width + 15)>> 4;
    buffer1->mb_height = AV_RB16(buffer3 + 0x16c);

    if ((buffer1->height + 15) >> 4 == buffer1->mb_height && buffer2->interlaced_frame)
        buffer1->height <<= 1;

    av_log(buffer1->avctx, AV_LOG_VERBOSE, "%dx%d, 4:%s %d bits, MBAFF=%d ACT=%d\n",
           buffer1->width, buffer1->height, buffer1->is_444 ? "4:4" : "2:2",
           buffer1->bit_depth, buffer1->mbaff, buffer1->act);

     if (buffer1->mb_height > 68 && ff_dnxhd_check_header_prefix_hr(header_prefix)) {
         buffer1->data_offset = 0x170 + (buffer1->mb_height << 2);
     } else {
        if (buffer1->mb_height > 68 ||
            (buffer1->mb_height << buffer2->interlaced_frame) > (buffer1->height + 15) >> 4) {
             av_log(buffer1->avctx, AV_LOG_ERROR,
                    "mb height too big: %d\n", buffer1->mb_height);
             return AVERROR_INVALIDDATA;
         }
         buffer1->data_offset = 0x280;
     }
 
     if (buf_size < buffer1->data_offset) {
         av_log(buffer1->avctx, AV_LOG_ERROR,
               "buffer too small (%d < %d).\n", buf_size, buffer1->data_offset);
        return AVERROR_INVALIDDATA;
    }

    if (buffer1->mb_height > FF_ARRAY_ELEMS(buffer1->mb_scan_index)) {
        av_log(buffer1->avctx, AV_LOG_ERROR,
               "mb_height too big (%d > %"SIZE_SPECIFIER").\n", buffer1->mb_height, FF_ARRAY_ELEMS(buffer1->mb_scan_index));
        return AVERROR_INVALIDDATA;
    }

    for (i = 0; i < buffer1->mb_height; i++) {
        buffer1->mb_scan_index[i] = AV_RB32(buffer3 + 0x170 + (i << 2));
        ff_dlog(buffer1->avctx, "mb scan index %d, pos %d: %"PRIu32"\n",
                i, 0x170 + (i << 2), buffer1->mb_scan_index[i]);
        if (buf_size - buffer1->data_offset < buffer1->mb_scan_index[i]) {
            av_log(buffer1->avctx, AV_LOG_ERROR,
                   "invalid mb scan index (%"PRIu32" vs %u).\n",
                   buffer1->mb_scan_index[i], buf_size - buffer1->data_offset);
            return AVERROR_INVALIDDATA;
        }
    }

    return 0;
}
