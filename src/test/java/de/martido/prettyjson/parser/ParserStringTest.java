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
 * Tests that the parser recognizes all kinds of strings correctly.
 */
public class ParserStringTest extends AbstractParserTestCase {

  @Test
  public void should_parse_empty_string() {
    this.parse("{'KEY':''}");
  }

  @Test
  public void should_parse_empty_string_in_array() {
    this.parse("{'KEY':['']}");
  }

  @Test
  public void should_parse_simple_string() {
    this.parse("{'KEY':'May the force be with you!'}");
  }

  @Test
  public void should_parse_simple_string_in_array() {
    this.parse("{'KEY':['May the force be with you!']}");
  }

  @Test
  public void should_parse_escaped_double_quotes() {
    this.parse("{'KEY':'\\\"VALUE\\\"'}");
  }

  @Test
  public void should_parse_escaped_double_quotes_in_array() {
    this.parse("{'KEY':['\\\"VALUE\\\"']}");
  }

  @Test
  public void should_parse_escaped_backslash() {
    this.parse("{'KEY':'\\\\VALUE\\\\'}");
  }

  @Test
  public void should_parse_escaped_backslash_in_array() {
    this.parse("{'KEY':['\\\\VALUE\\\\']}");
  }

  @Test
  public void should_parse_escaped_slash() {
    this.parse("{'KEY':'\\/VALUE\\/'}");
  }

  @Test
  public void should_parse_escaped_slash_in_array() {
    this.parse("{'KEY':['\\/VALUE\\/']}");
  }

  @Test
  public void should_parse_unescaped_slash() {
    this.parse("{'KEY':'/VALUE/'}");
  }

  @Test
  public void should_parse_unescaped_slash_in_array() {
    this.parse("{'KEY':['/VALUE/']}");
  }

  @Test
  public void should_parse_escaped_control_characters() {
    this.parse("{'KEY':'\\b'}");
    this.parse("{'KEY':'\\f'}");
    this.parse("{'KEY':'\\n'}");
    this.parse("{'KEY':'\\r'}");
    this.parse("{'KEY':'\\t'}");
  }

  @Test
  public void should_parse_escaped_control_characters_in_array() {
    this.parse("{'KEY':['\\b']}");
    this.parse("{'KEY':['\\f']}");
    this.parse("{'KEY':['\\n']}");
    this.parse("{'KEY':['\\r']}");
    this.parse("{'KEY':['\\t']}");
  }

  @Test
  public void should_parse_unicode_escape_characters() {
    this.parse("{'KEY':'\u0056\u0041\u004C\u0055\u0045'}");
  }

  @Test
  public void should_parse_unicode_escape_characters_in_array() {
    this.parse("{'KEY':['\u0056\u0041\u004C\u0055\u0045']}");
  }

}
