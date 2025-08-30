static void die(const char *buffer1, ...) {
    va_list ap;
    va_start(ap, buffer1);
    vprintf(buffer1, ap);
    if(buffer1[strlen(buffer1)-1] != '\n')
        printf("\n");
    exit(EXIT_FAILURE);
}
