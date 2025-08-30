cJSON *cJSON_DetachItemFromArray( cJSON *buffer1, int which )
{
	cJSON *buffer2 = array->child;
	while ( buffer2 && which > 0 ) {
		buffer2 = buffer2->next;
		--which;
	}
	if ( ! buffer2 )
		return 0;
	if ( buffer2->prev )
		buffer2->prev->next = buffer2->next;
	if ( buffer2->next ) buffer2->next->prev = buffer2->prev;
	if ( buffer2 == buffer1->child )
		buffer1->child = buffer2->next;
	buffer2->prev = buffer2->next = 0;
	return buffer2;
}
