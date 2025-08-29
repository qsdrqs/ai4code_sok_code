public void Write(LittleEndianOutput @out) { @out.WriteShort(); }
void BlockList<T>(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void AddByte(byte b) { if (currentBlock == null || upto == blockSize) { if (currentBlock != null) addBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public DeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
public int RamBytesUsed() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetMessage() => RawParseUtils.Decode(GuessEncoding(), buffer, msgB = RawParseUtils.TagMessage, buffer.Length);
public POIFSFileSystem() { _header.setBATCount(true); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
void (address) { slice = pool.buffers[(int)(address >> ByteBlockPool.BYTE_BLOCK_SHIFT)]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand(string path) { this.path = path; }
public ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool HasRemaining() throws IOException { lock (lock) { if (in == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || in.Available() > 0; } catch (Exception e) { return false; } }
public EscherOptRecord() { }
public synchronized int Write(char[] buffer, int offset, int length)  {     if (buffer == null)      {         throw new ArgumentNullException(nameof(buffer), "buffer == null");     }     if (offset < 0 || length < 0 || offset + length > buffer.Length)      {         throw new ArgumentException("Invalid offset or length");     }     if (length == 0)      {         return 0;     }     int copylen = Math.Min(count - pos, length);     for (int i = 0; i < copylen; i++)      {         buffer[offset + i] = this.buffer[pos + i];     }     return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Write(string str) => Console.Write(str ?? string.Empty);
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) => FunctionName = functionName;
V() { return base.nextEntry.getValue(); }
public void ReadIntoBuffer(byte[] b, int offset, int len, bool useBuffer)  {     available = bufferLength - bufferPosition;      if (len <= available)      {         if (len > 0)              Array.Copy(buffer, bufferPosition, b, offset, len);         bufferPosition += len;      }      else      {         if (available > 0)          {             Array.Copy(buffer, bufferPosition, b, offset, available);             offset += available;              len -= available;              bufferPosition += available;          }          if (useBuffer && len < bufferSize)          {             Refill();              if (bufferLength < len)              {                 Array.Copy(buffer, 0, b, offset, bufferLength);                 throw new EndOfStreamException("read past EOF: " + this);              }              else              {                 Array.Copy(buffer, 0, b, offset, len);             }         }          else          {             after = bufferStart + bufferPosition + len;              if (after > Length())                  throw new EndOfStreamException("read past EOF: " + this);             ReadInternal(b, offset, len);              bufferStart = after;              bufferPosition = 0;              bufferLength = 0;          }     } }
public TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
void () => { throw new NotSupportedException(); }
public CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void SetParams(string params) { base.SetParams(params); language = country = variant = ""; var st = new StringTokenizer(params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return executeDeleteDocumentationVersion(request); }
public bool Equals(object obj) => obj is FacetLabel other && length == other.length && Enumerable.Range(0, length).All(i => components[i].Equals(other.components[i]));
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) => executeGetInstanceAccessDetails(beforeClientExecution(request));
return new HSSFPolygon(this, anchor) { Parent = this, Anchor = anchor };
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).SheetName;
GetDashboardResult(GetDashboardRequest request) => executeGetDashboard(beforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
void MulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt()); insertCell(br); } }
public static string Escape(string string) => new StringBuilder().Append("\\Q").Append(string.Replace("\\E", "\\\\E\\Q")).Append("\\E").ToString();
public ByteBuffer(byte[] value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[,] values2d) {      nColumns = values2d.GetLength(1);      nRows = values2d.GetLength(0);      _nColumns = (int)nColumns;      _nRows = (int)nRows;      object[] vv = new object[_nColumns * _nRows];      for (int r = 0; r < nRows; r++) {          for (int c = 0; c < nColumns; c++) {              vv[getValueIndex(c, r)] = values2d[r, c];          }      }      _arrayValues = vv;      _reserved0Int = 0;      _reserved1Short = 0;      _reserved2Byte = 0;  }
GetIceServerConfigResult(GetIceServerConfigRequest request) { request = beforeClientExecution; return ExecuteGetIceServerConfig(request); }
public override string ToString() => GetType().Name + " [" + GetValueAsString() + "]";
public override string ToString() => $"ToChildBlockJoinQuery ({parentQuery.ToString()})";
refCount.InterlockedIncrement();
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
public int getXBATEntriesPerBlock() { return getXBATEntriesPerBlock() * 4; }
void MulShiftByPow10(int pow10) => pow10 < 0 ? MulShift(TenPower.GetInstance(Math.Abs(pow10))._divisorShift) : MulShift(TenPower.GetInstance(Math.Abs(pow10))._multiplicand, TenPower.GetInstance(Math.Abs(pow10))._multiplierShift);
public string ToString() { var b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) b.Append(Path.DirectorySeparatorChar); } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher)  {      this.fetcher = fetcher;  }  public InstanceProfileCredentialsProvider setRoleName(string roleName)  {      // setRoleName implementation      return this;  }   // becomes   public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher)  {      this.fetcher = fetcher;  }  public InstanceProfileCredentialsProvider SetRoleName(string roleName)  {      // setRoleName implementation      return this;  }
void MethodName(ProgressMonitor pm) { }
void MethodName() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); throw new InvalidOperationException(); }
string () { return ""; }
(value) => { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> List(CharsRef[] word, int length) {      List<CharsRef> stems = Stem(word, length);      if (stems.Count < 2) {          return stems;      }      CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase);      List<CharsRef> deduped = new List<CharsRef>();      foreach (CharsRef s in stems) {          if (!terms.Contains(s)) {              deduped.Add(s);              terms.Add(s);          }      }      return deduped;  }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
void Seek(int pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = null; currentBlockUpto = (int)(pos & blockMask); }
(n) => { s = Math.Min(Available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { setBootstrapActionConfig(bootstrapActionConfig); }
public void Write(LittleEndianOutput @out)  {     @out.WriteShort(field_1_row);     @out.WriteShort(field_2_col);     @out.WriteShort(field_3_flags);     @out.WriteShort(field_4_shapeid);     @out.WriteShort((short)field_6_author.Length);     @out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00);     if (field_5_hasMultibyte)      {         StringUtil.PutUnicodeLE(field_6_author, @out);     }      else      {         StringUtil.PutCompressedUnicode(field_6_author, @out);     }     if (field_7_padding.HasValue)      {         @out.WriteByte((byte)field_7_padding.Value);     } }
public Func<string, int> LastIndexOf = (string s) => s.LastIndexOf(s);
bool AddLast(E object) => addLastImpl;
void (/* ConfigSnapshot */) UnsetSection(string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public string ToString() { return tagName; }
void Add(int index, SubRecord element) => subrecords.Add(element);
bool () { lock (mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter(TokenStream input) : base(input) { }
public int get() { return inCoreLength; }
void MyMethod(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
get { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPath = "/repos"; Method = "Post"; }
bool () { }
void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) pos--; link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else throw new InvalidOperationException(); } else throw new InvalidOperationException("Collection was modified"); }
public MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) => executeAllocateHostedConnection(beforeClientExecution(request));
() { }
public static readonly WeightedTerm WeightedTerm(Query query) => GetTerms(query, false);
public ByteBuffer() => throw new ReadOnlyBufferException();
void Transform(byte[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations)  {      for (int i = 0; i < iterations; ++i)      {          byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF);          values[valuesOffset++] = (byte)(byte0 >> 2);          byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF);          values[valuesOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4));          byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF);          values[valuesOffset++] = (byte)(((byte1 & 0x0F) << 2) | (byte2 >> 6));          values[valuesOffset++] = (byte)(byte2 & 0x3F);      }  }
string GetName() throws Exception {      string s = GetPath();      if ("/".Equals(s) || "".Equals(s)) s = GetHost();      if (s == null) throw new ArgumentException();      string[] elements;      if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s))          elements = s.Split(new[] { "\\" + Path.DirectorySeparatorChar, "/" }, StringSplitOptions.RemoveEmptyEntries);      else          elements = s.Split(new[] { "/+" }, StringSplitOptions.RemoveEmptyEntries);      if (elements.Length == 0) throw new ArgumentException();      string result = elements[elements.Length - 1];      if (Constants.DOT_GIT.Equals(result))          result = elements[elements.Length - 2];      else if (result.EndsWith(Constants.DOT_GIT_EXT))          result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);      return result;  }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
string () { return ""; }
public CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) => executeListMonitoringExecutions(beforeClientExecution(request));
public DescribeJobRequest(string vaultName, string jobId) { this.VaultName = vaultName; this.JobId = jobId; }
public EscherRecord EscherRecord(int index) { return escherRecords[index]; }
GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
public DeleteSmsChannelResult(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel; }
public TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() { return GetChildren()[0]; }
public NotIgnoredFilter(TreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() { return prefixToOrdRange[dim]; }
public string ToString() => $"{typeof(LexerNoViableAltException).Name}('{getInputStream().GetText(new Interval(startIndex, startIndex)).Replace(" ", Utils.EscapeWhitespace(false))}')";
public E peekFirst() { return peekFirstImpl; }
public CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord() { return copy; }
public DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
using System.IO; FileStream( string fileName, FileMode mode, FileAccess access ) = new FileStream( fileName, mode, access );
public DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHexString(long value) => new StringBuilder(16).AppendHex(value, 16, "").ToString();
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
public CustomColor(int index) {      if (index == HSSFColorPredefined.AUTOMATIC.Index) return HSSFColorPredefined.AUTOMATIC.Color;      byte[] b = _palette.GetColor(index);      return b == null ? null : new CustomColor(b);  }
public ValueEval(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedFunctionException(_functionName);
public void Write() { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
DescribeDBEngineVersionsResult() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this.fontIndex = fontIndex; }
public static ushort[] ToUshortArray(ushort[] chars, int offset, int length)  {     ushort[] result = new ushort[length * 2];      int end = offset + length;      int resultIndex = 0;      for (int i = offset; i < end; ++i)      {         ushort ch = chars[i];          result[resultIndex++] = (ushort)(ch >> 8);          result[resultIndex++] = (ushort)ch;      }      return result;  }
public UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
public IList GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) =>      obj == this ||      (obj != null &&       GetType() == obj.GetType() &&       base.Equals(obj) &&       compiled.Equals(((AutomatonQuery)obj).compiled) &&       (term == null ? ((AutomatonQuery)obj).term == null : term.Equals(((AutomatonQuery)obj).term)));
SpanQuery[] spanQueries = new SpanQuery[size]; int i = 0; foreach (var sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; spanQueries[i++] = boost != 1f ? new SpanBoostQuery(sq, boost) : sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
public StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy; }
public class MyClass {     private CharsRefBuilder[] outputs = new CharsRefBuilder[0];     private int[] endOffsets = new int[0];     private int[] posLengths = new int[0];     private int count = 0;      public void AddOutput(char[] output, int offset, int len, int endOffset, int posLength)     {         if (count == outputs.Length) outputs = (CharsRefBuilder[])Grow(outputs, count + 1);         if (count == endOffsets.Length) endOffsets = (int[])Grow(endOffsets, count + 1);         if (count == posLengths.Length) posLengths = (int[])Grow(posLengths, count + 1);         if (outputs[count] == null) outputs[count] = new CharsRefBuilder();         outputs[count].CopyChars(output, offset, len);         endOffsets[count] = endOffset;         posLengths[count] = posLength;         count++;     }      Array Grow(Array array, int newSize) =>          array.Length >= newSize ? array :          Array.CreateInstance(array.GetType().GetElementType(), System.Math.Max(newSize, array.Length * 2)); }  public class CharsRefBuilder {     public void CopyChars(char[] output, int offset, int len)     {         // implement CopyChars     } }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.Exists; }
public FilterOutputStream( ) { this.out = out; }
new ScaleClusterRequest("/clusters/[ClusterId]").setMethod(MethodType.PUT);
public DataValidationConstraint(OperatorType operatorType, string formula1, string formula2)      => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => executeDescribeCacheSubnetGroups(beforeClientExecution(request));
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
bool () { }
ErrorNode(Token badToken) => { var t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Map args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Keys)); }
public EventSubscription() { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Create(string name, Dictionary<string, string> args) => loader.CreateInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) => executeGetThreatIntelSet(beforeClientExecution(request));
public RevFilter() { return new Binary((a.Clone() as int[]), (b.Clone() as int[])); }
bool Equals(object o) { return false; }
bool() => protectedHasArray();
public UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
public object GetObjectData() { return findObjectRecord.GetObjectData(); }
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
public override string ToString() => $"{getKey}: {getValue()}";
public ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetIndex(string name) => GetInstance().GetFunctionByNameInternal(name)?.GetIndex() ?? GetInstanceCetab().GetFunctionByNameInternal(name)?.GetIndex() ?? -1;
public DescribeAnomalyDetectorsResult(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return executeDescribeAnomalyDetectors(request); }
public static string GetInsertIdString(string message, ObjectId changeId) => insertId;
int sz = db.GetObjectSize(objectId, typeHint); return sz < 0 ? throw typeHint == OBJ_ANY ? new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2) : new MissingObjectException(objectId.Copy(), typeHint) : sz;
public ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg() { this.Value = in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
public DeleteAlarmResult(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
public TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new(); }
public bool Remove(Object object) { lock (mutex) { return c.Remove(object); } }
public GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ""; }
ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
void Encode([Out] byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
void Close() throws IOException {      if (isOpen) {          isOpen = false;          try {              Dump();          } finally {              try {                  channel.Truncate(fileLength);              } finally {                  try {                      channel.Close();                  } finally {                      fos.Close();                  }              }          }      }
public DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public String(int wordId, string[] surface, int off, int len) { }
public string() { }
public static double MeanSquareError(double[] v) => v == null || v.Length < 1 ? double.NaN : v.Length == 1 ? 0 : v.Select(x => Math.Pow(x - v.Average(), 2)).Average();
public DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool() { return passedThroughNonGreedyDecision; }
public delegate object MyDelegate() { return end; }
void TraverseCells(CellHandler handler) {      firstRow = range.GetFirstRow();      lastRow = range.GetLastRow();      firstColumn = range.GetFirstColumn();      lastColumn = range.GetLastColumn();      width = lastColumn - firstColumn + 1;      var ctx = new SimpleCellWalkContext();      Row currentRow = null;      Cell currentCell = null;      for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) {          currentRow = sheet.GetRow(ctx.rowNumber);          if (currentRow == null) continue;          for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) {              currentCell = currentRow.GetCell(ctx.colNumber);              if (currentCell == null) continue;              if (IsEmpty(currentCell) && !traverseEmptyCells) continue;              rowSize = ArithmeticUtils.MulAndCheck((int)(ctx.rowNumber - firstRow), width);              ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, ctx.colNumber - firstColumn + 1);              handler.OnCell(currentCell, ctx);          }      }  }
() { }
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? this.bytes.CompareTo(other.bytes) : this.boost.CompareTo(other.boost);
public int ProcessArray(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
public void Write(LittleEndianOutput @out) { @out.WriteShort(); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName) { AttributeName = attributeName; KeyType = KeyType.ToString(); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Exists(AnyObjectId id) { return FindOffset(id) != -1; }
public GroupingSearch(bool allGroups) { this.allGroups = allGroups; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
{ var i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { Character c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
