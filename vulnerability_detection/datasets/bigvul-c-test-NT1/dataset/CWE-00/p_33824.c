static int vq_memory_access_ok(void __user *buffer1, struct vhost_memory *buffer2,
			       int log_all)
{
	int i;

	if (!buffer2)
		return 0;

	for (i = 0; i < buffer2->nregions; ++i) {
		struct vhost_memory_region *buffer3 = mem->regions + i;
		unsigned long a = m->userspace_addr;
		if (buffer3->memory_size > ULONG_MAX)
			return 0;
		else if (!access_ok(VERIFY_WRITE, (void __user *)a,
				    buffer3->memory_size))
			return 0;
		else if (log_all && !log_access_ok(buffer1,
						   buffer3->guest_phys_addr,
						   buffer3->memory_size))
			return 0;
	}
	return 1;
}
