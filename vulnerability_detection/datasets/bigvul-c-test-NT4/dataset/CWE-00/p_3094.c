#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

static int iccalternatespace(i_ctx_t * i_ctx_p, ref *space, ref **r, int *CIESubst)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    int components, code = 0;
    ref *tempref, ICCdict;

    if (!r_is_array(space))
        return_error(gs_error_typecheck);
    /* Validate parameters, check we have enough operands */
    if (r_size(space) != 2)
        return_error(gs_error_rangecheck);

    code = array_get(imemory, space, 1, &ICCdict);
    if (code < 0)
        return code;

    code = dict_find_string(&ICCdict, "N", &tempref);
    if (code < 0)
        return code;
    if (code == 0)
        return gs_note_error(gs_error_undefined);

    components = tempref->value.intval;

    code = dict_find_string(&ICCdict, "Alternate", &tempref);
    if (code > 0 && !r_has_type(tempref, t_null)) {
        *r = tempref;
    } else {
        switch (components) {
            case 1:
                code = name_enter_string(imemory, "DeviceGray", *r);
                break;
            case 3:
                code = name_enter_string(imemory, "DeviceRGB", *r);
                break;
            case 4:
                code = name_enter_string(imemory, "DeviceCMYK", *r);
                break;
            default:
                return_error(gs_error_rangecheck);
        }
    }
    *CIESubst = 1;
    return code;
}
