public void serialize(ILittleEndianOutput out1) {out1.writeShort(this.out1);}
public void addAll() { if (0 != tailBlkIdx.src) { directory.src.addAll(tailBlock.src); } for (int srcDirIdx = 0; tailDirIdx.src < srcDirIdx; ++srcDirIdx) { } if (0 == size.src) { return; } BlockList.Util.NGit.addAll(src); }
} ; b ) byte ( ] ++ upto . outerInstance [ currentBlock . outerInstance } ; 0 upto . outerInstance ; ] blockSize . outerInstance [ byte new currentBlock . outerInstance } ; ) upto . outerInstance ( add ( blockEnd . outerInstance ; ) currentBlock . outerInstance ( add ( blocks . outerInstance { ) null != currentBlock . outerInstance ( if { ) blockSize . outerInstance == upto . outerInstance ( if { ) b byte ( writeByte ( void
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
return (fst == null ? 0 : fst.getSizeInBytes()) + ramBytesUsed;
return RawParseUtils.decode(ParseEncoding.getEncoding(enc).raw, buffer, 0 < msgB ? msgB : 0, TagMessage.EMPTY);
List _documents = new ArrayList();  PropertyTable _property_table = new PropertyTable();  HeaderBlock headerBlock = new HeaderBlock();  POIFSFileSystem _root = null;
assert Debug;  int address = offset0 & BYTE_BLOCK_MASK;  ByteBlockPool slice = pool.slice(upto);  assert Debug;  address = (upto >> BYTE_BLOCK_SHIFT) & address;
public void setPath(String path) { this.path = path; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public void SwitchTo(int lexState, ICharStream stream) { this.lexerState = lexState; this.inputStream = stream; }
return getShardIteratorResponse(getShardIteratorRequest(options), getShardIteratorResponseUnmarshaller(), getShardIteratorRequestMarshaller(), new InvokeOptions(options));
public ModifyStrategyRequest() {}
try {      synchronized (lock) {          if (in == null) throw new IOException("");          if (!in.hasRemaining() || in.available() == 0) return;      }  } catch (IOException e) {      throw e;  };
public EscherOptRecord getOptRecord() { return _optRecord; }
public final int read(final byte[] buffer, int offset, int length) {      if (buffer == null) { throw new NullPointerException("buffer"); }      checkOffsetAndCount(buffer.length, offset, length);      if (length == 0) { return 0; }      int copylen = Math.min(length, pos - count);      System.arraycopy(buffer, offset, this.buffer, i, copylen);      i += copylen;      pos -= copylen;      return copylen;  }
); sentenceOp sentenceOp . {  ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
public void print(String str) { if (str != null) { System.out.print(str); } }
public functionName() { throw new NotImplementedException("functionName"); }  public functionName(Object cause) { throw new NotImplementedFunctionException((Throwable) cause); }
return (V) nextEntry.value;
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) {      int available = bufferPosition;      if (available > 0) {          if (len > available) {              System.arraycopy(buffer, bufferStart, b, offset, available);              len -= available;              offset += available;              refill();          } else {              System.arraycopy(buffer, bufferStart, b, offset, len);              bufferPosition -= len;              return;          }      }      if (useBuffer && bufferSize < len) {          len = bufferSize;      }      if (len < bufferLength) {          if (len > 0) {              refill();              if (len > bufferPosition) {                  throw new EndOfStreamException("End of stream reached");              }              System.arraycopy(buffer, bufferStart, b, offset, len);              bufferPosition -= len;          }      } else {          long after = bufferStart + (long) bufferPosition + len;          if (after > Length) {              throw new EndOfStreamException("End of stream reached");          }          readInternal(b, offset, len);      }  }
public TagQueueResponse tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
throw new UnsupportedOperationException();
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {      request = beforeClientExecution(request);      return executeModifyCacheSubnetGroup(request);  }
StringTokenizer st = new StringTokenizer(culture, " ");  String @params = "";  while (st.hasMoreTokens())  {      @params += st.nextToken() + " ";  }  SetParams(@params);
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) {     if (obj == this) return true;     if (!(obj instanceof FacetLabel)) return false;     FacetLabel other = (FacetLabel) obj;     if (other.Length != Length) return false;     for (int i = Length - 1; i >= 0; i--) {         if (Components[i].equals(other.Components[i], StringComparison.Ordinal)) return false;     }     return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance());      options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance());      return invoke(request, options);  }
HSSFPolygon shape = new HSSFPolygon(); shape.setAnchor(new HSSFChildAnchor()); shape.add(anchor); shape.setParent(parent); OnCreate(shape); return shape;
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {      AssociateSigninDelegateGroupsWithAccountRequestMarshaller marshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.INSTANCE;      AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller unmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.INSTANCE;      InvokeOptions options = new InvokeOptions(marshaller, unmarshaller);      return invoke(AssociateSigninDelegateGroupsWithAccountResponse.class, request, options);  }
for (int j = FirstColumn.mbr; j < NumColumns.mbr; j++) {addMultipleBlanks(new BlankRecord(XFIndex.br, Row.mbr, Row.br, j));}
StringBuilder sb = new StringBuilder(); int apos = 0; while (apos < string.length()) { int k = string.indexOf("\\", apos); if (k < 0) { sb.append(string.substring(apos)); break; } sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 1; } return sb.toString();
throw new ReadOnlyBufferException();
Object[][] vv = new Object[_nRows][_nColumns]; for (int r = 0; r < _nRows; r++) { Object[] values2d = vv[r]; for (int c = 0; c < _nColumns; c++) { values2d[c] = rowData[GetValueIndex(c)]; } }
return new InvokeOptions().withRequest(new GetIceServerConfigRequest()).withResponse(new GetIceServerConfigResponse(), Instance.GetIceServerConfigResponseUnmarshaller.getInstance().options(), Instance.GetIceServerConfigRequestMarshaller.getInstance().options());
StringBuilder sb = new StringBuilder(); sb.append(GetType().Name); sb.append(" "); sb.append(GetValueAsString()); return sb.toString();
return ") " + _parentQuery + " " + field.toString() + " ";
public void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
return (int) (GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE + GetNextXBATChainOffset());
if (pow10 > 0) multiplyByPowerOfTen(_multiplicand.tp, _multiplierShift.tp, mulShift); else multiplyByPowerOfTen(_divisor.tp, _divisorShift.tp, mulShift); GetInstance.Abs(Math.TenPower(tp)); int pow10 = TenPower;
StringBuilder builder = new StringBuilder(); int length = path.length(); for (int i = 0; i < length; ++i) { builder.append(path.charAt(i) == '/' ? File.separatorChar : path.charAt(i)); } return builder.toString();
public ECSMetadataServiceCredentialsFetcher withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { }
public void setProgressMonitor(ProgressMonitor pm) { }
if (ptr == 0) { if (First) { First = false; } else { if (Eof) { Reset(); } } }
if (previousIndex >= start) {      return previous;  } else {      throw new NoSuchElementException();  }
public String getNewPrefix() { return newPrefix; }
for (int i = 0; i < mSize; i++) { if (value == mValues[i]) { return i; } } return -1;
public final List<CharsRef> getUniqueStems(List<CharsRef> stems) {if (stems.size() < 2) return stems;CharArraySet dictionary = CharArraySet.EMPTY_SET;dictionary = new CharArraySet(terms, true);List<CharsRef> uniqueStems = new ArrayList<>(stems.size());for (CharsRef s : stems) {if (!dictionary.contains(s)) {uniqueStems.add(s);dictionary.add(s);} }return uniqueStems;}
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
public void setPosition(long position) {      int currentBlockIndex = (int) (position >> blockBits.outerInstance);     int currentBlockUpto = (int) (position & blockMask.outerInstance);     int[] blocks = blocks.outerInstance;     currentBlock = blocks[currentBlockIndex]; }
long s = Math.min(n, Math.max(Available() - offset, 0)); return s; int ptr = (int)s; Skip(s);
public BootstrapActionConfig getBootstrapActionConfig() { return _bootstrapActionConfig; }
public void serialize(ILittleEndianOutput out1) {     if (field_7_padding != null) {         out1.writeByte(Convert.toInt32(field_7_padding, CultureInfo.InvariantCulture));     } else {         out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);     }     out1.writeShort(field_6_author.length);     StringUtil.putCompressedUnicode(field_6_author, out1);     StringUtil.putUnicodeLE(field_6_author, out1); }
return string.lastIndexOf();
); addLastImpl(E object, boolean return);
public void unsetSection(String section, String subsection) { ConfigSnapshot res; do { } while (!compareAndSet(state, (UnsetSection) get(state, section, subsection)));
public String getTagName() { return tagName; }
public void addSubRecord(int index, SubRecord element) { subrecords.insert(index, element); }
public synchronized boolean remove(Object object) { return mutex.remove(object); }
return new DoubleMetaphoneFilter(input, CreateTokenStream());
public long getInCoreLength() { return getLength(); }
public void setValue(boolean newValue) {}
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
throw new IndexOutOfBoundsException("Index: " + i + ", Count: " + count);
public static final String PUT = "PUT";  public enum MethodType {      Method  };  public static final String UriPattern = "  ,   ,   ,   ,   ( : ) ( CreateRepoRequest";
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
throw new ConcurrentModificationException(); else throw new InvalidOperationException(); ++modCount; --list._size; ++expectedModCount; Link<ET> lastLink = null, previous_1, next_1; --pos; if (link == lastLink) ; next_1 = next.previous_1; previous_1 = previous.next_1; previous.lastLink = previous_1; next.lastLink = next_1; if (null != lastLink) if (modCount == expectedModCount) void remove()
public MergeShardsResponse mergeShards(MergeShardsRequest request) {      request = beforeClientExecution(request);      return executeMergeShards(request);  }
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {      request = beforeClientExecution(request);      return executeAllocateHostedConnection(request);  }
return getBeginIndex();
public WeightedTerm[] getTerms(Query query) { return getTerms(query); }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) {      byte byte0 = blocks[++blocksOffset];      int int0 = 0xFF & byte0;      byte byte1 = blocks[++blocksOffset];      int0 |= (int)(0xFF & byte1) << 8;      byte byte2 = blocks[++blocksOffset];      int0 |= (int)(0xFF & byte2) << 16;      byte byte3 = blocks[++blocksOffset];      int0 |= (int)(0xFF & byte3) << 24;      values[++valuesOffset] = int0;  }
String result;  if (s == null || s.isEmpty() || s.equals(".")) {      throw new ArgumentException("Invalid path");  } else if (s.endsWith(DOT_GIT_EXT) && s.length() > DOT_GIT_EXT.length()) {      result = s.substring(0, s.length() - DOT_GIT_EXT.length());  } else {      String[] elements = s.split("\\\\|/");      if (elements.length == 0) {          result = s;      } else if (elements.length == 1) {          if (elements[0].isEmpty() || elements[0].equals(".")) {              result = "";          } else {              result = elements[0];          }      } else {          StringBuilder sb = new StringBuilder();          for (int i = 0; i < elements.length - 1; i++) {              sb.append(elements[i]).append(File.separator);          }          sb.append(elements[elements.length - 1]);          result = sb.toString();      }  }  return getHumanishName(result);
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() { return accessSecret; }
return Invoke(CreateVpnConnectionRequest.createVpnConnectionRequest(), CreateVpnConnectionRequestMarshaller.getInstance().marshall(request), CreateVpnConnectionResponseUnmarshaller.getInstance().marshall(options));
return new InvokeOptions().withRequest(new DescribeVoicesRequest()).withResponse(new DescribeVoicesResponse()).withRequestMarshaller(DescribeVoicesRequestMarshaller.instance).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.instance);
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) {     request = beforeClientExecution(request);     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance());     options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance());     return invoke(options, request); }
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
@Override public String toString() { return "print {" + "b=" + b + ", print=" + print + ", bool=" + bool + "}"; }
public IQueryNode getChild(int index) { return getChildren().get(index); }
); workdirTreeIndex index . {  ) workdirTreeIndex int ( NotIgnoredFilter
public void read(int field_1_formatFlags) throws IOException { this.field_1_formatFlags = readShort(); }
public enum ProtocolType { HTTPS };  public GetThumbnailRequest(String param1, String param2, String param3, String param4, String param5) {}
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {      request = beforeClientExecution(request);      return executeDescribeTransitGatewayVpcAttachments(request);  }
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
result = map.tryGetValue(prefix, new OrdRange());
throw new LexerNoViableAltException(this, getInputStream(), getCurrentToken(), String.Format("No viable alternative at input '" + EscapeWhitespace(Utils::getText, getInputStream().getInterval(startIndex, getInputStream().size() - 1)) + "'", CultureInfo.CurrentCulture));
return peekFirstImpl();
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) {      request = beforeClientExecution(request);      return executeCreateWorkspaces(request);  }
return new NumberFormatIndexRecord((NumberFormatIndexRecord)rec).clone();
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.INSTANCE);      return invoke(request, options);  }
public SparseIntArray(int initialCapacity) { mKeys = new int[idealIntArraySize(initialCapacity)]; mValues = new int[idealIntArraySize(initialCapacity)]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
throw new NotImplementedException();
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.INSTANCE);     options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.INSTANCE);     return invoke(DeleteWorkspaceImageResponse.class, request, options); }
public static String toHex(long value) { return Long.toHexString(value); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.instance).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.instance); UpdateDistributionResponse response = client.invoke(UpdateDistribution.class, request, options).getResponse();
return index == HSSFColor.Index.AUTOMATIC ? HSSFColor.getInstance().getAutomatic() : b != null ? new CustomColor((byte)b[palette]) : (HSSFColor)GetColor.getColor(index);
throw new NotImplementedFunctionException();
public void serialize(ILittleEndianOutput out) {out.writeShort(field_1_number_crn_records);out.writeShort(field_2_sheet_table_index);}
public DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest request) {request = beforeClientExecution(request);return executeDescribeDBEngineVersions(request);}
public FormatRun(short fontIndex, short character) { this.fontIndex = fontIndex; this.character = character; }
result = toBigEndianUtf16Bytes(chars, offset, length);
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public List<IToken> getHiddenTokensToLeft(int tokenIndex) {
public boolean equals(Object obj) {     if (this == obj)          return true;     if (obj == null || getClass() != obj.getClass())          return false;     AutomatonQuery other = (AutomatonQuery) obj;     if (m_compiled != null) {         return m_compiled.equals(other.m_compiled);     } else {         return m_term != null ? m_term.equals(other.m_term) : other.m_term == null;     } }
List<SpanQuery> spanQueries = new ArrayList<>();  for (Map.Entry<BoostKey, Float> wsq : weightBySpanQuery.entrySet()) {      spanQueries.add(MakeSpanClause(wsq.getKey().getSpanQuery()));  }  return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand();
} ; return ; ) return out , ( tryGetValue . byName ; FieldInfo { ) fieldName String ( FieldInfo FieldInfo
return Invoke(DescribeEventSourceRequestMarshaller.Instance, DescribeEventSourceResponseUnmarshaller.Instance, options, DescribeEventSourceRequest.class, DescribeEventSourceResponse.class);
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.INSTANCE);      return invoke(options, request);  }
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance());     options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());     return invoke(request, options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance());      options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance());      return invoke(request, options);  }
return invoke(new ModifyDBProxyRequestMarshaller(), new ModifyDBProxyResponseUnmarshaller(), request);
} ; ++count ; posLength ] [ posLengths ; endOffset ] [ endOffsets ; ) , , ( copyChars . ] [ outputs } ; ) ( charsRef new ] [ outputs { ) null == ] [ outputs ( if } ; next posLengths ; ) , , , , ( copyOf . arraycopy ; ] ) NUM_BYTES_INT32 . ramBytesAllocated , count + 1 ( oversize . arraySize , int new = next ] [ int { ) length . posLengths == count ( if } ; next endOffsets ; ) , , , , ( copyOf . arraycopy ; ] ) NUM_BYTES_INT32 . ramBytesAllocated , count + 1 ( oversize . arraySize , int new = next ] [ int { ) length . endOffsets == count ( if } ; next outputs ; ) , , , , ( copyOf . arraycopy ; ] ) NUM_BYTES_OBJECT_REF . ramBytesAllocated , count + 1 ( oversize . arraySize , charsRef new = next ] [ charsRef { ) length . outputs == count ( if { ) posLength int , endOffset int , len int , offset int , output ] [ char ( add void
public enum ProtocolType { HTTPS }; public class FetchLibrariesRequest { }
public boolean exists(Object[] objects) { return objects != null && objects.length > 0; }
public class FilterOutputStream extends OutputStream { }
public static final MethodType PUT = MethodType.methodType(void.class, String.class, UriPattern.class).withRequestEventType(ScaleClusterRequest.class);
public IDataValidationConstraint createTimeConstraint(String formula1, String formula2, int operatorType) { return new CreateTimeConstraint(formula1, formula2, operatorType); }
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {      request = beforeClientExecution(request);      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance());      options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());      return invoke(request, options).getResponse().getBody();  }
public SetSharedFormula(short field_5_options, boolean flag) { this.field_5_options = field_5_options; this.flag = flag; }
public boolean isReuseObjects() { return reuseObjects; }
t = new ErrorNodeImpl((IToken)badToken); Parent.addChild((IErrorNode)t); return t;
public LatvianStemFilterFactory(Map<String,String> args) {super(args);if (!args.isEmpty()) {throw new IllegalArgumentException("Unknown parameters: " + args);}}
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
return NewInstance.loader(ForName(TokenFilterFactory.class.getName()), args, (IDictionary<String, String>) null);
public enum ProtocolType { HTTPS } ;  public class AddAlbumPhotosRequest {
return invoke(GetThreatIntelSetRequest.class, request, GetThreatIntelSetResponse.class, ResponseUnmarshaller.getInstance(), RequestMarshaller.getInstance(), options);
return new AndTreeFilter((TreeFilter) a.clone(), (TreeFilter) b.clone());
@Override public boolean equals(Object o) {
protected boolean hasArray() { return hasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
void unwriteProtectWorkbook();
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) { }
return execute(RequestSpotInstancesRequest.class, invokeOptions(RequestSpotInstancesRequestMarshaller.INSTANCE, RequestSpotInstancesResponseUnmarshaller.INSTANCE, request));
public ObjectData getObjectData() { return findObjectRecord(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {      request = beforeClientExecution(request);      return executeGetContactAttributes(request);  }
return (GetKey + " " + GetValue).toString();
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance());return invoke(request, options);}
return invoke(GetContactMethodsRequest.class, GetContactMethodsResponse.class, request, options, GetContactMethodsRequestMarshaller.getInstance(), GetContactMethodsResponseUnmarshaller.getInstance());
public short getFunctionByNameInternal(String name) {      FunctionMetadata fd = getInstance().lookupIndexByName(name);      return fd == null ? (short) -1 : fd.getIndex();  }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {      request = beforeClientExecution(request);      return executeDescribeAnomalyDetectors(request);  }
public InsertId insert(String message, ObjectId changeId) { return (InsertId) null; }
if (typeHint == OBJ_ANY) { if (sz > 0) { db = GetObjectSize(objectId); } else { throw new MissingObjectException(""); } } else { throw new MissingObjectException(objectId); }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
return new PutLifecycleEventHookExecutionStatusResponse(options, new PutLifecycleEventHookExecutionStatusRequest(), PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance().marshall(request), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance().marshall((PutLifecycleEventHookExecutionStatusResponse) response));
public static NumberPtg readDouble(ILittleEndianInput in1) { return new NumberPtg(in1.readDouble()); }
return new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequest.getMarshaller()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponse.getUnmarshaller()).withRequest(GetFieldLevelEncryptionConfigRequest.getRequest());
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
return new PortugueseStemFilter(input);
};] ENCODED_SIZE [ byte new reserved {(FtCblsSubRecord)
public boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) {      request = beforeClientExecution(request);      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance());      options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance());      return invoke(options, request);  }
return precedence + " " + ToString();
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this._policyName = policyName; this._loadBalancerName = loadBalancerName; }
protected Options _options = new Options();
public UnbufferedCharStream(int bufferSize) { this.bufferSize = bufferSize; this.data = new char[bufferSize]; }
return instance.invoke(new GetOperationsRequest(), GetOperationsRequestMarshaller.getInstance().marshall(request), GetOperationsResponseUnmarshaller.getInstance(), options);
System.arraycopy(EncodeInt32.NB, 0, b, 4 + o, 4); System.arraycopy(EncodeInt32.NB, 0, b, 8 + o, 4); System.arraycopy(EncodeInt32.NB, 0, b, 12 + o, 4); System.arraycopy(EncodeInt32.NB, 0, b, 16 + o, 4);
public WindowOneRecord in1(RecordInputStream in) {      field_1_h_hold = in.readShort();      field_2_v_hold = in.readShort();      field_3_width = in.readShort();      field_4_height = in.readShort();      field_5_options = in.readShort();      field_6_active_sheet = in.readShort();      field_7_first_visible_tab = in.readShort();      field_8_num_selected_tabs = in.readShort();      field_9_tab_width_ratio = in.readShort();      return this;  }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
} } } } } finally { close.fos(); } try { close.channel(); } finally { } try { truncate.channel(); } finally { } try { dump(); } if (isOpen()) { throw new IOException("close void"); }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
return GetPath();
double r = Double.NaN;  if (v != null && v.length >= 1) {      double m = 0;      double s = 0;      int n = v.length;      for (int i = 0; i < n; i++) {          double devsq = (v[i] - m) * (v[i] - m);          s += devsq;      }      r = s;  }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }

public void traverse(ICellHandler handler) {     IRow currentRow = null;     ICell currentCell = null;     for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) {         currentRow = sheet.getRow(rowNumber);         if (currentRow == null) continue;         for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) {             currentCell = currentRow.getCell(colNumber);             if (currentCell == null) continue;             if (traverseEmptyCells && currentCell.getCellType() == CellType.BLANK) continue;             handler.onCell(new SimpleCellWalkContext(rowNumber, colNumber, currentCell, width * (colNumber - firstColumn) + 1));         }     } }
public int GetReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) {      if (this.term.bytesEquals(other.term)) {          if (this.boost == other.boost) {              return 0;          } else {              return this.boost.compareTo(other.boost);          }      } else {          return this.term.compareTo(other.term);      }  }
for (int i = 0; i < len; ++i) { switch (s[i]) { case 'ھ': case 'ﻫ': case 'ﻩ': case '﷽': case 'ﻫ': case 'ﻪ': case 'ﻫ': case 'ﻩ': case '﷽': case 'ﻫ': case 'ﻪ': case 'ﻫ': case 'ﻩ': case '﷽': case 'ﻫ': case 'ﻪ': case 'ﻫ': case 'ﻩ': case '﷽': case 'ﻫ': case 'ﻪ': case 'ﻫ': case 'ﻩ': case '﷽': } }
public void serialize(ILittleEndianOutput out) { out.writeShort(field); }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; } ; KeyType keyType; String attributeName;
return this.invoke(GetAssignmentRequest.class, GetAssignmentResponse.class, request, options, GetAssignmentRequestMarshaller.getInstance(), GetAssignmentResponseUnmarshaller.getInstance());
public int findOffset(long id) { return hasObject(id) ? 1 : -1; }
