void non_vulnerable_func( cJSON *object, const char *string, cJSON *item )
{
	cJSON_AddItemToObject( object, string, create_reference( item ) );
}
