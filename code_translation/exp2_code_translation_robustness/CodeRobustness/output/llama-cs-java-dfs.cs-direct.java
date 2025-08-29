public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
if (tailBlkIdx.src != 0) tailBlock.src.addAll(tailBlkIdx.src); for (int srcDirIdx = 0; tailDirIdx.src < srcDirIdx; ++srcDirIdx) directory.src.addAll(srcDirIdx); if (size.src == 0) return; src.addAll(BlockList.Util.NGit(src));
} ; byte [] b = new byte [upto.outerInstance.currentBlock.outerInstance.blockSize.outerInstance]; for (int i = 0; i < upto.outerInstance.currentBlock.outerInstance.blockSize.outerInstance; i++) { b[i] = upto.outerInstance.currentBlock.outerInstance[i]; } upto.outerInstance.currentBlock.outerInstance.add(blockEnd.outerInstance); upto.outerInstance.blocks.outerInstance.add(currentBlock.outerInstance); if (currentBlock.outerInstance != null) { if (upto.outerInstance == blockSize.outerInstance) { } } WriteByte void
return objectId;
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); DeleteDomainEntryResponse response = client.invoke(DeleteDomainEntry.class, request, options).getResponse().getResult(DeleteDomainEntryResponse.class);
return fst == null ? 0 : fst.getSizeInBytes();
return RawParseUtils.Decode(raw, (enc = ParseEncoding.getEncoding()) != null ? enc : "", (msgB = TagMessage.getFullMessage(buffer)) != null && msgB.length > 0 ? msgB : "");
); null _root; ) ( ArrayList _documents = new ArrayList(); ) ( PropertyTable _property_table = new PropertyTable(); ) ( HeaderBlock headerBlock = new HeaderBlock(); ) ( POIFSFileSystem
assert Debug; int address = offset0 & BYTE_BLOCK_MASK; ByteBlockPool pool = Buffers.pool; assert null != pool; int slice = pool.slice(upto); address = (address + slice) >> BYTE_BLOCK_SHIFT; void Init(int address) {}
}; return; path path. {) path String (setPath SubmoduleAddCommand.Api.NGit
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); ListIngestionsResponse response = client.invoke("ListIngestions", request, options).getResponse().getBody();
public void SwitchTo(int lexState, ICharStream stream) { ((QueryParserTokenManager) this).SwitchTo(lexState, stream); }
GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request, InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()).withResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()));
public enum Method { POST, GET, PUT, DELETE, PATCH }
try {    lock (@lock) {      if (@in == null) throw new IOException ("");      return (@in.available () > 0 || @in.hasRemaining ()) ? @in : 0;    }  } catch (IOException e) {    throw new IOException (System.IO);  };
return new EscherOptRecord();
public synchronized void read(int[] buffer, int offset, int count) {      if (buffer == null) throw new NullPointerException();      if (count < 0 || offset < 0 || count > buffer.length - offset) throw new IndexOutOfBoundsException();      int copylen = Math.min(count, available());      int i = 0;      for (; i < copylen; i++)          buffer[offset + i] = (byte) read();      return;  }
OpenNLPSentenceDetectorOp.class;
System.out.println(str != null ? str : null);
public class FunctionName { public FunctionName() throws NotImplementedException { } public FunctionName(String cause) throws NotImplementedFunctionException { } }
return (nextEntry != null) ? nextEntry.value : null;
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) {      int available = len;      if (available > 0) {          if (bufferPosition + len > bufferLength) {              long after = bufferStart + bufferPosition + len;              if (after > Length) {                  throw new EndOfStreamException();              }              if (useBuffer && bufferSize < len) {                  Refill();              }          }          if (available > 0) {              if (available > len) {                  available = len;              }              System.arraycopy(Buffer, bufferPosition, b, offset, available);              bufferPosition += available;          }      }  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()); TagQueueResponse response = client.invoke("TagQueue", request, options).getResponse().getResultAs(TagQueueResponse.class);
throw new UnsupportedOperationException();
ModifyCacheSubnetGroupResponse response = client.modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest.builder().build(), new InvokeOptions().withRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
StringTokenizer st = new StringTokenizer(" " + culture);  String @params = "";  void SetParams() {      if (st.hasMoreTokens()) {          @params += st.nextToken() + " ";      }  }
return instance.invoke("DeleteDocumentationVersion", request, options, DeleteDocumentationVersionRequestMarshaller.INSTANCE, DeleteDocumentationVersionResponseUnmarshaller.INSTANCE);
public boolean equals(Object obj) {      if (obj == this) return true;      if (!(obj instanceof FacetLabel)) return false;      FacetLabel other = (FacetLabel) obj;      if (other.Length != Length) return false;      for (int i = Length - 1; i >= 0; --i)          if (!Components[i].Equals(other.Components[i], StringComparison.Ordinal)) return false;      return true;  }
GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request, InvokeOptions options = null);
HSSFPolygon shape = new HSSFPolygon(); shape.setAnchor(new HSSFChildAnchor()); shape = CreatePolygon(shape); Parent.addShape(shape); OnCreate(shape);
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetName(); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); GetDashboardRequest request = new GetDashboardRequest(); GetDashboardResponse response = client.invoke("GetDashboard", request, options).getResponse().getResult(GetDashboardResponse.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); AssociateSigninDelegateGroupsWithAccountRequest request = new AssociateSigninDelegateGroupsWithAccountRequest();
void addMultipleBlanks() { int j = 0; for (; j < numColumns && 0 == j; j++) { BlankRecord br = new BlankRecord(); br.row = row; br.column = firstColumn + j; br.xfIndex = getXFAt(mbr); insertCell(new MulBlankRecord(br)); } }
String string = quote;  StringBuilder sb = new StringBuilder();  int apos = 0;  while (0 <= apos) {      apos = string.indexOf("\\", apos);      if (apos != -1) {          sb.append(string.substring(0, apos));          sb.append("\\\\");          apos += 2;      }  }  sb.append(string.substring(apos));  return sb.toString();
throw new ReadOnlyBufferException();
Object[][] vv = new Object[_nRows * _nColumns]; for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { vv[r * _nColumns + c] = rowData[r][c]; } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); GetIceServerConfigResponse response = client.invoke(GetIceServerConfigRequest.class, options).getResponse();
StringBuilder sb = new StringBuilder(); sb.Append(GetType().Name); sb.Append(" "); sb.Append(GetValueAsString()); sb.Append(" "); return sb.ToString();
return " " + _parentQuery + " ".toString();
public synchronized int incrementAndGet() { return ++refCount; }
UpdateConfigurationSetSendingEnabledResponse response = (UpdateConfigurationSetSendingEnabledResponse) client.invoke("UpdateConfigurationSetSendingEnabled", request, UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance(), UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance(), options);
return ((GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE) + GetNextXBATChainOffset());
public void multiplyByPowerOfTen(int pow10) {      if (0 < pow10) {          tp = GetInstance().TenPower(tp, pow10);      } else {          (mulShift = _multiplierShift.tp, _multiplicand.tp);          (mulShift = _divisorShift.tp, _divisor.tp);      }  };
StringBuilder builder = new StringBuilder(); int length = path.length(); for (int i = 0; i < length; i++) { builder.append(path.charAt(i)); if (i < length - 1) builder.append(File.separator); } return builder.toString();
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { }
public void setProgressMonitor(ProgressMonitor pm) { }
public class Main { public static void main(String[]args){}static{ParseEntry();if(Eof()){if(ptr==0){if(First()){Reset();}}}
throw new NoSuchElementException(); return previous; if (previousIndex >= 0) iterator.previous();
public String getNewPrefix() { return newPrefix; }
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
List<CharsRef> stems = new ArrayList<>();  if (stems.size() < 2) {      CharArraySet terms = new CharArraySet(dictionary, 8);      for (CharsRef s : stems) {          for (char c : s.chars) {              terms.add(new CharsRef(String.valueOf(c)));          }      }      if (!terms.contains(new CharsRef(word))) {          stems.add(new CharsRef(word));      }      return stems;  }  return new UniqueStems(stems, length, 618, 612);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance());
); blockMask.outerInstance & position() int(currentBlockUpto; ] [ blocks.outerInstance.currentBlock; ) blockBits.outerInstance >> position() int(currentBlockIndex { ) position long(SetPosition void
long s = Math.min(n, Max) - Math.max(Available, Skip); int ptr = (int) s; return ptr;
}; BootstrapActionConfig _bootstrapActionConfig) { }
void serialize(ILittleEndianOutput out1) {    if (field_7_padding != null) out1.writeByte(Convert.ToInt32(field_7_padding, CultureInfo.InvariantCulture));    else out1.writeByte(StringUtil.PutCompressedUnicode(field_6_author));    if (field_5_hasMultibyte) out1.writeByte(0x01);    else out1.writeByte(0x00);    out1.writeShort(field_6_author.length);    out1.writeShort(0);    out1.writeShort(0);    out1.writeShort(0);    out1.writeShort(0);  }
public int lastIndexOf(String string) { return string.lastIndexOf(string); }
public boolean addLastImpl(E object) { return add(object); }
while (!CompareAndSet.state()) ;  do {      ConfigSnapshot src = Get.state();      ConfigSnapshot res = UnsetSection(src, section, subsection);  } while (!CompareAndSet.state(res));
public String getTagName() {
public void addSubRecord(SubRecord element, int index) {
synchronized (mutex) {      object.remove();      return c;  }
return new DoubleMetaphoneFilter(input.createTokenStream());
public long getInCoreLength() { return length; }
public void setValue(boolean newValue) { }
new SourcePair(new ContentSource(newSource), new ContentSource(oldSource));
throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + count);
public enum MethodType { PUT; public final String uriPattern; MethodType(String uriPattern) { this.uriPattern = uriPattern; } }
public boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount == expectedModCount) {    if (lastLink != null) {      next.previous = previous;      previous.next = next;    }    --pos;    if (link == lastLink)      lastLink = null;    --_size;    ++modCount;    ++expectedModCount;  } else {    throw new ConcurrentModificationException();  }
return instance.invoke(new MergeShardsRequest(), options, MergeShardsRequestMarshaller.INSTANCE, MergeShardsResponseUnmarshaller.INSTANCE);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); AllocateHostedConnectionResponse response = client.invoke(AllocateHostedConnection.class, request, options).getResponse().getResult(AllocateHostedConnectionResponse.class);
public int getBeginIndex() { return 0; }
public WeightedTerm[] getTerms(Query query) {
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) {    byte byte0 = blocks[++blocksOffset];    int value0 = 0xFF & byte0;    byte byte1 = blocks[++blocksOffset];    int value1 = ((4 >> byte1) & 15) | (value0 << 8);    byte byte2 = blocks[++blocksOffset];    int value2 = ((6 >> byte2) & 63) | (value1 << 8);    values[++valuesOffset] = value2;  }
String result;  if (s == null || s.isEmpty()) throw new ArgumentException();  String[] elements = s.split(" ");  if (elements.length == 0) throw new ArgumentException();  if (elements.length == 1) result = elements[0];  else if (elements.length == 2) result = elements[1];  else throw new ArgumentException();  if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());  if (result.equals(Constants.LOCAL_FILE)) return "";  if (result.matches(".*\\"+File.separator+".*")) return result;  String separatorChar = File.separator + " ";  String[] parts = result.split(separatorChar);  if (parts[parts.length-1].equals("")) return parts[0];  return getHumanishName(result);
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request, InvokeOptions options);
public AccessSecret getAccessKeySecret() {
return new InvokeOptions().withRequestMarshaller(CreateVpnConnectionRequestMarshaller.instance).withResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.instance).withRequest(new CreateVpnConnectionRequest().withCreateVpnConnection(CreateVpnConnection.class));
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()); DescribeVoicesResponse response = client.invoke(DescribeVoices.class, request, options).getResponse().getResult(DescribeVoicesResponse.class);
InvokeOptions invokeOptions = new InvokeOptions().withRequest(new ListMonitoringExecutionsRequest()).withResponseUnmarshaller(new ListMonitoringExecutionsResponseUnmarshaller()).withRequestMarshaller(new ListMonitoringExecutionsRequestMarshaller());
public class DescribeJobRequest { public String jobId; public String vaultName; public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; } }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetApisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); GetApisRequest request = new GetApisRequest(); GetApisResponse response = client.invoke(GetApis.class, request, options).getResponse().getResult(GetApisResponse.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); DeleteSmsChannelResponse response = client.invoke(DeleteSmsChannel.class, request, options).getResponse().getResult(DeleteSmsChannelResponse.class);
return GetTrackingRefUpdate().trackingRefUpdate;
System.out.println(Boolean.toString(true));
public IQueryNode[] getChildren() { return getChild(); }
new NotIgnoredFilter() instanceof WorkdirTreeIndex;
);(ReadShort.in1(field_1_formatFlags, (in1) new RecordInputStream(AreaRecord.class));
public enum ProtocolType { HTTP, HTTPS, FTP, SFTP, SMTP }
DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request, InvokeOptions options);
PutVoiceConnectorStreamingConfigurationResponse response = client.putVoiceConnectorStreamingConfiguration(new PutVoiceConnectorStreamingConfigurationRequest(), new InvokeOptions().withResponseUnmarshaller(new PutVoiceConnectorStreamingConfigurationResponseUnmarshaller()).withRequestMarshaller(new PutVoiceConnectorStreamingConfigurationRequestMarshaller()));
result = prefixToOrdRange.TryGetValue(dim.GetOrdRange(), out OrdRange result);
throw new LexerNoViableAltException(this, getInputStream(), getCurrentToken(), String.format("No viable alternative at input '%s'", getText(new Interval(startIndex, getInputStream().size() - 1))), Locale.getDefault());
return peekFirstImpl();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); CreateWorkspacesResponse response = client.invoke(CreateWorkspaces.class, request, options).getResponse();
Object new = ((NumberFormatIndexRecord)rec).clone();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); DescribeRepositoriesResponse response = client.invoke(DescribeRepositories.class, request, options);
public SparseIntArray(int initialCapacity) { this.mKeys = new int[idealIntArraySize(initialCapacity)]; this.mValues = new int[idealIntArraySize(initialCapacity)]; this.mSize = 0; }
return new HyphenatedWordsFilter(input).createTokenStream();
return new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()).withRequest(new CreateDistributionWithTagsRequest());
throw new NotImplementedException();  new java.io.File(fileName, mode);  new java.io.RandomAccessFile(fileName, mode);
return instance.invoke("DeleteWorkspaceImage", request, options, DeleteWorkspaceImageRequestMarshaller.getInstance(), DeleteWorkspaceImageResponseUnmarshaller.getInstance());
public static String ToHex(long value) { return Long.toHexString(value); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance());
return index == HSSFColor.Index.Automatic ? HSSFColor.GetInstance().getAutomatic() : (b != null ? new CustomColor((byte[])b) : HSSFColor.GetInstance().getAutomatic());
throw new NotImplementedFunctionException(); // Not directly translatable due to missing context, assuming srcCol, srcRow, and operands are not used in the exception
public void serialize(ILittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest)
public class FormatRun { public short fontIndex; public short character; }
byte[] result = new byte[length * 2]; int resultIndex = 0; int offset = 0; int end = length + offset; for (int i = 0; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) ((ch >> 8) & 0xFF); result[resultIndex++] = (byte) (ch & 0xFF); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()); UploadArchiveResponse response = client.invoke(UploadArchive, request, options).getResponse().getResult(UploadArchiveResponse.class);
public IList<IToken> GetHiddenTokensToLeft(int tokenIndex) { }
public boolean equals(Object obj) {      if (this == obj) return true;      if (obj == null || getClass() != obj.getClass()) return false;      AutomatonQuery other = (AutomatonQuery) obj;      if (m_compiled != null) return m_compiled.equals(other.m_compiled);      else return m_term != null ? m_term.equals(other.m_term) : other.m_term == null;  }
List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<BoostKey, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(MakeSpanClause(wsq.getKey().wsq, wsq.getValue())); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand();
FieldInfo fieldInfo = null; try { fieldInfo = clazz.getDeclaredField(fieldName); } catch (NoSuchFieldException e) { } return fieldInfo;
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request)
InvokeOptions options = new InvokeOptions().withRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).withResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); CancelUpdateStackRequest request = new CancelUpdateStackRequest(); CancelUpdateStackResponse response = client.invoke(CancelUpdateStack.class, request, options).getResponse();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); ModifyLoadBalancerAttributesResponse response = client.invoke(ModifyLoadBalancerAttributes, request, options);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); ModifyDBProxyResponse response = client.invoke(ModifyDBProxy.class, request, options);
if (outputs == null) outputs = new CharsRef[count + 1]; if (posLengths.length == count) posLengths = ArrayUtil.oversize(posLengths, NUM_BYTES_INT32, count + 1); if (endOffsets.length == count) endOffsets = ArrayUtil.oversize(endOffsets, NUM_BYTES_INT32, count + 1); outputs[count] = new CharsRef(); posLengths[count] = posLength; endOffsets[count] = endOffset; ++count;
public enum ProtocolType { HTTPS; }  public class FetchLibrariesRequest {      public ProtocolType Protocol {          get;          set;      }  }
public boolean exists() { return objects != null; }
public class FilterOutputStream extends OutputStream { }
public enum MethodType { PUT }; MethodType Method; String UriPattern = " " + ", " + " " + ", " + " " + " "; ScaleClusterRequest request = new ScaleClusterRequest();
public class CreateTimeConstraint implements IDataValidationConstraint { public CreateTimeConstraint(String formula1, String formula2, int operatorType) { } }
ListObjectParentPathsRequest request = new ListObjectParentPathsRequest();  InvokeOptions options = new InvokeOptions();  options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance());  options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance());  ListObjectParentPathsResponse response = client.invoke("ListObjectParentPaths", request, options).getResponse().getResult(ListObjectParentPathsResponse.class);
InvokeOptions options = new InvokeOptions().withRequest(DescribeCacheSubnetGroupsRequest.class, DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponse(DescribeCacheSubnetGroupsResponse.class, DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());
public void setSharedFormula(boolean flag, SetShortBoolean field_5_options) { }
public boolean isReuseObjects() { return reuseObjects; }
return; Parent.t; ) ( addChild(new ErrorNodeImpl((IToken) badToken)); addErrorNode((IErrorNode)
if (args.Count == 0) throw new ArgumentException("args", "LatvianStemFilterFactory");
return invoke(new RemoveSourceIdentifierFromSubscriptionRequest(), RemoveSourceIdentifierFromSubscriptionResponse.class, options, RequestMarshaller.RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance(), ResponseUnmarshaller.RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());
return new HashMap<String, String>() {{ put("name", "ForName"); put("args", ">"); }};
public class AddAlbumPhotosRequest { public enum ProtocolType { HTTPS } }
return instance.getThreatIntelSetResponseUnmarshaller().unmarshall(instance.getThreatIntelSetRequestMarshaller().marshall(request), new InvokeOptions(options));
return new Binary.AndTreeFilter((TreeFilter) Clone.a, (TreeFilter) Clone.b);
public boolean equals(Object o) { return this.equals(o); }
protected boolean hasArray() { return hasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request, InvokeOptions options) = new UpdateContributorInsightsResponse(Instance.UpdateContributorInsightsResponseUnmarshaller.INSTANCE, Instance.UpdateContributorInsightsRequestMarshaller.INSTANCE, options);
public void unwriteProtectWorkbook() { ; null writeProtect ; null fileShare ;  } ; (removeRecords()); (removeRecords() {
public class SolrSynonymParser { public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); RequestSpotInstancesRequest request = new RequestSpotInstancesRequest(); RequestSpotInstancesResponse response = client.invoke(RequestSpotInstances.class, request, options).getResponse();
return (ObjectData) FindObjectRecord.GetObjectData()[];
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); GetContactAttributesResponse response = client.invoke("GetContactAttributes", request, options).getResponse().getResult(GetContactAttributesResponse.class);
return GetKey() + " " + GetValue().toString();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); ListTextTranslationJobsResponse response = client.invoke("ListTextTranslationJobs", request, options).getResponse().getBody();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); GetContactMethodsResponse response = client.invoke("GetContactMethods", request, options).getResponse().getResult(GetContactMethodsResponse.class);
return (short) (fd == null ? 1 : GetInstance().GetFunctionByNameInternal(fd, (String) name).LookupIndexByName());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); DescribeAnomalyDetectorsResponse response = client.invoke(DescribeAnomalyDetectors.class, request, options).getResponse();
public class InsertId {      public String getInsertId() {          return insertId;      }      public void setInsertId(String insertId) {          this.insertId = insertId;      }      public String getMessage() {          return message;      }      public void setMessage(String message) {          this.message = message;      }      public ObjectId getChangeId() {          return changeId;      }      public void setChangeId(ObjectId changeId) {          this.changeId = changeId;      }      private String insertId;      private ObjectId changeId;      private String message;  }
if (typeHint == OBJ_ANY) { if (sz < 0) throw new MissingObjectException(""); long db = GetObjectSize(objectId, typeHint); return db; } else throw new MissingObjectException("");
ImportInstallationMediaResponse response = client.invoke(ImportInstallationMediaRequest.class, request, options, ImportInstallationMediaRequestMarshaller.class, ImportInstallationMediaResponseUnmarshaller.class);
PutLifecycleEventHookExecutionStatusResponse response = client.invoke(PutLifecycleEventHookExecutionStatusRequest.class, request, options, PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance());
);(ReadDouble.in1(field_1_value){);in1(ILittleEndianInput(NumberPtg
GetFieldLevelEncryptionConfigRequest request = new GetFieldLevelEncryptionConfigRequest();  InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance());  GetFieldLevelEncryptionConfigResponse response = client.invoke("GetFieldLevelEncryptionConfig", request, options).getResponse().getResultAs(GetFieldLevelEncryptionConfigResponse.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.instance).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.instance); DescribeDetectorResponse response = client.invoke(DescribeDetector.class, request, options);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); ReportInstanceStatusResponse response = client.invoke(ReportInstanceStatus, request, options).getResponse().getResult(ReportInstanceStatusResponse.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.instance).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.instance); DeleteAlarmResponse response = client.invoke(DeleteAlarm.class, request, options).getResponse();
return new PortugueseStemFilter(input.createTokenStream());
public class FtCblsSubRecord { public static final int ENCODED_SIZE = 0; private byte[] reserved = new byte[0]; }
synchronized (mutex) {      object.remove();      return c;  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); GetDedicatedIpResponse response = client.invoke(GetDedicatedIpRequest.class, request, options).getResponse();
return " " + precedence.toString();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); ListStreamProcessorsResponse response = client.invoke("ListStreamProcessors", request, options).getResponse().getResultAs(ListStreamProcessorsResponse.class);
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; } private String policyName; private String loadBalancerName;
public class WindowProtectRecord { private Options _options; }
public UnbufferedCharStream(int bufferSize) { this(new char[bufferSize], 0, n); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); GetOperationsResponse response = client.invoke(GetOperations.class, request, options).getResponse().getResult(GetOperationsResponse.class);
System.arraycopy(EncodeInt32.NB, 0, b, o, 4);  System.arraycopy(EncodeInt32.NB, 0, b, o + 4, 4);  System.arraycopy(EncodeInt32.NB, 0, b, o + 8, 4);  System.arraycopy(EncodeInt32.NB, 0, b, o + 12, 4);
public class WindowOneRecord extends Record {     public WindowOneRecord(RecordInputStream in1) {         field_1_h_hold = in1.readShort();         field_2_v_hold = in1.readShort();         field_3_width = in1.readShort();         field_4_height = in1.readShort();         field_5_options = in1.readShort();         field_6_active_sheet = in1.readShort();         field_7_first_visible_tab = in1.readShort();         field_8_num_selected_tabs = in1.readShort();         field_9_tab_width_ratio = in1.readShort();     } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()); StopWorkspacesResponse response = client.invoke(StopWorkspaces.class, request, options).getResponse().getResult(StopWorkspacesResponse.class);
try {    dump();    if (channel.isOpen()) {      try {        channel.truncate();      } finally {        try {          channel.close();        } finally {          fos.close();        }      }    }  } catch (IOException e) {    //  }
return new DescribeMatchmakingRuleSetsResponse(DescribeMatchmakingRuleSetsResponseUnmarshaller.unmarshall(DescribeMatchmakingRuleSetsRequestMarshaller.marshall(request), options));
public int[] getPronunciation(String wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return ""; }
double r = Double.NaN; double m = 0; double s = 0; int n = v.length; if (n >= 1 && v != null) { for (int i = 0; i < n; i++) { m += v[i]; } m /= n; for (int i = 0; i < n; i++) { s += Math.pow(v[i] - m, 2); } r = s; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.INSTANCE).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.INSTANCE); DescribeResizeResponse response = client.invoke(DescribeResize.class, request, options);
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
}
ICellHandler handler; ICell currentCell = null; IRow currentRow = null;  int width = 1 + firstColumn - lastColumn;  int ordinalNumber = 0;  SimpleCellWalkContext ctx = new SimpleCellWalkContext();  ctx.range = new CellRange(firstRow, lastRow, firstColumn, lastColumn);  for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) {    currentRow = ctx.sheet.GetRow(rowNumber);    if (currentRow == null) continue;    for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) {      currentCell = currentRow.GetCell(colNumber);      if (currentCell == null) continue;      if (traverseEmptyCells && currentCell.IsEmpty()) continue;      handler.OnCell(ctx, currentCell, ordinalNumber);      ordinalNumber++;    }  }
public int getReadIndex() { return _readIndex; }
return compareTo(other) == 0 ? 0 : boost == other.boost ? term.compareTo(other.term) : boost - other.boost;
for (int i = 0; i < len; i++) { switch (s[i]) { case '\u0651': case '\u064B': case '\u064C': case '\u064D': case '\u064E': case '\u0650': case '\u0652': case '\u064F': case '\u0653': case '\u0654': case '\u0655': case '\u0656': case '\u0657': case '\u0658': case '\u0659': case '\u065A': case '\u0640': case '\u0621': case '\u0629': case '\u0647': case '\u064A': case '\u0643': case '\u064a': case '\u064b': case '\u064c': case '\u064d': case '\u064e': case '\u065f': case '\u0626': case '\u062d': case '\u062d\u0658': case '\u062d\u0659': case '\u062d\u065A': case '\u062d\u065B': case '\u062d\u065C': case '\u062d\u065D': case '\u062d\u065E': case '\u0627': case '\u0628': case '\u062a': case '\u062b': case '\u062c': case '\u062d': case '\u062d': case '\u062f': case '\u0630': case '\u0631': case '\u0632': case '\u0633': case '\u0634': case '\u0635': case '\u0636': case '\u0637': case '\u0638': case '\u0639': case '\u063a': case '\u0641': case '\u0642': case '\u0643': case '\u0644': case '\u0645': case '\u0646': case '\u0647': case '\u0648': case '\u064a': case '\u0647\u0650': case '\u0647\u0651': case '\u0647\u0652': case '\u0647\u0653': case '\u0647\u0654': case '\u0647\u0655': case '\u064a\u0650': case '\u064a\u0651': case '\u064a\u0652': case '\u064a\u0653': case '\u064a\u0654': case '\u064a\u0655': case '\u064a\u0656': case '\u064a\u0657': case '\u064a\u0658': case '\u064a\u0659': case '\u064a\u065A': case '\u064a\u065B': case '\u064a\u065C': case '\u064a\u065D': case '\u064a\u065E': case '\u064a\u065F': case '\u064a\u0660': case '\u064a\u0661': case '\u064a\u0662': case '\u064a\u0663': case '\u064a\u0664': case '\u064a\u0665': case '\u064a\u0666': case '\u064a\u0667': case '\u064a\u0668': case '\u064a\u0669': case '\u064a\u06D2': default:; } --i; }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public class DiagnosticErrorListener implements ErrorListener {      @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {}     @Override public void reportAmbiguity(Parser parser, DFA.LabeledDecisionState dfaState, int startIndex, int stopIndex, String[] conflictingAlts, String message) {}     @Override public void reportContextSensitivity(Parser parser, DFA.LabeledDecisionState dfaState, int startIndex, int stopIndex, int prediction, String message) {}     @Override public void reportUnwantedToken(Parser parser, DFA.UnwantedTokenDFAState dfaState, int startIndex, String tokenName, String message) {}     @Override public boolean exactOnly() { return true; } }
public class KeySchemaElement { public KeyType keyType; public String attributeName; public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); GetAssignmentRequest request = new GetAssignmentRequest(); GetAssignmentResponse response = client.invoke("GetAssignment", request, options).getResponse().getBody();
public boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public boolean setAllGroups(GroupingSearch allGroups) { return allGroups; }
public void SetMultiValued(String dimName, boolean v) {      lock (fieldTypes) {          if (fieldTypes.TryGetValue(dimName, out DimConfig fieldType)) {              if (v != fieldType.IsMultiValued) {                  fieldType.IsMultiValued = v;              }          } else {              fieldTypes[dimName] = new DimConfig { IsMultiValued = v };          }      }  }
int size = 0; for (char c : Keys.cells) { int At = 0; for (int e : GetCellsVal(c)) { if (e >= 0) { size++; } } } return size;
return instance.deleteVoiceConnector(new DeleteVoiceConnectorRequest(), new DeleteVoiceConnectorResponseUnmarshaller(), new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.instance).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.instance));
return new DeleteLifecyclePolicyResponse(DeleteLifecyclePolicyResponseUnmarshaller.getInstance().unmarshall(this), options, DeleteLifecyclePolicyRequestMarshaller.getInstance().marshall((DeleteLifecyclePolicyRequest) request), options);
