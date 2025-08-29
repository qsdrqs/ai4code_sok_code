void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
<T extends Util.NGit.BlockList> void addAll(T src, int tailDirIdx, int tailBlkIdx) { if (0 == srcDirIdx) { ; } for (; srcDirIdx < src.size; ++srcDirIdx) { ; } if (srcDirIdx != 0) { ; } return; }
public void writeByte(byte b) { if (currentBlock == null || currentBlock.length == blockSize) { currentBlock = new byte[blockSize]; blocks.add(currentBlock); } currentBlock[upto++] = b; }
public ObjectId getObjectId() { return objectId; }
return Invoke(new DeleteDomainEntryRequestMarshaller().marshall(request), options, new DeleteDomainEntryResponseUnmarshaller());
public long RamBytesUsed() { return fst == null ? 0 : GetSizeInBytes(fst); }
public String getFullMessage() { int msgB = 0; if (msgB < 0) return RawParseUtils.Decode(null, null, null, null); return RawParseUtils.ParseEncoding() + RawParseUtils.TagMessage(null, null); }
class POIFSFileSystem { private Object _root = null; private ArrayList _documents = new ArrayList(); private PropertyTable _property_table = new PropertyTable(); private HeaderBlock headerBlock = new HeaderBlock(); }
public void Init() { int address, offset0, upto, slice; assert (address & BYTE_BLOCK_MASK) == 0; assert address < Buffers.pool[slice].length; assert slice != null; address >>= BYTE_BLOCK_SHIFT; }
public void setPath(String path) { path = beforeClientExecution(path); executeSetPath(path); }
public ListIngestionsResponse listIngestions() { return invoke(new ListIngestionsRequest(), ListIngestionsResponse.class, Instance.ResponseUnmarshaller, Instance.RequestMarshaller, new InvokeOptions()); }
public QueryParserTokenManager(int lexState, ICharStream stream) { this(stream); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyResult modifyStrategy(ModifyStrategyRequest request) { request = beforeClientExecution(request); return executeModifyStrategy(request); }
public boolean ready() { synchronized(lock) { try { if (in == null) throw new IOException("Input is null"); return in.available() > 0 && bytes.hasRemaining(); } catch (IOException e) { return false; } } }
public GetOptRecord escherOptRecord() { return _optRecord; }
public static byte[] copyBytes(byte[] buffer, int offset, int length, int count) { if (buffer == null) throw new IllegalArgumentException("buffer"); if (length == 0) return new byte[0]; int copylen = Math.min(length, count); byte[] result = new byte[copylen]; for (int i = 0; i < copylen; i++) result[i] = buffer[offset + i]; return result; }
public OpenNLPSentenceBreakIterator nLPSentenceDetectorOp(OpenNLPSentenceBreakIterator sentenceOp) { return sentenceOp; }
public void print() { write(str != null ? str : null, StringHelper.sharpen(object)); }
public NotImplementedFunctionException(String functionName, Exception cause){setFunctionName(functionName);setCause(cause);}
public V next() { return value; }
public int readBytes(byte[] buffer, int offset, int len) throws IOException { if (available <= len) { if (useBuffer && available > 0) { System.arraycopy(buffer, bufferPosition, buffer, 0, available); } bufferPosition = 0; if (bufferSize < len) { throw new IOException("End of stream"); } int bytesRead = readInternal(buffer, 0, bufferSize); available = bytesRead; if (available < len) { throw new IOException("End of stream"); } } System.arraycopy(buffer, bufferPosition, buffer, offset, len); bufferPosition += len; available -= len; return len; }
return Invoke.<TagQueueRequest, TagQueueResponse>getInstance().invoke(request, new InvokeOptions().setRequestMarshaller(TagQueueRequestMarshaller.getInstance()).setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()));
void Remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return invoke(request, this.RequestMarshaller, this.ResponseUnmarshaller, new InvokeOptions()); }
public void setParams() { String params; StringTokenizer string; String culture; ignore = null; while (enumerator.hasNext()) { culture = enumerator.next(); } }
public DeleteDocumentationVersionResponse deleteDocumentationVersion() { return Invoke.<DeleteDocumentationVersionResponse, DeleteDocumentationVersionRequest>invoke(request, new InvokeOptions(), Instance.ResponseUnmarshaller, Instance.RequestMarshaller); }
final public boolean equals(Object obj) { if (obj == null || !(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; for (int i = Length - 1; i >= 0; i--) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) throws AmazonServiceException, SdkClientException { return invoke(request, GetInstanceAccessDetailsRequestMarshaller.getInstance(), GetInstanceAccessDetailsResponseUnmarshaller.getInstance(), new InvokeOptions()); }
public HSSFPolygon createPolygon() { return (HSSFPolygon)shapes.add(new HSSFPolygon(parent.getShape(), onCreate())); }
public String getSheetName() { return Sheetname.getBoundSheetRec(sheetIndex); }
GetDashboardResponse response = Instance.invoke(request, new InvokeOptions().setRequestMarshaller(GetDashboardRequestMarshaller.Instance()).setResponseUnmarshaller(GetDashboardResponseUnmarshaller.Instance()));
public AssociateSigninDelegateGroupsWithAccountResponse invoke(AssociateSigninDelegateGroupsWithAccountRequest request) { return this.invoke(request, new AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller(), new AssociateSigninDelegateGroupsWithAccountRequestMarshaller(), new InvokeOptions()); }
public final void addMultipleBlanks() { for (int j = 0; j < numColumns; j++) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn((short)(mbr.getFirstColumn() + j)); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public static String escapeString(String input) { StringBuilder sb = new StringBuilder(); int k = 0; int index; while((index = input.indexOf("\\")) != -1) { sb.append(input.substring(k, index)).append("\\\\"); k = index + 1; } return sb.append(input.substring(k)).toString(); }
public void putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { values2d[r][c] = rowData.GetValueIndex(r * _nColumns + c); } } Object[][] values2d = new Object[_nRows][_nColumns]; int _reserved0Int = 0, _reserved1Short = 0, _reserved2Byte = 0; ArrayPtg vv = new ArrayPtg();
public static GetIceServerConfigResponse getIceServerConfig() { return Instance.invoke(new GetIceServerConfigRequest(), RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance(), new InvokeOptions()); }
@Override public String toString() { StringBuilder sb = new StringBuilder(); sb.append(" ").append(getValueAsString()).append(" ").append(new Name().getClass()); return sb.toString(); }
public String toString() { return getField() + " " + getParentQuery(); }
public void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
public int getNextXBATChainOffset() { return LittleEndianConsts.INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
public void multiplyByPowerOfTen(int pow10) { (pow10 < 0 ? mulShift(_multiplicand, _divisor, _divisorShift, _multiplierShift) : TenPower.getInstance().abs(Math.pow(10, pow10))); }
public String toString() { StringBuilder builder = new StringBuilder(); int length = ...; for (int i = 0; i < length; i++) { if (GetComponent(i) == Path.DirectorySeparatorChar) { builder.append(DirectorySeparatorChar); } else { builder.append(GetComponent(i)); } } builder.append(Path.DirectorySeparatorChar); return builder.toString(); }
ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName();
private ProgressMonitor pm; public ProgressMonitor getProgressMonitor() { return pm; } public void setProgressMonitor(ProgressMonitor pm) { SetProgressMonitor(pm); }
public void reset() { if (!First) { if (Eof != 0) { ptr = 0; } } parseEntry(); }
public E previous() { if (start >= 0) throw new NoSuchElementException(); return java.util.Iterator.previousIndex().iterator().previous(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1; }
@SuppressWarnings("unchecked") public List<CharsRef> deduped(IList<CharsRef> stems) { List<CharsRef> terms = new ArrayList<>(); for (CharsRef s : stems) if (s.length > 0) terms.add(s); CharArraySet dictionary = new CharArraySet(8, false); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef term : terms) if (!dictionary.contains(term)) { dictionary.add(term); deduped.add(term); } return deduped; }
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
public void setPosition(long position) { currentBlockIndex = (int)(position >> blockBits); currentBlock = blocks[outerInstance][currentBlockIndex]; currentBlockUpto = (int)(position & blockMask); }
public static long skipLong(int s, long ptr, int available, int min, int max) { return ptr + Math.min(available, Math.max(0, s)); }
public BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig, BootstrapActionDetail _bootstrapActionConfig) {
public void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { for (int i = 0; i < field_7_padding.length; i++) out1.writeByte(field_7_padding[i]); } out1.writeShort(field_5_hasMultibyte ? 0x0001 : 0x0000); out1.writeShort((short)Integer.parseInt(Integer.toString(field_6_author.length()))); StringUtil.putCompressedUnicode(out1, field_6_author); }
public static final int lastIndexOf(String string, int index) { return string.lastIndexOf(index); }
public E addLastImpl() { return null; }
public void unsetSection(String section, String subsection) { setSection(section); setSubsection(subsection.toString()); while (doSomething()) { compareAndSetState(res, src, unsetSection); getState(); } }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { subrecords.add(index, element); }
public boolean remove() { synchronized(mutex) { Object o; return c.remove(); } }
return new TokenStream(input, new DoubleMetaphoneFilter());
public long Length() { return InCoreLength; }
public void setValue(boolean newValue) { this.value = newValue; }
new Pair<ContentSource>(newSource, oldSource);
public int get(int i) { if (i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
public CreateRepoResult createRepo(CreateRepoRequest request) {request = beforeClientExecution(request);return executeCreateRepo(request);}
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public class Example { public void method() { if (expectedModCount != modCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new java.util.ConcurrentModificationException(); if (link == lastLink) { previous_1 = lastLink.previous; next_1 = lastLink.next; } else { previous_1 = lastLink.previous; next_1 = lastLink.next; } } }
public MergeShardsResponse mergeShards() { return invoke(new InvokeOptions().setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()).setOptions(options)); }
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
public WeightedTerm getTerms(GetTerms query) { return executeGetTerms(query); }
throw new java.nio.ReadOnlyBufferException();
public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 << 2) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 0x0F) << 4) | (byte2 >> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public String getHumanishName() { if (result == null) throw new ArgumentException(); if (result.length() == 0) throw new ArgumentException(); if (result.startsWith(Constants.DOT_GIT) || result.endsWith(Constants.DOT_GIT_EXT)) return result; String[] elements = result.split(" "); if (elements.length == 0) throw new ArgumentException(); String s = elements[elements.length - 1]; if (s.endsWith(Constants.DOT_GIT_EXT)) return s; return s.split("\\.")[0]; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request, DescribeNotebookInstanceLifecycleConfigRequestMarshaller requestMarshaller, DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller responseUnmarshaller, InvokeOptions options) { return invoke(request, requestMarshaller, responseUnmarshaller, options); }
public String getAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
return invoke(request, options, DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance(), DescribeVoicesResponse.class);
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public DescribeJobRequest createJobRequest(String jobId, String vaultName) { return new DescribeJobRequest(jobId, vaultName); }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis() { return Invoke(request, options -> { options.setRequestMarshaller(GetApisRequestMarshaller.getInstance()).setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); }); }
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public GetTrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
public void print(boolean b) { System.out.println(Boolean.toString(b)); }
public IQueryNode getChild() { return getChildren()[0]; }
public void notIgnoredFilter(int workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord field_1_formatFlags = new AreaRecord(in1.readShort());
public GetThumbnailRequest(Protocol param) { super(param, HTTPS.ProtocolType); }
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
public GetOrdRange getOrdRange() { return (GetOrdRange) prefixToOrdRange.get("key"); }
public String toString() { if (startIndex < 0) return String.format("%s %s", CultureInfo.CurrentCulture(), symbol.getName()); throw new LexerNoViableAltException(); }
public long peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(InvokeOptions options) { return Instance.invoke(new DescribeRepositoriesRequestMarshaller(), new DescribeRepositoriesResponseUnmarshaller(), options); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; }
public TokenStream Create() { return new HyphenatedWordsFilter(input); }
return invoke(new CreateDistributionWithTagsRequestMarshaller().marshall(request), new CreateDistributionWithTagsResponseUnmarshaller(), new InvokeOptions());
{ throw new java.io.RandomAccessFile(fileName, mode); throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public String toHex(int value) { return Integer.toHexString(value); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { return Invoke(request, options -> { options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); }); }
public static HSSFColor GetColor(short index) { return (index == HSSFColor.Automatic) ? new HSSFColor.Automatic().GetInstance().CustomColor : palette[index]; }
public ValueEval Evaluate(int srcRow, int srcCol, ValueEval[] operands) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort((short)field_1_number_crn_records); out1.writeShort((short)field_2_sheet_table_index); }
return new DescribeDBEngineVersionsRequest();
new FormatRun(fontIndex, character);
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return Instance.invoke(request, new InvokeOptions().setRequestMarshaller(Instance.getUploadArchiveRequestMarshaller()).setResponseUnmarshaller(Instance.getUploadArchiveResponseUnmarshaller()), UploadArchiveResponse.class);
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (obj == null) return false; if (obj.getClass() != AutomatonQuery.class) return false; AutomatonQuery other = (AutomatonQuery)obj; if (m_term == null ? other.m_term != null : !m_term.equals(other.m_term)) return false; if (m_compiled != other.m_compiled) return false; return true; }
public SpanQuery MakeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (WeightBySpanQuery wsq : weightBySpanQueries) { spanQueries.add(new SpanQuery(wsq.getKey(), wsq.getValue().getBoost())); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreate getStashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo getFieldInfo(String fieldName) { return byName.get(fieldName); }
return DescribeEventSourceResponse invoke = new InvokeOptions().setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()).invoke(new DescribeEventSourceRequest(), DescribeEventSourceResponse.class);
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
return request -> new InvokeOptions<>(new ModifyDBProxyRequestMarshaller(), new ModifyDBProxyResponseUnmarshaller());
public void add(int posLength, int endOffset, int len, int offset, Output output, int count, char[] posLengths, char[] endOffsets, CharsRef[] outputs) { if (posLengths == null) { posLengths = new char[outputs.length]; } if (endOffsets == null) { endOffsets = new char[outputs.length]; } if (outputs == null) { outputs = new CharsRef[posLengths.length]; } CopyChars(output, posLengths, endOffsets, outputs); int next = (int) ArrayUtil.oversize(count + 1, NUM_BYTES_INT32); int next2 = (int) ArrayUtil.oversize(count + 1, NUM_BYTES_INT32); int next3 = (int) ArrayUtil.oversize(count + 1, NUM_BYTES_OBJECT_REF); char[] newPosLengths = new char[next]; char[] newEndOffsets = new char[next2]; CharsRef[] newOutputs = new CharsRef[next3]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); System.arraycopy(outputs, 0, newOutputs, 0, count); }
new FetchLibrariesRequest(ProtocolType.HTTPS, "", "", "", "", "", "", "", "", "");
public boolean exists() { return objects.exists(); }
public FilterOutputStream getOut() { return out; }
public class ScaleClusterRequest { public void method(UriPattern uriPattern, MethodType methodType) { PUT.MethodType(); } }
public CreateTimeConstraintResult createTimeConstraint(CreateTimeConstraintRequest request) {request = beforeClientExecution(request);return executeCreateTimeConstraint(request);}
public ListObjectParentPathsResponse invokeListObjectParentPaths() { return Instance.invoke(new ListObjectParentPathsRequest(), new ListObjectParentPathsResponseUnmarshaller(), new ListObjectParentPathsRequestMarshaller(), new InvokeOptions()); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups(request); }
public void setSharedFormula(boolean field_5_options) { this.sharedFormula = SetShortBoolean(field_5_options); }
public boolean isReuseObjects() { return reuseObjects; }
public IErrorNode addErrorNode() { IToken t = new ErrorNodeImpl(); ((ErrorNodeImpl)t).parent = t; ((ErrorNodeImpl)t).addChild(badToken); return t; }
if (args.size() == 0) throw new IllegalArgumentException("args must not be empty");
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription() { RemoveSourceIdentifierFromSubscriptionRequest request = new RemoveSourceIdentifierFromSubscriptionRequest(); RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller responseUnmarshaller = new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller(); RemoveSourceIdentifierFromSubscriptionRequestMarshaller requestMarshaller = new RemoveSourceIdentifierFromSubscriptionRequestMarshaller(); InvokeOptions options = new InvokeOptions(); return Instance.invoke(requestMarshaller, responseUnmarshaller, options, request); }
public TokenFilterFactory forName(String name, Map<String, String> args) { return executeForName(name, args); }
public class AddAlbumPhotosRequest implements Protocol { public AddAlbumPhotosRequest(ProtocolType protocolType, String... args) { /* implementation */ } }
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public TreeFilter clone() { return new Binary.AndTreeFilter(Clone.a(), Clone.b()); }
public boolean equals(Object o) { return o instanceof Object; }
public boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResponse updateContributorInsights() { return invoke(UpdateContributorInsightsRequest.builder().build(), new InvokeOptions().setRequestMarshaller(Instance.getUpdateContributorInsightsRequestMarshaller()).setResponseUnmarshaller(Instance.getUpdateContributorInsightsResponseUnmarshaller())); }
public void unwriteProtectWorkbook() { Remove.records(); Remove.records(); }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(expand, analyzer, dedup); }
public static RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { final InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(Instance.getRequestSpotInstancesResponseUnmarshaller()); options.setRequestMarshaller(Instance.getRequestSpotInstancesRequestMarshaller()); return invoke(request, options, RequestSpotInstancesRequest.class, RequestSpotInstancesResponse.class); }
public byte[] getObjectData() { return getOrCreateObjectData().findObjectRecord(); }
return request -> Instance.<GetContactAttributesRequest, GetContactAttributesResponse>invoke(RequestMarshaller.marshall(request, Instance.options), ResponseUnmarshaller.unmarshall(), new InvokeOptions());
@Override public String toString() { return GetKey() + " " + GetValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); return instance.invoke(request, options, responseUnmarshaller, requestMarshaller); }
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public short LookupIndexByName(String name) { FunctionMetadata fd = Index.GetFunctionByNameInternal(name); return fd == null ? -1 : fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(){DescribeAnomalyDetectorsRequest request;DescribeAnomalyDetectorsResponseUnmarshaller responseUnmarshaller;DescribeAnomalyDetectorsRequestMarshaller requestMarshaller;InvokeOptions options=new InvokeOptions();return instance.invoke(request,responseUnmarshaller,options,requestMarshaller,options);}
public String insertId(String message, ObjectId changeId) { return executeInsertId(message, changeId); }
public long getObjectSize(){int typeHint;long sz;if(typeHint==OBJ_ANY){throw new MissingObjectException();}sz=db.getObjectSize(Copy.objectId(" "));if(sz<0){throw new MissingObjectException();}return sz;}
@Override public ImportInstallationMediaResponse importInstallationMedia() { ImportInstallationMediaRequest request = new ImportInstallationMediaRequest(); ImportInstallationMediaResponseUnmarshaller responseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.getInstance(); ImportInstallationMediaRequestMarshaller requestMarshaller = new ImportInstallationMediaRequestMarshaller(); InvokeOptions options = new InvokeOptions(); return (ImportInstallationMediaResponse) this.invoke(request, responseUnmarshaller, requestMarshaller, options); }
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
new NumberPtg(field_1_value); try (ILittleEndianInput in1 = new ILittleEndianInput()) { ReadDouble(in1); }
public GetFieldLevelEncryptionConfigResponse getGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return invoke(request, GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance(), new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance())); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return invoke(request, new ReportInstanceStatusResponseUnmarshaller(), new InvokeOptions()); }
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
public TokenStream create() { return new PortugueseStemFilter(input, TokenStream); }
private byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove(Object object) { synchronized(object) { return true; } }
public GetDedicatedIpResponse getDedicatedIp() { return invoke(GetDedicatedIpRequest.class, GetDedicatedIpResponse.class, Instance.RequestMarshaller, Instance.ResponseUnmarshaller, new InvokeOptions()); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyResult deleteLoadBalancerPolicy(DeleteLoadBalancerPolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLoadBalancerPolicy(request); }
public int options(WindowProtectRecord _options) {
new int[bufferSize];
public GetOperationsResponse getOperations() { return Instance.invoke(new InvokeOptions().responseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()).requestMarshaller(GetOperationsRequestMarshaller.getInstance()).options(options)); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(b, o+4); NB.EncodeInt32(b, o+8); NB.EncodeInt32(b, o+12); NB.EncodeInt32(b, o+16); }
field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort();
return client.<StopWorkspacesRequest, StopWorkspacesResponse>invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()));
public void close() throws IOException { if (isOpen()) { try {} finally { dump(); truncate(); channel.close(); fos.close(); } } }
return invoke(new DescribeMatchmakingRuleSetsRequestMarshaller(), new DescribeMatchmakingRuleSetsResponseUnmarshaller(), new InvokeOptions());
public String getPronunciation(int wordId, char[] surface, int off, int len, String[] pronunciation) { pronunciation[0] = null; return null; }
public String GetPath() { return pathStr; }
public static double devsq(double[] v, double[] r) { if (v == null || r == null || v.length == 0) return Double.NaN; int n = v.length; if (n == 0) return Double.NaN; double s = 0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; double dev = 0; for (int i = 0; i < n; i++) dev += (v[i] - m) * (v[i] - m); return dev / (n - 1); }
return Invoke.<DescribeResizeResponse,DescribeResizeRequest>invoke(request,options->{options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance());});
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end(){return end;}
void traverse(int firstRow, int lastRow, int firstColumn, int lastColumn, ICellHandler handler, SimpleCellWalkContext ctx) { for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = ctx.getSheet().getRow(rowNumber); if (currentRow == null) { continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = ctx.getSheet().getCell(rowNumber, colNumber); if (currentCell == null) { continue; } if (!currentCell.isEmpty() && !traverseEmptyCells) { continue; } handler.onCell(currentRow, currentCell, rowNumber, colNumber, (lastColumn - firstColumn + 1), (rowNumber - firstRow) * (lastColumn - firstColumn + 1) + (colNumber - firstColumn)); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other){if(other==null)return 1;int cmp=Term.compareTo(other.Term);if(cmp!=0)return cmp;return Boost.compareTo(other.Boost);}
public int Normalize(int len) { for (int i = 0; i < len; i++) { char c = s[i]; switch (c) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: break; case KEHEH: break; case YEH_BARREE: case FARSI_YEH: --i; len = StemmerUtil.Delete(s, KAF, YEH, HEH); break; default: break; } } return len; }
out1.WriteShort();
public SomeClass(boolean exactOnly) { this(new DiagnosticErrorListener(exactOnly)); }
public KeySchemaElement(KeySchemaElement keyType, String attributeName) {
public GetAssignmentResult getAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
public boolean hasObject() { return id != AnyObjectId - 1 - FindOffset; }
public boolean groupingSearch() { return allGroups; }
synchronized(v) { if (!fieldTypes.containsKey(dimName)) { v = new DimConfig[fieldType]; } v.setIsMultiValued(true); }
public int getCellsVal(){int size=0;for(char c:Keys){if(cells==null){Cell=0;}size+=cmd.at(e);e++;}return size;}
() -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.deleteVoiceConnectorRequestMarshaller); options.setResponseUnmarshaller(Instance.deleteVoiceConnectorResponseUnmarshaller); return invoke(options); }
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
