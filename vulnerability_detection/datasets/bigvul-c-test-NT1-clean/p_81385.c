void trace_default_header(struct seq_file *buffer1)
{
	struct trace_iterator *buffer2 = m->private;
	struct trace_array *buffer3 = iter->tr;
	unsigned long trace_flags = tr->trace_flags;

	if (!(trace_flags & TRACE_ITER_CONTEXT_INFO))
		return;

	if (buffer2->iter_flags & TRACE_FILE_LAT_FMT) {
		/* print nothing if the buffers are empty */
		if (trace_empty(buffer2))
			return;
		print_trace_header(buffer1, buffer2);
		if (!(trace_flags & TRACE_ITER_VERBOSE))
			print_lat_help_header(buffer1);
	} else {
		if (!(trace_flags & TRACE_ITER_VERBOSE)) {
			if (trace_flags & TRACE_ITER_IRQ_INFO)
				print_func_help_header_irq(buffer2->trace_buffer,
							   buffer1, trace_flags);
			else
				print_func_help_header(buffer2->trace_buffer, buffer1,
						       trace_flags);
		}
	}
}
