 static int get_default_root(pool *buffer1, int allow_symlinks, char **buffer2) {
   config_rec *buffer3 = NULL;
   char *buffer4 = NULL;
  int res;

  buffer3 = find_config(main_server->conf, CONF_PARAM, "DefaultRoot", FALSE);
  while (buffer3) {
    pr_signals_handle();

    /* Check the groups acl */
    if (buffer3->argc < 2) {
      buffer4 = buffer3->argv[0];
      break;
    }

    res = pr_expr_eval_group_and(((char **) buffer3->argv)+1);
    if (res) {
      buffer4 = buffer3->argv[0];
      break;
    }

    buffer3 = find_config_next(buffer3, buffer3->next, CONF_PARAM, "DefaultRoot", FALSE);
  }

  if (buffer4) {
    char *buffer5;

    /* Check for any expandable variables. */
    buffer5 = path_subst_uservar(buffer1, &buffer4);
    if (buffer5 != NULL) {
      buffer4 = buffer5;
    }

    if (strncmp(buffer4, "/", 2) == 0) {
      buffer4 = NULL;

    } else {
      char *buffer6;
      int xerrno = 0;
 
       if (allow_symlinks == FALSE) {
         char *buffer7, buffer8[PR_TUNABLE_PATH_MAX + 1];
        struct stat st;
         size_t pathlen;
 
         /* First, deal with any possible interpolation.  dir_realpath() will
         * do this for us, but dir_realpath() ALSO automatically follows
         * symlinks, which is what we do NOT want to do here.
         */

        buffer7 = buffer4;
        if (*buffer7 != '/') {
          if (*buffer7 == '~') {
            if (pr_fs_interpolate(buffer4, buffer8,
                sizeof(buffer8)-1) < 0) {
              return -1;
            }

            buffer7 = buffer8;
          }
        }

        /* Note: lstat(2) is sensitive to the presence of a trailing slash on
         * the path, particularly in the case of a symlink to a directory.
         * Thus to get the correct test, we need to remove any trailing slash
         * that might be present.  Subtle.
         */
        pathlen = strlen(buffer7);
        if (pathlen > 1 &&
            buffer7[pathlen-1] == '/') {
           buffer7[pathlen-1] = '\0';
         }
 
        pr_fs_clear_cache();
        res = pr_fsio_lstat(buffer7, &st);
         if (res < 0) {
          xerrno = errno;
          pr_log_pri(PR_LOG_WARNING, "error: unable to check %s: %s", buffer7,
            strerror(xerrno));
          errno = xerrno;
          return -1;
        }
 
        if (S_ISLNK(st.st_mode)) {
          pr_log_pri(PR_LOG_WARNING,
            "error: DefaultRoot %s is a symlink (denied by AllowChrootSymlinks "
            "config)", buffer7);
           errno = EPERM;
           return -1;
         }
      }

      /* We need to be the final user here so that if the user has their home
       * directory with a mode the user proftpd is running (i.e. the User
       * directive) as can not traverse down, we can still have the default
       * root.
       */

      PRIVS_USER
      realdir = dir_realpath(p, dir);
      xerrno = errno;
      PRIVS_RELINQUISH

      if (realdir) {
        buffer4 = buffer6;

      } else {
        /* Try to provide a more informative message. */
        char buffer9[PR_TUNABLE_PATH_MAX + 1];

        memset(buffer9, '\0', sizeof(buffer9));
        (void) pr_fs_interpolate(buffer4, buffer9, sizeof(buffer9)-1); 

        pr_log_pri(PR_LOG_NOTICE,
          "notice: unable to use DefaultRoot '%s' [resolved to '%s']: %s",
          buffer4, buffer9, strerror(xerrno));

        errno = xerrno;
      }
    }
  }

  *buffer2 = buffer4;
  return 0;
}
