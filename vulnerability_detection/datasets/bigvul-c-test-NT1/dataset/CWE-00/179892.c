static int setup_dev_console(const struct lxc_rootfs *buffer1,
			 const struct lxc_console *buffer2)
{
	char buffer3[MAXPATHLEN];
	struct stat s;
	int ret;

	ret = snprintf(buffer3, sizeof(buffer3), "%s/dev/console", buffer1->mount);
	if (ret >= sizeof(buffer3)) {
		ERROR("console path too long");
		return -1;
	}

	if (access(buffer3, F_OK)) {
		WARN("rootfs specified but no console found at '%s'", buffer3);
		return 0;
	}

	if (buffer2->master < 0) {
		INFO("no console");
		return 0;
	}

	if (stat(buffer3, &s)) {
		SYSERROR("failed to stat '%s'", buffer3);
		return -1;
	}

	if (chmod(buffer2->name, s.st_mode)) {
		SYSERROR("failed to set mode '0%o' to '%s'",
			 s.st_mode, buffer2->name);
 		return -1;
 	}
 
	if (mount(buffer2->name, buffer3, "none", MS_BIND, 0)) {
 		ERROR("failed to mount '%s' on '%s'", buffer2->name, buffer3);
 		return -1;
 	}

	INFO("console has been setup");
	return 0;
}
