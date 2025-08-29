void Write(ILittleEndianOutput out){ out.WriteInt16(field_1_vcenter); }
void Src(BlockList<T> src){if(src.Count==0)return;for(srcDirIdx=0;srcDirIdx<tailDirIdx;++srcDirIdx){directory[srcDirIdx].AddAll(src,0,BLOCK_SIZE);}if(tailBlkIdx!=0){tailBlock.AddAll(src,0,tailBlkIdx);}}
void b(){if(blockSize==upto){if(currentBlock!=null){addBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}b=currentBlock[upto++];}
public ObjectId ObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
return (termOffsets != null ? ramBytesUsed.termOffsets : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0);
public string GetFullMessage(){byte[] raw=buffer;int msgB=RawParseUtils.TagMessage(raw,0);if(msgB<0)return string.Empty;return RawParseUtils.Decode(raw,msgB,raw.Length,GuessEncoding());}
_header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0);
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; this.address = address; System.Diagnostics.Debug.Assert(upto < slice.Length);
public SubmoduleAddCommand(string path){ this.path=path; return; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request){request=BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream) : this(stream, 0) { } public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request){request=BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready(){lock(lockObject){if(inputStream==null){throw new IOException("InputStreamReader is closed");}try{return bytes.HasRemaining()||inputStream.Available>0;}catch(Exception){return false;}}}
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer,int offset,int length){lock(this){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=(length<pos-count)?length:pos-count;for(int i=0;i<copylen;++i)buffer[offset+i]=this.buffer[pos+i];copylen+=pos;return copylen;}}
this.sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
void Write(string str){object valueOf=str!=null?str:null;}
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.functionName = functionName; }
return base.nextEntry().GetValue();
public sealed void ReadInternal(byte[] b,int offset,int len,bool useBuffer){if(len<=bufferLength-bufferPosition){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{int available=bufferLength-bufferPosition;if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(len==0)return;if(len<bufferSize&&useBuffer){Refill();if(bufferLength<len)throw new EndOfStreamException("read past EOF: "+this);Array.Copy(buffer,0,b,offset,len);bufferPosition=len;}else{long after=bufferStart+bufferPosition+len;if(after>Length)throw new EndOfStreamException("read past EOF: "+this);ReadFromSource(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
void () { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){request=BeforeClientExecution(request);return ExecuteModifyCacheSubnetGroup(request);}
public override void SetParams(string parameters){base.SetParams(parameters);string language="",country="",variant="";var tokens=parameters.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public override DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request=BeforeClientExecution(request);return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){ request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.SetParent(this);shape.SetAnchor(anchor);shapes.Add(shape);OnCreate(shape);return shape;}
public string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
void MulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
using System.Text;public static string Quote(string input){StringBuilder sb=new StringBuilder();sb.Append(@"\Q");int apos=0,k;while((k=input.IndexOf(@"\E",apos,System.StringComparison.Ordinal))>=0){sb.Append(input.Substring(apos,k+2-apos));sb.Append(@"\\E\Q");apos=k+2;}return sb.Append(input.Substring(apos)).Append(@"\E").ToString();}
ByteBuffer Value() { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=nColumns;_nRows=nRows;object[] vv=new object[_nRows*_nColumns];for(int r=0;r<nRows;++r){object[] rowData=values2d[r];for(int c=0;c<nColumns;++c){vv[getValueIndex(r,c)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {     request = beforeClientExecution(request);     return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
string Field() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void IncrementRefCount() { refCount.IncrementAndGet(); }
@Override public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {     request = beforeClientExecution(request);     return executeUpdateConfigurationSetSendingEnabled(request); }
return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void Pow10(){TenPower tp=TenPower.getInstance(Math.Abs(pow10));if(pow10>0){mulShift(_divisor.tp,_divisorShift.tp);}else{mulShift(_multiplicand.tp,_multiplierShift.tp);} }
string BuildPath(){var b=new System.Text.StringBuilder();int l=Length();b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;++i){b.Append(GetComponent(i));if(i<l-1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher=fetcher;this.fetcher.SetRoleName(roleName);}
ProgressMonitor pm = progressMonitor;
void ParseEntry(){if(!First()){ptr=0;if(!Eof())ParseEntry();}}
public E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string NewPrefix() { return this.newPrefix; }
int IndexOf(int value){ for(int i=0;i<mSize;++i){ if(mValues[i]==value) return i; } return -1; }
public static List<CharsRef> Dedup(List<CharsRef> stems,char[] word,int length,CharArraySet dictionary){if(stems.Count<2)return stems;CharArraySet terms=new CharArraySet(8,dictionary.IgnoreCase);List<CharsRef> deduped=new List<CharsRef>();foreach(CharsRef s in stems)if(!terms.Contains(s)){deduped.Add(s);terms.Add(s);}return deduped;}
GetGatewayResponsesRequest request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request);
void pos(int pos){currentBlockIndex=(currentBlockBits>>pos)&blockMask;currentBlock=blocks[currentBlockIndex];currentBlockUpto=pos;}
s = Math.Min(available(), Math.Max(0, n)); ptr += s; return s;
BootstrapActionDetail bootstrapActionDetail = new BootstrapActionDetail { BootstrapActionConfig = bootstrapActionConfig };
void serialize(LittleEndianOutput out) {     out.writeShort(field_1_row);     out.writeShort(field_2_col);     out.writeShort(field_3_flags);     out.writeShort(field_4_shapeid);     out.writeShort(field_6_author.length());     out.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);     if (field_5_hasMultibyte) {         StringUtil.putUnicodeLE(field_6_author, out);     } else {         StringUtil.putCompressedUnicode(field_6_author, out);     }     if (field_7_padding != null) {         out.writeByte(field_7_padding.intValue());     } }
public static int Count(string str){return str.LastIndexOf(',');}
bool Add<E>(E @object){ return AddLastImpl(@object); }
private void UnsetSection(string section,string subsection){ConfigSnapshot src,res;do{src=state;res=src.UnsetSection(section,subsection);}while(System.Threading.Interlocked.CompareExchange(ref state,res,src)!=src);}
public string TagName() { return tagName; }
public void AddSubrecords(SubRecord element, int index) { subrecords.Insert(index, element); }
bool Remove(object o) { lock (mutex) { return @delegate.Remove(o); } }
public override TokenStream Create(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
return inCoreLength();
void SetValue(bool newValue){ value = newValue; }
public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; }
if (i <= count) throw new IndexOutOfRangeException(i.ToString()); return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { SetMethod(MethodType.PUT); UriPattern = "/repos"; }
bool () { return deltaBaseAsOffset; }
if(list.modCount==expectedModCount){if(lastLink!=null){var previous=lastLink.previous;var next=lastLink.next;if(link==lastLink){--pos;}previous.next=next;next.previous=previous;lastLink.next=null;lastLink.previous=null;++expectedModCount;--list.size;++list.modCount;lastLink=null;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException("Collection was modified");}
return ExecuteMergeShards(BeforeClientExecution(request));
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {     request = beforeClientExecution(request);     return executeAllocateHostedConnection(request); }
}; start return { )(
public static WeightedTerm[] GetTerms(Query query){return GetTerms(query,false);}
ByteBuffer(){throw new ReadOnlyBufferException();}
public static void Unpack6Bits(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&0x03)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&0x0F)<<2)|(byte2>>6);values[valuesOffset++]=byte2&0x3F;}}
static string GetPath(string s){if(s==null)throw new ArgumentException();if(s.Equals("/")||s.Equals(""))s=GetHost(s);string scheme=GetScheme(s);if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s)){string[]elements=s.Split(new[]{'/','\\',System.IO.Path.DirectorySeparatorChar},StringSplitOptions.RemoveEmptyEntries);if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}throw new ArgumentException();}
public DescribeNotebookInstanceLifecycleConfigResult ExecuteDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){ return BeforeClientExecution(request); }
string GetAccessKeySecret() { return this.accessKeySecret; }
return ExecuteCreateVpnConnection(BeforeClientExecution(request));
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request){request=BeforeClientExecution(request);return ExecuteDescribeVoices(request);}
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
return (EscherRecord) escherRecords[index];
request = BeforeClientExecution(request); return ExecuteGetApis(request);
public DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){request=BeforeClientExecution(request);return ExecuteDeleteSmsChannel(request);}
return (TrackingRefUpdate)trackingRefUpdate;
void DisplayBoolean(bool b) { Console.Write(b.ToString()); }
return getChildren()[0];
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.index = workdirTreeIndex; }
field_1_formatFlags = inStream.ReadInt16();
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
return ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(request));
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request=BeforeClientExecution(request);return ExecutePutVoiceConnectorStreamingConfiguration(request);}
OrdRange PrefixToOrdRange(string dim) { return get.PrefixToOrdRange(dim); }
public override string ToString() { string symbol = ""; if (_input != null && _startIndex >= 0 && _startIndex < _input.Size) { symbol = _input.GetText(Interval.Of(_startIndex, _input.Index)); symbol = Utils.EscapeWhitespace(symbol, false); } string className = typeof(LexerNoViableAltException).Name; return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", className, symbol); }
public E PeekFirst() { return PeekFirstImpl(); }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request=BeforeClientExecution(request);return ExecuteCreateWorkspaces(request);}
NumberFormatIndexRecord() { return Copy(); }
return ExecuteDescribeRepositories(BeforeClientExecution(request));
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {     request = beforeClientExecution(request);        // pre-process     return executeCreateDistributionWithTags(request); // perform call & return result }
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToHexString(string value) { var sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
return ExecuteUpdateDistribution(BeforeClientExecution(request));
public HSSFColor GetColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}var b=_palette.GetColor(index);return b==null?null:new CustomColor(index,b);}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Serialize(BinaryWriter output){output.Write(field_1_number_crn_records);output.Write(field_2_sheet_table_index);}
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(int fontIndex, char character) { this._fontIndex = fontIndex; this._character = character; }
public static byte[] ToBytes(char[] chars,int offset,int length){byte[]result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request){request=BeforeClientExecution(request);return ExecuteUploadArchive(request);}
public IList<IToken> GetHiddenTokensToLeft(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex,-1);}
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery BuildQuery(Dictionary<SpanQuery,float> weightBySpanQuery){SpanQuery[] spanQueries=new SpanQuery[weightBySpanQuery.Count];int i=0;foreach(var sqKey in weightBySpanQuery.Keys){float boost=weightBySpanQuery[sqKey];SpanQuery sq=sqKey;if(boost!=1f){sq=new SpanBoostQuery(sq,boost);}spanQueries[i++]=sq;}return spanQueries.Length==1?spanQueries[0]:new SpanOrQuery(spanQueries);}
return new StashCreateCommand(repo);
FieldInfo Get(string fieldName) { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request){request=BeforeClientExecution(request);return ExecuteDescribeEventSource(request);}
private GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request){request=BeforeClientExecution(request);return ExecuteGetDocumentAnalysis(request);}
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) => ExecuteCancelUpdateStack(BeforeClientExecution(request));
return ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(request));
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
@Override public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {     request = beforeClientExecution(request);   // Pre-processing (validation, metrics, retries, etc.)     return executeModifyDBProxy(request);       // Sends the request to AWS and returns the result }
void Add(int posLength, int endOffset, char[] output, int offset, int len){if(count==posLengths.Length){int[] next=new int[Lucene.Net.Util.ArrayUtil.Oversize(count+1,sizeof(int))];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(count==endOffsets.Length){int[] next=new int[Lucene.Net.Util.ArrayUtil.Oversize(count+1,sizeof(int))];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==outputs.Length){outputs=Lucene.Net.Util.ArrayUtil.Grow(outputs,count+1);}if(outputs[count]==null){outputs[count]=new Lucene.Net.Util.CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);posLengths[count]=posLength;endOffsets[count]=endOffset;++count;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public bool ObjectsExist() { return fs.Exists(objects); }
public FilterOutputStream(System.IO.Stream output){this.output=output;}
public class ScaleClusterRequest : RpcAcsRequest<ScaleClusterResponse> { public ScaleClusterRequest() : base("csk", "2015-12-15", "ScaleCluster", "CS") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; } }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { }
public ListObjectParentPathsResult listObjectParentPaths(         ListObjectParentPathsRequest request) {     request = beforeClientExecution(request);    // performs validation, signing, etc.     return executeListObjectParentPaths(request); // actually makes the service call }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {     request = beforeClientExecution(request);          // Pre-processes or validates the request     return executeDescribeCacheSubnetGroups(request);  // Sends the request to AWS and returns the result }
void SetSharedFormula(bool flag){field_5_options = SetShortBoolean(field_5_options, flag);}
bool reuseObjects() { return reuseObjects; }
ErrorNode BadToken(Token badToken){ErrorNodeImpl t=new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent(this);return t;}
public LatvianStemFilterFactory(IDictionary<string, string> args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request=BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
public override RevFilter Clone() { return new Binary(a.Clone(), b.Clone()); }
return o instanceof ArmenianStemmer;
public bool protectedHasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void Cleanup(){records.Remove(fileShare);records.Remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, expand, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request){request=BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
public object[] GetObjectData() { return FindObjectRecord().GetObjectData(); }
private GetContactAttributesResult ExecuteGetContactAttributes(GetContactAttributesRequest request){request=BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
return GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {     request = beforeClientExecution(request);     return executeListTextTranslationJobs(request); }
return ExecuteGetContactMethods((GetContactMethodsRequest)BeforeClientExecution(request));
public static int GetFunctionIndex(string name){FunctionMetadata fd=GetFunctionByNameInternal(name);if(fd==null){fd=GetInstanceCetab(name);}if(fd==null)return -1;return fd.GetIndex();}
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string InsertId(string message, ObjectId changeId) { return insertId(message, changeId, false); }
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(this, objectId); if (0 < sz) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); } return sz; }
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput input) { readDouble(input); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request){request=BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
ReportInstanceStatusResult ExecuteReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
return new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj){lock(mutex){return c.Remove(obj);}}
GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {     request = beforeClientExecution(request);     return executeGetDedicatedIp(request); }
return "(" + precedence + " >= _p)";
return ExecuteListStreamProcessors(BeforeClientExecution(new ListStreamProcessorsRequest()));
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(object _options){options=_options;}
UnbufferedCharStream(int bufferSize) {          n = 0;                 // reset current position / length counter          data = new char[bufferSize]; // allocate the internal character buffer      }
return ExecuteGetOperations(BeforeClientExecution(request));
void WriteWords(byte[] b,int o,int w1,int w2,int w3,int w4,int w5){NB.EncodeInt32(b,o,w1);NB.EncodeInt32(b,o+4,w2);NB.EncodeInt32(b,o+8,w3);NB.EncodeInt32(b,o+12,w4);NB.EncodeInt32(b,o+16,w5);}
public WindowOneRecord(RecordInputStream inStream){field_1_h_hold=inStream.ReadInt16();field_2_v_hold=inStream.ReadInt16();field_3_width=inStream.ReadInt16();field_4_height=inStream.ReadInt16();field_5_options=inStream.ReadInt16();field_6_active_sheet=inStream.ReadInt16();field_7_first_visible_tab=inStream.ReadInt16();field_8_num_selected_tabs=inStream.ReadInt16();field_9_tab_width_ratio=inStream.ReadInt16();}
StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {     request = beforeClientExecution(request);     return executeStopWorkspaces(request); }
void Dump() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeMatchmakingRuleSets(request);}
public string Surface(int wordId, char[] surface, int off, int len) { return null; }
string GetPathStr() { return pathStr; }
public static double Variance(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;++i){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;++i){s+=(v[i]-m)*(v[i]-m);}r=(n==1?0:s);}return r;}
return ExecuteDescribeResize(BeforeClientExecution(request));
public bool PassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return end(0);
void Traverse(ICellHandler handler,bool traverseEmptyCells,ISheet sheet,CellRangeAddress range){int firstRow=range.FirstRow;int lastRow=range.LastRow;int firstColumn=range.FirstColumn;int lastColumn=range.LastColumn;int width=lastColumn-firstColumn+1;var ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.RowNumber=firstRow;ctx.RowNumber<=lastRow;ctx.RowNumber++){currentRow=sheet.GetRow(ctx.RowNumber);if(currentRow==null)continue;for(ctx.ColNumber=firstColumn;ctx.ColNumber<=lastColumn;ctx.ColNumber++){currentCell=currentRow.GetCell(ctx.ColNumber);if(currentCell==null)continue;if(!traverseEmptyCells&&currentCell.IsEmpty())continue;long rowSize=ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber,firstRow),width);ctx.OrdinalNumber=ArithmeticUtils.AddAndCheck(rowSize,1+ctx.ColNumber-firstColumn);handler.OnCell(ctx,currentCell);}}}
return pos;
public int CompareTo(ScoreTerm other){return this.boost==other.boost?this.bytes.CompareTo(other.bytes):other.boost.CompareTo(this.boost);}
for (i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); --i; break; } } return len;
void Out(LittleEndianOutput output){output.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly=exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType){ SetAttributeName(attributeName); SetKeyType(keyType); }
@Override public GetAssignmentResult getAssignment(GetAssignmentRequest request) {     request = beforeClientExecution(request);   // pre-process or validate the request     return executeGetAssignment(request);       // perform the actual service call }
bool Contains(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch SetAllGroups(bool allGroups){ this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock(this) { if (!fieldTypes.TryGetValue(dimName, out var ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
var i=cells.Keys.GetEnumerator();while(i.MoveNext()){char c=i.Current;Cell e=at(c);if(e.cmd>=0){++size;}}return size;
return ExecuteDeleteVoiceConnector(BeforeClientExecution(request));
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
