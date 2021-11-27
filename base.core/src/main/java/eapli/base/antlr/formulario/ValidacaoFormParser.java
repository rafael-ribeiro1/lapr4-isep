// Generated from C:/lei20_21_s4_2di_02/base.core/src/main/java/eapli/base/antlr/formulario\ValidacaoForm.g4 by ANTLR 4.9.1
package eapli.base.antlr.formulario;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ValidacaoFormParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, NUM=19, OPERADOR_BOOL=20, MUL_DIV=21, SOMA_SUB=22, ATRIBUTO=23, 
		STRING=24, WS=25, ERR_CHAR=26;
	public static final int
		RULE_start = 0, RULE_form = 1, RULE_instrucao_form = 2, RULE_cond_verificacao = 3, 
		RULE_validacoes = 4, RULE_validacao = 5, RULE_operacoes_logicas = 6, RULE_funcao_bool = 7, 
		RULE_operacao_bool = 8, RULE_inteiro = 9, RULE_atributo = 10, RULE_regex = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "form", "instrucao_form", "cond_verificacao", "validacoes", 
			"validacao", "operacoes_logicas", "funcao_bool", "operacao_bool", "inteiro", 
			"atributo", "regex"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'form'", "'{'", "'}'", "';'", "'->'", "'AND'", "'OR'", "'['", 
			"']'", "'isEmpty'", "'('", "')'", "'isNotEmpty'", "'match'", "','", "'notMatch'", 
			"'str'", "'length'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NUM", "OPERADOR_BOOL", "MUL_DIV", 
			"SOMA_SUB", "ATRIBUTO", "STRING", "WS", "ERR_CHAR"
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
	public String getGrammarFileName() { return "ValidacaoForm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ValidacaoFormParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	 
		public StartContext() { }
		public void copyFrom(StartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EndResultContext extends StartContext {
		public FormContext finalResult;
		public TerminalNode EOF() { return getToken(ValidacaoFormParser.EOF, 0); }
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public EndResultContext(StartContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterEndResult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitEndResult(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitEndResult(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			_localctx = new EndResultContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__0);
			setState(25);
			match(T__1);
			setState(26);
			((EndResultContext)_localctx).finalResult = form(0);
			setState(27);
			match(T__2);
			setState(28);
			match(EOF);
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

	public static class FormContext extends ParserRuleContext {
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
	 
		public FormContext() { }
		public void copyFrom(FormContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InstContext extends FormContext {
		public Instrucao_formContext verif;
		public Instrucao_formContext instrucao_form() {
			return getRuleContext(Instrucao_formContext.class,0);
		}
		public InstContext(FormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstRecContext extends FormContext {
		public Instrucao_formContext verif;
		public FormContext form() {
			return getRuleContext(FormContext.class,0);
		}
		public Instrucao_formContext instrucao_form() {
			return getRuleContext(Instrucao_formContext.class,0);
		}
		public InstRecContext(FormContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterInstRec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitInstRec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitInstRec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		return form(0);
	}

	private FormContext form(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormContext _localctx = new FormContext(_ctx, _parentState);
		FormContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_form, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new InstContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(31);
			((InstContext)_localctx).verif = instrucao_form();
			setState(32);
			match(T__3);
			}
			_ctx.stop = _input.LT(-1);
			setState(40);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InstRecContext(new FormContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_form);
					setState(34);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(35);
					((InstRecContext)_localctx).verif = instrucao_form();
					setState(36);
					match(T__3);
					}
					} 
				}
				setState(42);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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

	public static class Instrucao_formContext extends ParserRuleContext {
		public Instrucao_formContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instrucao_form; }
	 
		public Instrucao_formContext() { }
		public void copyFrom(Instrucao_formContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OpVerifyInstrucaoFormContext extends Instrucao_formContext {
		public Cond_verificacaoContext condicao;
		public ValidacoesContext valid;
		public Cond_verificacaoContext cond_verificacao() {
			return getRuleContext(Cond_verificacaoContext.class,0);
		}
		public ValidacoesContext validacoes() {
			return getRuleContext(ValidacoesContext.class,0);
		}
		public OpVerifyInstrucaoFormContext(Instrucao_formContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpVerifyInstrucaoForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpVerifyInstrucaoForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpVerifyInstrucaoForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instrucao_formContext instrucao_form() throws RecognitionException {
		Instrucao_formContext _localctx = new Instrucao_formContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instrucao_form);
		try {
			_localctx = new OpVerifyInstrucaoFormContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			((OpVerifyInstrucaoFormContext)_localctx).condicao = cond_verificacao(0);
			setState(44);
			match(T__4);
			setState(45);
			((OpVerifyInstrucaoFormContext)_localctx).valid = validacoes(0);
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
	public static class OpAndVerifContext extends Cond_verificacaoContext {
		public Cond_verificacaoContext left;
		public Cond_verificacaoContext right;
		public List<Cond_verificacaoContext> cond_verificacao() {
			return getRuleContexts(Cond_verificacaoContext.class);
		}
		public Cond_verificacaoContext cond_verificacao(int i) {
			return getRuleContext(Cond_verificacaoContext.class,i);
		}
		public OpAndVerifContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpAndVerif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpAndVerif(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpAndVerif(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpBoolVerifContext extends Cond_verificacaoContext {
		public Operacao_boolContext opBoolStack;
		public Operacao_boolContext operacao_bool() {
			return getRuleContext(Operacao_boolContext.class,0);
		}
		public OpBoolVerifContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpBoolVerif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpBoolVerif(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpBoolVerif(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpOrVerifContext extends Cond_verificacaoContext {
		public Cond_verificacaoContext left;
		public Cond_verificacaoContext right;
		public List<Cond_verificacaoContext> cond_verificacao() {
			return getRuleContexts(Cond_verificacaoContext.class);
		}
		public Cond_verificacaoContext cond_verificacao(int i) {
			return getRuleContext(Cond_verificacaoContext.class,i);
		}
		public OpOrVerifContext(Cond_verificacaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpOrVerif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpOrVerif(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpOrVerif(this);
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_cond_verificacao, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new OpBoolVerifContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(48);
			((OpBoolVerifContext)_localctx).opBoolStack = operacao_bool(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new OpAndVerifContext(new Cond_verificacaoContext(_parentctx, _parentState));
						((OpAndVerifContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_cond_verificacao);
						setState(50);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(51);
						match(T__5);
						setState(52);
						((OpAndVerifContext)_localctx).right = cond_verificacao(4);
						}
						break;
					case 2:
						{
						_localctx = new OpOrVerifContext(new Cond_verificacaoContext(_parentctx, _parentState));
						((OpOrVerifContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_cond_verificacao);
						setState(53);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(54);
						match(T__6);
						setState(55);
						((OpOrVerifContext)_localctx).right = cond_verificacao(3);
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class ValidacoesContext extends ParserRuleContext {
		public ValidacoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validacoes; }
	 
		public ValidacoesContext() { }
		public void copyFrom(ValidacoesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OpValidContext extends ValidacoesContext {
		public ValidacaoContext logicStack;
		public ValidacaoContext validacao() {
			return getRuleContext(ValidacaoContext.class,0);
		}
		public OpValidContext(ValidacoesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpValid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpValid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpValid(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpValidRecContext extends ValidacoesContext {
		public ValidacoesContext validStack;
		public ValidacaoContext logicStack;
		public ValidacoesContext validacoes() {
			return getRuleContext(ValidacoesContext.class,0);
		}
		public ValidacaoContext validacao() {
			return getRuleContext(ValidacaoContext.class,0);
		}
		public OpValidRecContext(ValidacoesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpValidRec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpValidRec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpValidRec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValidacoesContext validacoes() throws RecognitionException {
		return validacoes(0);
	}

	private ValidacoesContext validacoes(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValidacoesContext _localctx = new ValidacoesContext(_ctx, _parentState);
		ValidacoesContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_validacoes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new OpValidContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(62);
			((OpValidContext)_localctx).logicStack = validacao();
			}
			_ctx.stop = _input.LT(-1);
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new OpValidRecContext(new ValidacoesContext(_parentctx, _parentState));
					((OpValidRecContext)_localctx).validStack = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_validacoes);
					setState(64);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(65);
					((OpValidRecContext)_localctx).logicStack = validacao();
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class ValidacaoContext extends ParserRuleContext {
		public Operacoes_logicasContext operacoes_logicas() {
			return getRuleContext(Operacoes_logicasContext.class,0);
		}
		public ValidacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterValidacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitValidacao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitValidacao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValidacaoContext validacao() throws RecognitionException {
		ValidacaoContext _localctx = new ValidacaoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_validacao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__7);
			setState(72);
			operacoes_logicas(0);
			setState(73);
			match(T__8);
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

	public static class Operacoes_logicasContext extends ParserRuleContext {
		public Operacoes_logicasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacoes_logicas; }
	 
		public Operacoes_logicasContext() { }
		public void copyFrom(Operacoes_logicasContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InstFormContext extends Operacoes_logicasContext {
		public Instrucao_formContext instrucao_form() {
			return getRuleContext(Instrucao_formContext.class,0);
		}
		public InstFormContext(Operacoes_logicasContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterInstForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitInstForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitInstForm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpAndLogicContext extends Operacoes_logicasContext {
		public Operacoes_logicasContext left;
		public Operacoes_logicasContext right;
		public List<Operacoes_logicasContext> operacoes_logicas() {
			return getRuleContexts(Operacoes_logicasContext.class);
		}
		public Operacoes_logicasContext operacoes_logicas(int i) {
			return getRuleContext(Operacoes_logicasContext.class,i);
		}
		public OpAndLogicContext(Operacoes_logicasContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpAndLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpAndLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpAndLogic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncBoolContext extends Operacoes_logicasContext {
		public Funcao_boolContext funcao_bool() {
			return getRuleContext(Funcao_boolContext.class,0);
		}
		public FuncBoolContext(Operacoes_logicasContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterFuncBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitFuncBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitFuncBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpBoolLogicContext extends Operacoes_logicasContext {
		public Operacao_boolContext opBoolStack;
		public Operacao_boolContext operacao_bool() {
			return getRuleContext(Operacao_boolContext.class,0);
		}
		public OpBoolLogicContext(Operacoes_logicasContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpBoolLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpBoolLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpBoolLogic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpOrLogicContext extends Operacoes_logicasContext {
		public Operacoes_logicasContext left;
		public Operacoes_logicasContext right;
		public List<Operacoes_logicasContext> operacoes_logicas() {
			return getRuleContexts(Operacoes_logicasContext.class);
		}
		public Operacoes_logicasContext operacoes_logicas(int i) {
			return getRuleContext(Operacoes_logicasContext.class,i);
		}
		public OpOrLogicContext(Operacoes_logicasContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpOrLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpOrLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpOrLogic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacoes_logicasContext operacoes_logicas() throws RecognitionException {
		return operacoes_logicas(0);
	}

	private Operacoes_logicasContext operacoes_logicas(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Operacoes_logicasContext _localctx = new Operacoes_logicasContext(_ctx, _parentState);
		Operacoes_logicasContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_operacoes_logicas, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new OpBoolLogicContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(76);
				((OpBoolLogicContext)_localctx).opBoolStack = operacao_bool(0);
				}
				break;
			case 2:
				{
				_localctx = new FuncBoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				funcao_bool();
				}
				break;
			case 3:
				{
				_localctx = new InstFormContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				instrucao_form();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(87);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new OpAndLogicContext(new Operacoes_logicasContext(_parentctx, _parentState));
						((OpAndLogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_operacoes_logicas);
						setState(81);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(82);
						match(T__5);
						setState(83);
						((OpAndLogicContext)_localctx).right = operacoes_logicas(6);
						}
						break;
					case 2:
						{
						_localctx = new OpOrLogicContext(new Operacoes_logicasContext(_parentctx, _parentState));
						((OpOrLogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_operacoes_logicas);
						setState(84);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(85);
						match(T__6);
						setState(86);
						((OpOrLogicContext)_localctx).right = operacoes_logicas(5);
						}
						break;
					}
					} 
				}
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class Funcao_boolContext extends ParserRuleContext {
		public Funcao_boolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcao_bool; }
	 
		public Funcao_boolContext() { }
		public void copyFrom(Funcao_boolContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotMatchContext extends Funcao_boolContext {
		public AtributoContext attr;
		public RegexContext reg;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public RegexContext regex() {
			return getRuleContext(RegexContext.class,0);
		}
		public NotMatchContext(Funcao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterNotMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitNotMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitNotMatch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MatchContext extends Funcao_boolContext {
		public AtributoContext attr;
		public RegexContext reg;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public RegexContext regex() {
			return getRuleContext(RegexContext.class,0);
		}
		public MatchContext(Funcao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotEmptyContext extends Funcao_boolContext {
		public AtributoContext attr;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public NotEmptyContext(Funcao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterNotEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitNotEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitNotEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyContext extends Funcao_boolContext {
		public AtributoContext attr;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public EmptyContext(Funcao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Funcao_boolContext funcao_bool() throws RecognitionException {
		Funcao_boolContext _localctx = new Funcao_boolContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcao_bool);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				_localctx = new EmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(T__9);
				setState(93);
				match(T__10);
				setState(94);
				((EmptyContext)_localctx).attr = atributo();
				setState(95);
				match(T__11);
				}
				break;
			case T__12:
				_localctx = new NotEmptyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(T__12);
				setState(98);
				match(T__10);
				setState(99);
				((NotEmptyContext)_localctx).attr = atributo();
				setState(100);
				match(T__11);
				}
				break;
			case T__13:
				_localctx = new MatchContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(T__13);
				setState(103);
				match(T__10);
				setState(104);
				((MatchContext)_localctx).attr = atributo();
				setState(105);
				match(T__14);
				setState(106);
				((MatchContext)_localctx).reg = regex();
				setState(107);
				match(T__11);
				}
				break;
			case T__15:
				_localctx = new NotMatchContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				match(T__15);
				setState(110);
				match(T__10);
				setState(111);
				((NotMatchContext)_localctx).attr = atributo();
				setState(112);
				match(T__14);
				setState(113);
				((NotMatchContext)_localctx).reg = regex();
				setState(114);
				match(T__11);
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
	public static class OpStrContext extends Operacao_boolContext {
		public Token str;
		public TerminalNode STRING() { return getToken(ValidacaoFormParser.STRING, 0); }
		public OpStrContext(Operacao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpStr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpStr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpBoolAtrContext extends Operacao_boolContext {
		public AtributoContext attr;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public OpBoolAtrContext(Operacao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpBoolAtr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpBoolAtr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpBoolAtr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpBoolContext extends Operacao_boolContext {
		public Operacao_boolContext left;
		public Token op;
		public Operacao_boolContext right;
		public List<Operacao_boolContext> operacao_bool() {
			return getRuleContexts(Operacao_boolContext.class);
		}
		public Operacao_boolContext operacao_bool(int i) {
			return getRuleContext(Operacao_boolContext.class,i);
		}
		public TerminalNode OPERADOR_BOOL() { return getToken(ValidacaoFormParser.OPERADOR_BOOL, 0); }
		public OpBoolContext(Operacao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpIntContext extends Operacao_boolContext {
		public InteiroContext val;
		public InteiroContext inteiro() {
			return getRuleContext(InteiroContext.class,0);
		}
		public OpIntContext(Operacao_boolContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacao_boolContext operacao_bool() throws RecognitionException {
		return operacao_bool(0);
	}

	private Operacao_boolContext operacao_bool(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Operacao_boolContext _localctx = new Operacao_boolContext(_ctx, _parentState);
		Operacao_boolContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_operacao_bool, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				{
				_localctx = new OpBoolAtrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(119);
				match(T__16);
				setState(120);
				match(T__10);
				setState(121);
				((OpBoolAtrContext)_localctx).attr = atributo();
				setState(122);
				match(T__11);
				}
				break;
			case T__10:
			case T__17:
			case NUM:
			case MUL_DIV:
			case SOMA_SUB:
			case ATRIBUTO:
				{
				_localctx = new OpIntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				((OpIntContext)_localctx).val = inteiro(0);
				}
				break;
			case STRING:
				{
				_localctx = new OpStrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				((OpStrContext)_localctx).str = match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new OpBoolContext(new Operacao_boolContext(_parentctx, _parentState));
					((OpBoolContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_operacao_bool);
					setState(128);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(129);
					((OpBoolContext)_localctx).op = match(OPERADOR_BOOL);
					setState(130);
					((OpBoolContext)_localctx).right = operacao_bool(5);
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	public static class InteiroContext extends ParserRuleContext {
		public InteiroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inteiro; }
	 
		public InteiroContext() { }
		public void copyFrom(InteiroContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OpLenContext extends InteiroContext {
		public AtributoContext attr;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public OpLenContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpLen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpLen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpLen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OperrContext extends InteiroContext {
		public TerminalNode MUL_DIV() { return getToken(ValidacaoFormParser.MUL_DIV, 0); }
		public TerminalNode SOMA_SUB() { return getToken(ValidacaoFormParser.SOMA_SUB, 0); }
		public InteiroContext inteiro() {
			return getRuleContext(InteiroContext.class,0);
		}
		public OperrContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOperr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOperr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOperr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpNumContext extends InteiroContext {
		public Token num;
		public TerminalNode NUM() { return getToken(ValidacaoFormParser.NUM, 0); }
		public OpNumContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpNum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpNum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpSSContext extends InteiroContext {
		public InteiroContext left;
		public Token op;
		public InteiroContext right;
		public List<InteiroContext> inteiro() {
			return getRuleContexts(InteiroContext.class);
		}
		public InteiroContext inteiro(int i) {
			return getRuleContext(InteiroContext.class,i);
		}
		public TerminalNode SOMA_SUB() { return getToken(ValidacaoFormParser.SOMA_SUB, 0); }
		public OpSSContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpSS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpSS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpSS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpMDContext extends InteiroContext {
		public InteiroContext left;
		public Token op;
		public InteiroContext right;
		public List<InteiroContext> inteiro() {
			return getRuleContexts(InteiroContext.class);
		}
		public InteiroContext inteiro(int i) {
			return getRuleContext(InteiroContext.class,i);
		}
		public TerminalNode MUL_DIV() { return getToken(ValidacaoFormParser.MUL_DIV, 0); }
		public OpMDContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpMD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpMD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpMD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpParenContext extends InteiroContext {
		public InteiroContext val;
		public InteiroContext inteiro() {
			return getRuleContext(InteiroContext.class,0);
		}
		public OpParenContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpParen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpAtrContext extends InteiroContext {
		public AtributoContext attr;
		public AtributoContext atributo() {
			return getRuleContext(AtributoContext.class,0);
		}
		public OpAtrContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterOpAtr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitOpAtr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitOpAtr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InteiroContext inteiro() throws RecognitionException {
		return inteiro(0);
	}

	private InteiroContext inteiro(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InteiroContext _localctx = new InteiroContext(_ctx, _parentState);
		InteiroContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_inteiro, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new OpParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(137);
				match(T__10);
				setState(138);
				((OpParenContext)_localctx).val = inteiro(0);
				setState(139);
				match(T__11);
				}
				break;
			case 2:
				{
				_localctx = new OpLenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(T__17);
				setState(142);
				match(T__10);
				setState(143);
				((OpLenContext)_localctx).attr = atributo();
				setState(144);
				match(T__11);
				}
				break;
			case 3:
				{
				_localctx = new OpAtrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				((OpAtrContext)_localctx).attr = atributo();
				}
				break;
			case 4:
				{
				_localctx = new OpNumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				((OpNumContext)_localctx).num = match(NUM);
				}
				break;
			case 5:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(MUL_DIV);
				}
				break;
			case 6:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149);
				match(SOMA_SUB);
				}
				break;
			case 7:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(MUL_DIV);
				setState(151);
				inteiro(2);
				}
				break;
			case 8:
				{
				_localctx = new OperrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(SOMA_SUB);
				setState(153);
				inteiro(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(168);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(166);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new OpMDContext(new InteiroContext(_parentctx, _parentState));
						((OpMDContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inteiro);
						setState(156);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(157);
						((OpMDContext)_localctx).op = match(MUL_DIV);
						setState(158);
						((OpMDContext)_localctx).right = inteiro(12);
						}
						break;
					case 2:
						{
						_localctx = new OpSSContext(new InteiroContext(_parentctx, _parentState));
						((OpSSContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_inteiro);
						setState(159);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(160);
						((OpSSContext)_localctx).op = match(SOMA_SUB);
						setState(161);
						((OpSSContext)_localctx).right = inteiro(11);
						}
						break;
					case 3:
						{
						_localctx = new OperrContext(new InteiroContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_inteiro);
						setState(162);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(163);
						match(MUL_DIV);
						}
						break;
					case 4:
						{
						_localctx = new OperrContext(new InteiroContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_inteiro);
						setState(164);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(165);
						match(SOMA_SUB);
						}
						break;
					}
					} 
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class AtributoContext extends ParserRuleContext {
		public AtributoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributo; }
	 
		public AtributoContext() { }
		public void copyFrom(AtributoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AttributeContext extends AtributoContext {
		public Token name;
		public TerminalNode ATRIBUTO() { return getToken(ValidacaoFormParser.ATRIBUTO, 0); }
		public AttributeContext(AtributoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtributoContext atributo() throws RecognitionException {
		AtributoContext _localctx = new AtributoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_atributo);
		try {
			_localctx = new AttributeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			((AttributeContext)_localctx).name = match(ATRIBUTO);
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

	public static class RegexContext extends ParserRuleContext {
		public Token reg;
		public TerminalNode STRING() { return getToken(ValidacaoFormParser.STRING, 0); }
		public RegexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).enterRegex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ValidacaoFormListener ) ((ValidacaoFormListener)listener).exitRegex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ValidacaoFormVisitor ) return ((ValidacaoFormVisitor<? extends T>)visitor).visitRegex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexContext regex() throws RecognitionException {
		RegexContext _localctx = new RegexContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_regex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			((RegexContext)_localctx).reg = match(STRING);
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
			return form_sempred((FormContext)_localctx, predIndex);
		case 3:
			return cond_verificacao_sempred((Cond_verificacaoContext)_localctx, predIndex);
		case 4:
			return validacoes_sempred((ValidacoesContext)_localctx, predIndex);
		case 6:
			return operacoes_logicas_sempred((Operacoes_logicasContext)_localctx, predIndex);
		case 8:
			return operacao_bool_sempred((Operacao_boolContext)_localctx, predIndex);
		case 9:
			return inteiro_sempred((InteiroContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean form_sempred(FormContext _localctx, int predIndex) {
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
	private boolean validacoes_sempred(ValidacoesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean operacoes_logicas_sempred(Operacoes_logicasContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean operacao_bool_sempred(Operacao_boolContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean inteiro_sempred(InteiroContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 11);
		case 8:
			return precpred(_ctx, 10);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u00b2\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\3\6\3\6\3\6\3\6\3\6\7\6E\n\6\f"+
		"\6\16\6H\13\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bR\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\bZ\n\b\f\b\16\b]\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tw\n\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0081\n\n\3\n\3\n\3\n\7\n\u0086\n\n"+
		"\f\n\16\n\u0089\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009d\n\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00a9\n\13\f\13\16\13\u00ac\13"+
		"\13\3\f\3\f\3\r\3\r\3\r\2\b\4\b\n\16\22\24\16\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\2\2\2\u00be\2\32\3\2\2\2\4 \3\2\2\2\6-\3\2\2\2\b\61\3\2\2\2\n?"+
		"\3\2\2\2\fI\3\2\2\2\16Q\3\2\2\2\20v\3\2\2\2\22\u0080\3\2\2\2\24\u009c"+
		"\3\2\2\2\26\u00ad\3\2\2\2\30\u00af\3\2\2\2\32\33\7\3\2\2\33\34\7\4\2\2"+
		"\34\35\5\4\3\2\35\36\7\5\2\2\36\37\7\2\2\3\37\3\3\2\2\2 !\b\3\1\2!\"\5"+
		"\6\4\2\"#\7\6\2\2#*\3\2\2\2$%\f\4\2\2%&\5\6\4\2&\'\7\6\2\2\')\3\2\2\2"+
		"($\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\5\3\2\2\2,*\3\2\2\2-.\5\b\5"+
		"\2./\7\7\2\2/\60\5\n\6\2\60\7\3\2\2\2\61\62\b\5\1\2\62\63\5\22\n\2\63"+
		"<\3\2\2\2\64\65\f\5\2\2\65\66\7\b\2\2\66;\5\b\5\6\678\f\4\2\289\7\t\2"+
		"\29;\5\b\5\5:\64\3\2\2\2:\67\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\t"+
		"\3\2\2\2><\3\2\2\2?@\b\6\1\2@A\5\f\7\2AF\3\2\2\2BC\f\4\2\2CE\5\f\7\2D"+
		"B\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\13\3\2\2\2HF\3\2\2\2IJ\7\n\2"+
		"\2JK\5\16\b\2KL\7\13\2\2L\r\3\2\2\2MN\b\b\1\2NR\5\22\n\2OR\5\20\t\2PR"+
		"\5\6\4\2QM\3\2\2\2QO\3\2\2\2QP\3\2\2\2R[\3\2\2\2ST\f\7\2\2TU\7\b\2\2U"+
		"Z\5\16\b\bVW\f\6\2\2WX\7\t\2\2XZ\5\16\b\7YS\3\2\2\2YV\3\2\2\2Z]\3\2\2"+
		"\2[Y\3\2\2\2[\\\3\2\2\2\\\17\3\2\2\2][\3\2\2\2^_\7\f\2\2_`\7\r\2\2`a\5"+
		"\26\f\2ab\7\16\2\2bw\3\2\2\2cd\7\17\2\2de\7\r\2\2ef\5\26\f\2fg\7\16\2"+
		"\2gw\3\2\2\2hi\7\20\2\2ij\7\r\2\2jk\5\26\f\2kl\7\21\2\2lm\5\30\r\2mn\7"+
		"\16\2\2nw\3\2\2\2op\7\22\2\2pq\7\r\2\2qr\5\26\f\2rs\7\21\2\2st\5\30\r"+
		"\2tu\7\16\2\2uw\3\2\2\2v^\3\2\2\2vc\3\2\2\2vh\3\2\2\2vo\3\2\2\2w\21\3"+
		"\2\2\2xy\b\n\1\2yz\7\23\2\2z{\7\r\2\2{|\5\26\f\2|}\7\16\2\2}\u0081\3\2"+
		"\2\2~\u0081\5\24\13\2\177\u0081\7\32\2\2\u0080x\3\2\2\2\u0080~\3\2\2\2"+
		"\u0080\177\3\2\2\2\u0081\u0087\3\2\2\2\u0082\u0083\f\6\2\2\u0083\u0084"+
		"\7\26\2\2\u0084\u0086\5\22\n\7\u0085\u0082\3\2\2\2\u0086\u0089\3\2\2\2"+
		"\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\23\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u008a\u008b\b\13\1\2\u008b\u008c\7\r\2\2\u008c\u008d\5\24\13"+
		"\2\u008d\u008e\7\16\2\2\u008e\u009d\3\2\2\2\u008f\u0090\7\24\2\2\u0090"+
		"\u0091\7\r\2\2\u0091\u0092\5\26\f\2\u0092\u0093\7\16\2\2\u0093\u009d\3"+
		"\2\2\2\u0094\u009d\5\26\f\2\u0095\u009d\7\25\2\2\u0096\u009d\7\27\2\2"+
		"\u0097\u009d\7\30\2\2\u0098\u0099\7\27\2\2\u0099\u009d\5\24\13\4\u009a"+
		"\u009b\7\30\2\2\u009b\u009d\5\24\13\3\u009c\u008a\3\2\2\2\u009c\u008f"+
		"\3\2\2\2\u009c\u0094\3\2\2\2\u009c\u0095\3\2\2\2\u009c\u0096\3\2\2\2\u009c"+
		"\u0097\3\2\2\2\u009c\u0098\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u00aa\3\2"+
		"\2\2\u009e\u009f\f\r\2\2\u009f\u00a0\7\27\2\2\u00a0\u00a9\5\24\13\16\u00a1"+
		"\u00a2\f\f\2\2\u00a2\u00a3\7\30\2\2\u00a3\u00a9\5\24\13\r\u00a4\u00a5"+
		"\f\6\2\2\u00a5\u00a9\7\27\2\2\u00a6\u00a7\f\5\2\2\u00a7\u00a9\7\30\2\2"+
		"\u00a8\u009e\3\2\2\2\u00a8\u00a1\3\2\2\2\u00a8\u00a4\3\2\2\2\u00a8\u00a6"+
		"\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\25\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae\7\31\2\2\u00ae\27\3\2\2\2"+
		"\u00af\u00b0\7\32\2\2\u00b0\31\3\2\2\2\17*:<FQY[v\u0080\u0087\u009c\u00a8"+
		"\u00aa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}