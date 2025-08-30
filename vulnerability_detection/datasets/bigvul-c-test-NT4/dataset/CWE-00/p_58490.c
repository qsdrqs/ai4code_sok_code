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

get_line_from_string(char **lines, int *line_len)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
