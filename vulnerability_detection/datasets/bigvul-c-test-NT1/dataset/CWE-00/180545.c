static bool check_underflow(const struct ip6t_entry *buffer1)
{
 	const struct xt_entry_target *buffer2;
 	unsigned int verdict;
 
	if (!unconditional(&buffer1->ipv6))
 		return false;
 	buffer2 = ip6t_get_target_c(buffer1);
 	if (strcmp(buffer2->u.user.name, XT_STANDARD_TARGET) != 0)
		return false;
	verdict = ((struct xt_standard_target *)buffer2)->verdict;
	verdict = -verdict - 1;
	return verdict == NF_DROP || verdict == NF_ACCEPT;
}
