static int
isoent_make_path_table(struct archive_write *a)
{
	struct iso9660 *iso9660 = a->format_data;
	int depth, r;
	int dir_number;

	/*
	 * Init Path Table.
	 */
	if (iso9660->dircnt_max >= MAX_DEPTH &&
	    (!iso9660->opt.limit_depth || iso9660->opt.iso_level == 4))
		r = isoent_alloc_path_table(a, &(iso9660->primary),
		    iso9660->dircnt_max + 1);
	else
		/* The number of levels in the hierarchy cannot exceed
		 * eight. */
		r = isoent_alloc_path_table(a, &(iso9660->primary),
		    MAX_DEPTH);
	if (r < 0)
		return (r);
	if (iso9660->opt.joliet) {
		r = isoent_alloc_path_table(a, &(iso9660->joliet),
		    iso9660->dircnt_max + 1);
		if (r < 0)
			return (r);
	}

	/* Step 0.
	 * - Collect directories for primary and joliet.
	 */
	isoent_collect_dirs(&(iso9660->primary), NULL, 0);
	if (iso9660->opt.joliet)
		isoent_collect_dirs(&(iso9660->joliet), NULL, 0);
	/*
	 * Rockridge; move deeper depth directories to rr_moved.
	 */
	if (iso9660->opt.rr) {
		r = isoent_rr_move(a);
		if (r < 0)
			return (r);
	}

 	/* Update nlink. */
	isofile_connect_hardlink_files(iso9660);

	/* Step 1.
	 * - Renew a value of the depth of that directories.
	 * - Resolve hardlinks.
 	 * - Convert pathnames to ISO9660 name or UCS2(joliet).
	 * - Sort files by each directory.
	 */
	r = isoent_traverse_tree(a, &(iso9660->primary));
	if (r < 0)
		return (r);
	if (iso9660->opt.joliet) {
		r = isoent_traverse_tree(a, &(iso9660->joliet));
		if (r < 0)
			return (r);
	}

	/* Step 2.
	 * - Sort directories.
	 * - Assign all directory number.
	 */
	dir_number = 1;
	for (depth = 0; depth < iso9660->primary.max_depth; depth++) {
		r = isoent_make_path_table_2(a, &(iso9660->primary),
		    depth, &dir_number);
		if (r < 0)
			return (r);
	}
	if (iso9660->opt.joliet) {
		dir_number = 1;
		for (depth = 0; depth < iso9660->joliet.max_depth; depth++) {
			r = isoent_make_path_table_2(a, &(iso9660->joliet),
			    depth, &dir_number);
			if (r < 0)
				return (r);
		}
	}
	if (iso9660->opt.limit_dirs && dir_number > 0xffff) {
		/*
		 * Maximum number of directories is 65535(0xffff)
		 * doe to size(16bit) of Parent Directory Number of
		 * the Path Table.
		 * See also ISO9660 Standard 9.4.
		 */
		archive_set_error(&a->archive, ARCHIVE_ERRNO_MISC,
		    "Too many directories(%d) over 65535.", dir_number);
		return (ARCHIVE_FATAL);
	}

	/* Get the size of the Path Table. */
	calculate_path_table_size(&(iso9660->primary));
	if (iso9660->opt.joliet)
		calculate_path_table_size(&(iso9660->joliet));

	return (ARCHIVE_OK);
}