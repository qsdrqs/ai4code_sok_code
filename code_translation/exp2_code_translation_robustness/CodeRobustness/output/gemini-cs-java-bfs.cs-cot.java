void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll(BlockList<T> src) { if (src.size == 0) { return; } for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) { if (src.directory[srcDirIdx] != null) { addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); } } if (src.tailBlkIdx > 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
// Assumed class fields: List<byte[]> blocks, byte[] currentBlock, int upto, int blockSize     public void WriteByte(byte b)     {         if (currentBlock == null || upto == blockSize)         {             currentBlock = new byte[blockSize];             upto = 0;             blocks.Add(currentBlock);         }         currentBlock[upto++] = b;     }
ObjectId getObjectId() { return objectId; }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.instance).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.instance));
long RamBytesUsed() { return fst == null ? 0L : fst.GetSizeInBytes(); }
return RawParseUtils.tagMessage(raw, 0) < 0 ? "" : RawParseUtils.decode(RawParseUtils.parseEncoding(raw), raw, RawParseUtils.tagMessage(raw, 0), raw.length);
HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(headerBlock); _documents = new ArrayList<>(); _root = null;
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address;
return command.setPath(path);
public ListIngestionsResponse ListIngestions(ListIngestionsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance())); }
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return this.<GetShardIteratorRequest, GetShardIteratorResponse>invoke(request, options); }
new ModifyStrategyRequest().setMethod(MethodType.POST);
synchronized (lock) { try { if (in == null) throw new java.io.IOException(); return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } }
EscherOptRecord getOptRecord() { return _optRecord; }
System.arraycopy(this.buffer, pos, buffer, offset, copylen);
} { ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
void print(String str) { System.out.print(String.valueOf(str)); }
} { ; NotImplementedFunctionException functionName ) cause , functionName ( super : functionName . ) cause NotImplementedException , functionName String (
next V } { ) ( ; return value . ) ( nextEntry .
public void readBytes(byte[] b, int offset, int len) throws java.io.IOException { int available = _bufferLength - _bufferPosition; if (available >= len) { System.arraycopy(_buffer, _bufferPosition, b, offset, len); _bufferPosition += len; } else { if (available > 0) { System.arraycopy(_buffer, _bufferPosition, b, offset, available); offset += available; len -= available; _bufferPosition += available; } if (len > _bufferSize) { int n = _stream.read(b, offset, len); if (n != len) throw new java.io.EOFException(); } else { refillBuffer(); if (_bufferLength < len) throw new java.io.EOFException(); System.arraycopy(_buffer, _bufferPosition, b, offset, len); _bufferPosition += len; } } }
public TagQueueResponse tagQueue(TagQueueRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance())); }
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return invoke(request, new InvokeOptions().withMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).withUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance())); }
void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) st.nextToken(); if (st.hasMoreTokens()) st.nextToken(); if (st.hasMoreTokens()) st.nextToken(); }
public DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return this.<DeleteDocumentationVersionRequest, DeleteDocumentationVersionResponse>invoke(request, new InvokeOptions(), DeleteDocumentationVersionRequestMarshaller.getInstance(), DeleteDocumentationVersionResponseUnmarshaller.getInstance()); }
public boolean equals(Object obj) { if (obj == null || !(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (components.length != other.components.length) return false; for (int i = components.length - 1; i >= 0; --i) if (!components[i].equals(other.components[i])) return false; return true; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()));
HSSFPolygon shape = new HSSFPolygon(); shape.setAnchor(anchor); shapes.add(shape); return shape;
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = InvokeOptions.builder().requestMarshaller(GetDashboardRequestMarshaller.getInstance()).responseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()).build(); return invoke(request, options); }
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(request, options); }
void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.numColumns;++j){BlankRecord br=new BlankRecord();br.row=mbr.row;br.firstColumn=mbr.firstColumn+j;br.column=j;br.xfIndex=getXfAt(mbr.row);insertCell(br);}}
public static String quote(String string) { if (string == null || string.indexOf('\\') < 0) return string; StringBuilder sb = new StringBuilder(); int last = 0; int current; while ((current = string.indexOf('\\', last)) != -1) { sb.append(string.substring(last, current)); sb.append("\\\\"); last = current + 1; } sb.append(string.substring(last)); return sb.toString(); }
throw new java.nio.ReadOnlyBufferException();
for (int r = 0; r < _nRows; r++) for (int c = 0; c < _nColumns; c++) _arrayValues[r][c] = GetValueIndex(c, r);
return invoke(request, InvokeOptions.builder().requestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).responseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()).build());
public String toString() { return new StringBuilder().append(this.getClass().getSimpleName()).append(" = ").append(getValueAsString()).toString(); }
@Override public String toString() { return field + " " + _parentQuery; }
void IncRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { return invoke(request, new InvokeOptions().setMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).setUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance())); }
int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(); if (pow10 < 0) { tp.divShift(_divisor, _divisorShift, Math.abs(pow10)); } else { tp.mulShift(_multiplicand, _multiplierShift, pow10); } }
@Override public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < length - 1) { builder.append(java.io.File.separator); } } return builder.toString(); }
fetcher.setRoleName();
void setProgressMonitor(ProgressMonitor progressMonitor) { }
void Reset() { if (!First()) { if (!Eof()) { ParseEntry(); ptr = 0; } } }
if (start >= previousIndex()) throw new java.util.NoSuchElementException(); return iterator.previous();
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> deduped = new ArrayList<>(); CharArraySet dictionary = new CharArraySet(8, false); for (CharsRef s : stems) if (dictionary.add(s)) deduped.add(s); return deduped;
return this.<GetGatewayResponsesResponse>invoke(request, new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
void SetPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
int s = (int)Math.min(n, Math.max(ptr, Available));
public BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
if (field_5_hasMultibyte) StringUtil.putUnicodeLE(field_6_author, out1); else StringUtil.putCompressedUnicode(field_6_author, out1); if (field_7_padding != null) { } out1.writeByte(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0);
int lastIndexOf(String string) { return string.lastIndexOf(','); }
boolean addLastImpl(E object) { return add(); }
void unsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.get(); res = src.unsetSection(section, subsection); } while (!state.compareAndSet(src, res)); }
public String getTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public boolean remove() { synchronized (mutex) { return c.remove(); } }
return (TokenStream) new DoubleMetaphoneFilter(input);
long inCoreLength() { return getLength(); }
public void setValue(boolean newValue) { this.value = newValue; }
Pair(ContentSource newSource, ContentSource oldSource)
if (i >= count) throw new IndexOutOfBoundsException(); return entries[i];
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (lastReturned == null) throw new IllegalStateException(); if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastReturned.prev != null) lastReturned.prev.next = lastReturned.next; else first = lastReturned.next; if (lastReturned.next != null) lastReturned.next.prev = lastReturned.prev; else last = lastReturned.prev; size--; modCount++; expectedModCount++; lastReturned = null; }
return invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()).withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()));
int getBeginIndex() { return start; }
WeightedTerm[][] getTerms(Query query) { return query.getTerms(); }
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void Decode(int[] values, int valuesOffset, byte[] blocks, int blocksOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName() { String s = getPath(); if (s == null) { return ""; } String[] elements = s.split("/"); if (elements.length == 0) { return ""; } String result = elements[elements.length - 1]; if (result.equals(Constants.DOT_GIT) || result.isEmpty()) { if (elements.length >= 2) { result = elements[elements.length - 2]; } } if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
// Reconstructed C# method     public DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request)     {         var options = new InvokeOptions         {             RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance,             ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance         };         return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);     }
String GetAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance; options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance; return this.<CreateVpnConnectionResponse, CreateVpnConnectionRequest>Invoke(request, options); }
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, DescribeVoicesRequestMarshaller.INSTANCE, DescribeVoicesResponseUnmarshaller.INSTANCE); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(ListMonitoringExecutionsRequestMarshaller.INSTANCE).responseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.INSTANCE).build()); }
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request) {         InvokeOptions options = new InvokeOptions();         options.setRequestMarshaller(GetApisRequestMarshaller.getInstance());         options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance());         return this.<GetApisResponse, GetApisRequest>invoke(request, options);     }
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) {         InvokeOptions options = new InvokeOptions();         options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.INSTANCE);         options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.INSTANCE);         return this.<DeleteSmsChannelRequest, DeleteSmsChannelResponse>invoke(request, options);     }
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
IQueryNode[] getChild() { return getChildren(); }
} { ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
field_1_formatFlags = in1.readShort();
} { : ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { return client.describeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { return invoke(request, PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); }
return prefixToOrdRange.get(dim);
public String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size()) { symbol = getInputStream().getText(org.antlr.v4.runtime.misc.Interval.of(startIndex, startIndex)); symbol = org.antlr.v4.runtime.misc.Utils.escapeWhitespace(symbol, false); } return String.format("%s('%s')", getClass().getSimpleName(), symbol); }
public E peek() { return peekFirstImpl(); }
return invoke(request, new InvokeOptions.Builder().requestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).responseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()).build());
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { return invoke(request, new InvokeOptions(), DescribeRepositoriesRequestMarshaller.getInstance(), DescribeRepositoriesResponseUnmarshaller.getInstance()); }
initialCapacity = com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return this.<CreateDistributionWithTagsResponse>invoke(request, options); }
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()));
public String toHex(long value) { return Long.toHexString(value); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance())); }
public HSSFColor getColor(short index) { if (index == HSSFColor.Automatic.index) { return HSSFColor.Automatic.getInstance(); } byte[] b = palette.getColor(index); if (b != null) { return new HSSFColor.CustomColor(b); } return null; }
public ValueEval evaluate(int srcRow, int srcCol, ValueEval[] operands) { throw new UnsupportedOperationException(); }
out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index);
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length){byte[] result=new byte[length*2];int resultIndex=0;int end=offset+length;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
return invoke(request, new InvokeOptions.Builder().requestMarshaller(UploadArchiveRequestMarshaller.Instance).responseUnmarshaller(UploadArchiveResponseUnmarshaller.Instance).build());
List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; return java.util.Objects.equals(m_term, other.m_term) && java.util.Objects.equals(m_compiled, other.m_compiled); }
// Assuming the input is a Dictionary or similar collection. // The method likely returns a single SpanQuery. SpanQuery MakeSpanClause(IDictionary<SpanQuery, float> weightBySpanQuery) {     List<SpanQuery> spanQueries = new List<SpanQuery>();      foreach (var wsq in weightBySpanQuery)     {         SpanQuery query = wsq.Key;         query.Boost = wsq.Value; // In older Lucene.NET, Boost was a property.         spanQueries.Add(query);     }      if (spanQueries.Count == 1)     {         return spanQueries[0]; // Return the single query directly.     }     else     {         // Return a SpanOrQuery containing all the queries.         return new SpanOrQuery(spanQueries.ToArray());     } }
StashCreate StashCreateCommand() { return new StashCreateCommand(); }
return byName.get(fieldName);
return invoke(request, new InvokeOptions<>().setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()));
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions().setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.INSTANCE).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.INSTANCE));
return this.<SetInstanceProtectionRequest, SetInstanceProtectionResponse>invoke(request, new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.INSTANCE).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.INSTANCE));
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options); }
void Add(int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF); CharsRef[] newOutputs = new CharsRef[next]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; } if (count == endOffsets.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32); int[] newEndOffsets = new int[next]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; } if (count == posLengths.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32); int[] newPosLengths = new int[next]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } outputs[count] = new CharsRef(); CopyChars(outputs[count], offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
class FetchLibrariesRequest extends Protocol { }
boolean Exists() { return objects.Exists(); }
try (FilterOutputStream out = new FilterOutputStream(java)) { ... }
new ScaleClusterRequest(HttpMethod.PUT).setUriPattern(uriPattern);
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { return invoke(request, ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance()); }
return invoke(request, InvokeOptions.builder().requestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.INSTANCE).responseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.INSTANCE).build());
void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public boolean isReuseObjects() { return reuseObjects; }
public IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); this.addChild(t); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { return invoke(request, new InvokeOptions(), RemoveSourceIdentifierFromSubscriptionRequestMarshaller.INSTANCE, RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.INSTANCE); }
public static TokenFilterFactory newInstance(String name, Map<String, String> args) { return TokenFilterFactory.forName(name, args); }
class AddAlbumPhotosRequest implements Protocol { }
return this.<GetThreatIntelSetResponse>invoke(request, new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()));
public TreeFilter clone() { return new Binary.AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o) { return o instanceof Object; }
protected boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { return this.<UpdateContributorInsightsResponse>invoke(request, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance())); }
void unwriteProtectWorkbook() { records.remove(writeProtect); records.remove(fileShare); }
SolrSynonymParser(boolean expand, boolean dedup, Analyzer analyzer)
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance())); }
public byte[] getObjectData() { return ObjectData.findObjectRecord(); }
return this.<GetContactAttributesResponse>invoke(request, new InvokeOptions.Builder().requestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).responseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()).build());
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance())); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { return invoke(request, new InvokeOptions.Builder().requestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).responseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()).build()); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) return -1; return fd.getIndex(); }
return invoke(request, new InvokeOptions(), DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());
private String insertId; public String getInsertId() { return insertId; } public void setInsertId(String insertId) { this.insertId = insertId; }
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { if (typeHint == Constants.OBJ_ANY) { throw new MissingObjectException(objectId.copy(), " "); } else { throw new MissingObjectException(objectId.copy(), typeHint); } } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { return invoke(request, new InvokeOptions.Builder().requestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).responseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()).build()); }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(new PutLifecycleEventHookExecutionStatusRequestMarshaller()).withResponseUnmarshaller(new PutLifecycleEventHookExecutionStatusResponseUnmarshaller())); }
NumberPtg field_1_value = new NumberPtg(in1.readDouble());
GetFieldLevelEncryptionConfig GetFieldLevelEncryptionConfigResponse } { ) ( ; return ; ; ; request GetFieldLevelEncryptionConfigRequest > ) , ( GetFieldLevelEncryptionConfigResponse < Invoke Instance . GetFieldLevelEncryptionConfigResponseUnmarshaller ResponseUnmarshaller . options Instance . GetFieldLevelEncryptionConfigRequestMarshaller RequestMarshaller . options options = InvokeOptions new ) (
public DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { InvokeOptions options = new InvokeOptions(); return this.<DescribeDetectorResponse>invoke(request, options, DescribeDetectorRequestMarshaller.instance, DescribeDetectorResponseUnmarshaller.instance); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()));
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(request, options); }
return new PortugueseStemFilter(input);
private byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove() { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(request, options); }
string ToString() { return precedence + " "; }
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) {}
WindowProtectRecord(int options) { this.options = options; }
{ new UnbufferedCharStream(data, 0, n); new int[(int)bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance())); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(arg1, b, o); NB.EncodeInt32(arg2, b, o + 4); NB.EncodeInt32(arg3, b, o + 8); NB.EncodeInt32(arg4, b, o + 12); NB.EncodeInt32(arg5, b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { return this.<StopWorkspacesResponse>invoke(request, new InvokeOptions(), StopWorkspacesRequestMarshaller.getInstance(), StopWorkspacesResponseUnmarshaller.getInstance()); }
void close() throws IOException {if(isOpen()){try{try{dump();}finally{try{channel.truncate(0);}finally{channel.close();fos.close();}}}finally{isOpen=false;}}}
return invoke(request, new InvokeOptions(), DescribeMatchmakingRuleSetsRequestMarshaller.getInstance(), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance());
String GetPronunciation(char[] surface, int wordId, int off, int len) { return null; }
String getPath() { return pathStr; }
public static double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; if (v.length == 1) return 0.0; int n = v.length; double m = 0; for (int i = 0; i < n; i++) m += v[i]; m /= n; double s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); return s; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()); return invoke(request, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end; }
public void traverse(ICellHandler handler, int firstRow, int lastRow, int firstColumn, int lastColumn, boolean traverseEmptyCells) { int width = lastColumn - firstColumn + 1; for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = sheet.getRow(rowNumber); for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = (currentRow == null) ? null : currentRow.getCell(colNumber); if (!traverseEmptyCells && (currentCell == null || currentCell.isEmpty())) { continue; } SimpleCellWalkContext ctx = new SimpleCellWalkContext(); ctx.rowNumber = rowNumber; ctx.colNumber = colNumber; ctx.ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn); handler.onCell(currentCell, ctx); } } }
int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (term.compareTo(other.term) == 0) { return boost.compareTo(other.boost); } else { return term.compareTo(other.term); } }
public int Normalize(char[] s, int len) { for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.Delete(s, i, len); --i; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
} {  ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
public KeySchemaElement(String attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); return invoke(request, options); }
boolean hasObject() { return findOffset(id) != -1; }
public void SetAllGroups(boolean allGroups) { this.allGroups = allGroups; }
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig fieldType = fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()); fieldType.setMultiValued(v); } }
public int getCellsVal(String cmd) { int size = 0; for (char c : cmd.toCharArray()) { if (cells.containsKey(c)) { size++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return this.<DeleteVoiceConnectorResponse>invoke(request, options); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { return invoke(request, new DeleteLifecyclePolicyRequestMarshaller(), new DeleteLifecyclePolicyResponseUnmarshaller()); }
