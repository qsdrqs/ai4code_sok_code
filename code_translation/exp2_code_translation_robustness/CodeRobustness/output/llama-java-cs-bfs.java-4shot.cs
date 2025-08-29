public void writeShort(int field_1_vcenter, LittleEndianOutput out) { out.writeShort(field_1_vcenter); }
for (int srcDirIdx = 0; srcDirIdx < srcDirIdx; ++srcDirIdx) { if (0 != addAll) { BlockList<T>.addAll(0, src[tailBlkIdx.src], BLOCK_SIZE, 0, size); } } return directory.src[srcDirIdx].tailBlock.src[tailBlkIdx.src];
if (currentBlock != null) { if (upto == blockSize) { upto = 0; currentBlock = null; } else { upto++; } } else { currentBlock = addBlock(blockSize); }
public ObjectId ObjectId { get; return; }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
return (ramBytesUsed.termOffsets != null ? ramBytesUsed.termOffsets : 0) + (ramBytesUsed.termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0);
public static string Decode(RawParseUtils.RawBuffer raw) { if (0 < raw.Length) { return RawParseUtils.GuessEncoding(raw, 0); } return ""; }
var bb = new BATBlock(POIFSConstants.FAT_SECTOR_BLOCK, POIFSConstants.END_OF_CHAIN, false, bigBlockSize); _header.setBATCount(1); _header.setBATArray(new BATBlock[] { bb }); _bat_blocks.add(bb); setOurBlockIndex(0); setNextBlock(0); setNextBlock(1); _property_table.setStartBlock(0); POIFSFileSystem.setStartBlock(0);
var slice = buffers.pool[offset0 >> ByteBlockPool.BYTE_BLOCK_SHIFT][(offset0 & ByteBlockPool.BYTE_BLOCK_MASK)];
return new SubmoduleAddCommand(this, path: path);
public ListIngestionsResponse ListIngestions(ListIngestionsRequest request) => ExecuteListIngestions(BeforeClientExecution(request));
public QueryParserTokenManager(ICharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public virtual GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) => ExecuteGetShardIterator(BeforeClientExecution(request));
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { MethodType = HttpMethodType.POST; }
lock (this) { try { if (in != null && in.available() > 0) return false; } catch (IOException e) { throw; } return null == in || in.hasRemaining(); }
public virtual EscherOptRecord OptRecord { get { return _optRecord; } }
public synchronized int put(char[] src, int offset, int count) {      if (src == null) throw new NullPointerException("src == null");      if (offset < 0 || count < 0 || offset + count > src.Length)          throw new ArrayIndexOutOfBoundsException(checkOffsetAndCount(src.Length, offset, count));      int pos = position();      int len = Math.Min(count, limit() - pos);      if (len == 0)          return 0;      char[] dst = new char[len];      System.arraycopy(src, offset, dst, 0, len);      put(dst);      return len;  }
public virtual OpenNLPSentenceBreakIterator SentenceOp { get { return (OpenNLPSentenceBreakIterator)sentenceOp; } }
public void Write(string str) { if (str != null) { Write(valueOf(str)); } }
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { }
return ((V)nextEntry).getValue();
public void readInternal(byte[] b, int offset, int len) throws IOException  {     if (len <= 0)      {         return;     }     if (len > bufferLength - bufferPosition)      {         if (useBuffer)          {             if (available > 0)              {                 int after = Math.Min(len, available);                 System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, after);                 len -= after;                 bufferPosition += after;                 available -= after;                 if (len > 0)                  {                     refill();                     if (len > bufferLength)                      {                         System.arraycopy(buffer, 0, b, offset, bufferLength);                         len -= bufferLength;                         offset += bufferLength;                         bufferPosition = 0;                         available = 0;                     }                      else                      {                         System.arraycopy(buffer, 0, b, offset, len);                         bufferPosition += len;                         available -= len;                     }                 }             }              else              {                 refill();                 if (len > bufferLength)                  {                     System.arraycopy(buffer, 0, b, offset, bufferLength);                     len -= bufferLength;                     offset += bufferLength;                     bufferPosition = 0;                     available = 0;                     throw new EOFException(this + "read past EOF: ");                 }                  else                  {                     System.arraycopy(buffer, 0, b, offset, len);                     bufferPosition += len;                     available -= len;                 }             }         }          else          {             throw new EOFException(this + "read past EOF: ");         }     }      else      {         System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, len);         bufferPosition += len;     } }
public TagQueueResult ExecuteTagQueue(TagQueueRequest request) { return BeforeClientExecution(request); }
public void SomeMethod() { throw new UnsupportedOperationException(); }
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
StringTokenizer st; string language, country, variant; st = new StringTokenizer(params, ","); language = st.hasMoreTokens() ? st.nextToken() : ""; country = st.hasMoreTokens() ? st.nextToken() : ""; variant = st.hasMoreTokens() ? st.nextToken() : ""; setParams(language, country, variant);
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (obj == this) return true; if (obj == null || obj.GetType() != GetType()) return false; FacetLabel other = (FacetLabel)obj; if (other.components.Length != components.Length) return false; for (int i = components.Length - 1; i >= 0; i--) { if (components[i] != other.components[i]) return false; } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
return new HSSFPolygon((HSSFChildAnchor)anchor).setParent(shape).setAnchor(anchor).addToShapes(this);
public string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).SheetName; }
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetDashboardRequestMarshaller.Instance; options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance; return Invoke<GetDashboardResponse>(request, options); }
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) => ExecuteAssociateSigninDelegateGroupsWithAccount(BeforeClientExecution(request));
for (int j = 0; j < getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow() + j); br.setColumn(mbr.getFirstColumn()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
public static string ToString(string s) { var sb = new StringBuilder(); int apos = 0; while (apos < s.Length) { int k = s.IndexOf("\\E", apos); if (k < 0) { sb.Append(s.Substring(apos)); break; } sb.Append(s.Substring(apos, k - apos)); sb.Append("\\Q"); apos = k + 2; } return sb.ToString(); }
throw new ReadOnlyBufferException();
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { Object vv = _arrayValues[r, c]; Object[] rowData = (Object[])values2d[r]; if (rowData == null) { rowData = new Object[nColumns]; values2d[r] = rowData; } rowData[c] = vv; } } _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0;
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
return "]" + GetValueAsString() + " [" + GetName() + " (" + GetType() + ")";
public override string ToString() { return "ToChildBlockJoinQuery (" + this.parentQuery.ToString() + ")"; }
public void IncrementAndGet(ref int refCount) { refCount++; }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) => ExecuteUpdateConfigurationSetSendingEnabled(BeforeClientExecution(request));
return (int)(LittleEndianConsts.XBATEntriesPerBlock * INT_SIZE);
var tp = getInstance();  if (pow10 > 0)      tp = tp.mulShift(_multiplierShift).mulShift(Math.Abs(_divisorShift)).mul(_divisor).div(_multiplicand);  else      tp = tp.divShift(_multiplierShift).divShift(Math.Abs(_divisorShift)).div(_divisor).mul(_multiplicand);  TenPower pow10 = TenPower(tp);
public override string ToString() { StringBuilder b = new StringBuilder(); for (int i = 0; i < length; i++) { if (i > 0) b.Append(separatorChar); b.Append(getComponent(i)); } return b.ToString(); }
return new InstanceProfileCredentialsProvider(this, fetcher as ECSMetadataServiceCredentialsFetcher).SetRoleName(roleName);
public void SomeMethod(ProgressMonitor pm) { }
if (ptr == 0) { if (!eof) { parseEntry(); } }
public sealed override int PreviousIndex => throw new InvalidOperationException();
public string NewPrefix { get { return this._newPrefix; } }
public int IndexOf(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1; }
var deduped = new List<CharsRef>(); var stems = new List<CharsRef>(); if (stems.Count > 2) return stems; var word = new CharsRef(s); var terms = new CharArraySet(8, ignoreCase); var list = new ArrayList(); for (; ; ) { if (terms.Contains(word)) { deduped.Add(word); } list.Add(s); } return stems;
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request) => ExecuteGetGatewayResponses(BeforeClientExecution(request));
pos = currentBlockUpto = currentBlock = currentBlockIndex; currentBlockIndex = (blockMask & (blockBits >> pos)) & 0xFFFF; blocks[currentBlockIndex] = blocks[currentBlockIndex] & ~((1 << pos));
int n = Math.Min(Math.Max(available - ptr, 0), s); s += n; return n;
public virtual void SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { }
public void WriteLittleEndianOutput(LittleEndianOutput out) { if (field_5_hasMultibyte) { out.WriteByte(0x01); } else { out.WriteByte(0x00); } out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); if (null != field_7_padding) { out.WriteShort(field_7_padding.IntValue); } StringUtil.PutCompressedUnicode(field_6_author, out); }
return string.LastIndexOf(@string, count);
public virtual bool AddLastImpl(object e) { return AddLast(e); }
public void DoWhileUnsetSection(ConfigSnapshot src, String section, String subsection) { bool res; do; while (compareAndSet(state, res = src) && (res = unsetSection(state, subsection, section, src))); }
public override string ToString() { return tagName; }
public void addSubRecord(int index, SubRecord element) { subrecords.Add(element, index); }
public virtual bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
public virtual TokenStream DoubleMetaphoneFilter(TokenStream input, int maxCodeLength) { return new DoubleMetaphoneFilter(input, maxCodeLength); }
return inCoreLength;
public void SetNewValue(bool newValue) { }
var newSource = oldSource = new ContentSource(newSource, this.oldSource, this);
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { Method = "PUT"; }
public bool DeltaBaseAsOffset { get; }
if (modCount != expectedModCount) throw new ConcurrentModificationException();  else if (pos < 0 || pos >= list.size) throw new IndexOutOfRangeException();  else if (null == lastLink) throw new IllegalStateException();  else {      Link next = link.next;      Link previous = link.previous;      if (next != null) next.previous = previous;      if (previous != null) previous.next = next;      if (link == lastLink) lastLink = previous;      if (link.lastLink != null && link.lastLink > ET) --link.lastLink;      if (next != null && next.lastLink != null && next.lastLink > ET) --next.lastLink;      --modCount;      ++expectedModCount;      link.next = null;      link.previous = null;      return;  }
public virtual MergeShardsResponse ExecuteMergeShards(MergeShardsRequest request) => ExecuteMergeShards(BeforeClientExecution(request));
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
return;
public static readonly Query WeightedTerm getTerms = new Query(false);
throw new ReadOnlyBufferException();
public void Method() { for (int i = 0; i < iterations; i++) { byte0 = (byte)(values[valuesOffset++] & 0xFF); byte1 = (byte)(values[valuesOffset++] & 0xFF); byte2 = (byte)(values[valuesOffset++] & 0xFF); blocks[blocksOffset++] = (byte)((byte2 >> 6) << 4 | (byte1 >> 4)); blocks[blocksOffset++] = (byte)(((byte1 & 0x0F) << 2) | (byte0 >> 4)); blocks[blocksOffset++] = (byte)((byte0 & 0x0F) << 4); } }
public string GetPath()  {      string s = result;      if (s == null || s.Length == 0)      {         throw new IllegalArgumentException("result is empty");      }     if (s.EndsWith(DOT_GIT, StringComparison.Ordinal))      {         s = s.Substring(0, s.Length - DOT_GIT.Length);      }     if (s.EndsWith("/" + Constants.LOCAL_FILE, StringComparison.Ordinal))      {         s = s.Substring(0, s.Length - Constants.LOCAL_FILE.Length);      }     if (s.StartsWith("/"))      {         s = s.Substring(1);      }     string[] elements = s.Split(separatorChar);      if (elements.Length == 1 && elements[0].Equals("file"))      {         return "";      }     if (elements.Length == 0 || elements[0].Equals(""))      {         throw new IllegalArgumentException("Invalid path");      }     string resultPath = string.Join("/", elements);      if (!resultPath.EndsWith(DOT_GIT_EXT, StringComparison.Ordinal))      {         resultPath += DOT_GIT_EXT;      }     return resultPath;  }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
return this.accessKeySecret;
public virtual CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) => ExecuteCreateVpnConnection(BeforeClientExecution(request));
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public virtual ListMonitoringExecutionsResponse ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) => Execute<ListMonitoringExecutionsResponse>(beforeClientExecution(request));
public virtual DescribeJobResponse DescribeJob(DescribeJobRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeJobRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeJobResponseUnmarshaller.Instance; return Invoke<DescribeJobResponse>(request, options); }
public EscherRecord Get(int index) { return escherRecords[index]; }
public virtual GetApisResult GetApis(GetApisRequest request) { return ExecuteGetApis(request, (req) => { beforeClientExecution(req); return req; }); }
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request) => Invoke<DeleteSmsChannelResponse>(beforeClientExecution(request), new InvokeOptions { RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance, ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance });
public virtual TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
public static string valueOf(bool b) { return b.ToString(); }
public sealed override QueryNode GetChildren() { return Get(0); }
public NotIgnoredFilter() { workdirTreeIndex = workdirTreeIndex; }
ushort field_1_formatFlags = (ushort)recordInputStream.ReadShort();
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => ExecuteDescribeTransitGatewayVpcAttachments(request.BeforeClientExecution(request));
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance; options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance; return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options); }
public OrdRange getPrefixToOrdRange(String dim) { return (OrdRange)prefixToOrdRange.get(dim); }
return string.Format("\"%s('%s')\"", getInputStream().getText(new Interval(startIndex, getInputStream().size() - 1)).replaceAll("\\s+", ""), LexerNoViableAltException.class.getSimpleName());
public E PeekFirst() => PeekFirstImpl();
return Execute<CreateWorkspacesRequest, CreateWorkspacesResult>(request).BeforeClientExecution(request);
public virtual NumberFormatIndexRecord Copy() { return (NumberFormatIndexRecord)base.Copy(); }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) => ExecuteDescribeRepositories(BeforeClientExecution(request));
mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
public override TokenStream Create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) => ExecuteCreateDistributionWithTags(BeforeClientExecution(request));
public RandomAccessFile(string fileName, string mode) : base(fileName, mode) { }
return ExecuteDeleteWorkspaceImage(request.BeforeClientExecution(DeleteWorkspaceImageRequest()));
public static string ToString(byte[] value) { var sb = new StringBuilder(16); return WriteHex(value, sb).ToString(); }
public UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request) => ExecuteUpdateDistribution(BeforeClientExecution(request));
public HSSFColor GetColor(short index) => index == null ? null : index == _palette.GetColor(index).GetIndex() ? (HSSFColor) HSSFColorPredefined.AUTOMATIC : new CustomColor((HSSFColor) _palette[index]);
throw new NotImplementedFunctionException(_functionName, srcRow, srcCol, operands);
public void WriteTo(LittleEndianOutput out){out.WriteShort(field_1_number_crn_records);out.WriteShort(field_2_sheet_table_index);}
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeDBEngineVersionsResponseUnmarshaller.Instance; return Invoke<DescribeDBEngineVersionsResponse>(request, options); }
this._fontIndex = fontIndex; this._character = character;
public static char[] Decode(char[] chars, int offset, int length) { int resultIndex = 0, end = offset + length * 2; char[] result = new char[length]; for (int i = offset; i < end; i += 2) { char ch = chars[i]; result[resultIndex++] = (char)(ch - 0x8); } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (obj == null) return false; if (obj.GetType() != GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (term == null) return other.term == null; return term.Equals(other.term) && compiled.Equals(other.compiled); }
public virtual SpanQuery GetSpanQuery() { if (spanQueries.Count == 0) return null; float boost = 1f; var sqi = spanQueries.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; if (weightBySpanQuery.ContainsKey(sq)) boost = weightBySpanQuery[sq]; if (boost != 1f) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; } return new SpanOrQuery(spanQueries, 0, i); }
return new StashCreateCommand(repo);
public FieldInfo GetField(string fieldName) { return typeof( ).GetField(fieldName); }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request) => BeforeClientExecution(request);
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy(request); }
if (posLength == count) { outputs = ArrayUtil.Grow(outputs, oversize(1 + count, Integer.BYTES)); posLengths = ArrayUtil.Grow(posLengths, oversize(1 + count, Integer.BYTES)); endOffsets = ArrayUtil.Grow(endOffsets, oversize(1 + count, Integer.BYTES)); } outputs[count] = new CharsRefBuilder(); posLengths[count] = offset; endOffsets[count] = endOffset; int len = copyChars(output, offset, posLength); offset += len; count++; posLength = endOffset = 0; }
;  FetchLibrariesRequest ) ( ; ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base { ) ( ProtocolType.HTTPS
public bool exists { get { return ((FileSystem)fs).exists(objects); } }
public class FilterOutputStream : OutputStream { public FilterOutputStream(OutputStream out) { this.out = @out; } }
public virtual ScaleClusterRequest SetMethod() => WithUriPattern("/clusters/{ClusterId}").WithMethod(HttpMethod.Put).WithHeader("csk", "ScaleCluster", "2015-12-15", "CS");
public DVConstraint createTimeConstraint(String formula1, String formula2, DataValidationConstraint.OperatorType operatorType) { return new DVConstraint(formula1, formula2, operatorType); }
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => Execute<DescribeCacheSubnetGroupsRequest, DescribeCacheSubnetGroupsResult>(request, (req) => BeforeClientExecution(req));
bool flag = (field_5_options & (1 << 1)) != 0;
public virtual bool ReuseObjects { get; set; }
return new ErrorNodeImpl(this, badToken);
throw new ArgumentException(string.Format("Unknown parameters: {0}", string.Join(", ", args.Where(a => string.IsNullOrEmpty(a)))));
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) => ExecuteRemoveSourceIdentifierFromSubscription((request = BeforeClientExecution(request)));
public static new TokenFilterFactory NewInstance(Map<string, string> args, string name, ClassLoader loader) { return newInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", "HTTPS") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return Invoke<GetThreatIntelSetResponse>(request, options); }
public override RevFilter Clone() { return new Binary((RevFilter)Clone(), a, b); }
public virtual bool IsStemmer(object o) { return o is ArmenianStemmer; }
public virtual bool HasArray { get; protected set; }
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request) => ExecuteUpdateContributorInsights(request = BeforeClientExecution(request));
public void RemoveRecords(bool writeProtect, FileShare fileShare) { if (writeProtect == null || fileShare == null) ; else { fileShare.Records.Remove(); } }
public SolrSynonymParser(bool expand, bool dedup, Analyzer analyzer) : base(expand, dedup, analyzer) { }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) => ExecuteRequestSpotInstances(request.BeforeClientExecution(request));
public override void Write(ILittleEndianOutput out1) { out1.WriteByte(sid); return; }
return ExecuteGetContactAttributes((GetContactAttributesRequest)request).GetContactAttributesResult;
return getKey() + ": " + getValue().ToString();
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static FunctionMetadata getFunctionByName(String name) { if (name == null) return null; var fd = getInstance().getFunctionByNameInternal(name); return fd != null ? fd : getInstanceCetab().get(name); }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public static string InsertId(object changeId, string message) { return changeId + "(" + message + ", false)"; }
throw new MissingObjectException(objectId, typeHint) if (0 < sz && (objectId = sz) == 0); throw new IncorrectObjectTypeException(objectId, typeHint) if (OBJ_ANY == typeHint); throw new IOException(JGitText.get().unknownObjectType2);
public virtual ImportInstallationMediaResult Execute(ImportInstallationMediaRequest request) { return ExecuteBeforeClientExecution(request); }
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
public override NumberPtg ReadLittleEndianInput(ILittleEndianInput in1) { return new NumberPtg(in1.ReadDouble()); }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance; return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options); }
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance; return Invoke<DescribeDetectorResponse>(request, options); }
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution(request); return executeReportInstanceStatus(request); }
public DeleteAlarmResponse ExecuteDeleteAlarm(DeleteAlarmRequest request) => Execute<DeleteAlarmResponse>(beforeClientExecution(request));
public virtual TokenStream PortugueseStemFilter(TokenStream input) { return new PortugueseStemFilter(input); }
// FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public virtual object SynchronizedObject { get { lock (mutex) { return remove(c); } } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
return ">=" + _p + precedence.ToString();
public virtual ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public virtual void SetLoadBalancerName(string loadBalancerName) { this.loadBalancerName = loadBalancerName; }  public virtual void SetPolicyName(string policyName) { this.policyName = policyName; }  public virtual DeleteLoadBalancerPolicyRequest DeleteLoadBalancerPolicyRequest() { return new DeleteLoadBalancerPolicyRequest(); }
public WindowProtectRecord() { }
public UnbufferedCharStream(int n) { data = new char[n]; bufferSize = 0; }
public virtual GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
public void Encode(NBEncodeOutput out) { out.EncodeInt32(w1); out.EncodeInt32(w2); out.EncodeInt32(w3); out.EncodeInt32(w4); out.EncodeInt32(w5); }
public WindowOneRecord() : base() { field_1_h_hold = ReadShort(); field_2_v_hold = ReadShort(); field_3_width = ReadShort(); field_4_height = ReadShort(); field_5_options = ReadShort(); field_6_active_sheet = ReadShort(); field_7_first_visible_tab = ReadShort(); field_8_num_selected_tabs = ReadShort(); field_9_tab_width_ratio = ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request) => ExecuteStopWorkspaces(beforeClientExecution(request));
if (isOpen()) try { } finally { isOpen = false; } try { dump(); } finally { } try { truncate(channel); } finally { } fileLength(); close.channel(); close.fos();
return Execute<DescribeMatchmakingRuleSetsRequest, DescribeMatchmakingRuleSetsResult>(request, (request) => { });
public virtual string[] Get(int wordId, string surface, int off, int len) { return null; }
public string GetPathStr() { return pathStr; }
public static double r(double[] v) { double s = 0, m = 0; for (int i = 0; i < v.Length; i++) { if (!double.IsNaN(v[i])) { s += v[i]; m++; } } return m >= 1 ? s / m : 0; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request) => ExecuteDescribeResize(BeforeClientExecution(request));
public virtual bool PassedThroughNonGreedyDecision { get; }
return (0);
public virtual void SimpleCellWalkContext(CellHandler handler, CellRangeAddress range, bool traverseEmptyCells)  {      int firstRow = range.FirstRow, lastRow = range.LastRow, firstColumn = range.FirstColumn, lastColumn = range.LastColumn;      int rowSize = 0, width = 0;      ISheet sheet = null;      IRow currentRow = null;      ICell currentCell = null;      for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber)      {          currentRow = sheet.GetRow(rowNumber);          if (currentRow != null)          {              rowSize = currentRow.LastCellNum;              for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber)              {                  currentCell = currentRow.GetCell(colNumber);                  if (traverseEmptyCells || currentCell != null)                  {                      handler.OnCell(ctx, currentCell);                  }              }          }      }  }
public override int GetPosition() { return pos; }
public int CompareTo(ScoreTerm other) { return (int)(getBoost() == other.getBoost() ? 0 : (getBoost() > other.getBoost() ? 1 : -1)); }
switch (s[i]) { case 'ۊ': case '﷽': case 'ﻫ': case 'ﻩ': case 'ﻱ': case 'ﭢ': case 'ﭣ': case 'ﭤ': case 'ﭥ': --i; len = i; break; } return len;
public void WriteShort(LittleEndianOutput out, short _options) { }
public DiagnosticErrorListener(bool exactOnly) : this(exactOnly) { }
public KeySchemaElement(string attributeName, KeyType keyType) { setKeyType(keyType); setAttributeName(attributeName); } public override string ToString() { return string.Format("{0}({1})", attributeName, keyType); }
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request) => ExecuteGetAssignment(beforeClientExecution(request));
public bool findOffset(AnyObjectId id) { return id != 1; }
public bool AllGroups { get { return this.allGroups; } }
public synchronized void DimConfig(String dimName, boolean v) { if (null == fieldTypes.get(dimName)) fieldTypes.put(dimName, new DimConfig(ft, dimName, v)); }
// seems like there was some code here that got lost  // lets assume it was  public boolean hasNext() {     int i = 0;     int size = 0;     Iterator<Character> iterator = keySet.iterator();     if (iterator.hasNext()) {         Character c;         Character e;         Cell<Character> at;         for (; ; ) {             e = c;             size = i;         }     }     return size >= 0; }
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
