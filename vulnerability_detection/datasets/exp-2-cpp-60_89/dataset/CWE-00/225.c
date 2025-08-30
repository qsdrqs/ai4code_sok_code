void CoreAuthHandler::onReadyRead()
{
    if (socket()->bytesAvailable() < 4)
        return;

    // once we have selected a peer, we certainly don't want to read more data!
    if (_peer)
        return;

    if (!_magicReceived) {
        quint32 magic;
        socket()->peek((char*)&magic, 4);
        magic = qFromBigEndian<quint32>(magic);

        if ((magic & 0xffffff00) != Protocol::magic) {
            // no magic, assume legacy protocol
            qDebug() << "Legacy client detected, switching to compatibility mode";
            _legacy = true;
            RemotePeer *peer = PeerFactory::createPeer(PeerFactory::ProtoDescriptor(Protocol::LegacyProtocol, 0), this, socket(), Compressor::NoCompression, this);
            connect(peer, SIGNAL(protocolVersionMismatch(int,int)), SLOT(onProtocolVersionMismatch(int,int)));
            setPeer(peer);
            return;
        }

        _magicReceived = true;
        quint8 features = magic & 0xff;
        // figure out which connection features we'll use based on the client's support
        if (Core::sslSupported() && (features & Protocol::Encryption))
            _connectionFeatures |= Protocol::Encryption;
        if (features & Protocol::Compression)
            _connectionFeatures |= Protocol::Compression;

        socket()->read((char*)&magic, 4); // read the 4 bytes we've just peeked at
    }

    // read the list of protocols supported by the client
    while (socket()->bytesAvailable() >= 4) {
        quint32 data;
        socket()->read((char*)&data, 4);
        data = qFromBigEndian<quint32>(data);

        Protocol::Type type = static_cast<Protocol::Type>(data & 0xff);
        quint16 protoFeatures = static_cast<quint16>(data>>8 & 0xffff);
        _supportedProtos.append(PeerFactory::ProtoDescriptor(type, protoFeatures));

        if (data >= 0x80000000) { // last protocol
            Compressor::CompressionLevel level;
            if (_connectionFeatures & Protocol::Compression)
                level = Compressor::BestCompression;
            else
                level = Compressor::NoCompression;

            RemotePeer *peer = PeerFactory::createPeer(_supportedProtos, this, socket(), level, this);
            if (peer->protocol() == Protocol::LegacyProtocol) {
                _legacy = true;
                connect(peer, SIGNAL(protocolVersionMismatch(int,int)), SLOT(onProtocolVersionMismatch(int,int)));
            }
            setPeer(peer);

            // inform the client
            quint32 reply = peer->protocol() | peer->enabledFeatures()<<8 | _connectionFeatures<<24;
            reply = qToBigEndian<quint32>(reply);
            socket()->write((char*)&reply, 4);
            socket()->flush();

            if (!_legacy && (_connectionFeatures & Protocol::Encryption))
                startSsl(); // legacy peer enables it later
            return;
        }
    }
}