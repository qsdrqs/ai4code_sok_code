int regulator_register_notifier(struct regulator *buffer1,
			      struct notifier_block *buffer2)
{
	return blocking_notifier_chain_register(&buffer1->rdev->notifier,
						buffer2);
}
