// -----------------------------------------
// Grammar derived from http://www.json.org/
// -----------------------------------------

grammar JSON;

options {
	language=Java;
	output=AST;
}

tokens {
	OBJECT;
	MEMBERS;
	PAIR;
	ARRAY;
	ELEMENTS;
	VALUE;
	KEY;
}

@header {
package de.martido.prettyjson.parser;
}

@lexer::header {
package de.martido.prettyjson.parser;
}

// An artificial start rule; needed becaus ANTLR cannot determine the start rule on its own.
document	:	object
		;	

object		:	BEGIN_OBJECT (members)? END_OBJECT	-> ^(OBJECT members?)
		;

members		:	pair (VALUE_SEPARATOR pair)*		-> ^(MEMBERS pair*)
		;

pair		:	STRING NAME_SEPARATOR value		-> ^(PAIR ^(KEY STRING) value)
		;

array		:	BEGIN_ARRAY (elements)? END_ARRAY	-> ^(ARRAY elements?)
		;

elements	:	value (VALUE_SEPARATOR value)*		-> ^(ELEMENTS value*)
		;

value		:	object					-> ^(VALUE object)
		|	array					-> ^(VALUE array)
		|	NUMBER					-> ^(VALUE NUMBER)
		|	STRING					-> ^(VALUE STRING)
		|	FALSE					-> ^(VALUE FALSE)
		|	NULL					-> ^(VALUE NULL)
		|	TRUE					-> ^(VALUE TRUE)
		;
		
STRING  	:	'"' ( ESC_SEQ | ~('\\'|'"') )* '"'
		;

fragment
ESC_SEQ		:	'\\' ('\"'|'\\'|'/'|'b'|'f'|'n'|'r'|'t')
		|	UNICODE_ESC
		;

fragment
UNICODE_ESC	:	'\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
		;

fragment
HEX_DIGIT 	:	(DIGIT|'a'..'f'|'A'..'F') 
		;
		
// Note: More permissive than the RFC.
NUMBER 		:	('-')? DIGIT+ ('.' DIGIT+)* EXP?
		;
    		
fragment
EXP 		:	('e'|'E') ('+'|'-')? DIGIT+
		;

fragment
DIGIT 		:	'0'..'9'
		;

// Literal names according to RFC 4627
// -----------------------------------	
FALSE		:	'false';
NULL		:	'null';
TRUE		:	'true';
		
// Structural characters accoording to RFC 4627
// --------------------------------------------

BEGIN_OBJECT	:	'{';
END_OBJECT	:	'}';
BEGIN_ARRAY	:	'[';
END_ARRAY	:	']';
NAME_SEPARATOR	:	':';
VALUE_SEPARATOR	:	',';
		
// Insignificant whitespace according to RFC 4627
// ----------------------------------------------
WS		:	(' '|'\t'|'\r'|'\n') {skip();}
		;