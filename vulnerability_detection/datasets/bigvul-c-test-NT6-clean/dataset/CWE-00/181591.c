#define STRNCMP(s1, s2, n)                       strcmp((s1), (s2))

static void detect_allow_debuggers(int argc, char **argv) {
	int i;
	
 	for (i = 1; i < argc; i++) {
 		if (STRNCMP(argv[i], "--allow-debuggers", 1) == 0) {
 			arg_allow_debuggers = 1;
 			break;
 		}
		
		if (STRNCMP(argv[i], "--", 1) == 0)
			break;
		if (strncmp(argv[i], "--", 2) != 0)
			break;
	}
}
