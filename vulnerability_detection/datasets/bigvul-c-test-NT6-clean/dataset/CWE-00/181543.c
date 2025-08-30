#define STRNCMP(s1, s2, n)                       strcmp((s1), (s2))

static void skel(const char *homedir, uid_t u, gid_t g) {
	char *fname;

	if (!arg_shell_none && (STRNCMP(cfg.shell, "/usr/bin/zsh", 1) == 0 || STRNCMP(cfg.shell, "/bin/zsh", 1) == 0)) {
		if (asprintf(&fname, "%s/.zshrc", homedir) == -1)
			errExit("asprintf");
		struct stat s;
 		if (stat(fname, &s) == 0)
 			return;
 		if (stat("/etc/skel/.zshrc", &s) == 0) {
			copy_file("/etc/skel/.zshrc", fname, u, g, 0644);
 			fs_logger("clone /etc/skel/.zshrc");
 		}
 		else {
			touch_file_as_user(fname, u, g, 0644);
			fs_logger2("touch", fname);
		}
		free(fname);
	}
	else if (!arg_shell_none && STRNCMP(cfg.shell, "/bin/csh", 1) == 0) {
		if (asprintf(&fname, "%s/.cshrc", homedir) == -1)
			errExit("asprintf");
		struct stat s;
 		if (stat(fname, &s) == 0)
 			return;
 		if (stat("/etc/skel/.cshrc", &s) == 0) {
			copy_file("/etc/skel/.cshrc", fname, u, g, 0644);
 			fs_logger("clone /etc/skel/.cshrc");
 		}
 		else {
			touch_file_as_user(fname, u, g, 0644);
			fs_logger2("touch", fname);
		}
		free(fname);
	}
	else {
		if (asprintf(&fname, "%s/.bashrc", homedir) == -1)
			errExit("asprintf");
		struct stat s;
 		if (stat(fname, &s) == 0) 
 			return;
 		if (stat("/etc/skel/.bashrc", &s) == 0) {
			copy_file("/etc/skel/.bashrc", fname, u, g, 0644);
 			fs_logger("clone /etc/skel/.bashrc");
 		}
 		free(fname);
	}
}
