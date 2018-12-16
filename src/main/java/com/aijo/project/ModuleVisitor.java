package com.aijo.project;

import java.io.PrintStream;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.aijo.project.VotingParserBaseVisitor;
import com.aijo.project.VotingParser.ArgumentContext;
import com.aijo.project.VotingParser.ArgumentListContext;
import com.aijo.project.VotingParser.AritmOperatorContext;
import com.aijo.project.VotingParser.CompilationUnitContext;
import com.aijo.project.VotingParser.ConditionContext;
import com.aijo.project.VotingParser.ExpressionContext;
import com.aijo.project.VotingParser.FunctionBodyContext;
import com.aijo.project.VotingParser.FunctionDefinitionContext;
import com.aijo.project.VotingParser.FunctionStatementContext;
import com.aijo.project.VotingParser.IfBodyContext;
import com.aijo.project.VotingParser.IfStatementContext;
import com.aijo.project.VotingParser.LogicalOperatorContext;
import com.aijo.project.VotingParser.ModuleContext;
import com.aijo.project.VotingParser.ModuleDeclarationContext;
import com.aijo.project.VotingParser.ModuleMethodContext;
import com.aijo.project.VotingParser.ModuleMethodInvocationContext;
import com.aijo.project.VotingParser.ModuleMethodNameContext;
import com.aijo.project.VotingParser.ParameterContext;
import com.aijo.project.VotingParser.ParameterListContext;
import com.aijo.project.VotingParser.RelOperatorContext;
import com.aijo.project.VotingParser.StatementContext;
import com.aijo.project.VotingParser.ValueContext;
import com.aijo.project.VotingParser.VarTypeContext;
import com.aijo.project.VotingParser.VariableDeclarationContext;

public class ModuleVisitor extends VotingParserBaseVisitor<String> {
	
	private String result;
	
    public ModuleVisitor()
    {
        this.result = "";
    }
    
    public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String visitCompilationUnit(CompilationUnitContext ctx) {
		visitChildren(ctx);
		return null;
	}
	
	@Override
	public String visitStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStatement(ctx);
	}

	@Override
	public String visitFunctionDefinition(FunctionDefinitionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitFunctionDefinition(ctx);
	}

	@Override
	public String visitFunctionBody(FunctionBodyContext ctx) {
		// TODO Auto-generated method stub
		return super.visitFunctionBody(ctx);
	}

	@Override
	public String visitFunctionStatement(FunctionStatementContext ctx) {
		// TODO Auto-generated method stub
		return super.visitFunctionStatement(ctx);
	}

	@Override
	public String visitIfStatement(IfStatementContext ctx) {
		// TODO Auto-generated method stub
		return super.visitIfStatement(ctx);
	}

	@Override
	public String visitIfBody(IfBodyContext ctx) {
		// TODO Auto-generated method stub
		return super.visitIfBody(ctx);
	}

	@Override
	public String visitCondition(ConditionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitCondition(ctx);
	}

	@Override
	public String visitModuleMethodInvocation(ModuleMethodInvocationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitModuleMethod(ModuleMethodContext ctx) {
		// TODO Auto-generated method stub
		return super.visitModuleMethod(ctx);
	}

	@Override
	public String visitModuleMethodName(ModuleMethodNameContext ctx) {
		// TODO Auto-generated method stub
		return super.visitModuleMethodName(ctx);
	}

	@Override
	public String visitExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitExpression(ctx);
	}

	@Override
	public String visitLogicalOperator(LogicalOperatorContext ctx) {
		// TODO Auto-generated method stub
		return super.visitLogicalOperator(ctx);
	}

	@Override
	public String visitRelOperator(RelOperatorContext ctx) {
		// TODO Auto-generated method stub
		return super.visitRelOperator(ctx);
	}

	@Override
	public String visitAritmOperator(AritmOperatorContext ctx) {
		// TODO Auto-generated method stub
		return super.visitAritmOperator(ctx);
	}

	@Override
	public String visitValue(ValueContext ctx) {
		// TODO Auto-generated method stub
		return super.visitValue(ctx);
	}

	@Override
	public String visitVariableDeclaration(VariableDeclarationContext ctx) {
		// TODO Auto-generated method stub
		return super.visitVariableDeclaration(ctx);
	}

	@Override
	public String visitParameterList(ParameterListContext ctx) {
		// TODO Auto-generated method stub
		return super.visitParameterList(ctx);
	}

	@Override
	public String visitParameter(ParameterContext ctx) {
		// TODO Auto-generated method stub
		return super.visitParameter(ctx);
	}

	@Override
	public String visitArgumentList(ArgumentListContext ctx) {
		// TODO Auto-generated method stub
		return super.visitArgumentList(ctx);
	}

	@Override
	public String visitArgument(ArgumentContext ctx) {
		// TODO Auto-generated method stub
		return super.visitArgument(ctx);
	}

	@Override
	public String visitVarType(VarTypeContext ctx) {
		// TODO Auto-generated method stub
		return super.visitVarType(ctx);
	}

	@Override
    public String visitModuleDeclaration(ModuleDeclarationContext ctx) {
    	
    	visitChildren(ctx);
    	
    	result += "\n\n";
    	
    	return result;
    }
    
    @Override
    public String visitModule(ModuleContext ctx) {
    	String moduleName = ctx.getText();
    	
    	switch(moduleName) {
	    	case "VoterValidator":
	    		result += 
	    		"\tstruct Voter {\r\n" + 
	    		"\t\tuint votesLeft;\r\n" + 
	    		"\t}\r\n" + 
	    		"\t\r\n" + 
	    		"\tmapping(address => Voter) public voters;\r\n" + 
	    		"\r\n" + 
	    		"\tfunction checkPermissions(address voter, uint count) private returns (bool)\r\n" + 
	    		"\t{\r\n" + 
	    		"\t\tif(voters[voter].votesLeft >= count)\r\n" + 
	    		"\t\t\treturn true;\r\n" + 
	    		"\t\r\n" + 
	    		"\t\treturn false;\r\n" + 
	    		"\t}\r\n" + 
	    		"\r\n" + 
	    		"\tfunction addVoter(address voter, uint votesDispensed) private \r\n" + 
	    		"\t{\r\n" + 
	    		"\t\tvoters[voter] = Voter(votesDispensed);\r\n" + 
	    		"\t}";
	    		break;
	    	case "VotesManager":
	    		result += 
	    		"\tfunction addVotes(uint candidateId, uint count) private\r\n" + 
	    		"\t{\r\n" + 
	    		"\t\tcandidates[candidateId].voteCount += count;\r\n" + 
	    		"\t}\r\n" + 
	    		"\r\n" + 
	    		"\tfunction deleteVotes(uint candidateId, uint count) private\r\n" + 
	    		"\t{\r\n" + 
	    		"\t\tcandidates[candidateId].voteCount -= count;\r\n" + 
	    		"\t}";
	    		break;
	    	case "TimeRestrictor":
	    		result +=
	    		"\tuint startDate;\r\n" + 
	    		"\tuint endDate;\r\n" + 
	    		"\t\r\n" + 
	    		"\tfunction checkDedline() private returns (bool) \r\n" + 
	    		"\t{\r\n" + 
	    		"\t\tif(now > startDate && now < endDate)\r\n" + 
	    		"\t\t\treturn true;\r\n" + 
	    		"\r\n" + 
	    		"\t\treturn false;\r\n" + 
	    		"\t}";
	    		break;
    	}
    	
    	return null;
    }
    
    @Override
    public String visitTerminal(TerminalNode node) {
    	// TODO Auto-generated method stub
    	return super.visitTerminal(node);
    }
}
