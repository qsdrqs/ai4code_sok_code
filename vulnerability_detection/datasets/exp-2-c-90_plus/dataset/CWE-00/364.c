static int rotateImage(uint16_t rotation, struct image_data *image,
                       uint32_t *img_width, uint32_t *img_length,
                       unsigned char **ibuff_ptr)
{
    int shift_width;
    uint32_t bytes_per_pixel, bytes_per_sample;
    uint32_t row, rowsize, src_offset, dst_offset;
    uint32_t i, col, width, length;
    uint32_t colsize, buffsize, col_offset, pix_offset;
    unsigned char *ibuff;
    unsigned char *src;
    unsigned char *dst;
    uint16_t spp, bps;
    float res_temp;
    unsigned char *rbuff = NULL;

    width = *img_width;
    length = *img_length;
    spp = image->spp;
    bps = image->bps;

    rowsize = ((bps * spp * width) + 7) / 8;
    colsize = ((bps * spp * length) + 7) / 8;
    if ((colsize * width) > (rowsize * length))
        buffsize = (colsize + 1) * width;
    else
        buffsize = (rowsize + 1) * length;

    bytes_per_sample = (bps + 7) / 8;
    bytes_per_pixel = ((bps * spp) + 7) / 8;
    if (bytes_per_pixel < (bytes_per_sample + 1))
        shift_width = bytes_per_pixel;
    else
        shift_width = bytes_per_sample + 1;

    switch (rotation)
    {
        case 0:
        case 360:
            return (0);
        case 90:
        case 180:
        case 270:
            break;
        default:
            TIFFError("rotateImage", "Invalid rotation angle %" PRIu16,
                      rotation);
            return (-1);
    }

    /* Add 3 padding bytes for extractContigSamplesShifted32bits */
    if (!(rbuff =
              (unsigned char *)limitMalloc(buffsize + NUM_BUFF_OVERSIZE_BYTES)))
    {
        TIFFError("rotateImage",
                  "Unable to allocate rotation buffer of %1u bytes",
                  buffsize + NUM_BUFF_OVERSIZE_BYTES);
        return (-1);
    }
    _TIFFmemset(rbuff, '\0', buffsize + NUM_BUFF_OVERSIZE_BYTES);

    ibuff = *ibuff_ptr;
    switch (rotation)
    {
        case 180:
            if ((bps % 8) == 0) /* byte aligned data */
            {
                src = ibuff;
                pix_offset = (spp * bps) / 8;
                for (row = 0; row < length; row++)
                {
                    dst_offset = (length - row - 1) * rowsize;
                    for (col = 0; col < width; col++)
                    {
                        col_offset = (width - col - 1) * pix_offset;
                        dst = rbuff + dst_offset + col_offset;

                        for (i = 0; i < bytes_per_pixel; i++)
                            *dst++ = *src++;
                    }
                }
            }
            else
            { /* non 8 bit per sample data */
                for (row = 0; row < length; row++)
                {
                    src_offset = row * rowsize;
                    dst_offset = (length - row - 1) * rowsize;
                    src = ibuff + src_offset;
                    dst = rbuff + dst_offset;
                    switch (shift_width)
                    {
                        case 1:
                            if (bps == 1)
                            {
                                if (reverseSamples8bits(spp, bps, width, src,
                                                        dst))
                                {
                                    _TIFFfree(rbuff);
                                    return (-1);
                                }
                                break;
                            }
                            if (reverseSamples16bits(spp, bps, width, src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        case 2:
                            if (reverseSamples24bits(spp, bps, width, src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        case 3:
                        case 4:
                        case 5:
                            if (reverseSamples32bits(spp, bps, width, src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        default:
                            TIFFError("rotateImage",
                                      "Unsupported bit depth %" PRIu16, bps);
                            _TIFFfree(rbuff);
                            return (-1);
                    }
                }
            }
            _TIFFfree(ibuff);
            *(ibuff_ptr) = rbuff;
            break;

        case 90:
            if ((bps % 8) == 0) /* byte aligned data */
            {
                for (col = 0; col < width; col++)
                {
                    src_offset =
                        ((length - 1) * rowsize) + (col * bytes_per_pixel);
                    dst_offset = col * colsize;
                    src = ibuff + src_offset;
                    dst = rbuff + dst_offset;
                    for (row = length; row > 0; row--)
                    {
                        for (i = 0; i < bytes_per_pixel; i++)
                            *dst++ = *(src + i);
                        src -= rowsize;
                    }
                }
            }
            else
            { /* non 8 bit per sample data */
                for (col = 0; col < width; col++)
                {
                    src_offset = (length - 1) * rowsize;
                    dst_offset = col * colsize;
                    src = ibuff + src_offset;
                    dst = rbuff + dst_offset;
                    switch (shift_width)
                    {
                        case 1:
                            if (bps == 1)
                            {
                                if (rotateContigSamples8bits(rotation, spp, bps,
                                                             width, length, col,
                                                             src, dst))
                                {
                                    _TIFFfree(rbuff);
                                    return (-1);
                                }
                                break;
                            }
                            if (rotateContigSamples16bits(rotation, spp, bps,
                                                          width, length, col,
                                                          src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        case 2:
                            if (rotateContigSamples24bits(rotation, spp, bps,
                                                          width, length, col,
                                                          src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        case 3:
                        case 4:
                        case 5:
                            if (rotateContigSamples32bits(rotation, spp, bps,
                                                          width, length, col,
                                                          src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        default:
                            TIFFError("rotateImage",
                                      "Unsupported bit depth %" PRIu16, bps);
                            _TIFFfree(rbuff);
                            return (-1);
                    }
                }
            }
            _TIFFfree(ibuff);
            *(ibuff_ptr) = rbuff;

            *img_width = length;
            *img_length = width;
            image->width = length;
            image->length = width;
            res_temp = image->xres;
            image->xres = image->yres;
            image->yres = res_temp;
            break;

        case 270:
            if ((bps % 8) == 0) /* byte aligned data */
            {
                for (col = 0; col < width; col++)
                {
                    src_offset = col * bytes_per_pixel;
                    dst_offset = (width - col - 1) * colsize;
                    src = ibuff + src_offset;
                    dst = rbuff + dst_offset;
                    for (row = length; row > 0; row--)
                    {
                        for (i = 0; i < bytes_per_pixel; i++)
                            *dst++ = *(src + i);
                        src += rowsize;
                    }
                }
            }
            else
            { /* non 8 bit per sample data */
                for (col = 0; col < width; col++)
                {
                    src_offset = 0;
                    dst_offset = (width - col - 1) * colsize;
                    src = ibuff + src_offset;
                    dst = rbuff + dst_offset;
                    switch (shift_width)
                    {
                        case 1:
                            if (bps == 1)
                            {
                                if (rotateContigSamples8bits(rotation, spp, bps,
                                                             width, length, col,
                                                             src, dst))
                                {
                                    _TIFFfree(rbuff);
                                    return (-1);
                                }
                                break;
                            }
                            if (rotateContigSamples16bits(rotation, spp, bps,
                                                          width, length, col,
                                                          src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        case 2:
                            if (rotateContigSamples24bits(rotation, spp, bps,
                                                          width, length, col,
                                                          src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        case 3:
                        case 4:
                        case 5:
                            if (rotateContigSamples32bits(rotation, spp, bps,
                                                          width, length, col,
                                                          src, dst))
                            {
                                _TIFFfree(rbuff);
                                return (-1);
                            }
                            break;
                        default:
                            TIFFError("rotateImage",
                                      "Unsupported bit depth %" PRIu16, bps);
                            _TIFFfree(rbuff);
                            return (-1);
                    }
                }
            }
            _TIFFfree(ibuff);
            *(ibuff_ptr) = rbuff;

            *img_width = length;
            *img_length = width;
            image->width = length;
            image->length = width;
            res_temp = image->xres;
            image->xres = image->yres;
            image->yres = res_temp;
            break;
        default:
            break;
    }

    return (0);
}