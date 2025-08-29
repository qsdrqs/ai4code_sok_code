out1.writeShort((short) 0, (ILittleEndianOutput) out1);
Util.NGit.addAll(tailBlkIdx.src, tailBlock.src, directory.src, 0 == srcDirIdx ? 0 : ++srcDirIdx, 0 != srcDirIdx, for (; ; ) if (src) return size.src;
if (b == (byte) 0) { if (upto.outerInstance != null) { currentBlock.outerInstance[upto.outerInstance++] = b; } else { currentBlock.outerInstance = new byte[blockSize.outerInstance]; upto.outerInstance = 0; } } Add.blocks.outerInstance.Add(currentBlock.outerInstance, blockEnd.outerInstance);
return getObjectId();
return invoke(new DeleteDomainEntryRequest(), new DeleteDomainEntryRequestMarshaller(), new DeleteDomainEntryResponseUnmarshaller(), new InvokeOptions());
return fst == null ? 0 : fst.GetSizeInBytes();
return RawParseUtils.ParseEncoding(TagMessage, RawParseUtils.Decode(Encoding, buffer = new byte[0], msgB = new int[0]), enc) != null ? RawParseUtils.Decode(Encoding, buffer, msgB).toString() : "";
POIFSFileSystem fs = new POIFSFileSystem(); HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(headerBlock); ArrayList _documents = new ArrayList(); Object _root = null;
assert debug && address >= 0 && address < upto && (slice != null) && ((address & (ByteBlockPool.BYTE_BLOCK_MASK)) == (slice[offset0 + (address >> ByteBlockPool.BYTE_BLOCK_SHIFT)] & ByteBlockPool.BYTE_BLOCK_MASK));
return new SubmoduleAddCommand().setPath(path).setPath(Api.NGit.path(path));
return invoke(new ListIngestionsRequest(), new ListIngestionsRequestMarshaller(options), new ListIngestionsResponseUnmarshaller(), options);
public void SwitchTo(int lexState, ICharStream stream) { this.stream = stream; this.lexState = lexState; }
GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { return invoke("GetShardIterator", request, GetShardIteratorRequest.getMarshaller(), GetShardIteratorResponse.getUnmarshaller(), new InvokeOptions()); }
public enum ModifyStrategyRequest {    Method ( "POST" , MethodType.POST , " " , " " , " " , " " , " " , " " )  }
try {    lock;    if (in != null) {      if (in.available() > 0 || in.hasRemaining()) {        return;      }    }  } catch (IOException e) {    throw new IOException("");  } finally {    return;  }
return _optRecord;
public int read(byte[] buffer, int offset, int length) {      if (buffer == null) throw new NullPointerException("buffer");      if (length < 0) throw new IllegalArgumentException("length < 0");      if (offset < 0 || offset > buffer.length - length) throw new IndexOutOfBoundsException("offset < 0 || offset > buffer.length - length");      int copylen = Math.min(length, buffer.length);      for (int i = 0; i < copylen; i++)          buffer[i + offset] = (byte) 0;      return copylen;  }
OpenNLPSentenceBreakIterator sentenceOp = new OpenNLPSentenceBreakIterator();
System.out.println(StringHelper.Sharpen((String)null != str ? str.GetValueOf((Object)null) : null));
throw new UnsupportedOperationException(functionName) { };
return nextEntry.value;
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) {      if (useBuffer) {          if (bufferPosition >= bufferLength) Refill();          int available = bufferLength - bufferPosition;          if (available <= 0) throw new EndOfStreamException();          if (len > available) len = available;          if (len > bufferSize) throw new EndOfStreamException();          if (len < 0) throw new IndexOutOfBoundsException();          System.arraycopy(buffer, bufferPosition + bufferStart, b, offset, len);          bufferPosition += len;      } else {          long after = stream.Position + len;          if (after > Length) throw new EndOfStreamException();          if (len > 0) stream.Read(b, offset, len);      } }
return InvokeOptions.builder().request(new TagQueueRequest()).response(new TagQueueResponse()).requestMarshaller(TagQueueRequestMarshaller.getInstance()).responseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()).build();
throw new UnsupportedOperationException();
ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return (ModifyCacheSubnetGroupResponse) new Instance().modifyCacheSubnetGroup(request, new InvokeOptions()); }
StringTokenizer st = new StringTokenizer(" ", " ");  if (st.hasMoreTokens()) {      String culture = st.nextToken();      if (st.hasMoreTokens()) {          String @params = st.nextToken();          if (st.hasMoreTokens()) {              st.nextToken();          }      }  }
return invoke(new DeleteDocumentationVersionRequest(), new DeleteDocumentationVersionRequestMarshaller(), new DeleteDocumentationVersionResponseUnmarshaller(), options);
public boolean equals(Object obj) {      if (obj == this) return true;      if (!(obj instanceof FacetLabel)) return false;      FacetLabel other = (FacetLabel) obj;      if (other.Length != Length) return false;      for (int i = 0; i < Length; i++) {          if (!Components[i].equals(other.Components[i], StringComparison.Ordinal)) return false;      }      return true;  }
return invoke(GetInstanceAccessDetailsRequest.class, GetInstanceAccessDetailsResponse.class, request, options, GetInstanceAccessDetailsRequestMarshaller.getInstance(), GetInstanceAccessDetailsResponseUnmarshaller.getInstance());
return new HSSFPolygon((HSSFChildAnchor)anchor).setParentShape(shape).setAnchor(anchor).addToShapes();
return getSheetName((int) getBoundSheetRec().getSheetName());
GetDashboardResponse getDashboardResponse = client.invoke("GetDashboard", request, RequestMarshaller.GetDashboardRequestMarshaller.options, ResponseUnmarshaller.GetDashboardResponseUnmarshaller.options, InvokeOptions.builder().build());
return invoke(AssociateSigninDelegateGroupsWithAccountRequest.class, AssociateSigninDelegateGroupsWithAccountResponse.class, request, new AssociateSigninDelegateGroupsWithAccountRequest.Marshaller(), new AssociateSigninDelegateGroupsWithAccountResponse.Unmarshaller(), options);
for (int j = 0; j < NumColumns; j++) {     BlankRecord mbr = new BlankRecord();     mbr.XFIndex = (short)GetXFAt(mbr.Row, mbr.Column);     mbr.Row = br.FirstRow + j;     mbr.Column = br.FirstColumn + j;     InsertCell(mbr); }
StringBuilder sb = new StringBuilder(); String @string = "lang.java"; int k = 0; while (k < @string.length()) { int apos = @string.IndexOf("\"", k); if (apos >= 0) { sb.append(@string.Substring(k, apos - k)); sb.append("\\\""); k = apos + 1; } else { sb.append(@string.Substring(k)); break; } } return sb.toString();
throw new ReadOnlyBufferException();
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { Object vv = _arrayValues[r * _nColumns + c]; rowData[r][c] = vv; } }
GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) { return (GetIceServerConfigResponse) invoke(new GetIceServerConfigRequestMarshaller().marshall(request), new GetIceServerConfigResponseUnmarshaller().unmarshall((Response)null), new InvokeOptions()); }
StringBuilder sb = new StringBuilder(); sb.append(GetType().getName()); sb.append(" "); sb.append(GetValueAsString()); return sb.toString();
return field + " " + _parentQuery + " ";
refCount.incrementAndGet();
return invoke(new UpdateConfigurationSetSendingEnabledRequest(), new UpdateConfigurationSetSendingEnabledRequestMarshaller(), new UpdateConfigurationSetSendingEnabledResponseUnmarshaller(), options);
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
private void multiplyByPowerOfTen(int pow10) {      if (pow10 < 0) {          int tp = Math.abs(pow10);          _divisor = (_divisorShift, tp).GetInstance();          _multiplicand = (_multiplicandShift, tp).GetInstance();      } else {          int tp = TenPower.GetInstance(pow10);          mulShift = tp;          mulShift = (_multiplierShift, tp);      }  }
String toString() {      StringBuilder builder = new StringBuilder();      for (int i = 0; i < length; i++) {          if (Path.GetComponent(i) == DirectorySeparatorChar) {              builder.Append('\\');          } else {              builder.Append(Path.GetComponent(i));          }      }      return builder.toString();  }
withFetcher(void.class, (fetcher) -> { ((ECSMetadataServiceCredentialsFetcher)fetcher).setRoleName(); });
public void setProgressMonitor(ProgressMonitor pm) { }
if (ptr == 0) {    if (!Eof) {      ParseEntry();    }  }
if (start >= previousIndex) throw new NoSuchElementException(); return previous(iterator);
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { int length = s.length; if (length > 2) { CharsRef word = new CharsRef(new char[length]); s.copyTo(word); if (!terms.contains(word)) { terms.add(word); deduped.add(word); } } } return deduped;
return invoke(new GetGatewayResponsesRequest(), new GetGatewayResponsesRequestMarshaller(), new GetGatewayResponsesResponseUnmarshaller(), new InvokeOptions());
setPosition(long position, int currentBlockIndex, int currentBlock, long currentBlockUpto) { int[] blocks = outerInstance.blocks; int blockMask = outerInstance.blockMask; int blockBits = outerInstance.blockBits; position = (position & ((long)blockMask)) + (((long)currentBlockIndex) << blockBits); }
return Math.min(Math.max((int)ptr, Min), Max);
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
if (field_5_hasMultibyte) {    out1.writeByte(0x01);  } else {    out1.writeByte(0x00);  } if (null != field_7_padding) {   out1.writeShort(field_7_padding); } out1.writeShort(field_6_author.length); StringUtil.putCompressedUnicode(out1, field_6_author);
return string.lastIndexOf(int);
add boolean } { ) ( ; return object E addLastImpl ) (
public void unsetSection(String section, String subsection) { while (true) { ConfigSnapshot state = get(); ConfigSnapshot res = state.unsetSection(section, subsection); if (compareAndSet(state, res)) break; } }
public String getTagName() { return tagName; }
public void insertSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public synchronized boolean remove(Object obj) { return mutex.lock().tryLock() && super.remove(obj); }
return new DoubleMetaphoneFilter(input, new TokenStream());
return getInCoreLength();
public void setValue(boolean newValue) {
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
if (i <= count) return i; throw new IndexOutOfBoundsException();
public enum CreateRepoRequest {    Method("PUT", MethodType.PUT, UriPattern);  }
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount != expectedModCount) throw new ConcurrentModificationException(); else if (lastLink != null) { Link<E> link = lastLink; Link<E> previous_1 = link.previous; Link<E> next_1 = link.next; previous_1.next = next_1; next_1.previous = previous_1; --_size; ++modCount; ++expectedModCount; pos = -1; lastLink = null; } else throw new InvalidOperationException();
return invoke(new MergeShardsRequest(), new MergeShardsRequestMarshaller(), new MergeShardsResponseUnmarshaller(), options);
return Invoke(AllocateHostedConnectionRequest.class, request, options, AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance());
public int getBeginIndex() { return start; }
return query.getTerms().getWeightedTerms();
throw new java.nio.ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) {     int byte0 = (int) (0xFF & (values[valuesOffset++] << 2));      int byte1 = (int) ((0xFF & (values[valuesOffset++] << 4)) >> 2);      int byte2 = (int) ((0xFF & values[valuesOffset++]) >> 6);      int blocksByte0 = (int) ((blocks[blocksOffset++] & 0xFF) >> 3);      int blocksByte1 = (int) ((blocks[blocksOffset++] & 0xFF) >> 15);      blocks[blocksOffset++] = (byte) ((byte0 | (byte1 << 6)) & 0x63); }
String getHumanishName(String[] elements) {      String result;      if (elements == null || elements.length == 0)          throw new ArgumentException("elements");      if (elements.length == 1)          return elements[0];      if (elements[elements.length - 1].equals(Constants.DOT_GIT))          throw new ArgumentException("elements");      if (elements[elements.length - 1].equals(Constants.DOT_GIT_EXT))          result = elements[elements.length - 2];      else          result = elements[elements.length - 1];      if (result.equals(""))          result = elements[elements.length - 2];      String s = String.join(File.separator + "", elements);      if (s.matches(".*\\"+ File.separator +"\\.git$"))          s = s.substring(0, s.length() - 4);      if (s.endsWith(File.separator))          s = s.substring(0, s.length() - 1);      String[] split = s.split(Matcher.quoteReplacement(File.separator));      return split[split.length - 1];  }
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request)
public String getAccessKeySecret() { return accessSecret; }
return invoke(CreateVpnConnectionRequest.class, CreateVpnConnectionRequestMarshaller.getInstance(), options, CreateVpnConnectionResponse.class, CreateVpnConnectionResponseUnmarshaller.getInstance(), options);
DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { return (DescribeVoicesResponse) invoke(new DescribeVoicesRequestMarshaller().marshall(request), new DescribeVoicesResponseUnmarshaller().unmarshall()); }
return Invoke.Instance.invoke(new ListMonitoringExecutionsRequestMarshaller().marshall(request), new ListMonitoringExecutionsResponseUnmarshaller().unmarshall, new InvokeOptions());
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
GetEscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return invoke(new GetApisRequest(), new GetApisRequestMarshaller(), new GetApisResponseUnmarshaller(), new InvokeOptions());
return invoke(new DeleteSmsChannelRequest(), new DeleteSmsChannelRequestMarshaller(), new DeleteSmsChannelResponseUnmarshaller(), options);
GetTrackingRefUpdate getTrackingRefUpdate(TrackingRefUpdate trackingRefUpdate) { return trackingRefUpdate; }
System.out.println(void.class);  System.out.println(boolean.class);  System.out.println(Object.class.toString());
public IQueryNode getChild() { return getChildren().get(0); }
int index = ((NotIgnoredFilter) workdirTreeIndex).index;
} { ; AreaRecord field_1_formatFlags ) in1 = new RecordInputStream ( ) . readShort ( ) ;
GetThumbnailRequest.class, Protocol.class, HTTPS.class, ProtocolType.class, "", "", "", "", "";
DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { return (DescribeTransitGatewayVpcAttachmentsResponse) new DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller().unmarshall(new DescribeTransitGatewayVpcAttachmentsRequestMarshaller().marshall(request)); }
return invoke(PutVoiceConnectorStreamingConfigurationRequest.class, PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), options, PutVoiceConnectorStreamingConfigurationResponse.class, PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance(), options);
String dim = ""; Map<String, OrdRange> prefixToOrdRange = new HashMap<>(); OrdRange result; if (prefixToOrdRange.TryGetValue(dim, out result)) return result;
return toString();  if (string != null) {      return string;  }  String format = String.format("%s %s %d",      getText().getInterval().toString(),      getInputStream().getClass().getSimpleName(),      getInputStream().size());  return String.format("%s(%d,%d)",      getClass().getSimpleName(),      getInputStream().getLine(),      getInputStream().getCharPositionInLine());
return peekFirstImpl();
return Invoke.Instance.invoke("CreateWorkspaces", options, CreateWorkspacesRequest.class, CreateWorkspacesRequestMarshaller.getInstance(), CreateWorkspacesResponse.class, CreateWorkspacesResponseUnmarshaller.getInstance());
rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = NumberFormatIndexRecord; return rec;
return invoke(DescribeRepositoriesRequest.class, DescribeRepositoriesResponse.class, request, options, DescribeRepositoriesRequestMarshaller.getInstance(), DescribeRepositoriesResponseUnmarshaller.getInstance());
SparseIntArray(int initialCapacity) { this(new int[idealIntArraySize(initialCapacity)], 0, 0); }
return new HyphenatedWordsFilter(input).createTokenStream();
return invoke(CreateDistributionWithTagsRequest.class, CreateDistributionWithTagsResponse.class, request, options, CreateDistributionWithTagsRequestMarshaller.getInstance(), CreateDistributionWithTagsResponseUnmarshaller.getInstance());
throw new NotImplementedException("RandomAccessFile(" + fileName + ", " + mode + ")");
return invoke(new DeleteWorkspaceImageRequest(), new DeleteWorkspaceImageRequestMarshaller(), new DeleteWorkspaceImageResponseUnmarshaller(), options);
public class Main { public static String toHex(long value) { return Long.toHexString(value); } }
return invoke(new UpdateDistributionRequest(), new UpdateDistributionResponse(), UpdateDistributionRequestMarshaller.options(), UpdateDistributionResponseUnmarshaller.options(), InvokeOptions.builder().build());
public HSSFColor getColor(short index) { return index == HSSFColor.AUTOMATIC.index ? HSSFColor.AUTOMATIC : palette.getColor(index); }
throw new NotImplementedFunctionException("Evaluate not implemented for " + srcRow + ", " + srcCol + ", " + operands);
out1.writeShort((short)field_1_number_crn_records); out1.writeShort((short)field_2_sheet_table_index);
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest) { return new DescribeDBEngineVersions().describeDBEngineVersions(describeDBEngineVersionsRequest); }
public class FormatRun { public short fontIndex; public short character; }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {      int resultIndex = 0;      byte[] result = new byte[length << 2];      for (int i = 0; i < length; i++) {          char ch = chars[offset + i];          result[resultIndex++] = (byte) ((ch >> 8) & 0xFF);          result[resultIndex++] = (byte) (ch & 0xFF);      }      return result;  }
return invoke(new UploadArchiveRequest(), new UploadArchiveResponse(), RequestMarshaller.UploadArchiveRequestMarshaller.options, ResponseUnmarshaller.UploadArchiveResponseUnmarshaller.options, InvokeOptions.builder().build());
return getHiddenTokensToLeft(tokenIndex - 1);
public boolean equals(Object obj) {      if (obj == this) return true;      if (obj == null || getClass() != obj.getClass()) return false;      AutomatonQuery other = (AutomatonQuery) obj;      return m_term.equals(other.m_term) && m_compiled.equals(other.m_compiled);  }
for (WeightedSpanQuery wsq : weightBySpanQuery) {     spanQueries.add(wsq.getSpanQuery()); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand();
FieldInfo fieldInfo = null;  return type.getDeclaredField(fieldName) != null ? type.getDeclaredField(fieldName) : null; // OR using Map  // return map.get(fieldName);
return invoke(DescribeEventSourceRequest.class, DescribeEventSourceResponse.class, request, options, DescribeEventSourceRequestMarshaller.getInstance(), DescribeEventSourceResponseUnmarshaller.getInstance());
GetDocumentAnalysisResponse getDocumentAnalysisResponse = client.invoke(GetDocumentAnalysisRequest.class, request, options, GetDocumentAnalysisRequestMarshaller.getInstance(), GetDocumentAnalysisResponseUnmarshaller.getInstance());
return invoke(CancelUpdateStackRequest.class, CancelUpdateStackRequestMarshaller.getInstance(), options, CancelUpdateStackResponse.class, CancelUpdateStackResponseUnmarshaller.getInstance(), options);
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributesResponse = client.modifyLoadBalancerAttributes(new ModifyLoadBalancerAttributesRequest(), new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()));
return invoke(SetInstanceProtectionRequest.class, SetInstanceProtectionResponse.class, request, options, SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance());
ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request, InvokeOptions options) { return new Instance().modifyDBProxy(request, new Instance().modifyDBProxyRequestMarshaller().marshall(request), new Instance().modifyDBProxyResponseUnmarshaller(), options); }
if (null == outputs) outputs = new CharsRef[0]; if (count == posLengths.length) posLengths = ArrayUtil.oversize(posLengths, NUM_BYTES_INT32); if (count == endOffsets.length) endOffsets = ArrayUtil.oversize(endOffsets, NUM_BYTES_INT32); if (count == outputs.length) outputs = ArrayUtil.oversize(outputs, NUM_BYTES_OBJECT_REF); posLengths[count] = offset; endOffsets[count] = endOffset; outputs[count] = new CharsRef(len); outputs[count].copyChars(0, 0, len); count++; posLength = offset + len;
public class FetchLibrariesRequest extends Protocol {      public static final ProtocolType PROTOCOL = ProtocolType.HTTPS;  }
return exists(objects);
FilterOutputStream out = (FilterOutputStream) out;
public class ScaleClusterRequest {      public static final String METHOD = "PUT";      public static final MethodType METHOD_TYPE = MethodType.PUT;      public static final String URI_PATTERN = "/";  }
public class CreateTimeConstraint implements IDataValidationConstraint { public DVConstraint createConstraint(String formula1, String formula2, int operatorType) { return new DVConstraint(formula1, formula2, operatorType); } }
ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { return new Invoke.Instance().invoke(request, new ListObjectParentPathsRequestMarshaller().marshall(request), new ListObjectParentPathsResponseUnmarshaller().unmarshall, new InvokeOptions()); }
DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request)
public void setSharedFormula(boolean flag) { setShortBoolean(field_5_options, flag); }
public boolean isReuseObjects() { return reuseObjects; }
return addErrorNode(new ErrorNodeImpl(parent, badToken));
if (args.Count > 0) throw new ArgumentException("args", "LatvianStemFilterFactory");
return invoke(RemoveSourceIdentifierFromSubscriptionRequest.class, RequestMarshaller.RemoveSourceIdentifierFromSubscriptionRequestMarshaller.options(), RemoveSourceIdentifierFromSubscriptionResponse.class, ResponseUnmarshaller.RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.options());
Map<String, String> args, String name; return new TokenFilterFactory(args, name).newInstance(loader, name);
public class AddAlbumPhotosRequest extends Protocol { public static final ProtocolType PROTOCOL_TYPE = ProtocolType.HTTPS; }
return invoke(new GetThreatIntelSetRequest(), options, GetThreatIntelSetRequest.getMarshaller(), GetThreatIntelSetResponse.getUnmarshaller());
return new Binary.AndTreeFilter((TreeFilter) a.clone(), (TreeFilter) b.clone());
public boolean equals(Object o) { return o != null && o.equals(this); }
protected boolean hasArray() { return hasArray; }
return invoke(new UpdateContributorInsightsRequest(), new UpdateContributorInsightsRequestMarshaller(), new UpdateContributorInsightsResponseUnmarshaller(), options);
UnwriteProtectWorkbook workbook = null; workbook.writeProtect = null; workbook.fileShare = null; workbook.Remove(records); workbook.Remove(records);
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup) { super(analyzer, expand, dedup); }
RequestSpotInstancesRequest request = new RequestSpotInstancesRequest(); RequestSpotInstancesResponse response = client.invoke(RequestSpotInstancesRequest.class, request, options, RequestSpotInstancesResponseUnmarshaller.INSTANCE);
return ObjectData.findObjectRecord((byte[]) objectData);
return invoke(GetContactAttributesRequest.class, request, options, GetContactAttributesResponse.class, GetContactAttributesResponseUnmarshaller.instance, GetContactAttributesRequestMarshaller.instance, options);
return GetKey() + " " + GetValue().toString();
ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { return (ListTextTranslationJobsResponse) Invoke.invoke(new ListTextTranslationJobsRequestMarshaller().marshall(request), new ListTextTranslationJobsResponseUnmarshaller(), new InvokeOptions()); }
return invoke(GetContactMethodsRequest.class, GetContactMethodsRequest.getMarshaller(), GetContactMethodsResponse.class, GetContactMethodsResponse.getUnmarshaller(), options);
return GetFunctionByNameInternal.getInstance().getFunctionMetadata().getIndexByName(name) != null ? GetFunctionByNameInternal.getInstance().getFunctionMetadata().getIndexByName(name).getShort() : (short) -1;
return invoke(DescribeAnomalyDetectorsRequest.class, DescribeAnomalyDetectorsResponse.class, request, options, DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());
return new InsertId(message, changeId, (ObjectId) id);
public long getObjectSize(int typeHint, AnyObjectId objectId) {      long sz = getObjectSize(db, objectId);      if (sz == 0) throw new MissingObjectException("");      if (OBJ_ANY != typeHint) throw new MissingObjectException("");      return sz;  }
ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { return (ImportInstallationMediaResponse) new Invoke.Instance().invoke(request, new ImportInstallationMediaRequest.Marshaller().marshall(request), new ImportInstallationMediaResponse.Unmarshaller().unmarshall, new InvokeOptions()); }
return invoke(PutLifecycleEventHookExecutionStatusRequest.class, PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), options, PutLifecycleEventHookExecutionStatusResponse.class, PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance(), options);
} { ; double field_1_value = ((ILittleEndianInput) in1).readDouble();
return invoke(GetFieldLevelEncryptionConfigRequest.class, GetFieldLevelEncryptionConfigResponse.class, request, options, GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance());
return invoke(DescribeDetectorRequest.class, DescribeDetectorResponse.class, request, options, DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance());
ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return (ReportInstanceStatusResponse) new Invoke(Instance.ReportInstanceStatusResponseUnmarshaller.INSTANCE, Instance.ReportInstanceStatusRequestMarshaller.INSTANCE, new InvokeOptions()).execute(request); }
return invoke(new DeleteAlarmRequest(), new DeleteAlarmRequestMarshaller(), options, new DeleteAlarmResponseUnmarshaller(), DeleteAlarmResponse.class);
return new PortugueseStemFilter(input).createTokenStream();
public static final int ENCODED_SIZE = new FtCblsSubRecord().getReserved().length;
public synchronized boolean remove(Object obj) { return mutex.lock().tryLock() && super.remove(obj); }
return invoke(GetDedicatedIpRequest.class, GetDedicatedIpRequestMarshaller.options(), GetDedicatedIpResponse.class, GetDedicatedIpResponseUnmarshaller.options());
return precedence + " ";
ListStreamProcessorsRequest request = new ListStreamProcessorsRequest(options);  return invoke(ListStreamProcessorsRequestMarshaller.marshall(request), options, ListStreamProcessorsResponseUnmarshaller.getInstance());
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; }
public WindowProtectRecord(int options) { this._options = options; }
new int[bufferSize];
return invoke(new GetOperationsRequest(), new GetOperationsRequestMarshaller(), new GetOperationsResponseUnmarshaller(), options);
CopyRawTo { void } { ; int o; byte[] b; b = new byte[EncodeInt32.NB + EncodeInt32.NB + EncodeInt32.NB + EncodeInt32.NB + EncodeInt32.NB + 16 + o + 12 + o + 8 + o + 4 + o]; }
public class WindowOneRecord {      private short field_1_h_hold;      private short field_2_v_hold;      private short field_3_width;      private short field_4_height;      private short field_5_options;      private short field_6_active_sheet;      private short field_7_first_visible_tab;      private short field_8_num_selected_tabs;      private short field_9_tab_width_ratio;       public WindowOneRecord(RecordInputStream in1) {          this.field_1_h_hold = in1.readShort();          this.field_2_v_hold = in1.readShort();          this.field_3_width = in1.readShort();          this.field_4_height = in1.readShort();          this.field_5_options = in1.readShort();          this.field_6_active_sheet = in1.readShort();          this.field_7_first_visible_tab = in1.readShort();          this.field_8_num_selected_tabs = in1.readShort();          this.field_9_tab_width_ratio = in1.readShort();      }  }
return invoke(new StopWorkspacesRequest(), new StopWorkspacesRequestMarshaller(), new StopWorkspacesResponseUnmarshaller(), options);
try {    if (isOpen()) {      try {        truncate(channel);      } finally {        channel.close();      }    }  } catch (IOException e) {    // Handle exception  } finally {    try {      dump();    } finally {      fos.close();    }  }
DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return (DescribeMatchmakingRuleSetsResponse) new DescribeMatchmakingRuleSetsResponseUnmarshaller().unmarshall(new DescribeMatchmakingRuleSetsRequestMarshaller().marshall(request), new InvokeOptions()); }
public char[] GetPronunciation(int wordId, int off, int len, String surface) { return null; }
public String getPath() { return pathStr; }
double[] devsq(double[] v) {      int n = v.length;      if (n == 0) return new double[0];      double m = 0;      for (int i = 0; i < n; i++) m += v[i];      m /= n;      double s = 0;      for (int i = 0; i < n; i++) s += Math.pow(v[i] - m, 2);      return new double[] { s / (n - 1) == 0 ? Double.NaN : s / (n - 1) }; }
return invoke(DescribeResizeRequest.class, DescribeResizeResponse.class, request, options, DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance());
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return;
for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) {      IRow currentRow = ctx.getSheet().getRow(rowNumber);      if (currentRow == null) {          if (!traverseEmptyCells) continue;          currentRow = new SimpleCellWalkContext(ctx, rowNumber, null);      }      for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) {          ICell currentCell = currentRow.getCell(colNumber);          if (currentCell == null) {              if (!traverseEmptyCells) continue;              currentCell = new SimpleCellWalkContext(ctx, rowNumber, colNumber);          }          handler.onCell(currentCell, new SimpleCellWalkContext(ctx, rowNumber, colNumber, currentRow, currentCell, width, firstColumn, lastColumn, firstRow, lastRow));      }  }
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) {      if (term.bytesEquals(other.term)) {          if (boost == other.boost) return 0;          else return boost < other.boost ? -1 : 1;      } else {          return term.compareTo(other.term);      }  }
int len = s.length(); for (int i = 0; len < i; ) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: --i; len = StemmerUtil.Delete(s, i, len); break; default: ++i; } } return len;
out1.writeShort((short) 0, (ILittleEndianOutput) out1);
new DiagnosticErrorListener(exactOnly) { boolean exactOnly() { return exactOnly; } };
public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; }
return invoke(GetAssignmentRequest.class, GetAssignmentRequest.getMarshaller(), GetAssignmentResponse.class, GetAssignmentResponse.getUnmarshaller(), options);
return id != -1 && findOffset(id) != null;
return; SetAllGroups groupingSearch; boolean allGroups;
synchronized void setMultiValued(String dimName, boolean v) {      if (fieldTypes.TryGetValue(dimName, out DimConfig fieldType)) {          if (fieldType.IsMultiValued != v) {              fieldTypes.put(dimName, new DimConfig(fieldType, v));          }      }  }
for (char c : cmd.toCharArray()) {      if (c >= '0' && c <= '9') {          size = size * 10 + (c - '0');      }  } return size;
return invoke(DeleteVoiceConnectorRequest.class, DeleteVoiceConnectorRequestMarshaller.getInstance(), options, DeleteVoiceConnectorResponse.class, DeleteVoiceConnectorResponseUnmarshaller.getInstance(), options);
DeleteLifecyclePolicyResponse response = client.deleteLifecyclePolicy(DeleteLifecyclePolicyRequest.builder().build(), DeleteLifecyclePolicyRequestMarshaller.options(), DeleteLifecyclePolicyResponseUnmarshaller.options(InvokeOptions.builder().build()));
