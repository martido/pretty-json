/*
 * Copyright 2011 Martin Dobmeier
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.martido.prettyjson.formatter;

import java.util.HashMap;
import java.util.Map;

/**
 * A cache of indentation strings.
 * </p>
 * This class is not thread-safe.
 */
class IndentationCache {

  /** A whitespace constant. */
  private static final String BLANK = "\u0020";

  /** The default size of the {@code #CACHE}. */
  private static final int DEFAULT_CACHE_SIZE = 16;

  /** A pre-computed cache of whitespace indentation strings. */
  private final Map<Integer, String> cache;

  /** A single indentation level. */
  private final String singleIndent;

  /** The number of indentation levels. */
  private int maxLevel;

  public IndentationCache(int indentationSize) {
    this(indentationSize, DEFAULT_CACHE_SIZE);
  }

  public IndentationCache(int indentationSize, int expectedMaxLevel) {
    this.cache = new HashMap<Integer, String>(expectedMaxLevel);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < indentationSize; i++) {
      sb.append(BLANK);
    }
    this.singleIndent = sb.toString();
    this.maxLevel = expectedMaxLevel;
    this.fillCache();
  }

  private void fillCache() {
    for (int i = 0; i < this.maxLevel; i++) {
      this.cache.put(i, this.indent(i));
    }
  }

  private String indent(int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append(this.singleIndent);
    }
    return sb.toString();
  }

  public String get(int level) {
    String indent;
    if (level > this.maxLevel) {
      indent = this.indent(level);
      this.cache.put(level, indent);
      this.maxLevel += 1;
    } else {
      indent = this.cache.get(level);
    }
    return indent;
  }

}
