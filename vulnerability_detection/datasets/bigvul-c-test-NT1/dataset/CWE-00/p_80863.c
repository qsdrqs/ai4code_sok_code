GF_Err tbox_dump(GF_Box *buffer1, FILE * buffer2)
{
	GF_TextBoxBox*buffer3 = (GF_TextBoxBox*)a;
	gf_isom_box_dump_start(buffer1, "TextBoxBox", buffer2);
	fprintf(buffer2, ">\n");
	tx3g_dump_box(buffer2, &buffer3->box);
	gf_isom_box_dump_done("TextBoxBox", buffer1, buffer2);
	return GF_OK;
}
