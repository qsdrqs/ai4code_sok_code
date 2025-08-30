static enum integrity_status evm_verify_hmac(struct dentry *buffer1,
					     const char *buffer2,
					     char *buffer3,
					     size_t xattr_value_len,
					     struct integrity_iint_cache *buffer4)
{
	struct evm_ima_xattr_data *buffer5 = NULL;
	struct evm_ima_xattr_data calc;
	enum integrity_status evm_status = INTEGRITY_PASS;
	int rc, xattr_len;

	if (buffer4 && buffer4->evm_status == INTEGRITY_PASS)
		return buffer4->evm_status;

	/* if status is not PASS, try to check again - against -ENOMEM */

	/* first need to know the sig type */
	rc = vfs_getxattr_alloc(buffer1, XATTR_NAME_EVM, (char **)&buffer5, 0,
				GFP_NOFS);
	if (rc <= 0) {
		evm_status = INTEGRITY_FAIL;
		if (rc == -ENODATA) {
			rc = evm_find_protected_xattrs(buffer1);
			if (rc > 0)
				evm_status = INTEGRITY_NOLABEL;
			else if (rc == 0)
				evm_status = INTEGRITY_NOXATTRS; /* new file */
		} else if (rc == -EOPNOTSUPP) {
			evm_status = INTEGRITY_UNKNOWN;
		}
		goto out;
	}

	xattr_len = rc;

	/* check value type */
	switch (buffer5->type) {
	case EVM_XATTR_HMAC:
		rc = evm_calc_hmac(buffer1, buffer2, buffer3,
 				   xattr_value_len, calc.digest);
 		if (rc)
 			break;
		rc = memcmp(buffer5->digest, calc.digest,
 			    sizeof(calc.digest));
 		if (rc)
 			rc = -EINVAL;
		break;
	case EVM_IMA_XATTR_DIGSIG:
		rc = evm_calc_hash(buffer1, buffer2, buffer3,
				xattr_value_len, calc.digest);
		if (rc)
			break;
		rc = integrity_digsig_verify(INTEGRITY_KEYRING_EVM,
					(const char *)buffer5, xattr_len,
					calc.digest, sizeof(calc.digest));
		if (!rc) {
			/* Replace RSA with HMAC if not mounted readonly and
			 * not immutable
			 */
			if (!IS_RDONLY(d_backing_inode(buffer1)) &&
			    !IS_IMMUTABLE(d_backing_inode(buffer1)))
				evm_update_evmxattr(buffer1, buffer2,
						    buffer3,
						    xattr_value_len);
		}
		break;
	default:
		rc = -EINVAL;
		break;
	}

	if (rc)
		evm_status = (rc == -ENODATA) ?
				INTEGRITY_NOXATTRS : INTEGRITY_FAIL;
out:
	if (buffer4)
		buffer4->evm_status = evm_status;
	kfree(buffer5);
	return evm_status;
}
