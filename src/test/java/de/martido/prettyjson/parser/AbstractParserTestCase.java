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

import junit.framework.Assert;

import org.antlr.runtime.tree.Tree;

import de.martido.prettyjson.AbstractTestCase;
import de.martido.prettyjson.parser.JsonRecognizer;

/**
 * Base class for parser-related test cases.
 */
public abstract class AbstractParserTestCase extends AbstractTestCase {

  protected void parse(String s) {

    try {
      JsonRecognizer parser = new JsonRecognizer();
      String json = this.replaceSingleQuotes(s);

      System.out.println("JSON: " + json);
      Tree tree = parser.parse(json);
      System.out.println("Tree: " + tree.toStringTree());
      System.out.println("");

    } catch (Exception ex) {
      ex.printStackTrace();
      Assert.fail();
    }
  }

}
