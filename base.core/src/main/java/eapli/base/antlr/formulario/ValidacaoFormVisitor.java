// Generated from C:/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/formulario\ValidacaoForm.g4 by ANTLR 4.9.1
package eapli.base.antlr.formulario;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ValidacaoFormParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ValidacaoFormVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code endResult}
	 * labeled alternative in {@link ValidacaoFormParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndResult(ValidacaoFormParser.EndResultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inst}
	 * labeled alternative in {@link ValidacaoFormParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst(ValidacaoFormParser.InstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instRec}
	 * labeled alternative in {@link ValidacaoFormParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstRec(ValidacaoFormParser.InstRecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opVerifyInstrucaoForm}
	 * labeled alternative in {@link ValidacaoFormParser#instrucao_form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpVerifyInstrucaoForm(ValidacaoFormParser.OpVerifyInstrucaoFormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opAndVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpAndVerif(ValidacaoFormParser.OpAndVerifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opBoolVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpBoolVerif(ValidacaoFormParser.OpBoolVerifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opOrVerif}
	 * labeled alternative in {@link ValidacaoFormParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpOrVerif(ValidacaoFormParser.OpOrVerifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opValid}
	 * labeled alternative in {@link ValidacaoFormParser#validacoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpValid(ValidacaoFormParser.OpValidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opValidRec}
	 * labeled alternative in {@link ValidacaoFormParser#validacoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpValidRec(ValidacaoFormParser.OpValidRecContext ctx);
	/**
	 * Visit a parse tree produced by {@link ValidacaoFormParser#validacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValidacao(ValidacaoFormParser.ValidacaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instForm}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstForm(ValidacaoFormParser.InstFormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opAndLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpAndLogic(ValidacaoFormParser.OpAndLogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcBool}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncBool(ValidacaoFormParser.FuncBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opBoolLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpBoolLogic(ValidacaoFormParser.OpBoolLogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opOrLogic}
	 * labeled alternative in {@link ValidacaoFormParser#operacoes_logicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpOrLogic(ValidacaoFormParser.OpOrLogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code empty}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty(ValidacaoFormParser.EmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notEmpty}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEmpty(ValidacaoFormParser.NotEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code match}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(ValidacaoFormParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notMatch}
	 * labeled alternative in {@link ValidacaoFormParser#funcao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotMatch(ValidacaoFormParser.NotMatchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opStr}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpStr(ValidacaoFormParser.OpStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opBoolAtr}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpBoolAtr(ValidacaoFormParser.OpBoolAtrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opBool}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpBool(ValidacaoFormParser.OpBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opInt}
	 * labeled alternative in {@link ValidacaoFormParser#operacao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpInt(ValidacaoFormParser.OpIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opLen}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpLen(ValidacaoFormParser.OpLenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operr}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperr(ValidacaoFormParser.OperrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opNum}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpNum(ValidacaoFormParser.OpNumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opSS}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpSS(ValidacaoFormParser.OpSSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opMD}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpMD(ValidacaoFormParser.OpMDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opParen}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpParen(ValidacaoFormParser.OpParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opAtr}
	 * labeled alternative in {@link ValidacaoFormParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpAtr(ValidacaoFormParser.OpAtrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attribute}
	 * labeled alternative in {@link ValidacaoFormParser#atributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(ValidacaoFormParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ValidacaoFormParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegex(ValidacaoFormParser.RegexContext ctx);
}