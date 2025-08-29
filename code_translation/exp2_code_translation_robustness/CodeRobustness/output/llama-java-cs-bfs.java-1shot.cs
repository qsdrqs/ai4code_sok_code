public virtual void WriteTo(LittleEndianOutput output) { output.WriteShort(field_1_vcenter); }
if (srcDirIdx < 0) { srcDirIdx = 0; } for (; srcDirIdx < src.Length; ++srcDirIdx) { if (0 != (tailBlkIdx = src[srcDirIdx].tailBlkIdx)) { BlockList<T> tailBlock = src[tailBlkIdx].block; int size = Math.Min(BLOCK_SIZE, src[tailBlkIdx].size); addAll(tailBlock, 0, tailDirIdx, size); tailDirIdx += size; } } return;
if (currentBlock != null)      addBlock(currentBlock, blockSize);  if (blockSize == upto)      upto = 0;  upto++;  currentBlock = new Array[blockSize];
public virtual ObjectId GetObjectId() { return objectId; }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) => ExecuteDeleteDomainEntry(BeforeClientExecution(request));
return (null != termsDictOffsets) ? ramBytesUsed.termsDictOffsets : (null != termOffsets) ? ramBytesUsed.termOffsets : 0;
public virtual string DecodeRaw(byte[] raw, byte[] msgB) => msgB.Length > 0 ? RawParseUtils.GuessEncoding(raw, 0, msgB) : "";
var bb = CreateEmptyBATBlock(1); setOurBlockIndex(bb); _bat_blocks.Add(bb); _property_table.SetNextBlock(0, 1); _property_table.SetNextBlock(1, POIFSConstants.END_OF_CHAIN); _header.SetBATCount(1); _header.SetBATArray(new[] { 0 }); setStartBlock(_bat_blocks[0]); return true;
var address = offset0 = upto = slice != null ? slice.Length : 0; address = (address & ByteBlockPool.BYTE_BLOCK_MASK) >> ByteBlockPool.BYTE_BLOCK_SHIFT; var buffers = pool.GetBuffers(upto, address);
public SubmoduleAddCommand(string path) : this(path) { }
return ExecuteListIngestions(request).BeforeClientExecution(request);
public QueryParserTokenManager(SwitchTo[] stream, int lexState, CharStream stream) { }
return GetShardIteratorResult(request, (request) => ExecuteGetShardIterator(request, (request) => BeforeClientExecution(request)));
public ModifyStrategyRequest : RpcAegisRequest, IModifyStrategyRequest
lock (this) { try { return available() > 0 && in != null; } catch (IOException e) { throw new IOException("InputStreamReader is closed", e); } }
return (EscherOptRecord) _optRecord;
public synchronized int read(char[] buffer, int offset, int length) {      if (length == 0) return 0;      if (buffer == null) throw new NullReferenceException("buffer == null");      checkOffsetAndCount(length, offset, buffer.Length);      int pos = this.pos;      if (pos + length > count) length = count - pos;      if (length <= 0) return 0;      Arrays.copyOfRange(charArray, pos, pos + length, buffer, offset, offset + length);      pos += length;      this.pos = pos;      return length;  }
public class OpenNLPSentenceBreakIterator : NLPSentenceDetectorOp { }
public virtual void Write(string str) { if (str != null) { object value = str; } }
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { }
return ((V) nextEntry).getValue() != ((V) this).getValue();
public void readInternal(final byte[] b, int offset, int len) throws IOException  {      if (useBuffer)      {          if (available <= len)          {              len = available;          }          if (len > 0)          {              System.arraycopy(buffer, bufferPosition, b, offset, len);              bufferPosition += len;              available -= len;          }      }      else      {          if (bufferSize < len)          {              len = bufferSize;          }          if (len > 0)          {              available += bufferPosition;              System.arraycopy(buffer, 0, b, offset, len);              offset += len;              available -= len;              if (after > len)              {                  throw new EOFException(this + "read past EOF: " + after);              }              len = bufferLength;          }          if (len < bufferLength)          {              System.arraycopy(buffer, bufferStart, buffer, 0, len);              bufferPosition = bufferStart = 0;          }          if (len > 0)          {              System.arraycopy(b, 0, buffer, bufferPosition, len);          }          throw new EOFException(this + "read past EOF: ");      }  }
public virtual TagQueueResponse TagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
public virtual void Throw() { throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request)
string[] @params = value.Split(',');  string language = @params[0];  string country = @params.Length > 1 ? @params[1] : "";  string variant = @params.Length > 2 ? @params[2] : "";  StringTokenizer st = new StringTokenizer(language);  language = st.NextToken();  country = st.NextToken();  variant = st.NextToken();
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) => BeforeClientExecution(request);
public override bool Equals(object obj) => obj is FacetLabel other && other.components.Length == components.Length && Enumerable.Range(0, components.Length).All(i => components[i].Equals(other.components[i]));
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request)
shapes.Add(new HSSFPolygon(anchor, this) { Parent = this, Anchor = anchor });
return GetBoundSheetRec(sheetIndex).GetSheetName();
return ExecuteGetDashboard((GetDashboardRequest)request).BeforeClientExecution(request);
return Execute(request).AssociateSigninDelegateGroupsWithAccountResult;
for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(j + mbr.getFirstColumn()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
public static string ToString(string s) {      int apos = s.IndexOf('\'');      while (apos >= 0) {          StringBuilder sb = new StringBuilder();          sb.Append("\\Q");          sb.Append(s.Substring(0, apos));          sb.Append("\\E");          s = sb.ToString() + s.Substring(apos + 1);          apos = s.IndexOf('\'', apos + 2);      }      return s;  }
throw new ReadOnlyBufferException();
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { Object vv = _arrayValues[r, c]; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; ArrayPtg values2d = new ArrayPtg(_nRows, _nColumns); values2d[0, 0] = new Object(); values2d[r, c] = vv.getValueIndex(); } }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) => Invoke<GetIceServerConfigResponse>(beforeClientExecution(request), new InvokeOptions { RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance, ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance });
public override string ToString() { return "[" + GetType().Name + " " + getName() + " = " + getValueAsString() + "]"; }
public override string ToString() { return $"ToChildBlockJoinQuery({parentQuery}, {field})"; }
public void IncrementAndGet(ref int refCount) { }
public UpdateConfigurationSetSendingEnabledResult ExecuteUpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) => BeforeClientExecution(request);
return (int)(LittleEndianConsts.XBAT_ENTRIES_PER_BLOCK * INT_SIZE);
if (pow10 > 0) { TenPower tp = TenPower.getInstance(); _divisor = tp.mulShift(_divisor, tp._divisorShift); _multiplicand = tp.mulShift(_multiplicand, tp._multiplierShift); _divisor = tp.mulShift(_divisor, -Math.abs(pow10)); } else { }
var b = new StringBuilder();  for (int i = 0; i < length; i++)      b.Append(getComponent(i).ToString()).Append(File.separatorChar);  return b.ToString().TrimEnd(File.separatorChar);
this.fetcher = new ECSMetadataServiceCredentialsFetcher(roleName); return this as InstanceProfileCredentialsProvider;
public virtual void CreateHyperParameterTuningJob(ProgressMonitor pm = null)
if (ptr == 0) first = parseEntry(); if (eof) ; else if (!ptr) ptr = 0;
throw new NoSuchElementException(); return start >= previous; previousIndex = iterator.PreviousIndex(); previous = iterator.Previous(); iterator.MovePrevious();
return this.newPrefix + " (" + String.Format("{0})", "") + ";";
public int indexOf(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1; }
var deduped = new List<CharsRef>(); var stems = new List<CharsRef>(); if (stems.Count > 2) { stems = new List<CharsRef>(8); } for (; ; ) { var word = (CharsRef)null; var s = (string)null; if (word.Length > 0) { if (stems.Contains(word, ignoreCase)) { deduped.Add(word); } } }
return executeGetGatewayResponses(GetGatewayResponsesRequest(request).beforeClientExecution(request));
pos = currentBlockUpto = currentBlock = currentBlockIndex; blocks[currentBlockIndex] &= ~(blockMask >> pos);
int n = Math.Min(available, 0); int s = 0; for (; n > 0; n--) { s += ptr; ptr = (s); } return s;
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { setBootstrapActionConfig(bootstrapActionConfig); }
public void WriteTo(LittleEndianOutput output)  {      output.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00));      output.WriteShort(field_1_row);      output.WriteShort(field_2_col);      output.WriteShort(field_3_flags);      output.WriteShort(field_4_shapeid);      StringUtil.PutCompressedUnicode(field_6_author, output);      if (field_7_padding != null)      {          output.WriteShort(field_7_padding.IntValue);      }  }
return ((string)count).LastIndexOf(string);
public bool AddLastImpl(object e) => AddLast(e);
void DoWhileLoop(ConfigSnapshot src, string section, string subsection) { bool res; do { res = CompareAndSet(src, section, subsection); } while (!res); }
public virtual string TagName { get; }
public void AddSubRecord(int index, SubRecord element) { }
public bool Remove(Object o) { lock (mutex) { return delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject).TokenStream;
return inCoreLength;
public virtual void SetValue(bool value) { this._value = value; }
var newSource = oldSource = new ContentSource(newSource, this, oldSource, this);
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
public CreateRepoRequest(): base("cr", "2016-06-07", "CreateRepo", "cr", "openAPI") { UriPattern = "/repos"; Method = MethodType.PUT; }
public bool ReturnDeltaBaseAsOffset() { }
if (null != lastLink) throw new IllegalStateException(); else if (expectedModCount != modCount) throw new ConcurrentModificationException(); else { expectedModCount++; --pos; Link previous = previous; Link next = next; previous.next = next; next.previous = previous; if (link == lastLink) lastLink = previous; else if (next.lastLink > ET) next.lastLink = ET; if (link == firstLink) firstLink = next; else if (previous.lastLink > ET) previous.lastLink = ET; size--; modCount++; lastLink = previous; }
public MergeShardsResult executeMergeShards(MergeShardsRequest request) { return beforeClientExecution(request); }
return ExecuteAllocateHostedConnection(request, beforeClientExecution).AllocateHostedConnectionResult;
return start;
public static Query getTerms(final Query query) { return query; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte0 = (byte)(values[valuesOffset++] & 0xFF); byte1 = (byte)(values[valuesOffset++] & 0xFF); byte2 = (byte)(values[valuesOffset++] & 0xFF); blocks[blocksOffset++] = (byte)((byte2 >> 6) & 0x3F); blocks[blocksOffset++] = (byte)(((byte1 & 0x0F) << 2) | ((byte2 >> 4) & 0x03)); blocks[blocksOffset++] = (byte)(((byte0 & 0x03) << 4) | ((byte1 >> 4) & 0x0F)); blocks[blocksOffset++] = (byte)(byte0 & 0x3F); }
public string GetPath() {      string s = result;      if (s == null || s.Length == 0 || !s.EndsWith(DOT_GIT_EXT, StringComparison.Ordinal))          throw new IllegalArgumentException("Invalid git path");      s = s.Substring(0, s.Length - DOT_GIT_EXT.Length);      if (s.Equals("/"))          return "";      if (s.StartsWith("/"))          s = s.Substring(1);      string[] elements = s.Split('/');      if (elements.Length == 1 && elements[0].Equals("file"))          return LOCAL_FILE;      if (elements.Length > 1 && elements[elements.Length - 1].Equals(""))          elements = Arrays.CopyOf(elements, elements.Length - 1);      StringBuilder result = new StringBuilder();      for (int i = 0; i < elements.Length; i++) {          if (i > 0)              result.Append(File.separatorChar);          result.Append(elements[i].Replace("\\", "/"));      }      return result.ToString();  }
return ExecuteDescribeNotebookInstanceLifecycleConfig(request, (request) => request.BeforeClientExecution(DescribeNotebookInstanceLifecycleConfigRequest, DescribeNotebookInstanceLifecycleConfigResult));
return this.accessKeySecret.ToString();
public CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request) => BeforeClientExecution(request);
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) => ExecuteDescribeVoices(beforeClientExecution(request));
return ExecuteListMonitoringExecutions(request, (request) => beforeClientExecution(request)) as ListMonitoringExecutionsResult;
public class DescribeJobRequest { public string JobId { get; set; } public string VaultName { get; set; } }
return (EscherRecord)getEscherRecords().get(index);
public virtual GetApisResponse GetApis(GetApisRequest request) => ExecuteGetApis(beforeClientExecution(request));
return Execute<DeleteSmsChannelRequest, DeleteSmsChannelResult>(request, beforeClientExecution);
public virtual TrackingRefUpdate TrackingRefUpdate() { return trackingRefUpdate; }
public virtual void Print(string value, bool print) { bool b = bool.Parse(value); }
return GetChildren().Get<QueryNode>(0);
workdirTreeIndex = this.index + NotIgnoredFilter(workdirTreeIndex);
field_1_formatFlags = (ushort)in.ReadShort();
; // Note that ';' is not a valid C# statement, assuming it's a label or part of a larger expression GetThumbnailRequest ) ( ; ) "cloudphoto" , "GetThumbnail" , "2017-07-11" , "CloudPhoto" ( base { ) ( new HTTPS.ProtocolType
public virtual DescribeTransitGatewayVpcAttachmentsResult ExecuteDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => BeforeClientExecution(request);
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) => ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request));
public virtual OrdRange GetPrefixToOrdRange(string dim) { return dim; }
public override string ToString()  {     return string.Format("LexerNoViableAltException{input={0},symbol={1},startIndex={2}}",          getInputStream().ToString(),          symbol == null ? "" : symbol,          startIndex); }
public virtual E PeekFirstImpl() { return peekFirstImpl; }
return ExecuteCreateWorkspaces((CreateWorkspacesRequest)request).BeforeClientExecution();
return copy;
public DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request) => Client.BeforeClientExecution(request);
mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
public class HyphenatedWordsFilter : TokenStream { public HyphenatedWordsFilter(TokenStream input) : base(input) { } }
return Execute<CreateDistributionWithTagsRequest, CreateDistributionWithTagsResult>(request, (request) => beforeClientExecution(request));
public RandomAccessFile(string fileName, string mode) throws FileNotFoundException { }
