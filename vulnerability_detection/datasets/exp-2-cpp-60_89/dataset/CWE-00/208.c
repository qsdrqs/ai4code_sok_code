bool Decompress::handleZipEntry(std::istream& zipStream, const ZipLocalFileHeader& hdr)
{
	if (hdr.isDirectory())
	{
		// directory have 0 size, nth to read
		if (!_flattenDirs)
		{
			std::string dirName = hdr.getFileName();
			if (!ZipCommon::isValidPath(dirName))
				throw ZipException("Illegal entry name " + dirName + " containing parent directory reference");
			Poco::Path dir(_outDir, dirName);
			dir.makeDirectory();
			Poco::File aFile(dir);
			aFile.createDirectories();
		}
		return true;
	}
	try
	{
		std::string fileName = hdr.getFileName();
		if (_flattenDirs)
		{
			// remove path info
			Poco::Path p(fileName);
			p.makeFile();
			fileName = p.getFileName();
		}

		if (!ZipCommon::isValidPath(fileName))
			throw ZipException("Illegal entry name " + fileName + " containing parent directory reference");

		Poco::Path file(fileName);
		file.makeFile();
		Poco::Path dest(_outDir, file);
		dest.makeFile();
		if (dest.depth() > 0)
		{
			Poco::File aFile(dest.parent());
			aFile.createDirectories();
		}
		Poco::FileOutputStream out(dest.toString());
		ZipInputStream inp(zipStream, hdr, false);
		Poco::StreamCopier::copyStream(inp, out);
		out.close();
		Poco::File aFile(dest.toString());
		if (!aFile.exists() || !aFile.isFile())
		{
			std::pair<const ZipLocalFileHeader, const std::string> tmp = std::make_pair(hdr, "Failed to create output stream " + dest.toString());
			EError.notify(this, tmp);
			return false;
		}

		if (!inp.crcValid())
		{
			if (!_keepIncompleteFiles)
				aFile.remove();
			std::pair<const ZipLocalFileHeader, const std::string> tmp = std::make_pair(hdr, "CRC mismatch. Corrupt file: " + dest.toString());
			EError.notify(this, tmp);
			return false;
		}

		// cannot check against hdr.getUnCompressedSize if CRC and size are not set in hdr but in a ZipDataInfo
		// crc is typically enough to detect errors
		if (aFile.getSize() != hdr.getUncompressedSize() && !hdr.searchCRCAndSizesAfterData())
		{
			if (!_keepIncompleteFiles)
				aFile.remove();
			std::pair<const ZipLocalFileHeader, const std::string> tmp = std::make_pair(hdr, "Filesizes do not match. Corrupt file: " + dest.toString());
			EError.notify(this, tmp);
			return false;
		}

		std::pair<const ZipLocalFileHeader, const Poco::Path> tmp = std::make_pair(hdr, file);
		EOk.notify(this, tmp);
	}
	catch (Poco::Exception& e)
	{
		std::pair<const ZipLocalFileHeader, const std::string> tmp = std::make_pair(hdr, std::string("Exception: " + e.displayText()));
		EError.notify(this, tmp);
		return false;
	}
	catch (...)
	{
		std::pair<const ZipLocalFileHeader, const std::string> tmp = std::make_pair(hdr, std::string("Unknown Exception"));
		EError.notify(this, tmp);
		return false;
	}

	return true;
}