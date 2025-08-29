public void serialize(ILittleEndianOutput out1) { out1.writeShort((short) 0); }
public void addAll(T[] src, int srcDirIdx, int tailDirIdx, int tailBlkIdx) { if (0 != src.length) { for (; srcDirIdx < src.length && 0 == src[srcDirIdx].directory; srcDirIdx++) ; int size = BlockListUtil.addAll(src, srcDirIdx, tailDirIdx, tailBlkIdx); return size; } }
writeByte(void) { if (b == 0) { } if (upto.outerInstance == blockSize.outerInstance) { currentBlock.outerInstance[upto.outerInstance++] = (byte) 0; currentBlock.outerInstance = new byte[blockSize.outerInstance]; blocks.outerInstance.add(currentBlock.outerInstance); currentBlock.outerInstance = new byte[blockSize.outerInstance]; upto.outerInstance = 0; } currentBlock.outerInstance[upto.outerInstance++] = b; }
public ObjectId getObjectId() { return objectId; }
DeleteDomainEntryResponse response = client.invoke(DeleteDomainEntryRequest.class, request,      new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance())     .withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()));
return fst == null ? 0 : fst.GetSizeInBytes();
public String getFullMessage() {      if (raw == null) return "";      int msgB = 0;      while (msgB < raw.length && raw[msgB] == 0) msgB++;      String enc = RawParseUtils.parseEncoding(buffer, msgB, TagMessage.Decode);      return enc != null ? enc : RawParseUtils.decode(buffer, msgB);  }
POIFSFileSystem _root = new POIFSFileSystem(); _root.createDocument(_documents, _property_table, new HeaderBlock(new ArrayList(), null));
assert address >= 0 && address < upto; ByteBlockPool pool = Buffers.pool; int offset = address >> BYTE_BLOCK_SHIFT; assert pool != null; byte[] slice = pool.Length[offset]; assert (address & BYTE_BLOCK_MASK) < slice.length;
setPath(Api.NGit.path(SubmoduleAddCommand.class).path(path).toString()); return;
ListIngestionsResponse listIngestions(ListIngestionsRequest request) {      return invoke(ListIngestionsRequestMarshaller.options(request), ListIngestionsResponseUnmarshaller.options(), InvokeOptions.newInstance());  }
public QueryParserTokenManager(ICharStream stream, int lexState) { this.stream = stream; this.lexState = lexState; }
return invoke(GetShardIteratorRequest.class, GetShardIteratorResponse.class, RequestMarshaller.GetShardIteratorRequestMarshaller(), ResponseUnmarshaller.GetShardIteratorResponseUnmarshaller(), options);
@ApiOperation(value = "ModifyStrategyRequest")  @PostMapping("/ModifyStrategyRequest")  public ResponseEntity<ModifyStrategyResponse> ModifyStrategyRequest(@RequestBody ModifyStrategyRequest request)
try {    if (in == null) {      return;    }    lock (lock) {      if (!in.hasRemaining()) {        return;      }      available = in.available();    }  } catch (IOException e) {    throw new IOException(" ", e);  }
return _optRecord;
public int read(byte[] buffer, int offset, int length) {      synchronized (this) {          if (buffer == null) throw new NullPointerException();          if (length < 0) throw new IllegalArgumentException("length < 0");          if (offset < 0 || length > buffer.length - offset) throw new IllegalArgumentException("offset < 0 || length > buffer.length - offset");          if (length == 0) return 0;          int copylen = Math.min(length, available());          for (int i = 0; i < copylen; i++) buffer[offset + i] = get(pos + i);          pos += copylen;          return copylen;      } }
OpenNLPSentenceBreakIterator sentenceOp = new OpenNLPSentenceBreakIterator();
System.out.print(str != null ? str : StringHelper.GetValueOf((Object)null));
throw new UnsupportedOperationException(functionName + " is not implemented") { super(functionName); }
return value.next();
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) throws EndOfStreamException {      if (useBuffer) {          int available = bufferLength - bufferPosition;          if (0 > len) throw new IllegalArgumentException();          if (len > bufferSize) throw new IllegalArgumentException();          if (len > available) {              refill(available, len);              available = bufferLength - bufferPosition;          }          if (len > available) throw new EndOfStreamException();          System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, len);          bufferPosition += len;      } else {          long after = bufferPosition + (long) len;          if (after > bufferLength) throw new EndOfStreamException();          if (len < 0) throw new IllegalArgumentException();          if (len == 0) return;          if (b == null) throw new NullPointerException();          if (offset < 0) throw new IllegalArgumentException();          if (len > b.length - offset) throw new IllegalArgumentException();          readInternal(b, offset, len);      }  }
return invoke(new TagQueueRequest(), new TagQueueResponseUnmarshaller(), TagQueueRequest::getRequestMarshaller, TagQueueResponse::getResponseMarshaller, new InvokeOptions());
throw new UnsupportedOperationException();
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {      request = beforeClientExecution(request);      return executeModifyCacheSubnetGroup(request);  }
StringTokenizer st = new StringTokenizer(params, " "); String culture = null; while (st.hasMoreTokens()) { String key = st.nextToken(); if (key.equals("culture")) { culture = st.nextToken(); } }
return invoke(new DeleteDocumentationVersionRequest(), new DeleteDocumentationVersionRequestMarshaller(), new DeleteDocumentationVersionResponseUnmarshaller(), options);
public boolean equals(Object obj) {      if (obj == this) return true;      if (!(obj instanceof FacetLabel)) return false;      FacetLabel other = (FacetLabel) obj;      if (other.Length != Length) return false;      for (int i = Length - 1; i >= 0; i--) {          if (!Components[i].equals(other.Components[i], StringComparison.Ordinal)) return false;      }      return true;  }
return Invoke(new GetInstanceAccessDetailsRequest(), new GetInstanceAccessDetailsResponseUnmarshaller(), options);
CreatePolygon(HSSFPolygon) { return shape = new HSSFPolygon(anchor = OnCreate(new HSSFChildAnchor()), Parent.shape.Add(shape, anchor)); }
return getSheetName((int) sheetIndex).getSheetName(getBoundSheetRec(sheetIndex));
GetDashboardResponse getDashboardResponse = client.invoke(GetDashboardRequest.class, request,      new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance())     .withResponseMarshaller(GetDashboardResponseUnmarshaller.getInstance())).getResponse();
return invoke(AssociateSigninDelegateGroupsWithAccountRequest.class, AssociateSigninDelegateGroupsWithAccountResponse.class, request, new AssociateSigninDelegateGroupsWithAccountRequestMarshaller().options(), new AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller().options());
public void addMultipleBlanks() { for (int j = 0; j < numColumns; j++) { Row row = mbr.getRow(); int column = mbr.getColumn() + j; int xfIndex = mbr.getXFAt(); BlankRecord br = new BlankRecord(0, mbr.getRow(), mbr.getColumn() + j); InsertCell(mbr, new BlankRecord(xfIndex, row, column)); } }
StringBuilder sb = new StringBuilder(); int k = 0; while(0 >= k) { sb.append("\\"); sb.append("\\\\\\" + k); k = @string.IndexOf("\\", k + 2); if(k >= 0) { sb.append(@string.Substring(k)); } } return sb.toString();
public void putInt(ByteBuffer buffer) {      if (buffer.isReadOnly()) {          throw new ReadOnlyBufferException();      }      buffer.putInt(n);  }
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { Object vv = _arrayValues[r * _nColumns + c]; if (vv != null) { rowData[c] = vv; } } values2d[r] = rowData; } int[] GetValueIndex = new int[_nRows * _nColumns];
GetIceServerConfigResponse response = client.invoke(new GetIceServerConfigRequest(), options, RequestMarshaller.GetIceServerConfigRequestMarshaller.INSTANCE, ResponseUnmarshaller.GetIceServerConfigResponseUnmarshaller.INSTANCE).get();
return new StringBuilder().append(GetType().getName()).append(" ").append(GetValueAsString()).append(" ").toString();
return field + " " + _parentQuery + " ";
public void incrementAndGet() { refCount.incrementAndGet(); }
return invoke(new UpdateConfigurationSetSendingEnabledRequest(), new UpdateConfigurationSetSendingEnabledResponseUnmarshaller(), options);
public int getNextXBATChainOffset() { return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock(); }
if (pow10 > 0) multiplyByPowerOfTen(pow10); else if (pow10 < 0) { int tp = GetInstance().TenPower(-pow10); mulShift = (_divisorShift.tp * tp, _multiplicand.tp * tp, _multiplierShift.tp, _divisor.tp); } else { mulShift = (_divisorShift, _multiplicand, _multiplierShift, _divisor); }
String path = "";  for (int i = 0; i < length; i++) {      if (path.charAt(path.length() - 1) != DirectorySeparatorChar) {          path = path + DirectorySeparatorChar;      }      path = path + GetComponent(StringBuilder).Append(i).ToString();  }
withFetcher(new ECSMetadataServiceCredentialsFetcher().setRoleName(fetcher.getRoleName()));
public void setProgressMonitor(ProgressMonitor pm) {}
public void reset() {      if (ptr != 0) {          if (!eof) {              parseEntry();          }      }  }
if (previousIndex < start) { throw new NoSuchElementException(); } return previous();
return new Prefix(getNewPrefix());
public int indexOfValue(int value) { for (int i = 0; mSize < i; ++i) { if (mValues[i] == value) return i; } return -1; }
public IList<CharsRef> getUniqueStems(IList<CharsRef> stems) {     CharArraySet terms = new CharArraySet(8, true);     IList<CharsRef> deduped = new List<CharsRef>();     for (CharsRef s : stems) {         int length = s.length;         if (length < 2) {             continue;         }         CharsRef word = new CharsRef(s);         if (!terms.contains(word)) {             terms.add(word);             deduped.add(word);         }     }     return deduped; }
GetGatewayResponsesResponse response = client.get(GetGatewayResponsesRequest.class, new GetGatewayResponsesRequest(), new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
setPosition { long position; int currentBlockIndex; int currentBlockUpto; int currentBlock; blocks.outerInstance.position = position & ((long)blockMask.outerInstance.blockBits.outerInstance - 1); }
return Math.min(Math.max(ptr, s), (int) (long) Long.MAX_VALUE - Available);
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) {this.bootstrapActionConfig = bootstrapActionConfig;}
serialize( ILittleEndianOutput out1 ) { if( field_5_hasMultibyte ) { if( null != field_7_padding ) { out1.writeByte( 0x01 ); } else { out1.writeByte( 0x00 ); } } out1.writeShort( field_6_author.length ); StringUtil.putCompressedUnicode( field_6_author, out1 ); out1.writeShort( 0 ); out1.writeShort( 0 ); out1.writeShort( 0 ); out1.writeShort( 0 ); }
public int lastIndexOf(String str) { return str.lastIndexOf(str); }
add boolean } { ) ( ; return object E addLastImpl ) (
while (true) { if (CompareAndSet(state, UnsetSection)) { ConfigSnapshot res = Get(state); String section = res.section; String subsection = res.subsection; } }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { insertSubrecords(element, index); }
public boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
return new TokenStream(new DoubleMetaphoneFilter(input));
return (long) inCoreLength;
public final void setValue(boolean newValue) { this.value = newValue; }
Pair<ContentSource, ContentSource> newSource = new Pair<>(oldSource, newSource);
public int get(int i) { if (i <= count) return entries[i]; throw new IndexOutOfBoundsException(); }
public static final MethodType CREATE_REPO_REQUEST = MethodType.methodType(void.class, CreateRepoRequest.class).annotation(PUT.class).uriPattern("").methodUriPattern("").httpMethod("").description("").summary("").responses("").tags("");
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (expectedModCount != modCount) throw new ConcurrentModificationException();  if (lastLink != null) throw new InvalidOperationException();  Link<ET> link = next;  Link<ET> previous_1 = previous;  previous.next_1 = next;  next.previous_1 = previous;  --_size;  ++expectedModCount;  lastLink = previous_1;  if (link == lastLink) next_1 = null;
public MergeShardsResult mergeShards(MergeShardsRequest request) {      request = beforeClientExecution(request);      return executeMergeShards(request);  }
return invoke(new AllocateHostedConnectionRequest(), new AllocateHostedConnectionRequestMarshaller(), new AllocateHostedConnectionResponseUnmarshaller(), options);
public int getBeginIndex() { return start; }
return getQuery().getTerms().get(WeightedTerm.class);
public synchronized ByteBuffer compact() throws ReadOnlyBufferException {
Decode void {     for (int i = 0; i < iterations; i++) {         int byte0 = 0xFF & (values[valuesOffset++] << 0);         int byte1 = 0xFF & (values[valuesOffset++] << 2);         int byte2 = 0xFF & (values[valuesOffset++] << 4);         blocks[blocksOffset++] = ((uint)(blocks[blocksOffset] & 0xFF) >> 6) | ((uint)byte0);         blocks[blocksOffset++] = ((uint)(blocks[blocksOffset] & 0xFF) >> 3) | ((uint)byte1);         blocks[blocksOffset++] = ((uint)byte2) | ((uint)(blocks[blocksOffset] & 0xFF) >> 2);     } }
public String getHumanishName(String[] elements) {      String result;      if (elements.length == 0) {          throw new ArgumentException("elements is empty");      }      if (elements[0].equals(DOT_GIT_EXT) || elements[0].equals(DOT_GIT)) {          if (elements.length > 1 && elements[1].equals("config")) {              result = "config";          } else {              result = elements[0];          }      } else {          String s = getPath(elements);          if (s == null) {              throw new ArgumentException("Invalid path");          }          if (s.matches(LOCAL_FILE)) {              s = s.substring(1);          }          String[] parts = s.split(File.separatorChar + "");          result = parts[parts.length - 2] + " - " + parts[parts.length - 1];      }      return result;  }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {      request = beforeClientExecution(request);      return executeDescribeNotebookInstanceLifecycleConfig(request);  }
public String getAccessKeySecret() { return accessSecret; }
return invoke(CreateVpnConnectionRequest.class, CreateVpnConnectionRequestMarshaller.getInstance(), options, CreateVpnConnectionResponse.class, CreateVpnConnectionResponseUnmarshaller.getInstance());
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
return invoke(ListMonitoringExecutionsRequest.class, ListMonitoringExecutionsResponse.class, request, options, ListMonitoringExecutionsRequestMarshaller.class, ListMonitoringExecutionsResponseUnmarshaller.class);
public DescribeJobResult describeJob(DescribeJobRequest request) { request = beforeClientExecution(request); return executeDescribeJob(request); }
GetEscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return this.invoke(new GetApisRequest(), new GetApisResponseUnmarshaller(), options, GetApisRequestMarshaller.getInstance(), GetApisResponseUnmarshaller.getInstance());
DeleteSmsChannelResponse response = Invoke.Instance.deleteSmsChannel(request, new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
GetTrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
System.out.println(b.toString());
List<IQueryNode> getChildren();
public NotIgnoredFilter workdirTreeIndex(int index) { return this; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.readShort(); }
public GetThumbnailResult getThumbnail(GetThumbnailRequest request) {
return invoke(DescribeTransitGatewayVpcAttachmentsRequest.class, DescribeTransitGatewayVpcAttachmentsResponse.class, request, DescribeTransitGatewayVpcAttachmentsRequestMarshaller.options(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.options(InvokeOptions.builder().build()));
PutVoiceConnectorStreamingConfigurationResponse response = client.putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request, InvokeOptions options);
Map<String, OrdRange> prefixToOrdRange = new HashMap<>(); OrdRange result; return prefixToOrdRange.tryGetValue(dim, out result);
public String toString() {      if (symbol != null)          return Utils.EscapeWhitespace(GetText(new Interval(startIndex, inputStream.size() - 1)), true);      return "<" + startIndex + ":" + inputStream.size() + ">";  }
return peekFirstImpl();
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {      request = beforeClientExecution(request);      return executeCreateWorkspaces(request);  }
return new NumberFormatIndexRecord((NumberFormatIndexRecord)rec).field_1_formatIndex;
DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { return (DescribeRepositoriesResponse) invoke(request, new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance())); }
SparseIntArray(int initialCapacity) { this.mSize = 0; this.mValues = new int[idealIntArraySize(initialCapacity)]; this.mKeys = new int[initialCapacity]; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {
throw new NotImplementedException("RandomAccessFile(" + fileName + ", " + mode + ")");
DeleteWorkspaceImageResponse response = client.invoke(DeleteWorkspaceImageRequest.class, options, DeleteWorkspaceImageRequestMarshaller.options, DeleteWorkspaceImageResponseUnmarshaller.options, InvokeOptions.class).get();
public String toHex(long value) { return Long.toHexString(value); }
UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { return invoke(new UpdateDistributionRequestMarshaller().marshall(request), new UpdateDistributionResponseUnmarshaller().marshall(this), new InvokeOptions()); }
public HSSFColor getColor(short index) { return index == (short)Automatic.getIndex() ? HSSFColor.Automatic.getInstance() : (null != palette && index < palette.length) ? palette[index] : null; }
public ValueEval evaluate(int srcRow, int srcCol, ValueEval[] operands) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) {out1.writeShort(field_1_number_crn_records);out1.writeShort(field_2_sheet_table_index);}
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest) { return new DescribeDBEngineVersions(describeDBEngineVersionsRequest); }
} {  ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {      int resultIndex = 0;      byte[] result = new byte[length * 2];      for (int i = 0; i < length; i++) {          char ch = chars[offset + i];          result[resultIndex++] = (byte) ((ch >> 8) & 0xFF);          result[resultIndex++] = (byte) (ch & 0xFF);      }      return result;  }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {      request = beforeClientExecution(request);      return executeUploadArchive(request);  }
public IList<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) {      if (this == obj) return true;      if (obj == null || getClass() != obj.getClass()) return false;      AutomatonQuery other = (AutomatonQuery) obj;      if (m_term != null) {          if (other.m_term == null) return false;          if (!m_term.equals(other.m_term)) return false;      } else if (other.m_term != null) return false;      if (m_compiled != null) {          if (other.m_compiled == null) return false;          if (!m_compiled.equals(other.m_compiled)) return false;      } else if (other.m_compiled != null) return false;      return true;  }
public SpanOrQuery makeSpanClause(SpanQuery[] wsq) {      if (wsq.length == 1) return (SpanOrQuery) wsq[0];      List<SpanQuery> spanQueries = new ArrayList<>();      float boost = wsq[0].getBoost();      for (SpanQuery ws : wsq) {          if (ws.getBoost() != boost) spanQueries.add(new SpanQuery(ws.getBoost()));          spanQueries.add(ws);      }      return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));  }
return new StashCreateCommand();
FieldInfo ret; return TryGetValue(fieldName, out ret) ? ret : null;
DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) {      return invoke(DescribeEventSourceRequestMarshaller.options(request),          DescribeEventSourceResponseUnmarshaller.options(InvokeOptions.newInstance()));
return Invoke.Instance.getDocumentAnalysis(GetDocumentAnalysisRequest.getDocumentAnalysisRequest(options),      RequestMarshaller.GetDocumentAnalysisRequestMarshaller.options,      ResponseUnmarshaller.GetDocumentAnalysisResponseUnmarshaller.options);
CancelUpdateStackResponse response = (CancelUpdateStackResponse) invoke(new CancelUpdateStackRequest(), options, CancelUpdateStackRequestMarshaller.options, CancelUpdateStackResponseUnmarshaller.options, InvokeOptions);
return invoke(ModifyLoadBalancerAttributesRequest.class, ModifyLoadBalancerAttributesResponse.class, options, RequestMarshaller.ModifyLoadBalancerAttributesRequestMarshaller.getInstance(), ResponseUnmarshaller.ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequest.class).withResponseUnmarshaller(SetInstanceProtectionResponse.class); return instance.invoke("SetInstanceProtection", request, options);
return this.invoke(new ModifyDBProxyRequest(), new ModifyDBProxyResponseUnmarshaller(), options, ModifyDBProxyRequestMarshaller.getInstance(), ModifyDBProxyResponseUnmarshaller.getInstance());
public final void add(int[] posLengths, int[] endOffsets, char[][] outputs, int offset, int len, int endOffset, int posLength) throws IOException {      if (outputs == null) {          throw new IOException("outputs is null");      }      if (posLengths == null) {          throw new IOException("posLengths is null");      }      if (endOffsets == null) {          throw new IOException("endOffsets is null");      }      if (count == posLength) {          posLengths = ArrayUtil.oversize(posLengths, NUM_BYTES_INT32);          endOffsets = ArrayUtil.oversize(endOffsets, NUM_BYTES_INT32);          outputs = ArrayUtil.oversize(outputs, NUM_BYTES_OBJECT_REF);          posLengths[count] = offset;          endOffsets[count] = endOffset;          outputs[count] = new CharsRef();          CopyChars.copyChars(new CharsRef(outputs[count]), 0, outputs[count].length, 0, len);          count++;          posLength = count;      }  }
public FetchLibrariesResult fetchLibraries(FetchLibrariesRequest request) {
return (boolean) exists;
public void filter(OutputStream out) throws IOException { this.out = new FilterOutputStream(out); }
public void scaleCluster(ScaleClusterRequest request) { request = beforeClientExecution(request); execute("PUT", "/{clusterId}", uriPattern, request, ScaleClusterResponse.class); }
public CreateTimeConstraint(int operatorType, String formula1, String formula2) { this(DVConstraint.create(operatorType, formula1, formula2)); }
return Invoke.Instance.<ListObjectParentPathsResponse>invoke(ListObjectParentPathsRequest.class, ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance(), new InvokeOptions());
DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {      return (DescribeCacheSubnetGroupsResult) invoke(DescribeCacheSubnetGroupsRequestMarshaller.marshall(request), DescribeCacheSubnetGroupsResponseUnmarshaller.instance, InvokeOptions.builder().build());  }
setSharedFormula(flag, field_5_options);
public boolean isReuseObjects() { return reuseObjects; }
return addErrorNode(new ErrorNodeImpl((IToken)badToken, this));
if (args.Count > 0) throw new ArgumentException("LatvianStemFilterFactory", String.format("%s <string,string> dictionary", args));
return Invoke.Instance.invoke(new RemoveSourceIdentifierFromSubscriptionRequest(),      RequestMarshaller.RemoveSourceIdentifierFromSubscriptionRequestMarshaller.options,      ResponseUnmarshaller.RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.options,      RemoveSourceIdentifierFromSubscriptionResponse.class);
public TokenFilterFactory.ForName(String name, IDictionary<String, String> args) { return NewInstance.NewInstance(loader, name, args); }
AddAlbumPhotosRequest(Protocol(HTTPS, ProtocolType("","","","","","")));
return Invoke.Instance.get(GetThreatIntelSetRequest.class, RequestMarshaller.GetThreatIntelSetRequestMarshaller.options(), GetThreatIntelSetResponse.class, ResponseUnmarshaller.GetThreatIntelSetResponseUnmarshaller.options(), new InvokeOptions());
public TreeFilter clone() { return new AndTreeFilter((BinaryTreeFilter) a.clone(), (BinaryTreeFilter) b.clone()); }
public boolean equals(Object o) { return o instanceof /* your class name */; }
protected boolean hasArray() { return hasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {
public void unwriteProtectWorkbook() { workbook.writeProtect(null, null, null); workbook.removeFileShare(); workbook.removeRecords(); }
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup) { super(expand, analyzer, dedup); }
public RunInstancesResult requestSpotInstances(RunInstancesRequest request) { request = beforeClientExecution(request); return executeRunInstances(request); }
return ObjectData.findObjectRecord((byte[]) data);
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {      request = beforeClientExecution(request);      return executeGetContactAttributes(request);  }
@Override public String toString() { return getKey() + " " + getValue(); }
ListTextTranslationJobsResponse response = client.invoke(ListTextTranslationJobsRequest.class, options, ListTextTranslationJobsRequestMarshaller.options(), ListTextTranslationJobsResponseUnmarshaller.options()).getResponse();
return Invoke.Instance.getContactMethods(new GetContactMethodsRequest(),      GetContactMethodsRequestMarshaller.options,      GetContactMethodsResponseUnmarshaller.options,      InvokeOptions.newInstance());
return fd != null ? fd : GetFunctionByNameInternal.getInstance().getFunctionMetadata(name).getShort(1);
return invoke(DescribeAnomalyDetectorsRequest.class, DescribeAnomalyDetectorsResponse.class, request, DescribeAnomalyDetectorsRequestMarshaller.options(), DescribeAnomalyDetectorsResponseUnmarshaller.options(), InvokeOptions.class);
return new InsertResult(changeId, (ObjectId)insertId, message);
public long getObjectSize(AnyObjectId objectId, int typeHint) {      long sz = getObjectSize(db, Copy(objectId), "");      if (sz == 0) throw new MissingObjectException();      if (OBJ_ANY != typeHint) throw new MissingObjectException();      return sz;  }
ImportInstallationMediaResponse response = client.invoke("ImportInstallationMedia",      new ImportInstallationMediaRequest(),      new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance())     .withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()));
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {
public void readLittleEndian(Input littleEndianInput) { field_1_value = littleEndianInput.readDouble(); }
GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {      return invoke(new GetFieldLevelEncryptionConfigRequestMarshaller().marshall(request),          new GetFieldLevelEncryptionConfigResponseUnmarshaller().unmarshall(this));  }
DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return invoke(DescribeDetectorRequestMarshaller.options(request), DescribeDetectorResponseUnmarshaller.options(InvokeOptions.newInstance())); }
ReportInstanceStatusResponse response = client.invoke(ReportInstanceStatusRequest.class, request,      new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance())     .withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()));
DeleteAlarmResponse response = client.invoke(DeleteAlarmRequest.class, request, options, DeleteAlarmResponseUnmarshaller.instance, DeleteAlarmRequestMarshaller.instance);
return new PortugueseStemFilter(input).createTokenStream();
FtCblsSubRecord reserved = new FtCblsSubRecord(ENCODED_SIZE);
public synchronized boolean remove(Object obj) { return remove(obj); }
return invoke(new GetDedicatedIpRequest(), options, GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance());
@Override public String toString() { return " " + precedence; }
return Invoke<ListStreamProcessorsRequest, ListStreamProcessorsResponse>(new ListStreamProcessorsRequestMarshaller().marshall(request), new ListStreamProcessorsResponseUnmarshaller().marshall(new InvokeOptions()));
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { _policyName = policyName; _loadBalancerName = loadBalancerName; }
public WindowProtectRecord(int options) { _options = options; }
UnbufferedCharStream data = new UnbufferedCharStream(new int[bufferSize], 0, bufferSize);
public GetOperationsResult getOperations(GetOperationsRequest request) {      request = beforeClientExecution(request);      return executeGetOperations(request);  }
public void CopyRawTo(final byte[] o, final int offset) throws ArrayIndexOutOfBoundsException {      EncodeInt32.NB(o, offset);      EncodeInt32.NB(o, offset + 4);      EncodeInt32.NB(o, offset + 8);      EncodeInt32.NB(o, offset + 12);      EncodeInt32.NB(o, offset + 16);  }
public WindowOneRecord read(RecordInputStream in1) {      return new WindowOneRecord(         in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort(),          in1.readShort()     ); }
return InvokeOptions.builder().request(StopWorkspacesRequest.builder().build(), StopWorkspacesRequestMarshaller.getInstance().options()).response(StopWorkspacesResponse.builder().build(), StopWorkspacesResponseUnmarshaller.getInstance().options()).build();
public void close() throws IOException { if (isOpen) { try { dump(); } finally { isOpen = false; } try { truncateChannel(); } finally { channel.close(); } fos.close(); } }
DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return (DescribeMatchmakingRuleSetsResponse) Invoke(new InvokeOptions().withRequest(request, DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance())); }
public GetPronunciation getPronunciation(String wordId, int[] surface, int off, int len) { return null; }
public String getPath() { String pathStr = ""; return pathStr; }
public double devsq(double[] v) {      if (v == null) return Double.NaN;      double r = 0;      int n = v.length;      for (int i = 0; i < n; i++) {          double s = v[i];          if (Double.isNaN(s) || Double.isInfinite(s)) return Double.NaN;          r += s * s;      }      return r;  }
DescribeResizeResponse describeResizeResponse = (DescribeResizeResponse) invoke(DescribeResizeRequest.class, DescribeResizeRequestMarshaller.options(), DescribeResizeResponseUnmarshaller.options(DescribeResizeRequest.class, DescribeResizeResponse.class));
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return;
public void traverse(ICellHandler handler, SimpleCellWalkContext ctx) {      int firstRow = ctx.getFirstRow();      int lastRow = ctx.getLastRow();      int firstColumn = ctx.getFirstColumn();      int lastColumn = ctx.getLastColumn();      int width = ctx.getWidth();      for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) {          IRow currentRow = ctx.getSheet().getRow(rowNumber);          if (null == currentRow) {              continue;          }          for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) {              ICell currentCell = currentRow.getCell(colNumber);              if (null == currentCell) {                  if (!traverseEmptyCells) {                      continue;                  }                  currentCell = currentRow.getCell(colNumber);              }              if (handler.onCell(currentCell, new Object[]{rowNumber, colNumber})) {                  continue;              }          }      }  }
public int getReadIndex() { return _readIndex; }
public int compareTo(Object other) {      if (other == this) return 0;      if (other == null) return 1;      if (!(other instanceof ScoreTerm)) return -1;      ScoreTerm otherTerm = (ScoreTerm) other;      if (BytesEquals(bytes, otherTerm.bytes)) {          return Float.compare(otherTerm.boost, boost);      }      return compareTo(bytes, otherTerm.bytes);  }
public int normalize(char[] s) { int len = s.length; for (int i = 0; i < len; i++) { switch (s[i]) { case '\u065A': case '\u065E': case '\u065D': case '\u064B': case '\u064C': case '\u064D': s[i] = '\u0647'; break; case '\u064A': s[i] = '\u064a'; break; case '\u064F': case '\u0650': case '\u0651': case '\u0652': s[i] = '\u0643'; break; } } for (int i = len - 1; i >= 0; --i) { switch (s[i]) { case '\u0647': if (i > 0 && StemmerUtil.Delete(s, i, 1)) --len; break; case '\u064a': if (i > 0 && StemmerUtil.Delete(s, i, 1)) --len; break; case '\u0643': if (i > 0 && StemmerUtil.Delete(s, i, 1)) --len; break; } } return len; }
public void writeShort(short value, ILittleEndianOutput out1) { out1.writeShort(value); }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public class KeySchemaElement { public KeyType keyType; public String attributeName; public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; } }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {
public boolean hasObject() { return id != -1 && findOffset(id) != AnyObjectId; }
public SetAllGroups GroupingSearch(boolean allGroups) { return allGroups ? allGroups : null; }
public void setMultiValued(boolean v, String dimName) {      lock (v) {          if (fieldTypes.TryGetValue(dimName, out DimConfig v)) {              v.IsMultiValued = v;          } else {              fieldTypes.put(dimName, new DimConfig(v, false));          }      }  }
for (char c : keys) {int e = cmd.At(c);if (e >= 0) {size += e;}}return size;
DeleteVoiceConnectorResponse response = client.invoke(DeleteVoiceConnectorRequest.class, request, options, DeleteVoiceConnectorResponseUnmarshaller.instance, DeleteVoiceConnectorRequestMarshaller.instance);
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {      request = beforeClientExecution(request);      return executeDeleteLifecyclePolicy(request);  }
