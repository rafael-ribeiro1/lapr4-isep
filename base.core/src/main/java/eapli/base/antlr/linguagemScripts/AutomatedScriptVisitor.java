// Generated from C:/Users/Rafael/Desktop/LEI - ISEP/2Ano 2Semestre/LAPR4/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/linguagemScripts\AutomatedScript.g4 by ANTLR 4.9.1
package eapli.base.antlr.linguagemScripts;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AutomatedScriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AutomatedScriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AutomatedScriptParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(AutomatedScriptParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code resetExecucao}
	 * labeled alternative in {@link AutomatedScriptParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetExecucao(AutomatedScriptParser.ResetExecucaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instrucao_condicao}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrucao_condicao(AutomatedScriptParser.Instrucao_condicaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code do_funcao}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDo_funcao(AutomatedScriptParser.Do_funcaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atribuir_variavel_rule}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtribuir_variavel_rule(AutomatedScriptParser.Atribuir_variavel_ruleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code missing_ponto_virgula}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condicaoIf}
	 * labeled alternative in {@link AutomatedScriptParser#instrucoes_condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicaoIf(AutomatedScriptParser.CondicaoIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code verificarCondicaoIf}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerificarCondicaoIf(AutomatedScriptParser.VerificarCondicaoIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operacaoBoolean}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacaoBoolean(AutomatedScriptParser.OperacaoBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orCondition}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrCondition(AutomatedScriptParser.OrConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prioridade}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrioridade(AutomatedScriptParser.PrioridadeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andCondition}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndCondition(AutomatedScriptParser.AndConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realizarOperacaoBoolean}
	 * labeled alternative in {@link AutomatedScriptParser#operacao_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealizarOperacaoBoolean(AutomatedScriptParser.RealizarOperacaoBooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomatedScriptParser#funcao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncao(AutomatedScriptParser.FuncaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sendEmail}
	 * labeled alternative in {@link AutomatedScriptParser#send_email}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSendEmail(AutomatedScriptParser.SendEmailContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readFile}
	 * labeled alternative in {@link AutomatedScriptParser#read_file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadFile(AutomatedScriptParser.ReadFileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code processarString}
	 * labeled alternative in {@link AutomatedScriptParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcessarString(AutomatedScriptParser.ProcessarStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code missingAspasError}
	 * labeled alternative in {@link AutomatedScriptParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkContent}
	 * labeled alternative in {@link AutomatedScriptParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckContent(AutomatedScriptParser.CheckContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link AutomatedScriptParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(AutomatedScriptParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realizarOutput}
	 * labeled alternative in {@link AutomatedScriptParser#output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealizarOutput(AutomatedScriptParser.RealizarOutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnProprioNumero}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnProprioNumero(AutomatedScriptParser.ReturnProprioNumeroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnPercentagem}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnPercentagem(AutomatedScriptParser.ReturnPercentagemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operr}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperr(AutomatedScriptParser.OperrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realizarMultiplicaoDivisao}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealizarMultiplicaoDivisao(AutomatedScriptParser.RealizarMultiplicaoDivisaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code devolver_valor_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDevolver_valor_variavel(AutomatedScriptParser.Devolver_valor_variavelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realizarSomaSub}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealizarSomaSub(AutomatedScriptParser.RealizarSomaSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realizarCalculoPrioritario}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealizarCalculoPrioritario(AutomatedScriptParser.RealizarCalculoPrioritarioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code calculo_decimal}
	 * labeled alternative in {@link AutomatedScriptParser#objeto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculo_decimal(AutomatedScriptParser.Calculo_decimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code calculo_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#objeto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculo_variavel(AutomatedScriptParser.Calculo_variavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomatedScriptParser#percentagem_num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentagem_num(AutomatedScriptParser.Percentagem_numContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomatedScriptParser#nome_ficheiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNome_ficheiro(AutomatedScriptParser.Nome_ficheiroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definirNovaVariavel}
	 * labeled alternative in {@link AutomatedScriptParser#atribuir_variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinirNovaVariavel(AutomatedScriptParser.DefinirNovaVariavelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colocar_decimal_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColocar_decimal_variavel(AutomatedScriptParser.Colocar_decimal_variavelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colocar_funcao_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColocar_funcao_variavel(AutomatedScriptParser.Colocar_funcao_variavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomatedScriptParser#nome_variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNome_variavel(AutomatedScriptParser.Nome_variavelContext ctx);
}