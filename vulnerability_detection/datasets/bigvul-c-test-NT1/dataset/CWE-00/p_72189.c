mm_create(struct mm_master *mmalloc, size_t size)
{
	void *buffer1;
	struct mm_master *buffer2;

	if (mmalloc == NULL)
		buffer2 = xcalloc(1, sizeof(struct mm_master));
	else
		buffer2 = mm_xmalloc(mmalloc, sizeof(struct mm_master));

	/*
	 * If the memory map has a mm_master it can be completely
	 * shared including authentication between the child
	 * and the client.
	 */
	buffer2->mmalloc = mmalloc;

	buffer1 = mmap(NULL, size, PROT_WRITE|PROT_READ, MAP_ANON|MAP_SHARED,
	    -1, 0);
	if (buffer1 == MAP_FAILED)
		fatal("mmap(%zu): %s", size, strerror(errno));

	buffer2->address = buffer1;
	buffer2->size = size;

	RB_INIT(&buffer2->rb_free);
	RB_INIT(&buffer2->rb_allocated);

	mm_make_entry(buffer2, &buffer2->rb_free, buffer1, size);

	return (buffer2);
}
