void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public <T extends Entry> void addAll(BlockList<T> src) { if (src.tailBlkIdx == 0) return; for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx]); addAll(src.tailBlock, 0, src.tailBlkIdx); }
void WriteByte(byte b) { if (currentBlock == null || upto == blockSize) { currentBlock = new byte[blockSize]; upto = 0; blocks.add(currentBlock); } currentBlock[upto++] = b; }
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); return invoke(request, options); }
long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
public String getFullMessage() { Charset enc = RawParseUtils.parseEncoding(raw); int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.decode(enc, raw, msgB, raw.length); }
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(headerBlock); _documents = new ArrayList(); _root = null; }
assert slice != null && (address >> ByteBlockPool.BYTE_BLOCK_SHIFT) < pool.buffers.length && (address & ByteBlockPool.BYTE_BLOCK_MASK) < upto;
SubmoduleAddCommand.setPath(path);
return invoke(request, new InvokeOptions<>().setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()));
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(GetShardIteratorRequestMarshaller.INSTANCE).setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.INSTANCE)); }
@PostMapping public ResponseEntity<?> modifyStrategy(@RequestBody ModifyStrategyRequest request);
public boolean ready() throws java.io.IOException { synchronized (lock) { if (in == null) throw new java.io.IOException(" "); return bytes.hasRemaining() || in.available() > 0; } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new NullPointerException(); if (offset < 0 || length < 0 || length > buffer.length - offset) throw new IndexOutOfBoundsException(); if (length == 0) return 0; if (pos >= count) return -1; int copylen = Math.min(length, count - pos); for (int i = 0; i < copylen; i++) buffer[offset + i] = buf[pos + i]; pos += copylen; return copylen; }
} {  ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
void print(String str) { write(String.valueOf(str)); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
public V next() { return nextEntry().value; }
public void readBytes(byte[] b, int offset, int len) throws java.io.IOException { int available = this.bufferLength - this.bufferPosition; if (available >= len) { System.arraycopy(this.buffer, this.bufferPosition, b, offset, len); this.bufferPosition += len; return; } if (available > 0) { System.arraycopy(this.buffer, this.bufferPosition, b, offset, available); offset += available; len -= available; } if (len > this.buffer.length) { int totalRead = 0; while (totalRead < len) { int n = this.inputStream.read(b, offset + totalRead, len - totalRead); if (n == -1) throw new java.io.EOFException(); totalRead += n; } } else { this.bufferPosition = 0; this.bufferLength = this.inputStream.read(this.buffer); if (this.bufferLength < len) throw new java.io.EOFException(); System.arraycopy(this.buffer, 0, b, offset, len); this.bufferPosition = len; } }
public TagQueueResponse tagQueue(TagQueueRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance())); }
void Remove() { throw new UnsupportedOperationException(); }
return invoke(request, ModifyCacheSubnetGroupRequestMarshaller.getInstance(), ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());
void setParams(String params) { StringTokenizer st = new StringTokenizer(params); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); if (st.hasMoreTokens()) st_field = st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return invoke(request, new InvokeOptions<>().setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance())); }
@Override public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; --i) if (!Components[i].equals(other.Components[i])) return false; return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return invoke(request, options); }
HSSFPolygon shape = parent.createPolygon(anchor);
String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(GetDashboardRequestMarshaller.getInstance()).responseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()).build()); }
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance())); }
void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; ++j) { BlankRecord br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
while ((k = string.IndexOf("\\\\", k)) >= 0) { sb.Append(string.Substring(0, k) + "\\\\\\\\"); string = string.Substring(k + 2); k = 0; }
throw new java.nio.ReadOnlyBufferException();
for (int r = 0; r < _nRows; ++r) { Object[] rowData = values2d[r]; for (int c = 0; c < _nColumns; ++c) { rowData[c] = vv[GetValueIndex(c, r)]; } }
return invoke(request, InvokeOptions.builder().requestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).responseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()).build());
@Override public String toString() { StringBuilder sb = new StringBuilder(); sb.append(this.getClass().getSimpleName()); sb.append(" "); sb.append("= "); sb.append(getValueAsString()); return sb.toString(); }
@Override public String toString() { return field + " " + _parentQuery; }
incRef void } { ) ( ; ) ( incrementAndGet . refCount
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); return invoke(request, options); }
int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 > 0) { _multiplicand.mulShift(tp._multiplicand, tp._multiplierShift); } else { _multiplicand.mulShift(tp._divisor, tp._divisorShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (i > 0) { builder.append(java.io.File.separator); } builder.append(getComponent(i)); } return builder.toString(); }
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.fetcher.fetcher.SetRoleName();; }
void setProgressMonitor(ProgressMonitor progressMonitor) { }
void Reset() { if (!First()) { if (!Eof) { ParseEntry(); ptr = 0; } } }
E previous() { if ((start >= previousIndex)) { throw new java.util.NoSuchElementException(); } return iterator.iterator().previous(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
public static java.util.List<org.apache.lucene.util.CharsRef> uniqueStems(java.util.List<org.apache.lucene.util.CharsRef> stems) { org.apache.lucene.analysis.util.CharArraySet dictionary = new org.apache.lucene.analysis.util.CharArraySet(stems.size(), false); java.util.List<org.apache.lucene.util.CharsRef> deduped = new java.util.ArrayList<>(); for (org.apache.lucene.util.CharsRef s : stems) if (dictionary.add(s)) deduped.add(s); return deduped; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
void setPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
int s = (int)Math.max(0, Math.min(n, Available));
public BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
out1.writeByte(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 1 : 0); if (field_5_hasMultibyte) StringUtil.putUnicodeLE(field_6_author, out1); else StringUtil.putCompressedUnicode(field_6_author, out1); if (field_7_padding != null) out1.write(field_7_padding);
int lastIndexOf(String string) { return string.lastIndexOf(','); }
boolean add(E object) { return addLastImpl(); }
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection String section String ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( CompareAndSet . state res src UnsetSection ) , , ( ) ( Get . state
public String getTagName() { return tagName; }
void AddSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
boolean remove(Object object) { synchronized(mutex) { return c.remove(); } }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input, 4, true); }
long length() { return inCoreLength(); }
void setValue(boolean newValue) { this.value = newValue; }
public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; }
public int get(int i) { if (i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
CreateRepoRequest.builder().method(MethodType.PUT).uriPattern("").build();
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); if (lastLink.previous != null) lastLink.previous.next = lastLink.next; else list.first = lastLink.next; if (lastLink.next != null) lastLink.next.previous = lastLink.previous; else list.last = lastLink.previous; list.size--; list.modCount++; expectedModCount++; lastLink = null; }
return invoke(request, InvokeOptions.builder().requestMarshaller(MergeShardsRequestMarshaller.getInstance()).responseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()).build());
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance())); }
int getBeginIndex() { return start; }
public WeightedTerm[] getTerms(Query query) { return query.getTerms(); }
throw new java.nio.ReadOnlyBufferException();
void Decode(int iterations, int valuesOffset, int blocksOffset, int[] values, byte[] blocks){int byte0,byte1,byte2;for(int i=0;i<iterations;i++){byte0=blocks[blocksOffset++]&0xff;byte1=blocks[blocksOffset++]&0xff;byte2=blocks[blocksOffset++]&0xff;values[valuesOffset++]=byte0>>2;values[valuesOffset++]=((byte0&0x03)<<4)|(byte1>>4);values[valuesOffset++]=((byte1&0x0f)<<2)|(byte2>>6);values[valuesOffset++]=byte2&0x3f;}}
public String getHumanishName() { if (path == null) return ""; final String[] e = path.split("/"); if (e.length == 0) return ""; String r = e[e.length - 1]; if (org.eclipse.jgit.lib.Constants.DOT_GIT.equals(r) || r.length() == 0) { if (e.length >= 2) r = e[e.length - 2]; } if (r.endsWith(org.eclipse.jgit.lib.Constants.DOT_GIT_EXT)) r = r.substring(0, r.length() - org.eclipse.jgit.lib.Constants.DOT_GIT_EXT.length()); return r; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return client.describeNotebookInstanceLifecycleConfig(request); }
String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance())); }
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance())); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance())); }
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetApisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()));
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()), DeleteSmsChannelResponse.class); }
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
IQueryNode getChild() { return getChildren()[0]; }
} {  ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
field_1_formatFlags = in1.readShort();
} { extends ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(request, new InvokeOptions().requestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).responseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).responseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()).build()); }
return prefixToOrdRange.get(prefix);
@Override public String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < input.size()) { symbol = input.getText(org.antlr.v4.runtime.misc.Interval.of(startIndex, startIndex)); symbol = org.antlr.v4.runtime.misc.Utils.escapeWhitespace(symbol, false); } return String.format(java.util.Locale.getDefault(), "%s('%s')", getClass().getSimpleName(), symbol); }
E peek() { return peekFirstImpl(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()));
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, DescribeRepositoriesRequestMarshaller.getInstance(), DescribeRepositoriesResponseUnmarshaller.getInstance()); }
initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public RandomAccessFile randomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()));
String toHex(long value) { return Long.toHexString(value).toUpperCase(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()));
if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor(); else { byte[] b = palette.getColor(index); return (b != null) ? new HSSFColor.CustomColor(index, b) : null; }
ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(LittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];for(int i=0;i<length;i++){char c=chars[offset+i];result[i*2]=(byte)(c>>8);result[i*2+1]=(byte)c;}return result;}
return invoke(request, new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()));
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; return true; }
return weightBySpanQuery.entrySet().stream().map(e -> new SpanQuery(e.getKey(), e.getValue().getBoost())).collect(java.util.stream.Collectors.collectingAndThen(java.util.stream.Collectors.toList(), queries -> queries.size() == 1 ? queries.get(0) : new SpanOrQuery(queries.toArray(new SpanQuery[0]))));
StashCreateCommand StashCreate() { return new StashCreateCommand(); }
return byName.get(fieldName);
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance())); }
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions().withRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).withResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()));
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()));
add void } { ) , , , , ( ; ; ; ; ) ( if ) ( if ) ( if ) ( if posLength int endOffset int len int offset int output ++ count posLength endOffset } { null == } { == count } { == count } { == count char posLengths endOffsets ) , , ( copyChars . ; outputs ; ; ; length . posLengths ; ; ; length . endOffsets ; ; ; length . outputs ] [ ] [ ] [ outputs ] [ next posLengths next endOffsets next outputs ] [ CharsRef new outputs ) , , , , ( System . arraycopy next int ) , , , , ( System . arraycopy next int ) , , , , ( System . arraycopy next CharsRef ) ( ] [ = ] [ = ] [ = ] [ new new new int int CharsRef ] [ ] [ ] [ ) , ( ArrayUtil . oversize ) , ( ArrayUtil . oversize ) , ( ArrayUtil . oversize NUM_BYTES_INT32 . RamUsageEstimator count + 1 NUM_BYTES_INT32 . RamUsageEstimator count + 1 NUM_BYTES_OBJECT_REF . RamUsageEstimator count + 1
} { extends ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
boolean exists() { return objects.exists(); }
java.io.FilterOutputStream out = null;
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
return invoke(request, new InvokeOptions().setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()));
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance())); }
void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
boolean isReuseObjects() { return reuseObjects; }
IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); getParent().addChild(t); return t; }
LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(args + " "); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); return invoke(request, options); }
TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
class AddAlbumPhotosRequest implements Protocol {}
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { return invoke(request, GetThreatIntelSetRequestMarshaller.getInstance(), GetThreatIntelSetResponseUnmarshaller.getInstance()); }
public TreeFilter clone() { return new Binary.AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o) { return o != null; }
protected boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void unprotectWorkbook() { workbook.removeRecord(workbook.getWriteProtect()); workbook.removeRecord(workbook.getFilePass()); }
SolrSynonymParser(boolean expand, boolean dedup, Analyzer analyzer) { }
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance())); }
byte[] getObjectData() { return ObjectData.findObjectRecord(); }
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { return invoke(request, new InvokeOptions(), GetContactAttributesRequestMarshaller.INSTANCE, GetContactAttributesResponseUnmarshaller.INSTANCE); }
String toString() { return GetKey() + " " + GetValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance; return this.<GetContactMethodsResponse>invoke(request, options); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) return -1; return fd.index; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String changeId(ObjectId InsertId, String message) { return InsertId.toString(); }
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy()); else throw new MissingObjectException(objectId.copy(), " "); } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance())); }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance())); }
field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return invoke(request, new GetFieldLevelEncryptionConfigRequestMarshaller(), new GetFieldLevelEncryptionConfigResponseUnmarshaller()); }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return client.describeDetector(request); }
return clientHandler.execute(new ClientExecutionParams<ReportInstanceStatusRequest, ReportInstanceStatusResponse>().withInput(request).withMarshaller(new ReportInstanceStatusRequestMarshaller()).withResponseHandler(new ReportInstanceStatusResponseHandler()));
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return this.<DeleteAlarmResponse>invoke(request, options); }
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
byte[] reserved = new byte[ENCODED_SIZE];
boolean remove(Object object) { synchronized(mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { return invoke(request, GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance()); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { return invoke(request, new InvokeOptions<>().withMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance())); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
public WindowProtectRecord(int options) { this._options = options; }
int[] buffer = new int[bufferSize];
public GetOperationsResponse getOperations(GetOperationsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.INSTANCE).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.INSTANCE)); }
void CopyRawTo(byte[] b, int o) { EncodeInt32.NB(0, b, o); EncodeInt32.NB(0, b, o + 4); EncodeInt32.NB(0, b, o + 8); EncodeInt32.NB(0, b, o + 12); EncodeInt32.NB(0, b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()));
void close() throws java.io.IOException { if (isOpen()) { try { try { dump(); channel.truncate(0); } finally { channel.close(); fos.close(); } } finally { isOpen = false; } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return invoke(new DescribeMatchmakingRuleSetsRequestMarshaller().marshall(request), new DescribeMatchmakingRuleSetsResponseUnmarshaller()); }
String GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; int n = v.length; if (n == 1) return 0.0; double s = 0.0; for (int i = 0; i < n; ++i) s += v[i]; double m = s / n; double r = 0.0; for (int i = 0; i < n; ++i) r += (v[i] - m) * (v[i] - m); return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { return invoke(request, new InvokeOptions(), DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance()); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
public void traverse(ICellHandler handler, boolean traverseEmptyCells) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { Row currentRow = sheet.getRow(rowNumber); if (currentRow == null) { if (traverseEmptyCells) { for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(); ctx.sheet = sheet; ctx.row = null; ctx.cell = null; ctx.rowNumber = rowNumber; ctx.columnNumber = colNumber; ctx.ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn) + 1; handler.onCell(null, ctx); } } continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { Cell currentCell = currentRow.getCell(colNumber); boolean isCellEmpty = (currentCell == null || currentCell.getCellType() == CellType.BLANK); if (!traverseEmptyCells && isCellEmpty) { continue; } SimpleCellWalkContext ctx = new SimpleCellWalkContext(); ctx.sheet = sheet; ctx.row = currentRow; ctx.cell = currentCell; ctx.rowNumber = rowNumber; ctx.columnNumber = colNumber; ctx.ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn) + 1; handler.onCell(currentCell, ctx); } } }
int GetReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (boost == other.boost) { return term.compareTo(other.term); } else { return Float.compare(boost, other.boost); } }
int Normalize(char[] s, int len) { for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.Delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(LittleEndianOutput out1) { out1.writeShort(); }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
KeySchemaElement(KeyType keyType, String attributeName) { this._keyType = keyType; this._attributeName = attributeName; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()));
boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public void setAllGroups(boolean allGroups) { this.allGroups = allGroups; }
void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig fieldType = fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()); fieldType.setIsMultiValued(v); } }
int getCellsVal(String cmd) { int size = 0; for (char c : cmd.toCharArray()) { if (cells.containsKey(c) && cells.get(c) >= 0) size++; } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); return invoke(request, options); }
