public void Serialize(BinaryWriter out) => out.Write((ushort)field_1_vcenter);
public void AddAll(BlockList<T> src)  {     if (src.size == 0) return;     int srcDirIdx = 0;     for (; srcDirIdx < src.tailDirIdx; srcDirIdx++)          AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE);     if (src.tailBlkIdx != 0)          AddAll(src.tailBlock, 0, src.tailBlkIdx); }
public void WriteByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long RamBytesUsed() => (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string GetFullMessage()  {     byte[] raw = buffer;      int msgB = RawParseUtils.TagMessage(raw, 0);      return msgB < 0 ? "" : RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length);  }
public POIFSFileSystem() : this(true) { _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _batBlocks.Add(bb); SetNextBlock(0, POIFSConstants.EndOfChain); SetNextBlock(1, POIFSConstants.FatSectorBlock); _propertyTable.SetStartBlock(0); }
public void Init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand setPath(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) => executeListIngestions(beforeClientExecution(request));
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) => executeGetShardIterator(beforeClientExecution(request));
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") => Method = MethodType.POST;
public bool Ready() { lock (lockObj) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return byteBuffer.Remaining() > 0 || inStream.Available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord GetOptRecord() { return _optRecord; }
public synchronized int Read(byte[] buffer, int offset, int length)  {     if (buffer == null)      {         throw new ArgumentNullException(nameof(buffer));     }     if (offset < 0 || length < 0 || offset + length > buffer.Length)      {         throw new ArgumentException("Invalid offset or length");     }     if (length == 0)      {         return 0;     }     int copylen = Math.Min(count - pos, length);     for (int i = 0; i < copylen; i++)      {         buffer[offset + i] = (byte)this.buffer[pos + i];     }     pos += copylen;     return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) => this.sentenceOp = sentenceOp;
public void Print(string str) => Write(str ?? "null");
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V next() { return base.nextEntry().getValue(); }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer)  {     int available = bufferLength - bufferPosition;     if (len <= available)     {         if (len > 0)              Array.Copy(buffer, bufferPosition, b, offset, len);         bufferPosition += len;     }      else      {         if (available > 0)         {             Array.Copy(buffer, bufferPosition, b, offset, available);             offset += available;             len -= available;             bufferPosition += available;         }         if (useBuffer && len < bufferSize)         {             Refill();             if (bufferLength < len)             {                 Array.Copy(buffer, 0, b, offset, bufferLength);                 throw new EndOfStreamException("read past EOF: " + this);             }             else              {                 Array.Copy(buffer, 0, b, offset, len);                 bufferPosition = len;             }         }          else          {             long after = bufferStart + bufferPosition + len;             if (after > Length())                 throw new EndOfStreamException("read past EOF: " + this);             ReadInternal(b, offset, len);             bufferStart = after;             bufferPosition = 0;             bufferLength = 0;         }     } }
public TagQueueResult TagQueue(TagQueueRequest request) => executeTagQueue(beforeClientExecution(request));
public void Remove() => throw new NotSupportedException();
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; var st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public bool Equals(object obj) {      if (!(obj is FacetLabel other)) return false;      if (length != other.length) return false;      for (int i = length - 1; i >= 0; i--) {          if (!components[i].Equals(other.components[i])) return false;      }      return true;  }
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor)  {     HSSFPolygon shape = new HSSFPolygon(this, anchor);     shape.Parent = this;     shape.Anchor = anchor;     shapes.Add(shape);     OnCreate(shape);     return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetname();
public GetDashboardResult GetDashboard(GetDashboardRequest request) => executeGetDashboard(beforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request)  {     request = beforeClientExecution(request);     return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void AddMultipleBlanks(MulBlankRecord mbr)  {     for (int j = 0; j < mbr.GetNumColumns(); j++)      {         BlankRecord br = new BlankRecord();         br.SetColumn((short)(j + mbr.GetFirstColumn()));         br.SetRow(mbr.GetRow());         br.SetXFIndex(mbr.GetXFAt(j));         InsertCell(br);     } }
public static string Quote(string str) {     var sb = new StringBuilder();     sb.Append("\\Q");     int apos = 0;     int k;     while ((k = str.IndexOf("\\E", apos)) >= 0)     {         sb.Append(str.Substring(apos, k + 2)).Append("\\\\E\\Q");         apos = k + 2;     }     return sb.Append(str.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer putInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d)  {     int nColumns = values2d[0].Length;      int nRows = values2d.Length;      _nColumns = (short)nColumns;      _nRows = (short)nRows;      Object[] vv = new Object[_nColumns * _nRows];      for (int r = 0; r < nRows; r++)      {          Object[] rowData = values2d[r];          for (int c = 0; c < nColumns; c++)          {              vv[getValueIndex(c, r)] = rowData[c];          }      }      _arrayValues = vv;      _reserved0Int = 0;      _reserved1Short = 0;      _reserved2Byte = 0;  }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request)  {     request = beforeClientExecution(request);     return executeGetIceServerConfig(request); }
public override string ToString() => GetType().Name + " [" + GetValueAsString() + "]";
public override string ToString(string field) { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
public void IncRef() => Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int getNextXBATChainOffset() => getXBATEntriesPerBlock() * sizeof(int);
public void MultiplyByPowerOfTen(int pow10) => TenPower.GetInstance(Math.Abs(pow10)).Let(tp => pow10 < 0 ? MulShift(tp._divisor, tp._divisorShift) : MulShift(tp._multiplicand, tp._multiplierShift));
public override string ToString() => string.Join(File.separatorChar.ToString(), Enumerable.Range(0, length()).Select(i => getComponent(i)));
public InstanceProfileCredentialsProvider withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E Previous() => iterator.PreviousIndex() >= start ? iterator.Previous() : throw new InvalidOperationException();
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value) => Array.IndexOf(mValues, value, 0, mSize);
public List<CharsRef> uniqueStems(char[] word, int length)  {     List<CharsRef> stems = stem(word, length);     if (stems.Count < 2)      {         return stems;     }     var terms = new HashSet<CharsRef>(8, StringComparer.OrdinalIgnoreCase);     List<CharsRef> deduped = new List<CharsRef>();     foreach (CharsRef s in stems)      {         if (!terms.Contains(s))          {             deduped.Add(s);             terms.Add(s);         }     }     return deduped; }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) => executeGetGatewayResponses(beforeClientExecution(request));
public void setPosition(long pos) {      currentBlockIndex = (int)(pos >> blockBits);      currentBlock = blocks[currentBlockIndex];      currentBlockUpto = (int)(pos & blockMask);  }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) => SetBootstrapActionConfig(bootstrapActionConfig);
public void Serialize(ILittleEndianOutput out)      => out.WriteShort(field_1_row)     + out.WriteShort(field_2_col)     + out.WriteShort(field_3_flags)     + out.WriteShort(field_4_shapeid)     + out.WriteShort((short)field_6_author.Length)     + out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00)     + (field_5_hasMultibyte          ? StringUtil.PutUnicodeLE(field_6_author, out)          : StringUtil.PutCompressedUnicode(field_6_author, out))     + (field_7_padding.HasValue ? out.WriteByte((byte)field_7_padding.Value) : 0);
public int LastIndexOf(string string) => LastIndexOf(string, count);
public bool Add(E @object) => AddLastImpl(@object);
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Value; res = UnsetSection(src, section, subsection); } while (!state.CompareExchange(ref src, res)); }
public string GetTagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return ((ICollection)delegate).Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long Length() => inCoreLength();
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int get(int i) { if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPattern = "/repos"; Method = MethodType.PUT; }
public bool IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void Remove()  {     if (expectedModCount == list.modCount)      {         if (lastLink != null)          {             Link<ET> next = lastLink.next;             Link<ET> previous = lastLink.previous;             next.previous = previous;             previous.next = next;             if (lastLink == link)              {                 pos--;             }             link = previous;             lastLink = null;             expectedModCount++;             list.size--;             list.modCount++;         }          else          {             throw new InvalidOperationException();         }     }      else      {         throw new InvalidOperationException("Collection was modified; this operation is not allowed.");     } }
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int GetBeginIndex() { return start; }
public static WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer Compact() => throw new ReadOnlyBufferException();
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations)  {     for (int i = 0; i < iterations; ++i)      {         long byte0 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = byte0 >>> 2;         long byte1 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4);         long byte2 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6);         values[valuesOffset++] = byte2 & 63;     } }
public string GetHumanishName()  {     string s = GetPath();     if ("/".Equals(s) || "".Equals(s)) s = GetHost();     if (s == null) throw new ArgumentException();     string[] elements;     if ("file".Equals(Scheme) || LocalFile.IsMatch(s))          elements = s.Split(new[] { '\\', '/', Path.DirectorySeparatorChar }, StringSplitOptions.RemoveEmptyEntries);     else          elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);     if (elements.Length == 0) throw new ArgumentException();     string result = elements[elements.Length - 1];     if (Constants.DotGit.Equals(result))          result = elements[elements.Length - 2];     else if (result.EndsWith(Constants.DotGitExt))          result = result.Substring(0, result.Length - Constants.DotGitExt.Length);     return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request)  {      request = BeforeClientExecution(request);      return ExecuteDescribeNotebookInstanceLifecycleConfig(request);  }
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) => executeDescribeVoices(beforeClientExecution(request));
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord getEscherRecord(int index) => escherRecords[index];
public GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
public void Print(bool b) { Print(b.ToString()); }
public QueryNode getChild() { return getChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") => SetProtocol(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request)  {     request = BeforeClientExecution(request);     return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange getOrdRange(String dim) => prefixToOrdRange[dim];
public override string ToString() => $"{typeof(LexerNoViableAltException).Name}('{Utils.EscapeWhitespace(GetInputStream().GetText(new Interval(startIndex, startIndex)), false)}')";
public E peek() => peekFirstImpl();
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) => executeCreateWorkspaces(beforeClientExecution(request));
public NumberFormatIndexRecord Clone() { return Copy(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) => executeDescribeRepositories(beforeClientExecution(request));
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request)  {     request = BeforeClientExecution(request);     return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToHex(long value)  {     var sb = new StringBuilder(16);     WriteHex(sb, value, 16, "");     return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) => executeUpdateDistribution(beforeClientExecution(request));
public HSSFColor GetColor(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index)?.Let(b => new CustomColor(index, b));
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedException(_functionName);
public void Serialize(BinaryWriter out) => out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index);
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length) =>      Enumerable.Range(offset, length).Select(i => new { High = (byte)(chars[i] >> 8), Low = (byte)chars[i] })     .SelectMany(x => new byte[] { x.High, x.Low }).ToArray();
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) => executeUploadArchive(beforeClientExecution(request));
public List<Token> getHiddenTokensToLeft(int tokenIndex) => getHiddenTokensToLeft(tokenIndex, -1);
public bool Equals(object obj) =>      this == obj ||      (obj != null &&       GetType() == obj.GetType() &&       base.Equals(obj) &&       compiled.Equals(((AutomatonQuery)obj).compiled) &&       (term == null ? ((AutomatonQuery)obj).term == null : term.Equals(((AutomatonQuery)obj).term)));
public SpanQuery MakeSpanClause()  {     SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count];     int i = 0;     foreach (var sq in weightBySpanQuery.Keys)      {         float boost = weightBySpanQuery[sq];         if (boost != 1f)          {             sq = new SpanBoostQuery(sq, boost);         }         spanQueries[i++] = sq;     }     return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreate() => new StashCreateCommand(repo);
public FieldInfo FieldInfo(string fieldName) => byName[fieldName];
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) => executeDescribeEventSource(beforeClientExecution(request));
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength)  {     if (count == outputs.Length)      {         outputs = Array.Resize(outputs, count + 1);     }     if (count == endOffsets.Length)      {         int[] next = new int[Array.Resize(endOffsets, 0).Length + 1];         Array.Copy(endOffsets, next, count);         endOffsets = next;     }     if (count == posLengths.Length)      {         int[] next = new int[Array.Resize(posLengths, 0).Length + 1];         Array.Copy(posLengths, next, count);         posLengths = next;     }     if (outputs[count] == null)      {         outputs[count] = new CharsRefBuilder();     }     outputs[count].CopyChars(output, offset, len);     endOffsets[count] = endOffset;     posLengths[count] = posLength;     count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { ProtocolType = ProtocolType.HTTPS; }
public bool Exists() { return fs.Exists(@objects); }
public FilterOutputStream(Stream out) { this.out = out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") => (UriPattern, Method) = ("/clusters/[ClusterId]", MethodType.PUT);
public DataValidationConstraint createTimeConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request)  {      request = BeforeClientExecution(request);      return ExecuteListObjectParentPaths(request);  }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => executeDescribeCacheSubnetGroups(beforeClientExecution(request));
public void SetSharedFormula(bool flag) { field_5_options = SharedFormula.SetShortBoolean(field_5_options, flag); }
public bool IsReuseObjects() { return reuseObjects; }
public ErrorNode addErrorNode(Token badToken) {      ErrorNodeImpl t = new ErrorNodeImpl(badToken);      addAnyChild(t);      t.setParent(this);      return t;  }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request)  {      request = BeforeClientExecution(request);      return ExecuteRemoveSourceIdentifierFromSubscription(request);  }
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { ProtocolType = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request)  {     request = BeforeClientExecution(request);     return ExecuteGetThreatIntelSet(request); }
public RevFilter Clone() => new Binary(a.Clone(), b.Clone());
public override bool Equals(object o) => o is ArmenianStemmer;
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request)  {      request = beforeClientExecution(request);      return executeRequestSpotInstances(request);  }
public byte[] GetObjectData() => FindObjectRecord().GetObjectData();
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request)  {     request = BeforeClientExecution(request);     return ExecuteGetContactAttributes(request); }
public override string ToString() => GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request)  {     request = BeforeClientExecution(request);     return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short lookupIndexByName(string name) =>      (short)(GetInstance().GetFunctionByNameInternal(name)?.GetIndex() ??               (GetInstanceCetab().GetFunctionByNameInternal(name)?.GetIndex() ?? -1));
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long GetObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException {
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request)  {     request = beforeClientExecution(request);     return executeImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request)  {      request = BeforeClientExecution(request);      return ExecutePutLifecycleEventHookExecutionStatus(request);  }
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) { }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request)  {     request = BeforeClientExecution(request);     return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {      request = beforeClientExecution(request);      return executeReportInstanceStatus(request);  }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public TokenStream Create(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() { return precedence + " >= _p"; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void CopyRawTo(byte[] b, int o) {      NB.EncodeInt32(b, o, w1);      NB.EncodeInt32(b, o + 4, w2);      NB.EncodeInt32(b, o + 8, w3);      NB.EncodeInt32(b, o + 12, w4);      NB.EncodeInt32(b, o + 16, w5);  }
public WindowOneRecord(RecordInputStream in)  {     field_1_h_hold = in.ReadInt16();      field_2_v_hold = in.ReadInt16();      field_3_width = in.ReadInt16();      field_4_height = in.ReadInt16();      field_5_options = in.ReadInt16();      field_6_active_sheet = in.ReadInt16();      field_7_first_visible_tab = in.ReadInt16();      field_8_num_selected_tabs = in.ReadInt16();      field_9_tab_width_ratio = in.ReadInt16(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) => executeStopWorkspaces(beforeClientExecution(request));
public void Close()  {      if (isOpen)      {          isOpen = false;          try          {              Dump();          }          finally          {              try              {                  channel.Truncate(fileLength);              }              finally              {                  try                  {                      channel.Close();                  }                  finally                  {                      fos.Close();                  }              }          }      }  }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public string GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public string GetPath() { return pathStr; }
public static double Devsq(double[] v) => v == null || v.Length < 1 ? double.NaN : v.Length == 1 ? 0 : v.Select(x => x - v.Average()).Sum(x => x * x);
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int End() { return End(0); }
public void Traverse(CellHandler handler)  {     int firstRow = range.GetFirstRow();     int lastRow = range.GetLastRow();     int firstColumn = range.GetFirstColumn();     int lastColumn = range.GetLastColumn();     int width = lastColumn - firstColumn + 1;     SimpleCellWalkContext ctx = new SimpleCellWalkContext();     Row currentRow = null;     Cell currentCell = null;     for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber)      {         currentRow = sheet.GetRow(ctx.RowNumber);         if (currentRow == null)          {             continue;         }         for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber)          {             currentCell = currentRow.GetCell(ctx.ColNumber);             if (currentCell == null)              {                 continue;             }             if (IsEmpty(currentCell) && !traverseEmptyCells)              {                 continue;             }             long rowSize = checked((long)(ctx.RowNumber - firstRow) * width);             ctx.OrdinalNumber = checked(rowSize + (ctx.ColNumber - firstColumn + 1));             handler.OnCell(currentCell, ctx);         }     } }
public int GetReadIndex() { return pos; }
public int CompareTo(ScoreTerm other) =>      this.boost == other.boost ?      other.bytes.Get().CompareTo(this.bytes.Get()) :      Float.Compare(this.boost, other.boost);
public int Normalize(char[] s, int len)  {     for (int i = 0; i < len; i++)      {         switch (s[i])          {             case char FARSI_YEH:             case char YEH_BARREE:                 s[i] = YEH;                  break;             case char KEHEH:                 s[i] = KAF;                  break;             case char HEH_YEH:             case char HEH_GOAL:                 s[i] = HEH;                  break;             case char HAMZA_ABOVE:                 len = Delete(s, i, len);                 i--;                 break;             default:                 break;         }     }     return len; }
public void Serialize(BinaryWriter out) => out.Write((ushort)_options);
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool HasObject(AnyObjectId id) => FindOffset(id) != -1;
public GroupingSearch setAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) {     lock (this)     {         DimConfig ft = fieldTypes[dimName];         if (ft == null)         {             ft = new DimConfig();             fieldTypes[dimName] = ft;         }         ft.multiValued = v;     } }
public int getCellsVal() => cells.Keys.Where(c => at(c).cmd >= 0).Count();
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
