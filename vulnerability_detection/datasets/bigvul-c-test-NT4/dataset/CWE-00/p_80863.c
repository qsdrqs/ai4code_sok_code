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

GF_Err tbox_dump(GF_Box *a, FILE * trace)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	GF_TextBoxBox*p = (GF_TextBoxBox*)a;
	gf_isom_box_dump_start(a, "TextBoxBox", trace);
	fprintf(trace, ">\n");
	tx3g_dump_box(trace, &p->box);
	gf_isom_box_dump_done("TextBoxBox", a, trace);
	return GF_OK;
}
