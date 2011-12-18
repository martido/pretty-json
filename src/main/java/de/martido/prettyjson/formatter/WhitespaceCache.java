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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A fixed cache of whitespace with length up to 8.
 * </p>
 * This class is thread-safe.
 */
class WhitespaceCache {

  /** A whitespace constant. */
  private static final String BLANK = "\u0020";

  /** The size of the {@code #CACHE}. */
  public static final int CACHE_SIZE = 8;

  /** A pre-computed cache of whitespace of size {@code #CACHE_SIZE}. */
  private static final ConcurrentMap<Integer, String> CACHE =
      new ConcurrentHashMap<Integer, String>(CACHE_SIZE);

  static {
    for (int i = 0; i < CACHE_SIZE; i++) {
      CACHE.put(i, whitespace(i));
    }
  }

  private static String whitespace(int spaces) {
    StringBuilder buffer = new StringBuilder(spaces);
    for (int i = 0; i < spaces; i++) {
      buffer.append(BLANK);
    }
    return buffer.toString();
  }

  public static String get(int spaces) {
    return spaces > CACHE_SIZE ? whitespace(spaces) : CACHE.get(spaces);
  }

}
