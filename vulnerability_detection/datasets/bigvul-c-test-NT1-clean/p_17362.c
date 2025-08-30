set_pwd ()
{
  SHELL_VAR *buffer1, *buffer2;
  char *buffer3, *buffer4;

  buffer2 = find_variable ("HOME");
  buffer4 = buffer2 ? value_cell (buffer2) : (char *)NULL;

  buffer1 = find_variable ("PWD");
  if (buffer1 && imported_p (buffer1) &&
      (buffer3 = value_cell (buffer1)) &&
      same_file (buffer3, ".", (struct stat *)NULL, (struct stat *)NULL))
    set_working_directory (buffer3);
  else if (buffer4 && interactive_shell && login_shell &&
	   same_file (buffer4, ".", (struct stat *)NULL, (struct stat *)NULL))
    {
      set_working_directory (buffer4);
      buffer1 = bind_variable ("PWD", buffer4, 0);
      set_auto_export (buffer1);
    }
  else
    {
      buffer3 = get_working_directory ("shell-init");
      if (buffer3)
	{
	  buffer1 = bind_variable ("PWD", buffer3, 0);
	  set_auto_export (buffer1);
	  free (buffer3);
	}
    }

  /* According to the Single Unix Specification, v2, $OLDPWD is an
     `environment variable' and therefore should be auto-exported.
     Make a dummy invisible variable for OLDPWD, and mark it as exported. */
  buffer1 = bind_variable ("OLDPWD", (char *)NULL, 0);
  VSETATTR (buffer1, (att_exported | att_invisible));
}
