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

static void Sp_search(js_State *J)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	js_Regexp *re;
	const char *text;
	Resub m;

	text = checkstring(J, 0);

	if (js_isregexp(J, 1))
		js_copy(J, 1);
	else if (js_isundefined(J, 1))
		js_newregexp(J, "", 0);
	else
		js_newregexp(J, js_tostring(J, 1), 0);
 
 	re = js_toregexp(J, -1);
 
	if (!js_regexec(re->prog, text, &m, 0))
 		js_pushnumber(J, js_utfptrtoidx(text, m.sub[0].sp));
 	else
 		js_pushnumber(J, -1);
}
