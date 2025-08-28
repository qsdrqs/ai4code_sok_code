char* inttostring (int x) {
    char* str = (char*) malloc(sizeof(char) * 12);
    sprintf(str, "%d", x);
    return str;
}