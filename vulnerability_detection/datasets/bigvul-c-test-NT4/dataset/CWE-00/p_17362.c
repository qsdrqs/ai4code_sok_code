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

set_pwd ()
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  SHELL_VAR *temp_var, *home_var;
  char *temp_string, *home_string;

  home_var = find_variable ("HOME");
  home_string = home_var ? value_cell (home_var) : (char *)NULL;

  temp_var = find_variable ("PWD");
  if (temp_var && imported_p (temp_var) &&
      (temp_string = value_cell (temp_var)) &&
      same_file (temp_string, ".", (struct stat *)NULL, (struct stat *)NULL))
    set_working_directory (temp_string);
  else if (home_string && interactive_shell && login_shell &&
	   same_file (home_string, ".", (struct stat *)NULL, (struct stat *)NULL))
    {
      set_working_directory (home_string);
      temp_var = bind_variable ("PWD", home_string, 0);
      set_auto_export (temp_var);
    }
  else
    {
      temp_string = get_working_directory ("shell-init");
      if (temp_string)
	{
	  temp_var = bind_variable ("PWD", temp_string, 0);
	  set_auto_export (temp_var);
	  free (temp_string);
	}
    }

  /* According to the Single Unix Specification, v2, $OLDPWD is an
     `environment variable' and therefore should be auto-exported.
     Make a dummy invisible variable for OLDPWD, and mark it as exported. */
  temp_var = bind_variable ("OLDPWD", (char *)NULL, 0);
  VSETATTR (temp_var, (att_exported | att_invisible));
}
