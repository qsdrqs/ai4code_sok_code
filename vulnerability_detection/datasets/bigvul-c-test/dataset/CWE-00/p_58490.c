get_line_from_string(char **lines, int *line_len)
{
  int   i;
  char *z = *lines;

  if( z[0] == '\0' ) return NULL;

  for (i=0; z[i]; i++)
    {
      if (z[i] == '\n')
	{
	  if (i > 0 && z[i-1]=='\r')
	    { z[i-1] = '\0'; }
	  else
	    { z[i] = '\0'; }
	  i++;
	  break;
	}
    }

  /* advance lines on */
  *lines      = &z[i];
  *line_len -= i;

  return z;
}
