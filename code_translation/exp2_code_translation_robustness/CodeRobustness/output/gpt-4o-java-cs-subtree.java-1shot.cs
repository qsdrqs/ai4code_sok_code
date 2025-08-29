public void WriteTo(LittleEndianOutput output) { output.WriteShort(); }
void ProcessBlockList<T>(BlockList<T> src) { if (src.Size == 0) srcDirIdx = 0; for (; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
void AddByte(byte b) { if (condition) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
DeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
() => (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string GetMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
POIFSFileSystem() { (true); _header.SetBATCount; _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void Address() { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto <); }
SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool MethodName() { lock (lockObj) { if (inStream == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || inStream.Available() > 0; } catch (Exception) { return false; } } }
public EscherOptRecord() { }
public virtual int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException(nameof(buffer), "buffer == null"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Write(string str) { Write(str ?? Convert.ToString((object)null)); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V() { return base.NextEntry.GetValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer){int available = bufferLength - bufferPosition;if (len <= available){if (len > 0)Array.Copy(buffer, bufferPosition, b, offset, len);bufferPosition += len;}else{if (available > 0){Array.Copy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;}if (useBuffer && len < bufferSize){Refill();if (bufferLength < len){Array.Copy(buffer, 0, b, offset, bufferLength);throw new EndOfStreamException("read past EOF: " + this);}else{Array.Copy(buffer, 0, b, offset, len);}}else{long after = bufferStart + bufferPosition + len;if (after > Length())throw new EndOfStreamException("read past EOF: " + this);ReadInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
void MethodName() { throw new NotSupportedException(); }
CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; StringTokenizer st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails; }
public HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string SheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).SheetName;
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
void MulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn((j + mbr.getFirstColumn())); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt); insertCell(br); } }
public static string EscapeString(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos)) >= 0) { sb.Append(input.Substring(apos, k - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(input.Substring(apos)).Append("\\E").ToString(); }
ByteBuffer(value) => throw new ReadOnlyBufferException();
public ArrayPtg(object values2d) { nColumns = ((object[])values2d)[0].Length; nRows = ((object[])values2d).Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = (object[])((object[])values2d)[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution; return ExecuteGetIceServerConfig(request); }
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + m_parentQuery.ToString() + ")"; }
void () { _refCount.IncrementAndGet(); }
UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int CalculateEntries() { return GetXBATEntriesPerBlock() * SomeValue; }
void Pow10(int pow10) { var tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(tp.DivisorShift); } else { MulShift(tp.Multiplicand, tp.MultiplierShift); } }
public virtual string ToString() { StringBuilder b = new StringBuilder(); int l = Length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); return this; }
void ProgressMonitor(pm) { }
void Method() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
public string MethodName() { return ""; }
(value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> List(CharsRef[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
void Pos(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = null; currentBlockUpto = (int)(pos & blockMask); }
(n) => { int s = Math.Min(Available(), Math.Max(0, n)); ptr += s; };
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
public virtual void Write(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding.Value); } }
(string str) => lastIndexOf;
bool E(object obj) { return addLastImpl; }
public void SomeMethod(string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public string TagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element) { subrecords.Add(element); }
public virtual bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
public DoubleMetaphoneFilter(TokenStream input) => new DoubleMetaphoneFilter();
public int GetInCoreLength() { return inCoreLength; }
void (bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public object this[int i] { get { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; } }
CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { "/repos"; SetMethod(); }
bool () { }
void Method() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.next; var previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException("Concurrent modification detected."); } }
MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
() { }
public static WeightedTerm WeightedTerm(Query query) => GetTerms(query, false);
ByteBuffer() { throw new System.ReadOnlyBufferException(); }
void ProcessBlocks(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public string GetString() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution; return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string MethodName() { return ""; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
EscherRecord(int index) => escherRecords[index];
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
DeleteSmsChannelResult(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
public TrackingRefUpdate() { }
void Print() { Console.WriteLine(b.ToString()); }
public QueryNode() { return GetChildren[0]; }
NotIgnoredFilter(workdirTreeIndex) { = workdirTreeIndex; }
AreaRecord(RecordInputStream in) { _field1FormatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult() { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange() { return prefixToOrdRange.Get(dim); }
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public object E() { return peekFirstImpl; }
CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public virtual NumberFormatIndexRecord() { return Copy; }
public DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new File(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string Value() { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public HSSFColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.getIndex()) { return HSSFColorPredefined.AUTOMATIC.getColor(); } byte[] b = _palette.getColor(index); return (b == null) ? null : new CustomColor(); }
ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Write() { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDBEngineVersionsResponseUnmarshaller.Instance;return Invoke<DescribeDBEngineVersionsResponse>(request, options);}
FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] SomeMethod(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public virtual List<IToken> List(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if(this==obj)return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; IEnumerator sqi = weightBySpanQuery.Keys.GetEnumerator(); i = 0; while (sqi.MoveNext()) { SpanQuery sq = (SpanQuery)sqi.Current; boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo GetFieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy; }
void AddOutput(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; System.Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; System.Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
public bool Exists() { return fs.Exists; }
FilterOutputStream() : this() { this.out = out; }
ScaleClusterRequest("/clusters/[ClusterId]"); setMethod(MethodType.PUT);
DataValidationConstraint(operatorType, formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups; }
void () { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public virtual bool MethodName() { }
public ErrorNode(Token badToken) { var t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(); return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory Create(string name, java.util.Map<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet; }
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public virtual bool Object(object o) { return; }
bool ProtectedHasArray() { return ProtectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
SolrSynonymParser(bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult() { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public virtual object GetObjectData() { return findObjectRecord.GetObjectData(); }
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string MethodName(string message, ObjectId changeId) { return insertId; }
public long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
ImportInstallationMediaResult() { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult() { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { var _ = in.ReadDouble(); }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
public virtual DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector; }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution; return ExecuteReportInstanceStatus(request); }
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
TokenStream() => new PortugueseStemFilter(input);
FtCblsSubRecord() { Reserved = new(); }
public virtual bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
public override string ToString() { return ""; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
DeleteLoadBalancerPolicyRequest(string loadBalancerName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(WindowProtectRecordOptions options) { }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void Encode(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public virtual StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
void Method() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string String(string wordId, string[] surface, int off, int len) { }
string () { }
public static double CalculateVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool PassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public virtual object GetEnd() { return end; }
public virtual void CellHandler(CellHandler handler){throw new System.NotImplementedException();}
() { }
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(Get()) : this.boost.CompareTo(other.boost);
public int ProcessCharacters(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
void LittleEndianOutput(out) { out.WriteShort(); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName) { this.AttributeName = attributeName; this.KeyType = keyType.ToString(); }
GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
public override bool AnyObjectId(AnyObjectId id) => FindOffset(id) != 0;
public GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void SetDimConfig(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes.ContainsKey(dimName) ? fieldTypes[dimName] : null; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
public virtual int GetSize() { IEnumerator<char> i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = At(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution; return ExecuteDeleteLifecyclePolicy(request); }
