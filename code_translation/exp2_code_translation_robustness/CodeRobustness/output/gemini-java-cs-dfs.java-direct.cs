void Field1Vcenter(BinaryWriter writer) { writer.Write((short)0); }
} ; ) TailBlkIdx . src , 0 , TailBlock . src ( AddAll ) 0 != TailBlkIdx . src ( if ; ) BLOCK_SIZE , 0 , ] srcDirIdx [ Directory . src ( AddAll ) ++ srcDirIdx ; TailDirIdx . src < srcDirIdx ; ( for ; 0 = srcDirIdx ; return ) 0 == Size . src ( if { ) src > T < BlockList ( AddAll void
} ; b = ] ++ upto [ currentBlock } ; 0 = upto ; ] blockSize [ new = currentBlock } ; ) currentBlock ( addBlock { ) null != currentBlock ( if { ) blockSize == upto ( if { ) b (  void
ObjectId ObjectId => objectId;
} ; ) request ( ExecuteDeleteDomainEntry return ; ) request ( beforeClientExecution = request => { ) request DeleteDomainEntryRequest (  DeleteDomainEntryResult
return (termOffsets?.ramBytesUsed() ?? 0) + (termsDictOffsets?.ramBytesUsed() ?? 0);
public string GetShortMessage(byte[] buffer) { byte[] raw = buffer; byte[] msgB = RawParseUtils.TagMessage(raw, 0); if (msgB.Length == 0) return ""; return RawParseUtils.Decode(RawParseUtils.GuessEncoding(msgB, raw.Length)); }
; 0 = StartBlock . _property_table ; ) FAT_SECTOR_BLOCK . POIFSConstants , 1 ( SetNextBlock ; ) END_OF_CHAIN . POIFSConstants , 0 ( SetNextBlock ; ) bb ( Add . _bat_blocks ; 1 = OurBlockIndex . bb ; ) false , bigBlockSize ( CreateEmptyBATBlock . BATBlock = bb BATBlock ; } 1 { ] [ int new = BATArray . _header ; 1 = BATCount . _header ; ) true ( { ) ( POIFSFileSystem
} ; ) Length . slice < upto ( Debug.Assert ; address = offset0 ; BYTE_BLOCK_MASK . ByteBlockPool & address = upto ; ) null != slice ( Debug.Assert ; ] BYTE_BLOCK_SHIFT . ByteBlockPool >> address [ buffers . pool = slice { ) address (  void
public SubmoduleAddCommand Path(string path) { this.path = path; return this; }
} ; ) request ( ExecuteListIngestions return ; ) request ( BeforeClientExecution = request { ) request ListIngestionsRequest (  ListIngestionsResult
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
GetShardIteratorResponse response = client.GetShardIterator(BeforeClientExecution(request));
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { Method = "POST"; }
public bool Ready() { lock (@lock) { if (@in == null) { throw new System.IO.IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || @in.Available() > 0; } catch (System.IO.IOException) { return false; } } }
public EscherOptRecord EscherOptRecord => _optRecord;
public int Read(char[] buffer, int offset, int length) { lock (this) { if (buffer == null) throw new ArgumentNullException(nameof(buffer)); if (offset < 0 || length < 0 || buffer.Length - offset < length) throw new ArgumentOutOfRangeException(); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) buffer[offset + i] = _buffer[pos + i]; pos += copylen; return copylen; } }
} ; sentenceOp = sentenceOp . this { ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
Write(str ?? "null");
} ; functionName = functionName.this; ) cause, functionName(base { ) cause NotImplementedException, string functionName(NotImplementedFunctionException
} ; ) ( Value . ) ( NextEntry . base return { ) (  V
} } } ; 0 = bufferLength ; 0 = bufferPosition ; after = bufferStart ; ) len , offset , b ( readInternal ; ) this + "read past EOF: " ( EndOfStreamException.IO.System new throw ) ) ( length > after ( if ; len + bufferPosition + bufferStart = after long { else } } ; len = bufferPosition ; ) len , offset , b , 0 , buffer ( Copy . Array { else } ; ) this + "read past EOF: " ( EndOfStreamException.IO.System new throw ; ) bufferLength , offset , b , 0 , buffer ( Copy . Array { ) len < bufferLength ( if ; ) ( refill { ) bufferSize < len && useBuffer ( if } ; available += bufferPosition ; available -= len ; available += offset ; ) available , offset , b , bufferPosition , buffer ( Copy . Array { ) 0 > available ( if { else } } ; len += bufferPosition ; ) len , offset , b , bufferPosition , buffer ( Copy . Array ) 0 > len ( if { ) available <= len ( if ; bufferPosition - bufferLength = available int { ) useBuffer bool , len , offset , b ] [ byte ( read void public
} ; ) request ( ExecuteTagQueue return ; ) request ( beforeClientExecution = request { ) request TagQueueRequest ( TagQueueResult
} ; ) ( NotSupportedException new throw { ) ( void
} ; ) request ( ExecuteModifyCacheSubnetGroup return ; ) request ( BeforeClientExecution = request { ) request ModifyCacheSubnetGroupRequest ( CacheSubnetGroup
public override void SetParams(string @params) { base.SetParams(@params); var tokens = @params.Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries); language = tokens.Length > 0 ? tokens[0] : ""; country = tokens.Length > 1 ? tokens[1] : ""; variant = tokens.Length > 2 ? tokens[2] : ""; }
DeleteDocumentationVersionResult result = ExecuteDeleteDocumentationVersion(new DeleteDocumentationVersionRequest { BeforeClientExecution = request => { return; } });
var other = obj as FacetLabel; if (other == null) return false; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) if (!components[i].Equals(other.components[i])) return false; return true;
};) request(ExecuteGetInstanceAccessDetails return;) request(BeforeClientExecution = request {) request GetInstanceAccessDetailsRequest(GetInstanceAccessDetailsResult
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape;
public string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetName(); }
return client.GetDashboard(new GetDashboardRequest());
public AssociateSigninDelegateGroupsWithAccountResult ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
void MulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn(mbr.GetFirstColumn() + j); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string String(string @string) { System.Text.StringBuilder sb = new System.Text.StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = @string.IndexOf("\\E", apos)) >= 0) { sb.Append(@string.Substring(apos, k + 2 - apos)); sb.Append("\\E\\Q"); apos = k + 2; } return sb.Append(@string.Substring(apos)).Append("\\E").ToString(); }
void ByteBuffer(object value) => throw new ReadOnlyMemoryException();
} ; 0 = _reserved2Byte ; 0 = _reserved1Short ; 0 = _reserved0Int ; vv = _arrayValues } } ; ] c [ rowData = ] ) r , c ( getValueIndex [ vv { ) ++ c ; nColumns < c ; 0 = c ( for ; ] r [ values2d = rowData ] [ object { ) ++ r ; nRows < r ; 0 = r ( for ; ] _nRows * _nColumns [ object new = vv ] [ object ; nRows ) ( = _nRows ; nColumns ) ( = _nColumns ; Length . values2d = nRows ; Length . ] 0 [ values2d = nColumns { ) values2d ] [ ] [ object ( ArrayPtg
GetIceServerConfigResult result = client.ExecuteGetIceServerConfig(new GetIceServerConfigRequest { BeforeClientExecution = (request) => { return; } });
public override string ToString() { return $"{GetType().Name} [{GetValueAsString()}]"; }
public override string ToString(string field) { return "ToChildBlockJoinQuery (" + ParentQuery.ToString() + ")"; }
} ; ) refCount ref ( Increment . Interlocked { ) (  void public
UpdateConfigurationSetSendingEnabledResponse response = client.UpdateConfigurationSetSendingEnabled(request);
}; INT_SIZE.LittleEndianConsts*) (GetXBATEntriesPerBlock return {) (
} } ; ) _multiplierShift . tp , _multiplicand . tp ( mulShift { else } ; ) _divisorShift . tp , _divisor . tp ( mulShift { ) 0 < pow10 ( if ; ) ) ) pow10 ( Abs . Math ( GetInstance . TenPower = tp TenPower { ) pow10 (  void
} ; ) ( ToString . b return } } ; ) DirectorySeparatorChar . Path ( Append . b { ) 1 - l < i ( if ; ) ) i ( getComponent ( Append . b { ) ++ i ; l < i ; 0 = i ( for ; ) DirectorySeparatorChar . Path ( Append . b ; ) ( length = l ; ) ( StringBuilder new = b StringBuilder { ) (  string
this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this;
} ; pm = progressMonitor { ) pm ProgressMonitor (  void
if (!first) { ptr = 0; } if (!eof) { parseEntry(); }
} ; ) ( ) noitpOpoilavnI wen worht } ; ) ( ) suoiverP . rotareti nruter { ) trats >= ) ( ) xednIsuoiverP . rotareti ( fi { ) ( E
string NewPrefix => this.newPrefix;
{ for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
var stems = Stem(word, length); if (stems.Count < 2) return stems; var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) if (terms.Add(s)) deduped.Add(s); return deduped;
} ; ) request ( ExecuteGetGatewayResponses return ; ) request ( BeforeClientExecution = request { ) request GetGatewayResponsesRequest ( GetGatewayResponsesResult
void Method(int pos) { currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask; }
} ; s return ; s += ptr ; ) ) n , 0 ( Max . Math , ) ( available ( Min . Math ) ( = s { ) n (
BootstrapActionConfig = bootstrapActionConfig;
out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); }
return string.LastIndexOf(count);
bool @object(E @object) { return AddLastImpl(@object); }
void UpdateState(string section, string subsection) { ConfigSnapshot src, res; do { src = _state; res = UnsetSection(src, section, subsection); } while (System.Threading.Interlocked.CompareExchange(ref _state, res, src) != src); }
public string TagName() => tagName;
(SubRecord element, int index) => { subrecords.Add(element); };
} } ; ) o ( remove . ) ( delegate return { ) mutex ( lock { ) o object ( bool
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
{ return inCoreLength(); }
void Set(bool newValue) => value = newValue;
Pair(ContentSource oldSource, ContentSource newSource) { this.OldSource = oldSource; this.NewSource = newSource; }
} ; ] i [ entries return ; ) ( IndexOutOfRangeException new throw ) i <= count ( if {
; ) PUT . MethodType ( setMethod ; ) "/repos" (  ; ) "cr" , "CreateRepo" , "2016-06-07" , "cr" ( base { ) ( CreateRepoRequest
} ; deltaBaseAsOffset return { ) (  bool
public void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link<T> next = lastLink.Next; Link<T> previous = lastLink.Previous; previous.Next = next; next.Previous = previous; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.Count--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException("Collection was modified; enumeration operation may not execute."); } }
return ExecuteMergeShards(request);
} ; ) request ( ExecuteAllocateHostedConnection return ; ) request ( BeforeClientExecution = request { ) request AllocateHostedConnectionRequest ( AllocateHostedConnectionResponse
} ; start return { ) (
public static Query query(WeightedTerm[] query) { return getTerms(query, false); }
public ByteBuffer() { throw new ReadOnlyMemoryException(); }
for (int i = 0; i < iterations; i++) { byte byte0 = blocks[++blocksOffset]; values[++valuesOffset] = byte0 >> 2; byte byte1 = blocks[++blocksOffset]; values[++valuesOffset] = ((byte0 & 3) << 4) | (byte1 >> 4); byte byte2 = blocks[++blocksOffset]; values[++valuesOffset] = ((byte1 & 15) << 2) | (byte2 >> 6); values[++valuesOffset] = byte2 & 63; }
string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = System.Text.RegularExpressions.Regex.Split(s, "[" + System.IO.Path.DirectorySeparatorChar + @"\\/]"); else elements = System.Text.RegularExpressions.Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (result == Constants.DOT_GIT) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
return await client.DescribeNotebookInstanceLifecycleConfigAsync(new DescribeNotebookInstanceLifecycleConfigRequest());
public string AccessKeySecret { get { return this.accessKeySecret; } }
} ; ) request ( ExecuteCreateVpnConnection return ; ) request ( beforeClientExecution = request { ) request CreateVpnConnectionRequest ( CreateVpnConnectionResponse
}; ) request ( ExecuteDescribeVoices return ; ) request ( beforeClientExecution = request => { ) request DescribeVoicesRequest ( DescribeVoicesResult
} ; ) request ( ListMonitoringExecutions return ; ) request ( BeforeClientExecution = request { ) request ListMonitoringExecutionsRequest (  ListMonitoringExecutionsResponse
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
return (EscherRecord)escherRecords[index];
return ExecuteGetApis(BeforeClientExecution(request));
DeleteSmsChannelResult executeDeleteSmsChannel(DeleteSmsChannelRequest request) { return default; }
return new TrackingRefUpdate();
} ; ) ) b ( ToString . Convert ( Write . Console { ) b bool ( void
} ; ] 0 [ ) ( nerdlihCteG nruter { ) ( edoNyrueQ
public NotIgnoredFilter(object workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadInt16(); }
; ) HTTPS . ProtocolType (  ; ) "cloudphoto" , "GetThumbnail" , "2017-07-11" , "CloudPhoto" ( base { ) ( GetThumbnailRequest
DescribeTransitGatewayVpcAttachmentsResponse result = client.DescribeTransitGatewayVpcAttachments(request);
} ; ) request ( ExecutePutVoiceConnectorStreamingConfiguration return ; ) request ( beforeClientExecution = request { ) request PutVoiceConnectorStreamingConfigurationRequest (  PutVoiceConnectorStreamingConfigurationResult
} ; ) dim ( get . prefixToOrdRange return { ) dim string (  OrdRange
return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, Utils.EscapeWhitespace(InputStream.GetText(new Interval(startIndex, startIndex)), false));
E PeekFirst() { return PeekFirstImpl(); }
(CreateWorkspacesRequest request) => { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); };
} ; ) ( copy return { ) ( NumberFormatIndexRecord
} ; ) request ( executeDescribeRepositories return ; ) request ( beforeClientExecution = request { ) request DescribeRepositoriesRequest (  DescribeRepositoriesResult
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter HyphenatedWordsFilter(TokenStream input) => new HyphenatedWordsFilter(input);
} ; ) request ( ExecuteCreateDistributionWithTags return ; ) request ( beforeClientExecution = request => { ) request CreateDistributionWithTagsRequest ( CreateDistributionWithTagsResult
public RandomAccessFile(string fileName, string mode) { }
return ExecuteDeleteWorkspaceImage(request);
} ; ) "x16" ( ToString . value return { ) value long ( ToString string static public
} ; ) request ( ExecuteUpdateDistribution return ; ) request ( BeforeClientExecution = request { ) request UpdateDistributionRequest (  UpdateDistributionResponse
public HSSFColor GetColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) return HSSFColorPredefined.AUTOMATIC.GetColor(); byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(index, b); }
throw new NotImplementedFunctionException(_functionName);
out.Write(field_1_number_crn_records); out.Write(field_2_sheet_table_index);
DescribeDBEngineVersionsResult DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request) => new DescribeDBEngineVersionsResult();
public FormatRun(int fontIndex, char character) : base(fontIndex, character) { }
} ; result return } ; ch ) ( = ] ++ resultIndex [ result ; ) 8 >> ch ( ) ( = ] ++ resultIndex [ result ; ] i [ chars = ch { ) i ++ ; end < i ; offset = i ( for ; 0 = resultIndex ; length + offset = end ; ] 2 * length [ new = result ] [ { ) length , offset , chars ] [ (  ] [ static public
return ExecuteUploadArchive(new UploadArchiveRequest { BeforeClientExecution = request => { } });
List<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
} ; true return ; false return ) ) term . other ( Equals . term ! ( if else } ; false return ) null != term . other ( if { ) null == term ( if ; false return ) ) compiled . other ( Equals . compiled ! ( if ; ) obj ( AutomatonQuery = other AutomatonQuery ; false return ) ) GetType ( . obj != ) GetType ( ( if ; false return ) ) obj ( Equals . base ! ( if ; true return ) ) obj , this ( ReferenceEquals ( if { ) obj object ( Equals bool override public
var spanQueries = weightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
StashCreateCommand StashCreateCommand() => new StashCreateCommand(repo);
FieldInfo GetByName(string fieldName) { return byName[fieldName]; }
var response = await client.DescribeEventSourceAsync(new DescribeEventSourceRequest { Name = "event-source-name" });
GetDocumentAnalysisResponse result = await textractClient.GetDocumentAnalysisAsync(request);
} ; ) request ( ExecuteCancelUpdateStack return ; ) request ( BeforeClientExecution = request { ) request CancelUpdateStackRequest ( CancelUpdateStackResult
} ; ) request ( ExecuteModifyLoadBalancerAttributes return ; ) request ( BeforeClientExecution = request { ) request ModifyLoadBalancerAttributesRequest (  ModifyLoadBalancerAttributesResult
} ; ) request ( ExecuteSetInstanceProtection return ; ) request ( beforeClientExecution = request { ) request SetInstanceProtectionRequest (  SetInstanceProtectionResult
return ExecuteModifyDBProxy(request);
if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
;) ProtocolType.Https (;) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base {) ( FetchLibrariesRequest
bool Exists() { return fs.Objects(); }
public FilterStream(Stream out) { this.out = out; }
public ScaleClusterAction() : base("PUT", "/clusters/{ClusterId}", "csk", "ScaleCluster", "2015-12-15", "CS", typeof(ScaleClusterRequest)) { }
DataValidationConstraint CreateTimeConstraint(string formula1, string formula2, int operatorType) => DVConstraint.CreateTimeConstraint(formula1, formula2, operatorType);
return client.ListObjectParentPaths(new ListObjectParentPathsRequest());
} ; ) request ( ExecuteDescribeCacheSubnetGroups return ; ) request ( BeforeClientExecution = request { ) request DescribeCacheSubnetGroupsRequest ( DescribeCacheSubnetGroupsResponse
};)flag,field_5_options(setShortBoolean.sharedFormula=field_5_options{)flag bool(void
bool ReuseObjects() => reuseObjects;
} ; t return ; ) this ( SetParent . t ; ) t ( AddAnyChild ; ) badToken ( ErrorNodeImpl new = t ErrorNodeImpl { ) badToken Token ( ErrorNode
public LatvianStemFilterFactory(IDictionary<string, string> args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) => ExecuteRemoveSourceIdentifierFromSubscription(request);
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
; ) ProtocolType.Https ( ; ) "cloudphoto" , "AddAlbumPhotos" , "2017-07-11" , "CloudPhoto" ( base { ) ( AddAlbumPhotosRequest
return ExecuteGetThreatIntelSet(request);
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool ProtectedHasArray() { return ProtectedHasArray(); }
}; ) request ( ExecuteUpdateContributorInsights return ; ) request ( beforeClientExecution = request { ) request UpdateContributorInsightsRequest ( UpdateContributorInsightsResult
}; null = writeProtect; null = fileShare; )writeProtect(Remove.records; )fileShare(Remove.records{
} ; expand = expand . this ; ) analyzer , dedup ( base { ) analyzer Analyzer , expand bool , dedup bool ( SolrSynonymParser
} ; ) request ( ExecuteRequestSpotInstances return ; ) request ( BeforeClientExecution = request { ) request RequestSpotInstancesRequest (  RequestSpotInstancesResponse
} ; ) ( getObjectData . ) ( findObjectRecord return { ) (  ] [
} ; ) request ( ExecuteGetContactAttributes return ; ) request ( BeforeClientExecution = request { ) request GetContactAttributesRequest (  GetContactAttributesResult
public override string ToString() => $"{GetKey()}: {GetValue()}";
ListTextTranslationJobsResponse response = client.ListTextTranslationJobs(request);
(GetContactMethodsRequest request) => ExecuteGetContactMethods(BeforeClientExecution(request));
} ;)(xednIteG.df nruter } } ;1- nruter { )llun == df( fi ;)(eman)lanretnIemaNnoitcnuFteG.)(bateCtsnIteG = df { )llun == df( fi ;)(eman)lanretnIemaNnoitcnuFteG.)(ecnatsnIteG = df atadateMnoitcnuF { )eman gnirts(xednInoitcnuFteG tni citats cilbup
} ; ) request ( ExecuteDescribeAnomalyDetectors return ; ) request ( BeforeClientExecution = request { ) request DescribeAnomalyDetectorsRequest (  DescribeAnomalyDetectorsResult
public static string InsertId(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
(AnyObjectId objectId, int typeHint) { sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
return client.ImportInstallationMedia(new ImportInstallationMediaRequest { BeforeClientExecution = request => { } });
PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
};)) (ReadDouble.@in({) @in LittleEndianInput(NumberPtg
return await client.ExecuteGetFieldLevelEncryptionConfigAsync(request);
} ; ) request ( ExecuteDescribeDetector return ; ) request ( BeforeClientExecution = request { ) request DescribeDetectorRequest (  DescribeDetectorResult
return ExecuteReportInstanceStatus(request);
} ; ) request ( ExecuteDeleteAlarm return ; ) request ( BeforeClientExecution = request { ) request DeleteAlarmRequest (  DeleteAlarmResult
public TokenStream Create(TokenStream input) { return new PortugueseStemFilter(input); }
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
} ; ) request ( ExecuteGetDedicatedIp return ; ) request ( beforeClientExecution = request => { ) request GetDedicatedIpRequest ( GetDedicatedIpResult
} ; " >= _p" + precedence return { ) (  string
return client.ExecuteListStreamProcessors(request);
public class DeleteLoadBalancerPolicyRequest { public string LoadBalancerName { get; set; } public string PolicyName { get; set; } public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; } }
} ; options = _options { ) options ( WindowProtectRecord
public UnbufferedCharStream(int bufferSize) { n = 0; data = new char[bufferSize]; }
return ExecuteGetOperations(new GetOperationsRequest());
} ; ) w5 , 16 + o , b ( EncodeInt32 . NB ; ) w4 , 12 + o , b ( EncodeInt32 . NB ; ) w3 , 8 + o , b ( EncodeInt32 . NB ; ) w2 , 4 + o , b ( EncodeInt32 . NB ; ) w1 , o , b ( EncodeInt32 . NB { ) o , b ] [ (  void
} ; ) ( ReadShort . in = field_9_tab_width_ratio ; ) ( ReadShort . in = field_8_num_selected_tabs ; ) ( ReadShort . in = field_7_first_visible_tab ; ) ( ReadShort . in = field_6_active_sheet ; ) ( ReadShort . in = field_5_options ; ) ( ReadShort . in = field_4_height ; ) ( ReadShort . in = field_3_width ; ) ( ReadShort . in = field_2_v_hold ; ) ( ReadShort . in = field_1_h_hold { ) in RecordInputStream ( WindowOneRecord public
} ; ) request ( ExecuteStopWorkspaces return ; ) request ( BeforeClientExecution = request { ) request StopWorkspacesRequest ( StopWorkspacesResponse
if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
};) request(ExecuteDescribeMatchmakingRuleSets return;) request(beforeClientExecution: request => {) request DescribeMatchmakingRuleSetsRequest(DescribeMatchmakingRuleSetsResponse
} ; null return { ) len , off , ] [ surface , wordId (  string
string String() { return pathStr; }
};r return};s:0?)1==n(=r};)m-]i[v(*)m-]i[v(+=s{)++i;n<i;0=i(for;0=s;n/s=m};]i[v+=s{)++i;n<i;0=i(for;Length.v=n;0=s;0=m{)1>=Length.v&&null!=v(if;NaN.double=r{)v[]( static public
} ; ) request ( ExecuteDescribeResize return ; ) request ( BeforeClientExecution = request { ) request DescribeResizeRequest (  DescribeResizeResult
public bool PassedThroughNonGreedyDecision() { return false; }
} ; ) 0 ( end return { ) (
{ int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; var ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (!traverseEmptyCells && currentCell.IsEmpty()) { continue; } ctx.ordinalNumber = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width) + ArithmeticUtils.AddAndCheck(ctx.colNumber, 1 - firstColumn); handler.OnCell(currentCell, ctx); } } }
} ; pos return { ) (
} ; ) boost . other ( CompareTo . boost . this return ; ) Bytes . this ( CompareTo . Bytes . other return ) boost . other == boost . this ( if { ) other ScoreTerm (
} ; len return } } ; break : ; break ; -- i ; ) len , i , s ( delete = len : HAMZA_ABOVE case ; break ; HEH = ] i [ s : HEH_GOAL case : HEH_YEH case ; break ; KAF = ] i [ s : KEHEH case ; break ; YEH = ] i [ s : YEH_BARREE case : FARSI_YEH case { ) ] i [ s ( switch { ) ++ i ; len < i ; 0 = i ( for { ) len , ] [ s (
void WriteShort(System.IO.BinaryWriter writer) { writer.Write((short)_options); }
DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public class KeySchemaElement { public KeyType KeyType { get; set; } public string AttributeName { get; set; } }
return client.ExecuteGetAssignment(new GetAssignmentRequest { BeforeClientExecution = (request) => { } });
bool Has(AnyObjectId id) { return findOffset(id) != -1; }
GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetDimConfig(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes.Add(dimName, ft); } ft.multiValued = v; } }
} ; size nruter } } ; ++ size { ) 0 >= dmc . e ( fi ; ) c ( ta = e lleC { ) syeK . sllec ni c rahc ( hcaerof ; 0 = size {
(DeleteVoiceConnectorRequest request) => { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); };
public DeleteLifecyclePolicyResponse ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) => client.DeleteLifecyclePolicy(request);
