// Generated from C:/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/formulario\ValidacaoForm.g4 by ANTLR 4.9.1
package eapli.base.antlr.formulario;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ValidacaoFormParser}.
 */
public interface ValidacaoFormListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code endResult}
	 * labeled alternative in {@link ValidacaoFormParser#start}.
	 * @param ctx the parse tree
	 */
	void enterEndResult(ValidacaoFormParser.EndResultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code endResult}
	 * labeled alternative in {@link ValidacaoFormParser#start}.
	 * @param ctx the parse tree
	 */
	void exitEndResult(ValidacaoFormParser.EndResultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inst}
	 * labeled alternative in {@link ValidacaoFormParser#form}.
	 * @param ctx the parse tree
	 */
	void enterInst(ValidacaoFormParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inst}
	 * labeled alternative in {@link ValidacaoFormParser#form}.
	 * @param ctx the parse tree
	 */
	void exitInst(ValidacaoFormParser.InstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instRec}
	 * labeled alternative in {@link ValidacaoFormParser#form}.
	 * @param ctx the parse tree
	 */
	void enterInstRec(ValidacaoFormParser.InstRecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instRec}
	 * labeled alternative in {@link ValidacaoFormParser#form}.
	 * @param ctx the parse tree
	 */
	void exitInstRec(ValidacaoFormParser.InstRecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opVerifyInstrucaoForm}
	 * labeled alternative in {@link ValidacaoFormParser#instrucao_form}.
	 * @param ctx the parse tree
	 */
	void enterOpVerifyInstrucaoForm(ValidacaoFormParser.OpVerifyInstrucaoFormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opVerifyInstrucaoForm}
	 * labeled alternative in {@link ValidacaoFormParser#instrucao_form}.
	 * @param ctx the parse tree
	 */
	void exitOpVerifyInstrucaoForm(ValidacaoFormParser.OpVerifyInstrucaoFormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opAndVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterOpAndVerif(ValidacaoFormParser.OpAndVerifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opAndVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitOpAndVerif(ValidacaoFormParser.OpAndVerifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opBoolVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterOpBoolVerif(ValidacaoFormParser.OpBoolVerifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opBoolVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitOpBoolVerif(ValidacaoFormParser.OpBoolVerifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opOrVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterOpOrVerif(ValidacaoFormParser.OpOrVerifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opOrVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitOpOrVerif(ValidacaoFormParser.OpOrVerifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opValid}
	 * labeled alternative in {@link ValidacaoFormParser#validacoes}.
	 * @param ctx the parse tree
	 */
	void enterOpValid(ValidacaoFormParser.OpValidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opValid}
	 * labeled alternative in {@link ValidacaoFormParser#validacoes}.
	 * @param ctx the parse tree
	 */
	void exitOpValid(ValidacaoFormParser.OpValidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opValidRec}
	 * labeled alternative in {@link ValidacaoFormParser#validacoes}.
	 * @param ctx the parse tree
	 */
	void enterOpValidRec(ValidacaoFormParser.OpValidRecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opValidRec}
	 * labeled alternative in {@link ValidacaoFormParser#validacoes}.
	 * @param ctx the parse tree
	 */
	void exitOpValidRec(ValidacaoFormParser.OpValidRecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ValidacaoFormParser#validacao}.
	 * @param ctx the parse tree
	 */
	void enterValidacao(ValidacaoFormParser.ValidacaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ValidacaoFormParser#validacao}.
	 * @param ctx the parse tree
	 */
	void exitValidacao(ValidacaoFormParser.ValidacaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instForm}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void enterInstForm(ValidacaoFormParser.InstFormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instForm}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void exitInstForm(ValidacaoFormParser.InstFormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opAndLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void enterOpAndLogic(ValidacaoFormParser.OpAndLogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opAndLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void exitOpAndLogic(ValidacaoFormParser.OpAndLogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcBool}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void enterFuncBool(ValidacaoFormParser.FuncBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcBool}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void exitFuncBool(ValidacaoFormParser.FuncBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opBoolLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void enterOpBoolLogic(ValidacaoFormParser.OpBoolLogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opBoolLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void exitOpBoolLogic(ValidacaoFormParser.OpBoolLogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opOrLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void enterOpOrLogic(ValidacaoFormParser.OpOrLogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opOrLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 */
	void exitOpOrLogic(ValidacaoFormParser.OpOrLogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code empty}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void enterEmpty(ValidacaoFormParser.EmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code empty}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void exitEmpty(ValidacaoFormParser.EmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notEmpty}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void enterNotEmpty(ValidacaoFormParser.NotEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notEmpty}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void exitNotEmpty(ValidacaoFormParser.NotEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code match}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void enterMatch(ValidacaoFormParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code match}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void exitMatch(ValidacaoFormParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notMatch}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void enterNotMatch(ValidacaoFormParser.NotMatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notMatch}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 */
	void exitNotMatch(ValidacaoFormParser.NotMatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opStr}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void enterOpStr(ValidacaoFormParser.OpStrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opStr}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void exitOpStr(ValidacaoFormParser.OpStrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opBoolAtr}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void enterOpBoolAtr(ValidacaoFormParser.OpBoolAtrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opBoolAtr}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void exitOpBoolAtr(ValidacaoFormParser.OpBoolAtrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opBool}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void enterOpBool(ValidacaoFormParser.OpBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opBool}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void exitOpBool(ValidacaoFormParser.OpBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opInt}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void enterOpInt(ValidacaoFormParser.OpIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opInt}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void exitOpInt(ValidacaoFormParser.OpIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opLen}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpLen(ValidacaoFormParser.OpLenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opLen}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpLen(ValidacaoFormParser.OpLenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operr}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOperr(ValidacaoFormParser.OperrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operr}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOperr(ValidacaoFormParser.OperrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opNum}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpNum(ValidacaoFormParser.OpNumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opNum}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpNum(ValidacaoFormParser.OpNumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opSS}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpSS(ValidacaoFormParser.OpSSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opSS}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpSS(ValidacaoFormParser.OpSSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opMD}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpMD(ValidacaoFormParser.OpMDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opMD}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpMD(ValidacaoFormParser.OpMDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opParen}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpParen(ValidacaoFormParser.OpParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opParen}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpParen(ValidacaoFormParser.OpParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opAtr}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpAtr(ValidacaoFormParser.OpAtrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opAtr}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpAtr(ValidacaoFormParser.OpAtrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attribute}
	 * labeled alternative in {@link ValidacaoFormParser#atributo}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(ValidacaoFormParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attribute}
	 * labeled alternative in {@link ValidacaoFormParser#atributo}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(ValidacaoFormParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ValidacaoFormParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterRegex(ValidacaoFormParser.RegexContext ctx);
	/**
	 * Exit a parse tree produced by {@link ValidacaoFormParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitRegex(ValidacaoFormParser.RegexContext ctx);
}