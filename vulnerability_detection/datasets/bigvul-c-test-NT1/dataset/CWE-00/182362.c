 av_cold void ff_mpv_idct_init(MpegEncContext *buffer1)
 {
     ff_idctdsp_init(&buffer1->idsp, buffer1->avctx);
 
     /* load & permutate scantables
     * note: only wmv uses different ones
     */
    if (buffer1->alternate_scan) {
        ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->inter_scantable, ff_alternate_vertical_scan);
        ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->intra_scantable, ff_alternate_vertical_scan);
    } else {
        ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->inter_scantable, ff_zigzag_direct);
        ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->intra_scantable, ff_zigzag_direct);
    }
    ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->intra_h_scantable, ff_alternate_horizontal_scan);
    ff_init_scantable(buffer1->idsp.idct_permutation, &buffer1->intra_v_scantable, ff_alternate_vertical_scan);
}
