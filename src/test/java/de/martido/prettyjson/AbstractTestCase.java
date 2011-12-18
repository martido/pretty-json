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
package de.martido.prettyjson;

/**
 * Base class for all test cases.
 */
public abstract class AbstractTestCase {

  /**
   * Replaces all single quotes with double quotes in the given string.
   * 
   * @param str
   *          The string with single quotes to replace.
   * @return A string with double quotes for each single quote.
   */
  protected String replaceSingleQuotes(String str) {
    return str.replaceAll("'", "\"");
  }

  /**
   * Removes all control characters from the given string.
   * 
   * @param str
   *          The string to remove the control characters from.
   * @return A string with no control characters.
   */
  protected String removeControlCharacters(String str) {
    return str.replaceAll("\\p{Cntrl}", "");
  }

  /**
   * Removes all whitespace from the given string.
   * 
   * @param str
   *          The string to remove whitespace from.
   * @return A string with no whitespace.
   */
  protected String removeWhitespace(String str) {
    return str.replaceAll("\\p{Space}", "");
  }

}
