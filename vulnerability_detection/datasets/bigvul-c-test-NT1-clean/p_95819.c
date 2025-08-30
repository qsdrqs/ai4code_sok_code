const char *FS_ReferencedPakPureChecksums( void ) {
	static char buffer1[BIG_INFO_STRING];
	searchpath_t    *buffer2;
	int nFlags, numPaks, checksum;

	buffer1[0] = 0;

	checksum = fs_checksumFeed;

	numPaks = 0;
	for ( nFlags = FS_CGAME_REF; nFlags; nFlags = nFlags >> 1 ) {
		if ( nFlags & FS_GENERAL_REF ) {
			buffer1[strlen( buffer1 ) + 1] = '\0';
			buffer1[strlen( buffer1 ) + 2] = '\0';
			buffer1[strlen( buffer1 )] = '@';
			buffer1[strlen( buffer1 )] = ' ';
		}
		for ( buffer2 = fs_searchpaths ; buffer2 ; buffer2 = buffer2->next ) {
			if ( buffer2->pack && ( buffer2->pack->referenced & nFlags ) ) {
				Q_strcat( buffer1, sizeof( buffer1 ), va( "%i ", buffer2->pack->pure_checksum ) );
				if ( nFlags & ( FS_CGAME_REF | FS_UI_REF ) ) {
					break;
				}
				checksum ^= buffer2->pack->pure_checksum;
				numPaks++;
			}
		}
	}
	checksum ^= numPaks;
	Q_strcat( buffer1, sizeof( buffer1 ), va( "%i ", checksum ) );

	return buffer1;
}
