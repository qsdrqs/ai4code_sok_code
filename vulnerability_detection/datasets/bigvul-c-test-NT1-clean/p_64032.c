static int decode_entropy_coded_image(WebPContext *buffer1, enum ImageRole role,
                                      int w, int h)
{
    ImageContext *buffer2;
    HuffReader *buffer3;
    int i, j, ret, x, y, width;

    buffer2       = &buffer1->image[role];
    buffer2->role = role;

    if (!buffer2->frame) {
        buffer2->frame = av_frame_alloc();
        if (!buffer2->frame)
            return AVERROR(ENOMEM);
    }

    buffer2->frame->format = AV_PIX_FMT_ARGB;
    buffer2->frame->width  = w;
    buffer2->frame->height = h;

    if (role == IMAGE_ROLE_ARGB && !buffer2->is_alpha_primary) {
        ThreadFrame pt = { .f = img->frame };
        ret = ff_thread_get_buffer(buffer1->avctx, &pt, 0);
    } else
        ret = av_frame_get_buffer(buffer2->frame, 1);
    if (ret < 0)
        return ret;

    if (get_bits1(&buffer1->gb)) {
        buffer2->color_cache_bits = get_bits(&buffer1->gb, 4);
        if (buffer2->color_cache_bits < 1 || buffer2->color_cache_bits > 11) {
            av_log(buffer1->avctx, AV_LOG_ERROR, "invalid color cache bits: %d\n",
                   buffer2->color_cache_bits);
            return AVERROR_INVALIDDATA;
        }
        buffer2->color_cache = av_mallocz_array(1 << buffer2->color_cache_bits,
                                            sizeof(*buffer2->color_cache));
        if (!buffer2->color_cache)
            return AVERROR(ENOMEM);
    } else {
        buffer2->color_cache_bits = 0;
    }

    buffer2->nb_huffman_groups = 1;
    if (role == IMAGE_ROLE_ARGB && get_bits1(&buffer1->gb)) {
        ret = decode_entropy_image(buffer1);
        if (ret < 0)
            return ret;
        buffer2->nb_huffman_groups = buffer1->nb_huffman_groups;
    }
    buffer2->huffman_groups = av_mallocz_array(buffer2->nb_huffman_groups *
                                           HUFFMAN_CODES_PER_META_CODE,
                                           sizeof(*buffer2->huffman_groups));
    if (!buffer2->huffman_groups)
        return AVERROR(ENOMEM);

    for (i = 0; i < buffer2->nb_huffman_groups; i++) {
        buffer3 = &buffer2->huffman_groups[i * HUFFMAN_CODES_PER_META_CODE];
        for (j = 0; j < HUFFMAN_CODES_PER_META_CODE; j++) {
            int alphabet_size = alphabet_sizes[j];
            if (!j && buffer2->color_cache_bits > 0)
                alphabet_size += 1 << buffer2->color_cache_bits;

            if (get_bits1(&buffer1->gb)) {
                read_huffman_code_simple(buffer1, &buffer3[j]);
            } else {
                ret = read_huffman_code_normal(buffer1, &buffer3[j], alphabet_size);
                if (ret < 0)
                    return ret;
            }
        }
    }

    width = buffer2->frame->width;
    if (role == IMAGE_ROLE_ARGB && buffer1->reduced_width > 0)
        width = buffer1->reduced_width;

    x = 0; y = 0;
    while (y < buffer2->frame->height) {
        int v;

        buffer3 = get_huffman_group(buffer1, buffer2, x, y);
        v = huff_reader_get_symbol(&buffer3[HUFF_IDX_GREEN], &buffer1->gb);
        if (v < NUM_LITERAL_CODES) {
            /* literal pixel values */
            uint8_t *buffer4 = GET_PIXEL(img->frame, x, y);
            buffer4[2] = v;
            buffer4[1] = huff_reader_get_symbol(&buffer3[HUFF_IDX_RED],   &buffer1->gb);
            buffer4[3] = huff_reader_get_symbol(&buffer3[HUFF_IDX_BLUE],  &buffer1->gb);
            buffer4[0] = huff_reader_get_symbol(&buffer3[HUFF_IDX_ALPHA], &buffer1->gb);
            if (buffer2->color_cache_bits)
                color_cache_put(buffer2, AV_RB32(buffer4));
            x++;
            if (x == width) {
                x = 0;
                y++;
            }
        } else if (v < NUM_LITERAL_CODES + NUM_LENGTH_CODES) {
            /* LZ77 backwards mapping */
            int prefix_code, length, distance, ref_x, ref_y;

            /* parse length and distance */
            prefix_code = v - NUM_LITERAL_CODES;
            if (prefix_code < 4) {
                length = prefix_code + 1;
            } else {
                int extra_bits = (prefix_code - 2) >> 1;
                int offset     = 2 + (prefix_code & 1) << extra_bits;
                length = offset + get_bits(&buffer1->gb, extra_bits) + 1;
            }
            prefix_code = huff_reader_get_symbol(&buffer3[HUFF_IDX_DIST], &buffer1->gb);
            if (prefix_code > 39) {
                av_log(buffer1->avctx, AV_LOG_ERROR,
                       "distance prefix code too large: %d\n", prefix_code);
                return AVERROR_INVALIDDATA;
            }
            if (prefix_code < 4) {
                distance = prefix_code + 1;
            } else {
                int extra_bits = prefix_code - 2 >> 1;
                int offset     = 2 + (prefix_code & 1) << extra_bits;
                distance = offset + get_bits(&buffer1->gb, extra_bits) + 1;
            }

            /* find reference location */
            if (distance <= NUM_SHORT_DISTANCES) {
                int xi = lz77_distance_offsets[distance - 1][0];
                int yi = lz77_distance_offsets[distance - 1][1];
                distance = FFMAX(1, xi + yi * width);
            } else {
                distance -= NUM_SHORT_DISTANCES;
            }
            ref_x = x;
            ref_y = y;
            if (distance <= x) {
                ref_x -= distance;
                distance = 0;
            } else {
                ref_x = 0;
                distance -= x;
            }
            while (distance >= width) {
                ref_y--;
                distance -= width;
            }
            if (distance > 0) {
                ref_x = width - distance;
                ref_y--;
            }
            ref_x = FFMAX(0, ref_x);
            ref_y = FFMAX(0, ref_y);

            /* copy pixels
             * source and dest regions can overlap and wrap lines, so just
             * copy per-pixel */
            for (i = 0; i < length; i++) {
                uint8_t *buffer5 = GET_PIXEL(img->frame, ref_x, ref_y);
                uint8_t *buffer6     = GET_PIXEL(img->frame,     x,     y);

                AV_COPY32(buffer6, buffer5);
                if (buffer2->color_cache_bits)
                    color_cache_put(buffer2, AV_RB32(buffer6));
                x++;
                ref_x++;
                if (x == width) {
                    x = 0;
                    y++;
                }
                if (ref_x == width) {
                    ref_x = 0;
                    ref_y++;
                }
                if (y == buffer2->frame->height || ref_y == buffer2->frame->height)
                    break;
            }
        } else {
            /* read from color cache */
            uint8_t *buffer7 = GET_PIXEL(img->frame, x, y);
            int cache_idx = v - (NUM_LITERAL_CODES + NUM_LENGTH_CODES);

            if (!buffer2->color_cache_bits) {
                av_log(buffer1->avctx, AV_LOG_ERROR, "color cache not found\n");
                return AVERROR_INVALIDDATA;
            }
            if (cache_idx >= 1 << buffer2->color_cache_bits) {
                av_log(buffer1->avctx, AV_LOG_ERROR,
                       "color cache index out-of-bounds\n");
                return AVERROR_INVALIDDATA;
            }
            AV_WB32(buffer7, buffer2->color_cache[cache_idx]);
            x++;
            if (x == width) {
                x = 0;
                y++;
            }
        }
    }

    return 0;
}
