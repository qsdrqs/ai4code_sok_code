public void RemoveSourceIdentifierFromSubscription(BinaryWriter writer) { writer.Write(field_1_vcenter); }
public void quoteReplacement(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void toString(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId GetOrdRange() { return objectId; }
public DeleteDomainEntryResult SetTagger(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long GetRoute() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() : this(true) { _header.BATCount = 1; _header.BATArray = new int[] { 1 }; BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 1; _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.StartBlock = 0; }
public void Create(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand write(string path) { this.path = path; return this; }
public ListIngestionsResult GetSpatialStrategy(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult CreateStreamingDistribution(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool SetBytesValue() { lock (lock) { if (in == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining() || in.Available > 0; } catch (IOException) { return false; } } }
public EscherOptRecord asReadOnlyBuffer() { return _optRecord; }
public int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) throw new ArgumentNullException("buffer", "buffer == null"); if (offset < 0 || length < 0 || offset > buffer.Length || length > buffer.Length - offset) throw new ArgumentOutOfRangeException("offset or length"); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte)this.buffer[pos + i]; pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void deleteTransitGateway(string str) { write(str != null ? str : "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V StopDominantLanguageDetectionJob() { return base.NextEntry().Value; }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new IOException("read past EOF: " + this.ToString()); } else { Buffer.BlockCopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new IOException("read past EOF: " + this.ToString()); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult UnsetSection(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void allocate() { throw new NotSupportedException(); }
public CacheSubnetGroup create(ModifyCacheSubnetGroupRequest request) { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void describeConnections(string @params) { base.setParams(@params); language = country = variant = ""; string[] tokens = @params.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
public DeleteDocumentationVersionResult Serialize(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResult Decode(GetInstanceAccessDetailsRequest request) => ExecuteGetInstanceAccessDetails(BeforeClientExecution(request));
public HSSFPolygon boolean(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape; }
public string isSaturated(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResult Read(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult tagQueue(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void Add(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.GetFirstColumn()); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
public static string Last(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos, StringComparison.Ordinal)) >=0) { sb.Append(input.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(input.Substring(apos)).Append("\\E").ToString(); }
public byte[] fragA(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult listAssessmentTemplates(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public string openPush() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public string toFormulaString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public deregisterTransitGatewayMulticastGroupMembers incRef() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult DescribeDetector(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
public int setForce() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public void getDisk(int pow10){TenPower tp=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){mulShift(tp._divisor,tp._divisorShift);}else{mulShift(tp._multiplicand,tp._multiplierShift);}}
public override string ToString() { var b = new StringBuilder(); int l = Length(); b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider GetEvaluation(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void setOverridable(ProgressMonitor pm) { progressMonitor = pm; }
public void DeleteWorkspaceImage() { if (!first()) { ptr = 0; if (!eof()) parseEntry(); } }
public E Slice() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
public string GetNewPrefix() { return this.newPrefix; }
public int GetAssignment(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> registerTransitGatewayMulticastGroupMembers(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.Count < 2) return stems; HashSet<CharsRef> terms = new HashSet<CharsRef>(8); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } return deduped; }
public GetGatewayResponsesResult devsq(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
public void compareTo(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long describeLogPattern(long n) { int s = (int)Math.Min(available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.setBootstrapActionConfig(bootstrapActionConfig); }
public void getPronunciation(BinaryWriter @out) { @out.Write(field_1_row); @out.Write(field_2_col); @out.Write(field_3_flags); @out.Write(field_4_shapeid); @out.Write((short)field_6_author.Length); @out.Write((byte)(field_5_hasMultibyte ? 1 : 0)); if (field_5_hasMultibyte) { foreach (char c in field_6_author) @out.Write((short)c); } else { foreach (char c in field_6_author) @out.Write((byte)c); } if (field_7_padding.HasValue) @out.Write((byte)field_7_padding.Value); }
public int @new(string @string) { return LastIndexOf(@string, count); }
public bool GetAccessKeySecret(E @object) { return AddLastImpl(@object); }
public void DescribeMatchmakingRuleSets(string section, string subsection) { ConfigSnapshot src, res; do { src = state; res = UnsetSection(src, section, subsection); } while (Interlocked.CompareExchange(ref state, res, src) != src); }
public updateS3Resources getTagName() { return tagName; }
public void getBeginIndex(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool listBonusPayments(object o) { lock(mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter build(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
public long putLong() { return inCoreLength(); }
public void getChild(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int MergeShards(int i){if(count<=i)throw new ArgumentOutOfRangeException("i",i,"Index was out of range.");return entries[i];}
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public bool DisableCaching() { return deltaBaseAsOffset; }
public void addCommand() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); } }
public MergeShardsResult CreateProxySession(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult Decode(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int kthSmallest() { return start; }
public static WeightedTerm[] getTerms(Query query) { return getTerms(query, false); }
public ByteBuffer ToString() { throw new ReadOnlyBufferException(); }
public void CreateDBSubnetGroup(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {for (int i = 0; i < iterations; ++i) {long byte0 = blocks[blocksOffset++];values[valuesOffset++] = byte0 >> 2;long byte1 = blocks[blocksOffset++];values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4);long byte2 = blocks[blocksOffset++];values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6);values[valuesOffset++] = byte2 & 63;}}
public string associateMemberAccount() { string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = Regex.Split(s, @"[\\/]").Where(e => !string.IsNullOrEmpty(e)).ToArray(); else elements = Regex.Split(s, @"/+").Where(e => !string.IsNullOrEmpty(e)).ToArray(); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (result == Constants.DOT_GIT) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult GetCell(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public string deleteDataSource() { return this.accessKeySecret; }
public CreateVpnConnectionResult pattern(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult join(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult Decode(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { setVaultName(vaultName); setJobId(jobId); }
public EscherRecord ListHyperParameterTuningJobs(int index) { return escherRecords[index]; }
public GetApisResult DeleteMembers(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
public DeleteSmsChannelResult clearDFA(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public TrackingRefUpdate @short() { return trackingRefUpdate; }
public void Serialize(bool b) { print(b.ToString()); }
public QueryNode startRelationalDatabase() { return getChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream @in) { field_1_formatFlags = @in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public DescribeTransitGatewayVpcAttachmentsResult describeLocalGatewayVirtualInterfaces(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult ToString(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange RestoreFromClusterSnapshot(string dim) { return prefixToOrdRange[dim]; }
public string EmitEOF() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Count) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E TryFastForward() { return PeekFirstImpl(); }
public CreateWorkspacesResult freeBefore(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord describeDashboard() { return copy(); }
public DescribeRepositoriesResult hasPrevious(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter add(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult getToUnicodeLE(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new File(fileName), mode) { }
public DeleteWorkspaceImageResult ToString(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string sumTokenSizes(long value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult GetValue(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
public HSSFColor GetPersonTracking(short index) { if (index == HSSFColorPredefined.AUTOMATIC.Index) { return HSSFColorPredefined.AUTOMATIC.Color; } byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(index, b); }
public ValueEval asReadOnlyBuffer(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void toString(LittleEndianOutput @out) { @out.writeShort((short)field_1_number_crn_records); @out.writeShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult eat() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) {this._character = character;this._fontIndex = fontIndex;}
public static byte[] ParserExtension(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult GetFindings(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<Token> CreateVariable(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public bool getUniqueAlt(object obj){if (this == obj)return true;if (!base.Equals(obj))return false;if (GetType() != obj.GetType())return false;AutomatonQuery other = (AutomatonQuery)obj;if (!compiled.Equals(other.compiled))return false;if (term == null){if (other.term != null)return false;}else if (!term.Equals(other.term))return false;return true;}
public SpanQuery UniqueStems() { SpanQuery[] spanQueries = new SpanQuery[size()]; int i = 0; foreach (SpanQuery sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
public StashCreateCommand DeregisterWorkspaceDirectory() { return new StashCreateCommand(repo); }
public FieldInfo putLifecycleEventHookExecutionStatus(string fieldName) { return byName[fieldName]; }
public DescribeEventSourceResult Get(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult FromRuleContext(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult describeAnomalyDetectors(CancelUpdateStackRequest request) { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult @int(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult Get(SetInstanceProtectionRequest request){request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResult GetBytesReader(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void GetSSTRecord(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[count + 1]; Array.Copy(endOffsets, next, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[count + 1]; Array.Copy(posLengths, next, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public bool DescribeNetworkInterfaces() { return fs.Exists(objects); }
public FilterOutputStream(Stream @out) { this.out = @out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { SetUriPattern("/clusters/[ClusterId]"); SetMethod(MethodType.PUT); }
public DataValidationConstraint peek(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ToString(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult ListComponents(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void toString(bool flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public bool undeprecateDomain() { return reuseObjects; }
public ErrorNode toString(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent(this); return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public EventSubscription equals(RemoveSourceIdentifierFromSubscriptionRequest request) { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Next(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { setProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResult listDominantLanguageDetectionJobs(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public RevFilter ListExclusions() { return new Binary(a.Clone(), b.Clone()); }
public bool createParticipantConnection(object o) { return o is ArmenianStemmer; }
public Floor HasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult Handles(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void serialize() {records.Remove(fileShare);records.Remove(writeProtect);fileShare = null;writeProtect = null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult @void(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public byte[] fromConfig() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResult ToString(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public string stopKeyPhrasesDetectionJob() { return getKey() + ": " + getValue(); }
public ListTextTranslationJobsResult PutMetricData(ListTextTranslationJobsRequest request) { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResult DescribeAlias(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static short toString(string name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { fd = getInstanceCetab().getFunctionByNameInternal(name); if (fd == null) { return -1; } } return (short)fd.getIndex(); }
public DescribeAnomalyDetectorsResult NameSet(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public static string UpdateDistribution(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
public long CreateSecurityConfiguration(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult neverEquals(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult CreateDocumentationPart(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) {}
public GetFieldLevelEncryptionConfigResult CreateRoom(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult ShortBuffer(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult DescribeNetworkInterfaces(ReportInstanceStatusRequest request) { request = this.BeforeClientExecution(request); return this.ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult Create(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public TokenStream GetShardIterator(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool promoteReadReplicaDBCluster(object @object) { lock (mutex) { return c.Remove(@object); } }
public GetDedicatedIpResult RamBytesUsed(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public string replace() { return precedence + " >= _p;"; }
public ListStreamProcessorsResult @public(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult Serialize(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void DescribeModelPackage(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(BinaryReader in) { field_1_h_hold = in.ReadInt16(); field_2_v_hold = in.ReadInt16(); field_3_width = in.ReadInt16(); field_4_height = in.ReadInt16(); field_5_options = in.ReadInt16(); field_6_active_sheet = in.ReadInt16(); field_7_first_visible_tab = in.ReadInt16(); field_8_num_selected_tabs = in.ReadInt16(); field_9_tab_width_ratio = in.ReadInt16(); }
public StopWorkspacesResult DeleteApp(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void GetVoiceConnectorProxy() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DeleteLifecyclePolicy(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string setupEnvironment(int wordId, char[] surface, int off, int len) { return null; }
public string getRef3DEval() { return pathStr; }
public static double createDedicatedIpPool(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult Include(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public int hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int ready() { return end(0); }
public void SetRemote(CellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), (long)width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
public int unwriteProtectWorkbook() { return pos; }
public int toArray(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.get().CompareTo(this.bytes.get()); else return this.boost.CompareTo(other.boost); }
public int setTerminationProtection(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
public void deleteDomainEntry(LittleEndianOutput output) { output.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool toArray(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch Append(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes.Add(dimName, ft); } ft.MultiValued = v; } }
public int describeEventSource() { int size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) size++; } return size; }
public DeleteVoiceConnectorResult String(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult ToString(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
