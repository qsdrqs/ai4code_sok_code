void writeShortToOutput(LittleEndianOutput out, short value) {     out.writeShort(value); }
void ProcessBlockList(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void AddByte(byte b) { if (true) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public DeleteDomainEntryResult(DeleteDomainEntryRequest request) => executeDeleteDomainEntry(beforeClientExecution(request));
return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string Decode() => msgB = RawParseUtils.tagMessage < 0 ? "" : RawParseUtils.Decode(GuessEncoding(), buffer, msgB, buffer.Length);
public POIFSFileSystem() {      _header.setBATCount(true);      _header.setBATArray(new int[] { 1 });      BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false);      bb.setOurBlockIndex(1);      _bat_blocks.Add(bb);      setNextBlock(0, POIFSConstants.END_OF_CHAIN);      setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK);      _property_table.setStartBlock(0);  }
void SetAddress(long address) { slice = pool.buffers[(int)(address >> ByteBlockPool.BYTE_BLOCK_SHIFT)]; Debug.Assert(slice != null); upto = (int)(address & ByteBlockPool.BYTE_BLOCK_MASK); offset0 = address; Debug.Assert(upto < 256); }
public SubmoduleAddCommand(string path) { this.path = path; return this; }
public ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool HasData() { lock (lockObj) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || inStream.Available() > 0; } catch { return false; } }
public EscherOptRecord() { }
public synchronized int Read([NotNull] char[] buffer, int offset, int length)  {     if (buffer == null)      {         throw new ArgumentNullException(nameof(buffer), "buffer == null");     }     if (offset < 0 || length < 0 || offset + length > buffer.Length)      {         throw new ArgumentException("Invalid offset or length");     }     if (length == 0)      {         return 0;     }     int copylen = Math.Min(count - pos, length);     for (int i = 0; i < copylen; i++)      {         buffer[offset + i] = this.buffer[pos + i];     }     return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void WriteString(string str) => Console.Write(str ?? string.Format("{0}", (object)null));
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { }
public V GetValue() => ((IDictionaryEnumerator)base.nextEntry).Value;
public void ReadIntoBuffer(byte[] b, int offset, int len, bool useBuffer) {     int available = bufferLength - bufferPosition;     if (len <= available)     {         if (len > 0)              Array.Copy(buffer, bufferPosition, b, offset, len);         bufferPosition += len;     }     else     {         if (available > 0)         {             Array.Copy(buffer, bufferPosition, b, offset, available);             offset += available;             len -= available;             bufferPosition += available;         }         if (useBuffer && len < bufferSize)         {             Refill();             if (bufferLength < len)             {                 Array.Copy(buffer, 0, b, offset, bufferLength);                 throw new EndOfStreamException("read past EOF: " + this);             }             else             {                 Array.Copy(buffer, 0, b, offset, len);             }         }         else         {             long after = bufferStart + bufferPosition + len;             if (after > Length())                 throw new EndOfStreamException("read past EOF: " + this);             ReadInternal(b, offset, len);             bufferStart = after;             bufferPosition = 0;             bufferLength = 0;         }     } }
public TagQueueResult(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
void () => throw new UnsupportedOperationException();
public CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; var st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return executeDeleteDocumentationVersion(request); }
bool Equals(object obj) => obj is FacetLabel other && length == other.length && Enumerable.Range(0, length).All(i => components[length - 1 - i].Equals(other.components[length - 1 - i]));
public GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.setParent(this); shape.setAnchor(anchor); shapes.Add(shape); onCreate(shape); return shape; }
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).SheetName;
GetDashboardResult(GetDashboardRequest request) => executeGetDashboard(beforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
void InsertMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn(j + mbr.GetFirstColumn()); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string EscapeRegex(string string)  {     StringBuilder sb = new StringBuilder();     sb.Append("\\Q");     int apos = 0;     int k;     while ((k = string.IndexOf("\\E", apos)) >= 0)      {         sb.Append(string.Substring(apos, k + 2)).Append("\\\\E\\Q");         apos = k + 2;     }     return sb.Append(string.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer(object value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[,] values2d)  {     nColumns = values2d.GetLength(1);      nRows = values2d.GetLength(0);      _nColumns = (int)nColumns;      _nRows = (int)nRows;      object[] vv = new object[_nColumns * _nRows];      for (int r = 0; r < nRows; r++)      {          for (int c = 0; c < nColumns; c++)          {              vv[getValueIndex(c, r)] = values2d[r, c];          }      }      _arrayValues = vv;      _reserved0Int = 0;      _reserved1Short = 0;      _reserved2Byte = 0;  }
public GetIceServerConfigResult(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public override string ToString() => GetType().Name + " [" + GetValueAsString() + "]";
public override string ToString() => $"ToChildBlockJoinQuery ({parentQuery.ToString()})";
Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
public int GetXBATEntriesPerBlockResult() { return GetXBATEntriesPerBlock(); }
void Pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(0, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
string ToString() { var b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) b.Append(Path.DirectorySeparatorChar); } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) => this.fetcher = fetcher;
void MethodName(ProgressMonitor pm) { }
void MethodName() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
public T Previous() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); else throw new InvalidOperationException(); }
public class CustomString {     public CustomString() {         return;     } }
( value ) { for ( int i = 0 ; i < mSize ; i ++ ) if ( mValues [ i ] == value ) return - 1 ; }
List<CharsRef> ListCharsRef(CharsRef[] word, int length) {      List<CharsRef> stems = Stem(word, length);      if (stems.Count < 2) {          return stems;      }      CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase);      List<CharsRef> deduped = new List<CharsRef>();      foreach (CharsRef s in stems) {          if (!terms.Contains(s)) {              deduped.Add(s);              terms.Add(s);          }      }      return deduped;  }
public GetGatewayResponsesResult(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(); }
void SetPosition(int pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = null; currentBlockUpto = (int)(pos & blockMask); }
( n ) { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { setBootstrapActionConfig(bootstrapActionConfig); }
void Write(LittleEndianOutput out) {      out.WriteShort(field_1_row);      out.WriteShort(field_2_col);      out.WriteShort(field_3_flags);      out.WriteShort(field_4_shapeid);      out.WriteShort((short)field_6_author.Length);      out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00));      if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); }      else { StringUtil.PutCompressedUnicode(field_6_author, out); }      if (field_7_padding.HasValue) { out.WriteByte((byte)field_7_padding.Value); }  }
(string) -> string.lastIndexOf(string, 0) // Not a good example, always returns -1 or string.length() - 1 if string is the substring
bool(E object) => addLastImpl;
void UnsetSection(object _, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareExchange(ref src, res)); }
string() { return tagName; }
BiConsumer<Integer, SubRecord> addSubrecord = (index, element) -> subrecords.add(element); // Could be equivalently written as a method reference if subrecords is in scope BiConsumer<Integer, SubRecord> addSubrecord = (index, element) -> this.subrecords.add(element);
bool () { lock (mutex) { return delegateObj.Remove(o); } }
public static DoubleMetaphoneFilter createDoubleMetaphoneFilter(TokenStream input) {     return new DoubleMetaphoneFilter(input); }
public int InCoreLength { get { return inCoreLength; } }
void MethodName(bool newValue) { }
Pair(ContentSource oldSource, ContentSource newSource) {      this.oldSource = oldSource;      this.newSource = newSource;  }
public T get(int i) { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") => Path = "/repos"; Method = HttpMethod.Post;
boolean methodName(parameters) { }
void Remove() {      if (expectedModCount == list.modCount) {          if (lastLink != null) {              Link next = lastLink.next;              Link previous = lastLink.previous;              next.previous = previous;              previous.next = next;              if (lastLink == link) {                  pos--;              }              link = previous;              lastLink = null;              expectedModCount++;              list.size--;              list.modCount++;          } else {              throw new InvalidOperationException();          }      } else {          throw new InvalidOperationException("Concurrent modification detected");      }  }
public MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection(request); }
access_modifier return_type method_name(parameter_list) { method_body }
public static WeightedTerm GetWeightedTerm(Query query) => GetTerms(query, false);
public ByteBuffer() { throw new ReadOnlyBufferException(); }
void TransformData(byte[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations)  {     for (int i = 0; i < iterations; ++i)      {         byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF);         values[valuesOffset++] = (byte)(byte0 >>> 2);         byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF);         values[valuesOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >>> 4));         byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF);         values[valuesOffset++] = (byte)(((byte1 & 0x0F) << 2) | (byte2 >>> 6));         values[valuesOffset++] = (byte)(byte2 & 0x3F);     } }
string GetName() throws Exception {      string s = GetPath();      if ("/".Equals(s) || "".Equals(s)) s = GetHost();      if (s == null) throw new ArgumentException();      string[] elements;      if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { '\\', '/', File.separatorChar }, StringSplitOptions.RemoveEmptyEntries);      else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);      if (elements.Length == 0) throw new ArgumentException();      string result = elements[elements.Length - 1];      if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2];      else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);      return result;  }
DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public String() {     // constructor body }
CreateVpnConnectionResult() { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult() { request = beforeClientExecution(request); return executeDescribeVoices(request); }
ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
EscherRecord(index) { return escherRecords[index]; }
GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
public DeleteSmsChannelResult(DeleteSmsChannelRequest request) => request = beforeClientExecution(request); return executeDeleteSmsChannel(request);
public TrackingRefUpdate() { }
Console.Write(b.ToString());
QueryNode() { return getChildren()[0]; }
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult() { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() { return prefixToOrdRange[dim]; }
string () { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E PeekFirst() => peekFirstImpl;
public CreateWorkspacesResult() { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord() { return copy; }
DescribeRepositoriesResult() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
FileStream ( string fileName , FileMode mode , FileAccess access )
public DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHexString(long value) => new StringBuilder(16).Append(value.ToString("X16")).ToString();
public UpdateDistributionResult(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
public HSSFColor(int index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index)?.ToCustomColor();
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedException(_functionName); }
public void Write() { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult() { DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this.fontIndex = fontIndex; }
public static byte[] ConvertCharsToBytes(char[] chars, int offset, int length)  {     byte[] result = new byte[length * 2];     int end = offset + length;     int resultIndex = 0;     for (int i = offset; i < end; ++i)      {         char ch = chars[i];         result[resultIndex++] = (byte)(ch >> 8);         result[resultIndex++] = (byte)ch;     }     return result; }
public UploadArchiveResult(UploadArchiveRequest request) => ExecuteUploadArchive(BeforeClientExecution(request));
List(tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public bool Equals(object obj) =>      this == obj ||      (obj != null &&       GetType() == obj.GetType() &&       ((AutomatonQuery)obj).compiled.Equals(compiled) &&       (term == null ? ((AutomatonQuery)obj).term == null : term.Equals(((AutomatonQuery)obj).term)));
SpanQuery[] spanQueries = new SpanQuery[size()];  int i = 0;  foreach (var sq in weightBySpanQuery.Keys)  {      float boost = weightBySpanQuery[sq];      SpanQuery query = (boost != 1f) ? new SpanBoostQuery(sq, boost) : sq;      spanQueries[i++] = query;  }  return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
public class StashCreateCommand {     public StashCreateCommand() {}          // Or, if it's meant to be a factory method     public static StashCreateCommand createStashCreateCommand() {         return new StashCreateCommand();     } }
FieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult() { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
ModifyDBProxyResult(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy(request); }
void AddOutput(char[] output, int offset, int len, int endOffset, int posLength)  {     if (count == outputs.Length)      {         outputs = Array.Resize(outputs, count + 1);     }     if (count == endOffsets.Length)      {         int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];         Array.Copy(endOffsets, 0, next, 0, count);         endOffsets = next;     }     if (count == posLengths.Length)      {         int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];         Array.Copy(posLengths, 0, next, 0, count);         posLengths = next;     }     if (outputs[count] == null)      {         outputs[count] = new CharsRefBuilder();     }     ((CharsRefBuilder)outputs[count]).CopyChars(output, offset, len);     endOffsets[count] = endOffset;     posLengths[count] = posLength;     count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
boolean exists() { return fs.exists(); }
public FilterOutputStream(OutputStream out) { this.out = out; }
new ScaleClusterRequest("/clusters/[ClusterId]").setMethod(MethodType.PUT);
public DataValidationConstraint(operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups(); }
void MethodName() { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool() { }
ErrorNode(Token badToken) => { var t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(); return t; }
public LatvianStemFilterFactory(Dictionary<string, object> args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Keys)); }
public EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory(Map<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public RevFilter() { return new Binary((object)a.Clone(), (object)b.Clone()); }
bool IsObjectNull(Object o) { return o == null; }
bool () { return ProtectedHasArray(); }
public UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
void MethodName() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(object dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult() { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public object GetObjectData() { return findObjectRecord.GetObjectData(); }
public GetContactAttributesResult(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
string () => { return getKey + ": " + getValue(); }
public ListTextTranslationJobsResult() { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static int GetFunctionIndex() {      FunctionMetadata fd = Instance.GetFunctionByNameInternal(name);      if (fd == null) {          fd = InstanceCetab.GetFunctionByNameInternal(name);          if (fd == null) return -1;      }      return fd.GetIndex();  }
public DescribeAnomalyDetectorsResult(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public static string GetInsertId(string message, ObjectId changeId) => insertId;
public int GetObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException {      int sz = db.GetObjectSize(objectId);      if (sz < 0) {          if (typeHint == OBJ_ANY)              throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);          throw new MissingObjectException(objectId.Copy(), typeHint);      }      return sz;  }
public ImportInstallationMediaResult() { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg() { this.value = in.ReadDouble(); }
GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
public ReportInstanceStatusResult(ReportInstanceStatusRequest request)  {      request = beforeClientExecution(request);      return executeReportInstanceStatus(request);  }
public DeleteAlarmResult(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
public TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord()  {     reserved = new TypeOfReserved[0]; // or some other initialization based on actual type of 'reserved' }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public String() {     // constructor body }
public ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void EncodeInt32s(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in)  {     field_1_h_hold = in.ReadShort();     field_2_v_hold = in.ReadShort();     field_3_width = in.ReadShort();     field_4_height = in.ReadShort();     field_5_options = in.ReadShort();     field_6_active_sheet = in.ReadShort();     field_7_first_visible_tab = in.ReadShort();     field_8_num_selected_tabs = in.ReadShort();     field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution(request); return executeStopWorkspaces(request); }
void Close() throws IOException {      if (isOpen) {          isOpen = false;          try {              Dump();          } finally {              try {                  channel.Truncate(fileLength);              } finally {                  try {                      channel.Close();                  } finally {                      fos.Close();                  }              }          }      }  }
public DescribeMatchmakingRuleSetsResult() { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public String(int wordId, string[] surface, int off, int len) { }
public class String  {     public String() { } }
public static double CalculateVariance(double[] v)  {     double r = double.NaN;      if (v != null && v.Length >= 1)      {         double m = 0;          double s = 0;          int n = v.Length;          for (int i = 0; i < n; i++)          {             s += v[i];          }         m = s / n;          s = 0;          for (int i = 0; i < n; i++)          {             s += Math.Pow((v[i] - m), 2);          }         r = (n == 1) ? 0 : s / (n - 1);      }     return r; }
DescribeResizeResult() { request = beforeClientExecution(request); return executeDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
() -> { return end; }
void TraverseCells(CellHandler handler)  {     firstRow = range.GetFirstRow();     lastRow = range.GetLastRow();     firstColumn = range.GetFirstColumn();     lastColumn = range.GetLastColumn();     width = lastColumn - firstColumn + 1;     SimpleCellWalkContext ctx = new SimpleCellWalkContext();     Row currentRow = null;     Cell currentCell = null;     for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber)      {         currentRow = sheet.GetRow(ctx.rowNumber);         if (currentRow == null)          {             continue;         }         for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber)          {             currentCell = currentRow.GetCell(ctx.colNumber);             if (currentCell == null)              {                 continue;             }             if (IsEmpty(currentCell) && !traverseEmptyCells)              {                 continue;             }             rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), (long)width);             ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (long)(ctx.colNumber - firstColumn + 1));             handler.OnCell(currentCell, ctx);         }     } }
access_modifier return_type method_name(parameter_list) { method_body }
public int CompareTo(ScoreTerm other) =>      this.boost == other.boost ? this.bytes.CompareTo(other.bytes) : float.Compare(this.boost, other.boost);
int TransformArray(char[] s, int len) {      for (int i = 0; i < len; i++) {          switch (s[i]) {              case char FARSI_YEH:              case char YEH_BARREE:                  s[i] = YEH;                  break;              case char KEHEH:                  s[i] = KAF;                  break;              case char HEH_YEH:              case char HEH_GOAL:                  s[i] = HEH;                  break;              case char HAMZA_ABOVE:                  len = Delete(s, i, len);                  i--;                  break;              default:                  break;          }      }      return len;  }
void writeShortToOutput(LittleEndianOutput out, short value) {     out.writeShort(value); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
bool Exists(AnyObjectId id) => findOffset(id) != null;
public GroupingSearch(bool allGroups) { this.allGroups = allGroups; }
public synchronized void ConfigureDimension(string dimName, bool v) {      DimConfig ft = fieldTypes.GetValue(dimName);      if (ft == null) {          ft = new DimConfig();          fieldTypes[dimName] = ft;      }      ft.multiValued = v;  }
{ var i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { Character c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request)  => ExecuteAndReturn(executeDeleteVoiceConnector(beforeClientExecution(request))); // Assuming ExecuteAndReturn is a helper method to handle return.
public DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
