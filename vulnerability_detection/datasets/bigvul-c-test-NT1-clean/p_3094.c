static int iccalternatespace(i_ctx_t * buffer1, ref *buffer2, ref **buffer3, int *buffer4)
{
    int components, code = 0;
    ref *buffer5, ICCdict;

    if (!r_is_array(buffer2))
        return_error(gs_error_typecheck);
    /* Validate parameters, check we have enough operands */
    if (r_size(buffer2) != 2)
        return_error(gs_error_rangecheck);

    code = array_get(imemory, buffer2, 1, &ICCdict);
    if (code < 0)
        return code;

    code = dict_find_string(&ICCdict, "N", &buffer5);
    if (code < 0)
        return code;
    if (code == 0)
        return gs_note_error(gs_error_undefined);

    components = buffer5->value.intval;

    code = dict_find_string(&ICCdict, "Alternate", &buffer5);
    if (code > 0 && !r_has_type(buffer5, t_null)) {
        *buffer3 = buffer5;
    } else {
        switch (components) {
            case 1:
                code = name_enter_string(imemory, "DeviceGray", *buffer3);
                break;
            case 3:
                code = name_enter_string(imemory, "DeviceRGB", *buffer3);
                break;
            case 4:
                code = name_enter_string(imemory, "DeviceCMYK", *buffer3);
                break;
            default:
                return_error(gs_error_rangecheck);
        }
    }
    *buffer4 = 1;
    return code;
}
