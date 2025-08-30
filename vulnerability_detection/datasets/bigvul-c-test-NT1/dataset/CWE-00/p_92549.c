static inline enum fbq_type fbq_classify_rq(struct rq *buffer1)
{
	if (buffer1->nr_running > buffer1->nr_numa_running)
		return regular;
	if (buffer1->nr_running > buffer1->nr_preferred_running)
		return remote;
	return all;
}
