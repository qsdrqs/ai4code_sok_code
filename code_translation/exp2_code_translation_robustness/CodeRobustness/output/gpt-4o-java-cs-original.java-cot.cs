public void Serialize(ILittleEndianOutput out) => out.WriteShort(field_1_vcenter);
public void AddAll(BlockList<T> src) { if (src.Size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
public void WriteByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId GetObjectId() => objectId;
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long RamBytesUsed() => ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0);
public string GetFullMessage() => { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); };
public POIFSFileSystem() { this(true); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void Init(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SetPath(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { this.Method = MethodType.POST; }
public bool Ready() { lock (lockObj) { if (inputStream == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || inputStream.Available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord GetOptRecord() => _optRecord;
public int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) { throw new ArgumentNullException("buffer"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) { Write(str != null ? str : ((object)null)?.ToString()); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() => base.NextEntry().Value;
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void Remove() => throw new NotSupportedException();
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) => ExecuteModifyCacheSubnetGroup(BeforeClientExecution(request));
public void SetParams(string params) { base.SetParams(params); language = country = variant = ""; var st = new StringTokenizer(params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) { return false; } if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).Sheetname;
public GetDashboardResult GetDashboard(GetDashboardRequest request) => ExecuteGetDashboard(BeforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string Quote(string input) {var sb = new System.Text.StringBuilder();sb.Append("\\Q");int apos = 0, k;while ((k = input.IndexOf("\\E", apos)) >= 0) {sb.Append(input.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q");apos = k + 2;}return sb.Append(input.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer PutInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() => GetType().Name + " [" + GetValueAsString() + "]";
public string ToString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void IncRef() => Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) => ExecuteUpdateConfigurationSetSendingEnabled(BeforeClientExecution(request));
public int GetNextXBATChainOffset() => GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
public void MultiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(tp._divisor, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
public override string ToString(){var b = new StringBuilder();var l = Length;b.Append(Path.DirectorySeparatorChar);for (int i = 0; i < l; i++){b.Append(GetComponent(i));if (i < l - 1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } var terms = new HashSet<CharsRef>(new CharsRefComparer(dictionary.IgnoreCase)); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) => SetBootstrapActionConfig(bootstrapActionConfig);
public void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
public int LastIndexOf(string str) => LastIndexOf(str, count);
public bool Add(E obj) => AddLastImpl(obj);
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Value; res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public string GetTagName() => tagName;
public void AddSubRecord(int index, SubRecord element) => subrecords.Insert(index, element);
public bool Remove(object o) { lock (mutex) { return Delegate().Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long Length() => InCoreLength();
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int Get(int i) { if (count <= i) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { this.UriPattern = "/repos"; this.Method = MethodType.PUT; }
public bool IsDeltaBaseAsOffset() => deltaBaseAsOffset;
public void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.Next; var previous = lastLink.Previous; next.Previous = previous; previous.Next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.Size--; list.ModCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException("Concurrent modification detected."); } }
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) => ExecuteAllocateHostedConnection(BeforeClientExecution(request));
public int GetBeginIndex() => start;
public static WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer Compact() => throw new ReadOnlyBufferException();
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { long byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; long byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); long byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public string GetHumanishName() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) => ExecuteDescribeNotebookInstanceLifecycleConfig(BeforeClientExecution(request));
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) => ExecuteCreateVpnConnection(BeforeClientExecution(request));
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord GetEscherRecord(int index) => escherRecords[index];
public GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) => ExecuteDeleteSmsChannel(BeforeClientExecution(request));
public TrackingRefUpdate GetTrackingRefUpdate() => trackingRefUpdate;
public void Print(bool b) => Print(b.ToString());
public QueryNode GetChild() => GetChildren()[0];
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream input) { field_1_formatFlags = input.ReadInt16(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { this.Protocol = ProtocolType.HTTPS; }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(request));
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) => ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request));
public OrdRange GetOrdRange(string dim) => prefixToOrdRange[dim];
public override string ToString() => $"{typeof(LexerNoViableAltException).Name}('{(startIndex >= 0 && startIndex < GetInputStream().Size ? Utils.EscapeWhitespace(GetInputStream().GetText(Interval.Of(startIndex, startIndex)), false) : "")}')";
public E Peek() => PeekFirstImpl();
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) => ExecuteCreateWorkspaces(BeforeClientExecution(request));
public NumberFormatIndexRecord Clone() => Copy();
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) => ExecuteDescribeRepositories(BeforeClientExecution(request));
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request) => ExecuteCreateDistributionWithTags(BeforeClientExecution(request));
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToHex(long value) => { var sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) => ExecuteUpdateDistribution(BeforeClientExecution(request));
public HSSFColor GetColor(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index) is byte[] b ? new CustomColor(index, b) : null;
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedFunctionException(_functionName);
public void Serialize(LittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public SpanQuery MakeSpanClause() { SpanQuery[] spanQueries = new SpanQuery[Size()]; IEnumerator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreate() => new StashCreateCommand(repo);
public FieldInfo FieldInfo(string fieldName) => byName[fieldName];
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) => ExecuteGetDocumentAnalysis(BeforeClientExecution(request));
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) => ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(request));
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { var next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { var next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { this.Protocol = ProtocolType.HTTPS; }
public bool Exists() => fs.Exists(objects);
public FilterOutputStream(Stream outStream) { this.out = outStream; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { this.UriPattern = "/clusters/[ClusterId]"; this.Method = MethodType.PUT; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) => ExecuteListObjectParentPaths(BeforeClientExecution(request));
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void SetSharedFormula(bool flag) => field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
public bool IsReuseObjects() => reuseObjects;
public ErrorNode AddErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) => ExecuteRemoveSourceIdentifierFromSubscription(BeforeClientExecution(request));
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { this.Protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public override RevFilter Clone() => new Binary(a.Clone(), b.Clone());
public override bool Equals(object o) => o is ArmenianStemmer;
public bool HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) => ExecuteUpdateContributorInsights(BeforeClientExecution(request));
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
public byte[] GetObjectData() => FindObjectRecord().GetObjectData();
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() => GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short LookupIndexByName(string name) => (short)((GetInstance().GetFunctionByNameInternal(name) ?? GetInstanceCetab().GetFunctionByNameInternal(name))?.GetIndex() ?? -1);
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) => ExecuteDescribeAnomalyDetectors(BeforeClientExecution(request));
public static string InsertId(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) => ExecuteImportInstallationMedia(BeforeClientExecution(request));
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) => ExecutePutLifecycleEventHookExecutionStatus(BeforeClientExecution(request));
public NumberPtg(ILittleEndianInput input) : this(input.ReadDouble()) {}
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) => ExecuteGetFieldLevelEncryptionConfig(BeforeClientExecution(request));
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) => ExecuteDeleteAlarm(BeforeClientExecution(request));
public TokenStream Create(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() => precedence + " >= _p";
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadInt16(); field_2_v_hold = input.ReadInt16(); field_3_width = input.ReadInt16(); field_4_height = input.ReadInt16(); field_5_options = input.ReadInt16(); field_6_active_sheet = input.ReadInt16(); field_7_first_visible_tab = input.ReadInt16(); field_8_num_selected_tabs = input.ReadInt16(); field_9_tab_width_ratio = input.ReadInt16(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) => ExecuteStopWorkspaces(BeforeClientExecution(request));
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) => ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(request));
public string GetPronunciation(int wordId, char[] surface, int off, int len) => null;
public string GetPath() => pathStr;
public static double DevSq(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int End() => End(0);
public void Traverse(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), (long)width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
public int GetReadIndex() => pos;
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : this.boost.CompareTo(other.boost);
public int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
public void Serialize(LittleEndianOutput out) => out.WriteShort(_options);
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool HasObject(AnyObjectId id) => FindOffset(id) != -1;
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
public int GetCellsVal() {var i = cells.Keys.GetEnumerator();int size = 0;while (i.MoveNext()) {char c = i.Current;Cell e = At(c);if (e.cmd >= 0) {size++;}}return size;}
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) => ExecuteDeleteVoiceConnector(BeforeClientExecution(request));
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) => ExecuteDeleteLifecyclePolicy(BeforeClientExecution(request));
