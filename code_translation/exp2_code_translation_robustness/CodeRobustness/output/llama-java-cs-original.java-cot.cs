public void Serialize(BinaryWriter out) { out.Write((ushort)field_1_vcenter); }
public void addAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void WriteByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) => executeDeleteDomainEntry(beforeClientExecution(request));
public long RamBytesUsed() => (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string GetFullMessage()  {      byte[] raw = buffer;      int msgB = RawParseUtils.TagMessage(raw, 0);      return msgB < 0 ? "" : RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length);  }
public POIFSFileSystem() { this(true); _header.setBATCount(1); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
public void Init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SetPath(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) => executeListIngestions(beforeClientExecution(request));
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) => executeGetShardIterator(beforeClientExecution(request));
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { SetMethod(MethodType.POST); }
public bool Ready() { lock (lock) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || inStream.Available() > 0; } catch (IOException) { return false; } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int Read(byte[] buffer, int offset, int length)  {     if (buffer == null)      {         throw new ArgumentNullException(nameof(buffer));     }     if (offset < 0 || length < 0 || offset + length > buffer.Length)      {         throw new ArgumentException("Invalid offset or length");     }     if (length == 0)      {         return 0;     }     int copylen = Math.Min(count - pos, length);     for (int i = 0; i < copylen; i++)      {         buffer[offset + i] = (byte)this.buffer[pos + i];     }     pos += copylen;     return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) => Write(str != null ? str : ((object)null).ToString());
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() => ((IEnumerator<KeyValuePair<K, V>>)this).Current.Value;
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer)  {     int available = bufferLength - bufferPosition;     if (len <= available)     {         if (len > 0)              Array.Copy(buffer, bufferPosition, b, offset, len);         bufferPosition += len;     }     else     {         if (available > 0)         {             Array.Copy(buffer, bufferPosition, b, offset, available);             offset += available;             len -= available;             bufferPosition += available;         }         if (useBuffer && len < bufferSize)         {             Refill();             if (bufferLength < len)             {                 Array.Copy(buffer, 0, b, offset, bufferLength);                 throw new EndOfStreamException("read past EOF: "  );             }             else             {                 Array.Copy(buffer, 0, b, offset, len);                 bufferPosition = len;             }         }         else         {             long after = bufferStart + bufferPosition + len;             if (after > Length())                 throw new EndOfStreamException("read past EOF: "  );             ReadInternal(b, offset, len);             bufferStart = after;             bufferPosition = 0;             bufferLength = 0;         }     } }
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void Remove() => throw new NotSupportedException();
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) => executeModifyCacheSubnetGroup(beforeClientExecution(request));
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; var st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public bool Equals(object obj) => obj is FacetLabel other && length == other.length && Enumerable.Range(0, length).All(i => components[i].Equals(other.components[i]));
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
public GetDashboardResult GetDashboard(GetDashboardRequest request) => executeGetDashboard(beforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request)  {      request = BeforeClientExecution(request);      return ExecuteAssociateSigninDelegateGroupsWithAccount(request);  }
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
public static string Quote(string str) => new StringBuilder().Append("\\Q").Append(Regex.Replace(str, @"\\E", m => "\\E\\\\Q")).Append("\\E").ToString();
public ByteBuffer PutInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) {      int nColumns = values2d[0].Length;      int nRows = values2d.Length;      _nColumns = (short)nColumns;      _nRows = (short)nRows;      Object[] vv = new Object[_nColumns * _nRows];      for (int r = 0; r < nRows; r++) {          Object[] rowData = values2d[r];          for (int c = 0; c < nColumns; c++) {              vv[getValueIndex(c, r)] = rowData[c];          }      }      _arrayValues = vv;      _reserved0Int = 0;      _reserved1Short = 0;      _reserved2Byte = 0;  }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() => $"{GetType().Name} [{GetValueAsString()}]";
public override string ToString() => $"ToChildBlockJoinQuery ({parentQuery.ToString()})";
public void incRef() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int GetNextXBATChainOffset() => GetXBATEntriesPerBlock() * LittleEndianConsts.IntSize;
public void MultiplyByPowerOfTen(int pow10) {      TenPower tp = TenPower.GetInstance(Math.Abs(pow10));      if (pow10 < 0) {          MulShift(tp.Divisor, tp.DivisorShift);      } else {          MulShift(tp.Multiplicand, tp.MultiplierShift);      } }
public override string ToString() => string.Join(System.IO.Path.DirectorySeparatorChar.ToString(), Enumerable.Range(0, length).Select(i => getComponent(i)));
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.Fetcher = fetcher; fetcher.RoleName = this.RoleName; return this; }
public void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E Previous() => iterator.PreviousIndex() >= start ? iterator.Previous() : throw new InvalidOperationException();
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length)  {     var stems = stem(word, length);     if (stems.Count < 2)          return stems;     var terms = new HashSet<CharsRef>(stems.Count, StringComparer.OrdinalIgnoreCase);     return stems.Where(s => terms.Add(s)).ToList(); }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) => executeGetGatewayResponses(beforeClientExecution(request));
public void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) => (long)(Math.Min(Available(), Math.Max(0, n)));  // however that last expression does not work since it does not modify ptr  // so here is a working single line  public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(BinaryWriter out)  {     out.Write((ushort)field_1_row);     out.Write((ushort)field_2_col);     out.Write((ushort)field_3_flags);     out.Write((ushort)field_4_shapeid);     out.Write((ushort)field_6_author.Length);     out.Write((byte)(field_5_hasMultibyte ? 0x01 : 0x00));     if (field_5_hasMultibyte)      {         byte[] bytes = Encoding.Unicode.GetBytes(field_6_author);         Array.Reverse(bytes);         out.Write(bytes);     }      else      {         byte[] bytes = Encoding.GetBytes(field_6_author);         out.Write(bytes);     }     if (field_7_padding.HasValue)      {         out.Write((byte)field_7_padding.Value);     } }
public int LastIndexOf(string value) => LastIndexOf(value, count);
public bool Add(E @object) { return AddLastImpl(@object); }
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareExchange(ref src, res)); }
public string GetTagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return ((ICollection<object>)delegate).Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long Length() => InCoreLength();
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) : this() { this.oldSource = oldSource; this.newSource = newSource; }
public int get(int i) { if (count <= i) throw new IndexOutOfRangeException($"Index {i} is out of range"); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public bool IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void Remove() {      if (expectedModCount == list.modCount) {          if (lastLink != null) {              Link<ET> next = lastLink.next;              Link<ET> previous = lastLink.previous;              next.previous = previous;              previous.next = next;              if (lastLink == link) {                  pos--;              }              link = previous;              lastLink = null;              expectedModCount++;              list.size--;              list.modCount++;          } else {              throw new InvalidOperationException();          }      } else {          throw new InvalidOperationException("Concurrent modification");      }  }
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) => executeAllocateHostedConnection(beforeClientExecution(request));
public int getBeginIndex() { return start; }
public static readonly WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer Compact() { throw new ReadOnlyBufferException(); }
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations)  {     for (int i = 0; i < iterations; ++i)      {         long byte0 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = byte0 >>> 2;         long byte1 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4);         long byte2 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte1 & 0x0F) << 2) | (byte2 >>> 6);         values[valuesOffset++] = byte2 & 0x3F;     } }
public string GetHumanishName() {      string s = GetPath();      if ("/".Equals(s) || "".Equals(s)) s = GetHost();      if (s == null) throw new ArgumentException();      string[] elements;      if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { '\\', '/', Path.DirectorySeparatorChar }, StringSplitOptions.RemoveEmptyEntries);      else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);      if (elements.Length == 0) throw new ArgumentException();      string result = elements[elements.Length - 1];      if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2];      else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);      return result;  }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request)  {      request = BeforeClientExecution(request);      return ExecuteDescribeNotebookInstanceLifecycleConfig(request);  }
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) => executeCreateVpnConnection(beforeClientExecution(request));
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) => executeDescribeVoices(beforeClientExecution(request));
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) => executeListMonitoringExecutions(beforeClientExecution(request));
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord getEscherRecord(int index) => escherRecords[index];
public GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) => executeDeleteSmsChannel(beforeClientExecution(request));
public TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
public void Print(bool b) => Print(b.ToString());
public QueryNode getChild() { return getChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = (ushort)in.ReadInt16(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => executeDescribeTransitGatewayVpcAttachments(beforeClientExecution(request));
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request)  {      request = BeforeClientExecution(request);      return ExecutePutVoiceConnectorStreamingConfiguration(request);  }
public OrdRange GetOrdRange(string dim) => prefixToOrdRange[dim];
public override string ToString() => $"{typeof(LexerNoViableAltException).Name}('{Utils.EscapeWhitespace(getInputStream().GetText(new Interval(startIndex, startIndex)), false)}')";
public E peek() => peekFirstImpl();
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) => executeCreateWorkspaces(beforeClientExecution(request));
public NumberFormatIndexRecord Clone() { return (NumberFormatIndexRecord)Copy(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) => ExecuteDescribeRepositories(BeforeClientExecution(request));
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request) => executeCreateDistributionWithTags(beforeClientExecution(request));
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) => ExecuteDeleteWorkspaceImage(BeforeClientExecution(request));
public static string ToHex(long value) => string.Concat(Enumerable.Repeat("0123456789ABCDEF", 16).Select(c => c[(int)(value % 16)]).Reverse().ToArray());
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) => executeUpdateDistribution(beforeClientExecution(request));
public HSSFColor GetColor(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index) == null ? null : new CustomColor(index, _palette.GetColor(index));
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedException(_functionName);
public void Serialize(BinaryWriter out) { out.Write((ushort)field_1_number_crn_records); out.Write((ushort)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length) =>      ToBigEndianUtf16BytesImpl(chars, offset, length);  private static byte[] ToBigEndianUtf16BytesImpl(char[] chars, int offset, int length)  {     byte[] result = new byte[length * 2];     int end = offset + length;     int resultIndex = 0;     for (int i = offset; i < end; ++i)      {         char ch = chars[i];         result[resultIndex++] = (byte)(ch >> 8);         result[resultIndex++] = (byte)ch;     }     return result; }
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) => ExecuteUploadArchive(BeforeClientExecution(request));
public List<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public bool Equals(object obj)  {     if (obj == this) return true;     if (!base.Equals(obj) || GetType() != obj.GetType()) return false;     AutomatonQuery other = (AutomatonQuery)obj;     return compiled.Equals(other.compiled) && (term == null ? other.term == null : term.Equals(other.term)); }
public SpanQuery MakeSpanClause() => weightBySpanQuery.Select(x => x.Key.Boost(x.Value)).ToArray().Length == 1 ? weightBySpanQuery.Select(x => x.Key.Boost(x.Value)).ToArray()[0] : new SpanOrQuery(weightBySpanQuery.Select(x => x.Key.Boost(x.Value)).ToArray());
public StashCreateCommand StashCreate() => new StashCreateCommand(repo);
public FieldInfo FieldInfo(string fieldName) => byName[fieldName];
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) => executeGetDocumentAnalysis(beforeClientExecution(request));
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) => ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(request));
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) => executeSetInstanceProtection(beforeClientExecution(request));
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength)      => (         count == outputs.Length ? outputs = Array.Resize(outputs, count + 1) : 0,         count == endOffsets.Length ? endOffsets = new int[Array.Resize(endOffsets, Math.Max(1 + count, endOffsets.Length * 2))] : 0,         count == posLengths.Length ? posLengths = new int[Array.Resize(posLengths, Math.Max(1 + count, posLengths.Length * 2))] : 0,         outputs[count] ??= new CharsRefBuilder(),         outputs[count].CopyChars(output, offset, len),         endOffsets[count] = endOffset,         posLengths[count] = posLength,         count++     );
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public bool Exists() => fs.Exists(Objects);
public FilterOutputStream(System.IO.Stream out) { this.out = out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { SetUriPattern("/clusters/[ClusterId]"); SetMethod(MethodType.PUT); }
public DataValidationConstraint createTimeConstraint(int operatorType, string formula1, string formula2) { return DataValidationConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) => executeListObjectParentPaths(beforeClientExecution(request));
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => executeDescribeCacheSubnetGroups(beforeClientExecution(request));
public void SetSharedFormula(bool flag) { field_5_options = SharedFormula.SetShortBoolean(field_5_options, flag); }
public bool IsReuseObjects() { return reuseObjects; }
public ErrorNode AddErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request)  {      request = BeforeClientExecution(request);      return ExecuteRemoveSourceIdentifierFromSubscription(request);  }
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public RevFilter Clone() => new Binary(a.Clone() as object, b.Clone() as object);
public override bool Equals(object o) => o is ArmenianStemmer;
public bool HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) => executeUpdateContributorInsights(beforeClientExecution(request));
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) => executeRequestSpotInstances(beforeClientExecution(request));
public byte[] GetObjectData() => FindObjectRecord().GetObjectData();
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() => $"{GetKey()}: {GetValue()}";
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) => executeListTextTranslationJobs(beforeClientExecution(request));
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short LookupIndexByName(string name) =>      (short)(GetInstance().GetFunctionByNameInternal(name)?.GetIndex() ??      (GetInstanceCetab().GetFunctionByNameInternal(name)?.GetIndex() ?? -1));
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) => executeDescribeAnomalyDetectors(beforeClientExecution(request));
public static string InsertId(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long GetObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException {      long sz = db.GetObjectSize(this, objectId);      if (sz < 0) {          if (typeHint == OBJ_ANY)              throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);          throw new MissingObjectException(objectId.Copy(), typeHint);      }      return sz;  }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) => executeImportInstallationMedia(beforeClientExecution(request));
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request)  {      request = BeforeClientExecution(request);      return ExecutePutLifecycleEventHookExecutionStatus(request);  }
public NumberPtg(LittleEndianInput @in) : this(@in.ReadDouble()) { }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) => ExecuteDeleteAlarm(BeforeClientExecution(request));
public TokenStream Create(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) => executeGetDedicatedIp(beforeClientExecution(request));
public override string ToString() => precedence + " >= _p";
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void CopyRawTo(byte[] b, int o) =>      BitConverter.GetBytes(w1).CopyTo(b, o);     BitConverter.GetBytes(w2).CopyTo(b, o + 4);     BitConverter.GetBytes(w3).CopyTo(b, o + 8);     BitConverter.GetBytes(w4).CopyTo(b, o + 12);     BitConverter.GetBytes(w5).CopyTo(b, o + 16);
public WindowOneRecord(RecordInputStream in)  {     field_1_h_hold = in.ReadInt16();      field_2_v_hold = in.ReadInt16();      field_3_width = in.ReadInt16();      field_4_height = in.ReadInt16();      field_5_options = in.ReadInt16();      field_6_active_sheet = in.ReadInt16();      field_7_first_visible_tab = in.ReadInt16();      field_8_num_selected_tabs = in.ReadInt16();      field_9_tab_width_ratio = in.ReadInt16(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) => executeStopWorkspaces(beforeClientExecution(request));
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) => executeDescribeMatchmakingRuleSets(beforeClientExecution(request));
public string GetPronunciation(int wordId, char[] surface, int off, int len) => null;
public string GetPath() { return pathStr; }
public static double Devsq(double[] v) => v == null || v.Length < 1 ? double.NaN : v.Length == 1 ? 0 : v.Select(x => (x - v.Average()) * (x - v.Average())).Sum();
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) => ExecuteDescribeResize(BeforeClientExecution(request));
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int End() { return End(0); }
public void Traverse(CellHandler handler)  {     int firstRow = range.GetFirstRow();     int lastRow = range.GetLastRow();     int firstColumn = range.GetFirstColumn();     int lastColumn = range.GetLastColumn();     int width = lastColumn - firstColumn + 1;     var ctx = new SimpleCellWalkContext();     Row currentRow = null;     Cell currentCell = null;     for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber)      {         currentRow = sheet.GetRow(ctx.RowNumber);         if (currentRow == null)              continue;         for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber)          {             currentCell = currentRow.GetCell(ctx.ColNumber);             if (currentCell == null)                  continue;             if (IsEmpty(currentCell) && !traverseEmptyCells)                  continue;             long rowSize = ArithmeticUtils.MulAndCheck((long)(ctx.RowNumber - firstRow), (long)width);             ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1));             handler.OnCell(currentCell, ctx);         }     } }
public int getReadIndex() { return pos; }
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : float.Compare(this.boost, other.boost);
public int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case char.FARSI_YEH: case char.YEH_BARREE: s[i] = char.YEH; break; case char.KEHEH: s[i] = char.KAF; break; case char.HEH_YEH: case char.HEH_GOAL: s[i] = char.HEH; break; case char.HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
public void Serialize(BinaryWriter out) { out.Write((ushort)_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) => executeGetAssignment(beforeClientExecution(request));
public bool HasObject(AnyObjectId id) => FindOffset(id) != -1;
public GroupingSearch setAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
public int getCellsVal() {      int size = 0;      foreach (var c in cells.Keys) {          Cell e = at(c);          if (e.cmd >= 0) size++;      }      return size;  }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) => executeDeleteLifecyclePolicy(beforeClientExecution(request));
