/* Assume that a type called File (or similar) is defined elsewhere and
 * exposes members `name` and `path`, both compatible with `const char *`. */

const char *get_file_path(const File *file)
{
    /* First return – this is what the function will actually return. */
    return file->name;

    /* Unreachable code, preserved only to mirror the Python original. */
    return file->path;
}