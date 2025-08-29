public void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_vcenter); }
public void AddAll(BlockList<T> src) { if (src.Size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BlockSize); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
public void WriteByte(byte b) {if (upto == blockSize) {if (currentBlock != null) {AddBlock(currentBlock);}currentBlock = new byte[blockSize];upto = 0;}currentBlock[upto++] = b;}
public ObjectId ObjectId => objectId;
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long RamBytesUsed() => (termOffsets?.RamBytesUsed() ?? 0) + (termsDictOffsets?.RamBytesUsed() ?? 0);
public string GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() : this(true) { _header.BATCount = 1; _header.BATArray = new int[] { 1 }; BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 1; _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.StartBlock = 0; }
public void Init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SetPath(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready() { lock (_lock) { if (_in == null) throw new IOException("StreamReader is closed"); try { return _bytes.HasRemaining || _in.Available > 0; } catch (IOException) { return false; } } }
public EscherOptRecord OptRecord { get { return _optRecord; } }
public int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) throw new System.ArgumentNullException(nameof(buffer), "buffer == null"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new System.IndexOutOfRangeException(); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this._internalBuffer[pos + i]; } pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() { return base.NextEntry().Value; }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer){int available = bufferLength - bufferPosition;if(len <= available){if(len>0) Array.Copy(buffer, bufferPosition, b, offset, len);bufferPosition+=len;} else {if(available > 0){Array.Copy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;}if (useBuffer && len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer, 0, b, offset, bufferLength);throw new System.IO.EndOfStreamException("read past EOF: " + this);} else {Array.Copy(buffer, 0, b, offset, len);bufferPosition=len;}} else {long after = bufferStart+bufferPosition+len;if(after > Length)throw new System.IO.EndOfStreamException("read past EOF: " + this);ReadInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void Remove() { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void SetParams(string params){base.SetParams(params);language = country = variant = "";var tokens = params.Split(',');if (tokens.Length > 0) language = tokens[0];if (tokens.Length > 1) country = tokens[1];if (tokens.Length > 2) variant = tokens[2];}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) {if (!(obj is FacetLabel)) {return false;}FacetLabel other = (FacetLabel)obj;if (length != other.length) {return false;}for (int i = length - 1; i >= 0; i--) {if (!components[i].Equals(other.components[i])) {return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
public GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
public static string quote(string str){System.Text.StringBuilder sb = new System.Text.StringBuilder();sb.Append("\\Q");int apos = 0;int k;while ((k = str.IndexOf("\\E", apos)) >= 0){sb.Append(str.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q");apos = k + 2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer PutInt(int value) { throw new NotSupportedException(); }
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() { return $"{GetType().FullName} [{GetValueAsString()}]"; }
public string ToString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void incRef() { System.Threading.Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int NextXBATChainOffset => XBATEntriesPerBlock * LittleEndianConsts.INT_SIZE;
public void MultiplyByPowerOfTen(int pow10) {TenPower tp = TenPower.GetInstance(Math.Abs(pow10));if (pow10 < 0) {MulShift(tp._divisor, tp._divisorShift);} else {MulShift(tp._multiplicand, tp._multiplierShift);}}
public override string ToString(){var b = new System.Text.StringBuilder();int l = Length();b.Append(System.IO.Path.DirectorySeparatorChar);for (int i = 0; i < l; i++){b.Append(GetComponent(i));if (i < l - 1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.RoleName = roleName; return this; }
public void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void reset() {if (!first()) {ptr = 0;if (!eof())parseEntry();}}
public E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string NewPrefix => newPrefix;
public int IndexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(ILittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
public int lastIndexOf(string value) { return lastIndexOf(value, count); }
public bool Add(E item) { return AddLastImpl(item); }
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state; res = UnsetSection(src, section, subsection); } while (System.Threading.Interlocked.CompareExchange(ref state, res, src) != src); }
public string TagName => tagName;
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return Delegate().Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long Length() { return InCoreLength(); }
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { OldSource = oldSource; NewSource = newSource; }
public int Get(int i) { if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPattern = "/repos"; Method = MethodType.PUT; }
public bool DeltaBaseAsOffset => deltaBaseAsOffset;
public void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.Next; var previous = lastLink.Previous; next.Previous = previous; previous.Next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.Count--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int BeginIndex => start;
public static WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer Compact() { throw new NotSupportedException(); }
public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {for (int i = 0; i < iterations; ++i) {long byte0 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = byte0 >> 2;long byte1 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4);long byte2 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6);values[valuesOffset++] = byte2 & 63;}}
public string GetHumanishName() { string s = GetPath(); if (s == "/" || string.IsNullOrEmpty(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { System.IO.Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (result == Constants.DOT_GIT) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string AccessKeySecret => this.accessKeySecret;
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate TrackingRefUpdate => trackingRefUpdate;
public void print(bool b) { print(b.ToString()); }
public QueryNode GetChild() => GetChildren()[0];
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange GetOrdRange(string dim) { return prefixToOrdRange[dim]; }
public override string ToString() { string symbol = ""; if (StartIndex >= 0 && StartIndex < InputStream.Size) { symbol = InputStream.GetText(Interval.Of(StartIndex, StartIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return $"{nameof(LexerNoViableAltException)}('{symbol}')"; }
public E Peek() { return PeekFirstImpl(); }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord Clone() { return Copy(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter create(TokenStream input) {return new HyphenatedWordsFilter(input);}
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new File(fileName), mode) {}
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToHex(long value) { return value.ToString("X16"); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
public HSSFColor GetColor(short index){if (index == HSSFColor.Automatic.Index) {return HSSFColor.Automatic.GetInstance();}byte[] b = _palette.GetColor(index);return (b == null) ? null : new CustomColor(index, b);}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Serialize(LittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public IList<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) {if (ReferenceEquals(this, obj)) return true;if (!base.Equals(obj)) return false;if (GetType() != obj.GetType()) return false;AutomatonQuery other = (AutomatonQuery)obj;if (!compiled.Equals(other.compiled)) return false;if (!object.Equals(term, other.term)) return false;return true;}
public SpanQuery MakeSpanClause() { var spanQueries = weightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreate() => new StashCreateCommand(repo);
public FieldInfo FieldInfo(string fieldName) => byName[fieldName];
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { Array.Resize(ref endOffsets, count + 1); } if (count == posLengths.Length) { Array.Resize(ref posLengths, count + 1); } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.Https; }
public bool Exists() => fs.Exists(objects);
public FilterOutputStream(Stream stream) { this.out = stream; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public IDataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void SetSharedFormula(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public bool ReuseObjects => reuseObjects;
public ErrorNode AddErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory ForName(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.Https; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public RevFilter Clone() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
public byte[] GetObjectData() { return FindObjectRecord().GetObjectData(); }
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() => $"{GetKey()}: {GetValue()}";
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short LookupIndexByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return (short)fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string insertId(string message, ObjectId changeId) { return insertId(message, changeId, false); }
public long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput input) : this(input.ReadDouble()) { }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public TokenStream Create(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object item) { lock (mutex) { return c.Remove(item); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() { return $"{precedence} >= _p"; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void Close() {if (isOpen) {isOpen = false;try {Dump();} finally {try {channel.SetLength(fileLength);} finally {try {channel.Close();} finally {fos.Close();}}}}}
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public string Path => pathStr;
public static double devsq(double[] v) {double r = double.NaN;if (v!=null && v.Length >= 1) {double m = 0;double s = 0;int n = v.Length;for (int i=0; i<n; i++) {s += v[i];}m = s / n;s = 0;for (int i=0; i<n; i++) {s += (v[i]- m) * (v[i] - m);}r = (n == 1)? 0: s;}return r;}
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int end() {return end(0);}
public void Traverse(CellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; var ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !TraverseEmptyCells) { continue; } long rowSize = checked((long)(ctx.RowNumber - firstRow) * width); ctx.OrdinalNumber = checked(rowSize + (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
public int ReadIndex => pos;
public int CompareTo(ScoreTerm other) { if (this.Boost == other.Boost) return other.Bytes.Get().CompareTo(this.Bytes.Get()); else return float.Compare(this.Boost, other.Boost); }
public int normalize(char[] s, int len) {for (int i = 0; i < len; i++) {switch (s[i]) {case FARSI_YEH:case YEH_BARREE:s[i] = YEH;break;case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE: len = delete(s, i, len);i--;break;default:break;}}return len;}
public void Serialize(LittleEndianOutput out) { out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool HasObject(AnyObjectId id) { return FindOffset(id) != -1; }
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
[MethodImpl(MethodImplOptions.Synchronized)] public void SetMultiValued(string dimName, bool v) { if (!fieldTypes.TryGetValue(dimName, out var ft)) { ft = new DimConfig(); fieldTypes.Add(dimName, ft); } ft.MultiValued = v; }
public int GetCellsVal() { return cells.Keys.Count(c => at(c).cmd >= 0); }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
