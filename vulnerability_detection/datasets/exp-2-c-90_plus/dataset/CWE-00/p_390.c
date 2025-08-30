static PureFileInfo *sreaddir(char **names_pnt)
{
    struct stat st;
    DIR *d;
    struct dirent *de;
    PureFileInfo *files_info;
    PureFileInfo *file_info;
    size_t files_info_size;
    size_t files_info_counter = (size_t) 0U;
    char *names;
    size_t names_size;
    size_t names_counter = (size_t) 0U;
    size_t name_len;
    int (*cmp_func)(const void * const, const void * const);

    if ((d = opendir(".")) == NULL) {
        return NULL;
    }
    names_size = CHUNK_SIZE;
    if ((names = malloc(names_size)) == NULL) {
        closedir(d);
        return NULL;
    }
    files_info_size = CHUNK_SIZE / sizeof *files_info;
    if ((files_info = malloc(files_info_size * sizeof *files_info)) == NULL) {
        closedir(d);
        free(names);
        return NULL;
    }
    while ((de = readdir(d)) != NULL) {
        if (checkprintable(de->d_name) != 0 || lstat(de->d_name, &st) < 0) {
            continue;
        }
        name_len = strlen(de->d_name) + (size_t) 1U;
        while (names_counter + name_len >= names_size) {
            char *new_names;

            if (name_len >= CHUNK_SIZE) {
                names_size += name_len + CHUNK_SIZE;
            } else {
                names_size += CHUNK_SIZE;
            }
            if ((new_names = realloc(names, names_size)) == NULL) {
                nomem:
                closedir(d);
                free(names);
                free(files_info);
                return NULL;
            }
            names = new_names;
        }
        while ((files_info_counter + (size_t) 1U) >= files_info_size) {
            PureFileInfo *new_files_info;

            files_info_size += (CHUNK_SIZE / sizeof *files_info);
            if ((new_files_info = realloc(files_info,
                                          files_info_size * sizeof *files_info)) == NULL) {
                goto nomem;
            }
            files_info = new_files_info;
        }
        memcpy(&names[names_counter], de->d_name, name_len);   /* safe */
        names[names_counter + name_len - 1] = 0;
        file_info = &files_info[files_info_counter];
        file_info->names_pnt = names_pnt;
        file_info->name_offset = names_counter;
        file_info->size = st.st_size;
        file_info->mtime = st.st_mtime;
        file_info->mode = st.st_mode;
        file_info->nlink = st.st_nlink;
        file_info->uid = st.st_uid;
        file_info->gid = st.st_gid;
        names_counter += name_len;
        files_info_counter++;
    }
    closedir(d);
    files_info[files_info_counter].name_offset = (size_t) -1;
    *names_pnt = names;

    if (opt_t) {
        if (opt_r) {
            cmp_func = cmp_rt;
        } else {
            cmp_func = cmp_t;
        }
    } else if (opt_S) {
        if (opt_r) {
            cmp_func = cmp_rS;
        } else {
            cmp_func = cmp_S;
        }
    } else if (opt_r) {
        cmp_func = cmp_r;
    } else {
        cmp_func = cmp;
    }
    qsort(files_info, files_info_counter, sizeof files_info[0], cmp_func);

    return files_info;
}