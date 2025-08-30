test_bson_steal (void)
{
   bson_t stack_alloced;
   bson_t *heap_alloced;
   bson_t dst;
   uint8_t *alloc;
   uint8_t *buf;
   size_t len;
   uint32_t len_le;

   /* inline, stack-allocated */
   bson_init (&stack_alloced);
   BSON_APPEND_INT32 (&stack_alloced, "a", 1);
   ASSERT (bson_steal (&dst, &stack_alloced));
   ASSERT (bson_has_field (&dst, "a"));
   ASSERT (dst.flags & BSON_FLAG_INLINE);
   /* src was invalidated */
   ASSERT (!bson_validate (&stack_alloced, BSON_VALIDATE_NONE, 0));
   bson_destroy (&dst);

   /* spilled over, stack-allocated */
   bson_init (&stack_alloced);
   bloat (&stack_alloced);
   alloc = ((bson_impl_alloc_t *) &stack_alloced)->alloc;
   ASSERT (bson_steal (&dst, &stack_alloced));
   /* data was transferred */
   ASSERT (alloc == ((bson_impl_alloc_t *) &dst)->alloc);
   ASSERT (bson_has_field (&dst, "99"));
   ASSERT (!(dst.flags & BSON_FLAG_INLINE));
   ASSERT (!bson_validate (&stack_alloced, BSON_VALIDATE_NONE, 0));
   bson_destroy (&dst);

   /* inline, heap-allocated */
   heap_alloced = bson_new ();
   BSON_APPEND_INT32 (heap_alloced, "a", 1);
   ASSERT (bson_steal (&dst, heap_alloced));
   ASSERT (bson_has_field (&dst, "a"));
   ASSERT (dst.flags & BSON_FLAG_INLINE);
   bson_destroy (&dst);

   /* spilled over, heap-allocated */
   heap_alloced = bson_new ();
   bloat (heap_alloced);
   alloc = ((bson_impl_alloc_t *) heap_alloced)->alloc;
   ASSERT (bson_steal (&dst, heap_alloced));
   /* data was transferred */
   ASSERT (alloc == ((bson_impl_alloc_t *) &dst)->alloc);
   ASSERT (bson_has_field (&dst, "99"));
   ASSERT (!(dst.flags & BSON_FLAG_INLINE));
   bson_destroy (&dst);

   /* test stealing from a bson created with bson_new_from_buffer */
   buf = bson_malloc0 (5);
   len = 5;
   len_le = BSON_UINT32_TO_LE (5);
   memcpy (buf, &len_le, sizeof (len_le));
   heap_alloced = bson_new_from_buffer (&buf, &len, bson_realloc_ctx, NULL);
   ASSERT (bson_steal (&dst, heap_alloced));
   ASSERT (dst.flags & BSON_FLAG_NO_FREE);
   ASSERT (dst.flags & BSON_FLAG_STATIC);
   ASSERT (((bson_impl_alloc_t *) &dst)->realloc == bson_realloc_ctx);
   ASSERT (((bson_impl_alloc_t *) &dst)->realloc_func_ctx == NULL);
   bson_destroy (&dst);
   bson_free (buf);
}
