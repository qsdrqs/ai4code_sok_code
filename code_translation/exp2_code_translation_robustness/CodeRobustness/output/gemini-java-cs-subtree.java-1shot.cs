out.WriteShort();
public void AddAll<T>(BlockList<T> src) { if (src.Size == 0) SrcDirIdx = 0; for (; SrcDirIdx < src.TailDirIdx; SrcDirIdx++) AddAll(src.Directory[SrcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
void method(object b){if(upto==blockSize){if(currentBlock!=null){addBlock(currentBlock);}currentBlock=new object[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId() { }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public string String() { byte[] raw = buffer; msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { _header.SetBATCount(); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void address(int address){slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < );}
SubmoduleAddCommand SubmoduleAddCommand(string path){_path = path; return this;}
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIteratorResult(GetShardIteratorRequest request){request = BeforeClientExecution(request); return executeGetShardIterator;}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool Ready() { lock (@lock) { if (@in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || @in.Available() > 0; } catch { return false; } } }
internal EscherOptRecord(){}
[MethodImpl(MethodImplOptions.Synchronized)] public int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer", "buffer == null"); } if (offset < 0 || length < 0 || length > buffer.Length - offset) { throw new IndexOutOfRangeException(); } if (length == 0) { return 0; } int copylen = Math.Min(count - pos, length); Array.Copy(this.buffer, pos, buffer, offset, copylen); return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Method(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { }
public virtual V GetValue() { return base.NextEntry.GetValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer){ int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this.ToString()); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this.ToString()); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution; return ExecuteTagQueue(request); }
void Method() { throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
void setParams(string @params){ base.setParams(@params); language = country = variant = ""; java.util.StringTokenizer st = new java.util.StringTokenizer(@params, ","); if (st.hasMoreTokens()) language = st.nextToken(); if (st.hasMoreTokens()) country = st.nextToken(); if (st.hasMoreTokens()) variant = st.nextToken(); }
public DeleteDocumentationVersionResult DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (Length != other.Length) { return false; } for (i = Length - 1; ; i--) { if (!Components[i].Equals(other.Components[i])) { return false; } } return true; }
public virtual GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request = BeforeClientExecution(request);return executeGetInstanceAccessDetails;}
public HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.setParent; shape.setAnchor(anchor); shapes.Add(shape); onCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).SheetName; }
public virtual GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public void ProcessMulBlankRecord(MulBlankRecord mbr){for (int j = 0; j < mbr.NumColumns; j++){ BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br);}}
public static string Translate(){StringBuilder sb=new StringBuilder();sb.Append("\\Q");apos=0;;while((k=@string.IndexOf("\\E",apos))>=0){sb.Append(@string.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(@string.Substring(apos)).Append("\\E").ToString();}
ByteBuffer(sbyte[] value){throw new ReadOnlyBufferException();}
nColumns = ((object[][])values2d)[0].Length; nRows = ((object[][])values2d).Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = ((object[][])values2d)[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0;
public override GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request){request = beforeClientExecution; return executeGetIceServerConfig(request);}
public override string ToString(){return GetType().Name + " [" + GetValueAsString() + "]";}
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void Run() { Interlocked.Increment(ref refCount); }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
{ return GetXBATEntriesPerBlock() * ; }
void Pow10(int pow10){TenPower tp = TenPower.GetInstance(Math.Abs(pow10));if (pow10 < 0){MulShift(tp._divisor, tp._divisorShift);}else{MulShift(tp._multiplicand, tp._multiplierShift);}}
public virtual string ToString(){var b = new StringBuilder();b.Append(Path.DirectorySeparatorChar);for(var i = 0; i < length; i++){b.Append(GetComponent(i));if(i < length - 1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public virtual InstanceProfileCredentialsProvider SetRoleName(ECSMetadataServiceCredentialsFetcher fetcher, string roleName){ this.fetcher = fetcher; this.roleName = roleName; return this; }
internal virtual void Progress(ProgressMonitor pm) {}
internal void Skip() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
public virtual E E() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); throw new InvalidOperationException(); }
string Method() { return null; }
if (mValues.Take(mSize).Contains(value)) return -1;
public List<CharsRef> Deduplicate(char[] word, int length){var stems = Stem(word, length);if (stems.Count < 2) return stems;var terms = new CharArraySet(8, dictionary.IgnoreCase);var deduped = new List<CharsRef>();foreach (var s in stems){if (!terms.Contains(s)){deduped.Add(s);terms.Add(s);}}return deduped;}
public virtual GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return executeGetGatewayResponses; }
void Seek(long pos){ currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
(n) => { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { }
void Write(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
(string @string) => lastIndexOf
public bool AddLast(E obj) { return addLastImpl; }
void ( , string subsection ) { ConfigSnapshot , ; do { src = state ; res = UnsetSection ( src , section , subsection ) ; } while ( System.Threading.Interlocked.CompareExchange ( ref state , res , src ) != src ) ; }
public string TagName => tagName;
public void Add(int index, SubRecord element) { subrecords.Insert(index, element); }
public virtual bool Remove() { lock (mutex) { return Delegate().Remove(o); } }
public DoubleMetaphoneFilter(TokenStream input) : base(input) { }
public int InCoreLength { get { return inCoreLength; } }
void SetValue(bool newValue) {}
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
if (i >= entries.Count) throw new ArgumentOutOfRangeException(nameof(i)); return entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { throw new System.NotImplementedException(); }
bool () {}
public virtual void Remove(){if (m_expectedModCount == m_list.modCount){if (m_lastLink != null){Link next = m_lastLink.next;Link<ET> previous = m_lastLink.previous;next.previous = previous;previous.next = next;if (m_lastLink == m_link){m_pos--;}m_link = previous;m_lastLink = null;m_expectedModCount++;m_list.size--;m_list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}}
MergeShardsResult MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection; }
(  ) { }
public static WeightedTerm WeightedTerm(Query query){return getTerms(query, false);}
ByteBuffer() { throw new ReadOnlyBufferException(); }
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for (int i = 0; i < iterations; ++i){int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63;}}
public string GetHumanishName() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
String() { return; }
CreateVpnConnectionResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateVpnConnection ( request ) ; }
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public override ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){ request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public virtual EscherRecord EscherRecord(int index) { return escherRecords.Get(index); }
GetApisResult(GetApisRequest request) { request = BeforeClientExecution; return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
public QueryNode QueryNode => Children[0];
public NotIgnoredFilter(workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord(RecordInputStream in){ field_1_formatFlags = in.ReadShort; }
GetThumbnailRequest(ProtocolType.HTTPS);
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
OrdRange() { return prefixToOrdRange[dim]; }
public string GetMessage(){string symbol="";if(startIndex>=0&&startIndex<GetInputStream().Size){symbol=GetInputStream().GetText(Interval.Of(startIndex,startIndex));symbol=Utils.EscapeWhitespace(symbol,false);}return string.Format("{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);}
public E Peek() { return PeekFirstImpl; }
CreateWorkspacesResult CreateWorkspacesResult(){request = beforeClientExecution(request); return executeCreateWorkspaces(request);}
public virtual NumberFormatIndexRecord NumberFormatIndexRecord() => copy;
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResult CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new Sharpen.FilePath(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request_1){request_1 = beforeClientExecution(request_1); return executeDeleteWorkspaceImage;}
public static string ToHexString(long value) { return value.ToString("x16"); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public HSSFColor GetColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.Index) { return HSSFColorPredefined.AUTOMATIC.Color; } var b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
public ValueEval Evaluate(ValueEval operands, int srcRow, int srcCol) => throw new NotImplementedFunctionException(_functionName);
public void Write() { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(character, fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBytes(char[] chars, int offset, int length){byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i){char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch;} return result;}
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public IEnumerable<object> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (obj is null || GetType() != obj.GetType()) return false; var other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (!Equals(term, other.term)) return false; return true; }
var spanQueries = weightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult DescribeEventSourceResult(DescribeEventSourceRequest request) { request = BeforeClientExecution; return ExecuteDescribeEventSource(request); }
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return ExecuteModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy; }
if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool Exists { get { return fs.exists; } }
FilterOutputStream(Stream @out){this.out = @out;}
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.Put);
DVConstraint DataValidationConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResult ListObjectParentPaths() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
bool ( ) { }
ErrorNode ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(); return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public virtual RemoveSourceIdentifierFromSubscriptionResponse EventSubscription() { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o) { return false; }
bool Boolean() { return ProtectedHasArray(); }
UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = BeforeClientExecution; return ExecuteUpdateContributorInsights(request); }
records.Remove(fileShare); records.Remove(writeProtect); ;; writeProtect = null;
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
protected internal RequestSpotInstancesResult RequestSpotInstancesResult() { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
() => { return findObjectRecord.GetObjectData(); }
GetContactAttributesResult(GetContactAttributesRequest request) { request = BeforeClientExecution; return ExecuteGetContactAttributes(request); }
public override string ToString() { return Key + ": " + Value; }
public ListTextTranslationJobsResult ListTextTranslationJobs() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public virtual GetContactMethodsResult GetContactMethods(){request = BeforeClientExecution(request); return ExecuteGetContactMethods(request);}
public static int GetFunctionIndex(string name){var fd = GetInstance().GetFunctionByNameInternal(name) ?? GetInstanceCetab().GetFunctionByNameInternal(name);return fd?.GetIndex() ?? -1;}
DescribeAnomalyDetectorsResult (  DescribeAnomalyDetectorsRequest request ) { request = beforeClientExecution ; return executeDescribeAnomalyDetectors ( request ) ; }
public static string insert(string message, ObjectId changeId) { return insertId; }
sz = db.GetObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz;
ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { in.ReadDouble(); }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
ReportInstanceStatusResult(ReportInstanceStatusRequest request){ request = beforeClientExecution; return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
public override TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
public virtual GetDedicatedIpResult GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
String() { return; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(Options options) : base(options) {}
public UnbufferedCharStream(int bufferSize){data = new char[bufferSize];}
GetOperationsResult(GetOperationsRequest request){ request = beforeClientExecution(request); return executeGetOperations; }
public virtual void WriteTo(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.ReadShort(); field_2_v_hold = in1.ReadShort(); field_3_width = in1.ReadShort(); field_4_height = in1.ReadShort(); field_5_options = in1.ReadShort(); field_6_active_sheet = in1.ReadShort(); field_7_first_visible_tab = in1.ReadShort(); field_8_num_selected_tabs = in1.ReadShort(); field_9_tab_width_ratio = in1.ReadShort(); }
public StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request){request = beforeClientExecution;return executeStopWorkspaces(request);}
void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public String(int wordId, char[] surface, int off, int len) {}
public String() { }
public static void Calculate(double[] v){r = double.NaN;if (v != null && v.Length >= 1){m = 0;s = 0;n = v.Length;for (i = 0; i < n; i++){s += v[i];}m = s / n;s = 0;for (i = 0; i < n; i++){s += (v[i] - m) * (v[i] - m);}r = (n == 1) ? 0 : s;}}
DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public virtual bool PassedThroughNonGreedyDecision { get { return passedThroughNonGreedyDecision; } }
=> end;
public void Walk(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() { }
if (this.Boost == other.Boost) return other.Bytes.CompareTo(this.Bytes); else return this.Boost.CompareTo(other.Boost);
for (var i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len;
void WriteShort(LittleEndianOutput output) => output.WriteShort(default);
DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
KeySchemaElement(string attributeName, KeyType keyType) { setAttributeName(attributeName); setKeyType(keyType.ToString()); }
GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
public bool Has(AnyObjectId id){return FindOffset(id) != -1;}
public GroupingSearch WithAllGroups(bool allGroups){this.AllGroups = allGroups; return this;}
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
return size = cells.Keys.Count(c => at(c).cmd >= 0);
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution; return ExecuteDeleteLifecyclePolicy(request); }
