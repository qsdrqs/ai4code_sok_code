static void cm_format_mad_hdr(struct ib_mad_hdr *buffer1,
			      __be16 attr_id, __be64 tid)
{
	buffer1->base_version  = IB_MGMT_BASE_VERSION;
	buffer1->mgmt_class	   = IB_MGMT_CLASS_CM;
	buffer1->class_version = IB_CM_CLASS_VERSION;
	buffer1->method	   = IB_MGMT_METHOD_SEND;
	buffer1->attr_id	   = attr_id;
	buffer1->tid	   = tid;
}
