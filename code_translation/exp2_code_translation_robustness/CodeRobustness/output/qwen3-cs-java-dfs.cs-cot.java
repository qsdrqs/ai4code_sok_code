void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void AddAll(NGit.Util.BlockList<T> src) { if (src.size == 0) return; if (tailBlkIdx.src != 0) { AddAll(tailBlock.src); } for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; ++srcDirIdx) { AddAll(directory[srcDirIdx]); } }
public void writeByte(byte b) { if (blockSize.outerInstance == upto.outerInstance) { if (currentBlock.outerInstance != null) { blocks.outerInstance.add(currentBlock.outerInstance); blockEnd.outerInstance.add(upto.outerInstance); } currentBlock.outerInstance = new byte[outerInstance.blockSize]; upto.outerInstance = 0; } currentBlock.outerInstance[upto.outerInstance++] = b; }
public ObjectId GetObjectId() { return objectId; }
public static InvokeResult<DeleteDomainEntryResponse> deleteDomainEntry(DeleteDomainEntryRequest request, InvokeOptions options) { options = new InvokeOptions(); return invoke(request, DeleteDomainEntryRequestMarshaller.INSTANCE, DeleteDomainEntryResponseUnmarshaller.INSTANCE, options); }
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(); if (msgB < 0) return ""; Encoding enc = RawParseUtils.parseEncoding(); return RawParseUtils.decodeLength(raw); }
Object _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock(); POIFSFileSystem fs = new POIFSFileSystem();
void Init(int address) { assert offset0 == (address & ByteBlockPool.BYTE_BLOCK_MASK); slice = Buffers.pool[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; }
public void setPath(String path) { return; }
return Instance.listIngestions(request, new InvokeOptions());
public void SwitchTo(int lexState, ICharStream stream)
(Request<GetShardIteratorRequest> request, InvokeOptions options) -> Instance.GetShardIterator(request, new InvokeOptions().setUnmarshaller(Instance.GetShardIteratorResponseUnmarshaller).setMarshaller(Instance.GetShardIteratorRequestMarshaller))
@PostMapping public MethodType method(String p1, String p2, String p3, String p4, String p5) { /* ... */ }
boolean ready() throws IOException { synchronized(lock) { if (in == null) throw new IOException(" "); try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
private EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { synchronized (this) { if (buffer == null) { throw new NullPointerException("buffer"); } util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = (length < pos - count) ? length : pos - count; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) buffer[pos + i]; } pos += copylen; return copylen; } }
public class OpenNLPSentenceBreakIterator { public void NLPSentenceDetectorOp(SentenceOp sentenceOp) { sentenceOp.sentenceOp(); } }
public void print(String str) { if (str != null) System.out.print(str); }
throw new NotImplementedFunctionException(cause);
return value.nextEntry();
private void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (available <= 0) { if (useBuffer && bufferSize < len) { Refill(); } available = bufferLength - bufferPosition; } if (available > 0) { if (available <= len) { System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available; } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > Length) { throw new java.io.EOFException("End of stream."); } System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } }
return invoke(request, new InvokeOptions(), TagQueueRequestMarshaller.INSTANCE, TagQueueResponseUnmarshaller.INSTANCE);
void Remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.getModifyCacheSubnetGroupRequestMarshaller()); options.setResponseUnmarshaller(Instance.getModifyCacheSubnetGroupResponseUnmarshaller()); return invoke(request, options); }
public static void SetParams(String culture, String... params) { StringTokenizer st = new StringTokenizer(culture, " "); if (Current.st != null) { MoveNext.st("ignore"); } if (Current.st != null) { MoveNext.st(Current.st + " " + culture); } if (Current.st != null) { MoveNext.st(Current.st, culture); } }
return client.DeleteDocumentationVersion(request, new InvokeOptions() {{ setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()); setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()); }});
@Override public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; --i) if (!Components[i].equals(other.Components[i])) return false; return true; }
GetInstanceAccessDetailsResponse response = Invoke(request, new InvokeOptions().setRequestMarshaller(Instance.GetInstanceAccessDetailsRequestMarshaller).setResponseUnmarshaller(Instance.GetInstanceAccessDetailsResponseUnmarshaller));
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(); parent.shape.add(shape); shape.onCreate(); return shape; }
public String getSheetName(int sheetIndex) { return Sheetname.getBoundSheetRec(); }
return Invoke.GetDashboard(request, new InvokeOptions().setRequestMarshaller(Instance.GetDashboardRequestMarshaller).setResponseUnmarshaller(Instance.GetDashboardResponseUnmarshaller));
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { return invoke(request, new InvokeOptions().setMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.INSTANCE).setUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.INSTANCE)); }
void AddMultipleBlanks(MBR mbr, int NumColumns) { for (int j = 0; j < NumColumns; ++j) { BlankRecord br = new BlankRecord(); InsertCell(GetXFAt(mbr.XFIndex, mbr.Row, mbr.FirstColumn + j, mbr.Column)); mbr.MulBlankRecord(br); } }
public static String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\")) >= 0) { sb.append(StringHelper.sharpen(string.substring(apos, k))); sb.append("\\\\"); apos = k + 2; } sb.append(StringHelper.sharpen(string.substring(apos))); sb.append("\""); return sb.toString(); }
public ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public static Object[][] ArrayPtg() { int _reserved0Int = 0; short _reserved1Short = 0; byte _reserved2Byte = 0; Object[] vv = new Object[_nRows * _nColumns]; int nColumns = (int) _nColumns; int nRows = (int) _nRows; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[c + r * _nColumns] = rowData[c]; } } return (Object[][]) vv; }
return invoke(request, () -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.Instance); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.Instance); return options; }, GetIceServerConfigResponse.class);
() -> new StringBuilder().append(" ").append(GetValueAsString()).append(" ").append(Name.getClass()).toString()
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
return amazonSimpleEmailService.updateConfigurationSetSendingEnabled(request, new InvokeOptions().setRequestMarshaller(Instance.getUpdateConfigurationSetSendingEnabledRequestMarshaller()).setResponseUnmarshaller(Instance.getUpdateConfigurationSetSendingEnabledResponseUnmarshaller()));
public int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = GetInstance.TenPower(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisorShift.tp, _divisor.tp); } else { mulShift(_multiplierShift.tp, _multiplicand.tp); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < length - 1) { builder.append(java.io.File.separatorChar); } } return builder.toString(); }
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.setRoleName(); }
void setProgressMonitor(ProgressMonitor pm) { }
public void Reset() { if (!First) { ptr = 0; if (!Eof) { ParseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) return iterator.previous(); else throw new java.util.NoSuchElementException(); }
public String GetNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (value == mValues[i]) return i; } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) { @SuppressWarnings("612,618") CharArraySet terms = new CharArraySet(dictionary, 8, false); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) deduped.add(s); } return deduped.size() < 2 ? stems : deduped; }
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()); return invoke(request, options); }
void setPosition(long position) { currentBlockIndex = (int)(position >> blockBits.outerInstance); currentBlockUpto = (int)(position & blockMask.outerInstance); }
int s=(int)Math.min(Available,Math.max(ptr,n));ptr=s;return s;
public BootstrapActionConfig getBootstrapActionConfig() { return _bootstrapActionConfig; }
public void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { out1.writeByte(Integer.parseInt(field_7_padding)); } else { if (field_5_hasMultibyte) { StringUtil.PutCompressedUnicode(out1, field_6_author); } else { StringUtil.PutUnicodeLE(out1, field_6_author); } } out1.writeByte(field_5_hasMultibyte ? 1 : 0); out1.writeShort(field_6_author.length()); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); }
public static int lastIndexOf(String string, String substr) { return string.lastIndexOf(substr); }
boolean addLastImpl(Object E) { return add(E); }
public void unsetSection(String section, String subsection, ConfigSnapshot snapshot) { ConfigSnapshot src, res; do { src = state.get(); res = unsetSection(src, section, subsection); } while (state.compareAndSet(src, res)); }
public String getTagName() { return tagName; }
void AddSubRecord(int index, SubRecord element) { Insert.subrecords; }
public boolean remove(Object object) { synchronized(mutex) { return c.remove(); } }
return new DoubleMetaphoneFilter(input);
public long Length() { return InCoreLength; }
public void setValue(boolean newValue) { value = newValue; }
public class Pair { public ContentSource newSource; public ContentSource oldSource; public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; } }
int Get(int i) { if (i <= count) throw new Extensions.Sharpen.CreateIndexOutOfRangeException(); return entries; }
@PutMapping("/uriPattern") public void createRepoRequest(@RequestParam String param1, @RequestParam String param2) {
public boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount == expectedModCount) { if (lastLink != null) { previous_1.next_1 = next_1; next_1.previous_1 = previous_1; modCount++; size--; expectedModCount++; lastLink = null; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); } }
return (MergeShardsResponse) Invoke(() -> {      InvokeOptions options = new InvokeOptions();      return MergeShards(new MergeShardsRequestMarshaller().marshall(request), options);  });
return Invoke.<AllocateHostedConnectionResponse>invoke(new AllocateHostedConnectionRequestMarshaller().marshall(request), new AllocateHostedConnectionResponseUnmarshaller(), new InvokeOptions(options));
int getBeginIndex() { return start; }
public static WeightedTerm[] GetTerms(Query query) { return null; }
public ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
public static void decode(int iterations, int valuesOffset, int[] values, int blocksOffset, byte[] blocks) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6 & 63); } }
public static String GetHumanishName() throws IllegalArgumentException { String s = GetPath(); if (s == null || s.isEmpty() || java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(FilePath.SeparatorChar + " " + Constants.DOT_GIT_EXT)).matcher(s).matches() || s.equals("")) throw new IllegalArgumentException(); String[] elements = java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(FilePath.SeparatorChar + " " + Constants.DOT_GIT_EXT)).matcher(s).find() ? s.split(java.util.regex.Pattern.quote(FilePath.SeparatorChar + " " + Constants.DOT_GIT_EXT)) : s.split(" "); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.endsWith(Constants.DOT_GIT)) result = result.substring(0, result.length() - Constants.DOT_GIT.length()); return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return invoke(request, new RequestMarshaller().marshall(request), new ResponseUnmarshaller(), new InvokeOptions()); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest requestToCreate) { InvokeOptions options = new InvokeOptions(); CreateVpnConnectionRequestMarshaller RequestMarshaller = Instance.CreateVpnConnectionRequestMarshaller(); CreateVpnConnectionResponseUnmarshaller ResponseUnmarshaller = Instance.CreateVpnConnectionResponseUnmarshaller(); CreateVpnConnectionRequest request = new CreateVpnConnectionRequest(); request.Name = requestToCreate.Name; return Invoke.<CreateVpnConnectionResponse>invoke(request, RequestMarshaller, ResponseUnmarshaller, options); }
return (DescribeVoicesResponse) invoke(new DescribeVoicesRequest(), new InvokeOptions().setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()));
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setInstance(RequestMarshaller.getInstance()); options.setInstance(ResponseUnmarshaller.getInstance()); return invoke(request, options); }
public class DescribeJobRequest{private String _jobId;private String _vaultName;public DescribeJobRequest(String jobId,String vaultName){this._jobId=jobId;this._vaultName=vaultName;}}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return request -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetApisRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.INSTANCE); return invoke(GetApisResponse.class, options, request); };
public DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); RequestMarshaller.getInstance().marshall(request, options); return (DeleteSmsChannelResponse) Invoke.invoke(ResponseUnmarshaller.getInstance().deleteSmsChannelResponseUnmarshaller(), options); }
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
public static void print(boolean b) { print(Boolean.toString(b)); }
public IQueryNode GetChild() { return GetChildren()[0]; }
void NotIgnoredFilter(int workdirTreeIndex) { }
class AreaRecord { short field_1_formatFlags; AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); } }
public class Protocol implements IDisposable { public void GetThumbnailRequest(String... args) {} }
return client.invoke(new DescribeTransitGatewayVpcAttachmentsRequest(), new DescribeTransitGatewayVpcAttachmentsRequestMarshaller(), new DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller(), new InvokeOptions());
PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {      PutVoiceConnectorStreamingConfigurationRequestMarshaller RequestMarshaller = new PutVoiceConnectorStreamingConfigurationRequestMarshaller();      PutVoiceConnectorStreamingConfigurationResponseUnmarshaller ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance();      InvokeOptions options = new InvokeOptions();      return Invoke(request, options, RequestMarshaller, ResponseUnmarshaller);  }
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
return String.format("No viable alternative at input '%s'", startIndex >= 0 ? interval.getText(inputStream) : "");
public E peek() { return peekFirstImpl(); }
return Instance.CreateWorkspacesResponseUnmarshaller.invoke(request, Instance.CreateWorkspacesRequestMarshaller, new InvokeOptions());
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
DescribeRepositoriesResponse response = client.DescribeRepositories(request, new InvokeOptions<DescribeRepositoriesRequest, DescribeRepositoriesResponse>().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()));
public class SparseIntArray { private int[] mKeys; private int[] mValues; private int mSize; public SparseIntArray(int initialCapacity) { initialCapacity = android.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; } }
public TokenStream Create(TokenStream input) { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return Invoke.invoke(request, options); }
public RandomAccessFile(String mode, String fileName) { new java.io.File(fileName); throw new UnsupportedOperationException(); }
private DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, DeleteWorkspaceImageResponse.class, options); }
public static String ToHex(int value) { return Long.toHexString((long)value); }
return (UpdateDistributionRequest request) -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.UpdateDistributionRequestMarshaller()); options.setResponseUnmarshaller(Instance.UpdateDistributionResponseUnmarshaller()); return Invoke(request, options); };
public HSSFColor getColor(short index) { return index == HSSFColor.AUTOMATIC.getIndex() ? HSSFColor.AUTOMATIC.getInstance() : b != null ? palette.getColor(b) : new CustomColor(); }
public ValueEval evaluate(int srcCol, int srcRow, ValueEval[] operands) throws NotImplementedFunctionException { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_2_sheet_table_index); out1.writeShort((short) field_1_number_crn_records); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return new DescribeDBEngineVersionsRequest().describeDBEngineVersions(); }
class FormatRun { short _fontIndex; short _character; FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; } }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[++resultIndex] = (byte)(ch >> 8); result[++resultIndex] = (byte)ch; } return result; }
return () -> UploadArchive(request, new InvokeOptions(), Instance.UploadArchiveRequestMarshaller, Instance.UploadArchiveResponseUnmarshaller);
public List<Token> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (obj == null) return false; if (obj.getClass() != getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else { if (!m_term.equals(other.m_term)) return false; } return true; }
public SpanQuery MakeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<?, ?> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(new SpanQuery(wsq.getKey(), wsq.getValue().Boost)); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreateCommand StashCreate() { return new StashCreateCommand(); }
return byName.get(fieldName);
DescribeEventSourceResponse response = describeEventSource(request, InvokeOptions.builder().requestMarshaller(Instance.DescribeEventSourceRequestMarshaller).responseUnmarshaller(Instance.DescribeEventSourceResponseUnmarshaller).build());
return GetDocumentAnalysisResponse.invoke(request, new InvokeOptions(), Instance.getDocumentAnalysisRequestMarshaller(), Instance.getDocumentAnalysisResponseUnmarshaller());
return Invoke(new CancelUpdateStackRequest(), new CancelUpdateStackResponse(), new InvokeOptions().setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()));
return request -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.ModifyLoadBalancerAttributesRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(Instance.ModifyLoadBalancerAttributesResponseUnmarshaller.INSTANCE); return options.invoke(request); };
public SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request) { SetInstanceProtectionResponseUnmarshaller responseUnmarshaller = Instance.SetInstanceProtectionResponseUnmarshaller; SetInstanceProtectionRequestMarshaller requestMarshaller = Instance.SetInstanceProtectionRequestMarshaller; InvokeOptions options = new InvokeOptions(); return Invoke(request, options); }
ModifyDBProxyResponse -> { Instance.ModifyDBProxyResponseUnmarshaller.unmarshall(request, options); Instance.ModifyDBProxyRequestMarshaller.marshall(request, options); options = new InvokeOptions(); return Instance.ModifyDBProxy(request, options); }
public void add(char[] output, int offset, int len, int endOffset, int posLength) { if (outputs == null) { outputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; } if (posLengths.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, posLengths.length); posLengths = next; } if (endOffsets.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, endOffsets.length); endOffsets = next; } outputs[count] = new CharsRef(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
class HTTPS { public ProtocolType Protocol(String p1, String p2, String p3, String p4, String p5) {} }
public boolean exists() { return objects.exists(); }
public class FilterOutputStream extends java.io.FilterOutputStream {
@PutMapping("/UriPattern") public void scaleClusterRequest() { }
public DVConstraint CreateTimeConstraint(String formula1, String formula2, int operatorType)
public static ListObjectParentPathsResponse invoke(ListObjectParentPathsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestMarshaller.Instance()); options.setResponseUnmarshaller(ResponseUnmarshaller.Instance()); return super.invoke(request, options); }
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void SetSharedFormula(boolean flag) { sharedFormula.field_5_options.SetShortBoolean(flag); }
public boolean isReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); Parent.t.AddChild(); return t; }
public class LatvianStemFilterFactory implements Map<String, String> { public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(args.toString() + " "); } } }
RemoveSourceIdentifierFromSubscriptionResponse response = client.RemoveSourceIdentifierFromSubscription(request, new InvokeOptions().setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance()).setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance()));
public static TokenFilterFactory ForName(String name, Map<String, String> args) { return new TokenFilterFactory(loader, args); }
public class HTTPS extends ProtocolType { public HTTPS(String a, String b, String c, String d, String e) { super(a, b, c, d, e); } }
return Invoke.<GetThreatIntelSetRequest, GetThreatIntelSetResponse>invoke(request, new InvokeOptions().setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()));
return new Binary.AndTreeFilter(a.clone(), b.clone());
public boolean Equals(Object o){return o instanceof ;}
protected boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()), UpdateContributorInsightsResponse.class); }
void UnwriteProtectWorkbook() { Remove.records(); Remove.records(); writeProtect = null; fileShare = null; }
public class SolrSynonymParser extends Analyzer { public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer); } }
public RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); RequestSpotInstancesRequestMarshaller RequestMarshaller = new Instance.RequestSpotInstancesRequestMarshaller(); RequestSpotInstancesResponseUnmarshaller ResponseUnmarshaller = new Instance.RequestSpotInstancesResponseUnmarshaller(); return Invoke.<RequestSpotInstancesResponse>invoke(request, options); }
public byte[] GetObjectData() { return FindObjectRecord().Data; } public ObjectData FindObjectRecord() { /* implementation */ }
return GetContactAttributesResponseUnmarshaller.getInstance().unmarshall(Invoke.invoke(GetContactAttributesRequestMarshaller.getInstance().marshall(request), new InvokeOptions()), GetContactAttributesRequestMarshaller.getInstance());
public String toString() { return getKey() + " " + getValue(); }
return client.invoke(request, ListTextTranslationJobsResponse.class, InvokeOptions.builder().requestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).responseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()).build());
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(Instance.getGetContactMethodsRequestMarshaller()).withResponseUnmarshaller(Instance.getGetContactMethodsResponseUnmarshaller()), GetContactMethodsResponse.class); }
public short LookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadata.GetInstance().GetFunctionByNameInternal(); if (fd == null) return -1; return fd.Index; }
DescribeAnomalyDetectorsResponse response = instance.<DescribeAnomalyDetectorsResponse>describeAnomalyDetectors(request, new InvokeOptions(), Instance.DescribeAnomalyDetectorsRequestMarshaller, Instance.DescribeAnomalyDetectorsResponseUnmarshaller);
public String InsertId(String message, ObjectId changeId) { return InsertId; }
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(objectId, typeHint); if (0 < sz) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(Copy.objectId, " "); } throw new MissingObjectException(Copy.objectId); } return sz; }
return Instance.invoke(request, new InvokeOptions().setRequestMarshaller(Instance.getImportInstallationMediaRequestMarshaller()).setResponseUnmarshaller(Instance.getImportInstallationMediaResponseUnmarshaller()));
public PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance())); }
field_1_value = new NumberPtg((ILittleEndianInput) in1).readDouble();
return Client.getFieldLevelEncryptionConfig(request, GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance(), new InvokeOptions());
public DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.DescribeDetectorRequestMarshaller); options.setResponseUnmarshaller(Instance.DescribeDetectorResponseUnmarshaller); return Invoke(request, options); }
return (ReportInstanceStatusRequest request) -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller()); options.setResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller()); return Invoke(request, options); };
DeleteAlarmResponse DeleteAlarm = (DeleteAlarmRequest request) -> { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(Instance.DeleteAlarmResponseUnmarshaller); options.setRequestMarshaller(Instance.DeleteAlarmRequestMarshaller); return Invoke(request, options); };
public TokenStream CreateTokenStream(TokenStream input) { return new PortugueseStemFilter(input); }
private byte[] reserved = new byte[ENCODED_SIZE];
public boolean removeObject(Object object) { synchronized (mutex) { return c.remove(object); } }
return (request) -> { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); return Instance.invoke(request, options); };
return precedence.toString() + " ";
return invoke(request, new InvokeOptions().setRequestMarshaller(Instance.listStreamProcessorsRequestMarshaller).setResponseUnmarshaller(Instance.listStreamProcessorsResponseUnmarshaller));
public class DeleteLoadBalancerPolicyRequest { private String _policyName; private String _loadBalancerName; public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this._policyName = policyName; this._loadBalancerName = loadBalancerName; } }
private int WindowProtectRecord(Options _options) { }
UnbufferedCharStream(int bufferSize) { data = new int[bufferSize]; n = 0; }
return Invoke.invoke(new GetOperationsRequest(), new InvokeOptions().setRequestMarshaller(RequestMarshaller.INSTANCE).setResponseUnmarshaller(ResponseUnmarshaller.INSTANCE));
void CopyRawTo(byte[] b, int o) { EncodeInt32.NB(b, o + 16); EncodeInt32.NB(b, o + 12); EncodeInt32.NB(b, o + 8); EncodeInt32.NB(b, o + 4); EncodeInt32.NB(b, o); }
public class WindowOneRecord { private short field_9_tab_width_ratio; private short field_8_num_selected_tabs; private short field_7_first_visible_tab; private short field_6_active_sheet; private short field_5_options; private short field_4_height; private short field_3_width; private short field_2_v_hold; private short field_1_h_hold; public WindowOneRecord(RecordInputStream in1) { field_9_tab_width_ratio = ReadShort(in1); field_8_num_selected_tabs = ReadShort(in1); field_7_first_visible_tab = ReadShort(in1); field_6_active_sheet = ReadShort(in1); field_5_options = ReadShort(in1); field_4_height = ReadShort(in1); field_3_width = ReadShort(in1); field_2_v_hold = ReadShort(in1); field_1_h_hold = ReadShort(in1); } }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); return Invoke(RequestMarshaller.Instance, request, options, ResponseUnmarshaller.Instance).getStopWorkspacesResponse(); }
void close() throws IOException { if (isOpen()) { try { dump(); } finally { channel.truncate(); } } try { channel.close(); } finally { fos.close(); } }
public static DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request, AmazonGameLiftClient client) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance());      options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance());      return client.invoke(request, options);  }
String GetPronunciation(int len, int off, char[] surface, int wordId) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v){if(v!=null&&v.length>=1){int n=v.length;double m=0;for(int i=0;i<n;i++){m+=v[i];}m/=n;double s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}return s;}return Double.NaN;}
DescribeResizeResponse response = invoke(new InvokeOptions().setRequestMarshaller(Instance.DescribeResizeRequestMarshaller).setResponseUnmarshaller(Instance.DescribeResizeResponseUnmarshaller), request);
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end(){return end();}
public static void Traverse(ICellHandler handler, SimpleCellWalkContext ctx) { int firstRow=range.FirstRow; int lastRow=range.LastRow; int firstColumn=range.FirstColumn; int lastColumn=range.LastColumn; int width=lastColumn-firstColumn+1; SimpleCellWalkContext ctx=new SimpleCellWalkContext(); IRow currentRow=null; ICell currentCell=null; for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;ctx.rowNumber++) { currentRow=sheet.GetRow(ctx.rowNumber); if(currentRow==null) continue; for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;ctx.colNumber++) { currentCell=currentRow.GetCell(ctx.colNumber); if(currentCell==null) continue; if(!traverseEmptyCells&&!currentCell.IsEmpty()) continue; ctx.ordinalNumber=(ctx.rowNumber-firstRow)*width+(ctx.colNumber-firstColumn)+1; handler.OnCell(ctx); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (Term.BytesEquals(other.Term)) { return 0; } else if (Boost == other.Boost) { return Term.compareTo(other.Term); } else { return Boost.compareTo(other.Boost); } }
public static int Normalize(char[] s, int len) { for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: Delete.StemmerUtil(s, len); return len; case HEH_GOAL: case HEH_YEH: break; case KAF: Delete.StemmerUtil(s, len); break; case KEHEH: break; case YEH_BARREE: case FARSI_YEH: break; default: --i; break; } } return len; }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
boolean DiagnosticErrorListener(ExactOnly exactOnly);
public class KeySchemaElement { private KeyType _keyType; private String _attributeName; public KeySchemaElement(KeyType KeyType, String attributeName) { this._keyType = KeyType; this._attributeName = attributeName; } }
GetAssignmentResponse GetAssignment = (request) -> Instance.GetAssignmentResponseUnmarshaller().Invoke(() -> new InvokeOptions() {{ RequestMarshaller = Instance.GetAssignmentRequestMarshaller(); }}, request);
public boolean HasObject(AnyObjectId id) { return FindOffset() != 1; }
public GroupingSearch SetAllGroups(boolean allGroups) { return allGroups; }
public void setMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig config = fieldTypes.get(dimName); if (config == null) { config = new DimConfig(); fieldTypes.put(dimName, config); } config.isMultiValued.fieldType = v; } }
public int getCellsVal() { int size = 0; for (char c : Keys.cells) { int e = Cell.At(c); if (cmd.e >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.getDeleteVoiceConnectorRequestMarshaller()); options.setResponseUnmarshaller(Instance.getDeleteVoiceConnectorResponseUnmarshaller()); return invoke(request, options, DeleteVoiceConnectorResponse.class); }
DeleteLifecyclePolicyResponse response = DeleteLifecyclePolicy(request, new InvokeOptions().setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.INSTANCE).setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.INSTANCE));
