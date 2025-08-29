public void serialize(LittleEndianOutput @out) { @out.writeShort(field_1_vcenter); }
public void AddAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
public void WriteByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
public long ramBytesUsed() { return ((termOffsets != null) ? termOffsets.ramBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.ramBytesUsed() : 0); }
public string GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() : this(true) { _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SetPath(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = this.beforeClientExecution(request); return this.executeGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready() { lock (lockObj) { if (@in == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining() || @in.Available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord GetOptRecord() { return _optRecord; }
public int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) { throw new ArgumentNullException("buffer", "buffer == null"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentException("Invalid offset or length"); } if (length == 0) { return 0; } int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) { Write(str != null ? str : "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() { return base.NextEntry().Value; }
public sealed void readBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len>0) Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new IOException("read past EOF: " + this); } else { Buffer.BlockCopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) throw new IOException("read past EOF: " + this); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void Remove() { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void setParams(string @params) {base.setParams(@params);language=country=variant="";string[] tokens=@params.Split(new char[]{','},StringSplitOptions.RemoveEmptyEntries);if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) => executeGetInstanceAccessDetails(beforeClientExecution(request));
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public GetDashboardResult GetDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
public static string Quote(string s){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=s.IndexOf("\\E",apos,StringComparison.Ordinal))>=0){sb.Append(s.Substring(apos,k+2-apos)).Append("\\E\\Q");apos=k+2;}return sb.Append(s.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer PutInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request){request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public string toString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public sealed void incRef() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int GetNextXBATChainOffset() => GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
public void MultiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public override string ToString() { var b = new StringBuilder(); var l = Length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) b.Append(Path.DirectorySeparatorChar); } return b.ToString(); }
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void reset() {if (!first()) {ptr = 0;if (!eof())parseEntry();}}
public E Previous() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); else throw new InvalidOperationException(); }
public string GetNewPrefix() { return this.newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } var terms = new HashSet<string>(dictionary.ignoreCase ? StringComparer.OrdinalIgnoreCase : StringComparer.Ordinal); var deduped = new List<CharsRef>(); foreach (var s in stems) { string key = new string(s.Chars, s.Offset, s.Length); if (terms.Add(key)) { deduped.Add(s); } } return deduped; }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long skip(long n) { int s = (int)Math.Min(available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(BinaryWriter out) { out.Write((short)field_1_row); out.Write((short)field_2_col); out.Write((short)field_3_flags); out.Write((short)field_4_shapeid); out.Write((short)field_6_author.Length); out.Write(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { foreach (char c in field_6_author) { out.Write((short)c); } } else { foreach (char c in field_6_author) { out.Write((byte)c); } } if (field_7_padding.HasValue) { out.Write((byte)field_7_padding.Value); } }
public int lastIndexOf(string @string) { return lastIndexOf(@string, count); }
public bool Add(E item) { return addLastImpl(item); }
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state; res = UnsetSection(src, section, subsection); } while (Interlocked.CompareExchange(ref state, res, src) != src); }
public sealed string getTagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
public long length() { return inCoreLength(); }
public void setValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int Get(int i) { if (count <= i) throw new ArgumentOutOfRangeException(nameof(i), i, "Index was out of range."); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { setUriPattern("/repos"); setMethod(MethodType.PUT); }
public bool isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); } }
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = beforeClientExecution(request); return executeMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection(request); }
public int GetBeginIndex() { return start; }
public static WeightedTerm[] getTerms(Query query) { return getTerms(query, false); }
public ByteBuffer compact() { throw new ReadOnlyBufferException(); }
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { long byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; long byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); long byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public string GetHumanishName() { string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = Regex.Split(s, @"[\\/]"); else elements = Regex.Split(s, @"/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { setVaultName(vaultName); setJobId(jobId); }
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult GetApis(GetApisRequest request) => executeGetApis(beforeClientExecution(request));
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
public void print(bool b) { print(Convert.ToString(b)); }
public QueryNode GetChild() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream @in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => executeDescribeTransitGatewayVpcAttachments(beforeClientExecution(request));
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = this.beforeClientExecution(request); return this.executePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange GetOrdRange(string dim) => prefixToOrdRange[dim];
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size()) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public T Peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord Clone() { return Copy(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToHex(long value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
public HSSFColor GetColor(short index) { if (index == HSSFColorPredefined.AUTOMATIC.Index) return HSSFColorPredefined.AUTOMATIC.Color; byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(index, b); }
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Serialize(LittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
public List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public SpanQuery MakeSpanClause() { SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; int i = 0; foreach (SpanQuery sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; if (boost != 1.0f) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand stashCreate() { return new StashCreateCommand(repo); }
public FieldInfo FieldInfo(string fieldName) { return byName[fieldName]; }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request){request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) => executeModifyDBProxy(beforeClientExecution(request));
public void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.oversize(1 + count, 4)]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.oversize(1 + count, 4)]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { this.setProtocol(ProtocolType.HTTPS); }
public bool exists() { return fs.exists(objects); }
public FilterOutputStream(Stream @out) { this.@out = @out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { SetUriPattern("/clusters/[ClusterId]"); SetMethod(MethodType.PUT); }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups(request); }
public void setSharedFormula(bool flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public bool isReuseObjects() { return reuseObjects; }
public ErrorNode AddErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { setProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public RevFilter Clone() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public byte[] getObjectData() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static short lookupIndexByName(string name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { fd = getInstanceCetab().getFunctionByNameInternal(name); if (fd == null) { return -1; } } return (short)fd.getIndex(); }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public static string insertId(string message, ObjectId changeId) { return insertId(message, changeId, false); }
public long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), JGitText.get().unknownObjectType2); throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) { }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() { return precedence + " >= _p"; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult getOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
public void copyRawTo(byte[] b, int o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) { return executeStopWorkspaces(beforeClientExecution(request)); }
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public string PathStr => pathStr;
public static double devsq(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public bool HasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() { return end(0); }
public void Traverse(CellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ctx.RowNumber++) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ctx.ColNumber++) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !TraverseEmptyCells) { continue; } long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), (long)width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
public int GetReadIndex() { return pos; }
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return other.bytes.CompareTo(this.bytes);elsereturn this.boost.CompareTo(other.boost);}
public int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
public void serialize(LittleEndianOutput @out) { @out.writeShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { setAttributeName(attributeName); setKeyType(keyType.ToString()); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
public bool hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
public int GetCellsVal() { int size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
