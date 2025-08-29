public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll() { int srcDirIdx = 0; for (;;) { if (srcDirIdx == 0) { if (src != null) { ++srcDirIdx; if (srcDirIdx < tailBlkIdx) { tailDirIdx = srcDirIdx; } } } else { BlockList.Util.NGit.add(0); } } }
public void writeByte(byte b) { if (b == 0) { if (currentBlock == null) { currentBlock = new byte[blockSize]; blocks.add(currentBlock); upto = 0; blockEnd = blockSize; } currentBlock[upto++] = b; } }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
public long ramBytesUsed() { return (fst == null) ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { int msgB = RawParseUtils.Decode(enc, raw, buffer, TagMessage); if (msgB < 0) return ""; return RawParseUtils.ParseEncoding(raw, buffer); }
public SomeClass() { headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; poifs = new POIFSFileSystem(); }
public final void init(int address, int offset0, int upto, Slice slice) { assert ((address & BYTE_BLOCK_MASK) == 0); assert (slice != null); int length = Buffers.getPool().length; int shifted = address >> BYTE_BLOCK_SHIFT; }
public void setPath(String path) { Api.NGit.path = path; return; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) { request = beforeClientExecution(request); return executeListIngestions(request); }
public void switchTo(int lexState, ICharStream stream) { super(stream); this.lexState = lexState; }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
public ModifyStrategyResult modifyStrategy(ModifyStrategyRequest request) { request = beforeClientExecution(request); return executeModifyStrategy(request); }
public boolean ready() throws IOException { synchronized (this) { if (in == null) { return false; } try { return in.hasRemaining() || in.available() > 0; } catch (IOException e) { throw new IOException("bytes"); } } }
public GetOptRecord getEscherOptRecord() { return _optRecord; }
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new IllegalArgumentException("buffer"); if (offset < 0 || length < 0 || offset + length > buffer.length) throw new IllegalArgumentException("invalid offset or length"); int copylen = Math.min(count, length); for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return copylen; }
new OpenNLPSentenceBreakIterator(sentenceOp -> sentenceOp.nLPSentenceDetectorOp());
Sharpen.StringHelper.getValueOf(str != null ? str : null);
public class NotImplementedFunctionException extends NotImplementedException { public NotImplementedFunctionException(String functionName, Exception cause) { super(functionName, cause); } }
public V next() { return value.nextEntry(); }
public void readBytes(byte[] b, int offset, int len) { if (available <= 0) { if (!useBuffer) { if (bufferSize < len) { throw new EndOfStreamException("Buffer too small: " + bufferSize + " < " + len); } Refill(); } else { throw new EndOfStreamException(); } } if (len < bufferLength) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } else { int after = bufferLength - bufferPosition; System.arraycopy(buffer, bufferPosition, b, offset, after); bufferPosition = 0; available = 0; offset += after; len -= after; if (available <= 0 && !useBuffer) { throw new EndOfStreamException(); } System.arraycopy(buffer, 0, b, offset, len); bufferPosition += len; available -= len; } }
public TagQueueResponse invoke(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void setParams(String[] params) { StringTokenizer st = new StringTokenizer(params, " "); while (st.hasMoreElements()) { String current = (String) st.nextElement(); if (current.equals("culture")) {} if (current.equals("ignore")) {} } }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public boolean equals(Object obj) { if (obj == null) return false; if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (this.Length != other.Length) return false; for (int i = Length - 1; i >= 0; i--) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public InstanceAccessDetailsResult createInstanceAccessDetails(InstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeCreateInstanceAccessDetails(request); }
public HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.setParent(this); shapes.add(shape); return shape; }
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetName(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void addMultipleBlanks() { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setXFIndex(mbr.getXFIndex()); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); mbr.insertCell(br); } }
public String toString() { StringBuilder sb = new StringBuilder(); int k = 0; while (k < string.length()) { int i = string.indexOf("\\", k); if (i < 0) { sb.append(string.substring(k)); break; } sb.append(string.substring(k, i)).append("\\\\"); k = i + 1; } return sb.toString(); }
public final void putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
private int _reserved0Int = 0; private short _reserved1Short = 0; private byte _reserved2Byte = 0; public void someMethod() { Object[][] values2d = new Object[_nRows][_nColumns]; for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { values2d[r][c] = _arrayValues[getValueIndex(r, c)]; } } }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append("Name: ").append(this.getClass().getSimpleName()).append(" Value: ").append(GetValueAsString()); return sb.toString(); }
public String toString() { return field + " " + _parentQuery + " "; }
public synchronized int incRef() { return refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
public int getNextXBATChainOffset() { return LittleEndianConsts.INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
public void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { Math.abs(TenPower.getInstance(tp._divisor, tp._divisorShift, tp._multiplicand, tp._multiplierShift, mulShift)); } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (i > 0) { builder.append(File.separator); } builder.append(getComponent(i)); } return builder.toString(); }
ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName(...);
public void setProgressMonitor(ProgressMonitor pm) { }
public void reset() { if (First) {} if (Eof != 0) { ptr = parseEntry(); } }
public E previous() { if (start >= previousIndex()) throw new NoSuchElementException(); return iterator.previous(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mValues.length; i++) { if (mValues[i] == value) { return i; } } return -1; }
public CharArraySet uniqueStems(List<CharsRef> stems) {CharArraySet deduped = new CharArraySet();for (CharsRef s : stems) {if (s.length > 0) {if (!deduped.contains(s)) {deduped.add(s);}}}return deduped;}
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
public void setPosition(long position) {int currentBlockUpto; int currentBlock; int currentBlockIndex; blocks = outerInstance.blocks; position >> outerInstance.blockBits; position & outerInstance.blockMask;}
public long skip(int s, int ptr, int n) { return Math.min(available(s, ptr), Math.max(s, n)); }
public BootstrapActionResult bootstrapActionConfig(BootstrapActionDetail request){request = beforeClientExecution(request);return executeBootstrapActionConfig(request);}
public void serialize() { if (field_7_padding != null) { out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length); StringUtil.putCompressedUnicode(out1, field_6_author); out1.writeShort(0x00); out1.writeShort(0x00); out1.writeShort(0x00); out1.writeShort(0x00); out1.writeShort(0x00); } }
public LastIndexOfResult lastIndexOf(LastIndexOfRequest request) {request = beforeClientExecution(request);return executeLastIndexOf(request);}
public boolean addLastImpl(E object) { return ...; }
public void unsetSection(String section, String subsection) { while (section != null) {} ConfigSnapshot.compareAndSet(state, res, src, unsetSection()); state.get(); }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) {}
public boolean remove() { synchronized(mutex) { return object.remove(); } }
public TokenStream create() { return new DoubleMetaphoneFilter(input, null, null); }
public long getLength() { return InCoreLength; }
public void setValue(boolean newValue) { this.newValue = newValue; }
public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; }
public int get(int i) { if (i >= count) { throw new Extensions.Sharpen.CreateIndexOutOfRangeException(); } return entries[i]; }
new CreateRepoRequest().setMethod(MethodType.PUT).setUriPattern("");
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() {if (modCount != expectedModCount) throw new ConcurrentModificationException();if (lastLink == null) throw new IllegalStateException();Link<E> last = lastLink;Link<E> prev = last.previous_1;Link<E> next = last.next_1;prev.next_1 = next;next.previous_1 = prev;pos = prev;lastLink = null;--_size;++modCount;}
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public AllocateHostedConnectionResult allocateHostedConnection() { return invoke(new AllocateHostedConnectionRequest(), AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance(), new InvokeOptions()); }
public int getBeginIndex() { return start; }
public WeightedTerm[] getTerms() { return query.getTerms(); }
public class compact { public compact() { throw new java.nio.ReadOnlyBufferException(); } }
public void decode(int blocksOffset, int valuesOffset, int iterations, byte[] blocks, int[] values) { for (int i=0; i<iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 << 2) | (byte1 >> 6); values[valuesOffset++] = (byte1 << 4) | (byte2 >> 4); values[valuesOffset++] = (byte2 << 6) | (byte0 & 0x03); } }
public String getHumanishName() { if (result == null) throw new IllegalArgumentException("elements"); if (result.isEmpty()) throw new IllegalArgumentException("elements"); if (result.equals(Constants.DOT_GIT)) return Constants.EMPTY; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); String[] parts = result.split(FilePath.SEPARATOR); if (parts.length == 0) return Constants.EMPTY; String last = parts[parts.length - 1]; if (last.isEmpty()) return Constants.EMPTY; if (last.equals(Constants.LOCAL_FILE)) return parts.length >= 2 ? parts[parts.length - 2] + Constants.EMPTY : Constants.EMPTY; return last; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request, InvokeOptions options) { options = new InvokeOptions(); DescribeVoicesRequestMarshaller marshaller = Instance.getRequestMarshaller(); DescribeVoicesResponseUnmarshaller unmarshaller = Instance.getResponseUnmarshaller(); return invoke(request, marshaller, unmarshaller, options); }
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String jobId, String vaultName) { setJobId(jobId); setVaultName(vaultName); }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { return Invoke.<DeleteSmsChannelResponse>invoke(request, Instance.DeleteSmsChannelResponseUnmarshaller, Instance.DeleteSmsChannelRequestMarshaller, options = new InvokeOptions()); }
public GetTrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
public String toString() { return "b"; }
public GetChild iQueryNode() { return getChildren()[coffset]; }
public void NotIgnoredFilter(int workdirTreeIndex) { /* ... */ }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public GetThumbnailResult getThumbnail(GetThumbnailRequest request) {request = beforeClientExecution(request);return executeGetThumbnail(request);}
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
String result = prefixToOrdRange.get(key); if (result != null) return result;
@Override public String toString() { if (s == null || s.isEmpty()) return ""; else return String.format("('%s'%s, offset %d, length %d)", symbol.getName(), symbol.getSize() != 0 ? String.format(", size %d", symbol.getSize()) : "", startIndex, s.length()); }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); return (DescribeRepositoriesResponse) Invoke(request, options, DescribeRepositoriesResponseUnmarshaller, DescribeRepositoriesRequestMarshaller); }
new SparseIntArray(ArrayUtils.idealIntArraySize(initialCapacity));
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public java.io.RandomAccessFile createFile(String fileName, String mode) { return new java.io.RandomAccessFile(fileName, mode); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage(request); }
public static String toHex(long value) { return Long.toHexString(value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
public HSSFColor getColor(short index) { if (index == 0) return HSSFColor.AUTOMATIC; else return HSSFColor.forIndex(index, new byte[] { 0 }); }
public ValueEval[] evaluateValueEval(int srcRow, int srcCol, Object operands) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult describeDBEngineVersions(DescribeDBEngineVersionsRequest request) { request = beforeClientExecution(request); return executeDescribeDBEngineVersions(request); }
public void formatRun(short fontIndex, short character) { }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public final List<IToken> getHiddenTokensToLeft(int tokenIndex) { return ...; }
public boolean equals(Object obj) { if (obj == null) return false; if (!obj.getClass().equals(getClass())) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null) return other.m_term == null; return m_term.equals(other.m_term); }
public SpanQuery makeSpanClause() { if (spanQueries.size() == 1) { for (WeightBySpanQuery wsq : weightBySpanQuery) { spanQueries.add(wsq); wsq.setBoost(new Key(wsq.getKey(), wsq.getValue())); } return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
public StashCreateCommand getStashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo getFieldInfo() { return byName.get(fieldName); }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { return Instance.invoke(request, CancelUpdateStackRequestMarshaller.Instance, CancelUpdateStackResponseUnmarshaller.Instance); }
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResponse setInstanceProtection() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.setInstanceProtectionRequestMarshaller); options.setResponseUnmarshaller(Instance.setInstanceProtectionResponseUnmarshaller); return invoke(request, options); }
@Override public ModifyDBProxyResponse modifyDBProxy() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return instance.invoke(request, options); }
void add(int[] posLengths, int[] endOffsets, char[][] outputs, int count, int offset, int len, int endOffset, int posLength) { if (outputs == null) throw new NullPointerException(); if (count == 0) { /* ... */ } int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF); char[][] newOutputs = new char[next][]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; int[] newPosLengths = new int[next]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; int[] newEndOffsets = new int[next]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; }
public FetchLibrariesResult fetchLibraries(FetchLibrariesRequest request, Protocol protocol) { request = beforeClientExecution(request); return executeFetchLibraries(request); }
public boolean exists() { return objects.exists(); }
public void someMethod(FilterOutputStream out) { /* ... */ }
public void scaleClusterRequest(ScaleClusterRequest request, Method method, UriPattern uriPattern) { /* ... */ }
public CreateTimeConstraint createTimeConstraint(int operatorType, String formula1, String formula2);
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
public void setSharedFormula(boolean field5Options, SetShortBoolean sharedFormula) {}
public boolean isReuseObjects() { return reuseObjects; }
public ErrorNodeImpl addErrorNode(Token t) { ErrorNodeImpl node = new ErrorNodeImpl(t); parent.addChild(node); return node; }
super(args); if (args.size() > 0) throw new IllegalArgumentException("args must be empty");
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription() { return invoke(new RemoveSourceIdentifierFromSubscriptionRequestMarshaller(), new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller(), new InvokeOptions()); }
public static TokenFilterFactory forName(String name, Map<String, String> args) { return NewInstance.<Loader, String, String>loader(name, args); }
public AddAlbumPhotosRequest() {super("AddAlbumPhotosRequest", "version", "operation");setProtocol(ProtocolType.HTTPS);}
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public TreeFilter clone() { return new Binary.AndTreeFilter(clone(a), clone(b)); }
public boolean equals(Object o) { return o instanceof Object; }
public boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
public void unwriteProtectWorkbook() { writeProtect = null; fileShare = null; Remove.records(); Remove.records(); }
super(expand.analyzer, dedup, expand.bool, dedup.bool);
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public byte[] getObjectData() { return ObjectData.findObjectRecord(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs() { InvokeOptions options = new InvokeOptions(); return Instance.invoke(ListTextTranslationJobsRequest.class, ListTextTranslationJobsResponse.class, RequestMarshaller, ResponseUnmarshaller, options); }
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {request = beforeClientExecution(request);return executeGetContactMethods(request);}
public short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) return -1; return fd.index; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());return Invoke.<DescribeAnomalyDetectorsRequest,DescribeAnomalyDetectorsResponse>builder().withRequestMarshaller(RequestMarshaller).withResponseUnmarshaller(ResponseUnmarshaller).build().invoke(request,options);}
public InsertId someMethod(String string, ObjectId objectId, String message, InsertId insertId) { return changeId; }
public long getObjectSize(String section, String name, long defaultValue) { if (typeHint == OBJ_ANY) { throw new MissingObjectException("Missing object ID"); } if (sz < 0) { throw new MissingObjectException("Invalid size"); } return typedGetter.getObjectSize(db, Copy.copy(objectId), typeHint); }
public ImportInstallationMediaResponse importInstallationMediaResponse() { return invoke(request, new InvokeOptions(), RequestMarshaller, ResponseUnmarshaller); }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return instance.invoke(request, new InvokeOptions().withRequestMarshaller(RequestMarshaller).withResponseUnmarshaller(ResponseUnmarshaller)); }
public void readData(LittleEndianInput in1) { field_1_value = new NumberPtg(in1.readDouble()); }
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfigResponse(GetFieldLevelEncryptionConfigRequest request, InvokeOptions options) { return (GetFieldLevelEncryptionConfigResponse) Instance.invoke(request, new InvokeOptions().setRequestMarshaller(new GetFieldLevelEncryptionConfigRequestMarshaller()).setResponseUnmarshaller(new GetFieldLevelEncryptionConfigResponseUnmarshaller()), GetFieldLevelEncryptionConfigResponse.class); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
@Override public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution(request); return executeReportInstanceStatus(request); }
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return (DeleteAlarmResponse) Instance.invoke(options, request); }
public TokenStream create() { return new PortugueseStemFilter(input); }
byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove() { synchronized(mutex) { return c.remove(); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public void deleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName)
public WindowProtectRecord(int options) { this.options = options; }
int[] data = new int[bufferSize];
public GetOperationsResponse getOperations() { return Invoke.invoke(RequestMarshaller.INSTANCE, ResponseUnmarshaller.INSTANCE, options, new InvokeOptions()); }
public void copyRawTo(int o, byte[] b) { EncodeInt32.NB(b, 16 + o, ...); EncodeInt32.NB(b, 12 + o, ...); EncodeInt32.NB(b, 8 + o, ...); EncodeInt32.NB(b, 4 + o, ...); EncodeInt32.NB(b, 0 + o, ...); }
private short field_9_tab_width_ratio; private short field_8_num_selected_tabs; private short field_7_first_visible_tab; private short field_6_active_sheet; private short field_5_options; private short field_4_height; private short field_3_width; private short field_2_v_hold; private short field_1_h_hold; { field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution(request); return executeStopWorkspaces(request); }
public void close() throws IOException { if (isOpen) { try { try { dump(); } finally { if (isOpen) { try { truncateChannel(); } finally { closeChannel(); closeFos(); } } } } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int len, int off, int surface, int wordId, char[] string) { return null; }
public String getPath() { return pathStr; }
public double devsq(double[] v, double[] r) {if (r == null || v.length != r.length) {throw new IllegalArgumentException();}int s = 0, m = 0;for (int i = 0; i < v.length; i++) {s += v[i];}double mean = s / (double)v.length;double dev = 0;for (int i = 0; i < v.length; i++) {dev += (v[i] - mean) * (v[i] - mean);}return dev;}
public DescribeResizeResponse describeResize() { return Instance.invoke(DescribeResizeRequest.class, DescribeResizeResponse.class, RequestMarshaller, ResponseUnmarshaller, new InvokeOptions()); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() { return end; }
public void traverse() { SimpleCellWalkContext ctx; ICellHandler handler; IRow currentRow; ICell currentCell; int firstRow, lastRow, firstColumn, lastColumn, width; for (ctx = new SimpleCellWalkContext(range.getFirstRow() + 1, range.getLastRow(), range.getFirstColumn(), range.getLastColumn());; ctx.rowNumber(++ctx.rowNumber())) { if (currentRow == null && (currentRow = sheet.getRow(ctx.rowNumber())) == null) { if (traverseEmptyCells) { handler.onCell(null, ctx.ordinalNumber(), ctx.rowNumber(), ctx.colNumber(), width * (ctx.rowNumber() - firstRow) + (ctx.colNumber() - firstColumn)); continue; } continue; } for (ctx.colNumber(firstColumn);; ctx.colNumber(++ctx.colNumber())) { if (currentCell == null && (currentCell = currentRow.getCell(ctx.colNumber())) == null) { if (traverseEmptyCells && isEmpty) { handler.onCell(null, ctx.ordinalNumber(), ctx.rowNumber(), ctx.colNumber(), width * (ctx.rowNumber() - firstRow) + (ctx.colNumber() - firstColumn)); continue; } continue; } } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { return Integer.compare(Boost, other.Boost) == 0 ? Arrays.compareUnsigned(Term, other.Term) : Integer.compare(Boost, other.Boost); }
public static String normalize(String s) { StringBuilder sb = new StringBuilder(); for (int i = 0; i < s.length(); i++) { char c = s.charAt(i); switch (c) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: continue; default: sb.append(c); } } return sb.toString(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public DiagnosticErrorListener exactOnly(boolean exactOnly) { return null; }
public KeySchemaElement(KeyType keyType, String attributeName) { _keyType = keyType; _attributeName = attributeName; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
public boolean hasObject() { return id != anyObjectId - findOffset(); }
public boolean setAllGroups() { return allGroups; }
public void setMultiValued() { synchronized (v) { if (!fieldTypes.containsKey(dimName)) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.setMultiValued(true); } }
public int getCellsVal() { int size = 0; if (cells.getKeys() != null) { for (char c : cells.getKeys()) { if (Cell >= 0) { cmd.at(e); e++; size++; } } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); return Instance.invoke(request, DeleteLifecyclePolicyRequestMarshaller.getInstance(), DeleteLifecyclePolicyResponseUnmarshaller.getInstance(), options); }
