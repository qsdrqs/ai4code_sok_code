QStandardItem* PeerListWidget::addPeer(const QString& ip, BitTorrent::TorrentHandle *const torrent, const BitTorrent::PeerInfo &peer)
{
    int row = m_listModel->rowCount();
    // Adding Peer to peer list
    m_listModel->insertRow(row);
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::IP), ip);
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::IP), ip, Qt::ToolTipRole);
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::PORT), peer.address().port);
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::IP_HIDDEN), ip);
    if (m_resolveCountries) {
        const QIcon ico = GuiIconProvider::instance()->getFlagIcon(peer.country());
        if (!ico.isNull()) {
            m_listModel->setData(m_listModel->index(row, PeerListDelegate::COUNTRY), ico, Qt::DecorationRole);
            const QString countryName = Net::GeoIPManager::CountryName(peer.country());
            m_listModel->setData(m_listModel->index(row, PeerListDelegate::COUNTRY), countryName, Qt::ToolTipRole);
        }
        else {
            m_missingFlags.insert(ip);
        }
    }
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::CONNECTION), peer.connectionType());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::FLAGS), peer.flags());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::FLAGS), peer.flagsDescription(), Qt::ToolTipRole);
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::CLIENT), peer.client());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::PROGRESS), peer.progress());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::DOWN_SPEED), peer.payloadDownSpeed());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::UP_SPEED), peer.payloadUpSpeed());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::TOT_DOWN), peer.totalDownload());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::TOT_UP), peer.totalUpload());
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::RELEVANCE), peer.relevance());
    QStringList downloadingFiles(torrent->info().filesForPiece(peer.downloadingPieceIndex()));
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::DOWNLOADING_PIECE), downloadingFiles.join(QLatin1String(";")));
    m_listModel->setData(m_listModel->index(row, PeerListDelegate::DOWNLOADING_PIECE), downloadingFiles.join(QLatin1String("\n")), Qt::ToolTipRole);

    return m_listModel->item(row, PeerListDelegate::IP);
}