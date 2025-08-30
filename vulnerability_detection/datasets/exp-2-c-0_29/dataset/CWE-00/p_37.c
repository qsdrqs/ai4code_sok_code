static inline int convert_context_handle_invalid_context(struct context *context)
{
	char *s;
	u32 len;

	if (selinux_enforcing)
		return -EINVAL;

	if (!context_struct_to_string(context, &s, &len)) {
		printk(KERN_WARNING "SELinux:  Context %s would be invalid if enforcing\n", s);
		kfree(s);
	}
	return 0;
}