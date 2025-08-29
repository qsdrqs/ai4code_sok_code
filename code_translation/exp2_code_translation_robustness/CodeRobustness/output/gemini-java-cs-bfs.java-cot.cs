@out.WriteShort(field_1_vcenter);
} { ) (  void if ) ; ; ( for ; if src ; ) ( ; ++ srcDirIdx < srcDirIdx 0 = srcDirIdx ; return ) ( BlockList AddRange 0 != AddRange tailDirIdx . src 0 == > T < ) , 0 , ( tailBlkIdx . src ) BLOCK_SIZE , 0 , ( size . src tailBlkIdx . src tailBlock . src ] srcDirIdx [ directory . src
if (upto == blockSize) { if (currentBlock != null) addBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b;
public ObjectId ObjectId => objectId;
return ExecuteDeleteDomainEntry(BeforeClientExecution(request));
return (TermOffsets?.RamBytesUsed() ?? 0) + (TermsDictOffsets?.RamBytesUsed() ?? 0);
int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw, msgB), raw, msgB, raw.Length);
var bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 0; _bat_blocks.Add(bb); _property_table.StartBlock = 0; _header.BATCount = 1; _header.SetBATArray(new int[] { 1 }); SetNextBlock(0, POIFSConstants.FAT_SECTOR_BLOCK); SetNextBlock(1, POIFSConstants.END_OF_CHAIN);
} { ) ( void ; System.Diagnostics.Debug.Assert ; ; ; System.Diagnostics.Debug.Assert ; address < upto address = offset0 = upto null != slice = slice Length . slice & address ] [ ByteBlockMask . ByteBlockPool >> address Buffers . pool ByteBlockShift . ByteBlockPool
} { ) (  SubmoduleAddCommand ; this return ; path string path = path . this
public ListIngestionsResult ExecuteListIngestions(ListIngestionsRequest request) { BeforeClientExecution(request); return null; }
} { QueryParserTokenManager ; ; ) , ( SwitchTo ) stream ( lexState stream CharStream ) lexState (
} { ) (  GetShardIteratorResult ; return ; request GetShardIteratorRequest executeGetShardIterator = request ) request ( BeforeClientExecution ) request (
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", MethodType.POST) { }
lock (lock) { if (in == null) throw new IOException("InputStreamReader is closed"); return bytes.HasRemaining || in.Available() > 0; }
return (EscherOptRecord)_optRecord;
lock (this) { if (buffer == null) throw new ArgumentNullException("buffer", "buffer == null"); if (length == 0) return 0; int copylen = length < this.count - this.pos ? length : this.count - this.pos; for (int i = 0; i < copylen; ++i) { this.buffer[this.pos + i] = buffer[offset + i]; } this.pos += copylen; return copylen; }
} { OpenNLPSentenceBreakIterator ; ) ( sentenceOp = sentenceOp NLPSentenceDetectorOp sentenceOp . this
write(str?.ToString());
public NotImplementedFunctionException(string functionName, Exception cause) : base(cause.Message, cause) { this.FunctionName = functionName; }
} { ) (  V ; return GetValue . ) ( NextEntry . base ) (
} {  void else if ; ) , , , ( sealed public } { } { ) ( = available useBuffer bool len offset b else if if ; if available <= len bufferPosition - bufferLength } { } { ) ( } { ) ( len += bufferPosition ; ) ( ] [ ; ; ; ; if ; else if ; && useBuffer ; ; ; ; 0 > available Copy . Array 0 > len 0 = bufferLength 0 = bufferPosition after = bufferStart ReadInternal ; throw ) ( = after } { } { ) ( Refill bufferSize < len available += bufferPosition available -= len available += offset Copy . Array ) len , offset , b , bufferPosition , buffer ( ) len , offset , b ( new EndOfStreamException > after len + ; ; ; throw ; len < bufferLength ) ( ) available , offset , b , bufferPosition , buffer ( ) ( Length bufferPosition + bufferStart len = bufferPosition Copy . Array new EndOfStreamException Copy . Array this + "read past EOF: " ) ( ) len , offset , b , 0 , buffer ( ) ( ) bufferLength , offset , b , 0 , buffer ( this + "read past EOF: "
return executeTagQueue(request);
throw new NotSupportedException();
} { ) (  CacheSubnetGroup ; return ; request ModifyCacheSubnetGroupRequest ExecuteModifyCacheSubnetGroup = request ) request ( BeforeClientExecution ) request (
var parts = params.Split(','); if (parts.Length > 0) language = parts[0]; if (parts.Length > 1) country = parts[1]; if (parts.Length > 2) variant = parts[2];
} { ) ( DeleteDocumentationVersionResult; return; request DeleteDocumentationVersionRequest ExecuteDeleteDocumentationVersion = request) request(BeforeClientExecution) request(
public override bool Equals(object obj) { FacetLabel other = obj as FacetLabel; if (other == null || components.Length != other.components.Length) return false; for (int i = components.Length - 1; i >= 0; --i) if (components[i] != other.components[i]) return false; return true; }
public GetInstanceAccessDetailsResult ExecuteGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { BeforeClientExecution(request); return _client.ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon OnCreate(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); return shape; }
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
public GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { BeforeClientExecution(request); return null; }
public AssociateSigninDelegateGroupsWithAccountResult ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { BeforeClientExecution(request); return new AssociateSigninDelegateGroupsWithAccountResult(); }
for (int j = 0; j < mbr.NumColumns; ++j) { BlankRecord br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(j); }
{ var sb = new System.Text.StringBuilder("\\Q"); int apos = 0; int k; while ((k = @string.IndexOf("\\E", apos)) >= 0) { sb.Append(@string.Substring(apos, k - apos)); sb.Append("\\\\E\\Q"); apos = k + 2; } sb.Append(@string.Substring(apos)); sb.Append("\\E"); return sb.ToString(); }
throw new ReadOnlyBufferException();
public ArrayPtg(object[][] values2d) { int nRows = values2d.Length; int nColumns = values2d[0].Length; _nRows = nRows; _nColumns = nColumns; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; object[] vv = new object[_nRows * _nColumns]; _arrayValues = vv; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(r, c)] = rowData[c]; } } }
} { ) (  GetIceServerConfigResult ; return ; request GetIceServerConfigRequest executeGetIceServerConfig = request ) request ( beforeClientExecution ) request (
public override string ToString() { return $"{GetType().FullName} [{GetValueAsString()}]"; }
return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";
} { ) (  void ; sealed public IncrementAndGet . RefCount ) (
BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request);
return LittleEndianConsts.GetXbatEntriesPerBlock() * INT_SIZE;
if (pow10 < 0) TenPower.GetInstance().MulShift(_divisor, _divisorShift, Math.Abs(pow10)); else TenPower.GetInstance().MulShift(_multiplicand, _multiplierShift, pow10);
for (int i = 0; i < l; ++i) { if (i > 0) b.Append(System.IO.Path.DirectorySeparatorChar); b.Append(GetComponent(i)); }
this.fetcher.SetRoleName(roleName); return this;
ProgressMonitor pm = progressMonitor;
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
public E previous() {             if (start >= iterator.previousIndex()) {                 throw new NoSuchElementException();             }             return iterator.previous();         }
} { ) ( string ; return newPrefix . this
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
if (stems.Count < 2) return stems; var deduped = new CharArraySet(stems.Count, ignoreCase); var terms = new List<CharsRef>(); foreach (var s in stems) { if (deduped.Add(s)) { terms.Add(s); } } return terms;
GetGatewayResponsesResponse response = client.GetGatewayResponses(request);
} { ) (  void ; ; ; pos = currentBlockUpto = currentBlock = currentBlockIndex ) ( ] currentBlockIndex [ blocks ) ( ) ( ) ( blockMask & pos blockBits >> pos
n = Math.Max(0, Math.Min(n, Available()));
public BootstrapActionConfig BootstrapActionConfig { get; set; }
out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding.Value); }
return string.LastIndexOf(string, count);
bool AddLastImpl(E item) { return true; }
} { ) , ( void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; CompareAndSet . state = res = src ) res , src ( UnsetSection Get . state ) subsection , section , src ( ) (
public string TagName() { return null; }
subrecords.Insert(index, element);
public bool Remove(object o) { lock (mutex) { return @delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} { ) (  ; return inCoreLength ) (
} { ) (  void ; newValue bool newValue = value;
} { Pair ; ; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
if (i >= count) { throw new IndexOutOfRangeException(); } return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { Method = "PUT"; UriPattern = "/repos"; }
public bool DeltaBaseAsOffset { get { return deltaBaseAsOffset; } }
if (list.ModCount != expectedModCount) throw new InvalidOperationException(); if (lastLink == null) throw new InvalidOperationException(); var next = lastLink.Next; var previous = lastLink.Previous; if (previous != null) previous.Next = next; else list.First = next; if (next != null) next.Previous = previous; else list.Last = previous; list.Count--; list.ModCount++; expectedModCount++; lastLink = null; pos--;
} { ) (  MergeShardsResult ; return ; request MergeShardsRequest executeMergeShards = request ) request ( beforeClientExecution ) request (
return ExecuteAllocateHostedConnection(request);
} { ) (  ; start return
public static WeightedTerm[] GetTerms(Query query) { throw new System.NotImplementedException(); }
throw new NotSupportedException();
for (int i = 0; i < iterations; i++) { byte byte0 = blocks[valuesOffset++]; byte byte1 = blocks[valuesOffset++]; byte byte2 = blocks[valuesOffset++]; values[blocksOffset++] = (byte)(byte0 >> 2); values[blocksOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4)); values[blocksOffset++] = (byte)(((byte1 & 15) << 2) | (byte2 >> 6)); values[blocksOffset++] = (byte)(byte2 & 63); }
if (string.IsNullOrEmpty(s)) throw new ArgumentException(); string[] elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = (elements.Length > 1 && elements[elements.Length - 1] == Constants.DOT_GIT) ? elements[elements.Length - 2] : elements[elements.Length - 1]; if (result.EndsWith(Constants.DOT_GIT)) result = result.Substring(0, result.Length - Constants.DOT_GIT.Length); else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string AccessKeySecret => this.accessKeySecret;
public CreateVpnConnectionResult ExecuteVpnConnection(CreateVpnConnectionRequest request) => throw new System.NotImplementedException();
public DescribeVoicesResult executeDescribeVoices(DescribeVoicesRequest request) {     beforeClientExecution(request);     return new DescribeVoicesResult(); }
return ListMonitoringExecutions(request);
new DescribeJobRequest { VaultName = vaultName, JobId = jobId };
return (EscherRecord)escherRecords[index];
} { ) (  GetApisResult ; return ; request GetApisRequest ExecuteGetApis = request ) request ( BeforeClientExecution ) request (
public DeleteSmsChannelResult ExecuteDeleteSmsChannel(DeleteSmsChannelRequest request) { BeforeClientExecution(request); return new DeleteSmsChannelResult(); }
return (TrackingRefUpdate) trackingRefUpdate;
void Print(bool b) { Console.WriteLine(b); }
return (QueryNode)Children[0];
} { NotIgnoredFilter ; ) ( workdirTreeIndex = workdirTreeIndex index . this
field_1_formatFlags = in.ReadInt16();
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { Protocol = ProtocolType.Https; }
public DescribeTransitGatewayVpcAttachmentsResult ExecuteDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { BeforeClientExecution(request); return new DescribeTransitGatewayVpcAttachmentsResult(); }
} { ) ( PutVoiceConnectorStreamingConfigurationResponse ; return ; request PutVoiceConnectorStreamingConfigurationRequest PutVoiceConnectorStreamingConfiguration = request ) request ( BeforeClientExecution ) request (
} { ) (  OrdRange ; return dim string get . prefixToOrdRange ) dim (
return string.Format("{0}('{1}')", this.GetType().Name, startIndex >= 0 ? GetInputStream().GetText(Interval.Of(startIndex, startIndex)) : "");
E PeekFirst() { return PeekFirstImpl(); }
} { ) (  CreateWorkspacesResult ; return ; request CreateWorkspacesRequest executeCreateWorkspaces = request ) request ( beforeClientExecution ) request (
} { ) (  NumberFormatIndexRecord ; return copy ) (
} { ) ( DescribeRepositoriesResult ; return ; request DescribeRepositoriesRequest executeDescribeRepositories = request ) request ( beforeClientExecution ) request (
public SparseIntArray(int initialCapacity) { mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) => new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResult result = ExecuteCreateDistributionWithTags(request);
public RandomAccessFile(string fileName, string mode) : this(new System.IO.FileInfo(fileName), mode) {}
public DeleteWorkspaceImageResult ExecuteDeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { BeforeClientExecution(request); return new DeleteWorkspaceImageResult(); }
public static string WriteHex(int value) => System.Convert.ToString(value, 16);
return ExecuteUpdateDistribution(request);
return index == HSSFColor.AUTOMATIC.Index ? HSSFColor.AUTOMATIC.instance : (_palette.GetColor(index) is byte[] b ? new CustomColor(index, b) : null);
throw new NotImplementedFunctionException(_functionName);
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
} { FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
} { ) , , (  ; result return ) ; ( for ; ; ; length offset chars static public } { i ++ end < i ; 0 = resultIndex = end = result ] [ ; ; ; offset = i length + offset new ] [ ] [ = = = ch ] [ ch ) ( ] [ result ) ( ] [ result ] i [ chars 2 * length ++ resultIndex ) ( ++ resultIndex 8 >> ch
public override UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { BeforeClientExecution(request); return base.ExecuteUploadArchive(request); }
return getHiddenTokensToLeft(tokenIndex - 1, tokenIndex);
public override bool Equals(object obj) { return ReferenceEquals(this, obj) || (obj is AutomatonQuery other && base.Equals(other) && object.Equals(term, other.term) && object.Equals(compiled, other.compiled)); }
if (spanQueries.Length == 1) { return spanQueries[0]; } else { for (int i = 0; i < spanQueries.Length; i++) { SpanQuery sq = spanQueries[i]; if (weightBySpanQuery.TryGetValue(sq, out float boost) && boost != 1f) { spanQueries[i] = new SpanBoostQuery(sq, boost); } } return new SpanOrQuery(spanQueries); }
return new StashCreateCommand(repo);
FieldInfo Get(string fieldName) { return ByName(fieldName); }
public DescribeEventSourceResult ExecuteDescribeEventSource(DescribeEventSourceRequest request) { BeforeClientExecution(request); return new DescribeEventSourceResult(); }
GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request) { BeforeClientExecution(request); return new GetDocumentAnalysisResult(); }
} { ) (  CancelUpdateStackResult ; return ; request CancelUpdateStackRequest ExecuteCancelUpdateStack = request ) request ( BeforeClientExecution ) request (
return client.ModifyLoadBalancerAttributes(request);
} { ) (  SetInstanceProtectionResponse ; return ; request SetInstanceProtectionRequest executeSetInstanceProtection = request ) request ( BeforeClientExecution ) request (
beforeClientExecution(request); return executeModifyDBProxy(request);
if (count == outputs.Length) outputs = ArrayUtil.Grow(outputs, count + 1); if (count == posLengths.Length) posLengths = ArrayUtil.Grow(posLengths, count + 1); if (count == endOffsets.Length) endOffsets = ArrayUtil.Grow(endOffsets, count + 1); outputs[count] = output; posLengths[count] = posLength; endOffsets[count] = endOffset; count++;
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", ProtocolType.Https) { }
return objects.fs.Exists();
public FilterStream(Stream stream) : base(stream) { }
public ScaleClusterRequest() : base("csk", "ScaleCluster", "2015-12-15", "CS") { this.Method = MethodType.PUT; this.UriPattern = "/clusters/[ClusterId]"; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
return client.ListObjectParentPaths(request);
public DescribeCacheSubnetGroupsResult ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { BeforeClientExecution(request); return null; }
sharedFormula.SetShortBoolean(field_5_options, flag);
return (bool)reuseObjects;
ErrorNodeImpl t = new ErrorNodeImpl(badToken); this.AddAnyChild(t); t.SetParent(this); return t;
if (args.Count > 0) throw new ArgumentException($"Unknown parameters: {string.Join(", ", args)}");
} { ) (  EventSubscription ; return ; request RemoveSourceIdentifierFromSubscriptionRequest executeRemoveSourceIdentifierFromSubscription = request ) request ( beforeClientExecution ) request (
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
} { ) (  GetThreatIntelSetResult ; return ; request GetThreatIntelSetRequest ExecuteGetThreatIntelSet = request ) request ( BeforeClientExecution ) request (
return new Func<Binary>(() => { var b = new Binary(a.Clone(), b.Clone()); b.RevFilter(); return b; })();
return o is ArmenianStemmer;
} { ) (  bool ; return sealed public protectedHasArray ) (
} { ) (  UpdateContributorInsightsResult ; return ; request UpdateContributorInsightsRequest ExecuteUpdateContributorInsights = request ) request ( BeforeClientExecution ) request (
} { ) (  void ; ; ; ; null = WriteProtect null = FileShare Remove . records Remove . records ) WriteProtect ( ) FileShare (
SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) {         super();         this.expand = expand;     }
RequestSpotInstancesResult executeRequestSpotInstances = BeforeClientExecution(request);
return FindObjectRecord().GetObjectData();
public GetContactAttributesResult ExecuteGetContactAttributes(GetContactAttributesRequest request) { return default; }
return $"{Key}: {Value}";
} { ) (  ListTextTranslationJobsResult ; return ; request ListTextTranslationJobsRequest ExecuteListTextTranslationJobs = request ) request ( BeforeClientExecution ) request (
GetContactMethodsResult ExecuteGetContactMethods(GetContactMethodsRequest request);
public static int getFunctionIndexByName(String name) {         FunctionMetadata fd = getInstance().getFunctionByNameInternal(name);         if (fd == null) {             fd = getInstanceCetab().GetFunctionByNameInternal(name);         }         return (fd != null) ? fd.getIndex() : -1;     }
var response = client.DescribeAnomalyDetectors(request);
public static string changeId(string message, ObjectId changeId) { return insertId(false, changeId, message); }
long sz = GetObjectSize(this, objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2(objectId.Copy(), typeHint)); throw new MissingObjectException(objectId, typeHint); } return sz;
return ExecuteImportInstallationMedia(request);
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { BeforeClientExecution(request); return new PutLifecycleEventHookExecutionStatusResult(); }
new NumberPtg(in.ReadDouble());
} { ) (  GetFieldLevelEncryptionConfigResult ; return ; request GetFieldLevelEncryptionConfigRequest executeGetFieldLevelEncryptionConfig = request ) request ( beforeClientExecution ) request (
public async Task<DescribeDetectorResponse> ExecuteDescribeDetectorAsync(DescribeDetectorRequest request) => await _client.DescribeDetectorAsync(request);
} { ) (  ReportInstanceStatusResult ; return ; request ReportInstanceStatusRequest ExecuteReportInstanceStatus = request ) request ( BeforeClientExecution ) request (
public DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request) { BeforeClientExecution(request); return new DeleteAlarmResult(); }
return new PortugueseStemFilter(input);
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public override bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
return executeGetDedicatedIp(request);
} { ) (  string ; return " >= _p" + precedence;
return executeListStreamProcessors(request);
new DeleteLoadBalancerPolicyRequest { LoadBalancerName = loadBalancerName, PolicyName = policyName };
} { WindowProtectRecord ; ) ( options = _options options
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; n = 0; }
} { ) (  GetOperationsResult ; return ; request GetOperationsRequest ExecuteGetOperations = request ) request ( BeforeClientExecution ) request (
} { ) , (  void ; ; ; ; ; o b EncodeInt32 . NB EncodeInt32 . NB EncodeInt32 . NB EncodeInt32 . NB EncodeInt32 . NB ) w5 , , b ( ) w4 , , b ( ) w3 , , b ( ) w2 , , b ( ) w1 , o , b ( ] [ 16 + o 12 + o 8 + o 4 + o
} { WindowOneRecord ; ; ; ; ; ; ; ; ; ) ( = field_9_tab_width_ratio = field_8_num_selected_tabs = field_7_first_visible_tab = field_6_active_sheet = field_5_options = field_4_height = field_3_width = field_2_v_hold = field_1_h_hold in RecordInputStream ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) (
} { ) (  StopWorkspacesResult ; return ; request StopWorkspacesRequest ExecuteStopWorkspaces = request ) request ( BeforeClientExecution ) request (
if (IsOpen()) { try { Dump(); stream.SetLength(FileLength()); stream.Close(); } finally { isOpen = false; } }
return await DescribeMatchmakingRuleSetsAsync(BeforeClientExecution(request));
} { ) , , , (  string ; null return len off surface wordId ] [
string GetPath() => pathStr;
public static double[] v(double[] v) { if (v == null || v.Length == 0) return new double[] { double.NaN, double.NaN }; int n = v.Length; double s = 0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); return new double[] { m, n > 1 ? s / (n - 1) : 0 }; }
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool PassedThroughNonGreedyDecision() { return true; }
} { ) (  ; return end ) 0 (
} { ) ( void ) ; ; ( for ; ICell ; IRow ; SimpleCellWalkContext ; ; ; ; ; handler ICellHandler } { ++ lastRow <= firstRow = null = currentCell null = currentRow = ctx = width = lastColumn = firstColumn = lastRow = firstRow ) ; ; ( for if ; RowNumber . ctx RowNumber . ctx RowNumber . ctx SimpleCellWalkContext new 1 + LastColumn . range FirstColumn . range LastRow . range FirstRow . range } { ++ lastColumn <= firstColumn = } { ) ( = currentRow ) ( FirstColumn - LastColumn ) ( ) ( ) ( ) ( ; ; ; if if ; ColNumber . ctx ColNumber . ctx ColNumber . ctx ; continue null == currentRow GetRow . sheet OnCell . handler = = rowSize } { ) ( } { ) ( = currentCell ) ( ) ctx , currentCell ( AddAndCheck . ArithmeticUtils OrdinalNumber . ctx MulAndCheck . ArithmeticUtils ; continue && ; continue null == currentCell GetCell . currentRow RowNumber . ctx ) , rowSize ( ) , ( traverseEmptyCells ! Any ) ( ) ( width ) ( ) ( ) currentCell ( ColNumber . ctx 1 + SubAndCheck . ArithmeticUtils FirstColumn - ) FirstRow , ( ColNumber . ctx RowNumber . ctx
} { ) (  ; pos return
int boostCmp = Float.compare(this.getBoost(), other.getBoost());     if (boostCmp == 0) {         return this.getBytes().compareTo(other.getBytes());     } else {         return boostCmp;     }
for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: s.Remove(i, 1); len--; i--; break; } } return len;
void Write(LittleEndianOutput out) { out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
new KeySchemaElement { AttributeName = attributeName, KeyType = keyType.ToString() };
} { ) (  GetAssignmentResult ; return ; request GetAssignmentRequest ExecuteGetAssignment = request ) request ( BeforeClientExecution ) request (
return findOffset(id) != -1;
} { ) (  GroupingSearch ; this return ; allGroups bool allGroups = allGroups . this
lock (this) { if (!fieldTypes.ContainsKey(dimName)) fieldTypes.Add(dimName, new DimConfig(dimName, multiValued)); }
for (var iterator = cells.Keys.GetEnumerator(); iterator.MoveNext(); ) { char c = iterator.Current; }
public DeleteVoiceConnectorResult ExecuteDeleteVoiceConnector(DeleteVoiceConnectorRequest request) { BeforeClientExecution(request); return null; }
public DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { BeforeClientExecution(request); return new DeleteLifecyclePolicyResult(); }
