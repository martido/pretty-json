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

import de.martido.prettyjson.parser.JSONParser;

/**
 * A walker for a JSON AST.
 * </p>
 * This class is thread-safe.
 */
class AstWalker {

  /** A platform-independent newline constant. */
  private static final String NEWLINE = System.getProperty("line.separator");

  /**
   * Walks the ANTRL AST.
   * 
   * @param tree The {@code Tree} to walk.
   * @param ctx A {@link Context}.
   */
  public void walk(Tree tree, Context ctx) {

    if (tree == null) {
      return;
    }

    switch (tree.getType()) {

      case JSONParser.OBJECT:
        ctx.append("{");
        if (ctx.settings.isNewlineAfterOpeningBrace()) {
          ctx.append(NEWLINE);
        }

        ctx.increaseIndentation();
        this.walk(tree.getChild(0), ctx);
        ctx.decreaseIndentation();

        if (ctx.settings.isNewlineAfterClosingBrace()) {
          ctx.append(NEWLINE);
        }
        ctx.indent();
        ctx.append("}");
        break;

      case JSONParser.MEMBERS:
        for (int i = 0; i < tree.getChildCount(); i++) {
          ctx.indent();
          this.walk(tree.getChild(i), ctx);
          if (i < tree.getChildCount() - 1) {
            ctx.space(ctx.settings.getSpacesBeforeComma());
            ctx.append(",");
            ctx.space(ctx.settings.getSpacesAfterComma());
            if (ctx.settings.isNewlineAfterObjectMember()) {
              ctx.append(NEWLINE);
            }
          }
        }
        break;

      case JSONParser.PAIR:
        this.walk(tree.getChild(0), ctx);
        ctx.space(ctx.settings.getSpacesBeforeColon());
        ctx.append(":");
        ctx.space(ctx.settings.getSpacesAfterColon());
        this.walk(tree.getChild(1), ctx);
        break;

      case JSONParser.ARRAY:
        ctx.append("[");
        if (ctx.settings.isNewlineAfterOpeningBracket()) {
          ctx.append(NEWLINE);
        }

        ctx.increaseIndentation();
        this.walk(tree.getChild(0), ctx);
        ctx.decreaseIndentation();

        if (ctx.settings.isNewlineAfterClosingBracket()) {
          ctx.append(NEWLINE);
        }
        ctx.indent();
        ctx.append("]");
        break;

      case JSONParser.ELEMENTS:
        for (int i = 0; i < tree.getChildCount(); i++) {
          ctx.indent();
          this.walk(tree.getChild(i), ctx);
          if (i < tree.getChildCount() - 1) {
            ctx.space(ctx.settings.getSpacesBeforeComma());
            ctx.append(",");
            ctx.space(ctx.settings.getSpacesAfterComma());
            if (ctx.settings.isNewlineAfterObjectMember()) {
              ctx.append(NEWLINE);
            }
          }
        }
        break;

      case JSONParser.KEY:
        ctx.append(tree.getChild(0).getText());
        break;

      case JSONParser.VALUE:
        Tree child = tree.getChild(0);
        if (child.getType() == JSONParser.OBJECT) {
          this.walk(child, ctx);
        } else if (child.getType() == JSONParser.ARRAY) {
          this.walk(child, ctx);
        } else {
          ctx.append(child.getText());
        }
        break;

      default:
        // Do nothing.
    }
  }

  /**
   * This class is not thread-safe.
   */
  static class Context {

    /** The settings with which to format the JSON string. */
    private final JsonFormatterSettings settings;

    /** The current indentation level. */
    private int indentationLevel = 0;

    /** A cache of pre-computed indentation levels. */
    private final IndentationCache indentationCache;

    /** A buffer for a formatted JSON string. */
    private final StringBuilder buffer;

    public Context(JsonFormatterSettings settings, int initialBufferSize) {
      this.settings = settings;
      this.indentationCache = new IndentationCache(settings.getIndentationSize());
      this.buffer = new StringBuilder(initialBufferSize);
    }

    /**
     * Appends the given {@code Object} to the {@code #buffer}.
     * 
     * @param o The {@code Object} to append.
     */
    public void append(Object o) {
      this.buffer.append(o);
    }

    /**
     * Increases the current indentation level.
     */
    public void increaseIndentation() {
      this.indentationLevel++;
    }

    /**
     * Decreases the current indentation level.
     */
    public void decreaseIndentation() {
      this.indentationLevel--;
    }

    /**
     * Append the given number of spaces.
     * 
     * @param spaces The number of spaces to append.
     */
    public void space(int spaces) {
      this.buffer.append(WhitespaceCache.get(spaces));
    }

    /**
     * Indent according to the current level of indentation.
     */
    public void indent() {
      this.buffer.append(this.indentationCache.get(this.indentationLevel));
    }

    /**
     * @return The resulting string.
     */
    public String print() {
      return this.buffer.toString();
    }

  }

}
