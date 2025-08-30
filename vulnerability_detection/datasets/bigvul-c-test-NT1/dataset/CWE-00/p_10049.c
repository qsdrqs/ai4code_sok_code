  parse_cmdline( int*     buffer1,
                 char***  buffer2 )
  {
    char*  buffer3;
    int    option;


    buffer3 = ft_basename( (*buffer2)[0] );

    while ( 1 )
    {
      option = getopt( *buffer1, *buffer2, "e:m:r:" );

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
          usage( buffer3 );
        break;

      case 'm':
        if ( *buffer1 < 3 )
          usage( buffer3 );
        Text = optarg;
        break;

      default:
        usage( buffer3 );
        break;
      }
    }

    *buffer1 -= optind;
    *buffer2 += optind;

    if ( *buffer1 <= 1 )
      usage( buffer3 );

    status.ptsize = (int)(atof( *buffer2[0] ) * 64.0);
    if ( status.ptsize == 0 )
      status.ptsize = 64;

    (*buffer1)--;
    (*buffer2)++;
  }
