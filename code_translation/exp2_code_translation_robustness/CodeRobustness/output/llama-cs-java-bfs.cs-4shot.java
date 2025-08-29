public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll(Collection<? extends T> c) { int numNew = c.size(); if (numNew == 0) return; if (size == 0) { src = (T[]) c.toArray(); } else { Object[] a = c.toArray(); int srcDirIdx = 0; int tailDirIdx = size; int tailBlkIdx = tailDirIdx - 1; for (int i = 0; i < numNew; i++) { T e = (T) a[i]; if (srcDirIdx <= tailBlkIdx && Util.compare(src[srcDirIdx], e) <= 0) { src[tailDirIdx++] = src[srcDirIdx++]; } else { src[tailBlkIdx--] = e; } } src = Arrays.copyOf(src, size + numNew); } }
WriteByte(b -> { if (b == 0) { if (++upto.outerInstance >= blockSize.outerInstance) { currentBlock.outerInstance = new byte[blockSize.outerInstance]; blocks.outerInstance.Add(currentBlock.outerInstance); currentBlock.outerInstance = null; } } currentBlock.outerInstance[upto.outerInstance] = b; blocks.outerInstance.Add(currentBlock.outerInstance); });
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
return fst == null ? 0 : fst.getSizeInBytes();
public String getFullMessage(byte[] buffer) {      if (buffer.length == 0) return "";      int msgB = RawParseUtils.decode(buffer, 0, buffer.length);      return msgB < 0 ? "" : new String(buffer, 0, msgB, ParseEncoding.getEncoding());  }
POIFSFileSystem fs = new POIFSFileSystem(); _root = fs.getRoot(); _documents = new ArrayList(); _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock(); _header = headerBlock;
assert address >= 0 && address < upto && (slice != null) && ((address & (BYTE_BLOCK_MASK - 1)) == 0) && (slice.length >> BYTE_BLOCK_SHIFT == Buffers.pool.getByteBlockPool().length);
public SubmoduleAddCommand setPath(String path) { return api.ngit().path(path); }
ListIngestionsResponse listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public void SwitchTo(int lexState, ICharStream stream) { this.lexer.switchTo(lexState, stream); }
return invoke(new GetShardIteratorRequest(), new GetShardIteratorResponseUnmarshaller(), options, RequestMarshaller.GetShardIteratorRequestMarshaller.INSTANCE, ResponseUnmarshaller.GetShardIteratorResponseUnmarshaller.INSTANCE);
public static final ModifyStrategyRequest MODIFY_STRATEGY = new ModifyStrategyRequest(Method.POST, "ModifyStrategy", "ModifyStrategy", "ModifyStrategy");
public boolean ready() {      try {          if (in == null) {              return false;          }          lock.lock();          return in.available() > 0 || in.hasRemaining();      } catch (IOException e) {          throw new IOException(e);      } finally {          lock.unlock();      }  }
return (GetOptRecord) _optRecord;
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new NullPointerException("buffer"); if (length < 0) throw new IllegalArgumentException("length < 0"); if (offset < 0 || offset >= buffer.length || offset + length > buffer.length) throw new IndexOutOfBoundsException("offset out of range"); int copylen = Math.min(length, available()); if (copylen == 0) return 0; for (int i = 0; i < copylen; i++) buffer[offset + i] = get(pos++); return copylen; }
public OpenNLPSentenceBreakIterator sentenceOp(NLPSentenceDetectorOp sentenceOp) { return this; }
System.out.print(str != null ? str : StringHelper.GetValueOf((Object)null));
public NotImplementedException(String functionName, Throwable cause) { super(functionName, cause); }
return value.nextEntry();
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) throws IOException {      if (useBuffer) {          if (len > bufferSize) {              refill(len);          }          if (len > bufferLength - bufferPosition) {              refill(len);          }          if (len > available) {              throw new EndOfStreamException();          }          System.arraycopy(buffer, bufferPosition, b, offset, len);          bufferPosition += len;      } else {          if (len > available) {              throw new EndOfStreamException();          }          long after = position() + len;          if (after > length) {              throw new EndOfStreamException();          }          readInternal(b, offset, len);      }  }
public TagQueueResult tagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
throw new UnsupportedOperationException();
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {      request = beforeClientExecution(request);      return executeModifyCacheSubnetGroup(request);  }
StringTokenizer st = new StringTokenizer(params, " "); String culture = ""; while (st.hasMoreTokens()) { String token = st.nextToken(); if (token.equals("culture")) { culture = st.nextToken(); } } setParams(culture);
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {      request = beforeClientExecution(request);      return executeDeleteDocumentationVersion(request);  }
public boolean equals(Object obj) {if (obj == this) return true;if (!(obj instanceof FacetLabel)) return false;FacetLabel other = (FacetLabel) obj;if (other.Length != Length) return false;for (int i = 0; i < Length; i++) {if (!Components[i].equals(other.Components[i], StringComparison.Ordinal)) return false;}return true;}
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
shape = new HSSFPolygon((HSSFChildAnchor)anchor);
return getSheetName((int) getBoundSheetRec(sheetIndex).getSheetName());
GetDashboardResponse getDashboard(GetDashboardRequest request) { return executeGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {      request = beforeClientExecution(request);      return executeAssociateSigninDelegateGroupsWithAccount(request);  }
for (int j = 0; j < NumColumns; j++) {     mbr.InsertCell(j, new BlankRecord((short) 0, mbr.GetXFAt(mbr.FirstColumn + j), (short) (mbr.Row + j))); }
String toString(String string) {      StringBuilder sb = new StringBuilder();      int k = 0;      while (k < string.length()) {          char c = string.charAt(k);          if (c == '\\') {              sb.append("\\\\");          } else if (c == '"') {              sb.append("\\\"");          } else if (c == '\'') {              sb.append("\\'");          } else {              sb.append(c);          }          k += 1;      }      return sb.toString();  }
public void putInt(int value) { if (readOnly) { throw new ReadOnlyBufferException(); } java.nio.ByteBuffer.allocate(4).putInt(value); }
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { Object vv = _arrayValues[r][c]; if (vv != null) { rowData[c] = vv; } } values2d[r] = rowData; } int[] GetValueIndex = new int[_nRows * _nColumns];
GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance());      options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance());      return invoke(request, options);  }
return new StringBuilder().append(GetType().getName()).append(" (").append(GetValueAsString()).append(" )").toString();
public String toString() { return field + " " + _parentQuery + " "; }
public void incrementAndGet() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {return execute("UpdateConfigurationSetSendingEnabled", request, UpdateConfigurationSetSendingEnabledResponse.class);}
return (int) (((*GetNextXBATChainOffset) & 0xFFFFFFFFL) * LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock);
private void multiplyByPowerOfTen(int pow10) {      if (pow10 < 0) {          int tp = -pow10;          _divisor = TenPower.GetInstance(tp).multiply(_divisor);          _divisorShift += tp;      } else {          int tp = pow10;          _multiplier = TenPower.GetInstance(tp).multiply(_multiplier);          _multiplierShift += tp;      }  }
public String toString() {      StringBuilder builder = new StringBuilder();      for (int i = 0; i < length; i++) {          if (i > 0) builder.append(Path.DirectorySeparatorChar);          builder.append(GetComponent(i).toString());      }      return builder.toString();  }
withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; }
setProgressMonitor(ProgressMonitor pm) { }
reset() { if (eof != 0) ptr = first; }
if (previousIndex < 0) throw new NoSuchElementException();  return previous;
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) {for (int i = 0; i < mSize; i++) {if (mValues[i] == value) {return i;}}return -1;}
public UniqueStems(List<CharsRef> stems) {      List<CharsRef> deduped = new ArrayList<>();      CharArraySet terms = new CharArraySet(8, true);      for (CharsRef s : stems) {          int length = s.length;          if (length > 2) {              CharRef ref = new CharsRef();              s.copyTo(ref);              if (!terms.contains(ref)) {                  terms.add(ref);                  deduped.add(ref);              }          }      }      this.stems = deduped;  }
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
public void setPosition(long position, int currentBlockIndex, int currentBlock, long currentBlockUpto) { int blockBits = outerInstance.blockBits; int blockMask = outerInstance.blockMask; position = (position & ((long)blockMask)) + ((long)currentBlockIndex << blockBits); blocks[currentBlock].setPosition(position); }
return Math.min(Math.max(ptr, s), n);
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
public void serialize(ILittleEndianOutput out1) {      if (field_5_hasMultibyte) {          out1.writeByte(0x01);      } else {          out1.writeByte(0x00);      }      if (field_7_padding != null) {          out1.writeShort(field_7_padding.length);      } else {          out1.writeShort(0);      }      out1.writeShort(field_6_author.length);      StringUtil.putCompressedUnicode(field_6_author, out1);  }
public int lastIndexOf(String str) { return string.lastIndexOf(str); }
public void addLastImpl(E object, bool add) { return; }
public void unsetSection(String section, String subsection) {      ConfigSnapshot res;     ConfigSnapshot src;     do {         res = state.get();         src = res;     } while (!CompareAndSet(state, res, res.unsetSection(section, subsection)));     Get(state); }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { subrecords.Insert(index, element); }
public synchronized void remove() { mutex.lock(); try { object.remove(); } finally { mutex.unlock(); } }
return new DoubleMetaphoneFilter(input, new TokenStream());
public long getLength() { return inCoreLength; }
setValue(boolean newValue) { value = newValue; }
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
public int get(int i) { if (i < 0 || i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
public static final Method PUT = MethodType.methodType(void.class, CreateRepoRequest.class).withName("").withReturnType(void.class);
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount != expectedModCount) throw new ConcurrentModificationException();  if (lastLink == null) throw new InvalidOperationException();  if (link == lastLink) {      previous_1 = link.previous;      next_1 = null;  } else {      previous_1 = link.previous;      next_1 = link.next;      previous_1.next = next_1;      next_1.previous = previous_1;  }  --_size;  ++modCount;  expectedModCount = modCount;  pos = -1;  lastLink = link;
return Invoke(MergeShardsRequest.class, options, MergeShardsRequestMarshaller.class, MergeShardsResponse.class, MergeShardsResponseUnmarshaller.class);
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection(request); }
public int getBeginIndex() { return start; }
return getQuery(new GetTermsQuery(), Arrays.asList(new WeightedTerm[] {}));
throw new ReadOnlyBufferException();
public void decode() {      for (int i = 0; i < iterations; i++) {          int byte0 = blocks[blocksOffset++] & 0xFF;          int byte1 = blocks[blocksOffset++] & 0xFF;          int byte2 = blocks[blocksOffset++] & 0xFF;          values[valuesOffset++] = (byte0 & 0x3F) | (byte1 << 2) | ((byte2 & 0x03) << 8);          values[valuesOffset++] = ((byte2 & 0x1C) >> 2) | (byte0 << 6) | (byte1 << 14);      }  }
public String getHumanishName(String s) {      if (s == null) {          throw new ArgumentException("s");      }      if (s.length() == 0) {          throw new ArgumentException("s");      }      if (s.equals(DOT_GIT_EXT.getConstants()[0])) {          return DOT_GIT.getConstants()[0];      }      if (s.endsWith(File.separator + DOT_GIT_EXT.getConstants()[0])) {          s = s.substring(0, s.length() - DOT_GIT_EXT.getConstants()[0].length());      }      if (s.endsWith(DOT_GIT_EXT.getConstants()[0])) {          s = s.substring(0, s.length() - DOT_GIT_EXT.getConstants()[0].length());      }      String[] elements = s.split(File.separator);      if (elements.length == 1) {          return elements[0];      }      if (elements[elements.length - 1].equals(DOT_GIT.getConstants()[0])) {          return elements[elements.length - 2];      }      return elements[elements.length - 1];  }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {      request = beforeClientExecution(request);      return executeCreateVpnConnection(request);  }
DescribeVoicesResponse describeVoicesResponse = invoke(DescribeVoicesRequest.class, DescribeVoicesRequestMarshaller.options(), DescribeVoicesResponseUnmarshaller.options(), DescribeVoicesRequest.class, DescribeVoicesResponse.class); return describeVoicesResponse;
ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String jobId, String vaultName) { this._jobId = jobId; this._vaultName = vaultName; }
GetEscherRecord escherRecord = escherRecords[index];
return invoke(new GetApisRequest(), new GetApisResponseUnmarshaller(), options, RequestMarshaller.GetApisRequestMarshaller.INSTANCE, ResponseUnmarshaller.GetApisResponseUnmarshaller.INSTANCE);
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
GetTrackingRefUpdate trackingRefUpdate() { return trackingRefUpdate; }
@Override public String toString() {     return "print " + b + " void } { ) ( ;"; }
public IQueryNode getChild(int i) { return getChildren().get(i); }
public NotIgnoredFilter(int index) { this.index = index; }
public AreaRecord read(RecordInputStream in1) { return readShort(in1, field_1_formatFlags); }
return getProtocol().equals(ProtocolType.HTTPS) ? new GetThumbnailRequest() : null;
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
return dim.TryGetValue(prefixToOrdRange, out OrdRange result) ? result : GetOrdRange;
@Override public String toString() {     if (getToken() == null) {         return "";     }     Interval interval = getInputStream().getInterval();     String symbol = getSymbol();     String text = getText();     return String.format("%s %d,%d:%d,%d <%s> %s",          getClass().getSimpleName(),          interval.a,          interval.b,          interval.c,          interval.d,          symbol,          text); }
return peekFirstImpl();
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
return (Cloneable) new NumberFormatIndexRecord((NumberFormatIndexRecord)rec);
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
SparseIntArray(int initialCapacity) {this(new int[idealIntArraySize(initialCapacity)], new int[initialCapacity], 0, 0, 0);}
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) {     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance());     options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance());     return invoke(request, options); }
public void write(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {      request = beforeClientExecution(request);      return executeDeleteWorkspaceImage(request);  }
public String toHex(long value) { return Long.toHexString(value); }
UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
public HSSFColor getColor(short index) {      HSSFColor b = (HSSFColor) palette.get(index);      return b != null ? b : HSSFColor.Automatic.GetInstance();  }
public ValueEval evaluate(int srcRow, int srcCol, ValueEval[] operands) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out) {out.writeShort(field_1_number_crn_records);out.writeShort(field_2_sheet_table_index);}
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest) { return new DescribeDBEngineVersions().describeDBEngineVersions(describeDBEngineVersionsRequest); }
public FormatRun(short fontIndex, short character) { _fontIndex = fontIndex; _character = character; }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {      int resultLength = length * 2;      byte[] result = new byte[resultLength];      for (int i = 0, resultIndex = 0; i < length; i++, resultIndex += 2) {          char ch = chars[offset + i];          result[resultIndex] = (byte) ((ch >> 8) & 0xFF);          result[resultIndex + 1] = (byte) (ch & 0xFF);      }      return result;  }
UploadArchiveResponse response = client.invoke(UploadArchiveRequest.class, UploadArchiveRequest.builder().build(), options, UploadArchiveRequestMarshaller.options, UploadArchiveResponseUnmarshaller.options, UploadArchiveResponse.class).getResponse().get(UploadArchiveResponse.class);
public IList<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) {if (obj == this) return true;if (obj == null || getClass() != obj.getClass()) return false;AutomatonQuery other = (AutomatonQuery) obj;return m_term.equals(other.m_term) && m_compiled.equals(other.m_compiled);}
MakeSpanClause SpanQuery } { ) ( ;  else return ) ( if ) weightBySpanQuery in wsq ( for (SpanQuery spanQuery : wsq) { ;  SpanOrQuery ; return 1 == } { ) ( spanQueries.size() . spanQueries ; ; List<SpanQuery> spanQueries = new ArrayList>() ;  spanQueries.add(wsq.getKey().getBoost() * wsq.getValue()); return spanQueries.toArray(new SpanQuery[0]);
return new StashCreateCommand();
FieldInfo fieldInfo; return type.GetFieldInfoByName(fieldName, out fieldInfo);
DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {      request = beforeClientExecution(request);      return executeCancelUpdateStack(request);  }
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {      request = beforeClientExecution(request);      return executeModifyLoadBalancerAttributes(request);  }
return invoke(SetInstanceProtectionRequest.class, SetInstanceProtectionResponse.class, options, SetInstanceProtectionRequestMarshaller.options(), SetInstanceProtectionResponseUnmarshaller.options());
ModifyDBProxyResponse modifyDBProxyResponse = invoke(ModifyDBProxyRequest.class, ModifyDBProxyRequestMarshaller.options(), ModifyDBProxyResponse.class, ModifyDBProxyResponseUnmarshaller.options(), new InvokeOptions());
public void add(int offset, int len, int endOffset, int posLength) { int count = 0; if (outputs == null) { outputs = new CharsRef[count + 1]; posLengths = new int[count + 1]; endOffsets = new int[count + 1]; } if (count == outputs.length) { outputs = ArrayUtil.oversize(outputs, 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF); posLengths = ArrayUtil.oversize(posLengths, 1, RamUsageEstimator.NUM_BYTES_INT32); endOffsets = ArrayUtil.oversize(endOffsets, 1, RamUsageEstimator.NUM_BYTES_INT32); } outputs[count] = new CharsRef(); posLengths[count] = posLength; endOffsets[count] = endOffset; count++; posLength = 0; }
public FetchLibrariesResult fetchLibraries(FetchLibrariesRequest request) {request = beforeClientExecution(request);return executeFetchLibraries(request);}
public boolean exists() { return exists; }
public void FilterOutputStream(OutputStream out) { }
public static final String ScaleClusterRequest = "/";  public static final MethodType MethodType = MethodType.PUT;  public static final UriPattern UriPattern = UriPattern.of(" ", " ", " ", " ", " ", " ", " ", " ", " ");
CreateTimeConstraint implements IDataValidationConstraint { public CreateTimeConstraint(String formula1, String formula2, int operatorType) { return DVConstraint.createTimeConstraint(formula1, formula2, operatorType); } }
ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {      request = beforeClientExecution(request);      return executeDescribeCacheSubnetGroups(request);  }
public void setSharedFormula(boolean flag, SetShortBoolean field_5_options) { }
public boolean isReuseObjects() { return reuseObjects; }
AddErrorNode IErrorNode } { return new ErrorNodeImpl((IToken) t, badToken, (Parent) t); }
if (args == null || args.size() == 0) { throw new IllegalArgumentException("args"); }
return Invoke<>(new RemoveSourceIdentifierFromSubscriptionRequest(), RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance(), new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller(), options);
public TokenFilterFactory ForName(String name, IDictionary<String, String> args) { return (TokenFilterFactory) NewInstance(name, args); }
AddAlbumPhotosRequest.addProtocol(ProtocolType.HTTPS, " ", " ", " ", " ", " ");
return invoke(new GetThreatIntelSetRequest(), new GetThreatIntelSetResponseUnmarshaller(), options, GetThreatIntelSetRequestMarshaller.options, GetThreatIntelSetResponse.options);
return new Binary.AndTreeFilter((TreeFilter) a.clone(), (TreeFilter) b.clone());
@Override public boolean equals(Object o) {     return o != null; }
protected boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
unwriteProtectWorkbook(null, null, null, null, null, null, null);
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup) { super(expand, analyzer, dedup); }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
getObjectData() { return (byte[]) ObjectData.findObjectRecord(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
return GetKey() + " " + GetValue() + ")";
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
return Invoke(new GetContactMethodsRequest(), GetContactMethodsResponse.class, RequestMarshaller.GetContactMethodsRequestMarshaller(), ResponseUnmarshaller.GetContactMethodsResponseUnmarshaller(), new InvokeOptions());
short lookupIndexByName(String name) { FunctionMetadata fd = GetFunctionByNameInternal.getInstance().getFunctionMetadata(name); return fd != null ? fd.getIndex() - 1 : -1; }
return invoke(DescribeAnomalyDetectorsRequest.class, DescribeAnomalyDetectorsResponse.class, options, DescribeAnomalyDetectorsRequestMarshaller.INSTANCE, DescribeAnomalyDetectorsResponseUnmarshaller.INSTANCE);
public InsertId(String changeId, ObjectId message) { this.changeId = changeId; this.message = message; return this; }
public long getObjectSize(int typeHint, AnyObjectId objectId) {      long sz = getObjectSize(db, Copy(objectId), "");      if (sz < 0) throw new MissingObjectException();      if (OBJ_ANY != typeHint) throw new MissingObjectException();      return sz;  }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) {      request = beforeClientExecution(request);      return executeImportInstallationMedia(request);  }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {      request = beforeClientExecution(request);      return executePutLifecycleEventHookExecutionStatus(request);  }
public NumberPtg( LittleEndianInput in ) { this.field_1_value = in.readDouble(); }
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {      request = beforeClientExecution(request);      return executeGetFieldLevelEncryptionConfig(request);  }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
return new PortugueseStemFilter(input).tokenStream();
public static byte[] Grow(byte[] array) { return Grow(array, 1 + array.Length); }
public synchronized boolean remove(Object object) { return c.remove(object); }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
return " " + precedence.toString();
ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) {_policyName = policyName; _loadBalancerName = loadBalancerName;}
public WindowProtectRecord(int options) { this._options = options; }
new int[bufferSize]
GetOperationsResponse getOperationsResponse = invoke(new GetOperationsRequest(), GetOperationsRequestMarshaller.options(), GetOperationsResponseUnmarshaller.options());
CopyRawTo { void encode(int o) { byte[] b = new byte[16 + 12 + 8 + 4]; EncodeInt32.encode(b, 0, o); EncodeInt32.encode(b, 4, o); EncodeInt32.encode(b, 8, o); EncodeInt32.encode(b, 12, o); } }
public void read(RecordInputStream in1) {field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort();}
StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance());      options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance());      return invoke(StopWorkspacesResponse.class, request, options);  }
public void close() throws IOException {      if (isOpen()) {          try {              dump();          } finally {              try {                  truncateChannel();              } finally {                  closeChannel();                  closeFos();              }          }      }  }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int[] wordId, int off, int len, char[] surface) { return null; }
public String getPath() { return pathStr; }
public double[] someMethod() {      double r = 0;      if (v != null) {          double[] m = new double[n];          for (int i = 0; i < n; ++i) {              m[i] = Double.NaN;          }          for (int i = 0; i < v.length; ++i) {              if (i >= n) {                  break;              }              m[i] = v[i] / s;          }          r = s != 0 ? m[0] : 0;      }      return r == 1 ? new double[0] : m;  }
DescribeResizeResult describeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return;
public void traverse(ICellHandler handler, SimpleCellWalkContext ctx) {     int firstRow = ctx.getFirstRow();     int lastRow = ctx.getLastRow();     int firstColumn = ctx.getFirstColumn();     int lastColumn = ctx.getLastColumn();     int width = ctx.getWidth();     for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) {         IRow currentRow = ctx.getSheet().getRow(rowNumber);         if (currentRow == null) {             continue;         }         for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) {             ICell currentCell = currentRow.getCell(colNumber);             if (currentCell == null && !traverseEmptyCells) {                 continue;             }             handler.onCell(currentCell, new SimpleCellWalkContext(rowNumber, colNumber, ctx));         }     } }
public int getReadIndex() { return _readIndex; }
public int compareTo(Term other) {      if (this.term == null) return (other.term == null) ? 0 : -1;      else if (other.term == null) return 1;      else if (BytesEquals(this.term, other.term)) {          if (this.boost == other.boost) return 0;          else if (this.boost < other.boost) return -1;          else return 1;      }      return this.term.compareTo(other.term);  }
int[] Normalize(String s) {      int len = s.length();      for (int i = 0; i < len; ) {          char c = s.charAt(i);          switch (c) {              case '\u065A': // HAMZA_ABOVE             case '\u0647': // HEH             case '\u064A': // YEH             case '\u064B': // FARSI_YEH             case '\u064C': // KEHEH                 --i;                  len = StemmerUtil.Delete(s, i, len, new int[] { '\u0647', '\u0643', '\u064a' });                  break;              default:                  ++i;                  break;          }      }      return s.toCharArray();  }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public DiagnosticErrorListener exactOnly(boolean exactOnly) { }
public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
return id != -1 && AnyObjectId.FindOffset(id);
return setAllGroups(GroupingSearch.allGroups, allGroups);
public void setMultiValued(String dimName, boolean v) {      DimConfig fieldType;     if (fieldTypes.tryGetValue(dimName, out fieldType)) {          if (fieldType.isMultiValued() != v) {              fieldTypes.remove(dimName);              fieldTypes.put(dimName, new DimConfig(fieldType, v));          }      } else {          fieldTypes.put(dimName, new DimConfig(null, v));      }      synchronized (this) {          // assuming some lock is acquired here, but exact implementation is unclear      }  }
public int[] getCellsVal() { int size = 0; for (char c : keys.toCharArray()) { if (cmd.get(c - '0') != null) { size++; } } int[] cells = new int[size]; int e = 0; for (char c : keys.toCharArray()) { if (cmd.get(c - '0') != null) { cells[e++] = c - '0'; } } return cells; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
return invoke(new DeleteLifecyclePolicyRequest(), new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.instance).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.instance));
