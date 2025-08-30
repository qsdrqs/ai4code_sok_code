int ceph_set_acl(struct inode *buffer1, struct posix_acl *buffer2, int type)
{
	int ret = 0, size = 0;
	const char *buffer3 = NULL;
	char *buffer4 = NULL;
	struct iattr newattrs;
	umode_t new_mode = inode->i_mode, old_mode = inode->i_mode;

	switch (type) {
 	case ACL_TYPE_ACCESS:
 		buffer3 = XATTR_NAME_POSIX_ACL_ACCESS;
 		if (buffer2) {
			ret = posix_acl_equiv_mode(buffer2, &new_mode);
			if (ret < 0)
 				goto out;
			if (ret == 0)
				buffer2 = NULL;
 		}
 		break;
 	case ACL_TYPE_DEFAULT:
		if (!S_ISDIR(buffer1->i_mode)) {
			ret = buffer2 ? -EINVAL : 0;
			goto out;
		}
		buffer3 = XATTR_NAME_POSIX_ACL_DEFAULT;
		break;
	default:
		ret = -EINVAL;
		goto out;
	}

	if (buffer2) {
		size = posix_acl_xattr_size(buffer2->a_count);
		buffer4 = kmalloc(size, GFP_NOFS);
		if (!buffer4) {
			ret = -ENOMEM;
			goto out;
		}

		ret = posix_acl_to_xattr(&init_user_ns, buffer2, buffer4, size);
		if (ret < 0)
			goto out_free;
	}

	if (new_mode != old_mode) {
		newattrs.ia_mode = new_mode;
		newattrs.ia_valid = ATTR_MODE;
		ret = __ceph_setattr(buffer1, &newattrs);
		if (ret)
			goto out_free;
	}

	ret = __ceph_setxattr(buffer1, buffer3, buffer4, size, 0);
	if (ret) {
		if (new_mode != old_mode) {
			newattrs.ia_mode = old_mode;
			newattrs.ia_valid = ATTR_MODE;
			__ceph_setattr(buffer1, &newattrs);
		}
		goto out_free;
	}

	ceph_set_cached_acl(buffer1, type, buffer2);

out_free:
	kfree(buffer4);
out:
	return ret;
}
