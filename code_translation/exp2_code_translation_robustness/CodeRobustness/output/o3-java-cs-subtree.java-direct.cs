void (LittleEndianOutput out) { out.WriteShort(); }
void Method(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void Add(byte b){if(upto==blockSize){if(currentBlock!=null){addBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request){request=beforeClientExecution(request);return executeDeleteDomainEntry;}
() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetMessage(){byte[] raw=buffer;int msgB=RawParseUtils.tagMessage;if(msgB<0){return "";}return RawParseUtils.decode(guessEncoding(),raw,msgB,raw.Length);}
public POIFSFileSystem() : this(true) { _header.SetBATCount(1); _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void Method(int address){slice=pool.buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice!=null);upto=address&ByteBlockPool.BYTE_BLOCK_MASK;offset0=address;System.Diagnostics.Debug.Assert(upto<someValue);}
SubmoduleAddCommand Path(string path) { this.path = path; return this; }
ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request){ request = BeforeClientExecution(request); return executeGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool Ready(){ lock(@lock){ if(in==null){ throw new IOException("InputStreamReader is closed"); } try{ return bytes.HasRemaining||in.Available()>0; } catch(Exception){ return false; } } }
public EscherOptRecord() { }
public int Read(char[] buffer,int offset,int length){lock(this){if(buffer==null)throw new ArgumentNullException(nameof(buffer));if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=(count-pos)<length?(count-pos):length;for(int i=0;i<copylen;i++){buffer[offset+i]=this.buffer[pos+i];}return copylen;}}
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Method(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V() { return base.nextEntry.GetValue(); }
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(buffer,0,b,offset,len);}}else{long after=bufferStart+bufferPosition+len;if(after>length())throw new EndOfStreamException("read past EOF: "+this);readInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
void Method() { throw new NotSupportedException(); }
object CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
void SetParams(string @params){base.setParams(@params);language=country=variant="";var tokens=@params.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj){if(!(obj is FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(length!=other.length)return false;for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i]))return false;}return true;}
GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails; }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.Parent=this;shape.SetAnchor(anchor);shapes.Add(shape);OnCreate(shape);return shape;}
string GetSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
GetDashboardResult GetDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
void HandleMulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();j++){BlankRecord br=new BlankRecord();br.SetColumn((short)(j+mbr.GetFirstColumn()));br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt());InsertCell(br);}}
public static string Method(){StringBuilder sb=new StringBuilder();sb.Append("\\Q");apos=0;while((k=@string.IndexOf("\\E",apos))>=0){sb.Append(@string.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(@string.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(object value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[getValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void() { refCount.IncrementAndGet(); }
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=beforeClientExecution;return ExecuteUpdateConfigurationSetSendingEnabled(request);}
() { return getXBATEntriesPerBlock() * ; }
void Pow10(int pow10){TenPower tp=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){mulShift(tp._divisor,tp._divisorShift);}else{mulShift(tp._multiplicand,tp._multiplierShift);} }
string BuildPath(){var b=new StringBuilder();int l=length;b.Append(Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i<l-1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); }
void Method(ProgressMonitor pm) { }
void () { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); throw new NoSuchElementException(); }
string (  ) { return ; }
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1;
List<CharsRef> Method(char[] word,int length){List<CharsRef> stems=stem(word,length);if(stems.Count<2)return stems;CharArraySet terms=new CharArraySet(8,dictionary.ignoreCase);List<CharsRef> deduped=new List<CharsRef>();foreach(CharsRef s in stems){if(!terms.Contains(s)){deduped.Add(s);terms.Add(s);}}return deduped;}
GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses; }
void SetPos(int pos){currentBlockIndex=(int)(pos>>blockBits);currentBlock=blocks[currentBlockIndex];currentBlockUpto=(int)(pos&blockMask);}
{ s = (int)Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
(string @string) { return lastIndexOf; }
bool (E @object) { return addLastImpl; }
void Unset(string section,string subsection){ConfigSnapshot src,res;do{src=state.Value;res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
string () { return tagName; }
void Add(int index, SubRecord element) { subrecords.Add(element); }
bool  () { lock (mutex) { return @delegate().Remove(o); } }
DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
{ return inCoreLength; }
void (bool newValue) { ; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(i) { if () throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(); }
bool () { }
void Method(){if(expectedModCount==list.modCount){if(lastLink!=null){Link<ET> next=lastLink.next;Link<ET> previous=lastLink.previous;next.previous=previous;previous.next=next;if(lastLink==link){pos--;}link=previous;lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}}
MergeShardsResult MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() => { }
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
static void Encode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public string GetRepoName(){string s=GetPath();if("/".Equals(s)||"".Equals(s))s=GetHost();if(s==null)throw new ArgumentException();string[]elements;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s))elements=Regex.Split(s,"[\\\\/"+Path.DirectorySeparatorChar+"]");else elements=Regex.Split(s,"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
string () { return ; }
CreateVpnConnectionResult CreateVpnConnection() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request){request=BeforeClientExecution(request);return ExecuteDescribeVoices(request);}
ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
EscherRecord GetEscherRecord(int index){ return escherRecords[index]; }
GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel; }
TrackingRefUpdate() { }
void Method(){Console.WriteLine(b.ToString());}
QueryNode() { return getChildren[0]; }
public NotIgnoredFilter(object workdirTreeIndex){ this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord(RecordInputStream inStream) { field_1_formatFlags = inStream.ReadInt16(); }
GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange OrdRange() { return prefixToOrdRange[dim]; }
string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format("{0}('{1}')", nameof(LexerNoViableAltException), symbol); }
E PeekFirst() { return peekFirstImpl; }
CreateWorkspacesResult CreateWorkspaces() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new FileInfo(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHex(long value) { var sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
HSSFColor HSSFColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.Index) return HSSFColorPredefined.AUTOMATIC.Color; byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(); }
ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Serialize() { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public IList<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if(this==obj)return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
SpanQuery SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; var sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo () { return byName.Get(fieldName); }
DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult CancelUpdateStack() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request=BeforeClientExecution(request);return ExecuteModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResult SetInstanceProtection() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return executeModifyDBProxy; }
void Add(char[] output,int offset,int len,int endOffset,int posLength){if(count==outputs.Length){outputs=ArrayUtil.Grow(outputs,count+1);}if(count==endOffsets.Length){int[] next=new int[ArrayUtil.Oversize(1+count,4)];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){int[] next=new int[ArrayUtil.Oversize(1+count,4)];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool Method() { return fs.Exists; }
public FilterOutputStream() { this.@out = @out; }
new ScaleClusterRequest("/clusters/[ClusterId]").SetMethod(MethodType.PUT);
public static DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult ListObjectParentPaths() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
void SomeMethod() { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool () { }
public ErrorNode ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent; return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
EventSubscription EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (object o) { return; }
bool () { return protectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public object GetObjectData() { return findObjectRecord.GetObjectData(); }
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
string ToString() { return getKey + ": " + getValue(); }
return ExecuteListTextTranslationJobs(request = BeforeClientExecution(request));
GetContactMethodsResult GetContactMethods() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetFunctionIndex(string name){ FunctionMetadata fd=GetInstance().GetFunctionByNameInternal(name); if(fd==null){ fd=GetInstanceCetab().GetFunctionByNameInternal(name); if(fd==null){ return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){ request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string MethodName(string message, ObjectId changeId) { return insertId; }
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize; if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus ( ) { request = BeforeClientExecution ( request ) ; return ExecutePutLifecycleEventHookExecutionStatus ( request ) ; }
NumberPtg() { in.ReadDouble(); }
GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new ; }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult GetDedicatedIp() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution; return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
public WindowProtectRecord(object options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void Method(byte[] b, int o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream input){field_1_h_hold=input.readShort();field_2_v_hold=input.readShort();field_3_width=input.readShort();field_4_height=input.readShort();field_5_options=input.readShort();field_6_active_sheet=input.readShort();field_7_first_visible_tab=input.readShort();field_8_num_selected_tabs=input.readShort();field_9_tab_width_ratio=input.readShort();}
StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request){ request = beforeClientExecution; return executeStopWorkspaces(request); }
void Close() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
string (  wordId , surface [ ] , off , len ) { }
string () { }
public static double Var(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;i++)s+=v[i];m=s/n;s=0;for(int i=0;i<n;i++)s+=(v[i]-m)*(v[i]-m);r=(n==1)?0:s;}return r;}
DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
() { return end; }
void SomeMethod(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() { }
public int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.Get().CompareTo(bytes.Get()); else return this.boost.CompareTo(other.boost); }
static int Process(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; goto case KEHEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
void SomeMethod(LittleEndianOutput output){output.WriteShort();}
DiagnosticErrorListener() { this.exactOnly = exactOnly; }
KeySchemaElement(string attributeName, object keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Contains(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch SetAllGroups(bool allGroups){this.allGroups = allGroups; return this;}
public void SetMultiValued(string dimName, bool v){lock(this){DimConfig ft; if(!fieldTypes.TryGetValue(dimName,out ft)){ft=new DimConfig(); fieldTypes[dimName]=ft;} ft.multiValued=v;}}
int Size() { size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
private DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
