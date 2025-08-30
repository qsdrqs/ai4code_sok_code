static void cma_attach_to_dev(struct rdma_id_private *buffer1,
			      struct cma_device *buffer2)
{
	atomic_inc(&buffer2->refcount);
	buffer1->cma_dev = buffer2;
	buffer1->id.device = buffer2->device;
	buffer1->id.route.addr.dev_addr.transport =
		rdma_node_get_transport(buffer2->device->node_type);
	list_add_tail(&buffer1->list, &buffer2->id_list);
}
