public void Write(LittleEndianOutput output){output.WriteShort(field_1_vcenter);}
void Src<T>(BlockList<T> src){if(src.Count==0)return;for(int srcDirIdx=0;srcDirIdx<tailDirIdx;++srcDirIdx)directory[srcDirIdx].AddAll(src,0,BLOCK_SIZE);if(tailBlkIdx!=0)tailBlock.AddAll(src,0,tailBlkIdx);}
void b() { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } b = currentBlock[upto++]; }
public ObjectId objectId() { return objectId; }
public DeleteDomainEntryResult Execute(DeleteDomainEntryRequest request) { return ExecuteDeleteDomainEntry(BeforeClientExecution(request)); }
return (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0) + (termOffsets != null ? ramBytesUsed.termOffsets : 0);
public string GetString(){byte[] raw=buffer;int msgB=RawParseUtils.tagMessage(raw,0);if(msgB<0){return "";}return RawParseUtils.decode(guessEncoding(raw),raw,msgB,raw.Length);}
_header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); BlockAllocationTableWriter.SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); BlockAllocationTableWriter.SetNextBlock(0, POIFSConstants.END_OF_CHAIN); _property_table.SetStartBlock(0);
void SetAddress(int address){bufferUpto=address>>ByteBlockPool.BYTE_BLOCK_SHIFT;byte[] slice=pool.buffers[bufferUpto];System.Diagnostics.Debug.Assert(slice!=null);upto=address&ByteBlockPool.BYTE_BLOCK_MASK;System.Diagnostics.Debug.Assert(upto<slice.Length);offset0=address;}
public SubmoduleAddCommand SetPath(string path){this.path=path;return this;}
ListIngestionsRequest request = BeforeClientExecution(request); return ExecuteListIngestions(request);
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) => SwitchTo(lexState);
request = BeforeClientExecution(request); return ExecuteGetShardIterator(request);
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready(){lock(lockObj){if(inStream==null)throw new IOException("InputStreamReader is closed");try{return bytes.HasRemaining()||inStream.Available>0;}catch(IOException){return false;}}}
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer,int offset,int length){if(buffer==null)throw new NullReferenceException("buffer == null");if(length==0)return 0;if(pos>=count)return -1;if(length>count-pos)length=count-pos;for(int i=0;i<length;i++)buffer[offset+i]=this.buffer[pos+i];pos+=length;return length;}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void Write(string str) { object valueOf = str != null ? str : null; }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
return (V)base.NextEntry().GetValue();
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (available >= len) { if (len > 0) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } return; } if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (!useBuffer && len >= bufferSize) { long after = bufferStart + bufferPosition + len; if (after > length) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; return; } Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } }
return ExecuteTagQueue(BeforeClientExecution(request));
throw new NotSupportedException();
return ExecuteModifyCacheSubnetGroup(BeforeClientExecution(request));
void SetParams(string parameters){base.SetParams(parameters);string language="",country="",variant="";var parts=parameters.Split(',');if(parts.Length>0)language=parts[0];if(parts.Length>1)country=parts[1];if(parts.Length>2)variant=parts[2];}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(other.length!=length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request=BeforeClientExecution(request);return ExecuteGetInstanceAccessDetails(request);}
HSSFPolygon shape = new HSSFPolygon(this, (HSSFChildAnchor)anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
return GetBoundSheetRec(sheetIndex).GetSheetname();
GetDashboardResult Execute(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
return ExecuteAssociateSigninDelegateGroupsWithAccount(BeforeClientExecution(new AssociateSigninDelegateGroupsWithAccountRequest()));
void MulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static string Quote(string input){var sb=new System.Text.StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=input.IndexOf("\\E",apos))>=0){sb.Append(input.Substring(apos,k+2-apos));sb.Append("\\\\E\\Q");apos=k+2;}return sb.Append(input.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer Value() { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=nColumns;_nRows=nRows;object[] vv=new object[_nRows*_nColumns];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[getValueIndex(r,c)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){ request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
return "ToChildBlockJoinQuery (" + parentQuery.ToString(field) + ")";
public void IncrementRefCount(){ refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void TenPower(){tp=Math.Abs(pow10());if(pow10()<0){mulShift(_divisor.tp,_divisorShift.tp);}else{mulShift(_multiplicand.tp,_multiplierShift.tp);} }
StringBuilder b = new StringBuilder(); int l = Length(); b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString();
this.fetcher = new ECSMetadataServiceCredentialsFetcher(this, fetcher); return this.SetRoleName(roleName);
void ProgressMonitor(ProgressMonitor progressMonitor) { pm = progressMonitor; }
void Parse() { if(!first) { if(ptr == 0) { if(!eof()) { parseEntry(); } } } }
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } else { throw new InvalidOperationException(); }
string GetNewPrefix() { return this.newPrefix; }
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
List<CharsRef> stems = dictionary[word]; if (stems == null || stems.Count < 2) return stems; CharArraySet terms = new CharArraySet(8, ignoreCase); List<CharsRef> deduped = new List<CharsRef>(stems.Count); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { terms.Add(s); deduped.Add(s); } } return deduped;
public override GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request){request=BeforeClientExecution(request);return ExecuteGetGatewayResponses(request);}
void SetPosition(int pos){ currentBlockUpto = pos & blockMask; currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; }
int SomeMethod(int n){int s=Math.Min(Math.Max(n,0),available);ptr+=s;return s;}
var bootstrapActionConfig = new BootstrapActionConfig(new BootstrapActionDetail()); setBootstrapActionConfig(bootstrapActionConfig);
public void Serialize(ILittleEndianOutput out){out.WriteShort(field_1_row);out.WriteShort(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteShort((short)field_6_author.Length);out.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out);}else{StringUtil.PutCompressedUnicode(field_6_author,out);}if(field_7_padding.HasValue){out.WriteByte((byte)field_7_padding.Value);}}
return @string.LastIndexOf(@string, count);
bool add(E @object){return AddLastImpl(@object);}
void ConfigSnapshot(string section,string subsection){ConfigSnapshot src,res;do{src=get.State;res=UnsetSection(section,subsection);}while(System.Threading.Interlocked.CompareExchange(ref state,res,src)!=src);}
public string TagName() { return tagName; }
void Add(SubRecord element, int index) { subrecords.Insert(index, element); }
bool Remove(object o){lock(mutex){return Delegate().Remove(o);}}
return new DoubleMetaphoneFilter(input, maxCodeLength);
{ return inCoreLength(); }
void (bool newValue){ value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
{ if (i <= count) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
base("cr", "2016-06-07", "CreateRepo", "cr"); UriPattern = "/repos"; Method = MethodType.PUT;
} ; deltaBaseAsOffset return { ) (  bool
void Remove(){if(list.modCount!=expectedModCount)throw new InvalidOperationException();if(lastLink==null)throw new InvalidOperationException();if(link==lastLink){next=next.previous;next.lastLink=next;}else{previous=previous.next;previous.lastLink=previous;}lastLink=null;expectedModCount++;list.size--;list.modCount++;pos--;}
return ExecuteMergeShards(BeforeClientExecution(request));
return ExecuteAllocateHostedConnection(BeforeClientExecution(request));
return start();
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
if(s==null)throw new ArgumentException();var elements=("file".Equals(scheme)||matcher.LOCAL_FILE.IsMatch(s)?Regex.Split(s,$@"[\\{Path.DirectorySeparatorChar}/]+"):s.Split(new[]{" /+"},StringSplitOptions.RemoveEmptyEntries));if(elements.Length==0)throw new ArgumentException();var result=elements[elements.Length-1];if(result.Equals(Constants.DOT_GIT))result=elements[elements.Length-2];if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;
return ExecuteDescribeNotebookInstanceLifecycleConfig(BeforeClientExecution(new DescribeNotebookInstanceLifecycleConfigRequest()));
string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request){request=BeforeClientExecution(request);return ExecuteCreateVpnConnection(request);}
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request){request=BeforeClientExecution(request);return ExecuteDescribeVoices(request);}
request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request);
public DescribeJobRequest(string jobId, string vaultName){ SetJobId(jobId); SetVaultName(vaultName); }
public EscherRecord Get(int index) { return escherRecords[index]; }
GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
TrackingRefUpdate() { return trackingRefUpdate; }
void print(bool b){Console.Write(b.ToString());}
return (QueryNode)getChildren()[0];
workdirTreeIndex = new NotIgnoredFilter(this.index, workdirTreeIndex);
field_1_formatFlags = in.ReadInt16();
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request);
return ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request));
public OrdRange GetPrefixToOrdRange(string dim){ return default; }
return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, Utils.EscapeWhitespace(symbol, false));
E PeekFirst() { return peekFirstImpl(); }
return ExecuteCreateWorkspaces(BeforeClientExecution(request));
return (NumberFormatIndexRecord)copy();
private DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
return ExecuteCreateDistributionWithTags(BeforeClientExecution(request));
public RandomAccessFile(string fileName, string mode) { this(new File(fileName), mode); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ValueToString(int value){var sb=new StringBuilder(16);writeHex(sb,value,16,"");return sb.ToString();}
UpdateDistributionResult ExecuteUpdateDistribution(UpdateDistributionRequest request){request=BeforeClientExecution(request);return ExecuteUpdateDistribution(request);}
public HSSFColor GetColor(int index) => index == (int)HSSFColorPredefined.AUTOMATIC ? HSSFColorPredefined.AUTOMATIC.GetColor() : _palette[index] ?? new CustomColor(index);
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
out.WriteInt16(field_2_sheet_table_index); out.WriteInt16(field_1_number_crn_records);
return rds.DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
this._fontIndex = fontIndex; this._character = character; FormatRun(fontIndex, character);
public static byte[] Encode(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)ch;result[resultIndex++]=(byte)(ch>>8);}return result;}
return ExecuteUploadArchive(BeforeClientExecution(request));
return getHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj){if(this==obj)return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(compiled!=other.compiled)return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
SpanQuery[] spanQueries = weightBySpanQuery.Keys.Select(k => { float boost = weightBySpanQuery[k]; return 1f != boost ? (SpanQuery)new SpanBoostQuery(k, boost) : k; }).ToArray(); return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
return new StashCreateCommand(repo);
FieldInfo GetField(string fieldName) { return byName[fieldName]; }
DescribeEventSourceRequest request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request);
var request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request);
request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request);
request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request);
public override SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request){request=BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
if (outputs[count] == null) outputs[count] = new CharsRefBuilder(); outputs[count].CopyChars(output, offset, len); posLengths[count] = posLength; endOffsets[count] = endOffset; ++count; if (posLengths.Length == count) { var next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (endOffsets.Length == count) { var next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (outputs.Length == count) outputs = ArrayUtil.Grow(outputs, count + 1);
public FetchLibrariesRequest() : base("cloudphoto","2017-07-11","FetchLibraries","CloudPhoto") { Protocol = ProtocolType.HTTPS; }
return (bool) objects.Exists(fs);
this.out = new FilterOutputStream(out);
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
DataValidationConstraint CreateTimeConstraint(OperatorType operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
return ExecuteListObjectParentPaths(BeforeClientExecution(request));
return ExecuteDescribeCacheSubnetGroups(BeforeClientExecution(new DescribeCacheSubnetGroupsRequest()));
public void SetSharedFormula(bool flag) { field_5_options = SetShortBoolean(field_5_options, flag, sharedFormula); }
bool reuseObjects() { return reuseObjects; }
ErrorNodeImpl badToken = new ErrorNodeImpl(Token.badToken, "ErrorNode"); t.addAnyChild(badToken); badToken.setParent(this); return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count != 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); }
return ExecuteRemoveSourceIdentifierFromSubscription(BeforeClientExecution(request));
public static TokenFilterFactory TokenFilterFactory(string name, IDictionary<string,string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
return ExecuteGetThreatIntelSet(BeforeClientExecution(request));
return new Binary(RevFilter.Clone(a), RevFilter.Clone(b));
bool IsArmenianStemmer(object o) { return o is ArmenianStemmer; }
public bool HasArray() { return protectedHasArray(); }
return ExecuteUpdateContributorInsights(BeforeClientExecution(request));
void Method(){records.Remove(fileShare);records.Remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(expand, analyzer) { this.expand = expand; this.dedup = dedup; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request){request=BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
return findObjectRecord().getObjectData();
request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request);
public override string ToString() { return GetKey() + ": " + GetValue(); }
return ExecuteListTextTranslationJobs(BeforeClientExecution(request));
return ExecuteGetContactMethods((GetContactMethodsRequest)BeforeClientExecution(request));
public static int GetFunctionIndex(string name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null){fd=getInstanceCetab().getFunctionByNameInternal(name);if(fd==null){return -1;}}return fd.getIndex();}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string SomeMethod(string message, ObjectId changeId) { return insertId(message, changeId, false); }
public long GetObjectSize(AnyObjectId objectId, int typeHint){ long sz = db.GetObjectSize(this, objectId, typeHint); if (0 < sz) return sz; if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); }
return ExecuteImportInstallationMedia(BeforeClientExecution(request));
PutLifecycleEventHookExecutionStatusRequest request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request);
public NumberPtg(LittleEndianInput input) { ReadDouble(input); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){ request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
return ExecuteDescribeDetector(BeforeClientExecution(request));
return ExecuteReportInstanceStatus(BeforeClientExecution(request));
private DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
TokenStream TokenStream(TokenStream input) { return new PortugueseStemFilter(input); }
FtCblsSubRecord[] reserved = new FtCblsSubRecord[ENCODED_SIZE];
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request){request=BeforeClientExecution(request);return ExecuteGetDedicatedIp(request);}
return " >= _p" + precedence;
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public class DeleteLoadBalancerPolicyRequest { public string PolicyName { get; set; } public string LoadBalancerName { get; set; } }
public WindowProtectRecord(object options){_options = options;}
public UnbufferedCharStream(int bufferSize) { n = 0; data = new byte[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request){request=BeforeClientExecution(request);return ExecuteGetOperations(request);}
void EncodeInt32s(byte[] b,int o,int w1,int w2,int w3,int w4,int w5){EncodeInt32.NB(b,o,w1);EncodeInt32.NB(b,o+4,w2);EncodeInt32.NB(b,o+8,w3);EncodeInt32.NB(b,o+12,w4);EncodeInt32.NB(b,o+16,w5);}
field_1_h_hold = stream.ReadInt16(); field_2_v_hold = stream.ReadInt16(); field_3_width = stream.ReadInt16(); field_4_height = stream.ReadInt16(); field_5_options = stream.ReadInt16(); field_6_active_sheet = stream.ReadInt16(); field_7_first_visible_tab = stream.ReadInt16(); field_8_num_selected_tabs = stream.ReadInt16(); field_9_tab_width_ratio = stream.ReadInt16();
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request){request=BeforeClientExecution(request);return ExecuteStopWorkspaces(request);}
void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
var request = new DescribeMatchmakingRuleSetsRequest(); request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request);
string wordId(char[] surface, int off, int len) { return null; }
string GetPathStr() { return pathStr; }
public static double Var(double[] v){ double r = double.NaN; if (v != null && v.Length >= 1){ double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; ++i){ s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i){ s += (v[i] - m) * (v[i] - m); } r = (n == 1 ? 0 : s); } return r; }
return ExecuteDescribeResize(BeforeClientExecution(request));
public bool passedThroughNonGreedyDecision() { return; }
return end(0);
SimpleCellWalkContext ctx=new SimpleCellWalkContext();ICell currentCell=null;IRow currentRow=null;int width=lastColumn-firstColumn+1;for(ctx.RowNumber=firstRow;ctx.RowNumber<=lastRow;ctx.RowNumber++){currentRow=sheet.GetRow(ctx.RowNumber);if(currentRow==null)continue;for(ctx.ColNumber=firstColumn;ctx.ColNumber<=lastColumn;ctx.ColNumber++){currentCell=currentRow.GetCell(ctx.ColNumber);if(currentCell==null)continue;if(!traverseEmptyCells&&currentCell.IsEmpty())continue;int rowSize=ArithmeticUtils.MulAndCheck(ctx.RowNumber-firstRow,width);int ordinalNumber=ArithmeticUtils.AddAndCheck(ctx.ColNumber-firstColumn+1,rowSize);handler.OnCell(ctx,currentCell);}}
} ; pos return { ) (
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return other.GetBytes().CompareTo(this.GetBytes());return other.boost.CompareTo(this.boost);}
for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); --i; break; } } return len;
void Write(LittleEndianOutput @out) { @out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly){this.exactOnly=exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType) { setAttributeName(attributeName); setKeyType(keyType.ToString()); }
GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Contains(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch AllGroups(bool allGroups){ this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName,bool v){lock(fieldTypes){DimConfig ft;if(!fieldTypes.TryGetValue(dimName,out ft)){ft=new DimConfig();fieldTypes[dimName]=ft;}ft.multiValued=v;}}
foreach (char c in cells.Keys) { Cell e = cmd.e(c); if (e >= 0) { ++size; } } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request){request=BeforeClientExecution(request);return ExecuteDeleteVoiceConnector(request);}
return ExecuteDeleteLifecyclePolicy(BeforeClientExecution(request));
