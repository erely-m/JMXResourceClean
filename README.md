# JMXResourceClean
  应用使用JMX协议时需要创建JMXConnector,在使用完成之后没有对资源进行释放，导致用户使用的线程数不断增加,最终导致内存不足，应用宕掉。  It needs to create JMXConnector when using the JMX protocol. it is not to release resource of JMXConnector.Increase the number of threads used by users，Abnormal shutdown of final application。
