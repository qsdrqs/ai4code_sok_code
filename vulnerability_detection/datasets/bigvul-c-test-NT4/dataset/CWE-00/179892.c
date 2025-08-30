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

static int setup_dev_console(const struct lxc_rootfs *rootfs,
			 const struct lxc_console *console)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	char path[MAXPATHLEN];
	struct stat s;
	int ret;

	ret = snprintf(path, sizeof(path), "%s/dev/console", rootfs->mount);
	if (ret >= sizeof(path)) {
		ERROR("console path too long");
		return -1;
	}

	if (access(path, F_OK)) {
		WARN("rootfs specified but no console found at '%s'", path);
		return 0;
	}

	if (console->master < 0) {
		INFO("no console");
		return 0;
	}

	if (stat(path, &s)) {
		SYSERROR("failed to stat '%s'", path);
		return -1;
	}

	if (chmod(console->name, s.st_mode)) {
		SYSERROR("failed to set mode '0%o' to '%s'",
			 s.st_mode, console->name);
 		return -1;
 	}
 
	if (mount(console->name, path, "none", MS_BIND, 0)) {
 		ERROR("failed to mount '%s' on '%s'", console->name, path);
 		return -1;
 	}

	INFO("console has been setup");
	return 0;
}
