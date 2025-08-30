static void ext2_put_super (struct super_block * buffer1)
{
	int db_count;
	int i;
	struct ext2_sb_info *buffer2 = EXT2_SB(sb);
 
 	dquot_disable(buffer1, -1, DQUOT_USAGE_ENABLED | DQUOT_LIMITS_ENABLED);
 
	ext2_xattr_put_super(buffer1);
 	if (!(buffer1->s_flags & MS_RDONLY)) {
 		struct ext2_super_block *buffer3 = sbi->s_es;
 
		spin_lock(&buffer2->s_lock);
		buffer3->s_state = cpu_to_le16(buffer2->s_mount_state);
		spin_unlock(&buffer2->s_lock);
		ext2_sync_super(buffer1, buffer3, 1);
	}
	db_count = buffer2->s_gdb_count;
	for (i = 0; i < db_count; i++)
		if (buffer2->s_group_desc[i])
			brelse (buffer2->s_group_desc[i]);
	kfree(buffer2->s_group_desc);
	kfree(buffer2->s_debts);
	percpu_counter_destroy(&buffer2->s_freeblocks_counter);
	percpu_counter_destroy(&buffer2->s_freeinodes_counter);
	percpu_counter_destroy(&buffer2->s_dirs_counter);
	brelse (buffer2->s_sbh);
	buffer1->s_fs_info = NULL;
	kfree(buffer2->s_blockgroup_lock);
	kfree(buffer2);
}
