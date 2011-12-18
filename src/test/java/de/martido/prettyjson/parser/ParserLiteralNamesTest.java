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
package de.martido.prettyjson.parser;

import org.junit.Test;

/**
 * Tests that the parser recognizes all allowed literal names correctly.
 */
public class ParserLiteralNamesTest extends AbstractParserTestCase {

  @Test
  public void should_parse_true() {
    this.parse("{'KEY':true}");
  }

  @Test
  public void should_parse_true_in_array() {
    this.parse("{'KEY':[true]}");
  }

  @Test
  public void should_parse_false() {
    this.parse("{'KEY':false}");
  }

  @Test
  public void should_parse_false_in_array() {
    this.parse("{'KEY':[false]}");
  }

  @Test
  public void should_parse_null() {
    this.parse("{'KEY':null}");
  }

  @Test
  public void should_parse_null_in_array() {
    this.parse("{'KEY':[null]}");
  }

}
