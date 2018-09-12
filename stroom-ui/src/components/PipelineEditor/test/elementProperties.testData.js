export default {
  JSONWriter: {
    indentOutput: {
      elementType: {
        type: 'JSONWriter',
        category: 'WRITER',
        roles: ['writer', 'mutator', 'stepping', 'target'],
        icon: 'json.svg',
      },
      name: 'indentOutput',
      type: 'boolean',
      description: 'Should output JSON be indented and include new lines (pretty printed)?',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    encoding: {
      elementType: {
        type: 'JSONWriter',
        category: 'WRITER',
        roles: ['writer', 'mutator', 'stepping', 'target'],
        icon: 'json.svg',
      },
      name: 'encoding',
      type: 'String',
      description: 'The output character encoding to use.',
      defaultValue: 'UTF-8',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
  },
  BadTextXMLFilterReader: {
    tags: {
      elementType: {
        type: 'BadTextXMLFilterReader',
        category: 'READER',
        roles: ['reader', 'hasTargets'],
        icon: 'stream.svg',
      },
      name: 'tags',
      type: 'String',
      description:
        'A comma separated list of XML elements between which non-escaped characters will be escaped.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  XMLFragmentParser: {
    textConverter: {
      elementType: {
        type: 'XMLFragmentParser',
        category: 'PARSER',
        roles: ['parser', 'hasCode', 'simple', 'hasTargets', 'stepping', 'mutator'],
        icon: 'xml.svg',
      },
      name: 'textConverter',
      type: 'entity',
      description: 'The XML fragment wrapper that should be used to wrap the input XML.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['TextConverter'],
      displayPriority: 1,
    },
  },
  ElasticIndexingFilter: {
    index: {
      elementType: {
        type: 'ElasticIndexingFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'ElasticSearch.svg',
      },
      name: 'index',
      type: 'entity',
      description: 'The elastic index to write records to.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['ElasticIndex'],
      displayPriority: 1,
    },
    idFieldName: {
      elementType: {
        type: 'ElasticIndexingFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'ElasticSearch.svg',
      },
      name: 'idFieldName',
      type: 'String',
      description: 'The field name to use as the unique ID for records.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
  },
  RecordCountFilter: {
    countRead: {
      elementType: {
        type: 'RecordCountFilter',
        category: 'FILTER',
        roles: ['hasTargets', 'target'],
        icon: 'recordCount.svg',
      },
      name: 'countRead',
      type: 'boolean',
      description: 'Is this filter counting records read or records written?',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  TextWriter: {
    footer: {
      elementType: {
        type: 'TextWriter',
        category: 'WRITER',
        roles: ['hasTargets', 'writer', 'mutator', 'stepping', 'target'],
        icon: 'text.svg',
      },
      name: 'footer',
      type: 'String',
      description: 'Footer text that can be added to the output at the end.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    header: {
      elementType: {
        type: 'TextWriter',
        category: 'WRITER',
        roles: ['hasTargets', 'writer', 'mutator', 'stepping', 'target'],
        icon: 'text.svg',
      },
      name: 'header',
      type: 'String',
      description: 'Header text that can be added to the output at the start.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    encoding: {
      elementType: {
        type: 'TextWriter',
        category: 'WRITER',
        roles: ['hasTargets', 'writer', 'mutator', 'stepping', 'target'],
        icon: 'text.svg',
      },
      name: 'encoding',
      type: 'String',
      description: 'The output character encoding to use.',
      defaultValue: 'UTF-8',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
  },
  RollingFileAppender: {
    fileName: {
      elementType: {
        type: 'RollingFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'files.svg',
      },
      name: 'fileName',
      type: 'String',
      description: 'Choose the name of the file to write.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    rolledFileName: {
      elementType: {
        type: 'RollingFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'files.svg',
      },
      name: 'rolledFileName',
      type: 'String',
      description: 'Choose the name that files will be renamed to when they are rolled.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    maxSize: {
      elementType: {
        type: 'RollingFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'files.svg',
      },
      name: 'maxSize',
      type: 'String',
      description: 'Choose the maximum size that a file can be before it is rolled, e.g. 10M, 1G.',
      defaultValue: '100M',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 5,
    },
    outputPaths: {
      elementType: {
        type: 'RollingFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'files.svg',
      },
      name: 'outputPaths',
      type: 'String',
      description:
        'One or more destination paths for output files separated with commas. Replacement variables can be used in path strings such as ${feed}.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    frequency: {
      elementType: {
        type: 'RollingFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'files.svg',
      },
      name: 'frequency',
      type: 'String',
      description: 'Choose how frequently files are rolled.',
      defaultValue: '1h',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
  },
  StroomStatsFilter: {
    kafkaConfig: {
      elementType: {
        type: 'StroomStatsFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'kafkaConfig',
      type: 'entity',
      description: 'The Kafka config to use.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['KAFKA_CONFIG'],
      displayPriority: 1,
    },
    statisticsDataSource: {
      elementType: {
        type: 'StroomStatsFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'statisticsDataSource',
      type: 'entity',
      description: 'The stroom-stats data source to record statistics against.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['StroomStatsStore'],
      displayPriority: 1,
    },
    flushOnSend: {
      elementType: {
        type: 'StroomStatsFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'flushOnSend',
      type: 'boolean',
      description:
        'Wait for acknowledgement from the Kafka borker for each message sent. This is slower but catches errors sooner',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
  },
  Reader: {},
  TestFilter: {},
  DSParser: {
    textConverter: {
      elementType: {
        type: 'DSParser',
        category: 'PARSER',
        roles: ['parser', 'hasCode', 'simple', 'hasTargets', 'stepping', 'mutator'],
        icon: 'text.svg',
      },
      name: 'textConverter',
      type: 'entity',
      description: 'The data splitter configuration that should be used to parse the input data.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['TextConverter'],
      displayPriority: 1,
    },
  },
  CombinedParser: {
    textConverter: {
      elementType: {
        type: 'CombinedParser',
        category: 'PARSER',
        roles: ['parser', 'hasCode', 'simple', 'hasTargets', 'stepping', 'mutator'],
        icon: 'text.svg',
      },
      name: 'textConverter',
      type: 'entity',
      description: 'The text converter configuration that should be used to parse the input data.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['TextConverter'],
      displayPriority: 2,
    },
    type: {
      elementType: {
        type: 'CombinedParser',
        category: 'PARSER',
        roles: ['parser', 'hasCode', 'simple', 'hasTargets', 'stepping', 'mutator'],
        icon: 'text.svg',
      },
      name: 'type',
      type: 'String',
      description: "The parser type, e.g. 'JSON', 'XML', 'Data Splitter'.",
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    fixInvalidChars: {
      elementType: {
        type: 'CombinedParser',
        category: 'PARSER',
        roles: ['parser', 'hasCode', 'simple', 'hasTargets', 'stepping', 'mutator'],
        icon: 'text.svg',
      },
      name: 'fixInvalidChars',
      type: 'boolean',
      description: 'Fix invalid XML characters from the input stream.',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
  },
  XMLWriter: {
    indentOutput: {
      elementType: {
        type: 'XMLWriter',
        category: 'WRITER',
        roles: ['hasTargets', 'writer', 'mutator', 'stepping', 'target'],
        icon: 'xml.svg',
      },
      name: 'indentOutput',
      type: 'boolean',
      description: 'Should output XML be indented and include new lines (pretty printed)?',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    encoding: {
      elementType: {
        type: 'XMLWriter',
        category: 'WRITER',
        roles: ['hasTargets', 'writer', 'mutator', 'stepping', 'target'],
        icon: 'xml.svg',
      },
      name: 'encoding',
      type: 'String',
      description: 'The output character encoding to use.',
      defaultValue: 'UTF-8',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
  },
  Source: {},
  InvalidCharFilterReader: {},
  SplitFilter: {
    splitDepth: {
      elementType: {
        type: 'SplitFilter',
        category: 'FILTER',
        roles: ['hasTargets', 'target'],
        icon: 'split.svg',
      },
      name: 'splitDepth',
      type: 'int',
      description: 'The depth of XML elements to split at.',
      defaultValue: '1',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    splitCount: {
      elementType: {
        type: 'SplitFilter',
        category: 'FILTER',
        roles: ['hasTargets', 'target'],
        icon: 'split.svg',
      },
      name: 'splitCount',
      type: 'int',
      description: 'The number of elements at the split depth to count before the XML is split.',
      defaultValue: '10000',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
  },
  HttpPostFilter: {
    receivingApiUrl: {
      elementType: {
        type: 'HttpPostFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'stream.svg',
      },
      name: 'receivingApiUrl',
      type: 'String',
      description: 'The URL of the receiving API.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  RollingKafkaAppender: {
    kafkaConfig: {
      elementType: {
        type: 'RollingKafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'kafkaConfig',
      type: 'entity',
      description: 'The Kafka config to use.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['KAFKA_CONFIG'],
      displayPriority: 1,
    },
    recordKey: {
      elementType: {
        type: 'RollingKafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'recordKey',
      type: 'String',
      description:
        'The record key to apply to records, used to select partition. Replacement variables can be used in path strings such as ${feed}.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    topic: {
      elementType: {
        type: 'RollingKafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'topic',
      type: 'String',
      description:
        'The topic to send the record to. Replacement variables can be used in path strings such as ${feed}.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    maxSize: {
      elementType: {
        type: 'RollingKafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'maxSize',
      type: 'String',
      description: 'Choose the maximum size that a file can be before it is rolled, e.g. 10M, 1G.',
      defaultValue: '100M',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 5,
    },
    flushOnSend: {
      elementType: {
        type: 'RollingKafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'flushOnSend',
      type: 'boolean',
      description:
        'Wait for acknowledgement from the Kafka broker when the appender is rolledThis is slower but catches errors in the pipeline process',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 6,
    },
    frequency: {
      elementType: {
        type: 'RollingKafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'frequency',
      type: 'String',
      description: 'Choose how frequently files are rolled.',
      defaultValue: '1h',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
  },
  TestAppender: {},
  KafkaAppender: {
    kafkaConfig: {
      elementType: {
        type: 'KafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'kafkaConfig',
      type: 'entity',
      description: 'The Kafka config to use.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['KAFKA_CONFIG'],
      displayPriority: 1,
    },
    recordKey: {
      elementType: {
        type: 'KafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'recordKey',
      type: 'String',
      description: 'This key to apply to the records, used to select partition.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    maxRecordCount: {
      elementType: {
        type: 'KafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'maxRecordCount',
      type: 'String',
      description: 'Choose the maximum number of records or events that a message will contain',
      defaultValue: '1',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    topic: {
      elementType: {
        type: 'KafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'topic',
      type: 'String',
      description: 'The topic to send the record to.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    flushOnSend: {
      elementType: {
        type: 'KafkaAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'flushOnSend',
      type: 'boolean',
      description:
        'Wait for acknowledgement from the Kafka broker for all of the messages sent.This is slower but catches errors in the pipeline process',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
  },
  XSLTFilter: {
    suppressXSLTNotFoundWarnings: {
      elementType: {
        type: 'XSLTFilter',
        category: 'FILTER',
        roles: ['hasCode', 'simple', 'hasTargets', 'stepping', 'mutator', 'target'],
        icon: 'xslt.svg',
      },
      name: 'suppressXSLTNotFoundWarnings',
      type: 'boolean',
      description: 'If XSLT cannot be found to match the name pattern suppress warnings.',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    usePool: {
      elementType: {
        type: 'XSLTFilter',
        category: 'FILTER',
        roles: ['hasCode', 'simple', 'hasTargets', 'stepping', 'mutator', 'target'],
        icon: 'xslt.svg',
      },
      name: 'usePool',
      type: 'boolean',
      description:
        'Advanced: Choose whether or not you want to use cached XSLT templates to improve performance.',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
    pipelineReference: {
      elementType: {
        type: 'XSLTFilter',
        category: 'FILTER',
        roles: ['hasCode', 'simple', 'hasTargets', 'stepping', 'mutator', 'target'],
        icon: 'xslt.svg',
      },
      name: 'pipelineReference',
      type: 'PipelineReference',
      description: 'A list of places to load reference data from if required.',
      defaultValue: '',
      pipelineReference: true,
      docRefTypes: null,
      displayPriority: 5,
    },
    xslt: {
      elementType: {
        type: 'XSLTFilter',
        category: 'FILTER',
        roles: ['hasCode', 'simple', 'hasTargets', 'stepping', 'mutator', 'target'],
        icon: 'xslt.svg',
      },
      name: 'xslt',
      type: 'entity',
      description: 'The XSLT to use.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['XSLT'],
      displayPriority: 1,
    },
    xsltNamePattern: {
      elementType: {
        type: 'XSLTFilter',
        category: 'FILTER',
        roles: ['hasCode', 'simple', 'hasTargets', 'stepping', 'mutator', 'target'],
        icon: 'xslt.svg',
      },
      name: 'xsltNamePattern',
      type: 'String',
      description: 'A name pattern to load XSLT dynamically.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
  },
  StreamAppender: {
    feed: {
      elementType: {
        type: 'StreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'feed',
      type: 'entity',
      description:
        'The feed that output stream should be written to. If not specified the feed the input stream belongs to will be used.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['Feed'],
      displayPriority: 2,
    },
    segmentOutput: {
      elementType: {
        type: 'StreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'segmentOutput',
      type: 'boolean',
      description:
        'Should the output stream be marked with indexed segments to allow fast access to individual records?',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    streamType: {
      elementType: {
        type: 'StreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'streamType',
      type: 'String',
      description:
        'The stream type that the output stream should be written as. This must be specified.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  XMLParser: {},
  RecordOutputFilter: {},
  HDFSFileAppender: {
    runAsUser: {
      elementType: {
        type: 'HDFSFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'hadoop-elephant-logo.svg',
      },
      name: 'runAsUser',
      type: 'String',
      description: 'The user to connect to HDFS as',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    fileSystemUri: {
      elementType: {
        type: 'HDFSFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'hadoop-elephant-logo.svg',
      },
      name: 'fileSystemUri',
      type: 'String',
      description:
        'URI for the Hadoop Distributed File System (HDFS) to connect to, e.g. hdfs://mynamenode.mydomain.com:8020',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    outputPaths: {
      elementType: {
        type: 'HDFSFileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'hadoop-elephant-logo.svg',
      },
      name: 'outputPaths',
      type: 'String',
      description:
        'One or more destination paths for output files separated with commas. Replacement variables can be used in path strings such as ${feed}.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  SearchResultOutputFilter: {},
  FileAppender: {
    outputPaths: {
      elementType: {
        type: 'FileAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'file.svg',
      },
      name: 'outputPaths',
      type: 'String',
      description:
        'One or more destination paths for output files separated with commas. Replacement variables can be used in path strings such as ${feed}.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  ReferenceDataFilter: {
    warnOnDuplicateKeys: {
      elementType: {
        type: 'ReferenceDataFilter',
        category: 'FILTER',
        roles: ['hasTargets', 'target'],
        icon: 'referenceData.svg',
      },
      name: 'warnOnDuplicateKeys',
      type: 'boolean',
      description: 'Warn if there are duplicate keys found in the reference data?',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    overrideExistingValues: {
      elementType: {
        type: 'ReferenceDataFilter',
        category: 'FILTER',
        roles: ['hasTargets', 'target'],
        icon: 'referenceData.svg',
      },
      name: 'overrideExistingValues',
      type: 'boolean',
      description: 'Allow duplicate keys to override existing values?',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
  },
  InvalidXMLCharFilterReader: {
    xmlVersion: {
      elementType: {
        type: 'InvalidXMLCharFilterReader',
        category: 'READER',
        roles: ['reader', 'hasTargets'],
        icon: 'stream.svg',
      },
      name: 'xmlVersion',
      type: 'String',
      description: 'XML version, e.g. 1.0 or 1.1',
      defaultValue: '1.1',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  IdEnrichmentFilter: {},
  HTTPAppender: {
    logMetaKeys: {
      elementType: {
        type: 'HTTPAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'logMetaKeys',
      type: 'String',
      description: 'Which meta data values will be logged in the send log',
      defaultValue: 'guid,feed,system,environment,remotehost,remoteaddress',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 5,
    },
    forwardChunkSize: {
      elementType: {
        type: 'HTTPAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'forwardChunkSize',
      type: 'String',
      description: 'Should data be sent in chunks and if so how big should the chunks be',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
    forwardUrl: {
      elementType: {
        type: 'HTTPAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'forwardUrl',
      type: 'String',
      description: 'The URL to send data to',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    useCompression: {
      elementType: {
        type: 'HTTPAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'useCompression',
      type: 'boolean',
      description: 'Should data be compressed when sending',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    connectionTimeout: {
      elementType: {
        type: 'HTTPAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'connectionTimeout',
      type: 'String',
      description: 'How long to wait before we abort sending data due to connection timeout',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
  },
  SchemaFilter: {
    systemId: {
      elementType: {
        type: 'SchemaFilter',
        category: 'FILTER',
        roles: ['validator', 'hasTargets', 'stepping', 'target'],
        icon: 'xsd.svg',
      },
      name: 'systemId',
      type: 'String',
      description:
        'Limits the schemas that can be used to validate data to those with a matching system id.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    schemaValidation: {
      elementType: {
        type: 'SchemaFilter',
        category: 'FILTER',
        roles: ['validator', 'hasTargets', 'stepping', 'target'],
        icon: 'xsd.svg',
      },
      name: 'schemaValidation',
      type: 'boolean',
      description: 'Should schema validation be performed?',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 5,
    },
    namespaceURI: {
      elementType: {
        type: 'SchemaFilter',
        category: 'FILTER',
        roles: ['validator', 'hasTargets', 'stepping', 'target'],
        icon: 'xsd.svg',
      },
      name: 'namespaceURI',
      type: 'String',
      description:
        'Limits the schemas that can be used to validate data to those with a matching namespace URI.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    schemaLanguage: {
      elementType: {
        type: 'SchemaFilter',
        category: 'FILTER',
        roles: ['validator', 'hasTargets', 'stepping', 'target'],
        icon: 'xsd.svg',
      },
      name: 'schemaLanguage',
      type: 'String',
      description: 'The schema language that the schema is written in.',
      defaultValue: 'http://www.w3.org/2001/XMLSchema',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
    schemaGroup: {
      elementType: {
        type: 'SchemaFilter',
        category: 'FILTER',
        roles: ['validator', 'hasTargets', 'stepping', 'target'],
        icon: 'xsd.svg',
      },
      name: 'schemaGroup',
      type: 'String',
      description:
        'Limits the schemas that can be used to validate data to those with a matching schema group name.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
  },
  IndexingFilter: {
    index: {
      elementType: {
        type: 'IndexingFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'index.svg',
      },
      name: 'index',
      type: 'entity',
      description: 'The index to send records to.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['Index'],
      displayPriority: 1,
    },
  },
  BOMRemovalFilterInput: {},
  StroomStatsAppender: {
    kafkaConfig: {
      elementType: {
        type: 'StroomStatsAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'kafkaConfig',
      type: 'entity',
      description: 'The Kafka config to use.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['KAFKA_CONFIG'],
      displayPriority: 1,
    },
    maxRecordCount: {
      elementType: {
        type: 'StroomStatsAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'maxRecordCount',
      type: 'String',
      description: 'Choose the maximum number of records or events that a message will contain',
      defaultValue: '1',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
    statisticsDataSource: {
      elementType: {
        type: 'StroomStatsAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'statisticsDataSource',
      type: 'entity',
      description: 'The stroom-stats data source to record statistics against.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['StroomStatsStore'],
      displayPriority: 1,
    },
    flushOnSend: {
      elementType: {
        type: 'StroomStatsAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'StroomStatsStore.svg',
      },
      name: 'flushOnSend',
      type: 'boolean',
      description:
        'Wait for acknowledgement from the Kafka broker for all of the messages sent.This is slower but catches errors in the pipeline process',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
  },
  RollingStreamAppender: {
    feed: {
      elementType: {
        type: 'RollingStreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'feed',
      type: 'entity',
      description:
        'The feed that output stream should be written to. If not specified the feed the input stream belongs to will be used.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['Feed'],
      displayPriority: 2,
    },
    segmentOutput: {
      elementType: {
        type: 'RollingStreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'segmentOutput',
      type: 'boolean',
      description:
        'Should the output stream be marked with indexed segments to allow fast access to individual records?',
      defaultValue: 'true',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 6,
    },
    streamType: {
      elementType: {
        type: 'RollingStreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'streamType',
      type: 'String',
      description:
        'The stream type that the output stream should be written as. This must be specified.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    maxSize: {
      elementType: {
        type: 'RollingStreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'maxSize',
      type: 'String',
      description: 'Choose the maximum size that a file can be before it is rolled, e.g. 10M, 1G.',
      defaultValue: '100M',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 5,
    },
    frequency: {
      elementType: {
        type: 'RollingStreamAppender',
        category: 'DESTINATION',
        roles: ['destination', 'stepping', 'target'],
        icon: 'stream.svg',
      },
      name: 'frequency',
      type: 'String',
      description: 'Choose how frequently files are rolled.',
      defaultValue: '1h',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 4,
    },
  },
  JSONParser: {},
  GenericKafkaProducerFilter: {
    kafkaConfig: {
      elementType: {
        type: 'GenericKafkaProducerFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'kafkaConfig',
      type: 'entity',
      description: 'The Kafka config to use.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: ['KAFKA_CONFIG'],
      displayPriority: 1,
    },
    recordKey: {
      elementType: {
        type: 'GenericKafkaProducerFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'recordKey',
      type: 'String',
      description: 'This key to apply to the records, used to select partition.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 2,
    },
    topic: {
      elementType: {
        type: 'GenericKafkaProducerFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'topic',
      type: 'String',
      description: 'The topic to send the record to.',
      defaultValue: '',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 1,
    },
    flushOnSend: {
      elementType: {
        type: 'GenericKafkaProducerFilter',
        category: 'FILTER',
        roles: ['simple', 'hasTargets', 'target'],
        icon: 'apache_kafka-icon.svg',
      },
      name: 'flushOnSend',
      type: 'boolean',
      description:
        'Wait for acknowledgement from the Kafka borker for each message sent. This is slower but catches errors sooner',
      defaultValue: 'false',
      pipelineReference: false,
      docRefTypes: null,
      displayPriority: 3,
    },
  },
};
