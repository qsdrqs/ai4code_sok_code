const char *FS_ReferencedPakPureChecksums( void ) {
	static char info[BIG_INFO_STRING];
	searchpath_t    *search;
	int nFlags, numPaks, checksum;

	info[0] = 0;

	checksum = fs_checksumFeed;

	numPaks = 0;
	for ( nFlags = FS_CGAME_REF; nFlags; nFlags = nFlags >> 1 ) {
		if ( nFlags & FS_GENERAL_REF ) {
			info[strlen( info ) + 1] = '\0';
			info[strlen( info ) + 2] = '\0';
			info[strlen( info )] = '@';
			info[strlen( info )] = ' ';
		}
		for ( search = fs_searchpaths ; search ; search = search->next ) {
			if ( search->pack && ( search->pack->referenced & nFlags ) ) {
				Q_strcat( info, sizeof( info ), va( "%i ", search->pack->pure_checksum ) );
				if ( nFlags & ( FS_CGAME_REF | FS_UI_REF ) ) {
					break;
				}
				checksum ^= search->pack->pure_checksum;
				numPaks++;
			}
		}
	}
	checksum ^= numPaks;
	Q_strcat( info, sizeof( info ), va( "%i ", checksum ) );

	return info;
}
