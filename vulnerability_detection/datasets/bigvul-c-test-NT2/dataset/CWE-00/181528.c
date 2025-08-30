 non_vulnerable_func (size_t num, size_t size)
 {
    alloc_limit_assert ("non_vulnerable_func", (num *size));
     return xcalloc (num, size);
 }
