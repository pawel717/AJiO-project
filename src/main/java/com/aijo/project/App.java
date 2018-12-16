package com.aijo.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {  
//    	ANTLRInputStream votingInputStream = new ANTLRInputStream(
//                "module VoterValidator\n module VotesManager \n \n function vote(int voter, string vote){if(a-=10) b=a\n"
//                + "b=20 \n VoterValidator.addVoter(\"asdad\", 10)} module TimeRestrictor");  
    	
//    	String myCode = "module VoterValidator\n module VotesManager \n \n function vote(int voter, string vote){if(a=10) b=a\n"
//                + "b=20 \n VoterValidator.addVoter(\"asdad\", 10)} module TimeRestrictor"; 
//    	;
//    	
    	if(args.length < 2) {
            System.out.println("Missing arguments. Should have <input_path> <output_path>"); 
            return;
    	}
    	
    	String inputPath = args[0];
    	String outputPath = args[1];
    
    	String input = readAllFromFile(inputPath);
    	
    	System.out.println("input:\n"+input+"\n****\n");
    	
        
        VotingLexer votingLexer = new VotingLexer(CharStreams.fromString(input));
        votingLexer.removeErrorListeners();
        votingLexer.addErrorListener(ThrowableErrorListener.INSTANCE);
        CommonTokenStream votingCommonTokenStream = null;
        try {
        	votingCommonTokenStream = new CommonTokenStream(votingLexer);
		} catch (Exception e) {
			System.out.println("heheheheeh");
			e.printStackTrace();
			return;
		}
        
        VotingParser votingParser = new VotingParser(votingCommonTokenStream);
        votingParser.setBuildParseTree(true);
        votingParser.removeErrorListeners();
        votingParser.addErrorListener(ThrowableErrorListener.INSTANCE);
        VotingParser.CompilationUnitContext compilationUnitContext = null;
        try {
        	compilationUnitContext = votingParser.compilationUnit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
          
        
        // find modules declarations and translate
        ModuleVisitor moduleVisitor = new ModuleVisitor();
        moduleVisitor.visit(compilationUnitContext);
       
        // find function definitions and translate
        VotingVisitor votingVisitor = new VotingVisitor();
        votingVisitor.visit(compilationUnitContext); 
        
        // join translated code together
        String solidityCode = "pragma solidity ^0.4.24;\r\n" + 
        		"\r\n" + 
        		"contract Voting\r\n" + 
        		"{\r\n" + 
        		"\tstruct Candidate {\r\n" + 
        		"\t\tuint id;\r\n" + 
        		"\t\tstring firstName;\r\n" + 
        		"\t\tstring lastName;\r\n" + 
        		"\t\tuint voteCount;\r\n" + 
        		"\t}\r\n" + 
        		"\r\n" + 
        		"\tmapping(uint => Candidate) public candidates;\r\n" + 
        		"\r\n" + 
        		"\tuint public candidatesCount;\r\n" + 
        		"\r\n" + 
        		"\tconstructor() public \r\n" + 
        		"\t{\r\n" + 
        		"\t\t// set the candidates list\r\n" + 
        		"\t\taddCandidate(\"Jan\", \"Kowalski\");\r\n" + 
        		"\t\taddCandidate(\"Adam\", \"Nowak\");\r\n" + 
        		"\t\taddCandidate(\"Bożena\", \"Woźniak\");\r\n" + 
        		"\t}\r\n" + 
        		"\r\n" + 
        		"\tfunction addCandidate(string firstName, string lastName) private\r\n" + 
        		"\t{\r\n" + 
        		"\t\tcandidates[candidatesCount] = Candidate(candidatesCount, firstName, lastName, 0);  \r\n" + 
        		"\r\n" + 
        		"\t\tcandidatesCount++;\r\n" + 
        		"\t}\r\n" + 
        		"\r\n" + 
        		"\t// gets total count of votes which spececific candidate have\r\n" + 
        		"\tfunction getTotalVotes(uint candidateId) public returns (uint)\r\n" + 
        		"\t{\r\n" + 
        		"\t\treturn candidates[candidateId].voteCount;\r\n" + 
        		"\t}\r\n" + 
        		"\r\n" + 
        		"\t// vote for specific candidate - means increment vote count\r\n";
        String translatedCode = moduleVisitor.getResult() + votingVisitor.getResult();
        solidityCode += translatedCode + "\n}";
        
        
        // Check solidity code correctness
        SolidityLexer solidityLexer = new SolidityLexer(CharStreams.fromString(solidityCode));
        solidityLexer.removeErrorListeners();
        solidityLexer.addErrorListener(ThrowableErrorListener.INSTANCE);
        CommonTokenStream solidityCommonTokenStream = null;
        try {
        	solidityCommonTokenStream = new CommonTokenStream(solidityLexer);
		} catch (Exception e) {
			System.out.println(solidityCode);
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
        SolidityParser solidityParser = new SolidityParser(solidityCommonTokenStream);
        solidityParser.setBuildParseTree(true);
        solidityParser.removeErrorListeners();
        solidityParser.addErrorListener(ThrowableErrorListener.INSTANCE);
        SolidityParser.SourceUnitContext solidityCompilationUnitContext = null;
        try {
        	solidityCompilationUnitContext = solidityParser.sourceUnit();
		} catch (Exception e) {
			System.out.println(solidityCode);
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}

        // print translated code
        System.out.println("result: \n" + translatedCode);
        writeAllToFile(outputPath, solidityCode);
    }
    
    private static String readAllFromFile(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }
    
    private static boolean writeAllToFile(String filePath, String content)
    {
        try
        {
        	Files.write(Paths.get(filePath), content.getBytes());
        	return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
