} ; ) ( writeShort . out1 { ) out1 ILittleEndianOutput ( serialize void
public void addAll(BlockList<T> src) { if (src.size == 0) { return; } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[srcDirIdx], 0, Block.BLOCK_SIZE); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
void writeByte(byte b) { if (upto.outerInstance == blockSize.outerInstance) { if (currentBlock.outerInstance != null) { blocks.outerInstance.add(currentBlock.outerInstance); blockEnd.outerInstance.add(upto.outerInstance); } currentBlock.outerInstance = new byte[blockSize.outerInstance]; upto.outerInstance = 0; } currentBlock.outerInstance[upto.outerInstance++] = (byte) b; }
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); return invoke(request, options); }
public long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
} ; ) length . raw , , , ( decode . RawParseUtils return ; ) ( parseEncoding . RawParseUtils = enc Charset } ; "" return { ) 0 < msgB ( if ; ) , ( tagMessage . RawParseUtils = msgB int ; buffer = raw ] [ byte { ) ( getFullMessage String
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < slice.length; }
} ; return ; path path . { ) path String ( setPath SubmoduleAddCommand . Api . NGit
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance())); }
QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(); }
return invoke(request, new InvokeOptions().setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()).setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()));
public ModifyStrategyResult modifyStrategy(ModifyStrategyRequest request) {request = beforeClientExecution(request);return executeModifyStrategy(request);}
public boolean ready() { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
} ; optRecord return { ) ( getOptRecord EscherOptRecord
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer"); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
} ; sentenceOp sentenceOp . {  ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
void print(String str) { write(str != null ? String.valueOf((Object)str) : null); }
public NotImplementedFunctionException(String functionName, UnsupportedOperationException cause) { super(functionName, cause); }
return nextEntry.next().getValue();
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); } len -= available; offset += available; bufferPosition = 0; if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new RuntimeException(" " + bufferLength); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new RuntimeException(" " + (after - length)); } ReadInternal(b, offset, len); bufferPosition = 0; bufferLength = 0; } } }
return invoke(new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.Instance).withResponseUnmarshaller(TagQueueResponseUnmarshaller.Instance), request, TagQueueResponse.class);
} ; ) ( UnsupportedOperationException new throw { ) ( remove void
return invoke(request, new InvokeOptions().setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture = st.nextToken() + " "; if (st.hasMoreTokens()) ignore = st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance())); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance; return this.<GetInstanceAccessDetailsResponse>Invoke(request, options); }
} ; shape return ; ) ( onCreate ; ) ( add . shapes ; anchor anchor . shape ; parent . shape ; ) , ( HSSFPolygon new = shape HSSFPolygon { ) anchor HSSFChildAnchor ( createPolygon HSSFPolygon
public String getSheetName(int sheetIndex) { return getBoundSheetRec().getSheetname(); }
return this.<GetDashboardResponse>invoke(new GetDashboardRequest(), new InvokeOptions(), GetDashboardRequestMarshaller.INSTANCE, GetDashboardResponseUnmarshaller.INSTANCE);
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf('\\', apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 1; } sb.append(string.substring(apos)); sb.append("\""); return sb.toString(); }
java.nio.ByteBuffer.putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
return invoke(request, new InvokeOptions().setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()).setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()));
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()); sb.append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString(); }
public String toString(String field) { return " " + parentQuery + " "; }
} ; ) ( incrementAndGet . refCount { ) ( incRef void
return invoke(request, new InvokeOptions(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance(), UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()));
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) ( getNextXBATChainOffset int
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisor.getTp(), _divisorShift.getTp()); } else { mulShift(_multiplicand.getTp(), _multiplierShift.getTp()); } }
public String toString() { java.util.StringJoiner joiner = new java.util.StringJoiner(java.io.File.separator, java.io.File.separator, ""); for (int i = 0; i < getLength(); i++) { joiner.add(getComponent(i)); } return joiner.toString(); }
} ; ) ( setRoleName . fetcher . ; fetcher fetcher . { ) fetcher ECSMetadataServiceCredentialsFetcher ( withFetcher void
} ; pm progressMonitor { ) pm ProgressMonitor ( setProgressMonitor void
} } } ; ) ( parseEntry { ) eof ! ( if ; 0 ptr { ) first ! ( if { ) ( reset void
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (terms.add(s)) { deduped.add(s); } } return deduped; }
return invoke(request, new InvokeOptions(), GetGatewayResponsesRequestMarshaller.getInstance(), GetGatewayResponsesResponseUnmarshaller.getInstance());
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockUpto]; (int) (position & outerInstance.blockMask); }
} ; s return ; s ptr ; ) ) , ( max . Math , ) ( available ( min . Math ) int ( = s int { ) n long ( skip long
} ; bootstrapActionConfig = _bootstrapActionConfig { ) bootstrapActionConfig BootstrapActionConfig ( BootstrapActionDetail
public void serialize(LittleEndianOutput out1) { out1.writeShort(field_1_row); out1.writeShort(field_2_col); out1.writeShort(field_3_flags); out1.writeShort(field_4_shapeid); out1.writeShort(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.write(field_7_padding); } }
int lastIndexOf(String string) { return lastIndexOf(); }
boolean add(E object) { return addLastImpl(); }
} ; ) ) , ( compareAndSet . state ! ( while } ; ) , , ( unsetSection res ; ) ( get . state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( unsetSection void
public String getTagName() { return tagName; }
void AddSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
} } ; ) ( remove . c return { ) mutex ( synchronized { ) object Object ( remove boolean public
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input, 4, true); }
} ; ) ( InCoreLength return { ) ( Length Long
void setValue(boolean newValue) { value = newValue; }
} ; newSource newSource . ; oldSource oldSource . {  ) ContentSource newSource , ContentSource oldSource ( Pair
public int get(int i){if(i>=count){throw new IndexOutOfBoundsException();}return entries[i];}
} ; PUT.getMethodType().getMethod() ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( CreateRepoRequest
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
} } ; ) ( ConcurrentModificationException . util . java new throw { else } } ; ) ( IllegalStateException new throw { else } ; ++ modCount . list ; -- size . list ; ++ expectedModCount ; null = lastLink ; } ; -- pos { ) link == lastLink ( if ; next_1 = next . previous_1 ; previous_1 = previous . next_1 ; previous . lastLink = previous_1 > E < Link . LinkedList ; next . lastLink = next_1 > E < Link . LinkedList { ) null != lastLink ( if { ) modCount . list == expectedModCount ( if { ) ( remove void public
return invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()));
int getBeginIndex() { return start; }
} ; ) , ( getTerms return { ) query Query ( getTerms ] [ WeightedTerm
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
} } ; 63 & byte2 = ] ++ valuesOffset [ values ; ) ) 6 >> byte2 ( | ) 2 << ) 15 & byte1 ( ( = ] ++ valuesOffset [ values ; 0xFF & ] ++ blocksOffset [ blocks = byte2 int ; ) ) 4 >> byte1 ( | ) 4 << ) 3 & byte0 ( ( = ] ++ valuesOffset [ values ; 0xFF & ] ++ blocksOffset [ blocks = byte1 int ; 2 >> byte0 = ] ++ valuesOffset [ values ; 0xFF & ] ++ blocksOffset [ blocks = byte0 int { ) i ++ ; iterations < i ; 0 = i int ( for { ) iterations int , valuesOffset int , values ] [ int , blocksOffset int , blocks ] [ byte ( decode void
public String getHumanishName() { String s = getPath(); if (s == null || s.isEmpty()) { throw new IllegalArgumentException(); } String[] elements = s.split("[/\\\\]"); if (elements.length == 0) { throw new IllegalArgumentException(); } String result; if (elements.length > 1 && Constants.DOT_GIT.equals(elements[elements.length - 1])) { result = elements[elements.length - 2]; } else { result = elements[elements.length - 1]; } if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() { return accessSecret; }
} ; ) , ( > CreateVpnConnectionResponse < invoke return ; INSTANCE . CreateVpnConnectionResponseUnmarshaller responseUnmarshaller . options ; INSTANCE . CreateVpnConnectionRequestMarshaller requestMarshaller . options ; ) ( InvokeOptions new = options { ) request CreateVpnConnectionRequest ( createVpnConnection CreateVpnConnectionResponse
} ; ) , ( > DescribeVoicesResponse < invoke return ; instance . DescribeVoicesResponseUnmarshaller responseUnmarshaller . options ; instance . DescribeVoicesRequestMarshaller requestMarshaller . options ; ) ( InvokeOptions new = options { ) request DescribeVoicesRequest ( describeVoices DescribeVoicesResponse
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.INSTANCE).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.INSTANCE)); }
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
public EscherRecord getEscherRecord(int index) { return escherRecords.get(index); }
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
return invoke(request, new InvokeOptions().setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
} ; ) ) b ( toString . Boolean ( print { ) b boolean ( print void
} ; ] [ ) ( getChildren return { ) ( getChild IQueryNode
public NotIgnoredFilter(int workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
new GetThumbnailRequest(" ", " ", " ", " ", " ") {{ setProtocol(ProtocolType.HTTPS); }};
return this.<DescribeTransitGatewayVpcAttachmentsResponse>invoke(request, new InvokeOptions().requestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).responseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).withResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()));
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
} ; ) , name . ) LexerNoViableAltException . Runtime . Antlr4 ( .class , " " , getDefault . Locale ( format . String return } ; ) , ( escapeWhitespace . Utils symbol ; ) ) , ( of . Interval ( getText . ) InputStream ) ICharStream ( ( symbol { ) size . ) InputStream ) ICharStream ( ( < startIndex && 0 >= startIndex ( if ; "" . String = symbol String { ) ( toString String
E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options); }
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public SparseIntArray(int initialCapacity) { initialCapacity = com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
return invoke<CreateDistributionWithTagsResponse>(request, new InvokeOptions(), CreateDistributionWithTagsRequestMarshaller.getInstance(), CreateDistributionWithTagsResponseUnmarshaller.getInstance());
public RandomAccessFile(String fileName, String mode) throws java.io.FileNotFoundException { this(new java.io.File(fileName), mode); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options); }
String ToHex(int value) { return ToHex((long)value); }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()));
} ; null return } } ; ) , ( CustomColor new return { ) null != b ( if ; ) ( getColor . palette = b ] [ byte { else ; ) ( getInstance . Automatic . HSSFColor return ) index . Automatic . HSSFColor == index ( if { ) index short ( getColor HSSFColor
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(LittleEndianOutput out) { out.writeShort(field_1_number_crn_records); out.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult describeDBEngineVersions(DescribeDBEngineVersionsRequest request) {request = beforeClientExecution(request);return executeDescribeDBEngineVersions(request);}
FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
} ; result return } } ; ) ch ) byte ( ( ] ++ resultIndex [ result ; ) ) 8 >> ch ( ) byte ( ( ] ++ resultIndex [ result ; ] [ chars = ch char { ) i ++ ; end < i ; offset = i int ( for { ; 0 = resultIndex int ; length + offset = end int ; ] 2 * length [ byte new = result ] [ byte { ) length int , offset int , chars ] [ char ( toBigEndianUtf16Bytes ] [ byte
return invoke(request, new InvokeOptions(UploadArchiveRequestMarshaller.INSTANCE, UploadArchiveResponseUnmarshaller.INSTANCE));
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
} ;)))]0[yreuQnapS wen(yarrAot.seireuqnapS(yreuQrOnapS wen nruter esle ;0(teg.seireuqnapS nruter )1 == )(ezis.seireuqnapS( fi } ;))yeKteg.qsw(dda.seireuqnapS ;))eulaVteg.qsw(tsooBtes.)(yeKteg.qsw { ))(tesYrtne.yreuQnapSybthgiew : qsw >taoF ,yreuQnapS<yrtnE.paM( rof ;)(>tsLyarrA<wen = seireuqnapS >yreuQnapS<tsiL { )(esualCnapSekam yreuQnapS
} ; ) ( StashCreateCommand new return { ) ( stashCreate StashCreateCommand
FieldInfo fieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()));
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.Instance); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.Instance); return this.<ModifyLoadBalancerAttributesResponse>invoke(request, options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance())); }
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance())); }
} ; ++ count ; posLength ] [ posLengths ; endOffset ] [ endOffsets ; ) , , ( copyChars . ] [ outputs } ; ) ( CharsRef new ] [ outputs { ) null == ] [ outputs ( if } ; next posLengths ; ) , , , , ( arraycopy . System ; ] ) NUM_BYTES_INT32 . RamUsageEstimator , count + 1 ( oversize . ArrayUtil [ int new = next ] [ int { ) length . posLengths == count ( if } ; next endOffsets ; ) , , , , ( arraycopy . System ; ] ) NUM_BYTES_INT32 . RamUsageEstimator , count + 1 ( oversize . ArrayUtil [ int new = next ] [ int { ) length . endOffsets == count ( if } ; next outputs ; ) , , , , ( arraycopy . System ; ] ) NUM_BYTES_OBJECT_REF . RamUsageEstimator , count + 1 ( oversize . ArrayUtil [ CharsRef new = next ] [ CharsRef { ) length . outputs == count ( if { ) posLength int , endOffset int , len int , offset int , output ] [ char ( add void
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); Protocol = ProtocolType.HTTPS; }
boolean exists() { return objects.exists(); }
new java.io.FilterOutputStream(out) {};
} ; PUT.MethodType method; " " uriPattern { ) " ", " ", " ", " ", " " ( : ) (ScaleClusterRequest
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
return invoke(request, ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance());
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void SetSharedFormula(boolean flag) { field_5_options.SetShortBoolean(sharedFormula); }
boolean isReuseObjects() { return reuseObjects; }
} ; t return ; parent . t ; ) ( addChild ; ) ( ErrorNodeImpl new = t ErrorNodeImpl { ) badToken IToken ( addErrorNode IErrorNode
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
return invoke(request, new InvokeOptions().setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.INSTANCE).setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.INSTANCE));
public static TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(); }
public AddAlbumPhotosResult addAlbumPhotos(AddAlbumPhotosRequest request) {request = beforeClientExecution(request);return executeAddAlbumPhotos(request);}
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); return invoke(request, options); }
return new AndTreeFilter(Binary.clone(a), TreeFilter.clone(b));
public boolean equals(Object o){return o instanceof Object;}
protected boolean hasArray() { return hasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { options = new InvokeOptions(); options.requestMarshaller = UpdateContributorInsightsRequestMarshaller.instance; options.responseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.instance; return invoke(request, options); }
void unwriteProtectWorkbook() { records.remove(); records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) { super(dedup, analyzer); }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
byte[] getObjectData() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
} ; ) ( getValue + " " + ) ( getKey return { ) ( toString String public
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(request, options); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { return -1; } return (short) fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return this.<DescribeAnomalyDetectorsResponse>invoke(request, new InvokeOptions().setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance())); }
String insertId(String message, ObjectId changeId) { return insertId; }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), " "); } throw new MissingObjectException(objectId.copy()); } return sz; }
public interface InstallationMedias { Observable<ImportInstallationMediaResponse> importInstallationMediaAsync(String customAvailabilityZoneId, String engine, String engineVersion); }
public PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
public NumberPtg(LittleEndianInput in) { field_1_value = in.readDouble(); }
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
} ; ) , ( > DescribeDetectorResponse < invoke return ; INSTANCE . DescribeDetectorResponseUnmarshaller responseUnmarshaller . options ; INSTANCE . DescribeDetectorRequestMarshaller requestMarshaller . options ; ) ( InvokeOptions new = options { ) request DescribeDetectorRequest ( describeDetector DescribeDetectorResponse
public final ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ReportInstanceStatusRequestMarshaller.INSTANCE; options.responseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.INSTANCE; return invoke(request, options); }
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
} ; ) ( PortugueseStemFilter new return { ) input TokenStream ( create TokenStream
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
boolean remove(Object object) { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(request, options); }
String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
} ; options _options {  ) options int ( WindowProtectRecord
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options); }
} ; ) , 16 + o , ( encodeInt32 . NB ; ) , 12 + o , ( encodeInt32 . NB ; ) , 8 + o , ( encodeInt32 . NB ; ) , 4 + o , ( encodeInt32 . NB ; ) , , ( encodeInt32 . NB { ) o int , b ] [ byte ( copyRawTo void
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.readShort(); field_2_v_hold = in.readShort(); field_3_width = in.readShort(); field_4_height = in.readShort(); field_5_options = in.readShort(); field_6_active_sheet = in.readShort(); field_7_first_visible_tab = in.readShort(); field_8_num_selected_tabs = in.readShort(); field_9_tab_width_ratio = in.readShort(); }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public void close() throws IOException { if (getIsOpen()) { getIsOpen(); try { dump(); } finally { try { channel.truncate(0); } finally { try { channel.close(); } finally { fos.close(); } } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return invoke(request, new DescribeMatchmakingRuleSetsRequestMarshaller(), new DescribeMatchmakingRuleSetsResponseUnmarshaller()); }
String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String GetPath() { return pathStr; }
public static double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
public void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { Row currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { Cell currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null || (!traverseEmptyCells && currentCell.getCellType() == CellType.BLANK)) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(currentCell, ctx); } } }
public int getReadIndex() { return _readIndex; }
} } ; ) boost . other ( compareTo . boost . return { else } ; ) term . ( compareTo . term . other return { ) boost . other == boost . ( if } ; 0 return { ) ) term . other ( bytesEquals . term ( if { ) other ScoreTerm ( compareTo int
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
} ; ) ( writeShort . out1 { ) out1 LittleEndianOutput ( serialize void
} ; exactOnly exactOnly . {  ) exactOnly boolean ( DiagnosticErrorListener
public KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
public boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public void setAllGroups(boolean allGroups) { this.allGroups = allGroups; }
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.setIsMultiValued(v); } }
} ; size return } } ; ++ size { ) 0 >= cmd . e ( if ; ) ( at = e Cell { ) ) ( keySet . cells : c Character ( for ; 0 = size int { ) ( getCellsVal int public
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance())); }
