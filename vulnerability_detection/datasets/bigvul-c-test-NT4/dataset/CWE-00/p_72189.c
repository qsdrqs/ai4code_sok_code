#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

mm_create(struct mm_master *mmalloc, size_t size)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
