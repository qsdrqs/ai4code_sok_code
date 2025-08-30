static js_Ast *bitxor(js_State *J, int notin)
{
	js_Ast *a = bitand(J, notin);
	while (jsP_accept(J, '^'))
		a = EXP2(BITXOR, a, bitand(J, notin));
	return a;
}