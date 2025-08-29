public void serialize(ILittleEndianOutput out1) { out1.writeShort(0); }
public static <T> void AddAll(BlockList<T> src, int tailBlkIdx, int tailDirIdx, int[] directory, int srcDirIdx) { if (src != null) { for (int i = 0; i < directory.length; ++i) { if (directory[i] == 0) { Util.NGit.BlockList.AddAll(src, tailBlkIdx, tailDirIdx, directory, i); } } } return src.size(); }
public void WriteByte(byte b) { if (currentBlock.outerInstance.upto == currentBlock.outerInstance.blockEnd) { currentBlock.outerInstance.upto = 0; currentBlock.outerInstance.currentBlock = new Block(); currentBlock.outerInstance.blocks.add(currentBlock.outerInstance.currentBlock); } currentBlock.outerInstance.currentBlock.data[currentBlock.outerInstance.upto++] = b; }
public ObjectId GetObjectId() { return objectId; }
return Invoke(new DeleteDomainEntryRequestMarshaller().marshall(request), DeleteDomainEntryResponseUnmarshaller.getInstance(), options);
public long ramBytesUsed() { return fst == null ? 0 : getSizeInBytes(fst); }
public String getFullMessage() { return buffer == null || buffer.isEmpty() ? "" : new String(RawParseUtils.Decode(RawParseUtils.ParseEncoding, buffer), Charset.defaultCharset()); }
POIFSFileSystem fs = new POIFSFileSystem(); Object _root = null; List _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock();
public static void Init() { assert slice[Buffers.pool] != null; assert (address & BYTE_BLOCK_MASK) < upto; assert (address >> BYTE_BLOCK_SHIFT) < slice[Buffers.pool].length; ByteBlockPool pool = ByteBlockPool.instance; }
public void setPath(String path) { Api.NGit.path = path; return; }
return client.invoke(request, ListIngestionsRequestMarshaller.getInstance(), ListIngestionsResponseUnmarshaller.getInstance(), new ExecutionContext());
public void switchTo(QueryParserTokenManager tokenManager, int lexState, ICharStream stream)
public GetShardIteratorResponse GetShardIterator() { InvokeOptions options = new InvokeOptions(); return Invoke(request, GetShardIteratorRequest.class, GetShardIteratorResponse.class, RequestMarshaller, ResponseUnmarshaller, options); }
@PostMapping("/modifyStrategy") public ResponseEntity<?> modifyStrategyRequest(@RequestBody MethodType method) { /* method implementation */ }
synchronized(in){try{if(in==null){throw new IOException();}if(hasRemaining()){return 0;}return available();}catch(IOException e){return -1;}}
public GetOptRecord EscherOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new IllegalArgumentException("buffer"); if (offset < 0 || length < 0 || offset + length > buffer.length) throw new IllegalArgumentException("Invalid offset or length"); int copylen = Math.min(length, this.length - pos); for (int i = 0; i < copylen; i++) buffer[offset + i] = buffer[pos + i]; pos += copylen; return copylen; }
OpenNLPSentenceBreakIterator sentenceOp = new NLPSentenceDetectorOp();
public static void print(String str) { System.out.print(StringHelper.sharpen().getValueOf(str != null ? str : null)); }
public class NotImplementedFunctionException extends Exception { public NotImplementedFunctionException(String message, Throwable cause) { super(message, cause); } }
public V getNext() { return nextEntry().value; }
void readBytes(byte[] b, int offset, int len) { if (useBuffer) { if (available < len) { if (bufferSize < len) { throw new EndOfStreamException(); } Refill(); if (available < len) { throw new EndOfStreamException(); } } System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition = 0; bufferLength = 0; } if (len > bufferSize) { throw new EndOfStreamException(); } ReadInternal(b, offset, len); } }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(TagQueueRequestMarshaller.Instance); options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.Instance);
public void Remove() { throw new UnsupportedOperationException(); }
return invoke(     new ModifyCacheSubnetGroupRequestMarshaller().marshall(request),      new ModifyCacheSubnetGroupResponseUnmarshaller(),      new InvokeOptions() );
StringTokenizer st = new StringTokenizer(params, " "); while (st.hasMoreTokens()) { String current = st.nextToken(); if (!ignore.contains(current)) { /* process current token */ } }
DeleteDocumentationVersionResponse response = client.execute(request, new DeleteDocumentationVersionRequestMarshaller(), new DeleteDocumentationVersionResponseUnmarshaller(), options);
@Override public boolean equals(Object obj) { if (obj == null) return false; if (this == obj) return true; if (getClass() != obj.getClass()) return false; FacetLabel other = (FacetLabel)obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; i--) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); return GetInstanceAccessDetailsResponseUnmarshaller.getInstance().unmarshall(Instance.getInstanceAccessDetailsRequestMarshaller().marshall(request, options), options); }
public HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); HSSFChildAnchor anchor = new HSSFChildAnchor(); shape.setAnchor(anchor); parent.getShapes().add(shape); return shape; }
string GetSheetName(int sheetIndex) {     return workbook.GetSheetName(sheetIndex); }
return invoke(new GetDashboardRequest(), GetDashboardResponseUnmarshaller.getInstance(), GetDashboardRequestMarshaller.getInstance(), new InvokeOptions());
return invoke(new AssociateSigninDelegateGroupsWithAccountRequestMarshaller(), new AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller(), new InvokeOptions());
void AddMultipleBlanks() {     for (int j = 0; j < NumColumns; j++) {         BlankRecord br = new BlankRecord();         br.Row = Row;         br.Column = FirstColumn + j;         br.XFIndex = GetXFAt(j);         MulBlankRecord.InsertCell(br, j);     } }
public static String escapeJavaString(String input){StringBuilder sb=new StringBuilder();int k=0;while(k<input.length()){int index=input.indexOf("\\",k);if(index==-1){sb.append(input.substring(k));break;}sb.append(input.substring(k,index));sb.append("\\\\");k=index+1;}return sb.toString();}
public void putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
Object[][] values2d = new Object[_nRows][_nColumns]; for (int r = 0; r < _nRows; r++) { Object[] rowData = new Object[_nColumns]; for (int c = 0; c < _nColumns; c++) { values2d[r][c] = rowData[r * _nColumns + c]; } }
public GetIceServerConfigResponse getIceServerConfig() { return invoke(new InvokeOptions(), new GetIceServerConfigRequestMarshaller(), new GetIceServerConfigResponseUnmarshaller()); }
@Override public String toString() { StringBuilder sb = new StringBuilder(); sb.append("Name: "); sb.append(Name); sb.append(" "); sb.append(GetValueAsString()); return sb.toString(); }
public override string ToString() {     return field + " " + _parentQuery + " "; }
public int IncrementAndGet() { return refCount.incrementAndGet(); }
return this.invoke(new UpdateConfigurationSetSendingEnabledRequestMarshaller().marshall(request, options), new UpdateConfigurationSetSendingEnabledResponseUnmarshaller(), new InvokeOptions());
public int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public static double multiplyByPowerOfTen(double value, int pow10) { return pow10 > 0 ? value * Math.pow(10, pow10) : pow10 < 0 ? value / Math.pow(10, -pow10) : value; }
public String toString() { StringBuilder builder = new StringBuilder(1 - length); for (int i = 0; ; i++) { if (i < length) { if (i == java.nio.file.Path.of("").getFileSystem().getSeparator().charAt(0)) { builder.append(java.nio.file.Path.of("").getFileSystem().getSeparator().charAt(0)); } else { builder.append(getComponent(i)); } } } return builder.toString(); }
EcsMetadataServiceCredentialsFetcher fetcher = new EcsMetadataServiceCredentialsFetcher(); fetcher.setRoleName("roleName");
public void setProgressMonitor(ProgressMonitor pm) { this.pm = pm; }
public void Reset() { if (First != null) { if (Eof != null) { ptr = 0; ParseEntry(); } } }
public E previous() { if (previousIndex < 0) throw new NoSuchElementException(); return list.get(previousIndex); }
private string GetNewPrefix() {     return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(List<CharsRef> stems) { List<CharsRef> deduped = new ArrayList<>(); CharArraySet set = new CharArraySet(618, 612); for (CharsRef s : stems) { if (s.length >= 2) { set.add(s); } } deduped.addAll(set); return deduped; }
return invoke(new GetGatewayResponsesRequestMarshaller(), GetGatewayResponsesResponseUnmarshaller.getInstance(), new InvokeOptions());
currentBlock = outerInstance.blocks[(int)(position >> outerInstance.blockBits)] & outerInstance.blockMask;
return Math.min((long) Math.max(s, ptr), n);
private BootstrapActionConfig _bootstrapActionConfig; public BootstrapActionDetail getBootstrapActionConfig() { return _bootstrapActionConfig; } public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
public static void serialize(DataOutput out1) throws IOException { if (field_7_padding != null) { out1.writeByte(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeByte(field_6_author.length()); StringUtil.putCompressedUnicode(out1, field_6_author); StringUtil.putUnicodeLE(out1, field_6_author); } }
public static int lastIndexOf(String str, String search) { return str.lastIndexOf(search); }
public <E> boolean addLastImpl(Object e) { return add(e); }
public final void unsetSection(String section, String subsection) { while (true) { ConfigSnapshot state = getState(); if (!state.contains(section, subsection)) return; ConfigSnapshot newState = state.remove(section, subsection); if (compareAndSetState(state, newState)) return; } }
public String getTagName() { return tagName; }
public void Insert(int index, SubRecord element) { subrecords.add(index, element); }
private boolean Remove() { synchronized(mutex) { return object.Remove(); } }
return new DoubleMetaphoneFilter(input, null, null);
public long Length() { return InCoreLength; }
public void setValue(boolean newValue, Object value) { }
new Pair<>(newSource, oldSource);
public int Get(int i) { if (i < 0 || i >= count) throw new ArrayIndexOutOfBoundsException(); return entries[i]; }
@PutMapping("/UriPattern") public ResponseEntity<?> method(@RequestBody CreateRepoRequest request) { /* implementation */ }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); if (previous_1 != null) previous_1.next = next_1; else list.first = next_1; if (next_1 != null) next_1.previous = previous_1; list.size--; expectedModCount++; lastLink = null; }
public MergeShardsResponse invokeMergeShards(MergeShardsRequest request, InvokeOptions options) throws Exception { options = new InvokeOptions(); return Instance.invoke(request, options, MergeShardsRequestMarshaller.getInstance(), MergeShardsResponseUnmarshaller.getInstance()); }
return Instance.<AllocateHostedConnectionResponse>invoke(request, new InvokeOptions(), Instance.getAllocateHostedConnectionRequestMarshaller(), Instance.getAllocateHostedConnectionResponseUnmarshaller());
int getBeginIndex() { return start; }
public Query GetTerms(WeightedTerm[] terms) { return query; }
ByteBuffer.allocate(10).asReadOnlyBuffer().compact();
public static void decode(int iterations, int valuesOffset, int[] values, int blocksOffset, int[] blocks) { for (int i = 0; i < iterations; i++) { int byte0 = (int)(byte)values[valuesOffset++]; int byte1 = (int)(byte)values[valuesOffset++]; int byte2 = (int)(byte)values[valuesOffset++]; values[valuesOffset++] = (byte0 << 16) | (byte1 << 8) | byte2; values[valuesOffset++] = (byte0 << 8) | byte1; values[valuesOffset++] = byte0; int block0 = blocks[blocksOffset++]; int block1 = blocks[blocksOffset++]; int block2 = blocks[blocksOffset++]; int combined = (block0 << 16) | (block1 << 8) | block2; byte0 = (combined >> 16) & 0xFF; byte1 = (combined >> 8) & 0xFF; byte2 = combined & 0xFF; values[valuesOffset++] = byte0; values[valuesOffset++] = byte1; values[valuesOffset++] = byte2; } }
public static String GetHumanishName(String s) { if (s == null || s.isEmpty()) throw new IllegalArgumentException(); String[] elements = s.split("\\\\"); String result = ""; for (String part : elements) { if (!part.isEmpty()) result = part; } if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (result.isEmpty()) throw new IllegalArgumentException(); return result; }
return client.execute(new DescribeNotebookInstanceLifecycleConfigRequestMarshaller().marshall(request), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance());
public String getAccessKeySecret() { return accessSecret; }
return invoke(request, new CreateVpnConnectionRequestMarshaller(), new CreateVpnConnectionResponseUnmarshaller(), new InvokeOptions());
return invoke(new DescribeVoicesRequest(), DescribeVoicesResponseUnmarshaller.getInstance(), DescribeVoicesRequestMarshaller.getInstance(), new InvokeOptions());
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return instance.invoke(new ListMonitoringExecutionsRequestMarshaller().marshall(request), new InvokeOptions(), ListMonitoringExecutionsResponseUnmarshaller.getInstance()); }
public class DescribeJobRequest {     private string _jobId;     private string _vaultName;      public DescribeJobRequest(string jobId, string vaultName) {         _jobId = jobId;         _vaultName = vaultName;     } }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request) { return invoke(request, options -> { options.setMarshaller(GetApisRequestMarshaller.getInstance()); options.setUnmarshaller(GetApisResponseUnmarshaller.getInstance()); }); }
return invoke(new DeleteSmsChannelRequest(), options, new DeleteSmsChannelRequestMarshaller(), new DeleteSmsChannelResponseUnmarshaller());
public TrackingRefUpdate GetTrackingRefUpdate() {     return trackingRefUpdate; }
void print() {     bool b = true; // or some value     Console.WriteLine(b.ToString()); }
public GetChild[] getChildren() { return new GetChild[0]; }
// Invalid C# input; no direct Java translation possible
field_1_formatFlags = in1.readShort();
HttpURLConnection connection = (HttpURLConnection) new URL("https://example.com").openConnection();
DescribeTransitGatewayVpcAttachmentsResponse response = ec2Client.describeTransitGatewayVpcAttachments(request);
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, new PutVoiceConnectorStreamingConfigurationRequestMarshaller(), new PutVoiceConnectorStreamingConfigurationResponseUnmarshaller(), options); }
public String getOrdRange(String key) { return prefixToOrdRange.containsKey(key) ? prefixToOrdRange.get(key) : ""; }
@Override public String toString() { return (startIndex < 0) ? String.format(java.util.Locale.getDefault(), "%s:%s", symbol, symbolSize) : Utils.escapeWhitespace(((ICharStream)inputStream).getText(Interval.of(startIndex, ((ICharStream)inputStream).index()))); }
public E peek() { return peekFirstImpl(); }
Function<CreateWorkspacesRequest, CreateWorkspacesResponse> createWorkspaces = request -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options); };
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
return this.invoke(request, new InvokeOptions(), new DescribeRepositoriesRequestMarshaller(), new DescribeRepositoriesResponseUnmarshaller());
int cap = android.util.ArrayUtils.idealIntArraySize(10), mKeys[] = new int[cap], mValues[] = new int[cap], mSize = 0;
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return invoke(request, options); }
throw new System.NotImplementedException("Message", new java.io.RandomAccessFile(fileName, mode));
return invoke(new DeleteWorkspaceImageRequestMarshaller().marshall(new InvokeOptions()), new DeleteWorkspaceImageResponseUnmarshaller(), new InvokeOptions());
public static String toHex(long value) { return Long.toHexString(value).toUpperCase(); }
return this.invoke(request, new InvokeOptions(), UpdateDistributionRequestMarshaller.getInstance(), UpdateDistributionResponseUnmarshaller.getInstance());
public HSSFColor getColor(Short index) { if (index == null) return null; if (index == 0x40) return HSSFColor.AUTOMATIC; return palette.getCustomColor(index); }
public ValueEval evaluate(int srcCol, int srcRow, ValueEval[] operands) { throw new UnsupportedOperationException(); }
public static void serialize(ILittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
// Invalid C# input; translation not possible
public static void toBigEndianUtf16Bytes(char[] chars, int offset, int length, byte[] result) { int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } }
return invoke(request, options, new UploadArchiveRequestMarshaller(), new UploadArchiveResponseUnmarshaller());
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null ? other.m_term != null : !m_term.equals(other.m_term)) return false; return m_compiled.equals(other.m_compiled); }
public SpanQuery makeSpanClause() { if (spanQueries.size() == 1) return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); else { List<SpanQuery> newQueries = new ArrayList<>(); for (SpanQuery wsq : spanQueries) { wsq.setKey(new Key()); newQueries.add(wsq); } return new SpanOrQuery(newQueries.toArray(new SpanQuery[0])); } }
StashCreate StashCreateCommand() {     return new StashCreate(); }
public FieldInfo getFieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource() { DescribeEventSourceRequest request = new DescribeEventSourceRequest(); DescribeEventSourceResponseUnmarshaller responseUnmarshaller = DescribeEventSourceResponseUnmarshaller.getInstance(); DescribeEventSourceRequestMarshaller requestMarshaller = DescribeEventSourceRequestMarshaller.getInstance(); InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(requestMarshaller); options.setResponseUnmarshaller(responseUnmarshaller); return invoke(request, responseUnmarshaller, options); }
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { return invoke(request, GetDocumentAnalysisRequestMarshaller.getInstance(), GetDocumentAnalysisResponseUnmarshaller.getInstance(), new InvokeOptions()); }
return Instance.invoke(request, new InvokeOptions(), RequestMarshaller, ResponseUnmarshaller);
return invoke(request, new ModifyLoadBalancerAttributesRequestMarshaller(), new ModifyLoadBalancerAttributesResponseUnmarshaller(), new InvokeOptions());
return invoke(request, SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance(), options);
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); ModifyDBProxyRequestMarshaller.getInstance().marshall(request, options); return ModifyDBProxyResponseUnmarshaller.getInstance().unmarshall(Invoke(options)); }
// Translated Java code cannot be provided due to invalid/obfuscated source
public class FetchLibrariesRequest extends HTTPS.ProtocolType {
public boolean exists() { return objects.exists(); }
void method(FilterOutputStream out) { /* ... */ }
@PUT @Path("/{param}") public Response scaleCluster(ScaleClusterRequest request) { /* implementation */ }
DVConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2);
ListObjectParentPathsResponse response = Instance.invoke(new ListObjectParentPathsRequestMarshaller(options), new ListObjectParentPathsResponseUnmarshaller(), new InvokeOptions());
DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { return (DescribeCacheSubnetGroupsResponse) Invoke(request, new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance())); }
public void setSharedFormula() { setShortBoolean(sharedFormula, flag, field_5_options); }
public bool IsReuseObjects {     get { return reuseObjects; } }
public IErrorNode addErrorNode(IToken t) { return parent.addChild(new ErrorNodeImpl(t)); }
if (args.size() > 0) throw new IllegalArgumentException(" " + args);
return Invoke<RemoveSourceIdentifierFromSubscriptionRequest, RemoveSourceIdentifierFromSubscriptionResponse>(new RemoveSourceIdentifierFromSubscriptionRequest(), new InvokeOptions(), new RemoveSourceIdentifierFromSubscriptionRequestMarshaller(), new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller());
public static Object newInstance(String name, Map<String, String> args, ClassLoader loader) throws Exception { return Class.forName(name, true, loader).getConstructor(Map.class).newInstance(args); }
public class AddAlbumPhotosRequest { public AddAlbumPhotosRequest(Protocol protocol) { /* constructor logic */ } }
return this.invoke(new GetThreatIntelSetRequest(), this.requestMarshaller, GetThreatIntelSetResponseUnmarshaller.getInstance(), new ExecutionContext());
return new Binary.AndTreeFilter(Clone.a(), Clone.b());
public override bool Equals(object o) {     return o is SomeType; // Example logic to check if 'o' is of a specific type and equals to this instance }
protected boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, UpdateContributorInsightsResponseUnmarshaller.getInstance(), options); }
public void unwriteProtectWorkbook() { Remove.records().records(null, writeProtect, null, fileShare); }
public class SomeClass extends SolrSynonymParser { public SomeClass(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer, expand, dedup); } }
return invoke(RequestMarshaller.marshall(Request, new InvokeOptions()), new InvokeOptions(), ResponseUnmarshaller);
public byte[] GetObjectData() {     return ObjectData.FindObjectRecord(); }
return Instance.invoke(new GetContactAttributesRequestMarshaller(), new GetContactAttributesResponseUnmarshaller(), new InvokeOptions());
@Override public String toString() { return getKey() + " " + getValue(); }
return invoke(new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()), ListTextTranslationJobsRequest.class, ListTextTranslationJobsResponse.class);
public GetContactMethodsResponse getContactMethods() { InvokeOptions options = new InvokeOptions(); GetContactMethodsRequest request = GetContactMethodsRequestMarshaller.getInstance().marshall(); GetContactMethodsResponse response = invoke(request, options); return GetContactMethodsResponseUnmarshaller.getInstance().unmarshall(response); }
public short lookupIndexByName(String name) { if (FunctionMetadata.getInstance().getFunctionByNameInternal(name) == null) return -1; else return (short)(fd - 1); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.getDescribeAnomalyDetectorsRequestMarshaller()); options.setResponseUnmarshaller(Instance.getDescribeAnomalyDetectorsResponseUnmarshaller()); return invoke(request, options); }
public string InsertId(ObjectId id, string message, string insertId) {     return changeId; }
public long getObjectSize(int typeHint, AnyObjectId objectId) { long sz = db.getObjectSize(objectId); if (sz < 0) throw new MissingObjectException(); if (typeHint != OBJ_ANY) throw new MissingObjectException(); return sz; }
return invoke(request, new InvokeOptions(), requestMarshaller, responseUnmarshaller);
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return invoke(request, new PutLifecycleEventHookExecutionStatusRequestMarshaller(), new PutLifecycleEventHookExecutionStatusResponseUnmarshaller(), new InvokeOptions()); }
public class NumberPtg { private double field_1_value; public NumberPtg(java.io.DataInput in) throws java.io.IOException { byte[] bytes = new byte[8]; in.readFully(bytes); this.field_1_value = java.nio.ByteBuffer.wrap(bytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble(); } }
return this.invoke(request, GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance(), new InvokeOptions());
return client.handler().value().execute(SdkHttpFullRequestAdapter.create(), DescribeDetectorRequestMarshaller.getInstance()::marshall, DescribeDetectorResponseUnmarshaller.getInstance()::unmarshall, new DescribeDetectorRequest(), new RequestConfig());
return instance.invoke(new ReportInstanceStatusRequestMarshaller().marshall(request), new ReportInstanceStatusResponseUnmarshaller(), new InvokeOptions());
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { return this.client.invoke(request, DeleteAlarmResponse::builder, DeleteAlarmRequestMarshaller.getInstance(), DeleteAlarmResponseUnmarshaller.getInstance()); }
return new PortugueseStemFilter(input);
byte[] reserved = new byte[ENCODED_SIZE];
private boolean Remove() { synchronized(mutex) { return object.Remove(); } }
return request -> invoke(request, GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance(), new InvokeOptions());
@Override public String toString(){return " "+precedence;}
return invoke(request, new InvokeOptions(), ListStreamProcessorsRequestMarshaller.getInstance(), ListStreamProcessorsResponseUnmarshaller.getInstance());
DeleteLoadBalancerPolicyRequest request = new DeleteLoadBalancerPolicyRequest().withPolicyName(_policyName).withLoadBalancerName(_loadBalancerName);
public int WindowProtectRecord(options _options);
int[] data = new int[bufferSize];
public GetOperationsResponse getOperations(GetOperationsRequest request) { return Instance.invoke(request, Instance.getGetOperationsRequestMarshaller(), Instance.getGetOperationsResponseUnmarshaller(), new InvokeOptions()); }
void copyRawTo(byte[] b, int o, int v1, int v2, int v3, int v4, int v5) { java.nio.ByteBuffer.wrap(b).order(java.nio.ByteOrder.BIG_ENDIAN).position(o).putInt(v1).putInt(v2).putInt(v3).putInt(v4).putInt(v5); }
new WindowOneRecord(){{field_9_tab_width_ratio=in1.readShort();field_8_num_selected_tabs=in1.readShort();field_7_first_visible_tab=in1.readShort();field_6_active_sheet=in1.readShort();field_5_options=in1.readShort();field_4_height=in1.readShort();field_3_width=in1.readShort();field_2_v_hold=in1.readShort();field_1_h_hold=in1.readShort();}}
return invoke(request, StopWorkspacesRequestMarshaller.getInstance(), StopWorkspacesResponseUnmarshaller.getInstance(), new InvokeOptions());
public void close() throws IOException { if (isOpen()) { try { dump(); } finally { channel.truncate(0); } } try { channel.close(); } finally { fos.close(); } }
return Instance.invoke(new Request(), RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance(), DescribeMatchmakingRuleSetsResponse.class);
public char[] getPronunciation(int wordId, String surface, int off, int len) { return null; }
public String GetPath() { return pathStr; }
public static double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; int n = v.length; if (n == 1) return 0; double s = 0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; double r = 0; for (int i = 0; i < n; i++) r += (v[i] - m) * (v[i] - m); return r; }
return instance.invoke(new DescribeResizeRequestMarshaller().marshall(request), new InvokeOptions(), new DescribeResizeResponseUnmarshaller());
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() { return 0; }
public void traverse(Sheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn, boolean traverseEmptyCells, ICellHandler handler) { for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { Row currentRow = sheet.getRow(rowNumber); if (currentRow == null) continue; for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { Cell currentCell = currentRow.getCell(colNumber); if (currentCell == null) { if (traverseEmptyCells) handler.onCell(new SimpleCellWalkContext(rowNumber, colNumber, null, null, firstRow, lastRow, firstColumn, lastColumn)); continue; } handler.onCell(new SimpleCellWalkContext(rowNumber, colNumber, currentRow, currentCell, firstRow, lastRow, firstColumn, lastColumn)); } } }
public int GetReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { return BytesEquals(Term, other.Term) ? Boost.compareTo(other.Boost) : Term.compareTo(other.Term); }
public static String normalize(String s) { char[] chars = s.toCharArray(); for (int i = 0; i < chars.length; i++) { switch (chars[i]) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: chars[i] = HEH; break; case KEHEH: chars[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: chars[i] = YEH; break; } } return new String(chars); }
out1.WriteShort(value);
public void myMethod(DiagnosticErrorListener listener, boolean exactOnly) { }
public class KeySchemaElement {     private KeyType _keyType;     private string _attributeName;      public KeySchemaElement(KeyType keyType, string attributeName) {         _keyType = keyType;         _attributeName = attributeName;     } }
return invoke(new GetAssignmentRequestMarshaller(options), new GetAssignmentResponseUnmarshaller(), options, request, GetAssignmentResponse.class);
public boolean HasObject() { return id != AnyObjectId - 1 - FindOffset(); }
public void SetAllGroups(bool allGroups) {     // method logic     return; }
synchronized(lock) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) fieldTypes.put(dimName, fieldType = new DimConfig()); fieldType.setIsMultiValued(v); }
public int getCellsVal(char c, int size) { for (char key : cells.keySet()) { if (c == key && size >= 0) { size = cmd.get(e); e++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { return invoke(new InvokeOptions(), new DeleteVoiceConnectorRequestMarshaller(), new DeleteVoiceConnectorResponseUnmarshaller(), request); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, DeleteLifecyclePolicyRequestMarshaller.getInstance(), DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); }
