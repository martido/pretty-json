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

/**
 * This class is thread-safe.
 */
public class JsonFormatterSettings {

  /** The size of a single indentation level. */
  private final int indentationSize;

  /** The number of spaces before a colon. */
  private final int spacesBeforeColon;

  /** The number of spaces after a colon. */
  private final int spacesAfterColon;

  /** The number of spaces before a comma. */
  private final int spacesBeforeComma;

  /** The number of spaces after a comma. */
  private final int spacesAfterComma;

  /** Should there be a newline after an opening brace? */
  private final boolean newlineAfterOpeningBrace;

  /** Should there be a newline after a closing brace? */
  private final boolean newlineAfterClosingBrace;

  /** Should there be a newline after an opening bracket? */
  private final boolean newlineAfterOpeningBracket;

  /** Should there be a newline after a closing bracket? */
  private final boolean newlineAfterClosingBracket;

  /** Should there be a newline after an object member? */
  private final boolean newlineAfterObjectMember;

  /** Should there be a newline after an array element? */
  private final boolean newlineAfterArrayElement;

  private JsonFormatterSettings(Builder builder) {
    this.indentationSize = builder.indentationSize;
    this.spacesBeforeColon = builder.spacesBeforeColon;
    this.spacesAfterColon = builder.spacesAfterColon;
    this.spacesBeforeComma = builder.spacesBeforeComma;
    this.spacesAfterComma = builder.spacesAfterComma;
    this.newlineAfterOpeningBrace = builder.newlineAfterOpeningBrace;
    this.newlineAfterClosingBrace = builder.newlineAfterClosingBrace;
    this.newlineAfterOpeningBracket = builder.newlineAfterOpeningBracket;
    this.newlineAfterClosingBracket = builder.newlineAfterClosingBracket;
    this.newlineAfterObjectMember = builder.newlineAfterObjectMember;
    this.newlineAfterArrayElement = builder.newlineAfterArrayElement;
  }

  public int getIndentationSize() {
    return this.indentationSize;
  }

  public int getSpacesBeforeColon() {
    return this.spacesBeforeColon;
  }

  public int getSpacesAfterColon() {
    return this.spacesAfterColon;
  }

  public int getSpacesBeforeComma() {
    return this.spacesBeforeComma;
  }

  public int getSpacesAfterComma() {
    return this.spacesAfterComma;
  }

  public boolean isNewlineAfterOpeningBrace() {
    return this.newlineAfterOpeningBrace;
  }

  public boolean isNewlineAfterClosingBrace() {
    return this.newlineAfterClosingBrace;
  }

  public boolean isNewlineAfterOpeningBracket() {
    return this.newlineAfterOpeningBracket;
  }

  public boolean isNewlineAfterClosingBracket() {
    return this.newlineAfterClosingBracket;
  }

  public boolean isNewlineAfterObjectMember() {
    return this.newlineAfterObjectMember;
  }

  public boolean isNewlineAfterArrayElement() {
    return this.newlineAfterArrayElement;
  }

  /**
   * A default formatter specification.
   */
  private static volatile JsonFormatterSettings DEFAULT;

  public static JsonFormatterSettings defaultSettings() {
    if (DEFAULT == null) {
      synchronized (JsonFormatterSettings.class) {
        if (DEFAULT == null) {
          DEFAULT = new JsonFormatterSettings.Builder().build();
        }
      }
    }
    return DEFAULT;
  }

  /**
   * @author Martin Dobmeier
   */
  public static class Builder {

    /*
     * Default values of all optional elements.
     */

    private int indentationSize = 2;
    private int spacesBeforeColon = 1;
    private int spacesAfterColon = 1;
    private int spacesBeforeComma = 0;
    private int spacesAfterComma = 0;
    private boolean newlineAfterOpeningBrace = true;
    private boolean newlineAfterClosingBrace = true;
    private boolean newlineAfterOpeningBracket = true;
    private boolean newlineAfterClosingBracket = true;
    private boolean newlineAfterObjectMember = true;
    private boolean newlineAfterArrayElement = true;

    public Builder indentationSize(int value) {
      this.indentationSize = value;
      return this;
    }

    public Builder spacesBeforeColon(int value) {
      this.spacesBeforeColon = value;
      return this;
    }

    public Builder spacesAfterColon(int value) {
      this.spacesAfterColon = value;
      return this;
    }

    public Builder spacesBeforeComma(int value) {
      this.spacesBeforeComma = value;
      return this;
    }

    public Builder spacesAfterComma(int value) {
      this.spacesAfterComma = value;
      return this;
    }

    public Builder newlineAfterOpeningBrace(boolean value) {
      this.newlineAfterOpeningBrace = value;
      return this;
    }

    public Builder newlineAfterClosingBrace(boolean value) {
      this.newlineAfterClosingBrace = value;
      return this;
    }

    public Builder newlineAfterOpeningBracket(boolean value) {
      this.newlineAfterOpeningBracket = value;
      return this;
    }

    public Builder newlineAfterClosingBracket(boolean value) {
      this.newlineAfterClosingBracket = value;
      return this;
    }

    public Builder newlineAfterObjectMember(boolean value) {
      this.newlineAfterObjectMember = value;
      return this;
    }

    public Builder newlineAfterArrayElement(boolean value) {
      this.newlineAfterArrayElement = value;
      return this;
    }

    public JsonFormatterSettings build() {
      return new JsonFormatterSettings(this);
    }
  }

}
