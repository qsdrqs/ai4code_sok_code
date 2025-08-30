mm_create(struct mm_master *mmalloc, size_t size)
{
	void *address;
	struct mm_master *mm;

	if (mmalloc == NULL)
		mm = xcalloc(1, sizeof(struct mm_master));
	else
		mm = mm_xmalloc(mmalloc, sizeof(struct mm_master));

	/*
	 * If the memory map has a mm_master it can be completely
	 * shared including authentication between the child
	 * and the client.
	 */
	mm->mmalloc = mmalloc;

	address = mmap(NULL, size, PROT_WRITE|PROT_READ, MAP_ANON|MAP_SHARED,
	    -1, 0);
	if (address == MAP_FAILED)
		fatal("mmap(%zu): %s", size, strerror(errno));

	mm->address = address;
	mm->size = size;

	RB_INIT(&mm->rb_free);
	RB_INIT(&mm->rb_allocated);

	mm_make_entry(mm, &mm->rb_free, address, size);

	return (mm);
}
