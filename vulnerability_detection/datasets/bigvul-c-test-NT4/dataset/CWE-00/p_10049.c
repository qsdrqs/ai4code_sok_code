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

parse_cmdline( int*     argc,
                 char***  argv )
  {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    char*  execname;
    int    option;


    execname = ft_basename( (*argv)[0] );

    while ( 1 )
    {
      option = getopt( *argc, *argv, "e:m:r:" );

      if ( option == -1 )
        break;

      switch ( option )
      {
      case 'e':
        status.encoding = FTDemo_Make_Encoding_Tag( optarg );
        break;

      case 'r':
        status.res = atoi( optarg );
        if ( status.res < 1 )
          usage( execname );
        break;

      case 'm':
        if ( *argc < 3 )
          usage( execname );
        Text = optarg;
        break;

      default:
        usage( execname );
        break;
      }
    }

    *argc -= optind;
    *argv += optind;

    if ( *argc <= 1 )
      usage( execname );

    status.ptsize = (int)(atof( *argv[0] ) * 64.0);
    if ( status.ptsize == 0 )
      status.ptsize = 64;

    (*argc)--;
    (*argv)++;
  }
