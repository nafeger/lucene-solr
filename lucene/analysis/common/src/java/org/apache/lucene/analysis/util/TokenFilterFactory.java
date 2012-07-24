package org.apache.lucene.analysis.util;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Set;

import org.apache.lucene.analysis.TokenStream;

/**
 * Abstract parent class for analysis factories that create {@link org.apache.lucene.analysis.TokenFilter}
 * instances.
 */
public abstract class TokenFilterFactory extends AbstractAnalysisFactory {

  private static final AnalysisSPILoader<TokenFilterFactory> loader =
      new AnalysisSPILoader<TokenFilterFactory>(TokenFilterFactory.class, 
          new String[] { "TokenFilterFactory", "FilterFactory" });
  
  /** looks up a tokenfilter by name */
  public static TokenFilterFactory forName(String name) {
    return loader.newInstance(name);
  }
  
  /** returns a list of all available tokenfilter names */
  public static Set<String> availableTokenFilters() {
    return loader.availableServices();
  }
  
  /** Transform the specified input TokenStream */
  public abstract TokenStream create(TokenStream input);
}