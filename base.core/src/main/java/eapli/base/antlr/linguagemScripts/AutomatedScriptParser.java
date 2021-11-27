// Generated from C:/Users/Rafael/Desktop/LEI - ISEP/2Ano 2Semestre/LAPR4/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/linguagemScripts\AutomatedScript.g4 by ANTLR 4.9.1
package eapli.base.antlr.linguagemScripts;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AutomatedScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NUM=3, PERCENTAGEM=4, SOMA_SUB=5, MUL_DIV=6, BARRA=7, 
		SCRIPT=8, AND=9, OR=10, IGUAL=11, EMAIL=12, OPERADOR_COMPARAR=13, IF=14, 
		WORD=15, VIRGULA=16, DOLLAR=17, ASPAS=18, CHAVETA_ENTRADA=19, CHAVETA_FECHAR=20, 
		PARENTESE_CURVO_ABRIR=21, PARENTESE_CURVO_FECHAR=22, PONTO_VIRGULA=23, 
		XML=24, FILE_PATH=25, WS=26, ERR_CHAR=27;
	public static final int
		RULE_start = 0, RULE_script = 1, RULE_instrucao_script = 2, RULE_instrucoes_condicao = 3, 
		RULE_cond_verificacoes = 4, RULE_cond_verificacao = 5, RULE_operacao_bool = 6, 
		RULE_funcao = 7, RULE_send_email = 8, RULE_read_file = 9, RULE_string = 10, 
		RULE_content = 11, RULE_output = 12, RULE_decimal = 13, RULE_objeto = 14, 
		RULE_percentagem_num = 15, RULE_nome_ficheiro = 16, RULE_atribuir_variavel = 17, 
		RULE_variavel = 18, RULE_nome_variavel = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "script", "instrucao_script", "instrucoes_condicao", "cond_verificacoes", 
			"cond_verificacao", "operacao_bool", "funcao", "send_email", "read_file", 
			"string", "content", "output", "decimal", "objeto", "percentagem_num", 
			"nome_ficheiro", "atribuir_variavel", "variavel", "nome_variavel"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'sendEmail'", "'readFile'", null, "'%'", null, null, "'/'", "'SCRIPT'", 
			"'AND'", "'OR'", "'='", null, null, "'if'", null, "','", "'$'", "'\"'", 
			"'{'", "'}'", "'('", "')'", "';'", "'.xml'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "NUM", "PERCENTAGEM", "SOMA_SUB", "MUL_DIV", "BARRA", 
			"SCRIPT", "AND", "OR", "IGUAL", "EMAIL", "OPERADOR_COMPARAR", "IF", "WORD", 
			"VIRGULA", "DOLLAR", "ASPAS", "CHAVETA_ENTRADA", "CHAVETA_FECHAR", "PARENTESE_CURVO_ABRIR", 
			"PARENTESE_CURVO_FECHAR", "PONTO_VIRGULA", "XML", "FILE_PATH", "WS", 
			"ERR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AutomatedScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AutomatedScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public TerminalNode SCRIPT() { return getToken(AutomatedScriptParser.SCRIPT, 0); }
		public TerminalNode CHAVETA_ENTRADA() { return getToken(AutomatedScriptParser.CHAVETA_ENTRADA, 0); }
		public ScriptContext script() {
			return getRuleContext(ScriptContext.class,0);
		}
		public TerminalNode CHAVETA_FECHAR() { return getToken(AutomatedScriptParser.CHAVETA_FECHAR, 0); }
		public TerminalNode EOF() { return getToken(AutomatedScriptParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SCRIPT:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				match(SCRIPT);
				setState(41);
				match(CHAVETA_ENTRADA);
				setState(42);
				script(0);
				setState(43);
				match(CHAVETA_FECHAR);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScriptContext extends ParserRuleContext {
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
	 
		public ScriptContext() { }
		public void copyFrom(ScriptContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ResetExecucaoContext extends ScriptContext {
		public Instrucao_scriptContext instrucao_script() {
			return getRuleContext(Instrucao_scriptContext.class,0);
		}
		public ScriptContext script() {
			return getRuleContext(ScriptContext.class,0);
		}
		public ResetExecucaoContext(ScriptContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterResetExecucao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitResetExecucao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitResetExecucao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		return script(0);
	}

	private ScriptContext script(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ScriptContext _localctx = new ScriptContext(_ctx, _parentState);
		ScriptContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_script, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ResetExecucaoContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(49);
			instrucao_script();
			}
			_ctx.stop = _input.LT(-1);
			setState(55);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ResetExecucaoContext(new ScriptContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_script);
					setState(51);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(52);
					instrucao_script();
					}
					} 
				}
				setState(57);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Instrucao_scriptContext extends ParserRuleContext {
		public Instrucao_scriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instrucao_script; }
	 
		public Instrucao_scriptContext() { }
		public void copyFrom(Instrucao_scriptContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Instrucao_condicaoContext extends Instrucao_scriptContext {
		public Instrucoes_condicaoContext instrucoes_condicao() {
			return getRuleContext(Instrucoes_condicaoContext.class,0);
		}
		public Instrucao_condicaoContext(Instrucao_scriptContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterInstrucao_condicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitInstrucao_condicao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitInstrucao_condicao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Atribuir_variavel_ruleContext extends Instrucao_scriptContext {
		public Atribuir_variavelContext atribuir_variavel() {
			return getRuleContext(Atribuir_variavelContext.class,0);
		}
		public TerminalNode PONTO_VIRGULA() { return getToken(AutomatedScriptParser.PONTO_VIRGULA, 0); }
		public Atribuir_variavel_ruleContext(Instrucao_scriptContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterAtribuir_variavel_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitAtribuir_variavel_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitAtribuir_variavel_rule(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Missing_ponto_virgulaContext extends Instrucao_scriptContext {
		public FuncaoContext funcao() {
			return getRuleContext(FuncaoContext.class,0);
		}
		public Atribuir_variavelContext atribuir_variavel() {
			return getRuleContext(Atribuir_variavelContext.class,0);
		}
		public Missing_ponto_virgulaContext(Instrucao_scriptContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterMissing_ponto_virgula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitMissing_ponto_virgula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitMissing_ponto_virgula(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Do_funcaoContext extends Instrucao_scriptContext {
		public FuncaoContext funcao() {
			return getRuleContext(FuncaoContext.class,0);
		}
		public TerminalNode PONTO_VIRGULA() { return getToken(AutomatedScriptParser.PONTO_VIRGULA, 0); }
		public Do_funcaoContext(Instrucao_scriptContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterDo_funcao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitDo_funcao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitDo_funcao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instrucao_scriptContext instrucao_script() throws RecognitionException {
		Instrucao_scriptContext _localctx = new Instrucao_scriptContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instrucao_script);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new Instrucao_condicaoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				instrucoes_condicao();
				}
				break;
			case 2:
				_localctx = new Do_funcaoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				funcao();
				setState(60);
				match(PONTO_VIRGULA);
				}
				break;
			case 3:
				_localctx = new Atribuir_variavel_ruleContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				atribuir_variavel();
				setState(63);
				match(PONTO_VIRGULA);
				}
				break;
			case 4:
				_localctx = new Missing_ponto_virgulaContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				funcao();
				}
				break;
			case 5:
				_localctx = new Missing_ponto_virgulaContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				atribuir_variavel();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instrucoes_condicaoContext extends ParserRuleContext {
		public Instrucoes_condicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instrucoes_condicao; }
	 
		public Instrucoes_condicaoContext() { }
		public void copyFrom(Instrucoes_condicaoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CondicaoIfContext extends Instrucoes_condicaoContext {
		public Cond_verificacoesContext cond;
		public ScriptContext condicoes;
		public TerminalNode IF() { return getToken(AutomatedScriptParser.IF, 0); }
		public TerminalNode CHAVETA_ENTRADA() { return getToken(AutomatedScriptParser.CHAVETA_ENTRADA, 0); }
		public TerminalNode CHAVETA_FECHAR() { return getToken(AutomatedScriptParser.CHAVETA_FECHAR, 0); }
		public Cond_verificacoesContext cond_verificacoes() {
			return getRuleContext(Cond_verificacoesContext.class,0);
		}
		public ScriptContext script() {
			return getRuleContext(ScriptContext.class,0);
		}
		public CondicaoIfContext(Instrucoes_condicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterCondicaoIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitCondicaoIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitCondicaoIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instrucoes_condicaoContext instrucoes_condicao() throws RecognitionException {
		Instrucoes_condicaoContext _localctx = new Instrucoes_condicaoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_instrucoes_condicao);
		try {
			_localctx = new CondicaoIfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(IF);
			setState(70);
			((CondicaoIfContext)_localctx).cond = cond_verificacoes();
			setState(71);
			match(CHAVETA_ENTRADA);
			setState(72);
			((CondicaoIfContext)_localctx).condicoes = script(0);
			setState(73);
			match(CHAVETA_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cond_verificacoesContext extends ParserRuleContext {
		public Cond_verificacoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond_verificacoes; }
	 
		public Cond_verificacoesContext() { }
		public void copyFrom(Cond_verificacoesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VerificarCondicaoIfContext extends Cond_verificacoesContext {
		public Cond_verificacaoContext cond;
		public TerminalNode PARENTESE_CURVO_ABRIR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_ABRIR, 0); }
		public TerminalNode PARENTESE_CURVO_FECHAR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_FECHAR, 0); }
		public Cond_verificacaoContext cond_verificacao() {
			return getRuleContext(Cond_verificacaoContext.class,0);
		}
		public VerificarCondicaoIfContext(Cond_verificacoesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterVerificarCondicaoIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitVerificarCondicaoIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitVerificarCondicaoIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cond_verificacoesContext cond_verificacoes() throws RecognitionException {
		Cond_verificacoesContext _localctx = new Cond_verificacoesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cond_verificacoes);
		try {
			_localctx = new VerificarCondicaoIfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(PARENTESE_CURVO_ABRIR);
			setState(76);
			((VerificarCondicaoIfContext)_localctx).cond = cond_verificacao(0);
			setState(77);
			match(PARENTESE_CURVO_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cond_verificacaoContext extends ParserRuleContext {
		public Cond_verificacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond_verificacao; }
	 
		public Cond_verificacaoContext() { }
		public void copyFrom(Cond_verificacaoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OperacaoBooleanContext extends Cond_verificacaoContext {
		public Operacao_boolContext bool;
		public Operacao_boolContext operacao_bool() {
			return getRuleContext(Operacao_boolContext.class,0);
		}
		public OperacaoBooleanContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterOperacaoBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitOperacaoBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitOperacaoBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrConditionContext extends Cond_verificacaoContext {
		public Cond_verificacaoContext left;
		public Cond_verificacaoContext right;
		public TerminalNode OR() { return getToken(AutomatedScriptParser.OR, 0); }
		public List<Cond_verificacaoContext> cond_verificacao() {
			return getRuleContexts(Cond_verificacaoContext.class);
		}
		public Cond_verificacaoContext cond_verificacao(int i) {
			return getRuleContext(Cond_verificacaoContext.class,i);
		}
		public OrConditionContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterOrCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitOrCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitOrCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrioridadeContext extends Cond_verificacaoContext {
		public Cond_verificacaoContext cond;
		public TerminalNode PARENTESE_CURVO_ABRIR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_ABRIR, 0); }
		public TerminalNode PARENTESE_CURVO_FECHAR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_FECHAR, 0); }
		public Cond_verificacaoContext cond_verificacao() {
			return getRuleContext(Cond_verificacaoContext.class,0);
		}
		public PrioridadeContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterPrioridade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitPrioridade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitPrioridade(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndConditionContext extends Cond_verificacaoContext {
		public Cond_verificacaoContext left;
		public Cond_verificacaoContext right;
		public TerminalNode AND() { return getToken(AutomatedScriptParser.AND, 0); }
		public List<Cond_verificacaoContext> cond_verificacao() {
			return getRuleContexts(Cond_verificacaoContext.class);
		}
		public Cond_verificacaoContext cond_verificacao(int i) {
			return getRuleContext(Cond_verificacaoContext.class,i);
		}
		public AndConditionContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterAndCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitAndCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitAndCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cond_verificacaoContext cond_verificacao() throws RecognitionException {
		return cond_verificacao(0);
	}

	private Cond_verificacaoContext cond_verificacao(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Cond_verificacaoContext _localctx = new Cond_verificacaoContext(_ctx, _parentState);
		Cond_verificacaoContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_cond_verificacao, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new PrioridadeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(80);
				match(PARENTESE_CURVO_ABRIR);
				setState(81);
				((PrioridadeContext)_localctx).cond = cond_verificacao(0);
				setState(82);
				match(PARENTESE_CURVO_FECHAR);
				}
				break;
			case 2:
				{
				_localctx = new OperacaoBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				((OperacaoBooleanContext)_localctx).bool = operacao_bool();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(93);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new AndConditionContext(new Cond_verificacaoContext(_parentctx, _parentState));
						((AndConditionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_cond_verificacao);
						setState(87);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(88);
						match(AND);
						setState(89);
						((AndConditionContext)_localctx).right = cond_verificacao(4);
						}
						break;
					case 2:
						{
						_localctx = new OrConditionContext(new Cond_verificacaoContext(_parentctx, _parentState));
						((OrConditionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_cond_verificacao);
						setState(90);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(91);
						match(OR);
						setState(92);
						((OrConditionContext)_localctx).right = cond_verificacao(3);
						}
						break;
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Operacao_boolContext extends ParserRuleContext {
		public Operacao_boolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacao_bool; }
	 
		public Operacao_boolContext() { }
		public void copyFrom(Operacao_boolContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RealizarOperacaoBooleanContext extends Operacao_boolContext {
		public ObjetoContext left;
		public Token op;
		public ObjetoContext right;
		public List<ObjetoContext> objeto() {
			return getRuleContexts(ObjetoContext.class);
		}
		public ObjetoContext objeto(int i) {
			return getRuleContext(ObjetoContext.class,i);
		}
		public TerminalNode OPERADOR_COMPARAR() { return getToken(AutomatedScriptParser.OPERADOR_COMPARAR, 0); }
		public RealizarOperacaoBooleanContext(Operacao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterRealizarOperacaoBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitRealizarOperacaoBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitRealizarOperacaoBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacao_boolContext operacao_bool() throws RecognitionException {
		Operacao_boolContext _localctx = new Operacao_boolContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_operacao_bool);
		try {
			_localctx = new RealizarOperacaoBooleanContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((RealizarOperacaoBooleanContext)_localctx).left = objeto();
			setState(99);
			((RealizarOperacaoBooleanContext)_localctx).op = match(OPERADOR_COMPARAR);
			setState(100);
			((RealizarOperacaoBooleanContext)_localctx).right = objeto();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoContext extends ParserRuleContext {
		public Send_emailContext send_email() {
			return getRuleContext(Send_emailContext.class,0);
		}
		public Read_fileContext read_file() {
			return getRuleContext(Read_fileContext.class,0);
		}
		public FuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterFuncao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitFuncao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitFuncao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoContext funcao() throws RecognitionException {
		FuncaoContext _localctx = new FuncaoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcao);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				send_email();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				read_file();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Send_emailContext extends ParserRuleContext {
		public Send_emailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_send_email; }
	 
		public Send_emailContext() { }
		public void copyFrom(Send_emailContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SendEmailContext extends Send_emailContext {
		public Token destinatario;
		public StringContext assunto;
		public StringContext corpo;
		public TerminalNode PARENTESE_CURVO_ABRIR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_ABRIR, 0); }
		public List<TerminalNode> VIRGULA() { return getTokens(AutomatedScriptParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(AutomatedScriptParser.VIRGULA, i);
		}
		public TerminalNode PARENTESE_CURVO_FECHAR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_FECHAR, 0); }
		public TerminalNode EMAIL() { return getToken(AutomatedScriptParser.EMAIL, 0); }
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public SendEmailContext(Send_emailContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterSendEmail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitSendEmail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitSendEmail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Send_emailContext send_email() throws RecognitionException {
		Send_emailContext _localctx = new Send_emailContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_send_email);
		try {
			_localctx = new SendEmailContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__0);
			setState(107);
			match(PARENTESE_CURVO_ABRIR);
			setState(108);
			((SendEmailContext)_localctx).destinatario = match(EMAIL);
			setState(109);
			match(VIRGULA);
			setState(110);
			((SendEmailContext)_localctx).assunto = string();
			setState(111);
			match(VIRGULA);
			setState(112);
			((SendEmailContext)_localctx).corpo = string();
			setState(113);
			match(PARENTESE_CURVO_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Read_fileContext extends ParserRuleContext {
		public Read_fileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read_file; }
	 
		public Read_fileContext() { }
		public void copyFrom(Read_fileContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReadFileContext extends Read_fileContext {
		public Nome_ficheiroContext nomeFicheiro;
		public StringContext base;
		public StringContext id;
		public StringContext elemento;
		public TerminalNode PARENTESE_CURVO_ABRIR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_ABRIR, 0); }
		public List<TerminalNode> VIRGULA() { return getTokens(AutomatedScriptParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(AutomatedScriptParser.VIRGULA, i);
		}
		public TerminalNode PARENTESE_CURVO_FECHAR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_FECHAR, 0); }
		public Nome_ficheiroContext nome_ficheiro() {
			return getRuleContext(Nome_ficheiroContext.class,0);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public ReadFileContext(Read_fileContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterReadFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitReadFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitReadFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Read_fileContext read_file() throws RecognitionException {
		Read_fileContext _localctx = new Read_fileContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_read_file);
		try {
			_localctx = new ReadFileContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__1);
			setState(116);
			match(PARENTESE_CURVO_ABRIR);
			setState(117);
			((ReadFileContext)_localctx).nomeFicheiro = nome_ficheiro();
			setState(118);
			match(VIRGULA);
			setState(119);
			((ReadFileContext)_localctx).base = string();
			setState(120);
			match(VIRGULA);
			setState(121);
			((ReadFileContext)_localctx).id = string();
			setState(122);
			match(VIRGULA);
			setState(123);
			((ReadFileContext)_localctx).elemento = string();
			setState(124);
			match(PARENTESE_CURVO_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
	 
		public StringContext() { }
		public void copyFrom(StringContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProcessarStringContext extends StringContext {
		public ContentContext cont;
		public List<TerminalNode> ASPAS() { return getTokens(AutomatedScriptParser.ASPAS); }
		public TerminalNode ASPAS(int i) {
			return getToken(AutomatedScriptParser.ASPAS, i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public ProcessarStringContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterProcessarString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitProcessarString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitProcessarString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MissingAspasErrorContext extends StringContext {
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public MissingAspasErrorContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterMissingAspasError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitMissingAspasError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitMissingAspasError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_string);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASPAS:
				_localctx = new ProcessarStringContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(ASPAS);
				setState(127);
				((ProcessarStringContext)_localctx).cont = content(0);
				setState(128);
				match(ASPAS);
				}
				break;
			case NUM:
			case WORD:
			case CHAVETA_ENTRADA:
				_localctx = new MissingAspasErrorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				content(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContentContext extends ParserRuleContext {
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
	 
		public ContentContext() { }
		public void copyFrom(ContentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CheckContentContext extends ContentContext {
		public ContentContext cont;
		public OutputContext out;
		public OutputContext output() {
			return getRuleContext(OutputContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public CheckContentContext(ContentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterCheckContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitCheckContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitCheckContent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipContext extends ContentContext {
		public ContentContext cont;
		public Token word;
		public TerminalNode WORD() { return getToken(AutomatedScriptParser.WORD, 0); }
		public TerminalNode NUM() { return getToken(AutomatedScriptParser.NUM, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public SkipContext(ContentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterSkip(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitSkip(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		return content(0);
	}

	private ContentContext content(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ContentContext _localctx = new ContentContext(_ctx, _parentState);
		ContentContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_content, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHAVETA_ENTRADA:
				{
				_localctx = new CheckContentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(134);
				((CheckContentContext)_localctx).out = output();
				}
				break;
			case WORD:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				((SkipContext)_localctx).word = match(WORD);
				}
				break;
			case NUM:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				((SkipContext)_localctx).word = match(NUM);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(145);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new SkipContext(new ContentContext(_parentctx, _parentState));
						((SkipContext)_localctx).cont = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_content);
						setState(139);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(140);
						((SkipContext)_localctx).word = match(WORD);
						}
						break;
					case 2:
						{
						_localctx = new CheckContentContext(new ContentContext(_parentctx, _parentState));
						((CheckContentContext)_localctx).cont = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_content);
						setState(141);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(142);
						((CheckContentContext)_localctx).out = output();
						}
						break;
					case 3:
						{
						_localctx = new SkipContext(new ContentContext(_parentctx, _parentState));
						((SkipContext)_localctx).cont = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_content);
						setState(143);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(144);
						((SkipContext)_localctx).word = match(NUM);
						}
						break;
					}
					} 
				}
				setState(149);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OutputContext extends ParserRuleContext {
		public OutputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_output; }
	 
		public OutputContext() { }
		public void copyFrom(OutputContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RealizarOutputContext extends OutputContext {
		public Nome_variavelContext nome;
		public List<TerminalNode> CHAVETA_ENTRADA() { return getTokens(AutomatedScriptParser.CHAVETA_ENTRADA); }
		public TerminalNode CHAVETA_ENTRADA(int i) {
			return getToken(AutomatedScriptParser.CHAVETA_ENTRADA, i);
		}
		public List<TerminalNode> CHAVETA_FECHAR() { return getTokens(AutomatedScriptParser.CHAVETA_FECHAR); }
		public TerminalNode CHAVETA_FECHAR(int i) {
			return getToken(AutomatedScriptParser.CHAVETA_FECHAR, i);
		}
		public Nome_variavelContext nome_variavel() {
			return getRuleContext(Nome_variavelContext.class,0);
		}
		public RealizarOutputContext(OutputContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterRealizarOutput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitRealizarOutput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitRealizarOutput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputContext output() throws RecognitionException {
		OutputContext _localctx = new OutputContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_output);
		try {
			_localctx = new RealizarOutputContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(CHAVETA_ENTRADA);
			setState(151);
			match(CHAVETA_ENTRADA);
			setState(152);
			((RealizarOutputContext)_localctx).nome = nome_variavel();
			setState(153);
			match(CHAVETA_FECHAR);
			setState(154);
			match(CHAVETA_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalContext extends ParserRuleContext {
		public DecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal; }
	 
		public DecimalContext() { }
		public void copyFrom(DecimalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnProprioNumeroContext extends DecimalContext {
		public Token num;
		public TerminalNode NUM() { return getToken(AutomatedScriptParser.NUM, 0); }
		public ReturnProprioNumeroContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterReturnProprioNumero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitReturnProprioNumero(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitReturnProprioNumero(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnPercentagemContext extends DecimalContext {
		public Percentagem_numContext num_percentagem;
		public Percentagem_numContext percentagem_num() {
			return getRuleContext(Percentagem_numContext.class,0);
		}
		public ReturnPercentagemContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterReturnPercentagem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitReturnPercentagem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitReturnPercentagem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OperrContext extends DecimalContext {
		public TerminalNode MUL_DIV() { return getToken(AutomatedScriptParser.MUL_DIV, 0); }
		public TerminalNode SOMA_SUB() { return getToken(AutomatedScriptParser.SOMA_SUB, 0); }
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public OperrContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterOperr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitOperr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitOperr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RealizarMultiplicaoDivisaoContext extends DecimalContext {
		public DecimalContext left;
		public Token op;
		public DecimalContext right;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public TerminalNode MUL_DIV() { return getToken(AutomatedScriptParser.MUL_DIV, 0); }
		public RealizarMultiplicaoDivisaoContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterRealizarMultiplicaoDivisao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitRealizarMultiplicaoDivisao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitRealizarMultiplicaoDivisao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Devolver_valor_variavelContext extends DecimalContext {
		public Nome_variavelContext nome;
		public Nome_variavelContext nome_variavel() {
			return getRuleContext(Nome_variavelContext.class,0);
		}
		public Devolver_valor_variavelContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterDevolver_valor_variavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitDevolver_valor_variavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitDevolver_valor_variavel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RealizarSomaSubContext extends DecimalContext {
		public DecimalContext left;
		public Token op;
		public DecimalContext right;
		public List<DecimalContext> decimal() {
			return getRuleContexts(DecimalContext.class);
		}
		public DecimalContext decimal(int i) {
			return getRuleContext(DecimalContext.class,i);
		}
		public TerminalNode SOMA_SUB() { return getToken(AutomatedScriptParser.SOMA_SUB, 0); }
		public RealizarSomaSubContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterRealizarSomaSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitRealizarSomaSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitRealizarSomaSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RealizarCalculoPrioritarioContext extends DecimalContext {
		public DecimalContext dec;
		public TerminalNode PARENTESE_CURVO_ABRIR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_ABRIR, 0); }
		public TerminalNode PARENTESE_CURVO_FECHAR() { return getToken(AutomatedScriptParser.PARENTESE_CURVO_FECHAR, 0); }
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public RealizarCalculoPrioritarioContext(DecimalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterRealizarCalculoPrioritario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitRealizarCalculoPrioritario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitRealizarCalculoPrioritario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalContext decimal() throws RecognitionException {
		return decimal(0);
	}

	private DecimalContext decimal(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DecimalContext _localctx = new DecimalContext(_ctx, _parentState);
		DecimalContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_decimal, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new RealizarCalculoPrioritarioContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(157);
				match(PARENTESE_CURVO_ABRIR);
				setState(158);
				((RealizarCalculoPrioritarioContext)_localctx).dec = decimal(0);
				setState(159);
				match(PARENTESE_CURVO_FECHAR);
				}
				break;
			case 2:
				{
				_localctx = new ReturnProprioNumeroContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				((ReturnProprioNumeroContext)_localctx).num = match(NUM);
				}
				break;
			case 3:
				{
				_localctx = new Devolver_valor_variavelContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(162);
				((Devolver_valor_variavelContext)_localctx).nome = nome_variavel();
				}
				break;
			case 4:
				{
				_localctx = new ReturnPercentagemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163);
				((ReturnPercentagemContext)_localctx).num_percentagem = percentagem_num();
				}
				break;
			case 5:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164);
				match(MUL_DIV);
				}
				break;
			case 6:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(165);
				match(SOMA_SUB);
				}
				break;
			case 7:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				match(MUL_DIV);
				setState(167);
				decimal(2);
				}
				break;
			case 8:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168);
				match(SOMA_SUB);
				setState(169);
				decimal(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(182);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new RealizarMultiplicaoDivisaoContext(new DecimalContext(_parentctx, _parentState));
						((RealizarMultiplicaoDivisaoContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_decimal);
						setState(172);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(173);
						((RealizarMultiplicaoDivisaoContext)_localctx).op = match(MUL_DIV);
						setState(174);
						((RealizarMultiplicaoDivisaoContext)_localctx).right = decimal(12);
						}
						break;
					case 2:
						{
						_localctx = new RealizarSomaSubContext(new DecimalContext(_parentctx, _parentState));
						((RealizarSomaSubContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_decimal);
						setState(175);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(176);
						((RealizarSomaSubContext)_localctx).op = match(SOMA_SUB);
						setState(177);
						((RealizarSomaSubContext)_localctx).right = decimal(11);
						}
						break;
					case 3:
						{
						_localctx = new OperrContext(new DecimalContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_decimal);
						setState(178);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(179);
						match(MUL_DIV);
						}
						break;
					case 4:
						{
						_localctx = new OperrContext(new DecimalContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_decimal);
						setState(180);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(181);
						match(SOMA_SUB);
						}
						break;
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ObjetoContext extends ParserRuleContext {
		public ObjetoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objeto; }
	 
		public ObjetoContext() { }
		public void copyFrom(ObjetoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Calculo_decimalContext extends ObjetoContext {
		public DecimalContext dec;
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public Calculo_decimalContext(ObjetoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterCalculo_decimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitCalculo_decimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitCalculo_decimal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Calculo_variavelContext extends ObjetoContext {
		public VariavelContext var;
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public Calculo_variavelContext(ObjetoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterCalculo_variavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitCalculo_variavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitCalculo_variavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjetoContext objeto() throws RecognitionException {
		ObjetoContext _localctx = new ObjetoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_objeto);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new Calculo_decimalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				((Calculo_decimalContext)_localctx).dec = decimal(0);
				}
				break;
			case 2:
				_localctx = new Calculo_variavelContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				((Calculo_variavelContext)_localctx).var = variavel();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Percentagem_numContext extends ParserRuleContext {
		public Token num;
		public TerminalNode PERCENTAGEM() { return getToken(AutomatedScriptParser.PERCENTAGEM, 0); }
		public TerminalNode NUM() { return getToken(AutomatedScriptParser.NUM, 0); }
		public Percentagem_numContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percentagem_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterPercentagem_num(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitPercentagem_num(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitPercentagem_num(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Percentagem_numContext percentagem_num() throws RecognitionException {
		Percentagem_numContext _localctx = new Percentagem_numContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_percentagem_num);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(PERCENTAGEM);
			setState(192);
			((Percentagem_numContext)_localctx).num = match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Nome_ficheiroContext extends ParserRuleContext {
		public Token path;
		public List<TerminalNode> ASPAS() { return getTokens(AutomatedScriptParser.ASPAS); }
		public TerminalNode ASPAS(int i) {
			return getToken(AutomatedScriptParser.ASPAS, i);
		}
		public TerminalNode FILE_PATH() { return getToken(AutomatedScriptParser.FILE_PATH, 0); }
		public Nome_ficheiroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nome_ficheiro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterNome_ficheiro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitNome_ficheiro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitNome_ficheiro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Nome_ficheiroContext nome_ficheiro() throws RecognitionException {
		Nome_ficheiroContext _localctx = new Nome_ficheiroContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_nome_ficheiro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(ASPAS);
			setState(195);
			((Nome_ficheiroContext)_localctx).path = match(FILE_PATH);
			setState(196);
			match(ASPAS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atribuir_variavelContext extends ParserRuleContext {
		public Atribuir_variavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atribuir_variavel; }
	 
		public Atribuir_variavelContext() { }
		public void copyFrom(Atribuir_variavelContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DefinirNovaVariavelContext extends Atribuir_variavelContext {
		public VariavelContext var;
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public DefinirNovaVariavelContext(Atribuir_variavelContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterDefinirNovaVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitDefinirNovaVariavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitDefinirNovaVariavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atribuir_variavelContext atribuir_variavel() throws RecognitionException {
		Atribuir_variavelContext _localctx = new Atribuir_variavelContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_atribuir_variavel);
		try {
			_localctx = new DefinirNovaVariavelContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			((DefinirNovaVariavelContext)_localctx).var = variavel();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariavelContext extends ParserRuleContext {
		public VariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variavel; }
	 
		public VariavelContext() { }
		public void copyFrom(VariavelContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Colocar_funcao_variavelContext extends VariavelContext {
		public Nome_variavelContext nome;
		public Read_fileContext valor;
		public TerminalNode IGUAL() { return getToken(AutomatedScriptParser.IGUAL, 0); }
		public Nome_variavelContext nome_variavel() {
			return getRuleContext(Nome_variavelContext.class,0);
		}
		public Read_fileContext read_file() {
			return getRuleContext(Read_fileContext.class,0);
		}
		public Colocar_funcao_variavelContext(VariavelContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterColocar_funcao_variavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitColocar_funcao_variavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitColocar_funcao_variavel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Colocar_decimal_variavelContext extends VariavelContext {
		public Nome_variavelContext nome;
		public DecimalContext valor;
		public TerminalNode IGUAL() { return getToken(AutomatedScriptParser.IGUAL, 0); }
		public Nome_variavelContext nome_variavel() {
			return getRuleContext(Nome_variavelContext.class,0);
		}
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public Colocar_decimal_variavelContext(VariavelContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterColocar_decimal_variavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitColocar_decimal_variavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitColocar_decimal_variavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariavelContext variavel() throws RecognitionException {
		VariavelContext _localctx = new VariavelContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_variavel);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new Colocar_decimal_variavelContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				((Colocar_decimal_variavelContext)_localctx).nome = nome_variavel();
				setState(201);
				match(IGUAL);
				setState(202);
				((Colocar_decimal_variavelContext)_localctx).valor = decimal(0);
				}
				break;
			case 2:
				_localctx = new Colocar_funcao_variavelContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				((Colocar_funcao_variavelContext)_localctx).nome = nome_variavel();
				setState(205);
				match(IGUAL);
				setState(206);
				((Colocar_funcao_variavelContext)_localctx).valor = read_file();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Nome_variavelContext extends ParserRuleContext {
		public Token word;
		public TerminalNode DOLLAR() { return getToken(AutomatedScriptParser.DOLLAR, 0); }
		public TerminalNode WORD() { return getToken(AutomatedScriptParser.WORD, 0); }
		public Nome_variavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nome_variavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).enterNome_variavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatedScriptListener ) ((AutomatedScriptListener)listener).exitNome_variavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomatedScriptVisitor ) return ((AutomatedScriptVisitor<? extends T>)visitor).visitNome_variavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Nome_variavelContext nome_variavel() throws RecognitionException {
		Nome_variavelContext _localctx = new Nome_variavelContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_nome_variavel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(DOLLAR);
			setState(211);
			((Nome_variavelContext)_localctx).word = match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return script_sempred((ScriptContext)_localctx, predIndex);
		case 5:
			return cond_verificacao_sempred((Cond_verificacaoContext)_localctx, predIndex);
		case 11:
			return content_sempred((ContentContext)_localctx, predIndex);
		case 13:
			return decimal_sempred((DecimalContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean script_sempred(ScriptContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean cond_verificacao_sempred(Cond_verificacaoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean content_sempred(ContentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean decimal_sempred(DecimalContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00d8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\5\2\61\n\2\3\3"+
		"\3\3\3\3\3\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4F\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7X\n\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7`\n\7\f\7\16\7c\13\7\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\5\tk\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\5\f\u0086\n\f\3\r\3\r\3\r\3\r\5\r\u008c\n\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\7\r\u0094\n\r\f\r\16\r\u0097\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u00ad\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00b9"+
		"\n\17\f\17\16\17\u00bc\13\17\3\20\3\20\5\20\u00c0\n\20\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\5\24\u00d3\n\24\3\25\3\25\3\25\3\25\2\6\4\f\30\34\26\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(\2\2\2\u00e0\2\60\3\2\2\2\4\62\3\2\2\2\6E\3"+
		"\2\2\2\bG\3\2\2\2\nM\3\2\2\2\fW\3\2\2\2\16d\3\2\2\2\20j\3\2\2\2\22l\3"+
		"\2\2\2\24u\3\2\2\2\26\u0085\3\2\2\2\30\u008b\3\2\2\2\32\u0098\3\2\2\2"+
		"\34\u00ac\3\2\2\2\36\u00bf\3\2\2\2 \u00c1\3\2\2\2\"\u00c4\3\2\2\2$\u00c8"+
		"\3\2\2\2&\u00d2\3\2\2\2(\u00d4\3\2\2\2*+\7\n\2\2+,\7\25\2\2,-\5\4\3\2"+
		"-.\7\26\2\2.\61\3\2\2\2/\61\7\2\2\3\60*\3\2\2\2\60/\3\2\2\2\61\3\3\2\2"+
		"\2\62\63\b\3\1\2\63\64\5\6\4\2\649\3\2\2\2\65\66\f\4\2\2\668\5\6\4\2\67"+
		"\65\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;9\3\2\2\2<F\5\b"+
		"\5\2=>\5\20\t\2>?\7\31\2\2?F\3\2\2\2@A\5$\23\2AB\7\31\2\2BF\3\2\2\2CF"+
		"\5\20\t\2DF\5$\23\2E<\3\2\2\2E=\3\2\2\2E@\3\2\2\2EC\3\2\2\2ED\3\2\2\2"+
		"F\7\3\2\2\2GH\7\20\2\2HI\5\n\6\2IJ\7\25\2\2JK\5\4\3\2KL\7\26\2\2L\t\3"+
		"\2\2\2MN\7\27\2\2NO\5\f\7\2OP\7\30\2\2P\13\3\2\2\2QR\b\7\1\2RS\7\27\2"+
		"\2ST\5\f\7\2TU\7\30\2\2UX\3\2\2\2VX\5\16\b\2WQ\3\2\2\2WV\3\2\2\2Xa\3\2"+
		"\2\2YZ\f\5\2\2Z[\7\13\2\2[`\5\f\7\6\\]\f\4\2\2]^\7\f\2\2^`\5\f\7\5_Y\3"+
		"\2\2\2_\\\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\r\3\2\2\2ca\3\2\2\2d"+
		"e\5\36\20\2ef\7\17\2\2fg\5\36\20\2g\17\3\2\2\2hk\5\22\n\2ik\5\24\13\2"+
		"jh\3\2\2\2ji\3\2\2\2k\21\3\2\2\2lm\7\3\2\2mn\7\27\2\2no\7\16\2\2op\7\22"+
		"\2\2pq\5\26\f\2qr\7\22\2\2rs\5\26\f\2st\7\30\2\2t\23\3\2\2\2uv\7\4\2\2"+
		"vw\7\27\2\2wx\5\"\22\2xy\7\22\2\2yz\5\26\f\2z{\7\22\2\2{|\5\26\f\2|}\7"+
		"\22\2\2}~\5\26\f\2~\177\7\30\2\2\177\25\3\2\2\2\u0080\u0081\7\24\2\2\u0081"+
		"\u0082\5\30\r\2\u0082\u0083\7\24\2\2\u0083\u0086\3\2\2\2\u0084\u0086\5"+
		"\30\r\2\u0085\u0080\3\2\2\2\u0085\u0084\3\2\2\2\u0086\27\3\2\2\2\u0087"+
		"\u0088\b\r\1\2\u0088\u008c\5\32\16\2\u0089\u008c\7\21\2\2\u008a\u008c"+
		"\7\5\2\2\u008b\u0087\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c"+
		"\u0095\3\2\2\2\u008d\u008e\f\b\2\2\u008e\u0094\7\21\2\2\u008f\u0090\f"+
		"\7\2\2\u0090\u0094\5\32\16\2\u0091\u0092\f\4\2\2\u0092\u0094\7\5\2\2\u0093"+
		"\u008d\3\2\2\2\u0093\u008f\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0097\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\31\3\2\2\2\u0097\u0095"+
		"\3\2\2\2\u0098\u0099\7\25\2\2\u0099\u009a\7\25\2\2\u009a\u009b\5(\25\2"+
		"\u009b\u009c\7\26\2\2\u009c\u009d\7\26\2\2\u009d\33\3\2\2\2\u009e\u009f"+
		"\b\17\1\2\u009f\u00a0\7\27\2\2\u00a0\u00a1\5\34\17\2\u00a1\u00a2\7\30"+
		"\2\2\u00a2\u00ad\3\2\2\2\u00a3\u00ad\7\5\2\2\u00a4\u00ad\5(\25\2\u00a5"+
		"\u00ad\5 \21\2\u00a6\u00ad\7\b\2\2\u00a7\u00ad\7\7\2\2\u00a8\u00a9\7\b"+
		"\2\2\u00a9\u00ad\5\34\17\4\u00aa\u00ab\7\7\2\2\u00ab\u00ad\5\34\17\3\u00ac"+
		"\u009e\3\2\2\2\u00ac\u00a3\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac\u00a5\3\2"+
		"\2\2\u00ac\u00a6\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00ba\3\2\2\2\u00ae\u00af\f\r\2\2\u00af\u00b0\7\b"+
		"\2\2\u00b0\u00b9\5\34\17\16\u00b1\u00b2\f\f\2\2\u00b2\u00b3\7\7\2\2\u00b3"+
		"\u00b9\5\34\17\r\u00b4\u00b5\f\6\2\2\u00b5\u00b9\7\b\2\2\u00b6\u00b7\f"+
		"\5\2\2\u00b7\u00b9\7\7\2\2\u00b8\u00ae\3\2\2\2\u00b8\u00b1\3\2\2\2\u00b8"+
		"\u00b4\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\35\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0"+
		"\5\34\17\2\u00be\u00c0\5&\24\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2"+
		"\u00c0\37\3\2\2\2\u00c1\u00c2\7\6\2\2\u00c2\u00c3\7\5\2\2\u00c3!\3\2\2"+
		"\2\u00c4\u00c5\7\24\2\2\u00c5\u00c6\7\33\2\2\u00c6\u00c7\7\24\2\2\u00c7"+
		"#\3\2\2\2\u00c8\u00c9\5&\24\2\u00c9%\3\2\2\2\u00ca\u00cb\5(\25\2\u00cb"+
		"\u00cc\7\r\2\2\u00cc\u00cd\5\34\17\2\u00cd\u00d3\3\2\2\2\u00ce\u00cf\5"+
		"(\25\2\u00cf\u00d0\7\r\2\2\u00d0\u00d1\5\24\13\2\u00d1\u00d3\3\2\2\2\u00d2"+
		"\u00ca\3\2\2\2\u00d2\u00ce\3\2\2\2\u00d3\'\3\2\2\2\u00d4\u00d5\7\23\2"+
		"\2\u00d5\u00d6\7\21\2\2\u00d6)\3\2\2\2\22\609EW_aj\u0085\u008b\u0093\u0095"+
		"\u00ac\u00b8\u00ba\u00bf\u00d2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}