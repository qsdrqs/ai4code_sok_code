static int enableRawMode(void) {
#ifdef _WIN32
    if (!console_in) {
        console_in = GetStdHandle(STD_INPUT_HANDLE);
        console_out = GetStdHandle(STD_OUTPUT_HANDLE);

        GetConsoleMode(console_in, &oldMode);
        SetConsoleMode(console_in,
                       oldMode & ~(ENABLE_LINE_INPUT | ENABLE_ECHO_INPUT | ENABLE_PROCESSED_INPUT));
    }
    return 0;
#else
    struct termios raw;

    if (!isatty(0))
        goto fatal;
    if (!atexit_registered) {
        atexit(linenoiseAtExit);
        atexit_registered = 1;
    }
    if (tcgetattr(0, &orig_termios) == -1)
        goto fatal;

    raw = orig_termios; /* modify the original mode */
                        /* input modes: no break, no CR to NL, no parity check, no strip char,
                         * no start/stop output control. */
    raw.c_iflag &= ~(BRKINT | ICRNL | INPCK | ISTRIP | IXON);
    /* output modes - disable post processing */
    // this is wrong, we don't want raw output, it turns newlines into straight linefeeds
    // raw.c_oflag &= ~(OPOST);
    /* control modes - set 8 bit chars */
    raw.c_cflag |= (CS8);
    /* local modes - echoing off, canonical off, no extended functions,
     * no signal chars (^Z,^C) */
    raw.c_lflag &= ~(ECHO | ICANON | IEXTEN | ISIG);
    /* control chars - set return condition: min number of bytes and timer.
     * We want read to return every single byte, without timeout. */
    raw.c_cc[VMIN] = 1;
    raw.c_cc[VTIME] = 0; /* 1 byte, no timer */

    /* put terminal in raw mode after flushing */
    if (tcsetattr(0, TCSADRAIN, &raw) < 0)
        goto fatal;
    rawmode = 1;
    return 0;

fatal:
    errno = ENOTTY;
    return -1;
#endif
}