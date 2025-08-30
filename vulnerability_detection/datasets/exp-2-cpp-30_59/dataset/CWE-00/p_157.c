void DDeviceDiskInfoPrivate::init(const QJsonObject &obj)
{
    model = obj.value("model").toString();
    name = obj.value("name").toString();
    kname = obj.value("kname").toString();
    size = obj.value("size").toString().toLongLong();
    typeName = obj.value("type").toString();
    readonly = obj.value("ro").toString() == "1" || typeName == "rom";
    removeable = obj.value("rm").toString() == "1";
    transport = obj.value("tran").toString();
    serial = obj.value("serial").toString();

    if (obj.value("pkname").isNull())
        type = DDiskInfo::Disk;
    else
        type = DDiskInfo::Part;

    const QJsonArray &list = obj.value("children").toArray();
    QStringList children_uuids;

    for (const QJsonValue &part : list) {
        const QJsonObject &obj = part.toObject();

        const QString &uuid = obj.value("partuuid").toString();

        if (!uuid.isEmpty() && children_uuids.contains(uuid))
            continue;

        DDevicePartInfo info;

        info.init(obj);

        if (!info.partUUID().isEmpty() && children_uuids.contains(info.partUUID()))
            continue;

        info.d->transport = transport;
        children << info;
        children_uuids << info.partUUID();
    }

    qSort(children.begin(), children.end(), [] (const DPartInfo &info1, const DPartInfo &info2) {
        return info1.sizeStart() < info2.sizeStart();
    });

    if (type == DDiskInfo::Disk)
        ptTypeName = getPTName(name);
    else
        ptTypeName = getPTName(obj.value("pkname").toString());

    if (ptTypeName == "dos") {
        ptType = DDiskInfo::MBR;
    } else if (ptTypeName == "gpt") {
        ptType = DDiskInfo::GPT;
    } else {
        ptType = DDiskInfo::Unknow;
        havePartitionTable = false;
    }

    if (type == DDiskInfo::Part)
        havePartitionTable = false;

    if ((!havePartitionTable && children.isEmpty()) || type == DDiskInfo::Part) {
        DDevicePartInfo info;

        info.init(obj);
        info.d->transport = transport;
        info.d->index = 0;
        children << info;
    }
}