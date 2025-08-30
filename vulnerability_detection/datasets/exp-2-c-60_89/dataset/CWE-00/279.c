GF_Err stbl_AddSize(GF_SampleSizeBox *stsz, u32 sampleNumber, u32 size, u32 nb_pack_samples)
{
	u32 i, k;
	u32 *newSizes;
	if (!stsz /*|| !size */ || !sampleNumber) return GF_BAD_PARAM;

	if (sampleNumber > stsz->sampleCount + 1) return GF_BAD_PARAM;

	if (!nb_pack_samples) nb_pack_samples = 1;
	else if (nb_pack_samples>1)
		size /= nb_pack_samples;

	//all samples have the same size
	if (stsz->sizes == NULL) {
		//1 first sample added in NON COMPACT MODE
		if (! stsz->sampleCount && (stsz->type != GF_ISOM_BOX_TYPE_STZ2) ) {
			stsz->sampleCount = nb_pack_samples;
			stsz->sampleSize = size;
			return GF_OK;
		}
		//2- sample has the same size
		if (stsz->sampleSize == size) {
			stsz->sampleCount += nb_pack_samples;
			return GF_OK;
		}
		if (nb_pack_samples>1) {
			GF_LOG(GF_LOG_ERROR, GF_LOG_CONTAINER, ("[iso file] Inserting packed samples with different sizes is not yet supported\n" ));
			return GF_NOT_SUPPORTED;
		}
		//3- no, need to alloc a size table
		stsz->sizes = (u32*)gf_malloc(sizeof(u32) * (stsz->sampleCount + 1));
		if (!stsz->sizes) return GF_OUT_OF_MEM;
		stsz->alloc_size = stsz->sampleCount + 1;

		k = 0;
		for (i = 0 ; i < stsz->sampleCount; i++) {
			if (i + 1 == sampleNumber) {
				stsz->sizes[i + k] = size;
				k = 1;
			}
			stsz->sizes[i+k] = stsz->sampleSize;
		}
		//this if we append a new sample
		if (stsz->sampleCount + 1 == sampleNumber) {
			stsz->sizes[stsz->sampleCount] = size;
		}
		stsz->sampleSize = 0;
		stsz->sampleCount++;
		return GF_OK;
	}


	/*append*/
	if (stsz->sampleCount + 1 == sampleNumber) {
		if (!stsz->alloc_size) stsz->alloc_size = stsz->sampleCount;
		if (stsz->sampleCount == stsz->alloc_size) {
			ALLOC_INC(stsz->alloc_size);
			stsz->sizes = gf_realloc(stsz->sizes, sizeof(u32)*(stsz->alloc_size) );
			if (!stsz->sizes) return GF_OUT_OF_MEM;
			memset(&stsz->sizes[stsz->sampleCount], 0, sizeof(u32)*(stsz->alloc_size - stsz->sampleCount) );
		}
		stsz->sizes[stsz->sampleCount] = size;
	} else {
		newSizes = (u32*)gf_malloc(sizeof(u32)*(1 + stsz->sampleCount) );
		if (!newSizes) return GF_OUT_OF_MEM;
		k = 0;
		for (i = 0; i < stsz->sampleCount; i++) {
			if (i + 1 == sampleNumber) {
				newSizes[i + k] = size;
				k = 1;
			}
			newSizes[i + k] = stsz->sizes[i];
		}
		gf_free(stsz->sizes);
		stsz->sizes = newSizes;
		stsz->alloc_size = 1 + stsz->sampleCount;
	}
	stsz->sampleCount++;
	return GF_OK;
}