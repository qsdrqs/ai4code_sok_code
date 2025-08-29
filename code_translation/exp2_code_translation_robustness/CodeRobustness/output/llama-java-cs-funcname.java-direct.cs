public void removeSourceIdentifierFromSubscription(ILittleEndianOutput out) { out.WriteShort(field_1_vcenter); }
public void quoteReplacement(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void toString(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getOrdRange() => objectId;
public DeleteDomainEntryResult setTagger(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
public long GetRoute() => (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string GetFullMessage() => RawParseUtils.Decode(GuessEncoding(), buffer, RawParseUtils.TagMessage(buffer, 0), buffer.Length);
public POIFSFileSystem() { this(true); _header.setBATCount(1); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
public void create(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; }
public SubmoduleAddCommand Write(string path) { this.path = path; return this; }
public ListIngestionsResult GetSpatialStrategy(ListIngestionsRequest request) => executeListIngestions(beforeClientExecution(request));
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult CreateStreamingDistribution(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool SetBytesValue() { lock (lock) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || inStream.Available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord AsReadOnlyBuffer() { return _optRecord; }
public synchronized int Read(byte[] buffer, int offset, int length)  {      if (buffer == null) throw new ArgumentNullException(nameof(buffer));      if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentException("Invalid offset or length");      if (length == 0) return 0;      int copylen = Math.Min(count - pos, length);      for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte)this.buffer[pos + i];      pos += copylen;      return copylen;  }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void DeleteTransitGateway(string str) => Write(str != null ? str : (string)null);
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V stopDominantLanguageDetectionJob() => ((IDictionaryEnumerator)base.NextEntry()).Value;
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer)  {     int available = bufferLength - bufferPosition;     if (len <= available)      {         if (len > 0)              Array.Copy(buffer, bufferPosition, b, offset, len);         bufferPosition += len;     }      else      {         if (available > 0)          {             Array.Copy(buffer, bufferPosition, b, offset, available);             offset += available;             len -= available;             bufferPosition += available;         }         if (useBuffer && len < bufferSize)          {             Refill();             if (bufferLength < len)              {                 Array.Copy(buffer, 0, b, offset, bufferLength);                 throw new EndOfStreamException("read past EOF: " + this);             }              else              {                 Array.Copy(buffer, 0, b, offset, len);                 bufferPosition = len;             }         }          else          {             long after = bufferStart + bufferPosition + len;             if (after > Length())                  throw new EndOfStreamException("read past EOF: " + this);             ReadInternal(b, offset, len);             bufferStart = after;             bufferPosition = 0;             bufferLength = 0;         }     } }
public TagQueueResult UnsetSection(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void Allocate() => throw new NotSupportedException();
public CacheSubnetGroup Create(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void DescribeConnections(string @params) { base.SetParams(@params); language = country = variant = ""; var st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult Serialize(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { return obj is FacetLabel other && length == other.length && Enumerable.Range(0, length).All(i => components[i].Equals(other.components[i])); }
public GetInstanceAccessDetailsResult Decode(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon Boolean(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string IsSaturated(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
public GetDashboardResult Read(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult TagQueue(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public void Add(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
public static string Last(string str) => new StringBuilder().Append("\\Q").Append(str.Replace("\\E", "\\\\E\\Q")).Append("\\E").ToString();
public ByteBuffer FragA(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult ListAssessmentTemplates(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public string OpenPush() => GetType().Name + " [" + GetValueAsString() + "]";
public string ToFormulaString(string field) => $"ToChildBlockJoinQuery ({parentQuery.ToString()})";
public DeregisterTransitGatewayMulticastGroupMembers IncRef() { return refCount++; }
public UpdateConfigurationSetSendingEnabledResult DescribeDetector(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int SetForce() => GetXBATEntriesPerBlock() * LittleEndianConsts.IntSize;
public void GetDisk(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public override string ToString() => string.Join(System.IO.Path.DirectorySeparatorChar.ToString(), Enumerable.Range(0, length()).Select(i => getComponent(i)));
public InstanceProfileCredentialsProvider GetEvaluation(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void SetOverridable(ProgressMonitor pm) { progressMonitor = pm; }
public void DeleteWorkspaceImage() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E Slice() => iterator.PreviousIndex() >= start ? iterator.Previous() : throw new InvalidOperationException();
public string GetNewPrefix() { return this.newPrefix; }
public int getAssignment(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> registerTransitGatewayMulticastGroupMembers(char[] word, int length)  {     var stems = stem(word, length);     if (stems.Count < 2)          return stems;     var terms = new HashSet<CharsRef>(dictionary.IgnoreCase ? StringComparer.OrdinalIgnoreCase : StringComparer.Ordinal);     return stems.Where(s => !terms.Contains(s)).Select(s => { terms.Add(s); return s; }).ToList(); }
public GetGatewayResponsesResult Devsq(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public void CompareTo(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long DescribeLogPattern(long n) { int s = (int) Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.BootstrapActionConfig = bootstrapActionConfig; }
public void GetPronunciation( LittleEndianOutput @out ) { @out.WriteShort( field_1_row ); @out.WriteShort( field_2_col ); @out.WriteShort( field_3_flags ); @out.WriteShort( field_4_shapeid ); @out.WriteShort( ( short )field_6_author.Length ); @out.WriteByte( field_5_hasMultibyte ? ( byte )0x01 : ( byte )0x00 ); if ( field_5_hasMultibyte ) StringUtil.PutUnicodeLE( field_6_author, @out ); else StringUtil.PutCompressedUnicode( field_6_author, @out ); if ( field_7_padding.HasValue ) @out.WriteByte( ( byte )field_7_padding.Value ); }
public int New(string str) => LastIndexOf(str, count);
public bool GetAccessKeySecret(E @object) { return AddLastImpl(@object); }
public void DescribeMatchmakingRuleSets(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareExchange(ref src, res, src)); }
public readonly UpdateS3Resources TagName { get; } = tagName;
public void GetBeginIndex(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool ListBonusPayments(object o) { lock (mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter Build(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long PutLong() => InCoreLength();
public void GetChild(bool newValue) { value = newValue; }
public SourceChange(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int mergeShards(int i) => count <= i ? throw new IndexOutOfRangeException() : entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public bool DisableCaching() => deltaBaseAsOffset;
public void AddCommand() =>      (expectedModCount == list.modCount) ?      (lastLink != null ?          (next = lastLink.next,           previous = lastLink.previous,           next.previous = previous,           previous.next = next,           (lastLink == link ? pos-- : 0),           link = previous,           lastLink = null,           expectedModCount++,           list.size--,           list.modCount++) :          throw new InvalidOperationException()) :      throw new InvalidOperationException();
public MergeShardsResult CreateProxySession(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult Decode(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int KthSmallest() { return start; }
public static readonly WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public override string ToString() => throw new InvalidOperationException();
public void createDBSubnetGroup(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {for (int i = 0; i < iterations; ++i) {long byte0 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = byte0 >>> 2;long byte1 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4);long byte2 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6);values[valuesOffset++] = byte2 & 63;}}
public string AssociateMemberAccount() {      string s = GetPath();      if (s == "/" || s == "") s = GetHost();      if (s == null) throw new ArgumentException();      string[] elements;      if (scheme == "file" || System.Text.RegularExpressions.Regex.IsMatch(s, @"^.*[\/\\].*$"))          elements = s.Split(new char[] { '\\', '/' }, StringSplitOptions.RemoveEmptyEntries);      else elements = s.Split(new char[] { '/' }, StringSplitOptions.RemoveEmptyEntries);      if (elements.Length == 0) throw new ArgumentException();      string result = elements[elements.Length - 1];      if (result == Constants.DotGit) result = elements[elements.Length - 2];      else if (result.EndsWith(Constants.DotGitExt)) result = result.Substring(0, result.Length - Constants.DotGitExt.Length);      return result;  }
public DescribeNotebookInstanceLifecycleConfigResult GetCell(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string DeleteDataSource() => this.accessKeySecret;
public CreateVpnConnectionResult Pattern(CreateVpnConnectionRequest request) => executeCreateVpnConnection(beforeClientExecution(request));
public DescribeVoicesResult Join(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult Decode(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord listHyperParameterTuningJobs(int index) => escherRecords[index];
public GetApisResult DeleteMembers(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult ClearDfa(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate Short() => trackingRefUpdate;
public void Serialize(bool b) { Console.Write(b.ToString()); }
public QueryNode StartRelationalDatabase() => GetChildren()[0];
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public DescribeTransitGatewayVpcAttachmentsResult DescribeLocalGatewayVirtualInterfaces(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult ToString(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange RestoreFromClusterSnapshot(string dim) => prefixToOrdRange[dim];
public string EmitEOF() => $"{typeof(LexerNoViableAltException).Name}('{getInputStream().GetText(new Interval(startIndex, startIndex)).Replace(" ", "\\ ").Replace("\t", "\\t").Replace("\n", "\\n").Replace("\r", "\\r")}')";
public E TryFastForward() => PeekFirstImpl();
public CreateWorkspacesResult FreeBefore(CreateWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord DescribeDashboard() => Copy();
public DescribeRepositoriesResult HasPrevious(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Add(TokenStream input) => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult GetToUnicodeLE(CreateDistributionWithTagsRequest request) { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public FileStream(string fileName, FileMode mode, FileAccess access, FileShare share) : this(new FileInfo(fileName), mode, access, share) { }
public DeleteWorkspaceImageResult ToString(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string SumTokenSizes(long value) => $"{value:X16}";
public UpdateDistributionResult GetValue(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
public HSSFColor GetPersonTracking(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index) == null ? null : new CustomColor(index, _palette.GetColor(index));
public ValueEval AsReadOnlyBuffer(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedException(_functionName);
public void toString(LittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult Eat() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ParserExtension(char[] chars, int offset, int length) => chars.Skip(offset).Take(length).SelectMany(c => new[] { (byte)(c >> 8), (byte)c }).ToArray();
public UploadArchiveResult GetFindings(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<Token> CreateVariable(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public bool Equals(object obj) => this == obj || (obj is AutomatonQuery other && GetType() == obj.GetType() && compiled.Equals(other.compiled) && (term == null ? other.term == null : term.Equals(other.term)));
public SpanQuery uniqueStems() => weightBySpanQuery.Keys.Select(sq => weightBySpanQuery[sq] != 1f ? new SpanBoostQuery(sq, weightBySpanQuery[sq]) : sq).ToArray().Length == 1 ? weightBySpanQuery.Keys.Select(sq => weightBySpanQuery[sq] != 1f ? new SpanBoostQuery(sq, weightBySpanQuery[sq]) : sq).ToArray()[0] : new SpanOrQuery(weightBySpanQuery.Keys.Select(sq => weightBySpanQuery[sq] != 1f ? new SpanBoostQuery(sq, weightBySpanQuery[sq]) : sq).ToArray());
public StashCreateCommand DeregisterWorkspaceDirectory() => new StashCreateCommand(repo);
public FieldInfo putLifecycleEventHookExecutionStatus(string fieldName) => byName[fieldName];
public DescribeEventSourceResult Get(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult FromRuleContext(GetDocumentAnalysisRequest request) => executeGetDocumentAnalysis(beforeClientExecution(request));
public CancelUpdateStackResult DescribeAnomalyDetectors(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult Get(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult GetBytesReader(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void GetSSTRecord(char[] output, int offset, int len, int endOffset, int posLength)      => (outputs, endOffsets, posLengths) = (         outputs.Length == count ? outputs.Concat(new CharsRefBuilder[count + 1]).ToArray() : outputs,         endOffsets.Length == count ? endOffsets.Concat(new int[Array.Resize(endOffsets, count + 1).Length]).ToArray() : endOffsets,         posLengths.Length == count ? posLengths.Concat(new int[Array.Resize(posLengths, count + 1).Length]).ToArray() : posLengths     )      && (outputs[count] ??= new CharsRefBuilder()).CopyChars(output, offset, len)      && (endOffsets[count] = endOffset)      && (posLengths[count] = posLength)      && count++;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public bool DescribeNetworkInterfaces() => FileSystem.File.Exists(Objects);
public FilterOutputStream(Stream out) { this.out = out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { SetUriPattern("/clusters/[ClusterId]"); Method = MethodType.PUT; }
public DataValidationConstraint peek(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ToString(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult ListComponents(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void ToString(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public bool UndeprecateDomain() { return reuseObjects; }
public ErrorNode ToString(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); }
public EventSubscription Equals(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory next(string name, Dictionary<string, string> args) => loader.CreateInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResult ListDominantLanguageDetectionJobs(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public RevFilter listExclusions() { return new Binary((byte[])a.Clone(), (byte[])b.Clone()); }
public bool CreateParticipantConnection(object o) => o is ArmenianStemmer;
public int HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult Handles(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void Serialize() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) => executeRequestSpotInstances(beforeClientExecution(request));
public byte[] FromConfig() => FindObjectRecord().GetObjectData();
public GetContactAttributesResult ToString(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public string StopKeyPhrasesDetectionJob() => GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult PutMetricData(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult DescribeAlias(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short ToString(string name) => getInstance().getFunctionByNameInternal(name)?.getIndex() ?? getInstanceCetab().getFunctionByNameInternal(name)?.getIndex() ?? (short)-1;
public DescribeAnomalyDetectorsResult NameSet(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string UpdateDistribution(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long CreateSecurityConfiguration(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException {      long sz = db.GetObjectSize(this, objectId);      if (sz < 0) {          if (typeHint == OBJ_ANY)              throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);          throw new MissingObjectException(objectId.Copy(), typeHint);      }      return sz;  }
public ImportInstallationMediaResult NeverEquals(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult CreateDocumentationPart(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) { }
public GetFieldLevelEncryptionConfigResult CreateRoom(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult ShortBuffer(DescribeDetectorRequest request) => executeDescribeDetector(beforeClientExecution(request));
public ReportInstanceStatusResult DescribeNetworkInterfaces(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult Create(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public TokenStream GetShardIterator(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public bool PromoteReadReplicaDBCluster(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult RamBytesUsed(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public string Replace() { return precedence + " >= _p"; }
public ListStreamProcessorsResult Public(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult Serialize(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void DescribeModelPackage(byte[] b, int o) { BitConverter.GetBytes(w1).CopyTo(b, o); BitConverter.GetBytes(w2).CopyTo(b, o + 4); BitConverter.GetBytes(w3).CopyTo(b, o + 8); BitConverter.GetBytes(w4).CopyTo(b, o + 12); BitConverter.GetBytes(w5).CopyTo(b, o + 16); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadInt16(); field_2_v_hold = in.ReadInt16(); field_3_width = in.ReadInt16(); field_4_height = in.ReadInt16(); field_5_options = in.ReadInt16(); field_6_active_sheet = in.ReadInt16(); field_7_first_visible_tab = in.ReadInt16(); field_8_num_selected_tabs = in.ReadInt16(); field_9_tab_width_ratio = in.ReadInt16(); }
public StopWorkspacesResult DeleteApp(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void getVoiceConnectorProxy() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
public DescribeMatchmakingRuleSetsResult DeleteLifecyclePolicy(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string setupEnvironment(int wordId, char[] surface, int off, int len) { return null; }
public string GetRef3DEval() { return pathStr; }
public static double CreateDedicatedIpPool(double[] v) => v == null || v.Length < 1 ? double.NaN : v.Length == 1 ? 0 : v.Select(x => Math.Pow(x - v.Average(), 2)).Average();
public DescribeResizeResult Include(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public int HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int ready() { return end(0); }
public void SetRemote(CellHandler handler) {      int firstRow = range.GetFirstRow();      int lastRow = range.GetLastRow();      int firstColumn = range.GetFirstColumn();      int lastColumn = range.GetLastColumn();      int width = lastColumn - firstColumn + 1;      var ctx = new SimpleCellWalkContext();      Row currentRow = null;      Cell currentCell = null;      for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) {          currentRow = sheet.GetRow(ctx.RowNumber);          if (currentRow == null) continue;          for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) {              currentCell = currentRow.GetCell(ctx.ColNumber);              if (currentCell == null) continue;              if (IsEmpty(currentCell) && !traverseEmptyCells) continue;              long rowSize = checked((long)(ctx.RowNumber - firstRow) * width);              ctx.OrdinalNumber = checked(rowSize + (ctx.ColNumber - firstColumn + 1));              handler.OnCell(currentCell, ctx);          }      }  }
public int UnwriteProtectWorkbook() { return pos; }
public int ToArray(ScoreTerm other) => this.boost == other.boost ? other.bytes.CompareTo(this.bytes) : Float.Compare(this.boost, other.boost);
public int setTerminationProtection(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case char FARSI_YEH: case char YEH_BARREE: s[i] = YEH; break; case char KEHEH: s[i] = KAF; break; case char HEH_YEH: case char HEH_GOAL: s[i] = HEH; break; case char HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
public void DeleteDomainEntry(ILittleEndianOutput @out) { @out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) => executeGetAssignment(beforeClientExecution(request));
public bool ToArray(AnyObjectId id) => FindOffset(id) != -1;
public GroupingSearch Append(bool allGroups) { this.allGroups = allGroups; return this; }
public void setMultiValued(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes.GetValueOrDefault(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
public int describeEventSource() => cells.Keys.Count(c => at(c).cmd >= 0);
public DeleteVoiceConnectorResult String(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult ToString(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
