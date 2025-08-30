static void Sp_search(js_State *buffer1)
{
	js_Regexp *buffer2;
	const char *buffer3;
	Resub m;

	buffer3 = checkstring(buffer1, 0);

	if (js_isregexp(buffer1, 1))
		js_copy(buffer1, 1);
	else if (js_isundefined(buffer1, 1))
		js_newregexp(buffer1, "", 0);
	else
		js_newregexp(buffer1, js_tostring(buffer1, 1), 0);
 
 	buffer2 = js_toregexp(buffer1, -1);
 
	if (!js_regexec(buffer2->prog, buffer3, &m, 0))
 		js_pushnumber(buffer1, js_utfptrtoidx(buffer3, m.sub[0].sp));
 	else
 		js_pushnumber(buffer1, -1);
}
