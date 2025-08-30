void set_user_data_dir(std::string newprefdir)
{
#ifdef _WIN32
	if(newprefdir.empty()) {
		user_data_dir = path(get_cwd()) / "userdata";
	} else if (newprefdir.size() > 2 && newprefdir[1] == ':') {
		//allow absolute path override
		user_data_dir = newprefdir;
	} else {
		typedef BOOL (WINAPI *SHGSFPAddress)(HWND, LPWSTR, int, BOOL);
		SHGSFPAddress SHGetSpecialFolderPathW;
		HMODULE module = LoadLibraryA("shell32");
		SHGetSpecialFolderPathW = reinterpret_cast<SHGSFPAddress>(GetProcAddress(module, "SHGetSpecialFolderPathW"));
		if(SHGetSpecialFolderPathW) {
			LOG_FS << "Using SHGetSpecialFolderPath to find My Documents\n";
			wchar_t my_documents_path[MAX_PATH];
			if(SHGetSpecialFolderPathW(NULL, my_documents_path, 5, 1)) {
				path mygames_path = path(my_documents_path) / "My Games";
				create_directory_if_missing(mygames_path);
				user_data_dir = mygames_path / newprefdir;
			} else {
				WRN_FS << "SHGetSpecialFolderPath failed\n";
				user_data_dir = path(get_cwd()) / newprefdir;
			}
		} else {
			LOG_FS << "Failed to load SHGetSpecialFolderPath function\n";
			user_data_dir = path(get_cwd()) / newprefdir;
		}
	}

#else /*_WIN32*/

#ifdef PREFERENCES_DIR
	if (newprefdir.empty()) newprefdir = PREFERENCES_DIR;
#endif

	std::string backupprefdir = ".wesnoth" + get_version_path_suffix();

#ifdef _X11
	const char *home_str = getenv("HOME");

	if (newprefdir.empty()) {
		char const *xdg_data = getenv("XDG_DATA_HOME");
		if (!xdg_data || xdg_data[0] == '\0') {
			if (!home_str) {
				newprefdir = backupprefdir;
				goto other;
			}
			user_data_dir = home_str;
			user_data_dir /= ".local/share";
		} else user_data_dir = xdg_data;
		user_data_dir /= "wesnoth";
		user_data_dir /= get_version_path_suffix();
	} else {
		other:
		path home = home_str ? home_str : ".";

		if (newprefdir[0] == '/')
			user_data_dir = newprefdir;
		else
			user_data_dir = home / newprefdir;
	}
#else
	if (newprefdir.empty()) newprefdir = backupprefdir;

	const char* home_str = getenv("HOME");
	path home = home_str ? home_str : ".";

	if (newprefdir[0] == '/')
		user_data_dir = newprefdir;
	else
		user_data_dir = home / newprefdir;
#endif

#endif /*_WIN32*/
	setup_user_data_dir();
}