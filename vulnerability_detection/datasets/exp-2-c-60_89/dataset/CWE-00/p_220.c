OPJ_BOOL opj_tcd_encode_tile(   opj_tcd_t *p_tcd,
                                                        OPJ_UINT32 p_tile_no,
                                                        OPJ_BYTE *p_dest,
                                                        OPJ_UINT32 * p_data_written,
                                                        OPJ_UINT32 p_max_length,
                                                        opj_codestream_info_t *p_cstr_info)
{

        if (p_tcd->cur_tp_num == 0) {

                p_tcd->tcd_tileno = p_tile_no;
                p_tcd->tcp = &p_tcd->cp->tcps[p_tile_no];

                /* INDEX >> "Precinct_nb_X et Precinct_nb_Y" */
                if(p_cstr_info)  {
                        OPJ_UINT32 l_num_packs = 0;
                        OPJ_UINT32 i;
                        opj_tcd_tilecomp_t *l_tilec_idx = &p_tcd->tcd_image->tiles->comps[0];        /* based on component 0 */
                        opj_tccp_t *l_tccp = p_tcd->tcp->tccps; /* based on component 0 */

                        for (i = 0; i < l_tilec_idx->numresolutions; i++) {
                                opj_tcd_resolution_t *l_res_idx = &l_tilec_idx->resolutions[i];

                                p_cstr_info->tile[p_tile_no].pw[i] = (int)l_res_idx->pw;
                                p_cstr_info->tile[p_tile_no].ph[i] = (int)l_res_idx->ph;

                                l_num_packs += l_res_idx->pw * l_res_idx->ph;
                                p_cstr_info->tile[p_tile_no].pdx[i] = (int)l_tccp->prcw[i];
                                p_cstr_info->tile[p_tile_no].pdy[i] = (int)l_tccp->prch[i];
                        }
                        p_cstr_info->tile[p_tile_no].packet = (opj_packet_info_t*) opj_calloc((size_t)p_cstr_info->numcomps * (size_t)p_cstr_info->numlayers * l_num_packs, sizeof(opj_packet_info_t));
                        if (!p_cstr_info->tile[p_tile_no].packet) {
                                /* FIXME event manager error callback */
                                return OPJ_FALSE;
                        }
                }
                /* << INDEX */

                /* FIXME _ProfStart(PGROUP_DC_SHIFT); */
                /*---------------TILE-------------------*/
                if (! opj_tcd_dc_level_shift_encode(p_tcd)) {
                        return OPJ_FALSE;
                }
                /* FIXME _ProfStop(PGROUP_DC_SHIFT); */

                /* FIXME _ProfStart(PGROUP_MCT); */
                if (! opj_tcd_mct_encode(p_tcd)) {
                        return OPJ_FALSE;
                }
                /* FIXME _ProfStop(PGROUP_MCT); */

                /* FIXME _ProfStart(PGROUP_DWT); */
                if (! opj_tcd_dwt_encode(p_tcd)) {
                        return OPJ_FALSE;
                }
                /* FIXME  _ProfStop(PGROUP_DWT); */

                /* FIXME  _ProfStart(PGROUP_T1); */
                if (! opj_tcd_t1_encode(p_tcd)) {
                        return OPJ_FALSE;
                }
                /* FIXME _ProfStop(PGROUP_T1); */

                /* FIXME _ProfStart(PGROUP_RATE); */
                if (! opj_tcd_rate_allocate_encode(p_tcd,p_dest,p_max_length,p_cstr_info)) {
                        return OPJ_FALSE;
                }
                /* FIXME _ProfStop(PGROUP_RATE); */

        }
        /*--------------TIER2------------------*/

        /* INDEX */
        if (p_cstr_info) {
                p_cstr_info->index_write = 1;
        }
        /* FIXME _ProfStart(PGROUP_T2); */

        if (! opj_tcd_t2_encode(p_tcd,p_dest,p_data_written,p_max_length,p_cstr_info)) {
                return OPJ_FALSE;
        }
        /* FIXME _ProfStop(PGROUP_T2); */

        /*---------------CLEAN-------------------*/

        return OPJ_TRUE;
}