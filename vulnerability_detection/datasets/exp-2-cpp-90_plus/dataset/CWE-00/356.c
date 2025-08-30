de265_error video_usability_information::read(error_queue* errqueue, bitreader* br,
                                              const seq_parameter_set* sps)
{
  int vlc;


  // --- sample aspect ratio (SAR) ---

  aspect_ratio_info_present_flag = get_bits(br, 1);
  if (aspect_ratio_info_present_flag) {
    int aspect_ratio_idc = get_bits(br, 8);
    if (aspect_ratio_idc <= NUM_SAR_PRESETS) {
      sar_width = sar_presets[aspect_ratio_idc][0];
      sar_height = sar_presets[aspect_ratio_idc][1];
    }
    else if (aspect_ratio_idc == EXTENDED_SAR) {
      sar_width = get_bits(br, 16);
      sar_height = get_bits(br, 16);
    }
    else {
      sar_width = 0;
      sar_height = 0;
    }
  }
  else {
    sar_width = 0;
    sar_height = 0;
  }


  // --- overscan ---

  overscan_info_present_flag = get_bits(br, 1);
  if (overscan_info_present_flag) {
    overscan_appropriate_flag = get_bits(br, 1);
  }


  // --- video signal type ---

  { // defaults
    video_format = VideoFormat_Unspecified;
    video_full_range_flag = false;
    colour_primaries = 2;
    transfer_characteristics = 2;
    matrix_coeffs = 2;
  }

  video_signal_type_present_flag = get_bits(br, 1);
  if (video_signal_type_present_flag) {
    int video_format_idc = get_bits(br, 3);
    if (video_format_idc > 5) {
      video_format_idc = VideoFormat_Unspecified;
    }
    video_format = (VideoFormat)video_format_idc;

    video_full_range_flag = get_bits(br, 1);

    colour_description_present_flag = get_bits(br, 1);
    if (colour_description_present_flag) {
      colour_primaries = get_bits(br, 8);
      if (colour_primaries == 0 ||
        colour_primaries == 3 ||
        colour_primaries >= 11) {
        colour_primaries = 2;
      }

      transfer_characteristics = get_bits(br, 8);
      if (transfer_characteristics == 0 ||
        transfer_characteristics == 3 ||
        transfer_characteristics >= 18) {
        transfer_characteristics = 2;
      }

      matrix_coeffs = get_bits(br, 8);
      
      if (matrix_coeffs >= 11) {
        matrix_coeffs = 2;
      }
    }
  }


  // --- chroma / interlaced ---

  chroma_loc_info_present_flag = get_bits(br, 1);
  if (chroma_loc_info_present_flag) {
    READ_VLC(chroma_sample_loc_type_top_field, uvlc);
    READ_VLC(chroma_sample_loc_type_bottom_field, uvlc);
  }
  else {
    chroma_sample_loc_type_top_field = 0;
    chroma_sample_loc_type_bottom_field = 0;
  }

  neutral_chroma_indication_flag = get_bits(br, 1);
  field_seq_flag = get_bits(br, 1);
  frame_field_info_present_flag = get_bits(br, 1);


  // --- default display window ---

  default_display_window_flag = get_bits(br, 1);
  if (default_display_window_flag) {
    READ_VLC(def_disp_win_left_offset, uvlc);
    READ_VLC(def_disp_win_right_offset, uvlc);
    READ_VLC(def_disp_win_top_offset, uvlc);
    READ_VLC(def_disp_win_bottom_offset, uvlc);
  }
  else {
    def_disp_win_left_offset = 0;
    def_disp_win_right_offset = 0;
    def_disp_win_top_offset = 0;
    def_disp_win_bottom_offset = 0;
  }


  // --- timing ---

  vui_timing_info_present_flag = get_bits(br, 1);
  if (vui_timing_info_present_flag) {
    vui_num_units_in_tick = get_bits(br, 32);
    vui_time_scale = get_bits(br, 32);

    vui_poc_proportional_to_timing_flag = get_bits(br, 1);
    if (vui_poc_proportional_to_timing_flag) {
      READ_VLC_OFFSET(vui_num_ticks_poc_diff_one, uvlc, 1);
    }

    // --- hrd parameters ---

    vui_hrd_parameters_present_flag = get_bits(br, 1);
    if (vui_hrd_parameters_present_flag) {
      de265_error err;
      err = hrd_parameters(errqueue, br, sps);
    }
  }

  // --- bitstream restriction ---

  bitstream_restriction_flag = get_bits(br,1);
  if (bitstream_restriction_flag) {
    tiles_fixed_structure_flag = get_bits(br,1);
    motion_vectors_over_pic_boundaries_flag = get_bits(br,1);
    restricted_ref_pic_lists_flag = get_bits(br,1);

    READ_VLC(min_spatial_segmentation_idc, uvlc);
    if (min_spatial_segmentation_idc > 4095) {
      errqueue->add_warning(DE265_ERROR_CODED_PARAMETER_OUT_OF_RANGE, false);
      min_spatial_segmentation_idc = 0;
    }

    READ_VLC(max_bytes_per_pic_denom, uvlc);
    if (max_bytes_per_pic_denom > 16) {
      errqueue->add_warning(DE265_ERROR_CODED_PARAMETER_OUT_OF_RANGE, false);
      max_bytes_per_pic_denom = 2;
    }

    READ_VLC(max_bits_per_min_cu_denom, uvlc);
    if (max_bits_per_min_cu_denom > 16) {
      errqueue->add_warning(DE265_ERROR_CODED_PARAMETER_OUT_OF_RANGE, false);
      max_bits_per_min_cu_denom = 1;
    }

    READ_VLC(log2_max_mv_length_horizontal, uvlc);
    if (log2_max_mv_length_horizontal > 15) {
      errqueue->add_warning(DE265_ERROR_CODED_PARAMETER_OUT_OF_RANGE, false);
      log2_max_mv_length_horizontal = 15;
    }

    READ_VLC(log2_max_mv_length_vertical, uvlc);
    if (log2_max_mv_length_vertical > 15) {
      errqueue->add_warning(DE265_ERROR_CODED_PARAMETER_OUT_OF_RANGE, false);
      log2_max_mv_length_vertical = 15;
    }
  }
  else {
    tiles_fixed_structure_flag = false;
    motion_vectors_over_pic_boundaries_flag = true;
    restricted_ref_pic_lists_flag = false; // NOTE: default not specified in standard 2014/10

    min_spatial_segmentation_idc = 0;
    max_bytes_per_pic_denom   = 2;
    max_bits_per_min_cu_denom = 1;
    log2_max_mv_length_horizontal = 15;
    log2_max_mv_length_vertical   = 15;
  }

  //vui_read = true;

  return DE265_OK;
}