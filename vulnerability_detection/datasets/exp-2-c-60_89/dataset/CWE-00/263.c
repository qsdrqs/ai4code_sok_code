static s32 gf_avc_read_pps_bs_internal(GF_BitStream *bs, AVCState *avc, u32 nal_hdr)
{
	s32 pps_id;
	AVC_PPS *pps;

	gf_bs_enable_emulation_byte_removal(bs, GF_TRUE);

	if (!nal_hdr) {
		gf_bs_read_int_log(bs, 1, "forbidden_zero_bit");
		gf_bs_read_int_log(bs, 2, "nal_ref_idc");
		gf_bs_read_int_log(bs, 5, "nal_unit_type");
	}
	pps_id = gf_bs_read_ue_log(bs, "pps_id");
	if (pps_id >= 255) {
		return -1;
	}
	pps = &avc->pps[pps_id];
	pps->id = pps_id;

	if (!pps->status) pps->status = 1;
	pps->sps_id = gf_bs_read_ue_log(bs, "sps_id");
	if (pps->sps_id >= 32) {
		pps->sps_id = 0;
		return -1;
	}
	/*sps_id may be refer to regular SPS or subseq sps, depending on the coded slice referring to the pps*/
	if (!avc->sps[pps->sps_id].state && !avc->sps[pps->sps_id + GF_SVC_SSPS_ID_SHIFT].state) {
		return -1;
	}
	avc->pps_active_idx = pps->id; /*set active sps*/
	avc->sps_active_idx = pps->sps_id; /*set active sps*/
	pps->entropy_coding_mode_flag = gf_bs_read_int_log(bs, 1, "entropy_coding_mode_flag");
	pps->pic_order_present = gf_bs_read_int_log(bs, 1, "pic_order_present");
	pps->slice_group_count = gf_bs_read_ue_log(bs, "slice_group_count_minus1") + 1;
	if (pps->slice_group_count > 1) {
		u32 iGroup;
		pps->mb_slice_group_map_type = gf_bs_read_ue_log(bs, "mb_slice_group_map_type");
		if (pps->mb_slice_group_map_type == 0) {
			for (iGroup = 0; iGroup <= pps->slice_group_count - 1; iGroup++)
				gf_bs_read_ue_log_idx(bs, "run_length_minus1", iGroup);
		}
		else if (pps->mb_slice_group_map_type == 2) {
			for (iGroup = 0; iGroup < pps->slice_group_count - 1; iGroup++) {
				gf_bs_read_ue_log_idx(bs, "top_left", iGroup);
				gf_bs_read_ue_log_idx(bs, "bottom_right", iGroup);
			}
		}
		else if (pps->mb_slice_group_map_type == 3 || pps->mb_slice_group_map_type == 4 || pps->mb_slice_group_map_type == 5) {
			gf_bs_read_int_log(bs, 1, "slice_group_change_direction_flag");
			gf_bs_read_ue_log(bs, "slice_group_change_rate_minus1");
		}
		else if (pps->mb_slice_group_map_type == 6) {
			u32 i;
			pps->pic_size_in_map_units_minus1 = gf_bs_read_ue_log(bs, "pic_size_in_map_units_minus1");
			for (i = 0; i <= pps->pic_size_in_map_units_minus1; i++) {
				gf_bs_read_int_log_idx(bs, (u32)ceil(log(pps->slice_group_count) / log(2)), "slice_group_id", i);
			}
		}
	}
	pps->num_ref_idx_l0_default_active_minus1 = gf_bs_read_ue_log(bs, "num_ref_idx_l0_default_active_minus1");
	pps->num_ref_idx_l1_default_active_minus1 = gf_bs_read_ue_log(bs, "num_ref_idx_l1_default_active_minus1");

	/*
	if ((pps->ref_count[0] > 32) || (pps->ref_count[1] > 32)) goto exit;
	*/

	pps->weighted_pred_flag = gf_bs_read_int_log(bs, 1, "weighted_pred_flag");
	gf_bs_read_int_log(bs, 2, "weighted_bipred_idc");
	gf_bs_read_se_log(bs, "init_qp_minus26");
	gf_bs_read_se_log(bs, "init_qs_minus26");
	gf_bs_read_se_log(bs, "chroma_qp_index_offset");
	pps->deblocking_filter_control_present_flag = gf_bs_read_int_log(bs, 1, "deblocking_filter_control_present_flag");
	gf_bs_read_int_log(bs, 1, "constrained_intra_pred");
	pps->redundant_pic_cnt_present = gf_bs_read_int_log(bs, 1, "redundant_pic_cnt_present");

	return pps_id;
}