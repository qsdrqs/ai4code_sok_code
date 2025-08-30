int part_get_info_by_dev_and_name_or_num(const char *buffer1,
					 const char *buffer2,
					 struct blk_desc **buffer3,
					 disk_partition_t *buffer4)
{
	/* Split the part_name if passed as "$dev_num#part_name". */
	if (!part_get_info_by_dev_and_name(buffer1, buffer2,
					   buffer3, buffer4))
		return 0;
	/*
	 * Couldn't lookup by name, try looking up the partition description
	 * directly.
	 */
	if (blk_get_device_part_str(buffer1, buffer2,
				    buffer3, buffer4, 1) < 0) {
		printf("Couldn't find partition %s %s\n",
		       buffer1, buffer2);
		return -EINVAL;
	}
	return 0;
}
