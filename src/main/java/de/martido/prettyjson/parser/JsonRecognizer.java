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

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

/**
 * A JSON parser based on ANTLR.
 */
public class JsonRecognizer {

  /**
   * Parses a JSON string and creates an AST.
   * 
   * @param json The JSON string to parse.
   * @return A {@code Tree} reference to the first node of the AST.
   */
  public Tree parse(String json) {

    CharStream charStream = new ANTLRStringStream(json);
    JSONLexer lexer = new JSONLexer(charStream);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    JSONParser parser = new JSONParser(tokens);
    JSONParser.document_return result = null;
    try {
      result = parser.document();
    } catch (RecognitionException ex) {
      ex.printStackTrace();
    }
    return (Tree) result.getTree();
  }

}
