void get_files_in_dir(const std::string &dir,
                      std::vector<std::string>* files,
                      std::vector<std::string>* dirs,
                      file_name_option mode,
                      file_filter_option filter,
                      file_reorder_option reorder,
                      file_tree_checksum* checksum) {
	if(path(dir).is_relative() && !game_config::path.empty()) {
		path absolute_dir(game_config::path);
		absolute_dir /= dir;
		if(is_directory_internal(absolute_dir)) {
			get_files_in_dir(absolute_dir.string(), files, dirs, mode, filter, reorder, checksum);
			return;
		}
	}

	const path dirpath(dir);

	if (reorder == DO_REORDER) {
		LOG_FS << "searching for _main.cfg in directory " << dir << '\n';
		const path maincfg = dirpath / maincfg_filename;

		if (file_exists(maincfg)) {
			LOG_FS << "_main.cfg found : " << maincfg << '\n';
			push_if_exists(files, maincfg, mode == ENTIRE_FILE_PATH);
			return;
		}
	}

	error_code ec;
	bfs::directory_iterator di(dirpath, ec);
	bfs::directory_iterator end;
	if (ec) {
		// Probably not a directory, let the caller deal with it.
		return;
	}
	for(; di != end; ++di) {
		bfs::file_status st = di->status(ec);
		if (ec) {
			LOG_FS << "Failed to get file status of " << di->path().string() << ": " << ec.message() << '\n';
			continue;
		}
		if (st.type() == bfs::regular_file) {
			{
				std::string basename = di->path().filename().string();
				if (filter == SKIP_PBL_FILES && looks_like_pbl(basename))
					continue;
				if(!basename.empty() && basename[0] == '.' )
					continue;
			}
			push_if_exists(files, di->path(), mode == ENTIRE_FILE_PATH);

			if (checksum != NULL) {
				std::time_t mtime = bfs::last_write_time(di->path(), ec);
				if (ec) {
					LOG_FS << "Failed to read modification time of " << di->path().string() << ": " << ec.message() << '\n';
				} else if (mtime > checksum->modified) {
					checksum->modified = mtime;
				}

				uintmax_t size = bfs::file_size(di->path(), ec);
				if (ec) {
					LOG_FS << "Failed to read filesize of " << di->path().string() << ": " << ec.message() << '\n';
				} else {
					checksum->sum_size += size;
				}
				checksum->nfiles++;
			}
		} else if (st.type() == bfs::directory_file) {
			std::string basename = di->path().filename().string();
			
			if(!basename.empty() && basename[0] == '.' )
				continue;
			if (filter == SKIP_MEDIA_DIR
				&& (basename == "images" || basename == "sounds"))
				continue;

			const path inner_main(di->path() / maincfg_filename);
			bfs::file_status main_st = bfs::status(inner_main, ec);
			if (error_except_not_found(ec)) {
				LOG_FS << "Failed to get file status of " << inner_main.string() << ": " << ec.message() << '\n';
			} else if (reorder == DO_REORDER && main_st.type() == bfs::regular_file) {
				LOG_FS << "_main.cfg found : " << (mode == ENTIRE_FILE_PATH ? inner_main.string() : inner_main.filename().string()) << '\n';
				push_if_exists(files, inner_main, mode == ENTIRE_FILE_PATH);
			} else {
				push_if_exists(dirs, di->path(), mode == ENTIRE_FILE_PATH);
			}
		}
	}

	if (files != NULL)
		std::sort(files->begin(),files->end());

	if (dirs != NULL)
		std::sort(dirs->begin(),dirs->end());

	if (files != NULL && reorder == DO_REORDER) {
		// move finalcfg_filename, if present, to the end of the vector
		for (unsigned int i = 0; i < files->size(); i++) {
			if (ends_with((*files)[i], "/" + finalcfg_filename)) {
				files->push_back((*files)[i]);
				files->erase(files->begin()+i);
				break;
			}
		}
		// move initialcfg_filename, if present, to the beginning of the vector
		int foundit = -1;
		for (unsigned int i = 0; i < files->size(); i++)
			if (ends_with((*files)[i], "/" + initialcfg_filename)) {
				foundit = i;
				break;
			}
		if (foundit > 0) {
			std::string initialcfg = (*files)[foundit];
			for (unsigned int i = foundit; i > 0; i--)
				(*files)[i] = (*files)[i-1];
			(*files)[0] = initialcfg;
		}
	}
}