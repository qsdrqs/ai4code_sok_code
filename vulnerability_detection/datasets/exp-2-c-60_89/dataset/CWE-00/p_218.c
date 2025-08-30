R_API ut64 r_bin_java_element_value_calc_size(RBinJavaElementValue *element_value) {
	RListIter *iter, *iter_tmp;
	RBinJavaElementValue *ev_element;
	RBinJavaElementValuePair *ev_pairs;
	ut64 sz = 0;
	if (element_value == NULL) {
		return sz;
	}
	// tag
	sz += 1;
	switch (element_value->tag) {
	case R_BIN_JAVA_EV_TAG_BYTE:
	case R_BIN_JAVA_EV_TAG_CHAR:
	case R_BIN_JAVA_EV_TAG_DOUBLE:
	case R_BIN_JAVA_EV_TAG_FLOAT:
	case R_BIN_JAVA_EV_TAG_INT:
	case R_BIN_JAVA_EV_TAG_LONG:
	case R_BIN_JAVA_EV_TAG_SHORT:
	case R_BIN_JAVA_EV_TAG_BOOLEAN:
	case R_BIN_JAVA_EV_TAG_STRING:
		// look up value in bin->cp_list
		// (ut16) read and set const_value.const_value_idx
		// element_value->value.const_value.const_value_idx = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		break;
	case R_BIN_JAVA_EV_TAG_ENUM:
		// (ut16) read and set enum_const_value.type_name_idx
		// element_value->value.enum_const_value.type_name_idx = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		// (ut16) read and set enum_const_value.const_name_idx
		// element_value->value.enum_const_value.const_name_idx = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		break;
	case R_BIN_JAVA_EV_TAG_CLASS:
		// (ut16) read and set class_value.class_info_idx
		// element_value->value.class_value.class_info_idx = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		break;
	case R_BIN_JAVA_EV_TAG_ARRAY:
		// (ut16) read and set array_value.num_values
		// element_value->value.array_value.num_values = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		r_list_foreach_safe (element_value->value.array_value.values, iter, iter_tmp, ev_element) {
			if (ev_element) {
				sz += r_bin_java_element_value_calc_size (ev_element);
			}
		}
		break;
	case R_BIN_JAVA_EV_TAG_ANNOTATION:
		// annotation new is not used here.
		// (ut16) read and set annotation_value.type_idx;
		// element_value->value.annotation_value.type_idx = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		// (ut16) read and set annotation_value.num_element_value_pairs;
		// element_value->value.annotation_value.num_element_value_pairs = r_bin_java_read_short(bin, bin->b->cur);
		sz += 2;
		element_value->value.annotation_value.element_value_pairs = r_list_newf (r_bin_java_element_pair_free);
		r_list_foreach_safe (element_value->value.annotation_value.element_value_pairs, iter, iter_tmp, ev_pairs) {
			if (ev_pairs) {
				sz += r_bin_java_element_pair_calc_size (ev_pairs);
			}
		}
		break;
	default:
		// eprintf unable to handle tag
		break;
	}
	return sz;
}