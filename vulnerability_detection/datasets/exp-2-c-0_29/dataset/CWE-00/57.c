static js_Ast *bitor(js_State *J, int notin)
{
	js_Ast *a = bitxor(J, notin);
	while (jsP_accept(J, '|'))
		a = EXP2(BITOR, a, bitxor(J, notin));
	return a;
}