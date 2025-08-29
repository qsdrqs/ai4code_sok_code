public void serialize(LittleEndianOutput out) { out.writeShort(getSid()); }
void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { return; } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[srcDirIdx]); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
public void writeByte(byte b) {if (upto == blockSize) {if (currentBlock != null) {blocks.add(currentBlock);blockEnd.add(upto);}currentBlock = new byte[blockSize];upto = 0;}currentBlock[upto++] = b;}
} ; objectId return { ) ( getObjectId ObjectId
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.Instance); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.Instance); return invoke(request, options); }
public long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
public String getFullMessage(byte[] raw) { int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) return ""; java.nio.charset.Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
public POIFSFileSystem() { this.headerBlock = new HeaderBlock(); this._property_table = new PropertyTable(); this._documents = new ArrayList(); this._root = null; }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < slice.length; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState) { this(stream); switchTo(lexState); }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyRequest() { super(" ", " ", " ", " ", " "); }
public synchronized boolean ready() throws IOException {if (in == null) {throw new IOException("Pipe not connected");}return (in.available() > 0);}
public EscherOptRecord getOptRecord() { return _optRecord; }
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer"); } if (offset < 0 || length < 0 || length > buffer.length - offset) { throw new IndexOutOfBoundsException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
sentenceOp = new NLPSentenceDetectorOp ( new OpenNLPSentenceBreakIterator ( ) ) ;
public void print(String str) { write(String.valueOf(str)); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
V next() { return nextEntry.getValue(); }
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); len -= available; offset += available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new java.io.EOFException(); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new java.io.EOFException(); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) { culture = st.nextToken(); } if (st.hasMoreTokens()) { culture += " " + st.nextToken(); } if (st.hasMoreTokens()) { String ignore = st.nextToken(); } }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (components.length != other.components.length) return false; for (int i = components.length - 1; i >= 0; i--) if (!components[i].equals(other.components[i])) return false; return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(null, anchor); shape.setParent(this); shape.setAnchor(anchor); addShape(shape); onCreate(shape); return shape; }
public String getSheetName(int sheetIndex) { return getBoundSheetRec().getSheetname(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
public void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 1; } sb.append(string.substring(apos)); sb.append("\""); return sb.toString(); }
java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d){final int nColumns = values2d[0].length;final int nRows = values2d.length;_nColumns = (short)nColumns;_nRows = (short)nRows;final Object[] vv = new Object[_nRows * _nColumns];for (int r = 0; r < nRows; r++){final Object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[getValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) { final InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()); sb.append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString(); }
String toString(String field) { return " " + _parentQuery + " "; }
} ; ) ( incrementAndGet . refCount { ) ( incRef void public
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { return invoke(request, new InvokeOptions(UpdateConfigurationSetSendingEnabledRequestMarshaller.instance, UpdateConfigurationSetSendingEnabledResponseUnmarshaller.instance)); }
public int getNextXBATChainOffset(){return getXBATEntriesPerBlock()*LittleEndianConsts.INT_SIZE;}
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
public String toString() { StringBuilder builder = new StringBuilder(); int len = length(); builder.append(java.io.File.separator); for (int i = 0; i < len; i++) { builder.append(getComponent(i)); if (i < len - 1) { builder.append(java.io.File.separator); } } return builder.toString(); }
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.setRoleName(fetcher.getRoleName()); }
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void reset() { if (!first) { ptr = 0; if (!eof) { parseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary); List<CharsRef> deduped = new ArrayList<CharsRef>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long skip(long n) { int s = (int) Math.min(available, (long) Integer.MAX_VALUE); s = Math.max(s, 0); ptr += s; return s; }
public BootstrapActionDetail withBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) {
public void serialize(LittleEndianOutput out) { out.writeShort(field_1_row); out.writeShort(field_2_col); out.writeShort(field_3_flags); out.writeShort(field_4_note_object_id); out.writeShort(field_5_author.length()); out.writeByte(field_6_hasMultibyte ? 0x01 : 0x00); if (field_6_hasMultibyte) { StringUtil.putUnicodeLE(field_5_author, out); } else { StringUtil.putCompressedUnicode(field_5_author, out); } if (field_7_padding != null) { out.write(field_7_padding); } }
int lastIndexOf(String string) { return string.lastIndexOf(','); }
public boolean add(E object) { return addLastImpl(); }
} ; ) ) , ( compareAndSet . state ! ( while } ; ) , , ( unsetSection res ; ) ( get . state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( unsetSection void
String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { subrecords.add(index, element); }
public boolean remove(Object object) { synchronized (mutex) { return c.remove(); } }
} ; ) input ( DoubleMetaphoneFilter new return { ) input TokenStream ( create TokenStream
} ; ) ( inCoreLength return { ) ( length long public
public void setValue(boolean newValue) { this.value = newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int get(int i) { if (i >= count) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoResult createRepo(CreateRepoRequest request) {request = beforeClientExecution(request);return executeCreateRepo(request);}
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
} } ; ) ( ConcurrentModificationException new throw { else } } ; ) ( IllegalStateException new throw { else } ; ++ modCount . list ; -- size . list ; ++ expectedModCount ; null lastLink ; } ; -- pos { ) link == lastLink ( if ; next_1 next . previous_1 ; previous_1 previous . next_1 ; previous . lastLink = previous_1 > E < Link . LinkedList . util . java ; next . lastLink = next_1 > E < Link . LinkedList . util . java { ) null != lastLink ( if { ) modCount . list == expectedModCount ( if { ) ( remove void public
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public Connection allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
int getBeginIndex() { return start; }
return query.getTerms(WeightedTerm.class);
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName() { String s = getPath(); if (s == null || s.length() == 0) { throw new IllegalArgumentException(); } String[] elements = s.split("/"); if (elements.length == 0) { throw new IllegalArgumentException(); } String result; if (Constants.DOT_GIT.equals(elements[elements.length - 1])) { result = elements[elements.length - 2]; } else { result = elements[elements.length - 1]; } if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeJobRequest(String jobId, String vaultName) {setJobId(jobId); setVaultName(vaultName);}
public EscherRecord getEscherRecord(int index) { return escherRecords.get(index); }
public Apis getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) {print(Boolean.toString(b));}
public IQueryNode getChild() { return getChildren().get(0); }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
getThumbnailRequest.setProtocol(Protocol.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
return String.format(Locale.ROOT, "line %d:%d no viable alternative at input %s", line, charPositionInLine, getErrorDisplay(e.getInputStream().getText(new Interval(e.getStartIndex(), e.getStartIndex()))));
public E peek() { return peekFirst(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public String toHex(int value) { return toHex((long) value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index) { if (index == HSSFColor.Automatic.getIndex()) { return HSSFColor.Automatic.getInstance(); } else { byte[] b = palette.getColor(); if (b != null) { return new CustomColor(); } } return null; }
} ; ) ( NotImplementedFunctionException new throw { ) srcCol int , srcRow int , operands ] [ ValueEval ( evaluate ValueEval
} ; ) field_2_sheet_table_index ) short ( ( writeShort . out1 ; ) field_1_number_crn_records ) short ( ( writeShort . out1 { ) out1 ILittleEndianOutput ( serialize void public
public DescribeDBEngineVersionsResult describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this.character = character; this.fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (this == obj) return true; if (!super.equals(obj)) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
return client.execute(GetDocumentAnalysisRequest.builder().build(), new GetDocumentAnalysisResponseUnmarshaller());
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options); }
} ; ) , ( > ModifyLoadBalancerAttributesResponse < invoke return ; instance . ModifyLoadBalancerAttributesResponseUnmarshaller responseUnmarshaller . options ; instance . ModifyLoadBalancerAttributesRequestMarshaller requestMarshaller . options ; ) ( InvokeOptions new = options { ) request ModifyLoadBalancerAttributesRequest ( modifyLoadBalancerAttributes ModifyLoadBalancerAttributesResponse
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { return invoke(request, ModifyDBProxyResponseUnmarshaller.getInstance()); }
public void add(char[] output, int offset, int len, int endOffset, int posLength) {if (outputs.length == count) {final CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs, 0, next, 0, count);outputs = next;}if (endOffsets.length == count) {final int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(endOffsets, 0, next, 0, count);endOffsets = next;}if (posLengths.length == count) {final int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(posLengths, 0, next, 0, count);posLengths = next;}if (outputs[count] == null) {outputs[count] = new CharsRef();}outputs[count].copyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); setProtocol(ProtocolType.HTTPS); }
public boolean exists() { return objects.exists(); }
} finally { out.close(); }
public ScaleClusterRequest() {super(" ", " ", " ", " ", " ");setUriPattern(" ");setMethod(MethodType.PUT);}
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
public void setSharedFormula(boolean flag) { field_5_options.sharedFormula = setShortBoolean(flag); }
public boolean isReuseObjects() { return reuseObjects; }
ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.parent; return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (!args.isEmpty()) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(); }
public AddAlbumPhotosRequest() { super(" ", " ", " ", " ", " "); protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.instance); options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.instance); return invoke(request, options); }
public TreeFilter clone() { return new Binary.AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o) { return o instanceof Object; }
} ; ) ( protectedHasArray return { ) ( hasArray boolean
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void unwriteProtectWorkbook() { records.remove(); records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean expand, boolean dedup, Analyzer analyzer) { super(analyzer, dedup); }
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { return invoke(request, new InvokeOptions(RequestSpotInstancesRequestMarshaller.getInstance(), RequestSpotInstancesResponseUnmarshaller.getInstance())); }
byte[] getObjectData() { return ObjectData.findObjectRecord(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {request = beforeClientExecution(request);return executeGetContactMethods(request);}
public short lookupIndexByName(String name){FunctionMetadata fd = getInstance().getFunctionByNameInternal(name);if (fd == null){return -1;}return (short)fd.getIndex();}
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
String insertId(String message, ObjectId changeId) { return insertId; }
public long getObjectSize(AnyObjectId objectId, int typeHint) {long sz = db.getObjectSize(objectId, typeHint);if (sz < 0) {throw new MissingObjectException(objectId.copy(), typeHint);}return sz;}
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options); }
return invoke(new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()), request);
public NumberPtg(LittleEndianInput in) { setValue(in.readDouble()); }
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return invoke(request, ReportInstanceStatusRequestMarshaller.getInstance(), ReportInstanceStatusResponseUnmarshaller.getInstance()); }
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
} ; ) ( PortugueseStemFilter new return { ) input TokenStream ( create TokenStream
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
public boolean remove(Object object) { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public String toString() { return " " + precedence; }
return invoke(request, new InvokeOptions() {{ setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()); setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); }});
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
public WindowProtectRecord(int options) { _options options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void copyRawTo(byte[] b, int o) { NB.encodeInt32(b, o, field_1_value); NB.encodeInt32(b, o + 4, field_2_value); NB.encodeInt32(b, o + 8, field_3_value); NB.encodeInt32(b, o + 12, field_4_value); NB.encodeInt32(b, o + 16, field_5_value); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.readShort(); field_2_v_hold = in.readShort(); field_3_width = in.readShort(); field_4_height = in.readShort(); field_5_options = in.readShort(); field_6_active_sheet = in.readShort(); field_7_first_visible_tab = in.readShort(); field_8_num_selected_tabs = in.readShort(); field_9_tab_width_ratio = in.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
void close() throws IOException { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(0); } finally { try { channel.close(); } finally { fos.close(); } } } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String getPath() { return pathStr; }
} ; r return } ; s : 0 ? ) 1 == n ( r } ; ) m - ] [ v ( * ) m - ] [ v ( s { ) ++ i ; n < i ; 0 = i int ( for ; 0 s ; n / s m } ; ] [ v s { ) ++ i ; n < i ; 0 = i int ( for ; length . v = n int ; 0 = s double ; 0 = m double { ) 1 >= length . v && null != v ( if ; NaN . Double = r double { ) v ] [ double ( devsq double
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
public void traverse(CellHandler handler) {int firstRow = range.getFirstRow();int lastRow = range.getLastRow();int firstColumn = range.getFirstColumn();int lastColumn = range.getLastColumn();int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();Row currentRow = null;Cell currentCell = null;for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) {currentRow = sheet.getRow(ctx.rowNumber);if (currentRow == null) {continue;}for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) {currentCell = currentRow.getCell(ctx.colNumber);if (currentCell == null) {continue;}if (isEmpty() && !traverseEmptyCells) {continue;}ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn) + 1;handler.onCell(ctx, currentCell);}}}
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (term.equals(other.term)) { return Float.compare(boost, other.boost); } else { return term.compareTo(other.term); } }
int normalize(char s[], int len) {for (int i = 0; i < len; i++) {switch (s[i]) {case FARSI_YEH:case YEH_BARREE:s[i] = YEH;break;case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE:len = StemmerUtil.delete(s, i, len);i--;break;default:break;}}return len;}
public void serialize(LittleEndianOutput out) { out.writeShort(); }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(String attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()));
public boolean hasObject(AnyObjectId id){return findOffset(id) != -1;}
public GroupingSearch setAllGroups(boolean allGroups) { this.allGroups = allGroups; return this; }
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.setMultiValued(v); } }
public int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
