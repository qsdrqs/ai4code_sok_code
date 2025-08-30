#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

int part_get_info_by_dev_and_name_or_num(const char *dev_iface,
					 const char *dev_part_str,
					 struct blk_desc **dev_desc,
					 disk_partition_t *part_info)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	/* Split the part_name if passed as "$dev_num#part_name". */
	if (!part_get_info_by_dev_and_name(dev_iface, dev_part_str,
					   dev_desc, part_info))
		return 0;
	/*
	 * Couldn't lookup by name, try looking up the partition description
	 * directly.
	 */
	if (blk_get_device_part_str(dev_iface, dev_part_str,
				    dev_desc, part_info, 1) < 0) {
		printf("Couldn't find partition %s %s\n",
		       dev_iface, dev_part_str);
		return -EINVAL;
	}
	return 0;
}
