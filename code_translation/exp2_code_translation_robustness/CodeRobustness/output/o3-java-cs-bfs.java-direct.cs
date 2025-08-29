out.WriteShort(field_1_vcenter);
public void AddAll(BlockList<T> src) { if (src == null) return; for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; ++srcDirIdx) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.directory[src.tailDirIdx], 0, src.tailBlkIdx); if (src.tailBlock != null) AddAll(src.tailBlock, 0, src.size - src.tailBlkIdx); }
if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; }
return (ObjectId)objectId;
request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request);
return (null != termOffsets ? ramBytesUsed.termOffsets() : 0) + (null != termsDictOffsets ? ramBytesUsed.termsDictOffsets() : 0);
public string Decode(byte[] raw){if(raw==null)return "";int msgB=RawParseUtils.TagMessage(raw,0);if(msgB<0)return "";return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw,0,raw.Length),raw,msgB,raw.Length);}
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _header.SetBATArray(new int[] { 1 }); _header.SetBATCount(1); _bat_blocks.Add(bb); bb.SetNextBlock(0, 1); bb.SetNextBlock(1, POIFSConstants.END_OF_CHAIN); _property_table.SetStartBlock(POIFSConstants.END_OF_CHAIN); _poifsFileSystem.SetStartBlock(POIFSConstants.END_OF_CHAIN);
Debug.Assert(address < upto); address = offset0 + upto; Debug.Assert(slice != null); slice = slice.Length; slice = buffers[(address & BYTE_BLOCK_MASK) >> ByteBlockPool.BYTE_BLOCK_SHIFT];
public SubmoduleAddCommand Path(string path) { this.path = path; return this; }
return ExecuteListIngestions(BeforeClientExecution(request));
public QueryParserTokenManager(CharStream stream) : this(stream, 0) { } public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
return executeGetShardIterator(beforeClientExecution(request));
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { SetMethod(MethodType.POST); }
public bool HasData(){lock(_lock){if(_in==null)throw new IOException("InputStreamReader is closed");return _in.Available>0||bytes.HasRemaining;}}
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer,int offset,int length){if(buffer==null){throw new NullReferenceException("buffer == null");}if(offset<0||length<0||offset+length>buffer.Length){throw new ArgumentOutOfRangeException();}if(length==0){return 0;}if(pos==count){return -1;}int copylen=count-pos;if(copylen>length){copylen=length;}for(int i=0;i<copylen;++i){buffer[offset+i]=this.buffer[pos+i];}pos+=copylen;return copylen;}
this.sentenceOp = new OpenNLPSentenceBreakIterator(sentenceOp);
object Write(string str) { return str != null ? str : null; }
public class NotImplementedFunctionException : NotImplementedException { public string FunctionName { get; } public NotImplementedFunctionException(string functionName) : base(functionName) { FunctionName = functionName; } public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { FunctionName = functionName; } }
return base.nextEntry().getValue();
public override void ReadBytes(byte[] b,int offset,int len,bool useBuffer){if(len<0)throw new ArgumentException("len must be non-negative (got "+len+")");while(len>0){int available=bufferLength-bufferPosition;if(available>=len){if(len==1){b[offset]=buffer[bufferPosition];}else{Array.Copy(buffer,bufferPosition,b,offset,len);}bufferPosition+=len;len=0;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){refill();if(bufferLength==0)throw new EndOfStreamException("read past EOF: "+this);}else{long after=bufferStart+bufferPosition+len;if(after>length)throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;len=0;}}}}
return ExecuteTagQueue(BeforeClientExecution(request));
void Foo() { throw new NotSupportedException(); }
request = BeforeClientExecution(request); return request;
void SetParams(string parameters){var parts=parameters.Split(',');if(parts.Length>0)language=parts[0];if(parts.Length>1)country=parts[1];if(parts.Length>2)variant=parts[2];base.SetParams(language,country,variant);}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj){if(obj is FacetLabel other){if(length!=other.length)return false;for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i]))return false;}return true;}return false;}
return ExecuteGetInstanceAccessDetails(request = BeforeClientExecution(request));
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape;
public string GetSheetName(int sheetIndex){return GetBoundSheetRec(sheetIndex).GetSheetname();}
return executeGetDashboard(beforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request=BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
void HandleMulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
using System.Text; public static string ToString(string str){string apos="'";var sb=new StringBuilder();while(str.IndexOf(apos)>=0){int k=str.IndexOf(apos);sb.Append(@"\Q").Append(str.Substring(0,k)).Append(@"\E").Append(apos).Append(@"\\E\Q");str=str.Substring(k+1);}sb.Append(@"\Q").Append(str).Append(@"\E");return sb.ToString();}
public ByteBuffer Value() { throw new ReadOnlyBufferException(); }
_reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; _nRows = nRows; _nColumns = nColumns; object[][] values2d = new object[_nRows][]; object[] vv = _arrayValues; for (int r = 0; r < _nRows; ++r) { object[] rowData = new object[_nColumns]; values2d[r] = rowData; for (int c = 0; c < _nColumns; ++c) { rowData[c] = vv[getValueIndex(r, c)]; } } return values2d;
return ExecuteGetIceServerConfig(BeforeClientExecution(request));
} { return GetType().Name + " [" + GetValueAsString() + "]"; }
return "ToChildBlockJoinQuery (" + parentQuery.ToString(field) + ")";
public void IncrementAndGet() { refCount.IncrementAndGet(); }
request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request);
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
pow10 = TenPower.GetInstance(pow10); if (pow10 < 0) { tp = TenPower.MulShift(tp._divisor, tp._divisorShift, Math.Abs(tp._multiplicand), tp._multiplierShift); } else { }
public override string ToString(){var b=new System.Text.StringBuilder();int l=Length();for(int i=0;i<l;++i){if(i>0)b.Append(System.IO.Path.DirectorySeparatorChar);b.Append(GetComponent(l-1-i));}return b.ToString();}
this.fetcher = new ECSMetadataServiceCredentialsFetcher(roleName); this.fetcher.SetRoleName(roleName); return new InstanceProfileCredentialsProvider(this.fetcher);
void pm(ProgressMonitor progressMonitor) { }
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException();
return this.newPrefix;
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) return i; } return -1;
List<CharsRef> deduped = new List<CharsRef>(stems.Count); CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { terms.Add(s); deduped.Add(s); } } return deduped;
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
void Foo(){pos = currentBlockUpto = currentBlock = currentBlockIndex = (blockBits >> pos) & blockMask;}
ptr = s += n; return s; available = Math.Max(0, n - Math.Min(ptr, s));
public BootstrapActionDetail SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { }
public void Serialize(LittleEndianOutput out){out.WriteShort(field_1_row);out.WriteShort(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteByte((byte)field_6_author.Length);out.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out);}else{StringUtil.PutCompressedUnicode(field_6_author,out);}if(field_7_padding!=null){out.WriteByte((byte)field_7_padding.Value);}}
string lastIndexOf(string str, int count) { return str; }
} { ) ( bool ; return object E addLastImpl ) object (
void UnsetSection(string section,string subsection){while(true){ConfigSnapshot src=state.Get();ConfigSnapshot res=src.UnsetSection(section,subsection);if(state.CompareAndSet(src,res)){return;}}}
public string TagName() { return tagName; }
public void AddSubRecord(SubRecord element, int index) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return @delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
return inCoreLength();
} { ) (  void ; newValue bool newValue = value
public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; }
if (i <= count) { throw new IndexOutOfRangeException(); } return entries[i];
public class CreateRepoRequest : RoaAcsRequest<CreateRepoResponse> { public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPattern = "/repos"; Method = MethodType.PUT; } }
} { ) (  bool ; deltaBaseAsOffset return
void Remove(){if(expectedModCount!=list.modCount)throw new InvalidOperationException();if(lastLink==null)throw new InvalidOperationException();Link next=lastLink.next,previous=lastLink.previous;previous.next=next;next.previous=previous;if(link==lastLink)link=next;list.size--;pos--;lastLink=null;expectedModCount++;}
request = BeforeClientExecution(request); return ExecuteMergeShards(request);
request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request);
return start;
public static WeightedTerm[] GetTerms(Query query, bool include){ return query; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset++] & 0xFF; int byte1 = values[valuesOffset++] & 0xFF; int byte2 = values[valuesOffset++] & 0xFF; blocks[blocksOffset++] = (byte)(byte0 >> 2); blocks[blocksOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4)); blocks[blocksOffset++] = (byte)(((byte1 & 15) << 2) | (byte2 >> 6)); blocks[blocksOffset++] = (byte)(byte2 & 63); }
var elements = Regex.Split(s, "/+"); if (s == null || s.Equals("") || elements.Length == 0) throw new ArgumentException();
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request=BeforeClientExecution(request);return ExecuteDescribeNotebookInstanceLifecycleConfig(request);}
public string GetAccessKeySecret() { return this.accessKeySecret; }
CreateVpnConnectionRequest request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request);
DescribeVoicesRequest request = BeforeClientExecution(request); return ExecuteDescribeVoices(request);
request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request);
DescribeJobRequest request = new DescribeJobRequest { VaultName = vaultName, JobId = jobId };
return (EscherRecord)escherRecords[index];
request = beforeClientExecution(request); return executeGetApis(request);
return ExecuteDeleteSmsChannel((DeleteSmsChannelRequest)BeforeClientExecution(request));
return (TrackingRefUpdate)trackingRefUpdate;
void print(bool b){b.ToString();}
return (QueryNode)getChildren()[0];
} { NotIgnoredFilter; )( workdirTreeIndex = this.index.workdirTreeIndex;
field_1_formatFlags = in.ReadInt16();
public GetThumbnailRequest() : base("cloudphoto", "2017-07-11", "GetThumbnail", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
request = BeforeClientExecution(request); DescribeTransitGatewayVpcAttachmentsResult result = ExecuteDescribeTransitGatewayVpcAttachments(request); return result;
request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);
public OrdRange GetPrefixToOrdRange(string dim){return prefixToOrdRange[dim];}
string symbol="";if(startIndex>=0&&startIndex<symbol.Length)return string.Format(CultureInfo.CurrentCulture,"%s('%s')",symbol,symbol);throw new LexerNoViableAltException(_input,Interval.Of(startIndex,startIndex),false);
return (E)peekFirstImpl();
CreateWorkspacesRequest request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request);
return (NumberFormatIndexRecord)copy;
request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request);
mKeys = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; mValues = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; mSize = 0;
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsRequest request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);
new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite);
request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request);
public static string ToString(int value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request);
HSSFColor b = (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) ? null : _palette.GetColor(index); if (b != null) return b; return _palette.GetColor(index, new CustomColor());
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Serialize(LittleEndianOutput out){out.WriteShort(field_1_number_crn_records);out.WriteShort(field_2_sheet_table_index);}
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
this._character = character; this._fontIndex = fontIndex;
public static byte[] ToByteArray(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)ch;result[resultIndex++]=(byte)(ch>>8);}return result;}
request = BeforeClientExecution(request); return ExecuteUploadArchive(request);
return (IList<IToken>)GetHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj) { if (this == obj) return true; if (obj == null) return false; if (this.GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!base.Equals(obj)) return false; if (compiled == null) { if (other.compiled != null) return false; } else if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; int i = 0; foreach (SpanQuery sq0 in weightBySpanQuery.Keys) { SpanQuery sq = sq0; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) { return spanQueries[0]; } else { return new SpanOrQuery(spanQueries); }
return new StashCreateCommand(repo);
string GetByName(FieldInfo fieldName) { return fieldName; }
request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request);
request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request);
return ExecuteCancelUpdateStack(BeforeClientExecution(request));
request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request);
SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return SetInstanceProtection(request); }
return ExecuteModifyDBProxy(BeforeClientExecution(request));
private void Grow() { if (count == posLengths.Length) { int newLength = ArrayUtil.Oversize(1 + count, sizeof(int)); int[] next = new int[newLength]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; next = new int[newLength]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == outputs.Length) { CharsRefBuilder[] next = new CharsRefBuilder[ArrayUtil.Oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; Array.Copy(outputs, 0, next, 0, count); outputs = next; } }
public FetchLibrariesRequest() : base("cloudphoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
bool Exists(FileSystem fs, Path objects) { return fs.Exists(objects); }
public FilterOutputStream(Stream @out) { this.@out = @out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
return (DVConstraint)DataValidationConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request);
private DescribeCacheSubnetGroupsResult ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return request; }
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
} { ) (  bool ; reuseObjects return
t = t.AddAnyChild(new ErrorNodeImpl(this, t, badToken)); t.SetParent(Token.BadToken); return t;
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
RemoveSourceIdentifierFromSubscriptionRequest request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request);
public static TokenFilterFactory NewInstance(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request);
return new BinaryRevFilter(a.Clone(), b.Clone());
return o is ArmenianStemmer;
} { ) (  bool ; return sealed public protectedHasArray ) (
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void MethodName() { records.Remove(fileShare()); records.Remove(writeProtect()); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(expand, dedup, analyzer) { this.expand = expand; this.dedup = dedup; this.analyzer = analyzer; }
request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request);
return getObjectData()[findObjectRecord()];
public GetContactAttributesResult ExecuteGetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return GetContactAttributes(request); }
return Key + ": " + Value;
request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request);
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static string GetName(FunctionMetadata fd){if(fd==null)return null;return fd.GetName();}public static int GetIndex(FunctionMetadata fd){if(fd==null)return -1;return fd.GetIndex();}public static FunctionMetadata GetFunctionByName(string name){FunctionMetadata fd=GetFunctionByNameInternal(name);if(fd==null){fd=GetInstanceCetab().GetFunctionByNameInternal(name);}return fd;}
DescribeAnomalyDetectorsResult executeDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request = beforeClientExecution(request); return request;}
public static string InsertId(ObjectId changeId, string message) { return changeId.ToString(); }
long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) throw new MissingObjectException(objectId.Copy(), typeHint == OBJ_ANY ? JGitText.Get().UnknownObjectType2 : null); return sz;
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request){request=BeforeClientExecution(request);return ExecuteImportInstallationMedia(request);}
request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request);
NumberPtg.ReadDouble(in);
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) => ExecuteGetFieldLevelEncryptionConfig(BeforeClientExecution(request));
DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request){request=BeforeClientExecution(request);return new DeleteAlarmResult();}
return new PortugueseStemFilter(input);
private byte[] reserved = new byte[ENCODED_SIZE];
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public override string ToString() { return " >= _p" + precedence; }
request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request);
public class DeleteLoadBalancerPolicyRequest { public string LoadBalancerName { get; set; } public string PolicyName { get; set; } }
var options = _options; WindowProtectRecord(options);
public UnbufferedCharStream() { n = 0; data = new char[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5);
field_1_h_hold = in.ReadInt16(); field_2_v_hold = in.ReadInt16(); field_3_width = in.ReadInt16(); field_4_height = in.ReadInt16(); field_5_options = in.ReadInt16(); field_6_active_sheet = in.ReadInt16(); field_7_first_visible_tab = in.ReadInt16(); field_8_num_selected_tabs = in.ReadInt16(); field_9_tab_width_ratio = in.ReadInt16();
return ExecuteStopWorkspaces(BeforeClientExecution(request));
void FinalizeWriting(){if(isOpen()){try{isOpen=false;try{dump();}finally{channel.truncate(fileLength());}}finally{channel.close();fos.close();}}}
private DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
} { ) , , , (  string ; null return len off surface wordId ] [
string GetPathStr() { return pathStr; }
} { ) ( ; r return if ; v static public } { ) ( = r ; ) ; ( for ; ; ) ; ( for ; ; ; && double . NaN ] [ = r } { ++ i n < i ; 0 = s = m } { ++ i n < i ; = n 0 = s 0 = m 1 >= null != v s : 0 ? ; 0 = i n / s ; 0 = i Length . v Length . v ) ( += s += s 1 == n * ] i [ v ) ( ) ( m - m - ] i [ v ] i [ v
var finalRequest = BeforeClientExecution(request); return ExecuteDescribeResize(finalRequest);
public readonly bool passedThroughNonGreedyDecision;
() { return end(0); }
void Walk(CellHandler handler){int firstRow=range.GetFirstRow();int lastRow=range.GetLastRow()+1;int firstColumn=range.GetFirstColumn();int lastColumn=range.GetLastColumn()+1;int width=lastColumn-firstColumn;SimpleCellWalkContext ctx=null;Row currentRow=null;Cell currentCell=null;for(int rowNumber=firstRow;rowNumber<=lastRow;++rowNumber){currentRow=sheet.GetRow(rowNumber);int rowSize=width;if(currentRow==null){if(!traverseEmptyCells)continue;}else{rowSize=currentRow.LastCellNum;}for(int colNumber=firstColumn;colNumber<=lastColumn;++colNumber){currentCell=currentRow==null?null:currentRow.GetCell(colNumber);if(currentCell==null||currentCell.IsEmpty()){if(!traverseEmptyCells)continue;}int ordinalNumber=ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(rowNumber-firstRow,width),ArithmeticUtils.SubAndCheck(colNumber-firstColumn,1));ctx=new SimpleCellWalkContext(sheet,range,rowNumber,colNumber,ordinalNumber);handler.OnCell(ctx,currentCell);}}}
return pos();
return this.bytes.Equals(other.bytes) ? float.Compare(other.boost, this.boost) : this.bytes.CompareTo(other.bytes);
for(int i=0;i<len;++i){switch(s[i]){case HAMZA_ABOVE:case HEH_GOAL:case HEH_YEH:case KEHEH:case YEH_BARREE:case FARSI_YEH:Delete(s,i,len);--i;break;default:break;}}return len;
public void WriteShort(LittleEndianOutput out, int _options){ out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public class KeySchemaElement { public string AttributeName { get; set; } public KeyType KeyType { get; set; } public override string ToString() { return $"({AttributeName}, {KeyType})"; } }
request = beforeClientExecution(request); return executeGetAssignment(request);
return id != AnyObjectId.FindOffset(id) - 1;
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void PutDimConfig(string dimName, bool multiValued){ DimConfig ft = fieldTypes.Get(dimName); if(ft == null){ ft = new DimConfig(dimName, multiValued); fieldTypes.Put(dimName, ft); } }
int Size() { int size = 0; foreach (Cell<char> c in cells.Values) if (c.at >= 0) size++; return size; }
request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request);
request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request);
