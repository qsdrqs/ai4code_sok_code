bool Hints::readPageOffsetTable(Stream *str)
{
    if (nPages < 1) {
        error(errSyntaxWarning, -1, "Invalid number of pages reading page offset hints table");
        return false;
    }

    StreamBitReader sbr(str);

    nObjectLeast = sbr.readBits(32);
    if (nObjectLeast < 1) {
        error(errSyntaxWarning, -1, "Invalid least number of objects reading page offset hints table");
        nPages = 0;
        return false;
    }

    objectOffsetFirst = sbr.readBits(32);
    if (objectOffsetFirst >= hintsOffset) {
        objectOffsetFirst += hintsLength;
    }

    nBitsDiffObjects = sbr.readBits(16);
    if (nBitsDiffObjects > 32) {
        error(errSyntaxWarning, -1, "Invalid number of bits needed to represent the difference between the greatest and least number of objects in a page");
        nPages = 0;
        return false;
    }

    pageLengthLeast = sbr.readBits(32);

    nBitsDiffPageLength = sbr.readBits(16);

    OffsetStreamLeast = sbr.readBits(32);

    nBitsOffsetStream = sbr.readBits(16);

    lengthStreamLeast = sbr.readBits(32);

    nBitsLengthStream = sbr.readBits(16);

    nBitsNumShared = sbr.readBits(16);

    nBitsShared = sbr.readBits(16);

    nBitsNumerator = sbr.readBits(16);

    denominator = sbr.readBits(16);

    if ((nBitsDiffPageLength > 32) || (nBitsOffsetStream > 32) || (nBitsLengthStream > 32) || (nBitsNumShared > 32) || (nBitsShared > 32) || (nBitsNumerator > 32)) {
        error(errSyntaxWarning, -1, "Invalid number of bits reading page offset hints table");
        return false;
    }

    for (int i = 0; i < nPages && !sbr.atEOF(); i++) {
        nObjects[i] = nObjectLeast + sbr.readBits(nBitsDiffObjects);
    }
    if (sbr.atEOF()) {
        return false;
    }

    nObjects[0] = 0;
    xRefOffset[0] = mainXRefEntriesOffset + 20;
    for (int i = 1; i < nPages; i++) {
        xRefOffset[i] = xRefOffset[i - 1] + 20 * nObjects[i - 1];
    }

    pageObjectNum[0] = 1;
    for (int i = 1; i < nPages; i++) {
        pageObjectNum[i] = pageObjectNum[i - 1] + nObjects[i - 1];
    }
    pageObjectNum[0] = pageObjectFirst;

    sbr.resetInputBits(); // reset on byte boundary. Not in specs!
    for (int i = 0; i < nPages && !sbr.atEOF(); i++) {
        pageLength[i] = pageLengthLeast + sbr.readBits(nBitsDiffPageLength);
    }
    if (sbr.atEOF()) {
        return false;
    }

    sbr.resetInputBits(); // reset on byte boundary. Not in specs!
    numSharedObject[0] = sbr.readBits(nBitsNumShared);
    numSharedObject[0] = 0; // Do not trust the read value to be 0.
    sharedObjectId[0] = nullptr;
    for (int i = 1; i < nPages && !sbr.atEOF(); i++) {
        numSharedObject[i] = sbr.readBits(nBitsNumShared);
        if (numSharedObject[i] >= INT_MAX / (int)sizeof(unsigned int)) {
            error(errSyntaxWarning, -1, "Invalid number of shared objects");
            numSharedObject[i] = 0;
            return false;
        }
        sharedObjectId[i] = (unsigned int *)gmallocn_checkoverflow(numSharedObject[i], sizeof(unsigned int));
        if (numSharedObject[i] && !sharedObjectId[i]) {
            error(errSyntaxWarning, -1, "Failed to allocate memory for shared object IDs");
            numSharedObject[i] = 0;
            return false;
        }
    }
    if (sbr.atEOF()) {
        return false;
    }

    sbr.resetInputBits(); // reset on byte boundary. Not in specs!
    for (int i = 1; i < nPages; i++) {
        for (unsigned int j = 0; j < numSharedObject[i] && !sbr.atEOF(); j++) {
            sharedObjectId[i][j] = sbr.readBits(nBitsShared);
        }
    }

    pageOffset[0] = pageOffsetFirst;
    // find pageOffsets.
    for (int i = 1; i < nPages; i++) {
        pageOffset[i] = pageOffset[i - 1] + pageLength[i - 1];
    }

    return !sbr.atEOF();
}