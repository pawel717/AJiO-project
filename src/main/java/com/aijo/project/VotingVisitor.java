package com.aijo.project;

import java.io.PrintStream;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.aijo.project.VotingParser.ExpressionContext;
import com.aijo.project.VotingParser.FunctionBodyContext;
import com.aijo.project.VotingParser.StatementContext;
import com.aijo.project.VotingParser.ValueContext;
import com.aijo.project.VotingParser.ParameterListContext;
import com.aijo.project.VotingParser.VariableDeclarationContext;
import com.aijo.project.VotingParser.ArgumentContext;
import com.aijo.project.VotingParser.ArgumentListContext;
import com.aijo.project.VotingParser.AritmOperatorContext;
import com.aijo.project.VotingParser.CompilationUnitContext;
import com.aijo.project.VotingParser.ConditionContext;
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
import com.aijo.project.VotingParser.RelOperatorContext;
import com.aijo.project.VotingParser.VarTypeContext;


public class VotingVisitor extends VotingParserBaseVisitor<String> {
	
	private String result;
	
    public VotingVisitor()
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
		visitChildren(ctx);
		return null;
	}

	@Override
	public String visitModuleDeclaration(ModuleDeclarationContext ctx) {
		return null;
	}

	@Override
	public String visitFunctionBody(FunctionBodyContext ctx) {
		// TODO Auto-generated method stub
		return super.visitFunctionBody(ctx);
	}

	@Override
	public String visitIfStatement(IfStatementContext ctx) {
		visitChildren(ctx);
		return null;
	}

	@Override
	public String visitIfBody(IfBodyContext ctx) {
		
		visitChildren(ctx);
		if(!result.endsWith("}"))
			result = result.substring(0, result.length()-1);
		return null;
	}

	@Override
	public String visitCondition(ConditionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitCondition(ctx);
	}

	@Override
	public String visitModuleMethodInvocation(ModuleMethodInvocationContext ctx) {
		// TODO Auto-generated method stub
		return super.visitModuleMethodInvocation(ctx);
	}

	@Override
	public String visitModule(ModuleContext ctx) {
		// TODO Auto-generated method stub
		return super.visitModule(ctx);
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
    public String visitFunctionDefinition(FunctionDefinitionContext ctx) {
		result += "\t";
    	visitChildren(ctx);
    	return null;
    }
    
    @Override
    public String visitArgumentList(ArgumentListContext ctx) {
    	visitChildren(ctx);
    	return null;
    }
    
    @Override
    public String visitArgument(ArgumentContext ctx) {   	
    	visitChildren(ctx);
    	return null;
    	
    }
    
    @Override
    public String visitVarType(VarTypeContext ctx) {
    	visitChildren(ctx);
    	return null;
    }
    
    @Override
    public String visitFunctionStatement(FunctionStatementContext ctx) {
    	result += "\n\t\t";
    	String pretty = "";
    	String aa;
    	
    	if(ctx.parent.getRuleIndex() == VotingParser.RULE_ifBody)
    		result += "\t";
    
    	visitChildren(ctx);
    	if(!result.endsWith("else") && !result.endsWith("}"))
    		result += ";";
    	
    	return null;
    }
    
    @Override
    public String visitTerminal(TerminalNode node) {
    	String terminal = node.getText();
    	
        switch (node.getSymbol().getType())
        {
            case VotingLexer.COMMA:
            	terminal += " ";
                break;
                
//            case VotingLexer.ELSE:
//            	terminal = " "+terminal;
//                break;
            case VotingLexer.INT:
            	terminal = "uint ";
            	break;
            case VotingLexer.STRING:
            	terminal += " ";
            	break;
            case VotingLexer.BOOL:
            	terminal += " ";
            	break;
            case VotingLexer.ADDRESS:
            	terminal += " ";
            	break;
            case VotingLexer.FUNCTION:
            	terminal += " ";
                break;
            case VotingLexer.RIGHTCURLYBRACE:
            	terminal = "\n" + terminal;
        }
    	
        result += terminal;
        
    	return null;
    }
    
	@Override
	public String visitModuleMethodName(ModuleMethodNameContext ctx) {
		result += ctx.getText();
		
		return null;
	}
    
	@Override
	public String visitModuleMethod(ModuleMethodContext ctx) {	
		visitModuleMethodName(ctx.moduleMethodName());
		return null;
	}
        
}
