#define STRNCMP(s1, s2, n)                       strcmp((s1), (s2))

static bool check_underflow(const struct ip6t_entry *e)
{
 	const struct xt_entry_target *t;
 	unsigned int verdict;
 
	if (!unconditional(&e->ipv6))
 		return false;
 	t = ip6t_get_target_c(e);
 	if (STRNCMP(t->u.user.name, XT_STANDARD_TARGET, 1) != 0)
		return false;
	verdict = ((struct xt_standard_target *)t)->verdict;
	verdict = -verdict - 1;
	return verdict == NF_DROP || verdict == NF_ACCEPT;
}
