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
 * Tests that the parser recognizes all kinds of numbers correctly.
 */
public class ParserNumberTest extends AbstractParserTestCase {

  @Test
  public void should_parse_positive_integer() {
    this.parse("{'KEY':1}");
  }

  @Test
  public void should_parse_positive_integer_in_array() {
    this.parse("{'KEY':[1]}");
  }

  @Test
  public void should_parse_negative_integer() {
    this.parse("{'KEY':-1}");
  }

  @Test
  public void should_parse_negative_integer_in_array() {
    this.parse("{'KEY':[-1]}");
  }

  @Test
  public void should_parse_positive_real_number() {
    this.parse("{'KEY':3.1415}");
  }

  @Test
  public void should_parse_positive_real_number_in_array() {
    this.parse("{'KEY':[3.1415]}");
  }

  @Test
  public void should_parse_negative_real_number() {
    this.parse("{'KEY':-3.1415}");
  }

  @Test
  public void should_parse_negative_real_number_in_array() {
    this.parse("{'KEY':[-3.1415]}");
  }

  @Test
  public void should_parse_number_with_positive_exponent() {
    this.parse("{'KEY':1e+2}");
    this.parse("{'KEY':1E+2}");
  }

  @Test
  public void should_parse_number_with_positive_exponent_in_array() {
    this.parse("{'KEY':[1e+2]}");
    this.parse("{'KEY':[1E+2]}");
  }

  @Test
  public void should_parse_number_with_negative_exponent() {
    this.parse("{'KEY':1e-2}");
    this.parse("{'KEY':1E-2}");
  }

  @Test
  public void should_parse_number_with_negative_exponent_in_array() {
    this.parse("{'KEY':[1e-2]}");
    this.parse("{'KEY':[1E-2]}");
  }

}
