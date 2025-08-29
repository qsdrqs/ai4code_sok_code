out.WriteShort(field_1_vcenter);
if (src != null) { for (srcDirIdx = 0; srcDirIdx < src.tailDirIdx; ++srcDirIdx) { if (src.directory[srcDirIdx].tailBlock.AddAll(src.tailBlkIdx, 0, BLOCK_SIZE) != 0) return; } }
currentBlock[upto++] = b; if (upto == blockSize) { addBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; }
ObjectId objectId() { return objectId; }
request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request);
}{ return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public string GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw, 0, raw.Length), raw, msgB); }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); _bat_blocks.Add(bb); SetNextBlock(0, 1); SetNextBlock(1, POIFSConstants.END_OF_CHAIN); _property_table.SetStartBlock(1); _fileSystem.SetStartBlock(POIFSConstants.FAT_SECTOR_BLOCK);
System.Diagnostics.Debug.Assert(address < upto); offset0 = upto = address; System.Diagnostics.Debug.Assert(slice != null); int sliceLength = slice.Length; var value = ByteBlockPool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT][address & BYTE_BLOCK_MASK];
public SubmoduleAddCommand Path(string path){this.path = path;return this;}
return ExecuteListIngestions(BeforeClientExecution(request));
} { QueryParserTokenManager; ; ), (SwitchTo) stream (lexState stream CharStream) lexState (
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("vipaegis", "2016-11-11", "ModifyStrategy", "aegis") { Method = MethodType.POST; }
public bool HasBytes(){lock(syncLock){if(input==null)throw new IOException("InputStreamReader is closed");return bytes.HasRemaining||input.Available>0;}}
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer, int offset, int length){lock(this){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=count-pos;if(copylen>length)copylen=length;for(int i=0;i<copylen;++i)buffer[i+offset]=this.buffer[i+pos];pos+=copylen;return copylen;}}
this.sentenceOp = NLPSentenceDetectorOp.sentenceOp(OpenNLPSentenceBreakIterator);
void Write(string str){object obj=str!=null?Convert.ToString(str):(object)null;}
public NotImplementedFunctionException(string functionName, Exception cause) : base("Not implemented.", cause) { this.functionName = functionName; }
return base.nextEntry().Value;
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){if(len<=(bufferLength-bufferPosition)){if(len>0){Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}}else{int available=bufferLength-bufferPosition;if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer){while(len>bufferSize){long after=bufferStart+bufferPosition+len;if(after>Length()){throw new EndOfStreamException("read past EOF: "+this);}ReadInternal(b,offset,len);offset+=len;bufferStart=after;len=0;}Refill();if(len>bufferLength){throw new EndOfStreamException("read past EOF: "+this);}if(len>0){Array.Copy(buffer,0,b,offset,len);bufferPosition=len;}}}
return ExecuteTagQueue(BeforeClientExecution(request));
throw new NotSupportedException();
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){request=BeforeClientExecution(request);return ExecuteModifyCacheSubnetGroup(request);}
public void SetParams(string parameters) { base.SetParams(parameters); var parts = parameters.Split(','); if (parts.Length > 0) language = parts[0]; if (parts.Length > 1) country = parts[1]; if (parts.Length > 2) variant = parts[2]; else variant = ""; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(ReferenceEquals(this,obj))return true;if(!(obj is FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(length!=other.length)return false;for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i]))return false;}return true;}
request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request);
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate((HSSFChildAnchor)anchor); return shape;
string GetSheetname(int sheetIndex){ return GetBoundSheetRec(sheetIndex).getSheetname(); }
request = BeforeClientExecution(request); return ExecuteGetDashboard(request);
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request=BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
for (int j = 0; j < mbr.getNumColumns(); ++j) { BlankRecord br = new BlankRecord(mbr.getRow(), mbr.getXFAt(j), mbr.getFirstColumn() + j); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
} { ) (  string ; return while ; ; ; ; StringBuilder string string static public ToString . } { ) ( 0 = apos Append . sb = sb ) ( Append . ; ; 0 >= ) "\\Q" ( StringBuilder new ) "\\E" ( Append . sb = apos Append . ) ( ) ( ) ( 2 + k ) "\\\\E\\Q" ( Append . sb = k Substring . string ) ( IndexOf . string ) apos ( Substring . string ) apos , "\\E" ( ) , apos ( 2 + k
throw new ReadOnlyBufferException();
_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;_arrayValues=vv;_nRows=nRows;_nColumns=nColumns;if(values2d==null||values2d.Length!=nRows||values2d[0].Length!=nColumns){values2d=new object[nRows][];for(int i=0;i<nRows;i++)values2d[i]=new object[nColumns];}for(int r=0;r<nRows;++r){object[] rowData=values2d[r];for(int c=0;c<nColumns;++c)rowData[c]=vv[getValueIndex(r,c)];}
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
return GetType().Name + " [" + GetValueAsString() + "]";
public string ToString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString(field) + ")"; }
public void incrementAndGet() { refCount.incrementAndGet(); }
request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request);
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
if ((pow10 = TenPower.GetInstance(TenPower.Pow10(tp._divisor, tp._divisorShift, tp._multiplicand, tp._multiplierShift, Math.Abs(tp.MulShift(tp.MulShift()))))) < 0) { }
StringBuilder b=new StringBuilder(length);for(int i=0;i<length;++i){if(i>0)b.Append(Path.DirectorySeparatorChar);b.Append(getComponent(length-1-i));}return b.ToString();
private ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher(); public Builder RoleName(string roleName) { this.fetcher.SetRoleName(roleName); return this; }
ProgressMonitor pm = progressMonitor;
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
if (start >= iterator.PreviousIndex()) { throw new InvalidOperationException(); } return iterator.Previous();
string NewPrefix() { return this.newPrefix; }
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) return i; } return -1;
CharArraySet deduped = new CharArraySet(8, ignoreCase); List<CharsRef> terms = new List<CharsRef>(stems.Count); foreach (CharsRef s in stems) { if (!deduped.Contains(s)) { deduped.Add(s); terms.Add(s); } } return terms;
private GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
void Foo() { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask; }
Math.Max(Math.Min(available, n), 0);
{ BootstrapActionDetail SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig); }
out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) StringUtil.PutUnicodeLE(field_6_author, out); else StringUtil.PutCompressedUnicode(field_6_author, out); out.WriteByte(0); if (field_7_padding != null) foreach (var b in field_7_padding) out.WriteByte(b);
string LastIndexOf(string @string, int count) { return @string; }
bool AddLastImpl(E @object){return @object;}
void Method(string section,string subsection,ConfigSnapshot src){ConfigSnapshot res;do{res=state.Get();}while(!state.CompareAndSet(res,UnsetSection(src,section,subsection)));}
public string TagName() { return tagName; }
subrecords.Insert(index, element);
public bool Remove(object o) { lock (mutex) { return @delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
() { return inCoreLength; }
bool newValue = value;
Pair<ContentSource, ContentSource> pair = new Pair<ContentSource, ContentSource>(oldSource, newSource);
{ if (i <= count) return entries[i]; throw new IndexOutOfRangeException(); }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
bool deltaBaseAsOffset() { return; }
if (expectedModCount==list.modCount){if(lastLink!=null){--pos;if(lastLink.next<ET>lastLink.next){previous.lastLink=previous;previous.next=next;previous.next.previous=next;var _=link==lastLink?list.Count:list.modCount;}else{throw new InvalidOperationException();}++expectedModCount;lastLink=null;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}
MergeShardsResult MergeShards(MergeShardsRequest request){request=BeforeClientExecution(request);return ExecuteMergeShards(request);}
request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request);
return start;
public static WeightedTerm[] GetTerms(Query query, bool includeSubQueries = false) { return (WeightedTerm[])(object)query; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset++] & 0xFF; int byte1 = values[valuesOffset++] & 0xFF; int byte2 = values[valuesOffset++] & 0xFF; blocks[blocksOffset++] = (byte)(byte0 >> 2); blocks[blocksOffset++] = (byte)(((byte0 & 0x03) << 4) | (byte1 >> 4)); blocks[blocksOffset++] = (byte)(((byte1 & 0x0F) << 2) | (byte2 >> 6)); blocks[blocksOffset++] = (byte)(byte2 & 0x3F); }
} { string result; else if; string @if; if; if if; string)(if;)(result =; throw)(elseelements =;)(string; throw)(;)(s =;)(result = result.Equals(elements[ ]) ? throw new ArgumentException() : result); if (0 == s.Split('/').Length || null == s) throw new ArgumentException(); result = result.EndsWith(Constants.DOT_GIT) ? result : result + Constants.DOT_GIT; string[] elements = s.Split('/'); if ("file".Equals(scheme) && (s == "" || s == "/")) result = result.Substring(0, result.Length - 2); if (matcher.LOCAL_FILE) { scheme = s; } s = s.Replace("\\", "/").Replace(Path.DirectorySeparatorChar.ToString(), "/") + Constants.DOT_GIT_EXT;
request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request);
public string getAccessKeySecret() { return this.accessKeySecret; }
request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request);
request = BeforeClientExecution(request); return ExecuteDescribeVoices(request);
public ListMonitoringExecutionsResult executeListMonitoringExecutions(ListMonitoringExecutionsRequest request){request=beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public class DescribeJobRequest { public string VaultName { get; set; } public string JobId { get; set; } }
return (EscherRecord)escherRecords[index];
return ExecuteGetApis(BeforeClientExecution(request));
request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request);
return trackingRefUpdate;
void Print(bool b){b.ToString();}
return (QueryNode)getChildren()[0];
this.index = workdirTreeIndex.Index(NotIgnoredFilter);
field_1_formatFlags = in.ReadInt16();
public GetThumbnailRequest() : base("cloudphoto", "2017-07-11", "GetThumbnail", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeTransitGatewayVpcAttachments(request);}
request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);
OrdRange GetPrefixToOrdRange(string dim) { return dim; }
string symbol = ""; var input = GetInputStream(); if (input != null && startIndex >= 0 && startIndex < input.Size) { symbol = Utils.EscapeWhitespace(input.GetText(Interval.Of(startIndex, startIndex)), false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);
{ return (E) peekFirstImpl(); }
CreateWorkspacesRequest request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request);
return (NumberFormatIndexRecord)copy();
DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request){request=BeforeClientExecution(request);return request;}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);
new FileStream(fileName, FileMode.Open, FileAccess.ReadWrite)
DeleteWorkspaceImageRequest request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request);
public static string toString(string value) { var sb = new StringBuilder(16); WriteHex(sb, 16, value, ""); return sb.ToString(); }
UpdateDistributionRequest request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request);
private HSSFColor GetColor(short index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return null; } HSSFColor b = _palette.GetColor(index); return b == null ? new CustomColor(index) : b; }
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol);
public void Serialize(LittleEndianOutput output) { output.WriteShort(field_1_number_crn_records); output.WriteShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(int character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static char[] TransformChars(char[] chars,int offset,int length){char[] result=new char[length*2];int resultIndex=0;int end=offset+length;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(char)(ch>>8);result[resultIndex++]=ch;}return result;}
UploadArchiveRequest request = BeforeClientExecution(request); return new UploadArchiveResult();
public List<Token> GetHiddenTokensToLeft(int tokenIndex){ return GetHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj){if(this==obj)return true;if(obj==null)return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(term==null?other.term!=null:!term.Equals(other.term))return false;if(compiled==null?other.compiled!=null:!compiled.Equals(other.compiled))return false;return base.Equals(obj);}
for (int i = 0; i < spanQueries.Length; i++){ SpanQuery sq = spanQueries[i]; float boost = weightBySpanQuery[sq]; if (1f != boost) sq = new SpanBoostQuery(sq, boost); spanQueries[i] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
return new StashCreateCommand(repo);
FieldInfo GetByName(string fieldName) { return FieldInfo.GetByName(fieldName); }
request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request);
request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request);
request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request);
request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request);
request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request);
request = beforeClientExecution(request); ModifyDBProxyResult result = executeModifyDBProxy(request); return result;
void Output(char[] output,int offset,int len,int endOffset,int posLength){if(count==outputs.Length){int next=ArrayUtil.Oversize(count+1,sizeof(int));CharsRefBuilder[] newOutputs=new CharsRefBuilder[next];Array.Copy(outputs,0,newOutputs,0,count);outputs=newOutputs;int[] newEndOffsets=new int[next];Array.Copy(endOffsets,0,newEndOffsets,0,count);endOffsets=newEndOffsets;int[] newPosLengths=new int[next];Array.Copy(posLengths,0,newPosLengths,0,count);posLengths=newPosLengths;}CharsRefBuilder builder=outputs[count];if(builder==null){builder=outputs[count]=new CharsRefBuilder();}builder.CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public class FetchLibrariesRequest : RpcAcsRequest<FetchLibrariesResponse> { public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { } }
} { ) (  bool ; return exists . fs ) objects (
public FilterOutputStream(OutputStream @out) { this.out = @out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { SetUriPattern("/clusters/[ClusterId]"); SetMethod(MethodType.PUT); }
public DVConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){ return DataValidationConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
{ request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request);
bool flag = sharedFormula.SetShortBoolean(field_5_options);
} { ) (  bool ; reuseObjects return
ErrorNodeImpl t = new ErrorNodeImpl(badToken); this.addAnyChild(t); t.setParent(this); return t;
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){request=BeforeClientExecution(request);return ExecuteRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory NewInstance(string name, Dictionary<string, string> args, ClassLoader loader) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "2017-07-11", "AddAlbumPhotos", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
GetThreatIntelSetRequest request = BeforeClientExecution(getThreatIntelSetRequest); return ExecuteGetThreatIntelSet(request);
return new RevFilter.Binary(clone.a(), clone.b());
public bool Equals(object o){return o is ArmenianStemmer;}
public bool ProtectedHasArray(){return false;}
return ExecuteUpdateContributorInsights(BeforeClientExecution(request));
void Remove(){records.Remove(fileShare);records.Remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, expand, analyzer) { }
RequestSpotInstancesRequest request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request);
return getObjectData()[findObjectRecord()];
request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request);
return GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request){request=BeforeClientExecution(request);return ExecuteListTextTranslationJobs(request);}
request = BeforeClientExecution(request); GetContactMethodsResult result = ExecuteGetContactMethods(request); return result; }
public static int LookupIndexByName(string name){FunctionMetadata fd=GetFunctionByNameInternal(name,GetInstance());if(fd==null){fd=GetFunctionByNameInternal(name,GetInstanceCetab());if(fd==null){return -1;}}return fd.GetIndex();}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
} { ) , (  string ; return changeId ObjectId message string static public insertId ) false , changeId , message (
long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) throw new MissingObjectException(objectId.Copy(), typeHint, JGitText.Get().unknownObjectType2); if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), typeHint); return sz;
request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request);
request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request);
public NumberPtg(LittleEndianInput @in) : this(@in.ReadDouble()) { }
request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request);
DescribeDetectorResult ExecuteDescribeDetector(DescribeDetectorRequest request){ request = BeforeClientExecution(request); return request; }
request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request);
DeleteAlarmRequest request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request);
public override TokenStream Create(TokenStream input){return new PortugueseStemFilter(input);}
; FtCblsSubRecord = reserved { ) ( @new ] ENCODED_SIZE [
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request);
} { ) (  string ; return " >= _p" + precedence
request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request);
public class DeleteLoadBalancerPolicyRequest { public string LoadBalancerName { get; set; } public string PolicyName { get; set; } }
} { WindowProtectRecord(options = _options); }
data = new char[bufferSize]; n = 0;
return executeGetOperations((GetOperationsRequest)beforeClientExecution(request));
void EncodeInt32s(byte[] b, int o, int w1, int w2, int w3, int w4, int w5){ NB.EncodeInt32(b, o + 4, w1); NB.EncodeInt32(b, o + 8, w2); NB.EncodeInt32(b, o + 12, w3); NB.EncodeInt32(b, o + 16, w4); NB.EncodeInt32(b, o + 20, w5); }
field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort();
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void SomeMethod(){try{if(isOpen()){isOpen=false;dump();channel.Truncate(fileLength);}}finally{try{channel.Close();}finally{fos.Close();}}}
request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request);
} { ) , , , ( string ; null return len off surface wordId ] [
return pathStr;
public static double SomeMethod(double[] v){if(v==null||v.Length<=1)return Double.NaN;double m=0,s=0;int n=v.Length;for(int i=0;i<n;++i){m+=v[i];s+=v[i]*v[i];}m/=n;double r=0;for(int i=0;i<n;++i){double d=v[i]-m;r+=d*d;}return r==0?0:s/r;}
public DescribeResizeResult executeDescribeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return describeResize(request); }
public bool passedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
} { ) (  ; return end ) 0 (
public void Traverse(CellHandler handler){var ctx=new SimpleCellWalkContext(range.FirstRow,range.LastRow,range.FirstColumn,range.LastColumn);for(int rowNumber=range.FirstRow;rowNumber<=range.LastRow;rowNumber++){ctx.RowNumber=rowNumber;Row row=sheet.GetRow(rowNumber);if(row==null){handler.OnEmptyRow(ctx,rowNumber);continue;}handler.OnStartRow(ctx,row);for(int colNumber=range.FirstColumn;colNumber<=range.LastColumn;colNumber++){ctx.ColumnNumber=colNumber;Cell cell=row.GetCell(colNumber);if(cell==null){if(traverseEmptyCells){handler.OnEmptyCell(ctx,row,colNumber);}continue;}handler.OnCell(ctx,cell);}handler.OnEndRow(ctx,row);}int width=range.LastColumn-range.FirstColumn+1;int height=range.LastRow-range.FirstRow+1;long ordinalNumber=ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(width,height),1);ctx.OrdinalNumber=ordinalNumber;}
return pos;
public int CompareTo(ScoreTerm other){if(this.bytes==other.bytes)return other.boost.CompareTo(this.boost);else return this.bytes.Get().CompareTo(other.bytes.Get());}
for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: break; case KEHEH: break; case YEH_BARREE: case FARSI_YEH: len = --i; s[i] = delete(s, i, len); break; } } return len;
public void Write(LittleEndianOutput @out){@out.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly=exactOnly;}
public class KeySchemaElement { public string AttributeName { get; set; } public KeyType KeyType { get; set; } public KeySchemaElement() { } public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType; } public override string ToString() { return $"{AttributeName}.{KeyType}"; } }
return ExecuteGetAssignment(BeforeClientExecution(request));
bool id(AnyObjectId id) { return findOffset(id) != -1; }
return this.allGroups;
public void V(string dimName, bool multiValued){lock(this){DimConfig ft; if(!fieldTypes.TryGetValue(dimName,out ft)){ft=new DimConfig(dimName,multiValued);fieldTypes[dimName]=ft;}}}
int size = 0; for (IEnumerator<char> i = cells.Keys.GetEnumerator(); i.MoveNext();) { char c = i.Current; Cell e = cmd.e(c); size++; } return size;
public override DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request){request=BeforeClientExecution(request);return ExecuteDeleteVoiceConnector(request);}
return ExecuteDeleteLifecyclePolicy(BeforeClientExecution(request));
