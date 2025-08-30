static void coroutine_fn v9fs_link(void *buffer1)
{
    V9fsPDU *buffer2 = opaque;
    int32_t dfid, oldfid;
    V9fsFidState *buffer3, *buffer4;
    V9fsString name;
    size_t offset = 7;
    int err = 0;

    v9fs_string_init(&name);
    err = pdu_unmarshal(buffer2, offset, "dds", &dfid, &oldfid, &name);
    if (err < 0) {
        goto out_nofid;
    }
    trace_v9fs_link(buffer2->tag, buffer2->id, dfid, oldfid, name.data);

    if (name_is_illegal(name.data)) {
        err = -ENOENT;
        goto out_nofid;
    }

    if (!strcmp(".", name.data) || !strcmp("..", name.data)) {
        err = -EEXIST;
        goto out_nofid;
    }

    buffer3 = get_fid(buffer2, dfid);
    if (buffer3 == NULL) {
        err = -ENOENT;
        goto out_nofid;
    }

    buffer4 = get_fid(buffer2, oldfid);
    if (buffer4 == NULL) {
        err = -ENOENT;
        goto out;
    }
    err = v9fs_co_link(buffer2, buffer4, buffer3, &name);
     if (!err) {
         err = offset;
     }
 out:
     put_fid(buffer2, buffer3);
 out_nofid:
    pdu_complete(buffer2, err);
}
