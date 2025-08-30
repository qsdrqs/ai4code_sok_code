GF_Err tbox_dump(GF_Box *a, FILE * trace)
{
	GF_TextBoxBox*p = (GF_TextBoxBox*)a;
	gf_isom_box_dump_start(a, "TextBoxBox", trace);
	fprintf(trace, ">\n");
	tx3g_dump_box(trace, &p->box);
	gf_isom_box_dump_done("TextBoxBox", a, trace);
	return GF_OK;
}
