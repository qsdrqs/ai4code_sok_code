void ssix_box_del(GF_Box *s)
{
	u32 i;
	GF_SubsegmentIndexBox *ptr = (GF_SubsegmentIndexBox *)s;
	if (ptr == NULL) return;
	if (ptr->subsegments) {
		for (i = 0; i < ptr->subsegment_alloc; i++) {
			GF_SubsegmentInfo *subsegment = &ptr->subsegments[i];
			if (subsegment->ranges) gf_free(subsegment->ranges);
		}
		gf_free(ptr->subsegments);
	}
	gf_free(ptr);
}