public void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
void addAll(T src) { if (tailBlkIdx.src != 0) { for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; srcDirIdx++) { AddAll(directory.src[srcDirIdx]); } } if (size.src == 0) return; }
public void writeByte(byte b) { if (currentBlock != null) { if (upto == blockSize) { blocks.add(currentBlock); currentBlock = new byte[blockSize]; blockEnd = currentBlock; upto = 0; } currentBlock[upto++] = b; } }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, RequestMarshaller.INSTANCE, ResponseUnmarshaller.INSTANCE, options, DeleteDomainEntryResponse.class); }
public long RamBytesUsed() { return fst == null ? 0 : fst.GetSizeInBytes(); }
public String getFullMessage() { byte[] buffer = raw; int msgB = RawParseUtils.parseTagMessage(buffer); if (msgB < 0) return ""; Charset enc = RawParseUtils.parseEncoding(); return Integer.toString(RawParseUtils.decode(raw, msgB, enc).length()); }
public class POIFSFileSystem { static { _root = null; _documents = new java.util.ArrayList(); _property_table = new PropertyTable(); headerBlock = new HeaderBlock(); } }
void init(int address) { assert slice.length < upto; offset0 = address & BYTE_BLOCK_MASK; assert slice != null; Buffers.pool[address >> BYTE_BLOCK_SHIFT].slice = slice; }
private void SetPath(String path) { return; }
return client.invoke(new ListIngestionsRequest(), ListIngestionsRequestMarshaller.getInstance(), ListIngestionsResponseUnmarshaller.getInstance(), new InvokeOptions());
public void SwitchTo(ICharStream stream, int lexState) {     // method body }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return invoke(request, options); }
[HttpPost] public async Task<IActionResult> ModifyStrategyRequest(string param1, string param2, string param3, string param4, string param5) {     // logic to modify strategy     return Ok(); }
public boolean ready() { synchronized (lock) { if (in == null) throw new java.io.IOException(" "); try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer"); } synchronized (buffer) { Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = Math.min(length, pos - count); for (int i = 0; i < copylen; i++) { buffer[i + pos] = (byte) buffer[i + offset]; } return copylen; } }
OpenNLPSentenceBreakIterator sentenceOp = (sentenceOp) -> { /* implementation */ };
void print(String str) { write(str != null ? Sharpen.StringHelper.GetValueOf((Object)null) : null); }
public class NotImplementedFunctionException extends RuntimeException { public NotImplementedFunctionException(String message, Throwable cause) { super(message, cause); } }
public V next() { return nextEntry.value; }
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (available > 0) { if (available <= len) { len = available; bufferPosition += len; System.arraycopy(buffer, bufferPosition - len, b, offset, len); } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (useBuffer && bufferSize < len) { long after = bufferStart + bufferPosition + len; if (after > length) throw new EndOfStreamException(); readInternal(); available = bufferLength - bufferPosition; if (len > available) throw new EndOfStreamException(); } else { available = bufferLength - bufferPosition; if (len > available) throw new EndOfStreamException(); } System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } }
return Instance.tagQueue(request, new InvokeOptions().withRequestMarshaller(RequestMarshaller.Instance).withResponseUnmarshaller(ResponseUnmarshaller.Instance));
public void remove() { throw new UnsupportedOperationException(); }
return (request, options) -> {      InvokeOptions invokeOptions = new InvokeOptions();      RequestMarshaller.getInstance().setOptions(invokeOptions);      ResponseUnmarshaller.getInstance().setOptions(invokeOptions);      return ModifyCacheSubnetGroup(request, invokeOptions);  };
public void setParams(String paramsStr) { StringTokenizer st = new StringTokenizer(paramsStr, " "); String culture = null; if (st.hasMoreTokens()) { culture = st.nextToken() + " "; } if (st.hasMoreTokens()) { culture = st.nextToken(); } if (st.hasMoreTokens()) { culture = st.nextToken(); } }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { InvokeOptions options = new InvokeOptions(); return this.invoke(request, RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance(), options); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Components.length != other.Components.length) return false; for (int i = Components.length - 1; i >= 0; --i) if (!Components[i].equals(other.Components[i])) return false; return true; }
return invoke(new GetInstanceAccessDetailsRequestMarshaller().marshall(request), GetInstanceAccessDetailsResponseUnmarshaller.getInstance(), new InvokeOptions());
HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(); Parent.shape.Add(shapes.OnCreate()); shape.Anchor = anchor; return shape; }
public String GetSheetName(int sheetIndex) { return Sheetname.GetBoundSheetRec(); }
return DashboardInvoker.invoke(request, new InvokeOptions().setResponseUnmarshaller(Instance.getDashboardResponseUnmarshaller()).setRequestMarshaller(Instance.getDashboardRequestMarshaller()));
return instance.associateSigninDelegateGroupsWithAccount(request, new InvokeOptions().setRequestMarshaller(RequestMarshaller.getInstance()).setResponseUnmarshaller(ResponseUnmarshaller.getInstance()));
void AddMultipleBlanks() { for (int j = 0; j < NumColumns.mbr; ++j) { BlankRecord br = new BlankRecord(); br.Row = Row.mbr; br.FirstColumn = mbr + j; br.XFIndex = GetXFAt(XFIndex.mbr); InsertCell(br); } }
public static String quote(String string) { StringBuilder sb = new StringBuilder(); int apos = 0; int k = string.indexOf("\\"); while (k >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 1; k = string.indexOf("\\", apos); } sb.append(string.substring(apos)); return sb.toString(); }
public void putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public class ArrayPtg { private short _reserved2Byte = 0; private short _reserved1Short = 0; private int _reserved0Int = 0; private short _nRows; private short _nColumns; private Object[] _arrayValues; public ArrayPtg(Object[][] values2d) { int nRows = values2d.length; int nColumns = values2d[0].length; _nRows = (short) nRows; _nColumns = (short) nColumns; _arrayValues = new Object[_nRows * _nColumns]; for (int c = 0; c < nColumns; c++) { for (int r = 0; r < nRows; r++) { _arrayValues[r + c * _nRows] = values2d[r][c]; } } } }
return GetIceServerConfigResponse.unmarshall(Invoke.invoke(new GetIceServerConfigRequestMarshaller(), request, new GetIceServerConfigResponseUnmarshaller(), options));
public String toString() { return new StringBuilder().append(getClass().getSimpleName()).append(" ").append(GetValueAsString()).append(" ").toString(); }
public String toString() { return " " + _parentQuery + " " + field; }
public void IncRef() { refCount.incrementAndGet(); }
return client.updateConfigurationSetSendingEnabled(request, new InvokeOptions());
int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { TenPower tp = TenPower.getInstance().tenPower(Math.abs(pow10)); mulShift(_divisorShift, _divisor, tp); } else { TenPower tp = TenPower.getInstance().tenPower(Math.abs(pow10)); mulShift(_multiplierShift, _multiplicand, tp); } }
public String toString(){StringBuilder builder=new StringBuilder();int length=Length;builder.append(File.separatorChar);for(int i=0;i<length;i++){builder.append(getComponent(i));if(i<length-1){builder.append(File.separatorChar);}}return builder.toString();}
new ECSMetadataServiceCredentialsFetcher().withRoleName("roleName").withFetcher(fetcher);
public void setProgressMonitor(ProgressMonitor pm) { } ProgressMonitor pm;
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) { ParseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) return iterator.previous(); else throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (value == mValues[i]) { return i; } } return 1; }
public List<CharsRef> UniqueStems(char[] word, int length) { @SuppressWarnings("deprecation") CharArraySet terms = new CharArraySet(dictionary, 8, 612, 618); List<CharsRef> stems = new ArrayList<>(); for (int i = 0; i < length; i++) { stems.add(new CharsRef(word[i])); } List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } return stems.size() < 2 ? stems : deduped; }
return client.invoke(request, GetGatewayResponsesRequestMarshaller.getInstance(), GetGatewayResponsesResponseUnmarshaller.getInstance(), new InvokeOptions());
public void setPosition(long position) { currentBlockIndex = (int)(position >> OuterClass.this.blockBits); currentBlock = OuterClass.this.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & OuterClass.this.blockMask); }
int s = (int) Math.min(Available(), Math.max(ptr, Skip(n)));
public BootstrapActionConfig bootstrapActionConfig(BootstrapActionDetail detail)
public void serialize(LittleEndianOutput out1) { if (field_7_padding != null) out1.writeByte((byte)Integer.parseInt(field_7_padding)); if (field_5_hasMultibyte) StringUtil.putUnicodeLE(field_6_author, out1); else StringUtil.putCompressedUnicode(field_6_author, out1); out1.writeByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); out1.writeShortLE(field_6_author.length()); out1.writeShortLE(...); out1.writeShortLE(...); out1.writeShortLE(...); out1.writeShortLE(...); out1.writeShortLE(...); }
public int lastIndexOf(String string) { return someString.lastIndexOf(string); }
boolean addLastImpl(E object) { return add; }
public void unsetSection(String section,String subsection){ConfigSnapshot snapshot;do{snapshot=state.get();}while(!state.compareAndSet(snapshot,unsetSection(snapshot,section,subsection)));}
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { subrecords.add(index, element); }
public boolean remove(Object object) { synchronized(mutex) { return c.remove(object); } }
() -> new DoubleMetaphoneFilter()
long Length() { return InCoreLength; }
void MethodName(ParameterType ParameterName) { /* method body */ }
public class Pair { public ContentSource newSource; public ContentSource oldSource; public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; } }
public int Get(int i) { if (i <= count) return entries[i]; else throw new Extensions.Sharpen.CreateIndexOutOfRangeException(); }
@PutMapping("UriPattern")public MethodType Method(CreateRepoRequest request){}
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
void remove() { if (modCount == expectedModCount) { if (lastLink != null) { lastLink.previous.next = next; next.previous = lastLink.previous; modCount++; size--; expectedModCount++; lastLink = null; } else { throw new ConcurrentModificationException(); } } else { throw new ConcurrentModificationException(); } }
return invoke(request, new InvokeOptions(), mergeShardsRequestMarshaller, mergeShardsResponseUnmarshaller);
AllocateHostedConnectionResponse response = client.invoke(request, AllocateHostedConnectionRequestMarshaller.INSTANCE, AllocateHostedConnectionResponseUnmarshaller.INSTANCE, new InvokeOptions());
returnType methodName(parameters) { body }
public WeightedTerm[] GetTerms(Query query) { return ...; }
public void compact() { throw new java.nio.ReadOnlyBufferException(); }
public static void decode(int iterations,int valuesOffset,int[] values,int blocksOffset,byte[] blocks){for(int i=0;i<iterations;i++){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=(byte0>>2);int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=(byte0&3)<<4|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=(byte1&15)<<2|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public static String GetHumanishName(String s) { if (s == null || s.isEmpty()) throw new IllegalArgumentException(); String[] elements = s.split(" |\\" + (File.separatorChar == '\\' ? "\\" : File.separator), -1); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (".git".equals(result)) result = elements[elements.length - 2]; if (result.endsWith(".git")) result = result.substring(0, result.length() - 4); return result; }
service.invoke(new DescribeNotebookInstanceLifecycleConfigRequest(), new DescribeNotebookInstanceLifecycleConfigResponseHandler(), new DescribeNotebookInstanceLifecycleConfigRequestMarshaller(), new DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller(), new InvokeOptions());
public string GetAccessKeySecret() {     return AccessSecret; }
return invoke(request, new InvokeOptions(), CreateVpnConnectionRequestMarshaller.INSTANCE, CreateVpnConnectionResponseUnmarshaller.INSTANCE);
DescribeVoicesResponse response = DescribeVoicesClient.getInstance().describeVoices(request, new InvokeOptions().setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()));
ListMonitoringExecutionsResponse invokeResult = Invoke.<ListMonitoringExecutionsRequest, ListMonitoringExecutionsResponse>newBuilder().requestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).responseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()).build().invoke(request);
public record DescribeJobRequest(string jobId, string vaultName);
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return Invoke.invoke(request -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.getApisRequestMarshaller()); options.setResponseUnmarshaller(Instance.getApisResponseUnmarshaller()); return GetApisResponse; });
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance(), DeleteSmsChannelResponse.class); }
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { System.out.println(Boolean.toString(b)); }
public IQueryNode[] getChildren() { return getChild(); }
public static int NotIgnoredFilter(int workdirTreeIndex) { return workdirTreeIndex; }
public class AreaRecord { private short field_1_formatFlags; public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); } }
new HTTPS.ProtocolType().setProtocol(GetThumbnailRequest("", "", "", "", ""));
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, new DescribeTransitGatewayVpcAttachmentsRequestMarshaller(), new DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller(), options); }
return invoke(new PutVoiceConnectorStreamingConfigurationRequest(), new InvokeOptions().setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.INSTANCE).setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.INSTANCE));
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
return startIndex >= 0 && startIndex < input.size() ? String.format(java.util.Locale.getDefault(), "%s", EscapeWhitespace.utils(input.getText(new org.antlr.v4.runtime.misc.Interval(startIndex, startIndex)))) : throw new org.antlr.v4.runtime.LexerNoViableAltException();
public E peek() { return peekFirstImpl(); }
return Invoke.<CreateWorkspacesResponse>invoke(request, CreateWorkspacesRequestMarshaller.INSTANCE, CreateWorkspacesResponseUnmarshaller.INSTANCE, new InvokeOptions());
@Override public NumberFormatIndexRecord clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
DescribeRepositoriesResponse response = client.invoke(new DescribeRepositoriesRequestMarshaller().marshall(request), new DescribeRepositoriesResponseUnmarshaller(), new ExecutionContext(client.getConfiguration(), requestMetrics));
public SparseIntArray(int initialCapacity) { initialCapacity = android.util.ArrayUtils.idealIntArraySize(initialCapacity); mValues = new int[initialCapacity]; mKeys = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter();
return new CreateDistributionWithTagsResponse(); } ; someService.invoke(new CreateDistributionWithTagsRequestMarshaller(), new CreateDistributionWithTagsResponseUnmarshaller(), new InvokeOptions());
public RandomAccessFile someMethod(String fileName, String mode) { throw new UnsupportedOperationException(); }
return Invoke(new DeleteWorkspaceImageRequest(), InvokeOptions.builder().requestMarshaller(DeleteWorkspaceImageRequestMarshaller.INSTANCE).responseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.INSTANCE).build());
public static String ToHex(int value) { return Long.toHexString(value).toUpperCase(); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request, InvokeOptions options) { options = new InvokeOptions(); options.setRequestMarshaller(UpdateDistributionRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.INSTANCE); return invoke(request, options); }
HSSFColor GetColor(short index){if(index==HSSFColor.AUTOMATIC.index)return HSSFColor.AUTOMATIC.getInstance().getColor();else{byte[]b=GetColor(palette,null);if(b!=null)return new CustomColor(b);else return null;}}
public ValueEval Evaluate(int srcCol, int srcRow, ValueEval[] operands) { throw new NotImplementedException(); }
void serialize(LittleEndianOutput out1) { out1.writeShort((short)field_2_sheet_table_index); out1.writeShort((short)field_1_number_crn_records); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
public class FormatRun { public short fontIndex; public short character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResponse UploadArchive(UploadArchiveRequest request, InvokeOptions options) { return Invoke.<UploadArchiveRequest, UploadArchiveResponse>builder().requestMarshaller(UploadArchiveRequestMarshaller.getInstance()).responseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()).build().invoke(request, options); }
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null ? other.m_term != null : !m_term.equals(other.m_term)) return false; return true; }
public SpanQuery makeSpanClause(Map<SpanQuery, Float> weightBySpanQuery) { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> entry : weightBySpanQuery.entrySet()) { SpanQuery key = entry.getKey(); Float value = entry.getValue(); key.setBoost(value); spanQueries.add(key); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public static StashCreateCommand StashCreate() { return new StashCreateCommand(); }
return byName.get(fieldName);
private DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request, InvokeOptions options) { return invoke(request, new InvokeOptions().setRequestMarshaller(DescribeEventSourceRequestMarshaller.INSTANCE).setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.INSTANCE)); }
return getDocumentAnalysis(request, new InvokeOptions().setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()));
BiFunction<CancelUpdateStackRequest, InvokeOptions, CancelUpdateStackResponse> function = (request, options) -> { InvokeOptions newOptions = new InvokeOptions(); newOptions.setRequestMarshaller(RequestMarshaller.Instance); newOptions.setResponseUnmarshaller(ResponseUnmarshaller.Instance); return invoke(request, newOptions); };
public class YourClass { public ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(new ModifyLoadBalancerAttributesRequestMarshaller()); options.setResponseUnmarshaller(new ModifyLoadBalancerAttributesResponseUnmarshaller()); return Invoke(request, options); } }
SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.SetInstanceProtectionRequestMarshaller); options.setResponseUnmarshaller(Instance.SetInstanceProtectionResponseUnmarshaller); return invoke(request, options); }
return Invoke.<ModifyDBProxyResponse>invoke(new InvokeOptions(), request, ModifyDBProxyRequestMarshaller.getInstance(), ModifyDBProxyResponseUnmarshaller.getInstance(), ModifyDBProxyResponse.class);
public void add(int posLength, int endOffset, int len, int offset, char[] output) { if (outputs == null || count == outputs.length) { CharsRef[] newOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; } if (posLengths.length == count) { int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } if (endOffsets.length == count) { int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; } outputs[count] = new CharsRef(output, offset, len); posLengths[count] = posLength; endOffsets[count] = endOffset; count++; }
// Invalid C# code provided; no direct translation possible. Example Java placeholder: class FetchLibrariesRequest { public static void main(String[] args) { /* HTTPS request logic */ } }
public boolean exists() { return !objects.isEmpty(); }
public class MyFilterOutputStream extends java.io.FilterOutputStream { public MyFilterOutputStream(java.io.OutputStream out) { super(out); } }
@PutMapping("UriPattern") void Method(@RequestBody ScaleClusterRequest request);
public static DVConstraint CreateTimeConstraint(String formula2, String formula1, int operatorType) { return new DVConstraint(formula2, formula1, operatorType); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { InvokeOptions options = new InvokeOptions(); return this.invoke(request, options); }
return awsClient.invoke(new DescribeCacheSubnetGroupsRequest(), new DescribeCacheSubnetGroupsRequestMarshaller(), new DescribeCacheSubnetGroupsResponseUnmarshaller(), new InvokeOptions());
void SetSharedFormula(boolean flag) { sharedFormula.field_5_options.SetShortBoolean(flag); }
public boolean isIsReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); Parent.AddChild(t); return t; }
public LatvianStemFilterFactory(java.util.Map<String,String> args) { super(args); if (args.size() > 0) throw new IllegalArgumentException("args "); }
return ResponseUnmarshaller.getInstance().unmarshall(Instance.invoke(RequestMarshaller.getInstance().marshall(request), new InvokeOptions()));
public static TokenFilterFactory.NewInstance.Loader forName(Map<String, String> args, String name) { return new TokenFilterFactory.NewInstance.Loader(args, name); }
public class AddAlbumPhotosRequest {     public AddAlbumPhotosRequest(string param1, string param2, string param3, string param4, string param5)     {         // Initialization logic     } }
return GetThreatIntelSetResponse.unmarshall(GetThreatIntelSetRequest.marshall(request), new InvokeOptions());
public TreeFilter Clone() { return new Binary.AndTreeFilter(Clone.a, Clone.b); }
public boolean Equals(Object o){return o instanceof Equals;}
public boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestMarshaller.Instance); options.setResponseUnmarshaller(ResponseUnmarshaller.Instance); return invoke(request, options); }
void UnwriteProtectWorkbook() { records.Remove(); records.Remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer, expand, dedup); }
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); RequestSpotInstancesRequestMarshaller marshaller = RequestSpotInstancesRequestMarshaller.getInstance(); RequestSpotInstancesResponseUnmarshaller unmarshaller = RequestSpotInstancesResponseUnmarshaller.getInstance(); return invoke(request, marshaller, unmarshaller, options); }
public byte[] GetObjectData() {     return ObjectData.FindObjectRecord(); }
return Invoke.<GetContactAttributesResponse>invoke(new GetContactAttributesRequest(), InvokeOptions.builder().build(), GetContactAttributesRequestMarshaller.getInstance(), GetContactAttributesResponseUnmarshaller.getInstance());
public String toString() { return GetKey() + " " + GetValue(); }
ListTextTranslationJobsResponse response = client.invoke(request, new ListTextTranslationJobsRequestMarshaller(), new ListTextTranslationJobsResponseUnmarshaller(), new InvokeOptions());
GetContactMethodsResponse GetContactMethods = (request) -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactMethodsRequestMarshaller.Instance); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.Instance); return Invoke(request, options); };
public short lookupIndexByName(String name) { FunctionMetadata fd = getFunctionByNameInternal(name); if (fd == null) return -1; return (short) fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.DescribeAnomalyDetectorsRequestMarshaller); options.setResponseUnmarshaller(Instance.DescribeAnomalyDetectorsResponseUnmarshaller); return invoke(request, options); }
public String InsertId(String message, ObjectId changeId) { return InsertId; }
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(objectId, typeHint); if (sz > 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(" ", Copy.objectId()); throw new MissingObjectException(Copy.objectId()); } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); return (ImportInstallationMediaResponse) this.invoke(this.RequestMarshaller.marshall(request), this.ResponseUnmarshaller, options); }
return new PutLifecycleEventHookExecutionStatusResponse(); } ; private PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); RequestMarshaller = new PutLifecycleEventHookExecutionStatusRequestMarshaller(); ResponseUnmarshaller = new PutLifecycleEventHookExecutionStatusResponseUnmarshaller(); return Invoke(request, options, RequestMarshaller, ResponseUnmarshaller);
public void readDouble(LittleEndianInput in1) { field_1_value = in1.readDouble(); }
return GetFieldLevelEncryptionConfigResponse.invoke(request, new InvokeOptions().setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.INSTANCE).setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.INSTANCE));
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()); return invoke(request, options, DescribeDetectorResponse.class); }
public ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller); options.setResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller); return invoke(request, options); }
return Invoke(DeleteAlarmRequestMarshaller.getInstance().marshall(request), DeleteAlarmResponseUnmarshaller.getInstance(), options = new InvokeOptions());
public TokenStream Create(TokenStream input) {     return new PortugueseStemFilter(); }
public class FtCblsSubRecord { private byte[] reserved = new byte[ENCODED_SIZE]; }
public boolean remove(Object object, Object mutex) { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(GetDedicatedIpRequestMarshaller.Instance()).setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.Instance())); }
@Override public String toString() { return " " + precedence; }
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance())); }
void DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName);
public class WindowProtectRecord {     private options _options; }
public class UnbufferedCharStream { private int[] data; private int n; public UnbufferedCharStream(int bufferSize) { data = new int[bufferSize]; n = 0; } }
public GetOperationsResponse getOperations(GetOperationsRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(Instance.getGetOperationsRequestMarshaller()).setResponseUnmarshaller(Instance.getGetOperationsResponseUnmarshaller())); }
public static void CopyRawTo(byte[] b, int o) { EncodeInt32.NB(b, o + 16); EncodeInt32.NB(b, o + 12); EncodeInt32.NB(b, o + 8); EncodeInt32.NB(b, o + 4); EncodeInt32.NB(b, o); }
public class WindowOneRecord { short field_9_tab_width_ratio; short field_8_num_selected_tabs; short field_7_first_visible_tab; short field_6_active_sheet; short field_5_options; short field_4_height; short field_3_width; short field_2_v_hold; short field_1_h_hold; public WindowOneRecord(RecordInputStream in1) { field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort(); } }
return invoke(new StopWorkspacesRequest(), new RequestOptions().setRequestMarshaller(Instance.StopWorkspacesRequestMarshaller).setResponseUnmarshaller(Instance.StopWorkspacesResponseUnmarshaller).setOptions(options));
public void close() throws IOException { if (isOpen) { try { dump(); try { } finally { channel.truncate(channel.position()); } try { } finally { channel.close(); } } finally { fos.close(); } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, DescribeMatchmakingRuleSetsRequestMarshaller.getInstance(), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance(), options); }
String GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String GetPath() { return pathStr; }
public static double devsq(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){int n=v.length;double s=0,m=0;for(int i=0;i<n;i++)s+=v[i];m=s/n;for(int i=0;i<n;i++)s+=(v[i]-m)*(v[i]-m);r=s;}return r;}
public DescribeResizeResponse DescribeResize(DescribeResizeRequest request) { return client.invoke(request, DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance(), new InvokeOptions()); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
public void traverse(ICellHandler handler) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(); int firstRow = range.getFirstRow(), lastRow = range.getLastRow(), firstColumn = range.getFirstColumn(), lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { IRow currentRow = sheet.getRow(rowNumber); if (currentRow == null) continue; for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { ICell currentCell = currentRow.getCell(colNumber); if (currentCell == null) continue; if (!traverseEmptyCells && IsEmpty(currentCell)) continue; ctx.ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn + 1); handler.OnCell(ctx, ctx.ordinalNumber); } } }
public int GetReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (Term.BytesEquals(other.Term)) { if (Boost == other.Boost) return 0; else return Boost.compareTo(other.Boost); } else return Term.compareTo(other.Term); }
public static int Normalize(char[] s, int len) { for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: len = Delete.StemmerUtil(s, len); --i; break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
DiagnosticErrorListener diagnosticErrorListener = (boolean exactOnly) -> exactOnly;
public class KeySchemaElement { private KeyType _keyType; private String _attributeName; public KeySchemaElement(KeyType KeyType, String attributeName) { this._keyType = KeyType; this._attributeName = attributeName; } }
return invoke(request, options, GetAssignmentRequestMarshaller.getInstance(), GetAssignmentResponseUnmarshaller.getInstance());
public boolean HasObject(AnyObjectId id) { return FindOffset() != 1; }
public boolean setAllGroups(boolean allGroups) { return allGroups; }
private void setMultiValued(String dimName, boolean v){synchronized(fieldTypes){if(!fieldTypes.containsKey(dimName))fieldTypes.put(dimName,new DimConfig());fieldTypes.get(dimName).isMultiValued=v;}}
public int GetCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = At(c); if (e.e >= 0) ++size; } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { return invoke(request, new InvokeOptions(), DeleteVoiceConnectorRequestMarshaller.getInstance(), DeleteVoiceConnectorResponseUnmarshaller.getInstance()); }
DeleteLifecyclePolicyResponse response = invoke(request, new InvokeOptions().withMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()));
