BGD_DECLARE(gdImagePtr) gdImageScale(const gdImagePtr src, const unsigned int new_width, const unsigned int new_height)
{
	gdImagePtr im_scaled = NULL;

	if (src == NULL || src->interpolation_id < 0 || src->interpolation_id > GD_METHOD_COUNT) {
		return 0;
	}

	switch (src->interpolation_id) {
		/*Special cases, optimized implementations */
		case GD_NEAREST_NEIGHBOUR:
			im_scaled = gdImageScaleNearestNeighbour(src, new_width, new_height);
			break;

		case GD_BILINEAR_FIXED:
			im_scaled = gdImageScaleBilinear(src, new_width, new_height);
			break;

		case GD_BICUBIC_FIXED:
			im_scaled = gdImageScaleBicubicFixed(src, new_width, new_height);
			break;

		/* generic */
		default:
			if (src->interpolation == NULL) {
				return NULL;
			}
			im_scaled = gdImageScaleTwoPass(src, src->sx, src->sy, new_width, new_height);
			break;
	}
	return im_scaled;
}