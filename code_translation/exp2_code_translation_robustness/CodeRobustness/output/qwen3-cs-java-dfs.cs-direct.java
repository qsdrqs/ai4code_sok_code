void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
public static <T> void AddAll(BlockList.Util.NGit<T> src) { if (src.tailBlkIdx != 0) AddAll(src.tailBlock); for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx]); if (src.size == 0) return; }
public void WriteByte(byte b) { if (currentBlock.outerInstance != null) { if (currentBlock.outerInstance.blockSize == currentBlock.outerInstance.upto) { currentBlock.outerInstance.blocks.add(currentBlock.outerInstance.currentBlock); currentBlock.outerInstance.currentBlock = new byte[currentBlock.outerInstance.blockSize]; currentBlock.outerInstance.upto = 0; currentBlock.outerInstance.blockEnd.add(currentBlock.outerInstance.currentBlock); } currentBlock.outerInstance.currentBlock[++currentBlock.outerInstance.upto] = b; } }
public ObjectId GetObjectId() { return objectId; }
return invoke(RequestMarshaller.INSTANCE, ResponseUnmarshaller.INSTANCE, new InvokeOptions(), new DeleteDomainEntryRequest(request));
public long RamBytesUsed() { return fst == null ? 0 : fst.GetSizeInBytes(); }
public static String GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw); if (msgB < 0) { return ""; } Encoding enc = RawParseUtils.ParseEncoding(); return enc.decode(raw, 0, raw.length); }
} ; Object _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock(new POIFSFileSystem());
void Init(int address) { int offset0 = address & ByteBlockPool.BYTE_BLOCK_MASK; assert slice != null; assert slice.length >= upto; Buffers.pool.slice(address >> ByteBlockPool.BYTE_BLOCK_SHIFT); }
return path; } public String SetPath(NGit.Api.SubmoduleAddCommand path) {
public ListIngestionsResponse ListIngestions(ListIngestionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.ListIngestionsRequestMarshaller); options.setResponseUnmarshaller(Instance.ListIngestionsResponseUnmarshaller); return invoke(request, options); }
public void switchTo(int lexState, ICharStream stream) { ... }
(options, request) -> { InvokeOptions opts = new InvokeOptions(); return GetShardIteratorResponseUnmarshaller.INSTANCE.unmarshall(GetShardIteratorRequestMarshaller.INSTANCE.marshall(request, opts), opts); }
@PostMapping public void Method(String param1, String param2, String param3, String param4, @RequestBody ModifyStrategyRequest request) {}
}}}return false;}catch(java.io.IOException e){}try{return in.available()>0||bytes.hasRemaining();}finally{}if(in==null)throw new java.io.IOException(" ");synchronized(lock){}public boolean ready(){
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { synchronized (this) { if (buffer == null) throw new NullPointerException("buffer"); Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) return 0; int copylen = Math.min(length, pos - count); for (int i = 0; i < copylen; i++) buffer[pos + i] = buffer[offset + i]; return copylen; } }
sentenceOp = (OpenNLPSentenceBreakIterator) NLPSentenceDetectorOp();
void print(String str) { write(str != null ? Sharpen.StringHelper.GetValueOf((Object)null) : null); }
public class NotImplementedFunctionException extends Exception { public NotImplementedFunctionException(String functionName, Exception cause) { super(functionName, cause); } } public void functionName() { throw new NotImplementedFunctionException("functionName", cause); }
public V next() { return nextEntry().value; }
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer, int bufferSize) { int bufferLength = 0; int bufferPosition = 0; long bufferStart = 0; int available = bufferLength - bufferPosition; if (available <= len) { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && bufferSize < len) { Refill(); } else { throw new EndOfStreamException(""); } } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } }
return TagQueueResponse::class.java, (request) -> TagQueueRequest(request), new InvokeOptions().withRequestMarshaller(Instance.TagQueueRequestMarshaller).withResponseUnmarshaller(Instance.TagQueueResponseUnmarshaller);
void Remove() { throw new NotSupportedException(); }
return invoke -> { AwsInvokeOptions options = new AwsInvokeOptions(); options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()); return invoke.modifyCacheSubnetGroup(request, options); };
public void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) { Current.st = culture; st.nextToken(); } if (st.hasMoreTokens()) { Current.st = culture + " " + st.nextToken(); } if (st.hasMoreTokens()) { Current.st = st.nextToken(); } }
return Invoke(new DeleteDocumentationVersionRequest(), new InvokeOptions().setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.Instance).setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.Instance));
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (other.Components.length != Components.length) return false; for (int i = Components.length - 1; i >= 0; i--) { if (!other.Components[i].equals(Components[i])) return false; } return true; }
return invoke(new InvokeOptions<>(request, GetInstanceAccessDetailsRequestMarshaller.getInstance(), GetInstanceAccessDetailsResponseUnmarshaller.getInstance()));
HSSFPolygon shape = patriarch.createPolygon(new HSSFChildAnchor());
public String GetSheetName(int sheetIndex) { return GetBoundSheetRec().Sheetname; }
return invoke(request, new InvokeOptions().setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(request, options); }
void AddMultipleBlanks(MulBlankRecord mbr, Row Row, FirstColumn FirstColumn, int NumColumns, XFIndex XFIndex) { for (int j = 0; j < NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Row.mbr = Row.br; br.Column.mbr = FirstColumn.br + j; br.XFIndex = GetXFAt(XFIndex.br); mbr.InsertCell(br); ++j; } }
public static String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 1; } sb.append(string.substring(apos)); sb.append("\""); return sb.toString(); }
public int putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
int _reserved0Int=0;short _reserved1Short=0;byte _reserved2Byte=0;short _nRows=(short)values2d.length;short _nColumns=(short)values2d[0].length;Object[][]rowData=new Object[values2d.length][values2d[0].length];Object[]vv=new Object[_nRows*_nColumns];for(int r=0;r<values2d.length;r++)for(int c=0;c<values2d[0].length;c++)vv[GetValueIndex(c,values2d.length)]=rowData[r][c];
return Invoke.<GetIceServerConfigRequest, GetIceServerConfigResponse>builder().requestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).responseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()).build().invoke(request, options);
return new StringBuilder().append(getClass().getName()).append(" ").append(GetValueAsString()).append(" ").toString();
public String toString() { return " " + _parentQuery + " "; }
public void IncRef() { refCount.IncrementAndGet(); }
public <T> UpdateConfigurationSetSendingEnabledResponse<T> UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); RequestMarshaller<UpdateConfigurationSetSendingEnabledRequest> requestMarshaller = Instance.getUpdateConfigurationSetSendingEnabledRequestMarshaller(); ResponseUnmarshaller<UpdateConfigurationSetSendingEnabledResponse<T>> responseUnmarshaller = Instance.getUpdateConfigurationSetSendingEnabledResponseUnmarshaller(); return invoke(request, options, requestMarshaller, responseUnmarshaller); }
public int getNextXBATChainOffset() { return INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
void multiplyByPowerOfTen(int pow10) { TenPower tp = GetInstance.TenPower(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp, tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp, tp); } }
public String someMethod(){StringBuilder b=new StringBuilder();int l=getLength();b.append(File.separatorChar);for(int i=0;i<l;i++){b.append(getComponent(i));if(i<l-1)b.append(File.separatorChar);}return b.toString();}
void withFetcher() { ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName(); }
public void setProgressMonitor(ProgressMonitor pm) { }
void Reset() { if (!First) { ptr = 0; } if (!Eof) { ParseEntry(); } }
public E previous() { if (start >= iterator.previousIndex()) { return iterator.previous(); } else { throw new java.util.NoSuchElementException(); } }
public String GetNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) { List<CharsRef> deduped = new ArrayList<>(); CharArraySet terms = new CharArraySet(618, 612); for (CharsRef stem : stems) { if (!terms.contains(stem)) { deduped.add(stem); } } return stems.size() < 2 ? stems : deduped; }
return GetGatewayResponsesResponse.<InvokeOptions>invoke(new GetGatewayResponsesRequestMarshaller(), new GetGatewayResponsesRequest(), new GetGatewayResponsesResponseUnmarshaller(), options -> {});
public void SetPosition(long position) { int currentBlockIndex = (int)(position >> blockBits.outerInstance); int currentBlockUpto = (int)(position & blockMask.outerInstance); currentBlock = blocks.outerInstance[currentBlockIndex]; }
int s = (int) Math.min(Available(), ptr.skip(n).count());
private BootstrapActionConfig _bootstrapActionConfig; public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
void serialize(LittleEndianOutput out1) { if (field_7_padding != null) StringUtil.putCompressedUnicode(out1, field_6_author); else StringUtil.putUnicodeLE(out1, field_6_author); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); }
public static int lastIndexOf(String string, char c) { return string.lastIndexOf(c); }
boolean add(E object) { return addLastImpl(); }
void UnsetSection(String section,String subsection){do{ConfigSnapshot src=state.get();ConfigSnapshot res=UnsetSection(src,section,subsection);}while(!state.compareAndSet(src,res));}
public String getTagName() { return tagName; }
public void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
} } ; ) ( remove . c return { ) mutex ( synchronized { ) Object object ( boolean
public TokenStream CreateTokenStream(TokenStream input) { return new DoubleMetaphoneFilter(,,); }
public long Length() { return InCoreLength; }
public void setValue(boolean newValue) { value = newValue; }
new Pair<>(oldSource, newSource);
public int getInt(int i) { if (i <= count) { } else { throw new Extensions.Sharpen.CreateIndexOutOfRangeException(); } return entries; }
@PutMapping("UriPattern") public void CreateRepoRequest() { }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
void remove() { if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); if (link != lastLink) throw new IllegalStateException(); next_1.next.previous_1 = previous_1; previous_1.previous.next_1 = next_1; previous_1.lastLink = (java.util.LinkedList<ET>.Link) previous_1; next_1.lastLink = (java.util.LinkedList<ET>.Link) next_1; list.modCount++; list._size--; expectedModCount++; lastLink = null; pos--; }
public MergeShardsResponse MergeShards(MergeShardsRequest request) { return Invoke.<MergeShardsResponse>invoke(MergeShardsResponseUnmarshaller.Instance, MergeShardsRequestMarshaller.Instance, new InvokeOptions(), request); }
public AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request) { return Invoke.<AllocateHostedConnectionResponse>invoke(new InvokeOptions(), AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance(), request); }
int getBeginIndex() { return start; }
public WeightedTerm[] GetTerms(Object query) { return GetTerms((Query) query); }
() -> { throw new java.nio.ReadOnlyBufferException(); return java.nio.ByteBuffer.allocate(0).compact(); }
void Decode(int iterations, int valuesOffset, int[] values, int blocksOffset, byte[] blocks) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 >> 2) & 0x3F; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 << 4) & 0x30) | ((byte1 >> 4) & 0x0F); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 << 2) & 0x3C) | ((byte2 >> 6) & 0x03); } }
public String getHumanishName() { String s = GetPath(); if (s == null || s.isEmpty()) throw new IllegalArgumentException(); String[] elements; if (s.matches(".*[\\\\ ].*") || " ".equals(s)) elements = s.split(" "); else elements = s.split("\\\\ "); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.equals(Constants.DOT_GIT)) result = elements[elements.length - 2]; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); return invoke(request, options); }
public String getAccessKeySecret() { return accessSecret; }
(options, request) -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.CreateVpnConnectionRequestMarshaller); options.setResponseUnmarshaller(Instance.CreateVpnConnectionResponseUnmarshaller); return options.invoke(request); }
public DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request, InvokeOptions options) { options = new InvokeOptions(); RequestMarshaller = Instance.DescribeVoicesRequestMarshaller; ResponseUnmarshaller = Instance.DescribeVoicesResponseUnmarshaller; return Invoke(request, options, RequestMarshaller, ResponseUnmarshaller); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return Invoke.invoke(request, options); }
public class DescribeJobRequest { public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; } private String jobId; private String vaultName; }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return (request, options) -> { options = new InvokeOptions(); options.setResponseUnmarshaller(Instance.GetApisResponseUnmarshaller()); options.setRequestMarshaller(Instance.GetApisRequestMarshaller()); return Invoke(request, options); };
return (request, options) -> { InvokeOptions opts = new InvokeOptions(); opts.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.INSTANCE); opts.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.INSTANCE); return deleteSmsChannel(request, opts); };
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
public static void print(boolean b) { System.out.println(Boolean.toString(b)); }
public IQueryNode[] GetChildren() { return GetChild(); }
public class NotIgnoredFilter { public int get(int workdirTreeIndex) { } }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
}; HTTPS.ProtocolType Protocol { } (" ", " ", " ", " ", " ") : (GetThumbnailRequest)
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller responseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance(); DescribeTransitGatewayVpcAttachmentsRequestMarshaller requestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(); return invoke(request, requestMarshaller, responseUnmarshaller, options); }
PutVoiceConnectorStreamingConfigurationResponse response = invoke(new PutVoiceConnectorStreamingConfigurationRequest(), InvokeOptions.builder().requestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).responseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()).build());
public OrdRange getOrdRange(String dim) { OrdRange result = prefixToOrdRange.get(dim); return result; }
return String.format(Locale.getDefault(), "%s", Utils.escapeWhitespace(((ICharStream)input).getText(new Interval(startIndex, ((ICharStream)input).size() - 1)), true).equals("") ? "" : Utils.escapeWhitespace(((ICharStream)input).getText(new Interval(startIndex, ((ICharStream)input).size() - 1)), true));
public E peek() { return peekFirstImpl(); }
return client.invoke(new CreateWorkspacesRequest(), CreateWorkspacesResponse.class, new CreateWorkspacesRequestMarshaller(), new CreateWorkspacesResponseUnmarshaller(), new InvokeOptions());
@Override public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { return invoke(options -> { options.setRequestMarshaller(RequestMarshaller); options.setResponseUnmarshaller(ResponseUnmarshaller); }); }
public SparseIntArray(int initialCapacity) { mKeys = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; mValues = new int[mKeys.length]; mSize = 0; }
TokenStream CreateTokenStream(TokenStream input) { return new HyphenatedWordsFilter(); }
public static Invoke<CreateDistributionWithTagsResponse> CreateDistributionWithTags(CreateDistributionWithTagsRequest request, InvokeOptions options) { return new Invoke<>(request, options, Instance.CreateDistributionWithTagsRequestMarshaller, Instance.CreateDistributionWithTagsResponseUnmarshaller); }
public void someMethod(String fileName, String mode) { throw new UnsupportedOperationException(); new RandomAccessFile(fileName, mode); }
DeleteWorkspaceImageResponse response = invoke(new DeleteWorkspaceImageRequest(), new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()));
public static String toHex(int value) { return Long.toHexString((long)value); }
return invoke(request, options, UpdateDistributionRequestMarshaller.INSTANCE, UpdateDistributionResponseUnmarshaller.INSTANCE);
public static HSSFColor getColor(short index) { if (index == HSSFColor.AUTOMATIC.index) return HSSFColor.AUTOMATIC.getInstance(); else { byte[] b = palette.getColor(); return b != null ? new CustomColor(b) : null; } }
ValueEval Evaluate(int srcCol, int srcRow, ValueEval[] operands) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort((short)field_2_sheet_table_index); out1.writeShort((short)field_1_number_crn_records); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
public class FormatRun { private short _character; private short _fontIndex; public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; } }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
return invoke(request, UploadArchiveRequestMarshaller.getInstance(), UploadArchiveResponseUnmarshaller.getInstance(), new InvokeOptions());
public List<IToken> getHiddenTokensToLeft(int tokenIndex){return getHiddenTokensToLeft(tokenIndex-1);}
public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; return java.util.Objects.equals(m_term, other.m_term) && java.util.Objects.equals(m_compiled, other.m_compiled); }
public SpanQuery MakeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, ?> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
public StashCreateCommand StashCreate() { return new StashCreateCommand(); }
return byName.get(fieldName);
return (DescribeEventSourceResponse) Invoke(() -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.DescribeEventSourceRequestMarshaller); options.setResponseUnmarshaller(Instance.DescribeEventSourceResponseUnmarshaller); }, request, DescribeEventSourceRequest.class);
return GetDocumentAnalysisResponse.invoke(new InvokeOptions().setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()), request);
return new CancelUpdateStackResponse(Instance.CancelUpdateStackResponseUnmarshaller, Instance.CancelUpdateStackRequestMarshaller, new InvokeOptions());
return (software.amazon.awssdk.services.elasticloadbalancingv2.model.ModifyLoadBalancerAttributesRequest request) -> { software.amazon.awssdk.core.InvokeOptions options = new software.amazon.awssdk.core.InvokeOptions(); return (software.amazon.awssdk.services.elasticloadbalancingv2.model.ModifyLoadBalancerAttributesResponse) software.amazon.awssdk.core.internal.http.ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance().unmarshall(software.amazon.awssdk.services.elasticloadbalancingv2.model.ModifyLoadBalancerAttributesRequestMarshaller.getInstance().marshall(request), options); };
SetInstanceProtectionResponse SetInstanceProtection = (request) -> Invoke(request, SetInstanceProtectionRequestMarshaller.Instance, SetInstanceProtectionResponseUnmarshaller.Instance, new InvokeOptions());
return invoke(new ModifyDBProxyRequest(), options -> { options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); });
public void add(int posLength, int endOffset, int len, int offset, char[] output) { if (posLengths.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, posLengths.length); posLengths = next; } if (endOffsets.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, endOffsets.length); endOffsets = next; } if (outputs.length == count) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, outputs.length); outputs = next; } posLengths[count] = posLength; endOffsets[count] = endOffset; outputs[count] = new CharsRef(output, offset, len); count++; }
}; HTTPS.ProtocolType.Protocol("", "", "", "", "");
public boolean Exists() { return objects.Exists(); }
import java.io.FilterOutputStream;
@PUT @Path("UriPattern") public void MethodTypeMethod(ScaleClusterRequest request) { }
public static DVConstraint CreateTimeConstraint(String formula2, String formula1, int operatorType) { return new DVConstraint(formula2, formula1, operatorType); }
ListObjectParentPathsResponse response = invoke(new ListObjectParentPathsRequest(), new InvokeOptions().setRequestMarshaller(ListObjectParentPathsRequestMarshaller.Instance).setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.Instance));
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { return invoke(request, DescribeCacheSubnetGroupsRequestMarshaller.getInstance(), DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance(), new InvokeOptions()); }
void setSharedFormula(boolean flag) { field_5_options.setShortBoolean(flag); }
public boolean IsReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); Parent.AddChild(); return t; }
public LatvianStemFilterFactory(java.util.Map<java.lang.String, java.lang.String> args) { super(args); if (!args.isEmpty()) throw new java.lang.IllegalArgumentException(args + " "); }
return client.RemoveSourceIdentifierFromSubscription(request, new InvokeOptions().setRequestMarshaller(Instance.RemoveSourceIdentifierFromSubscriptionRequestMarshaller).setResponseUnmarshaller(Instance.RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller));
public static TokenFilterFactory ForName(String name, Map<String, String> args, ClassLoader loader) { return new TokenFilterFactory(); }
new AddAlbumPhotosRequest("", "", "", "", "");
return invoke(request, new InvokeOptions().setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()), GetThreatIntelSetResponse.class);
return new TreeFilter.Binary.AndTreeFilter(Clone.a(), Clone.b());
public boolean Equals(Object o) { return o instanceof MyClass; }
protected boolean hasArray() { return protectedHasArray(); }
return invoke(request, UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance(), new InvokeOptions());
public void UnwriteProtectWorkbook() { writeProtect = null; fileShare = null; Remove.records(); Remove.records(); }
public class SolrSynonymParser extends BaseClass { public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer); } }
public RequestSpotInstancesResponse invoke(Request request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ObjectData FindObjectRecord() { return GetObjectData(); }
return invoke(request, new InvokeOptions().setRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()), GetContactAttributesResponse.class);
public String toString() { return GetValue() + " " + GetKey(); }
return invoke(new ListTextTranslationJobsRequestMarshaller().marshall(request), new InvokeOptions().setResponseUnmarshaller(new ListTextTranslationJobsResponseUnmarshaller()), ListTextTranslationJobsResponse.class);
return invoke(request, GetContactMethodsResponse.class); InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance());
public short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(); if (fd == null) return -1; return (short)fd.Index; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String InsertId(ObjectId changeId, String message) { return InsertId; }
long GetObjectSize(int typeHint, AnyObjectId objectId, Db db) { long sz = db.GetObjectSize(typeHint, objectId); if (typeHint == OBJ_ANY) { if (sz < 0) { throw new MissingObjectException(" ", Copy.objectId()); } } return sz; }
public ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); RequestMarshaller.getInstance().marshall(request, options); return ResponseUnmarshaller.getInstance().unmarshall(options, ImportInstallationMediaResponse.class); }
InvokeOptions<PutLifecycleEventHookExecutionStatusRequest, PutLifecycleEventHookExecutionStatusResponse> options = new InvokeOptions<>(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options);
field_1_value = in1.readDouble();
private GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options, GetFieldLevelEncryptionConfigResponse.class); }
BiFunction<DescribeDetectorRequest, InvokeOptions, DescribeDetectorResponse> DescribeDetector = (request, options) -> invoke(new InvokeOptions(), RequestMarshaller.Instance.DescribeDetectorRequest, ResponseUnmarshaller.Instance.DescribeDetectorResponseUnmarshaller, request);
public ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { return Invoke(request, new InvokeOptions().setRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller).setResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller)); }
return invoke(request, DeleteAlarmResponse.class, options -> { options.setMarshaller(DeleteAlarmRequestMarshaller.Instance).setUnmarshaller(DeleteAlarmResponseUnmarshaller.Instance); });
public TokenStream createTokenStream(TokenStream input) { return new PortugueseStemFilter(); }
byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove(Object object) { synchronized(mutex) { return c.remove(object); } }
return Invoke.invoke(new GetDedicatedIpRequestMarshaller().marshall(request), InvokeOptions.builder().requestMarshaller(new GetDedicatedIpRequestMarshaller()).responseUnmarshaller(new GetDedicatedIpResponseUnmarshaller()).build(), new GetDedicatedIpResponseHandler());
public String toString() { return " " + precedence; }
() -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.ListStreamProcessorsRequestMarshaller); options.setResponseUnmarshaller(Instance.ListStreamProcessorsResponseUnmarshaller); return Invoke(options); }
public class DeleteLoadBalancerPolicyRequest { private String _policyName; private String _loadBalancerName; public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this._policyName = policyName; this._loadBalancerName = loadBalancerName; } }
int WindowProtectRecord(options _options) { }
public UnbufferedCharStream(int bufferSize) { int n = 0; int[] data = new int[bufferSize]; }
return Invoke(new GetOperationsRequest(), new InvokeOptions().setRequestMarshaller(GetOperationsRequestMarshaller.Instance).setResponseUnmarshaller(GetOperationsResponseUnmarshaller.Instance));
void CopyRawTo(byte[] b, int o) { EncodeInt32.NB(b, o + 16); EncodeInt32.NB(b, o + 12); EncodeInt32.NB(b, o + 8); EncodeInt32.NB(b, o + 4); EncodeInt32.NB(b, o); }
field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort();
return Invoke(request, new InvokeOptions().setRequestMarshaller(Instance.getStopWorkspacesRequestMarshaller()).setResponseUnmarshaller(Instance.getStopWorkspacesResponseUnmarshaller()));
public void close() throws IOException { if (isOpen) { try { try { try { dump(); } finally { channel.truncate(); } } finally { channel.close(); } } finally { fos.close(); } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return invoke(options, req -> new DescribeMatchmakingRuleSetsResponse()); }
public static String GetPronunciation(int len, int off, char[] surface, int wordId) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v) { double m = 0, s = 0, r = Double.NaN; if (v != null && v.length >= 1) { int n = v.length; for (int i = 0; i < n; i++) s += v[i]; m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = n != 1 ? s : Double.NaN; } return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); DescribeResizeRequestMarshaller marshaller = DescribeResizeRequestMarshaller.getInstance(); DescribeResizeResponseUnmarshaller unmarshaller = DescribeResizeResponseUnmarshaller.getInstance(); return invoke(request, marshaller, unmarshaller, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return 0; }
public void Traverse(ICellHandler handler) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(); int firstColumn = range.FirstColumn, lastColumn = range.LastColumn, firstRow = range.FirstRow, lastRow = range.LastRow; int width = 1 + firstColumn - lastColumn; for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { Row currentRow = sheet.GetRow(rowNumber); if (currentRow == null) continue; for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { Cell currentCell = currentRow.GetCell(colNumber); if (currentCell == null) continue; if (!traverseEmptyCells && currentCell.IsEmpty()) continue; handler.OnCell(currentCell, ctx); } } }
public int GetReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other){if(Term.BytesEquals(other.Term))return 0;if(Boost.compareTo(other.Boost)==0)return Term.compareTo(other.Term);else return Boost.compareTo(other.Boost);}
int Normalize(char[] s, int len) { for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: Delete.StemmerUtil(s, len, i); --i; break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
} ; boolean exactOnly exactOnly . { ( DiagnosticErrorListener
public class KeySchemaElement { private KeyType _keyType; private String _attributeName; public KeySchemaElement(KeyType KeyType, String attributeName) { this._keyType = KeyType; this._attributeName = attributeName; } }
return Invoke(request, GetAssignmentResponse, options -> { options.setResponseUnmarshaller(Instance.getGetAssignmentResponseUnmarshaller()); options.setRequestMarshaller(Instance.getGetAssignmentRequestMarshaller()); });
boolean HasObject(AnyObjectId id) { return FindOffset() != -1; }
boolean SetAllGroups(GroupingSearch allGroups) { return allGroups; }
public void setMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.isMultiValued = v; } }
public int GetCellsVal(){int size=0;for(char c:cells.keySet()){Cell e=At();if(e.cmd>=0)++size;}return size;}
return deleteVoiceConnector(new DeleteVoiceConnectorRequest(), new InvokeOptions().setRequestMarshaller(Instance.DeleteVoiceConnectorRequestMarshaller).setResponseUnmarshaller(Instance.DeleteVoiceConnectorResponseUnmarshaller));
return Invoke(new DeleteLifecyclePolicyRequest(), options, new DeleteLifecyclePolicyRequestMarshaller(), new DeleteLifecyclePolicyResponseUnmarshaller());
