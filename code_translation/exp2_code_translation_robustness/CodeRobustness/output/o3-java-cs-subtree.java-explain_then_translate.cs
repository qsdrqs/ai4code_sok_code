void (LittleEndianOutput out){out.WriteShort();}
void AddAll(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
void Append(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
internal ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0);
public string GetFullMessage(){byte[] raw=buffer;int msgB=RawParseUtils.TagMessage(raw,0);if(msgB<0){return "";}return RawParseUtils.Decode(GuessEncoding(),raw,msgB,raw.Length);}
public POIFSFileSystem() : this(true) { _header.SetBATCount(); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void SetAddress(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < slice.Length); }
SubmoduleAddCommand Path(string path) { this.path = path; return this; }
ListIngestionsResult ListIngestions() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool CheckAvailable() { lock (lockObj) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || in.Available > 0; } catch { return false; } } }
EscherOptRecord() { }
public int Read(char[] buffer,int offset,int length){lock(this){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=count-pos<length?count-pos:length;for(int i=0;i<copylen;i++){buffer[offset+i]=this.buffer[pos+i];}return copylen;}}
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void SomeMethod(string str){write(str!=null?str:"null");}
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() { return base.nextEntry.Value; }
public void Read(byte[] b,int offset,int len,bool useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(buffer,0,b,offset,len);}}else{long after=bufferStart+bufferPosition+len;if(after>Length())throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
TagQueueResult TagQueue(TagQueueRequest request){request=BeforeClientExecution(request);return ExecuteTagQueue(request);}
void MethodName() { throw new NotSupportedException(); }
CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
void SetValues(string @params){base.SetParams(@params);language=country=variant="";var tokens=@params.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request=beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) { return false; } if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request=beforeClientExecution(request);return executeGetInstanceAccessDetails;}
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor){ HSSFPolygon shape=new HSSFPolygon(this,anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); onCreate(shape); return shape; }
string GetSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname; }
GetDashboardResult GetDashboard(GetDashboardRequest request){ request = BeforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void Process(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();j++){BlankRecord br=new BlankRecord();br.SetColumn((short)(j+mbr.GetFirstColumn()));br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt(j));InsertCell(br);} }
public static string Quote(string str){System.Text.StringBuilder sb=new System.Text.StringBuilder();sb.Append(@"\Q");int apos=0,k;while((k=str.IndexOf(@"\E",apos))>=0){sb.Append(str.Substring(apos,k+2-apos)).Append(@"\\E\Q");apos=k+2;}return sb.Append(str.Substring(apos)).Append(@"\E").ToString();}
public ByteBuffer(object value){throw new ReadOnlyBufferException();}
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[GetValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void IncrementRefCount() { System.Threading.Interlocked.Increment(ref refCount); }
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
() { return GetXBATEntriesPerBlock() * ; }
void AdjustScale(int pow10){TenPower tp=TenPower.getInstance(Math.Abs(pow10));if(pow10<0){mulShift(tp._divisor,tp._divisorShift);}else{mulShift(tp._multiplicand,tp._multiplierShift);}}
public override string ToString() { StringBuilder b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); return this; }
(ProgressMonitor pm) => { };
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E Get() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string () { return ; }
(value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
public List<CharsRef> ProcessStems(char[] word,int length){var stems=Stem(word,length);if(stems.Count<2)return stems;var terms=new CharArraySet(8,dictionary.IgnoreCase);var deduped=new List<CharsRef>();foreach(var s in stems)if(!terms.Contains(s)){deduped.Add(s);terms.Add(s);}return deduped;}
GetGatewayResponsesResult GetGatewayResponsesResult(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses; }
void (long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = ; currentBlockUpto = (int)(pos & blockMask); }
s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s;
public BootstrapActionDetail() { setBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(ILittleEndianOutput out1){out1.WriteShort(field_1_row);out1.WriteShort(field_2_col);out1.WriteShort(field_3_flags);out1.WriteShort(field_4_shapeid);out1.WriteShort(field_6_author.Length);out1.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out1);}else{StringUtil.PutCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.WriteByte((byte)field_7_padding);} }
(string @string) => lastIndexOf;
bool Add(E @object) { return addLastImpl; }
void UpdateConfig(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = unsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
string () { return tagName; }
void Add(int index, SubRecord element) { subrecords.Add(element); }
bool Remove(object o) { lock (mutex) { return Delegate().Remove(o); } }
DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input){return new DoubleMetaphoneFilter();}
long InCoreLength(){return inCoreLength;}
void (bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
object GetEntry(int i){if(i<0||i>=entries.Length)throw new IndexOutOfRangeException(i.ToString());return entries[i];}
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(); }
bool (  ) { }
void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException("Collection was modified; enumeration operation may not execute."); } }
MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() { }
public static WeightedTerm GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer() { throw new ReadOnlyBufferException(); }
static void ExtractValues(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
string GetRepositoryName() { string s = getPath(); if ("/".Equals(s) || "".Equals(s)) s = getHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = Regex.Split(s, @"[\\/]+"); else elements = Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
string () { return ; }
CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request){ request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request=BeforeClientExecution(request);return ExecuteListMonitoringExecutions();}
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord Get(int index) => escherRecords[index];
GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request){request=BeforeClientExecution(request);return ExecuteDeleteSmsChannel();}
internal TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() { return getChildren()[0]; }
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex){this.workdirTreeIndex=workdirTreeIndex;}
public AreaRecord(RecordInputStream inStream){ field_1_formatFlags = inStream.ReadInt16(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
private DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request=BeforeClientExecution(request);return ExecutePutVoiceConnectorStreamingConfiguration(request);}
OrdRange() { return prefixToOrdRange[dim]; }
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < InputStream.Size) { symbol = InputStream.GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
E PeekFirst() { return peekFirstImpl; }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
public DescribeRepositoriesResult DescribeRepositories() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
public HyphenatedWordsFilter Create() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult CreateDistributionWithTags() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new System.IO.FileInfo(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHex(long value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(); }
public HSSFColor HSSFColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}byte[] b=_palette.GetColor(index);return b==null?null:new CustomColor();}
ValueEval ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Serialize(){@out.Write((short)field_1_number_crn_records);@out.Write((short)field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(char character,int fontIndex){this._character=character;this._fontIndex=fontIndex;}
public static byte[] CharsToBytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
System.Collections.IList GetHiddenTokensToLeft(int tokenIndex){ return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if(ReferenceEquals(this,obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
SpanQuery BuildSpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; var sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
return byName[fieldName];
DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
GetDocumentAnalysisResult GetDocumentAnalysis() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request){ request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult SetInstanceProtection() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request){request = BeforeClientExecution(request); return ExecuteModifyDBProxy;}
void Add(char[] output,int offset,int len,int endOffset,int posLength){if(count==outputs.Length){Array.Resize(ref outputs,count+1);}if(count==endOffsets.Length){var next=new int[Oversize(1+count,sizeof(int))];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){var next=new int[Oversize(1+count,sizeof(int))];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { (); }
bool () { return fs.exists; }
public FilterOutputStream(System.IO.Stream @out){ this.@out = @out; }
var request = new ScaleClusterRequest("/clusters/[ClusterId]"); request.SetMethod(MethodType.PUT);
DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPaths() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
void UpdateOptions() { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool Method() { }
ErrorNodeImpl ErrorNode(Token badToken) { var t = new ErrorNodeImpl(badToken); addAnyChild(t); t.SetParent(); return t; }
public LatvianStemFilterFactory(IDictionary<string,string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
EventSubscription RemoveSourceIdentifierFromSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Create(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (object o) { return; }
bool() { return protectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void Method(){ records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
[]() { return findObjectRecord.GetObjectData(); }
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult GetContactMethods() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetFunctionIndex(string name){FunctionMetadata fd=GetInstance().GetFunctionByNameInternal(name);if(fd==null){fd=GetInstanceCetab().GetFunctionByNameInternal(name);if(fd==null){return -1;}}return fd.GetIndex();}
private DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string _(string message, ObjectId changeId){return insertId;}
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize; if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
ImportInstallationMediaResult ImportInstallationMedia() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
private PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request=BeforeClientExecution(request);return ExecutePutLifecycleEventHookExecutionStatus(request);}
NumberPtg() { @in.ReadDouble(); }
GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request){request=BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request){request = BeforeClientExecution(request); return ExecuteDeleteAlarm;}
public override TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[8]; }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request){ request = beforeClientExecution; return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName,string policyName){SetLoadBalancerName(loadBalancerName);SetPolicyName(policyName);}
public WindowProtectRecord(object options) { }
public UnbufferedCharStream(int bufferSize){data=new char[bufferSize];}
GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void WriteWords(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream inStream){ field_1_h_hold = inStream.ReadInt16(); field_2_v_hold = inStream.ReadInt16(); field_3_width = inStream.ReadInt16(); field_4_height = inStream.ReadInt16(); field_5_options = inStream.ReadInt16(); field_6_active_sheet = inStream.ReadInt16(); field_7_first_visible_tab = inStream.ReadInt16(); field_8_num_selected_tabs = inStream.ReadInt16(); field_9_tab_width_ratio = inStream.ReadInt16(); }
StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){ request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
string (int wordId, char[] surface, int off, int len) { }
String() { }
public static double Compute(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
public int End() { return end; }
void Walk(CellHandler handler){firstRow=range.GetFirstRow();lastRow=range.GetLastRow();firstColumn=range.GetFirstColumn();lastColumn=range.GetLastColumn();width=lastColumn-firstColumn+1;var ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.GetRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.GetCell(ctx.colNumber);if(currentCell==null){continue;}if(IsEmpty(currentCell)&&!traverseEmptyCells){continue;}var rowSize=ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber,firstRow),width);ctx.ordinalNumber=ArithmeticUtils.AddAndCheck(rowSize,(ctx.colNumber-firstColumn+1));handler.OnCell(currentCell,ctx);}}}
() { }
public int CompareTo(ScoreTerm other){return this.boost==other.boost?other.bytes.Get().CompareTo(this.bytes.Get()):other.boost.CompareTo(this.boost);}
static int Process(char[] s,int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);i--;break;default:break;}}return len;}
void Method(LittleEndianOutput outStream) { outStream.WriteShort(); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
KeySchemaElement(string attributeName, KeyType keyType){SetAttributeName(attributeName);SetKeyType(keyType.ToString());}
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Contains(AnyObjectId id) { return FindOffset(id) != -1; }
public GroupingSearch(bool allGroups){ this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName,bool v){lock(this){if(!fieldTypes.TryGetValue(dimName,out DimConfig ft)){ft=new DimConfig();fieldTypes[dimName]=ft;}ft.multiValued=v;}}
{ size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){request=beforeClientExecution;return executeDeleteLifecyclePolicy(request);}
