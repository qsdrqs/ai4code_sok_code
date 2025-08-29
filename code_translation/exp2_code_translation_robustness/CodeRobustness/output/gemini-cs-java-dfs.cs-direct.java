void serialize(LittleEndianOutput out1) { out1.writeShort(); }
void addAll(BlockList<T> src) { if (src.size == 0) return; for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
ObjectId getObjectId() { return objectId; }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()));
long RamBytesUsed() { return fst == null ? 0L : fst.GetSizeInBytes(); }
} ; ) length . raw , , , ( decode . RawParseUtils return ; ) ( parseEncoding . RawParseUtils = enc Charset } ; "" return { ) 0 < msgB ( if ; ) , ( tagMessage . RawParseUtils = msgB int ; buffer = raw [ ] byte { ) ( getFullMessage String
new POIFSFileSystem() {{ headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }};
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < slice.length; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(ListIngestionsRequestMarshaller.getInstance()).responseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()).build()); }
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { return execute(request, GetShardIteratorRequestMarshaller.getInstance(), GetShardIteratorResponseUnmarshaller.getInstance()); }
public ModifyStrategyRequest() { super(" ", " ", " ", " ", " "); this.Method = MethodType.POST; }
boolean ready() throws java.io.IOException { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
EscherOptRecord GetOptRecord() { return _optRecord; }
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new NullPointerException("buffer"); java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) buffer[offset + i] = buffer[pos + i]; pos += copylen; return copylen; }
NLPSentenceDetectorOp sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
} ; ) ) null ) Object ( ( valueOf . String : str ? null != str ( write { ) str String ( print void
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
V next() { return nextEntry().getValue(); }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) throws java.io.IOException { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new java.io.EOFException(); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new java.io.EOFException(); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
return invoke(request, InvokeOptions.builder().requestMarshaller(TagQueueRequestMarshaller.getInstance()).responseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()).build());
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance())); }
} ; ) ( nextToken . st ignore = ) ( hasMoreTokens . st if ; " " + ) ( nextToken . st culture = ) ( hasMoreTokens . st if ; ) ( nextToken . st culture = ) ( hasMoreTokens . st if ; " " , params ( StringTokenizer new = st StringTokenizer ; { ) params String ( setParams void
return invoke(request, InvokeOptions.builder().requestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).responseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()).build());
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance())); }
public HSSFPolygon createPolygon(HSSFShape parent, HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(parent, anchor); shapes.add(shape); return shape; }
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetName(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance())); }
return client.associateSigninDelegateGroupsWithAccount(request);
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn((short)(mbr.getFirstColumn() + j)); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFIndex()); insertCell(br); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 2; } return sb.append(string.substring(apos)).append("\"").toString(); }
java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short)nColumns; _nRows = (short)nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0;
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance())); }
public String toString() { return new StringBuilder().append(this.getClass().getSimpleName()).append(" ").append(this.getValueAsString()).append(" ").toString(); }
public String toString(String field) { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
return client.updateConfigurationSetSendingEnabled(request);
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) ( getNextXBATChainOffset int
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
} ; ) ( toString . builder return } } ; ) separator . File ( append . builder { ) ) 1 - length ( < i ( if ; ) ) i ( getComponent ( append . builder { ) ++ i ; length < i ; 0 = i int ( for ; ) separator . File ( append . builder ; ) ( getLength = length int ; ) ( StringBuilder new = builder StringBuilder { ) ( toString String public
} ; ) ( setRoleName . fetcher . ; fetcher fetcher . { ) fetcher ECSMetadataServiceCredentialsFetcher ( withFetcher void
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
}}};)(ParseEntry{)Eof!(if;0 ptr{)First!(if{)(Reset void
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
} ; 1 - return } } } ; i return { ) value == ] [ mValues ( if { ) ++ i ; mSize < i ; 0 = i int ( for { { ) value int ( indexOfValue int
public List<CharsRef> uniqueStems(char[][] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) return stems; CharArraySet terms = new CharArraySet(8, true); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
void SetPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; (int) (position & outerInstance.blockMask); }
long skip(long n) { int s = (int)Math.min(Available, Math.max(ptr, s)); return s; }
public BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
out1.writeShort(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.write(field_7_padding); }
int lastIndexOf(String string) { return string.lastIndexOf(','); }
boolean addLastImpl(E o) { return add(o); }
} ; ) ) , ( CompareAndSet . state ! ( while } ; ) , , ( UnsetSection res ; ) ( Get . state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( UnsetSection void
String getTagName() { return tagName; }
void addSubRecord(SubRecord element, int index) { subrecords.add(index, element); }
boolean remove(Object object) { synchronized(mutex) { return c.remove(); } }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input, 4, false); }
} ; ) ( InCoreLength return { ) ( Length long
void setValue(boolean newValue) { this.value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if (i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
} ; PUT . MethodType method ; " " uriPattern { ) " " , " " , " " , " " , " " ( : ) ( CreateRepoRequest
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
} } ; ) ( ConcurrentModificationException . util . java new throw { else } } ; ) ( IllegalStateException new throw { else } ; ++ modCount . list ; -- size . list ; ++ expectedModCount ; null lastLink ; previous_1 link } ; -- pos { ) link == lastLink ( if ; previous_1 previous . next_1 ; next_1 next . previous_1 ; previous . lastLink = previous_1 >E< Link . LinkedList . util . java ; next . lastLink = next_1 >E< Link . LinkedList . util . java { ) null != lastLink ( if { ) modCount . list == expectedModCount ( if { ) ( remove void
public MergeShardsResponse mergeShards(MergeShardsRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(MergeShardsRequestMarshaller.getInstance()).responseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()).build()); }
return invoke(request, new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()));
int getBeginIndex() { return start; }
WeightedTerm[] getTerms(Query query) { return null; }
throw new java.nio.ReadOnlyBufferException();
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName(String s) { if (s == null || s.isEmpty()) { throw new IllegalArgumentException(); } String[] elements; if (s.equals(" ") || LOCAL_FILE.matcher(s).matches()) { elements = s.split(java.util.regex.Pattern.quote(" \\ " + java.io.File.separator + " "), -1); } else { elements = s.split(" ", -1); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (elements.length > 1 && elements[elements.length - 2].equals(Constants.DOT_GIT)) { result = elements[elements.length - 2]; } else if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return invoke(request, new InvokeOptions(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance(), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance())); }
String GetAccessKeySecret() { return AccessSecret; }
return invoke(request, InvokeOptions.builder().requestMarshaller(CreateVpnConnectionRequestMarshaller.INSTANCE).responseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.INSTANCE).build());
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()));
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance())); }
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetApisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance())); }
return client.deleteSmsChannel(request);
TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
IQueryNode getChild() { return getChildren()[0]; }
void NotIgnoredFilter(int workdirTreeIndex) { index.workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public class GetThumbnailRequest { public Https.ProtocolType protocol; public GetThumbnailRequest(String s1, String s2, String s3, String s4, String s5) {} }
return invoke(request, new InvokeOptions(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { return client.putVoiceConnectorStreamingConfiguration(request); }
} ; ) dim ( get . prefixToOrdRange return { ) dim String ( getOrdRange OrdRange
public String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size()) { symbol = Utils.escapeWhitespace(getInputStream().getText(Interval.of(startIndex, startIndex))); } return " "; }
E peekFirstImpl() { return peek(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options); }
} ; rec return ; field_1_formatIndex field_1_formatIndex . rec ; ) ( NumberFormatIndexRecord new = rec NumberFormatIndexRecord { ) ( clone Object
DescribeRepositoriesResponse response = ecrClient.describeRepositories(DescribeRepositoriesRequest.builder().build());
public SparseIntArray(int initialCapacity) { initialCapacity = android.util.internal.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()));
String ToHex(int value) { return ToHex((long)value); }
return invoke(request, InvokeOptions.builder().requestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).responseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()).build());
HSSFColor getColor(short index) { if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor(); return palette.getColor(index); }
ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new UnsupportedOperationException(); }
public void serialize(LittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest request) { return new DescribeDBEngineVersionsResponse(); }
public class FormatRun { public short fontIndex; public short character; public FormatRun(short character, short fontIndex) { this.fontIndex = fontIndex; this.character = character; } }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
invoke(request, new InvokeOptions.Builder().requestMarshaller(UploadArchiveRequestMarshaller.getInstance()).responseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()).build());
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
} ; ) ( StashCreateCommand new return { ) ( StashCreate StashCreateCommand
FieldInfo FieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()); return invoke(request, options); }
return invoke(request, new InvokeOptions().setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()));
return this.<CancelUpdateStackResponse>invoke(request, new InvokeOptions().setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()));
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return this.<ModifyLoadBalancerAttributesResponse>invoke(request, options); }
return this.<SetInstanceProtectionResponse>invoke(request, new InvokeOptions(SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance()));
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { return invoke(request, new InvokeOptions(ModifyDBProxyRequestMarshaller.INSTANCE, ModifyDBProxyResponseUnmarshaller.INSTANCE)); }
void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); this.protocol = ProtocolType.HTTPS; }
boolean Exists() { return objects.Exists(); }
new java.io.FilterOutputStream(out);
} ; PUT . MethodType method ; " " uriPattern { ) " " , " " , " " , " " , " " ( : ) ( ScaleClusterRequest
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return new DVConstraint(operatorType, formula1, formula2); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()));
void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
boolean IsReuseObjects() { return reuseObjects; }
ErrorNodeImpl t = new ErrorNodeImpl(badToken); addChild(t); return t;
public LatvianStemFilterFactory(java.util.Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance())); }
public static TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
new AddAlbumPhotosRequest(" ", " ", " ", " ", " ") {{ setProtocol(ProtocolType.HTTPS); }};
return invoke(request, new InvokeOptions(GetThreatIntelSetRequestMarshaller.getInstance(), GetThreatIntelSetResponseUnmarshaller.getInstance()));
} ; ) ) ( clone . b , ) ( clone . a ( Binary . AndTreeFilter new return { ) ( Clone TreeFilter
public boolean equals(Object o) { return o instanceof Object; }
protected boolean hasArray() { return hasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance())); }
void unwriteProtectWorkbook() { records.remove(); records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer, expand, dedup); }
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).responseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()).build()); }
byte[] getObjectData() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance())); }
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { return invoke(request, new InvokeOptions(), ListTextTranslationJobsRequestMarshaller.getInstance(), ListTextTranslationJobsResponseUnmarshaller.getInstance()); }
return invoke(request, new InvokeOptions().requestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).responseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { return -1; } return (short) fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance())); }
String someMethod(String message, ObjectId changeId, String InsertId) { return InsertId; }
} ; zs nruter } ; ) "" , dItcejbo ( noitpecxEgniJtcbeOssiM wen worht } ; ) " " , dItcejbo ( noitpecxEgniJtcbeOssiM wen worht { ) YNA_JBO == tniHepyt ( fi { ) 0 < zs ( fi ; ) tniHepyt , dItcejbo ( eziStcejbOteg . bd = zs gnol { ) tniHepyt tni , dItcejbo diJtcbejOynA ( eziStcejbOteg gnol
return invoke(request, new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()));
NumberPtg(ILittleEndianInput in1) { field_1_value = in1.readDouble(); }
return invoke(request, new InvokeOptions(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()), GetFieldLevelEncryptionConfigResponse.class);
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()));
} ; ) input ( PortugueseStemFilter new return { ) input TokenStream ( create TokenStream
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
} } ; ) ( remove . c return { ) mutex ( synchronized { ) object Object ( remove boolean
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance())); }
public String toString() { return " " + precedence; }
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.INSTANCE).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.INSTANCE)); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
WindowProtectRecord(int options) { _options options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { return invoke(request, new InvokeOptions(GetOperationsRequestMarshaller.getInstance(), GetOperationsResponseUnmarshaller.getInstance())); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(type.Value, b, o); NB.EncodeInt32(key.Value, b, o + 4); NB.EncodeInt32(field.Value, b, o + 8); NB.EncodeInt32(item.Value, b, o + 12); NB.EncodeInt32(sub.Value, b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { this.field_1_h_hold = in1.readShort(); this.field_2_v_hold = in1.readShort(); this.field_3_width = in1.readShort(); this.field_4_height = in1.readShort(); this.field_5_options = in1.readShort(); this.field_6_active_sheet = in1.readShort(); this.field_7_first_visible_tab = in1.readShort(); this.field_8_num_selected_tabs = in1.readShort(); this.field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance())); }
}}}}} ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
return invoke(request, InvokeOptions.builder().requestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).responseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()).build());
String GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String GetPath() { return pathStr; }
double devsq(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){double m=0;double s=0;int n=v.length;for(int i=0;i<n;++i){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;++i){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
} ; passedThroughNonGreedyDecision return { ) ( hasPassedThroughNonGreedyDecision boolean
int end() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (!traverseEmptyCells && currentCell.getCellType() == CellType.BLANK) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(ctx, currentCell); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (this.term.equals(other.term)) { if (this.boost == other.boost) { return 0; } else { return Float.compare(this.boost, other.boost); } } else { return this.term.compareTo(other.term); } }
} ; len return } } ; break : default ; break ; -- i ; ) , , ( Delete . StemmerUtil len : HAMZA_ABOVE case ; break ; HEH ] [ s : HEH_GOAL case : HEH_YEH case ; break ; KAF ] [ s : KEHEH case ; break ; YEH ] [ s : YEH_BARREE case : FARSI_YEH case { ) ] [ s ( switch { ) ++ i ; len < i ; 0 = i int ( for { ) len int , s ] [ char ( Normalize int
} ; ) ( writeShort . out1 { ) out1 ILittleEndianOutput ( serialize void
} ; exactOnly exactOnly . { ) exactOnly boolean ( DiagnosticErrorListener
public KeySchemaElement(String attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { return invoke(request, new InvokeOptions(GetAssignmentRequestMarshaller.getInstance(), GetAssignmentResponseUnmarshaller.getInstance())); }
boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
} ; return ; allGroups allGroups . { ) allGroups boolean ( SetAllGroups GroupingSearch
void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()).setIsMultiValued(v); } }
public int getCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return this.<DeleteVoiceConnectorResponse>invoke(request, options);
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()));
