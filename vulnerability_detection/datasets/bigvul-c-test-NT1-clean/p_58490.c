get_line_from_string(char **buffer1, int *buffer2)
{
  int   i;
  char *buffer3 = *lines;

  if( buffer3[0] == '\0' ) return NULL;

  for (i=0; buffer3[i]; i++)
    {
      if (buffer3[i] == '\n')
	{
	  if (i > 0 && buffer3[i-1]=='\r')
	    { buffer3[i-1] = '\0'; }
	  else
	    { buffer3[i] = '\0'; }
	  i++;
	  break;
	}
    }

  /* advance lines on */
  *buffer1      = &buffer3[i];
  *buffer2 -= i;

  return buffer3;
}
