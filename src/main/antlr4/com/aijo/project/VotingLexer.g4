lexer grammar VotingLexer;

channels { ERRORCHANNEL }

// Keywords
FUNCTION : 'function' ;
MODULE : 'module' ;
TIMERESTRICTOR : 'TimeRestrictor' ;
DATE : 'date' ;
VOTERVALIDATOR : 'VoterValidator' ;
VOTESMANAGER : 'VotesManager' ;
IF : 'if' ;
ELSE : 'else' ;
INT : 'int' ;
STRING : 'string' ;
BOOL : 'bool' ;
ADDRESS : 'address';
CHECK_DEADLINE : 'checkDeadline' ;
CHECK_PERMISSIONS : 'checkPermissions' ;
ADD_VOTER : 'addVoter' ;
ADD_VOTES : 'addVotes' ;
DELETE_VOTES : 'deleteVotes' ;

// Operators
EQUAL : '==' ;
ASSIGN : '=' ;
DOT : '.' ;
PLUS : '+' ;
MINUS : '-' ;
DIVIDE : '/' ;
MULTPLY : '*' ;
MOD : '%' ;
GREATER : '>' ;
LESS : '<' ;
GREATER_OR_EQUAL : '>=' ;
LESS_OR_EQUAL : '<=' ;
OR : '||' ;
AND : '&&' ;
NOT : '!' ;


// Literals
LEFTBRACKET : '(' ;
RIGHTBRACKET : ')' ;
COMMA : ',' ;
LEFTCURLYBRACE : '{' ;
RIGHTCURLYBRACE : '}' ;
STRING_LITERAL : '"' LetterOrDigit* '"' ;
INT_LITERAL : NUMBER ;
BOOL_LITERAL : 'true' | 'false' ;

// Identifier
ID
	: Letter LetterOrDigit*
	;

// Letters
fragment
Letter
	: [a-zA-Z]+
	;

// Letter or digit
fragment
LetterOrDigit
	: [a-zA-Z0-9]+
	;

// Numbers
NUMBER
	: [0-9]+ ;

// Skip whitespaces
WS
	: [ \t\r\n] -> skip
	;

// Skip comments "//"
LINE_COMMENT
	:   '//' ~[\r\n]* -> skip
	;

ERROR_RECONGNIGION:  .    -> channel(ERRORCHANNEL);
