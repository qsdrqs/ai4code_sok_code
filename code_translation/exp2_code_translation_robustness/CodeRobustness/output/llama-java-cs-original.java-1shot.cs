public void Serialize(ILittleEndianOutput out) { out.WriteShort(field_1_vcenter); }
public void addAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void WriteByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId GetObjectId() { return objectId; }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;     options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;     return Invoke<DeleteDomainEntryResponse>(request, options); }
public long ramBytesUsed() { return (termOffsets != null ? termOffsets.ramBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.ramBytesUsed() : 0); }
public string getFullMessage() => RawParseUtils.decode(guessEncoding(), buffer, RawParseUtils.tagMessage(buffer, 0), buffer.Length);
public POIFSFileSystem() : this(true) { _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void Init(int address) { slice = pool.Buffers[address >> ByteBlockPool.ByteBlockShift]; Debug.Assert(slice != null); upto = address & ByteBlockPool.ByteBlockMask; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SetPath(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request)
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready() { lock (lock) { return in != null && (bytes.HasRemaining || in.Available > 0); } }
public EscherOptRecord OptRecord { get { return _optRecord; } }
public int Read(byte[] buffer, int offset, int length) {      if (buffer == null) throw new ArgumentNullException(nameof(buffer));      if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentException("Invalid offset or length");      if (length == 0) return 0;      int copylen = Math.Min(count - pos, length);      for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte)this.buffer[pos + i];      pos += copylen;      return copylen;  }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) => Write(str != null ? str : ((object)null).ToString());
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public virtual V Next() => ((System.Collections.Generic.KeyValuePair<K, V>)base.NextEntry()).Value;
public virtual void ReadBytes(byte[] b, int offset, int len, bool useBuffer) {      int available = bufferLength - bufferPosition;      if(len <= available){          if(len > 0) Array.Copy(buffer, bufferPosition, b, offset, len);          bufferPosition += len;      }      else {          if(available > 0){              Array.Copy(buffer, bufferPosition, b, offset, available);              offset += available;              len -= available;              bufferPosition += available;          }          if (useBuffer && len < bufferSize){              Refill();              if(bufferLength < len){                  Array.Copy(buffer, 0, b, offset, bufferLength);                  throw new EndOfStreamException("read past EOF: " + this);              }              else {                  Array.Copy(buffer, 0, b, offset, len);                  bufferPosition = len;              }          }          else {              long after = bufferStart + bufferPosition + len;              if(after > Length())                  throw new EndOfStreamException("read past EOF: " + this);              ReadInternal(b, offset, len);              bufferStart = after;              bufferPosition = 0;              bufferLength = 0;          }      }  }
public virtual TagQueueResponse TagQueue(TagQueueRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = TagQueueRequestMarshaller.Instance; options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance; return Invoke<TagQueueResponse>(request, options); }
public virtual void Remove() { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] parts = @params.Split(','); language = parts.Length > 0 ? parts[0] : ""; country = parts.Length > 1 ? parts[1] : ""; variant = parts.Length > 2 ? parts[2] : ""; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public bool Equals(object obj) { if (!(obj is FacetLabel other)) return false; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) if (!components[i].Equals(other.components[i])) return false; return true; }
public virtual GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { var shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetname();
public GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public virtual AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request)  {     request = BeforeClientExecution(request);     return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string Quote(string str) => new StringBuilder().Append("\\Q").Append(str.Replace("\\E", "\\\\E\\Q")).Append("\\E").ToString();
public ByteBuffer PutInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public virtual GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public virtual string ToString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void IncRef() => Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int GetNextXBATChainOffset() => GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
public void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public override string ToString() => string.Join(File.separatorChar.ToString(), Enumerable.Range(0, length()).Select(i => getComponent(i)));
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public virtual void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public virtual E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) { var stems = Stem(word, length); if (stems.Count < 2) return stems; var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request)
public void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { BootstrapActionConfig = bootstrapActionConfig; }
public void Serialize(ILittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) StringUtil.PutUnicodeLE(field_6_author, out); else StringUtil.PutCompressedUnicode(field_6_author, out); if (field_7_padding.HasValue) out.WriteByte((byte)field_7_padding); }
public int LastIndexOf(string value) { return LastIndexOf(value, count); }
public virtual bool Add(E object) => AddLastImpl(object);
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public override string getTagName() { return tagName; }
public void addSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, MaxCodeLength, Inject);
public long Length() { return InCoreLength(); }
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) : base() { this.oldSource = oldSource; this.newSource = newSource; }
public int Get(int i) { if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { setUriPattern("/repos"); setMethod(MethodType.PUT); }
public bool IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void Remove()  {     if (expectedModCount == list.modCount)      {         if (lastLink != null)          {             Link<ET> next = lastLink.next;             Link<ET> previous = lastLink.previous;             next.previous = previous;             previous.next = next;             if (lastLink == link)              {                 pos--;             }             link = previous;             lastLink = null;             expectedModCount++;             list.size--;             list.modCount++;         }          else          {             throw new InvalidOperationException();         }     }      else      {         throw new InvalidOperationException("Collection was modified; enumeration operation may not execute.");     } }
public virtual MergeShardsResult mergeShards(MergeShardsRequest request) { request = beforeClientExecution(request); return executeMergeShards(request); }
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request)
public int getBeginIndex() { return start; }
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
public ByteBuffer compact() => throw new ReadOnlyBufferException();
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations)  {     for (int i = 0; i < iterations; ++i)      {         long byte0 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = byte0 >>> 2;         long byte1 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4);         long byte2 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6);         values[valuesOffset++] = byte2 & 63;     } }
public virtual string GetHumanishName(){var s = GetPath();if(s == "/" || s == "")s = GetHost();if(s == null)throw new ArgumentException();var elements = (scheme == "file" || LOCAL_FILE.IsMatch(s))?s.Split(new[]{'\\',Path.DirectorySeparatorChar,'/'}, StringSplitOptions.RemoveEmptyEntries):s.Split(new[]{'/'}, StringSplitOptions.RemoveEmptyEntries);if(elements.Length == 0)throw new ArgumentException();var result = elements[elements.Length - 1];if(result == Constants.DOT_GIT)result = elements[elements.Length - 2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(String vaultName, String jobId) { this.VaultName = vaultName; this.JobId = jobId; }
public virtual EscherRecord GetEscherRecord(int index) => escherRecords[index];
public GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public virtual TrackingRefUpdate GetTrackingRefUpdate() => trackingRefUpdate;
public void Print(bool b) => Print(b.ToString());
public virtual QueryNode GetChild() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { setProtocol(ProtocolType.HTTPS); }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange GetOrdRange(string dim) => prefixToOrdRange[dim];
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().Size()) { symbol = getInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E Peek() { return PeekFirstImpl(); }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord clone() { return copy(); }
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public virtual HyphenatedWordsFilter Create(TokenStream input) => new HyphenatedWordsFilter(input);
public virtual CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new File(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static String ToHex(long value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) => ExecuteUpdateDistribution(BeforeClientExecution(request));
public HSSFColor GetColor(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index)?.Let(c => new CustomColor(index, c));
public override ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedFunctionException(_functionName);
public void Serialize(ILittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) => ExecuteUploadArchive(BeforeClientExecution(request));
public List<Token> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (obj == this) return true; if (obj == null || GetType() != obj.GetType()) return false; var other = (AutomatonQuery)obj; return compiled.Equals(other.compiled) && (term == null ? other.term == null : term.Equals(other.term)); }
public virtual SpanQuery MakeSpanClause(){var spanQueries = new SpanQuery[Size()];var sqi = weightBySpanQuery.Keys.GetEnumerator();int i = 0;while (sqi.MoveNext()){var sq = sqi.Current;float boost = weightBySpanQuery[sq];if (boost != 1f)sq = new SpanBoostQuery(sq, boost);spanQueries[i++] = sq;}return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);}
public StashCreateCommand StashCreate() { return new StashCreateCommand(repo); }
public virtual FieldInfo FieldInfo(string fieldName) { return byName.GetValue(fieldName); }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;     options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;     return Invoke<ModifyDBProxyResponse>(request, options); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public virtual bool Exists() { return fs.Exists(objects); }
public FilterOutputStream(OutputStream out) { this.out = out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public DataValidationConstraint createTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void SetSharedFormula(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public virtual bool IsReuseObjects() { return reuseObjects; }
public ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request)
public RevFilter Clone() => new Binary(a.Clone() as object[], b.Clone() as object[]);
public override bool Equals(object o) => o is ArmenianStemmer;
public virtual bool HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) => executeUpdateContributorInsights(beforeClientExecution(request));
public void unwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request)
public virtual byte[] GetObjectData() => FindObjectRecord().GetObjectData();
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short LookupIndexByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return (short)fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) => executeDescribeAnomalyDetectors(beforeClientExecution(request));
public static string InsertId(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long GetObjectSize(AnyObjectId objectId, int typeHint)  {     long sz = db.GetObjectSize(this, objectId);     if (sz < 0)      {         if (typeHint == OBJ_ANY)             throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);         throw new MissingObjectException(objectId.Copy(), typeHint);     }     return sz; }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) { }
public virtual GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public virtual ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public virtual TokenStream Create(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() { return precedence + " >= _p"; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public virtual void Close(){if (isOpen){isOpen = false;try{dump();}finally{try{channel.Truncate(fileLength);}finally{try{channel.Close();}finally{fos.Close();}}}}
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public virtual string getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public virtual string GetPath() { return pathStr; }
public static double DevSq(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) : this(executeDescribeResize(beforeClientExecution(request)))
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int end() { return end(0); }
public void Traverse(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) continue; for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) continue; if (IsEmpty(currentCell) && !traverseEmptyCells) continue; long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), (long)width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } }
public int GetReadIndex() { return pos; }
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : Float.Compare(this.boost, other.boost);
public int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
public void serialize(LittleEndianOutput @out) { @out.writeShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes.GetValueOrDefault(dimName, new DimConfig()); ft.multiValued = v; fieldTypes[dimName] = ft; } }
public int GetCellsVal() => cells.Keys.Count(c => at(c).cmd >= 0);
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request)
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
