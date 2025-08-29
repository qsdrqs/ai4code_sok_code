out.WriteShort((short)field_1_vcenter);
if (srcDirIdx == 0) return; for (srcDirIdx = 0; srcDirIdx < tailDirIdx; ++srcDirIdx) if (src != null) BlockList.AddRange(src.directory[srcDirIdx].tailBlock, 0, BLOCK_SIZE);
if (++upto == blockSize) { upto = 0; if (currentBlock != null) addBlock(currentBlock); currentBlock = new byte[blockSize]; }
ObjectId objectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request){request=BeforeClientExecution(request);return ExecuteDeleteDomainEntry(request);}
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public static string Decode(byte[] buffer){if(buffer==null)return "";int msgB=RawParseUtils.TagMessage(buffer,0);if(msgB<0)return "";string enc=RawParseUtils.GuessEncoding(buffer,0,msgB);return RawParseUtils.Decode(enc,buffer,msgB,buffer.Length-msgB);}
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(0); bb.SetNextBlock(POIFSConstants.END_OF_CHAIN); _bat_blocks.Add(bb); _header.SetBATCount(_header.BATCount + 1); _header.SetBATArray(new[] { 0 }); _property_table.SetStartBlock(0); poifsFileSystem.SetNextBlock(0, POIFSConstants.END_OF_CHAIN);
System.Diagnostics.Debug.Assert(address < upto); address = offset0 = upto; System.Diagnostics.Debug.Assert(slice != null); int length = slice.Length; slice[address & BYTE_BLOCK_MASK] = ByteBlockPool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT][BYTE_BLOCK_MASK];
public SubmoduleAddCommand SetPath(string path){ this.path = path; return this; }
request = BeforeClientExecution(request); return ExecuteListIngestions(request);
public QueryParserTokenManager(CharStream stream):this(stream,0){} public QueryParserTokenManager(CharStream stream,int lexState){ReInit(stream);SwitchTo(lexState);}
request = BeforeClientExecution(request); return ExecuteGetShardIterator(request);
public class ModifyStrategyRequest : RpcAcsRequest<ModifyStrategyResponse>{public ModifyStrategyRequest():base("aegis","2016-11-11","ModifyStrategy","vipaegis"){Method=MethodType.POST;}}
public bool Ready(){lock(_lock){if(input==null){throw new IOException("InputStreamReader is closed");}return bytes.HasRemaining||input.Available>0;}}
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer,int offset,int length){lock(this){if(buffer==null)throw new NullReferenceException("buffer == null");if(length==0)return 0;if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();int copylen=Math.Min(length,count-pos);for(int i=0;i<copylen;i++){buffer[offset+i]=this.CharAt(pos+i);}pos+=copylen;return copylen;}}
sentenceOp = new NLPSentenceDetectorOp(sentenceOp, new OpenNLPSentenceBreakIterator());
void Write(string str){object obj=(str!=null?str:null);}
public class NotImplementedFunctionException : NotImplementedException { private readonly string functionName; public NotImplementedFunctionException(string functionName, Exception cause) : base(cause?.Message, cause) { this.functionName = functionName; } }
return (V) base.NextEntry().GetValue();
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){if(len<=0)return;int available=bufferLength-bufferPosition;if(available>=len){Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(len<bufferSize&&useBuffer){Refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException(this+"read past EOF: "+(bufferStart+bufferLength));}else{Array.Copy(buffer,0,b,offset,len);bufferPosition=len;return;}}long after=bufferStart+bufferPosition+len;if(after>Length()){throw new EndOfStreamException(this+"read past EOF: "+after);}ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}
request = BeforeClientExecution(request); return ExecuteTagQueue(request);
void someMethod() {     throw new UnsupportedOperationException(); }
request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request);
public void SetParams(string parameters){string language="",country="",variant="";if(parameters!=null){var tokens=parameters.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}base.SetParams(language,country,variant);}
private DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
request = BeforeClientExecution(request); GetInstanceAccessDetailsResult result = ExecuteGetInstanceAccessDetails(request); return result;
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) {     HSSFPolygon shape = new HSSFPolygon(this, anchor);  // 1) construct shape     shape.setParent(this);                              // 2) register its parent     shape.setAnchor(anchor);                            // 3) set its position/size     shapes.add(shape);                                  // 4) add to internal list     onCreate(shape);                                    // 5) let subclasses hook in     return shape;                                       // 6) hand the shape back }
return getBoundSheetRec(sheetIndex).getSheetname();
{ request = beforeClientExecution(request); return executeGetDashboard(request); }
// Translated C# (single line) AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request=BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
for (int j = 0; j < mbr.GetNumColumns(); ++j) { BlankRecord br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); }
public static string ToString(string input){StringBuilder sb=new StringBuilder();string apos="'";int k=input.IndexOf(apos);while(k>=0){sb.Append("\\Q").Append(input.Substring(0,k)).Append("\\E").Append(apos);input=input.Substring(k+2);k=input.IndexOf(apos);if(k>=0)sb.Append("\\\\E\\Q");}sb.Append("\\Q").Append(input).Append("\\E");return sb.ToString();}
throw new ReadOnlyBufferException();
_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;_nRows=nRows;_nColumns=nColumns;object[][] values2d=new object[nRows][];for(int r=0;r<nRows;r++){object[] rowData=new object[nColumns];values2d[r]=rowData;for(int c=0;c<nColumns;c++){rowData[c]=vv[getValueIndex(r,c)];}}
return ExecuteGetIceServerConfig((GetIceServerConfigRequest) BeforeClientExecution(request));
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public string ToString(string field){ return parentQuery.ToString(field) + "ToChildBlockJoinQuery (" + field + ")"; }
public void IncrementAndGet() { System.Threading.Interlocked.Increment(ref refCount); }
request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request);
{ return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
} { ) ( void else if ; TenPower pow10 } { } { ) ( = tp ; ; 0 < TenPower.GetInstance pow10 mulShift mulShift ) ( ) , ( ) , ( Math.Abs _multiplierShift . tp _multiplicand . tp _divisorShift . tp _divisor . tp ) pow10 (
public override string ToString() { StringBuilder b = new StringBuilder(length); for (int i = 0; i < l; ++i) { if (i > 0) { b.Append(Path.DirectorySeparatorChar); } b.Append(GetComponent(l - 1 - i)); } return b.ToString(); }
public InstanceProfileCredentialsProvider WithRoleName(string roleName) { this.fetcher.SetRoleName(roleName); return this; }
ProgressMonitor pm = progressMonitor;
if (eof()) { if (!parseEntry(first, ptr = 0)) { } }
public E Previous() { if (iterator.PreviousIndex() < start) { throw new InvalidOperationException(); } return iterator.Previous(); }
string NewPrefix() { return this.newPrefix; }
for (int i = 0; i < mSize; ++i) { if (value == mValues[i]) return i; } return -1;
List<CharsRef> Deduplicate(List<CharsRef> stems, CharArraySet dictionary, bool ignoreCase){var terms=new List<CharsRef>();var deduped=new List<CharsRef>();foreach(var s in stems){if(!dictionary.Contains(s,ignoreCase)){deduped.Add(s);terms.Add(s);}}var word=new List<CharsRef>(Math.Min(stems.Count,8));int length=word.Count;return deduped;}
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
pos = (blocks[currentBlockIndex] >> blockBits) & blockMask;
n = Math.Max(0, Math.Min(available, n)); ptr = s += n; return s;
public class BootstrapActionDetail { public void SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; } }
public void Serialize(ILittleEndianOutput output){output.WriteShort(field_1_row);output.WriteShort(field_2_col);output.WriteShort(field_3_flags);output.WriteShort(field_4_shapeid);output.WriteByte(field_6_author.Length);bool hasMultibyte=StringUtil.HasMultibyte(field_6_author);output.WriteByte(hasMultibyte?(byte)0x01:(byte)0x00);if(hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,output);}else{StringUtil.PutCompressedUnicode(field_6_author,output);}if(field_7_padding!=null){foreach(var b in field_7_padding){output.WriteByte(b);}}}
public static int LastIndexOf(string source,string sub){return source.LastIndexOf(sub);}
bool AddLastImpl<E>(E @object){return Add(@object);}
public void UnsetSection(string section,string subsection){while(true){ConfigSnapshot src=state.Get();ConfigSnapshot res=src.UnsetSection(section,subsection);if(state.CompareAndSet(src,res)){break;}}}
public string TagName() { return tagName; }
void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return @delegate.Remove(o); } }
public override TokenStream Create(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
return inCoreLength();
} { ) (  void ; newValue boolean newValue = value
this.oldSource = oldSource; this.newSource = newSource; var pair = new Pair<ContentSource, ContentSource>(oldSource, newSource);
if (i <= count) { return entries[i]; } else { throw new IndexOutOfRangeException(i.ToString()); }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { Method = MethodType.PUT; UriPattern = "/repos"; }
boolean isDeltaBaseAsOffset() {     return deltaBaseAsOffset; }
if (expectedModCount != list.modCount) throw new InvalidOperationException(); if (lastLink == null) throw new InvalidOperationException(); Link link = lastLink; lastLink = null; if (link == previous) { previous = previous.previous; pos--; } else { next = next.next; } list.size--; list.modCount++; expectedModCount++;
public MergeShardsResult MergeShards(MergeShardsRequest request){request=BeforeClientExecution(request);return ExecuteMergeShards(request);}
request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request);
return start;
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset++] & 0xFF; int byte1 = values[valuesOffset++] & 0xFF; int byte2 = values[valuesOffset++] & 0xFF; blocks[blocksOffset++] = (byte)(byte0 >> 2); blocks[blocksOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4)); blocks[blocksOffset++] = (byte)(((byte1 & 15) << 2) | (byte2 >> 6)); blocks[blocksOffset++] = (byte)(byte2 & 63); }
string s = GetPath(); if (s == null || s == "") throw new ArgumentException(nameof(s)); string[] elements = System.Text.RegularExpressions.Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(nameof(s)); string result = string.Join(System.IO.Path.DirectorySeparatorChar.ToString(), elements); if (result.EndsWith(Constants.DOT_GIT)) result = result.Substring(0, result.Length - Constants.DOT_GIT.Length); else if (result.Equals(Constants.DOT_GIT_EXT)) throw new ArgumentException(nameof(s)); return result;
return executeDescribeNotebookInstanceLifecycleConfig(beforeClientExecution(request));
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request){request=BeforeClientExecution(request);return CreateVpnConnection(request);}
DescribeVoicesRequest request = BeforeClientExecution(request); return ExecuteDescribeVoices(request);
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request=BeforeClientExecution(request);return ExecuteListMonitoringExecutions(request);}
var request = new DescribeJobRequest { VaultName = vaultName, JobId = jobId };
return (EscherRecord)escherRecords[index];
request = beforeClientExecution(request); return executeGetApis(request);
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request){request=BeforeClientExecution(request);return ExecuteDeleteSmsChannel(request);}
return (TrackingRefUpdate) trackingRefUpdate;
void print(bool b){Console.Write(b.ToString());}
return GetChildren()[0];
this.index = workdirTreeIndex;
field_1_formatFlags = in.ReadInt16();
public GetThumbnailRequest() : base("cloudphoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
return ExecuteDescribeTransitGatewayVpcAttachments(request);
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange PrefixToOrdRange(string dim) { return prefixToOrdRange[dim]; }
return string.Format(System.Globalization.CultureInfo.CurrentCulture, "%s('%s')", GetType().Name, Utils.EscapeWhitespace(GetInputStream().GetText(Interval.Of(startIndex, startIndex)), false));
return (E)PeekFirstImpl();
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request=BeforeClientExecution(request);return ExecuteCreateWorkspaces(request);}
return copy(NumberFormatIndexRecord);
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity){if(initialCapacity==0){mKeys=EmptyArray.INT;mValues=EmptyArray.INT;}else{initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];}mSize=0;}
public override TokenStream Create(TokenStream input) { return new HyphenatedWordsFilter(input); }
request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);
new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite);
return ExecuteDeleteWorkspaceImage(BeforeClientExecution(request));
public static string WriteHex(string value){var sb=new System.Text.StringBuilder(16);WriteHex(sb,value,16,"");return sb.ToString();}
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {        request = beforeClientExecution(request);        return executeUpdateDistribution(request);    }
public HSSFColor GetColor(int index){if(HSSFColorPredefined.AUTOMATIC.GetIndex()==index){return HSSFColorPredefined.AUTOMATIC.GetColor();}HSSFColor b=_palette[index];return b==null?new CustomColor(index):b;}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Serialize(LittleEndianOutput output){output.WriteInt16(field_1_number_crn_records);output.WriteInt16(field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
this._character = character; this._fontIndex = fontIndex; FormatRun(this, character, fontIndex);
public static byte[] CharsToBytes(char[] chars, int offset, int length) { int end = offset + length; byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
request = BeforeClientExecution(request); return ExecuteUploadArchive(request);
return (List<Token>)GetHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj){if(ReferenceEquals(this,obj))return true;if(!(obj is AutomatonQuery))return false;var other=(AutomatonQuery)obj;if(!base.Equals(obj))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;if(compiled==null){if(other.compiled!=null)return false;}else if(!compiled.Equals(other.compiled))return false;return true;}
SpanQuery BuildSpanQuery(IDictionary<SpanQuery,float> weightBySpanQuery){var spanQueries=new List<SpanQuery>();foreach(var kvp in weightBySpanQuery){SpanQuery sq=kvp.Key;float boost=kvp.Value;if(boost!=1f)sq=new SpanBoostQuery(sq,boost);spanQueries.Add(sq);}return spanQueries.Count==1?spanQueries[0]:new SpanOrQuery(spanQueries.ToArray());}
return new StashCreateCommand(repo);
FieldInfo GetByName(string fieldName){ return get.byName(fieldName); }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request);
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request){ request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request);
return ExecuteSetInstanceProtection(BeforeClientExecution(request));
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request){request=BeforeClientExecution(request);return ExecuteModifyDBProxy(request);}
private void EnsureCapacity(){if(count==outputs.Length){int newSize=ArrayUtil.Oversize(1+count,RamUsageEstimator.NUM_BYTES_OBJECT_REF);var nextOutputs=new CharsRefBuilder[newSize];Array.Copy(outputs,0,nextOutputs,0,count);outputs=nextOutputs;var nextPosLengths=new int[newSize];Array.Copy(posLengths,0,nextPosLengths,0,count);posLengths=nextPosLengths;var nextEndOffsets=new int[newSize];Array.Copy(endOffsets,0,nextEndOffsets,0,count);endOffsets=nextEndOffsets;}}
public FetchLibrariesRequest() : base("cloudphoto","FetchLibraries","2017-07-11","CloudPhoto") { Protocol = ProtocolType.HTTPS; }
bool Exists(FsObjects fs){ return fs.Exists(); }
this.out = out;
public ScaleClusterRequest() : base("csk", "2015-12-15", "ScaleCluster", "CS") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeCacheSubnetGroups(request);}
public void SetSharedFormula(bool flag){ field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
boolean isReuseObjects() {     return reuseObjects; }
{ ErrorNode t = new ErrorNodeImpl(badToken); this.AddAnyChild(t); t.SetParent(this); t.Token = badToken; return t; }
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args);
private EventSubscription ExecuteRemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return RemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(string name, Dictionary<string,string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "2017-07-11", "AddAlbumPhotos", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request=BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
return new Binary(a.Clone(), b.Clone());
bool IsArmenianStemmer(object o) { return o is ArmenianStemmer; }
public bool ProtectedHasArray(){return false;}
request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request);
void ClearPermissions(){records.Remove(fileShare);records.Remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(expand, analyzer) { this.dedup = dedup; this.expand = expand; }
RequestSpotInstancesResult ExecuteRequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return RequestSpotInstances(request); }
return getObjectData()[findObjectRecord()];
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request){request=BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
public override string ToString() { return Key + ": " + Value; }
request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request);
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static FunctionMetadata GetIndex(string name){FunctionMetadata fd=GetFunctionByNameInternal(name);if(fd==null){fd=GetInstance(name);if(fd==null)return GetInstanceCetab(name-1).GetFunctionByNameInternal(name);}return fd;}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request);}
public static string InsertId(string message, ObjectId changeId){return InsertId(message, changeId, false);}
public long GetObjectSize(AnyObjectId objectId,int typeHint){long sz=db.GetObjectSize(objectId,typeHint);if(sz>0)return sz;if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),typeHint);throw new IncorrectObjectTypeException(objectId.Copy(),JGitText.Get().UnknownObjectType2);}
private ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request){request=BeforeClientExecution(request);return ExecuteImportInstallationMedia(request);}
PutLifecycleEventHookExecutionStatusRequest request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request);
public NumberPtg(LittleEndianInput input){field_1_value=input.ReadDouble();}
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetFieldLevelEncryptionConfig(request);}
{ DescribeDetectorRequest request = BeforeClientExecution(request); DescribeDetectorResult result = ExecuteDescribeDetector(request); return result; }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
request = BeforeClientExecution(request); DeleteAlarmResult executeDeleteAlarm = ExecuteDeleteAlarm(request); return executeDeleteAlarm;
public override TokenStream Create(TokenStream input) { return new PortugueseStemFilter(input); }
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public override bool Remove(object obj) { lock (mutex) { return mutex.Remove(obj); } }
GetDedicatedIpResult ExecuteGetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return new GetDedicatedIpResult(); }
public override string ToString() { return " >= _p" + precedence; }
ListStreamProcessorsRequest request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request);
public DeleteLoadBalancerPolicyRequest SetLoadBalancerName(string loadBalancerName){this.LoadBalancerName=loadBalancerName;return this;} public DeleteLoadBalancerPolicyRequest SetPolicyName(string policyName){this.PolicyName=policyName;return this;}
public WindowProtectRecord(Options options){ _options = options; }
public UnbufferedCharStream(){n=0;bufferSize=n;data=new char[bufferSize];}
request = BeforeClientExecution(request); return ExecuteGetOperations(request);
{ encodeInt32.NB(b, o, w1); encodeInt32.NB(b, o + 4, w2); encodeInt32.NB(b, o + 8, w3); encodeInt32.NB(b, o + 12, w4); encodeInt32.NB(b, o + 16, w5); }
public WindowOneRecord(BinaryReader reader){field_1_h_hold=reader.ReadInt16();field_2_v_hold=reader.ReadInt16();field_3_width=reader.ReadInt16();field_4_height=reader.ReadInt16();field_5_options=reader.ReadInt16();field_6_active_sheet=reader.ReadInt16();field_7_first_visible_tab=reader.ReadInt16();field_8_num_selected_tabs=reader.ReadInt16();field_9_tab_width_ratio=reader.ReadInt16();}
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
try { if (isOpen) { isOpen = false; Dump(); channel.SetLength(fileLength); channel.Close(); fos.Close(); } } catch (System.IO.IOException) { }
public DescribeMatchmakingRuleSetsResult ExecuteDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){request=BeforeClientExecution(request);return DescribeMatchmakingRuleSets(request);}
return surface == null ? null : new string(surface[wordId], off, len);
return pathStr;
public static double r(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){int n=v.Length;double m=0,s=0;for(int i=0;i<n;++i){m+=v[i];}m/=n;for(int i=0;i<n;++i){double d=v[i]-m;s+=d*d;}r=s/n;}return r;}
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool IsPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return end(0);
public void Walk(ICellHandler handler,bool traverseEmptyCells){int firstRow=_range.FirstRow,lastRow=_range.LastRow,firstColumn=_range.FirstColumn,lastColumn=_range.LastColumn,width=lastColumn-firstColumn+1;var ctx=new SimpleCellWalkContext(_range,_sheet);for(int rowNumber=0,rowIdx=firstRow;rowIdx<=lastRow;++rowIdx,++rowNumber){ctx.RowNumber=rowNumber;var currentRow=_sheet.GetRow(rowIdx);ctx.CurrentRow=currentRow;if(currentRow==null){if(traverseEmptyCells){for(int colNumber=0,colIdx=firstColumn;colIdx<=lastColumn;++colIdx,++colNumber){ctx.ColNumber=colNumber;ctx.CurrentCell=null;ctx.OrdinalNumber=checked(rowNumber*width+colNumber);handler.OnCell(ctx);}}continue;}for(int colNumber=0,colIdx=firstColumn;colIdx<=lastColumn;++colIdx,++colNumber){ctx.ColNumber=colNumber;var currentCell=currentRow.GetCell(colIdx);ctx.CurrentCell=currentCell;ctx.OrdinalNumber=checked(rowNumber*width+colNumber);if(!traverseEmptyCells&&(currentCell==null||currentCell.CellType==CellType.Blank))continue;handler.OnCell(ctx);}}}
return pos;
public int CompareTo(ScoreTerm other){return this.boost==other.boost?this.bytes.CompareTo(other.bytes):other.boost.CompareTo(this.boost);}
private static int Normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;break;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);--i;break;}}return len;}
void WriteShort(LittleEndianOutput output){ output.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly){this.exactOnly=exactOnly;}
public class KeySchemaElement{private string attributeName;private KeyType keyType;public void setAttributeName(string attributeName){this.attributeName=attributeName;}public void setKeyType(KeyType keyType){this.keyType=keyType;}public override string ToString(){return"("+attributeName+", "+keyType+")";}}
request = beforeClientExecution(request); GetAssignmentRequest executeGetAssignment = request; return GetAssignmentResult();
bool id(AnyObjectId id){return findOffset(id)!=-1;}
public bool AllGroups => this.allGroups;
public void SetDimConfig(string dimName,bool multiValued){lock(this){DimConfig ft=fieldTypes.ContainsKey(dimName)?fieldTypes[dimName]:null;if(ft==null){ft=new DimConfig(dimName,multiValued);fieldTypes[dimName]=ft;}else{ft.multiValued=multiValued;}}}
public int Size() { int size = 0; foreach (char c in cells.Keys) { Cell e = cmd[c]; if (e.at >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request){ request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
