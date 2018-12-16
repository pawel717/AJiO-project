parser grammar VotingParser;

options { tokenVocab=VotingLexer; }

compilationUnit
	: statement*
	;

statement
	: functionDefinition
	| variableDeclaration
	| moduleDeclaration
	;

moduleDeclaration
	: MODULE module
	;

functionDefinition
	: FUNCTION ID LEFTBRACKET argumentList? RIGHTBRACKET functionBody
	;

functionBody
	:  LEFTCURLYBRACE functionStatement*? RIGHTCURLYBRACE
	;

functionStatement
	: variableDeclaration
	| expression
	| moduleMethodInvocation
	| ifStatement
	;

//ifStatement
//	: IF LEFTBRACKET condition RIGHTBRACKET ifBody (ELSE ifBody)*? (ELSE IF ifBody)?
//	;
ifStatement
	: ifThenStatement
	| ifElseStatement
	;

ifThenStatement
	: IF LEFTBRACKET condition RIGHTBRACKET ifBody
	;

ifElseStatement
	: ELSE ifBody
	;



ifBody
	: LEFTCURLYBRACE functionStatement*? RIGHTCURLYBRACE
	| functionStatement
	;

condition
	: moduleMethodInvocation
	| condition relOperator condition
	| condition logicalOperator condition
	| value
	| LEFTBRACKET condition RIGHTBRACKET
	| NOT condition
	;

moduleMethodInvocation
	: moduleMethod LEFTBRACKET parameterList? RIGHTBRACKET
	;

moduleMethod
	: module DOT moduleMethodName
	;

moduleMethodName
	: CHECK_DEADLINE
	| CHECK_PERMISSIONS
	| ADD_VOTER
	| ADD_VOTES
	| DELETE_VOTES
	;

module
	: TIMERESTRICTOR
	| VOTERVALIDATOR
	| VOTESMANAGER
	;

expression
	: ID ASSIGN value
	| value aritmOperator value
	| value
	| NOT expression
	;

logicalOperator
	: OR
	| AND
	| NOT
	;

relOperator
	: EQUAL
	| ASSIGN
	| GREATER
	| LESS
	| LESS_OR_EQUAL
	| GREATER_OR_EQUAL
	| NOT EQUALS
	;

aritmOperator
	: PLUS
	| MINUS
	| DIVIDE
	| MULTPLY
	| MOD
	;

value
	: STRING_LITERAL
	| INT_LITERAL
	| BOOL_LITERAL
	| ID
	| moduleMethodInvocation
	;

variableDeclaration
	: varType ID
	| varType ID ASSIGN value
	;

parameterList
	: parameter (COMMA parameter)*
	;

parameter
	: value
	;

argumentList
	: argument (COMMA argument)*
	;

argument
	: varType ID
	;

varType
	: STRING
	| INT #Int
	| BOOL
	| ADDRESS
	;
