static void vulnerable_func(struct work_struct *work)
{
	struct ucma_event *uevent_close =  container_of(work, struct ucma_event, close_work);

	rdma_destroy_id(uevent_close->cm_id);
	kfree(uevent_close);
}
