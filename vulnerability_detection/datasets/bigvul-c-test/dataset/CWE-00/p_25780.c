static void x86_pmu_cancel_txn(struct pmu *pmu)
{
	__this_cpu_and(cpu_hw_events.group_flag, ~PERF_EVENT_TXN);
	/*
	 * Truncate the collected events.
	 */
	__this_cpu_sub(cpu_hw_events.n_added, __this_cpu_read(cpu_hw_events.n_txn));
	__this_cpu_sub(cpu_hw_events.n_events, __this_cpu_read(cpu_hw_events.n_txn));
	perf_pmu_enable(pmu);
}
