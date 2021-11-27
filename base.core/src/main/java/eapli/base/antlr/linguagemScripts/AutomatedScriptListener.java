// Generated from C:/Users/Rafael/Desktop/LEI - ISEP/2Ano 2Semestre/LAPR4/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/linguagemScripts\AutomatedScript.g4 by ANTLR 4.9.1
package eapli.base.antlr.linguagemScripts;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AutomatedScriptParser}.
 */
public interface AutomatedScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AutomatedScriptParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(AutomatedScriptParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatedScriptParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(AutomatedScriptParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code resetExecucao}
	 * labeled alternative in {@link AutomatedScriptParser#script}.
	 * @param ctx the parse tree
	 */
	void enterResetExecucao(AutomatedScriptParser.ResetExecucaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code resetExecucao}
	 * labeled alternative in {@link AutomatedScriptParser#script}.
	 * @param ctx the parse tree
	 */
	void exitResetExecucao(AutomatedScriptParser.ResetExecucaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instrucao_condicao}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void enterInstrucao_condicao(AutomatedScriptParser.Instrucao_condicaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instrucao_condicao}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void exitInstrucao_condicao(AutomatedScriptParser.Instrucao_condicaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code do_funcao}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void enterDo_funcao(AutomatedScriptParser.Do_funcaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code do_funcao}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void exitDo_funcao(AutomatedScriptParser.Do_funcaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atribuir_variavel_rule}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void enterAtribuir_variavel_rule(AutomatedScriptParser.Atribuir_variavel_ruleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atribuir_variavel_rule}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void exitAtribuir_variavel_rule(AutomatedScriptParser.Atribuir_variavel_ruleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code missing_ponto_virgula}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void enterMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code missing_ponto_virgula}
	 * labeled alternative in {@link AutomatedScriptParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void exitMissing_ponto_virgula(AutomatedScriptParser.Missing_ponto_virgulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condicaoIf}
	 * labeled alternative in {@link AutomatedScriptParser#instrucoes_condicao}.
	 * @param ctx the parse tree
	 */
	void enterCondicaoIf(AutomatedScriptParser.CondicaoIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condicaoIf}
	 * labeled alternative in {@link AutomatedScriptParser#instrucoes_condicao}.
	 * @param ctx the parse tree
	 */
	void exitCondicaoIf(AutomatedScriptParser.CondicaoIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code verificarCondicaoIf}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacoes}.
	 * @param ctx the parse tree
	 */
	void enterVerificarCondicaoIf(AutomatedScriptParser.VerificarCondicaoIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code verificarCondicaoIf}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacoes}.
	 * @param ctx the parse tree
	 */
	void exitVerificarCondicaoIf(AutomatedScriptParser.VerificarCondicaoIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operacaoBoolean}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterOperacaoBoolean(AutomatedScriptParser.OperacaoBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operacaoBoolean}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitOperacaoBoolean(AutomatedScriptParser.OperacaoBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orCondition}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterOrCondition(AutomatedScriptParser.OrConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orCondition}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitOrCondition(AutomatedScriptParser.OrConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prioridade}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterPrioridade(AutomatedScriptParser.PrioridadeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prioridade}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitPrioridade(AutomatedScriptParser.PrioridadeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andCondition}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void enterAndCondition(AutomatedScriptParser.AndConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andCondition}
	 * labeled alternative in {@link AutomatedScriptParser#cond_verificacao}.
	 * @param ctx the parse tree
	 */
	void exitAndCondition(AutomatedScriptParser.AndConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realizarOperacaoBoolean}
	 * labeled alternative in {@link AutomatedScriptParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void enterRealizarOperacaoBoolean(AutomatedScriptParser.RealizarOperacaoBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realizarOperacaoBoolean}
	 * labeled alternative in {@link AutomatedScriptParser#operacao_bool}.
	 * @param ctx the parse tree
	 */
	void exitRealizarOperacaoBoolean(AutomatedScriptParser.RealizarOperacaoBooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatedScriptParser#funcao}.
	 * @param ctx the parse tree
	 */
	void enterFuncao(AutomatedScriptParser.FuncaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatedScriptParser#funcao}.
	 * @param ctx the parse tree
	 */
	void exitFuncao(AutomatedScriptParser.FuncaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sendEmail}
	 * labeled alternative in {@link AutomatedScriptParser#send_email}.
	 * @param ctx the parse tree
	 */
	void enterSendEmail(AutomatedScriptParser.SendEmailContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sendEmail}
	 * labeled alternative in {@link AutomatedScriptParser#send_email}.
	 * @param ctx the parse tree
	 */
	void exitSendEmail(AutomatedScriptParser.SendEmailContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readFile}
	 * labeled alternative in {@link AutomatedScriptParser#read_file}.
	 * @param ctx the parse tree
	 */
	void enterReadFile(AutomatedScriptParser.ReadFileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readFile}
	 * labeled alternative in {@link AutomatedScriptParser#read_file}.
	 * @param ctx the parse tree
	 */
	void exitReadFile(AutomatedScriptParser.ReadFileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code processarString}
	 * labeled alternative in {@link AutomatedScriptParser#string}.
	 * @param ctx the parse tree
	 */
	void enterProcessarString(AutomatedScriptParser.ProcessarStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code processarString}
	 * labeled alternative in {@link AutomatedScriptParser#string}.
	 * @param ctx the parse tree
	 */
	void exitProcessarString(AutomatedScriptParser.ProcessarStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code missingAspasError}
	 * labeled alternative in {@link AutomatedScriptParser#string}.
	 * @param ctx the parse tree
	 */
	void enterMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code missingAspasError}
	 * labeled alternative in {@link AutomatedScriptParser#string}.
	 * @param ctx the parse tree
	 */
	void exitMissingAspasError(AutomatedScriptParser.MissingAspasErrorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkContent}
	 * labeled alternative in {@link AutomatedScriptParser#content}.
	 * @param ctx the parse tree
	 */
	void enterCheckContent(AutomatedScriptParser.CheckContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkContent}
	 * labeled alternative in {@link AutomatedScriptParser#content}.
	 * @param ctx the parse tree
	 */
	void exitCheckContent(AutomatedScriptParser.CheckContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skip}
	 * labeled alternative in {@link AutomatedScriptParser#content}.
	 * @param ctx the parse tree
	 */
	void enterSkip(AutomatedScriptParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link AutomatedScriptParser#content}.
	 * @param ctx the parse tree
	 */
	void exitSkip(AutomatedScriptParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realizarOutput}
	 * labeled alternative in {@link AutomatedScriptParser#output}.
	 * @param ctx the parse tree
	 */
	void enterRealizarOutput(AutomatedScriptParser.RealizarOutputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realizarOutput}
	 * labeled alternative in {@link AutomatedScriptParser#output}.
	 * @param ctx the parse tree
	 */
	void exitRealizarOutput(AutomatedScriptParser.RealizarOutputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnProprioNumero}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterReturnProprioNumero(AutomatedScriptParser.ReturnProprioNumeroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnProprioNumero}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitReturnProprioNumero(AutomatedScriptParser.ReturnProprioNumeroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnPercentagem}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterReturnPercentagem(AutomatedScriptParser.ReturnPercentagemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnPercentagem}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitReturnPercentagem(AutomatedScriptParser.ReturnPercentagemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operr}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterOperr(AutomatedScriptParser.OperrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operr}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitOperr(AutomatedScriptParser.OperrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realizarMultiplicaoDivisao}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterRealizarMultiplicaoDivisao(AutomatedScriptParser.RealizarMultiplicaoDivisaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realizarMultiplicaoDivisao}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitRealizarMultiplicaoDivisao(AutomatedScriptParser.RealizarMultiplicaoDivisaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code devolver_valor_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterDevolver_valor_variavel(AutomatedScriptParser.Devolver_valor_variavelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code devolver_valor_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitDevolver_valor_variavel(AutomatedScriptParser.Devolver_valor_variavelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realizarSomaSub}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterRealizarSomaSub(AutomatedScriptParser.RealizarSomaSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realizarSomaSub}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitRealizarSomaSub(AutomatedScriptParser.RealizarSomaSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code realizarCalculoPrioritario}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterRealizarCalculoPrioritario(AutomatedScriptParser.RealizarCalculoPrioritarioContext ctx);
	/**
	 * Exit a parse tree produced by the {@code realizarCalculoPrioritario}
	 * labeled alternative in {@link AutomatedScriptParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitRealizarCalculoPrioritario(AutomatedScriptParser.RealizarCalculoPrioritarioContext ctx);
	/**
	 * Enter a parse tree produced by the {@code calculo_decimal}
	 * labeled alternative in {@link AutomatedScriptParser#objeto}.
	 * @param ctx the parse tree
	 */
	void enterCalculo_decimal(AutomatedScriptParser.Calculo_decimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code calculo_decimal}
	 * labeled alternative in {@link AutomatedScriptParser#objeto}.
	 * @param ctx the parse tree
	 */
	void exitCalculo_decimal(AutomatedScriptParser.Calculo_decimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code calculo_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#objeto}.
	 * @param ctx the parse tree
	 */
	void enterCalculo_variavel(AutomatedScriptParser.Calculo_variavelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code calculo_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#objeto}.
	 * @param ctx the parse tree
	 */
	void exitCalculo_variavel(AutomatedScriptParser.Calculo_variavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatedScriptParser#percentagem_num}.
	 * @param ctx the parse tree
	 */
	void enterPercentagem_num(AutomatedScriptParser.Percentagem_numContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatedScriptParser#percentagem_num}.
	 * @param ctx the parse tree
	 */
	void exitPercentagem_num(AutomatedScriptParser.Percentagem_numContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatedScriptParser#nome_ficheiro}.
	 * @param ctx the parse tree
	 */
	void enterNome_ficheiro(AutomatedScriptParser.Nome_ficheiroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatedScriptParser#nome_ficheiro}.
	 * @param ctx the parse tree
	 */
	void exitNome_ficheiro(AutomatedScriptParser.Nome_ficheiroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definirNovaVariavel}
	 * labeled alternative in {@link AutomatedScriptParser#atribuir_variavel}.
	 * @param ctx the parse tree
	 */
	void enterDefinirNovaVariavel(AutomatedScriptParser.DefinirNovaVariavelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definirNovaVariavel}
	 * labeled alternative in {@link AutomatedScriptParser#atribuir_variavel}.
	 * @param ctx the parse tree
	 */
	void exitDefinirNovaVariavel(AutomatedScriptParser.DefinirNovaVariavelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colocar_decimal_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterColocar_decimal_variavel(AutomatedScriptParser.Colocar_decimal_variavelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colocar_decimal_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitColocar_decimal_variavel(AutomatedScriptParser.Colocar_decimal_variavelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colocar_funcao_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterColocar_funcao_variavel(AutomatedScriptParser.Colocar_funcao_variavelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colocar_funcao_variavel}
	 * labeled alternative in {@link AutomatedScriptParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitColocar_funcao_variavel(AutomatedScriptParser.Colocar_funcao_variavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatedScriptParser#nome_variavel}.
	 * @param ctx the parse tree
	 */
	void enterNome_variavel(AutomatedScriptParser.Nome_variavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatedScriptParser#nome_variavel}.
	 * @param ctx the parse tree
	 */
	void exitNome_variavel(AutomatedScriptParser.Nome_variavelContext ctx);
}