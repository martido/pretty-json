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

import org.antlr.runtime.tree.Tree;

import de.martido.prettyjson.parser.JsonRecognizer;

/**
 * A JSON formatter for RFC 4627-compliant JSON strings.
 * </p>
 * This class is thread-safe.
 */
public class JsonFormatter {

  /** The settings with which to format the JSON string. */
  private final JsonFormatterSettings settings;

  /** The JSON parser. */
  private final JsonRecognizer parser;

  /** A walker for the JSON AST. */
  private final AstWalker walker;

  public JsonFormatter() {
    this(JsonFormatterSettings.defaultSettings());
  }

  public JsonFormatter(JsonFormatterSettings settings) {
    this.settings = settings;
    this.parser = new JsonRecognizer();
    this.walker = new AstWalker();
  }

  /**
   * Formats the given JSON string according to {@link #settings}.
   * 
   * @param json The JSON string to format.
   * @return A formatted JSON string.
   */
  public String format(String json) {

    Tree tree = this.parser.parse(json);
    System.out.println(tree.toStringTree());

    AstWalker.Context context = new AstWalker.Context(this.settings, json.length());
    this.walker.walk(tree, context);

    String formatted = context.print();
    System.out.println(formatted);
    return formatted;
  }

}
