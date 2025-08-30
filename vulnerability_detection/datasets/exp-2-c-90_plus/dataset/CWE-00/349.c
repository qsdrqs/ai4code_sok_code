static bool ok_jpg_decode_scan(ok_jpg_decoder *decoder) {
    decoder->next_restart = 0;
    ok_jpg_decode_restart(decoder);
    if (decoder->restart_intervals_remaining > 0) {
        // Increment because the restart is checked before each data unit instead of after.
        decoder->restart_intervals_remaining++;
    }
    if (decoder->progressive) {
        void (*decode_function)(ok_jpg_decoder *decoder, ok_jpg_component *c, int16_t *block);
        if (decoder->scan_prev_scale > 0) {
            decode_function = ok_jpg_decode_block_subsequent_scan;
        } else {
            decode_function = ok_jpg_decode_block_progressive;
        }
        if (decoder->num_scan_components == 1) {
            ok_jpg_component *c = decoder->components + decoder->scan_components[0];
            c->next_block = 0;
            for (int data_unit_y = 0; data_unit_y < c->blocks_v; data_unit_y++) {
                int16_t *block = c->blocks + (c->next_block * 64);
                for (int data_unit_x = 0; data_unit_x < c->blocks_h; data_unit_x++) {
                    ok_jpg_decode_restart_if_needed(decoder);
                    decode_function(decoder, c, block);
                    block += 64;
                }
                if (decoder->eof_found || decoder->huffman_error) {
                    return false;
                }
                c->next_block += (size_t)(c->H * decoder->data_units_x);
            }
        } else {
            for (int i = 0; i < decoder->num_scan_components; i++) {
                ok_jpg_component *c = decoder->components + decoder->scan_components[i];
                c->next_block = 0;
            }
            for (int data_unit_y = 0; data_unit_y < decoder->data_units_y; data_unit_y++) {
                for (int data_unit_x = 0; data_unit_x < decoder->data_units_x; data_unit_x++) {
                    ok_jpg_decode_restart_if_needed(decoder);
                    for (int i = 0; i < decoder->num_scan_components; i++) {
                        ok_jpg_component *c = decoder->components + decoder->scan_components[i];
                        size_t block_index = c->next_block;
                        for (int y = 0; y < c->V; y++) {
                            for (int x = 0; x < c->H; x++) {
                                decode_function(decoder, c, c->blocks + (block_index * 64));
                                block_index++;
                            }
                            block_index += (size_t)(c->H * (decoder->data_units_x - 1));
                        }
                        c->next_block += c->H;
                    }
                }
                if (decoder->eof_found || decoder->huffman_error) {
                    return false;
                }
                for (int i = 0; i < decoder->num_scan_components; i++) {
                    ok_jpg_component *c = decoder->components + decoder->scan_components[i];
                    c->next_block += (size_t)((c->V - 1) * c->H * decoder->data_units_x);
                }
            }
        }
    } else {
        int16_t block[64];
        for (int data_unit_y = 0; data_unit_y < decoder->data_units_y; data_unit_y++) {
            for (int data_unit_x = 0; data_unit_x < decoder->data_units_x; data_unit_x++) {
                ok_jpg_decode_restart_if_needed(decoder);
                for (int i = 0; i < decoder->num_scan_components; i++) {
                    ok_jpg_component *c = decoder->components + decoder->scan_components[i];
                    int offset_y = 0;
                    for (int y = 0; y < c->V; y++) {
                        int offset_x = 0;
                        for (int x = 0; x < c->H; x++) {
                            ok_jpg_decode_block(decoder, c, block);
                            c->idct(block, c->output + offset_x + offset_y);
                            offset_x += 8;
                        }
                        offset_y += C_WIDTH * 8;
                    }
                }
                if (decoder->huffman_error) {
                    return false;
                }
                ok_jpg_convert_data_unit(decoder, data_unit_x, data_unit_y);
            }
            if (decoder->eof_found) {
                return false;
            }
        }
    }

    ok_jpg_dump_bits(decoder);

    for (int i = 0; i < decoder->num_scan_components; i++) {
        ok_jpg_component *c = decoder->components + decoder->scan_components[i];
        if (!c->complete) {
            c->complete = decoder->scan_end == 63 && decoder->scan_scale == 0;
        }
    }

    return true;
}