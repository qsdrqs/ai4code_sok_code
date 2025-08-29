void writeShort(LittleEndianOutput out1);
public void addAll(BlockList.Util.NGit src) {int srcDirIdx=0;for(;tailDirIdx.src>srcDirIdx;++srcDirIdx){if(tailBlkIdx.src!=0){addAll(directory.src);}addAll(tailBlock.src);}if(size.src==0)return;}
public void writeByte(byte b) { if (blockSize.outerInstance == upto.outerInstance) { currentBlock.outerInstance = new byte[blockSize.outerInstance]; upto.outerInstance = 0; } if (currentBlock.outerInstance != null) { blockEnd.outerInstance.add(upto.outerInstance); blocks.outerInstance.add(currentBlock.outerInstance); } currentBlock.outerInstance[upto.outerInstance++] = b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public long ramBytesUsed() { return (fst == null) ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { Charset enc = ParseEncoding.RAW; byte[] buffer = raw; byte[] msgB = TagMessage.RAW_PARSE_UTILS; if (msgB.length == 0) return ""; return RawParseUtils.decode(raw, enc); }
Object _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock(); POIFSFileSystem fs = new POIFSFileSystem();
public void init(int address) { Assert.Debug(null != slice); int offset0 = address & ByteBlockPool.BYTE_BLOCK_MASK; Assert.Debug(upto < slice.length); Buffers.pool[(slice >> ByteBlockPool.BYTE_BLOCK_SHIFT) & ByteBlockPool.BYTE_BLOCK_MASK] = address; }
public void setPath(String path) { return; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public void switchTo(int lexState, ICharStream stream) { }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyResult post(ModifyStrategyRequest request) { request = beforeClientExecution(request); return executePost(request); }
public boolean ready() throws IOException { synchronized (this) { if (in == null) { throw new IOException("Stream closed"); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
public EscherOptRecord getOptRecord() { return optRecord; }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException(); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } synchronized (this) { int copylen = (length < pos - count) ? length : pos - count; for (int i = 0; i < copylen; i++) { buffer[offset + i] = buffer[pos + i]; } return copylen; } }
public OpenNLPSentenceBreakIterator sentenceOp() { return new OpenNLPSentenceBreakIterator(); }
public void print(String str) { write((str != null) ? StringHelper.Sharpen.getValueOf(str) : null); }
public class NotImplementedFunctionException extends BaseException { public NotImplementedFunctionException(String cause, String functionName) { super(cause, functionName); } }
public V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) {int available = bufferLength - bufferPosition;if (available > 0) {if (len > available) len = available;System.arraycopy(buffer, bufferPosition, b, offset, len);bufferPosition += len;return;}if (useBuffer && bufferSize < len) {refill();available = bufferLength - bufferPosition;}if (available > 0) {System.arraycopy(buffer, bufferPosition, b, offset, len);bufferPosition += len;} else throw new EndOfStreamException();}
return Invoke.<TagQueueResponse>apply(request, (options) -> { options.setRequestMarshaller(Instance.getTagQueueRequestMarshaller()); options.setResponseUnmarshaller(Instance.getTagQueueResponseUnmarshaller()); });
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String params) {StringTokenizer st = new StringTokenizer(params, " ");if (st.hasMoreTokens()) {ignore = st.nextToken();}if (st.hasMoreTokens()) {culture = st.nextToken() + " ";}if (st.hasMoreTokens()) {culture = st.nextToken();}}
return Invoke.<DeleteDocumentationVersionResponse>invoke(request, Instance.DeleteDocumentationVersionRequestMarshaller, Instance.DeleteDocumentationVersionResponseUnmarshaller, new InvokeOptions());
@Override public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; --i) if (!Components[i].equals(other.Components[i])) return false; return true; }
public GetInstanceAccessDetailsResponse invoke(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); RequestMarshaller requestMarshaller = Instance.getInstanceAccessDetailsRequestMarshaller(); ResponseUnmarshaller responseUnmarshaller = Instance.getInstanceAccessDetailsResponseUnmarshaller(); return invoke(request, options, requestMarshaller, responseUnmarshaller); }
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(); shape.setParent(this); shapes.add(shape); onCreate(); return shape; }
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex); }
return Invoke.<GetDashboardResponse>invoke(new InvokeOptions().setRequestMarshaller(Instance.getDashboardRequestMarshaller()).setResponseUnmarshaller(Instance.getDashboardResponseUnmarshaller()), (GetDashboardRequest)request);
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.INSTANCE).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.INSTANCE)); }
public final void addMultipleBlanks() { for (int j = 0; j < NumColumns.mbr; j++) { BlankRecord br = new BlankRecord(); br.Column.mbr = FirstColumn.mbr + j; br.Row.mbr = Row.mbr; br.XFIndex.mbr = GetXFAt(j); insertCell(br); } }
public static String quoteString(String string){StringBuilder sb=new StringBuilder();int k=0;while((k=string.indexOf("\\",k))>=0){sb.append(string.substring(0,k));if(string.substring(k,k+2).equals("\\\"")){sb.append("\\\\\"");k+=2;}else{sb.append("\\\"");k+=1;}}return sb.toString();}
throw new java.nio.ReadOnlyBufferException(); return ((java.nio.ByteBuffer) this).putInt(value);
private int _reserved2Byte = 0; private short _reserved1Short = 0; private int _reserved0Int = 0; private Object[] vv; public void arrayPtgMethod(Object[][] values2d, int nRowsInt, int nColumnsInt) { _nRows = (short)nRowsInt; _nColumns = (short)nColumnsInt; nRowsInt = values2d.length; nColumnsInt = values2d[0].length; vv = new Object[_nRows * _nColumns]; for (int r = 0; r < _nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < _nColumns; c++) { rowData[c] = getValueIndex(vv); } } } private Object getValueIndex(Object[] vv) { return null; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
public String toString(String field) { return " " + _parentQuery + " "; }
public void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
public int getNextXBATChainOffset() { return INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
public void multiplyByPowerOfTen(int pow10) { TenPower tp = GetInstance.getTenPower(); if (pow10 < 0) { mulShift(_divisorShift.tp, _divisor.tp); } else { mulShift(_multiplierShift.tp, _multiplicand.tp); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = Length; builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < length - 1) { builder.append(File.separatorChar); } } return builder.toString(); }
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.setRoleName(); }
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
public void reset() { if (!first) { ptr = 0; if (!eof) { parseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } else { throw new java.util.NoSuchElementException(); } }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) { if (stems.size() < 2) return stems; @SuppressWarnings("612,618") CharArraySet terms = new CharArraySet(dictionary, 8, true); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) deduped.add(s); } return deduped; }
return Invoke.<GetGatewayResponsesResponse>invoke(request, new InvokeOptions().setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
public void setPosition(long position) { currentBlockIndex = (int)(position >> blockBits.outerInstance); currentBlock = blocks.outerInstance[currentBlockIndex]; currentBlockUpto = (int)(position & blockMask.outerInstance); }
public int calculateS(int s, long ptr) { return (int)Math.max(Math.min(Available(), s + ptr), 0); }
public BootstrapActionConfig bootstrapActionConfig(BootstrapActionDetail detail) { return BootstrapActionDetail; }
public void serialize(LittleEndianOutput out1) { if (field_7_padding != null) { StringUtil.putCompressedUnicode(out1, field_6_author); } else { StringUtil.putUnicodeLE(out1, field_6_author); } out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); }
public int lastIndexOf(String string) { return lastIndexOf(); }
public boolean add(Object E) { return addLastImpl(); }
public void unsetSection(String section, String subsection) { ConfigSnapshot snapshot = getSnapshot(); do { unsetSection(snapshot, section, subsection); } while (!compareAndSetSnapshot(snapshot)); }
public String getTagName() { return tagName; }
public void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public boolean removeObject(Object object) { synchronized(mutex) { return records.remove(object); } }
@Override public TokenStream createTokenStream(String input) { return new DoubleMetaphoneFilter(input); }
public long length() { return inCoreLength; }
public void setValue(boolean newValue) {}
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int get(int i) { if (i <= count) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoResult createRepo(CreateRepoRequest request)
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new UnsupportedOperationException(); LinkedList<E>.Link<E> link = lastLink; LinkedList<E>.Link<E> previous_1 = link.previous; LinkedList<E>.Link<E> next_1 = link.next; if (previous_1 != null) next_1.previous = previous_1; else ((LinkedList<E>)list).firstLink = next_1; if (next_1 != null) previous_1.next = next_1; else ((LinkedList<E>)list).lastLink = previous_1; ((LinkedList<E>)list).modCount++; ((LinkedList<E>)list).size--; expectedModCount = modCount; lastLink = null; }
public MergeShardsResult mergeShards(MergeShardsRequest request) { request = beforeClientExecution(request); return executeMergeShards(request); }
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
public WeightedTerm[] getTerms(Query query) { query = beforeClientExecution(query); return executeGetTerms(query); }
public ByteBuffer compact() { throw new ReadOnlyBufferException(); }
private static void decode(int iterations, int valuesOffset, int[] values, int blocksOffset, byte[] blocks) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 0x03) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 0x0F) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public String getHumanishName() { if (getPath() == null || getPath().equals("")) throw new IllegalArgumentException(); String s = getPath(); String[] elements; if (s.matches(".*" + "\\" + " " + ".*")) elements = s.split(" "); else elements = s.split(" "); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); else { if (elements.length >= 2) result = elements[elements.length - 2]; else if (elements.length == 1 && result.equals(Constants.DOT_GIT)) result = elements[0]; else throw new IllegalArgumentException(); } return result; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig() { return describeNotebookInstanceLifecycleConfig(new DescribeNotebookInstanceLifecycleConfigRequest(), new InvokeOptions().setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance())); }
public final String getAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public class DescribeJobRequest { private String jobId; private String vaultName; public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; } }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult getApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
public void print(boolean b) { print(Boolean.toString(b)); }
public IQueryNode getChild() { return getChildren()[0]; }
public workdirTreeIndex notIgnoredFilter(int workdirTreeIndex) {  }
public AreaRecord(RecordInputStream in1) { in1.readShort(field_1_formatFlags); }
public GetThumbnailResult getThumbnail(GetThumbnailRequest request) { request = beforeClientExecution(request); return executeGetThumbnail(request); }
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
if (startIndex >= 0 && ...) { symbol = input.getText(Interval.of(...)); symbol = Utils.escapeWhitespace(symbol); } else { symbol = ""; } return String.format("%s: %s", LexerNoViableAltException.class.getName(), symbol);
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord clone() { return new NumberFormatIndexRecord(field_1_formatIndex); }
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mSize = 0; mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; }
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); CreateDistributionWithTagsRequestMarshaller.Instance.marshall(request, options); return Invoke<CreateDistributionWithTagsResponse>(options, CreateDistributionWithTagsResponseUnmarshaller.Instance); }
public RandomAccessFile randomAccessFile(String fileName, String mode) { throw new java.lang.UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static String toHex(long value) { return Long.toHexString(value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public static HSSFColor getColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) return HSSFColor.AUTOMATIC.getInstance(); byte[] b = Palette.getColor(index); return b != null ? new CustomColor(b) : null; }
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult describeDBEngineVersions(DescribeDBEngineVersionsRequest request) { request = beforeClientExecution(request); return executeDescribeDBEngineVersions(request); }
public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int resultIndex = 0;int end = offset + length;for (int i = offset; i < end; i++) {char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null ? other.m_term != null : !m_term.equals(other.m_term)) return false; if (m_compiled == null ? other.m_compiled != null : !m_compiled.equals(other.m_compiled)) return false; return true; }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> entry : weightBySpanQuery.entrySet()) { spanQueries.add(entry.getKey()); entry.getKey().setBoost(entry.getValue()); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
public FieldInfo getFieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) {InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()); return invoke(request, options, DescribeEventSourceResponse.class);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options, CancelUpdateStackResponse.class); }
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request);return executeSetInstanceProtection(request); }
return invoke(new ModifyDBProxyResponseUnmarshaller(), new ModifyDBProxyRequestMarshaller(), new InvokeOptions(), request);
public void add(char[] output, int offset, int len, int endOffset, int posLength) { if (outputs == null) { outputs = new CharsRef[1]; } if (posLengths.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (endOffsets.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (outputs.length == count) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } posLengths[count] = posLength; endOffsets[count] = endOffset; outputs[count++] = new CharsRef(output, offset, len); }
public FetchLibrariesResult fetchLibraries(FetchLibrariesRequest request) { request = beforeClientExecution(request); return executeFetchLibraries(request); }
public boolean exists() { return objects.exists(); }
public FilterOutputStream filterOutputStream(FilterOutputStreamRequest request) {request = beforeClientExecution(request);return executeFilterOutputStream(request);}
@PUT @Path("UriPattern") public void scaleClusterRequest(MethodType method, String... params);
public DVConstraint createTimeConstraint(int operatorType, String formula1, String formula2);
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
public void setSharedFormula(boolean flag) { sharedFormula.field5Options.setShortBoolean(flag); }
public boolean getIsReuseObjects() { return reuseObjects; }
public IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(t); Parent.t(); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(args + " "); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory forName(String name, Map<String, String> args) { return NewInstance.loader; }
public HTTPS.ProtocolType protocol() { return addAlbumPhotosRequest("", "", "", "", ""); }
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
public TreeFilter clone() { return new AndTreeFilter.Binary(Clone.a(), Clone.b()); }
public boolean equals(Object o) { return o instanceof SomeType; }
protected boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
public void unwriteProtectWorkbook() { beforeClientExecution(null); executeUnwriteProtectWorkbook(null); }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer, expand, dedup); }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public byte[] getObjectData() { return FindObjectRecord().ObjectData; }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) { return executeListTextTranslationJobs(beforeClientExecution(request)); }
return (GetContactMethodsResponse) instance.invoke(request, new InvokeOptions().setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));
public short lookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadata.getInstance().getFunctionByNameInternal(); if (fd == null) return -1; return (short) fd.getIndex(); }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public String insertId(String message, ObjectId changeId) { return insertId(message, changeId); }
public long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(); if (0 < sz) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), " "); } throw new MissingObjectException(objectId.copy()); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(ILittleEndianInput in1) { field_1_value = in1.readDouble(); }
public GetFieldLevelEncryptionConfigResult getGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
return invoke(DeleteAlarmResponse.class, DeleteAlarmResponseUnmarshaller.ResponseUnmarshaller.options, DeleteAlarmRequestMarshaller.RequestMarshaller.options, new InvokeOptions());
public TokenStream createTokenStream(TokenStream input) { return new PortugueseStemFilter(); }
public class FtCblsSubRecord { private byte[] reserved = new byte[ENCODED_SIZE]; }
public boolean remove(Object object) { synchronized(mutex) { return c.remove(); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public class DeleteLoadBalancerPolicyRequest { private String _policyName; private String _loadBalancerName; public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this._policyName = policyName; this._loadBalancerName = loadBalancerName; } }
public int windowProtectRecord(options _options) { ... }
public UnbufferedCharStream(int bufferSize) { int[] data = new int[bufferSize]; int n = 0; }
return Invoke.invoke(new InvokeOptions().setRequest(GetOperationsRequestMarshaller.Instance().marshall(request)), GetOperationsResponseUnmarshaller.Instance(), GetOperationsRequestMarshaller.Instance(), request);
public void copyRawTo(byte[] b, int o) { NB.encodeInt32(b, o); NB.encodeInt32(b, o+4); NB.encodeInt32(b, o+8); NB.encodeInt32(b, o+12); NB.encodeInt32(b, o+16); }
public WindowOneRecord(RecordInputStream in1) { field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort(); }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { return invoke(StopWorkspacesRequestMarshaller.instance(), StopWorkspacesResponseUnmarshaller.instance(), new InvokeOptions()); }
public void close() throws IOException { if (isOpen()) { try { try { try { dump(); } finally { channel.truncate(); } } finally { channel.close(); } } finally { fos.close(); } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
public double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { int n = v.length; double s = 0.0; for (int i = 0; i < n; i++) { s += v[i]; } double m = s / n; s = 0.0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = s; } return r; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() {return end;}
public void traverse(ICellHandler handler, CellRange range) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = firstColumn - lastColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) continue; for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) continue; if (!traverseEmptyCells && currentCell.isEmpty()) continue; ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn) + 1; handler.onCell(ctx); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (getTerm().bytesEquals(other.getTerm())) return 0; if (getBoost() == other.getBoost()) return getTerm().compareTo(other.getTerm()); else return getBoost().compareTo(other.getBoost()); }
public int normalize(int len, char[] s) {for (int i = 0; i < len; ++i) {switch (s[i]) {case HAMZA_ABOVE: break; case HEH_YEH: case HEH_GOAL: case HEH: s[i] = HEH; break; case KAF: s[i] = KEHEH; break; case YEH: case FARSI_YEH: s[i] = YEH_BARREE; break; default: break;}} return len;}
public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public DiagnosticErrorListener(boolean exactOnly) {
public KeySchemaElement(KeyType keyType, String attributeName) { this._keyType = keyType; this._attributeName = attributeName; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
public boolean hasObject(String id) { return findOffset() != -1; }
public boolean setAllGroups() { return allGroups; }
public void setMultiValued(String dimName, boolean v) { synchronized(fieldTypes) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); } fieldTypes.get(dimName).IsMultiValued = v; } }
public int getCellsVal() { int size = 0; for (char c : Keys.getCells()) { e = Cell.at(); if (cmd.e >= 0) { ++size; } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
