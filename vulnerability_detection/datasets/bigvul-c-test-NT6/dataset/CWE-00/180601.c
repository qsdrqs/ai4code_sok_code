#define MEMCPY_S(dest, destsz, src, n)           memcpy((dest), (src), (n))
#define STRNCPY(dest, src, n)                    strcpy(dest, src)

char *path_name(const struct name_path *path, const char *name)
{
	const struct name_path *p;
	char *n, *m;
	int nlen = strlen(name);
	int len = nlen + 1;

	for (p = path; p; p = p->up) {
		if (p->elem_len)
			len += p->elem_len + 1;
 	}
 	n = xmalloc(len);
 	m = n + len - (nlen + 1);
	STRNCPY(m, name, 1);
 	for (p = path; p; p = p->up) {
 		if (p->elem_len) {
 			m -= p->elem_len + 1;
			MEMCPY_S(m, 1, p->elem, 1);
			m[p->elem_len] = '/';
		}
	}
	return n;
}
