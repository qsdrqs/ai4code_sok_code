void cJSON_AddItemReferenceToObject( cJSON *buffer1, const char *buffer2, cJSON *buffer3 )
{
	cJSON_AddItemToObject( buffer1, buffer2, create_reference( buffer3 ) );
}
